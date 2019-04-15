   ### pub-sub-domain: true
   这句配置设置为true时 只有topic起作用， false时候只有queue起作用， 默认false
   想要queue和topic同时起作用，需要配置两个监听工厂，一个setPubSubDomain(false)，一个setPubSubDomain(true)
   ### acknowledge-mode: 
   dups_ok： 批量确认
   auto：自动确认 
   client: 客户端手动确认
   ### question0:
   在client模式下，我没有手动确认消息，为什么服务端还是出队了
   ### answer0： not real right answer
   因为程序没有异常，在方法执行完后还是会自动ack，我们显示抛出一个异常就可以了。重试几次默认是6后会加入ActiveMQ.DLQ。
   客户端成功接收一条消息的标志是一条消息被签收，成功应答。
   消息的签收情形分两种：
   1、带事务的session
    如果session带有事务，并且事务成功提交，则消息被自动签收。如果事务回滚，则消息会被再次传送。
   2、不带事务的session
    不带事务的session的签收方式，取决于session的配置。
   Activemq支持一下三种模式：
     Session.AUTO_ACKNOWLEDGE  消息自动签收
     Session.CLIENT_ACKNOWLEDGE  客户端调用acknowledge方法手动签收
     Session.DUPS_OK_ACKNOWLEDGE 不必必须签收，消息可能会重复发送。在第二次重新传递消息的时候，消息头的JmsDelivered会被置为true标示当前消息已经传送过一次，客户端需要进行消息的重复处理控制。
   ### question1： 
   两个消费者时平分消息数量 一个延迟一秒处理结束 一个无延迟处理结束 100条消息一人处理50条 一个结束了另一个慢慢处理
   #### answer1：
   Broker发送消息给消费者，消费者在处理结束后会发送ack反馈给broker。为了提高消息分发的效率，引入了预取策略。即，Broker在未得到消费者的ack反馈之前，会继续发送新消息给它，除非消费者的消息缓存区已满，或是未收到反馈的消息数达到了prefetch上限。
   需要注意的是，消息被prefetch后，仍然会在ActiveMQ的控制台里处于Pending状态——直到它被实际消费，Broker收到了反馈，才会认为其Dequeued.
   明白了这个道理，就知道了上面提到的“消息似乎被预先指定给各个消费者”的原因了——因为消息都被prefetch了。这个时候，即便有新的消费者加入，它也没办法处理别人已经prefetch的消息
   
   ## ActiveMQ生产者和消费者优化策略 
   ### 生产者
    默认情况下，ActiveMQ服务端认为生产者端发送的是PERSISTENT Message。所以如果要发送NON_PERSISTENT Message，那么生产者端就要明确指定。发送NON_PERSISTENT Message时，消息发送方默认使用异步方式：即是说消息发送后发送方不会等待NON_PERSISTENT Message在服务端的任何回执。为避免MQ消息堆积但发送方不知道无法采取策略的情况，消息发送者会在发送一定大小的消息后等待服务端进行回执，可以通过代码设置回执点或者设置每次都等待服务端回执。connectionFactory.setProducerWindowSize(102400); 设置消息发送者在累计发送102400byte大小的消息后（可能是一条消息也可能是多条消息）
    等待服务端进行回执,以便确定之前发送的消息是否被正确处理，确定服务器端是否产生了过量的消息堆积，需要减慢消息生产端的生产速度。
    如果您不特意指定消息的发送类型，那么消息生产者默认发送PERSISTENT Meaage。这样的消息发送到ActiveMQ服务端后将被进行持久化存储（比较耗时），并且消息发送者默认等待ActiveMQ服务端对这条消息处理情况的回执。为了提高ActiveMQ在接受PERSISTENT Meaage时的性能，ActiveMQ允许开发人员遵从JMS API中的设置方式，为消息发送端在发送PERSISTENT Meaage时提供异步方式，connectionFactory.setUseAsyncSend(true);此时要设置回执点。
    JMS规范中支持带事务的消息，也就是说您可以启动一个事务（并由消息发送者的连接会话设置一个事务号Transaction ID），然后在事务中发送多条消息。这个事务提交前这些消息都不会进入队列（无论是Queue还是Topic）。
    生产流控制，是ActiveMQ消息生产者端最为重要的性能策略，它主要设定了在ActiveMQ服务节点在产生消息堆积，并超过限制大小的情况下，如何进行消息生产者端的限流。在ActiveMQ的主配置文件activemq.xml中，关于ProducerFlowControl策略的控制标签是“destinationPolicy”和它的子标签，可以配置每个队列是否启用生产者流控，以及每个Queue的最大内存限制。有关于policyEntry标签的所有配置选项都有完整说明：http://activemq.apache.org/per-destination-policies.html。
   spring.activemq.template.delivery-mode: persistent/non_persistent  持久化存储/非持久化存储
   发送NON_PERSISTENT Message时，消息发送方默认使用异步方式：即是说消息发送后发送方不会等待NON_PERSISTENT Message在服务端的任何回执
   这时候最好设置回值大小，即在消息大小达到设定值时，服务端返回一个回执。connectionFactory.setProducerWindowSize(102400);
   如果在异步情况下，需要每次都回执connectionFactory.setAlwaysSyncSend(true);
   
   如果您不特意指定消息的发送类型，那么消息生产者默认发送PERSISTENT Meaage。这样的消息发送到ActiveMQ服务端后将被进行持久化存储（持久化存储方案将在后文进行详细介绍），并且消息发送者默认等待ActiveMQ服务端对这条消息处理情况的回执。
   以上这个过程非常耗时，ActiveMQ服务端不但要接受消息，在内存中完成存储，并且按照ActiveMQ服务端设置的持久化存储方案对消息进行存储（主要的处理时间耗费在这里）。为了提高ActiveMQ在接受PERSISTENT Meaage时的性能，ActiveMQ允许开发人员遵从JMS API中的设置方式，为消息发送端在发送PERSISTENT Meaage时提供异步方式：
   connectionFactory.setUseAsyncSend(true); 一旦这么做了就必须设置回执窗口 connectionFactory.setProducerWindowSize(102400);
   ### 消费者
    比起消息生产者来说消息消费者的性能更能影响ActiveMQ系统的整体性能，因为要成功完成一条消息的处理，它的工作要远远多于消息生产者。默认情况下ActiveMQ服务端采用异步方式向客户端推送消息。也就是说ActiveMQ服务端在向某个消费者会话推送消息后，不会等待消费者的响应信息，直到消费者处理完消息后，主动向服务端返回处理结果。
     ActiveMQ系统中，默认的策略是ActiveMQ服务端一旦有消息，就主动按照设置的规则推送给当前活动的消费者。其中每次推送都有一定的数量限制，这个限制值就是prefetchSize。针对Queue工作模型的队列和Topic工作模型的队列，ActiveMQ有不同的默认“预取数量”；针对NON_PERSISTENT Message和PERSISTENT Message，ActiveMQ也有不同的默认“预取数量”：
    PERSISTENT Message—Queue：prefetchSize=1000
    NON_PERSISTENT Message—Queue：prefetchSize=1000
    PERSISTENT Message—Topic：prefetchSize=100
    NON_PERSISTENT Message—Topic：prefetchSize=32766
    ActiveMQ中设置的各种默认预取数量一般情况下不需要进行改变。但是非必要情况下，请不要设置prefetchSize=1，因为这样就是一条一条的取数据；也不要设置为prefetchSize=0，因为这将导致关闭服务器端的推送机制，改为客户端主动请求。
     JMS规范除了为消息生产者端提供事务支持以外，还为消费服务端准备了事务的支持。您可以通过在消费者端操作事务的commit和rollback方法，向服务器告知一组消息是否处理完成。采用事务的意义在于，一组消息要么被全部处理并确认成功，要么全部被回滚并重新处理。
     如果一条消息被不断的处理失败，那么最可能的情况就是这条消息承载的业务内容本身就有问题。那么无论重发多少次，这条消息还是会处理失败。为了解决这个问题，ActiveMQ中引入了“死信队列”（Dead Letter Queue）的概念。即一条消息再被重发了多次后（默认为重发6次redeliveryCounter==6），将会被ActiveMQ移入“死信队列”。开发人员可以在这个Queue中查看处理出错的消息，进行人工干预。默认情况下“死信队列”只接受PERSISTENT Message，如果NON_PERSISTENT Message超过了重发上限，将直接被删除。
  
 ### jmsTemplate
 jmsTemplate.sendAndReceive：阻塞接收来自消费者的消息