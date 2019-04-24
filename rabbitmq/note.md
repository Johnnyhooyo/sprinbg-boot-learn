RabbitMQ 是一个开源的 AMQP（高级消息队列协议） 实现，服务器端用Erlang语言编写.
### 相关概念
-
**虚拟主机**   
一个虚拟主机持有一组交换机、队列和绑定。为什么需要多个虚拟主机呢？很简单， RabbitMQ 当中，用户只能在虚拟主机的粒度进行权限控制。 因此，如果需要禁止A组访问B组的交换机/队列/绑定，必须为A和B分别创建一个虚拟主机。每一个 RabbitMQ 服务器都有一个默认的虚拟主机“/”。    
**交换机**            
Exchange 用于转发消息，但是它不会做存储 ，如果没有 Queue bind 到 Exchange 的话，它会直接丢弃掉 Producer 发送过来的消息。
这里有一个比较重要的概念：路由键 。消息到交换机的时候，交互机会转发到对应的队列中，那么究竟转发到哪个队列，就要根据该路由键。
+ Direct：（默认）"先匹配, 再投送". 即在绑定时设定一个 routing_key, 消息的routing_key 匹配时, 才会被交换器投送到绑定的队列中去.可以多个队列绑定同一个交换机。
+ Topic：按规则转发消息（最灵活）。根据通配符来转发，其他和direct类似。*可以匹配句点之间的字符，#可以匹配任意字符，包括句点。
+ Headers：设置 header attribute 参数类型的交换机，根据消息中的headers属性和队列与交换机绑定时候设定的键值对规则匹配，x-match参数可以有any和all，any是只要有一组键值对匹配上就会发送消息到相应队列，all则需所有键值对都匹配上才可以。
+ Fanout：转发消息到所有绑定队列，不管所有规则，只要绑定了就会发送。
+ Default Exchange: 这种是特殊的Direct Exchange，是rabbitmq内部默认的一个交换机。该交换机的name是空字符串，所有queue都默认binding 到该交换机上。所有binding到该交换机上的queue，routing-key都和queue的name一样。

headers表越大性能越差

**绑定**        
也就是交换机需要和队列相绑定，是多对多的关系。   
**Channel**  
网络信道，几乎所有的操作都在Channel中进行，Channel是进行消息读写的通道。客户端可以建立多个Channel，每个Channel代表一个会话任务。如果每一次访问RabbitMQ都建立一个Connection，在消息量大的时候建立TCP Connection的开销将是巨大的，效率也较低。Channel是在connection内部建立的逻辑连接，如果应用程序支持多线程，通常每个thread创建单独的channel进行通讯，AMQP method包含了channel id帮助客户端和message broker识别channel，所以channel之间是完全隔离的。Channel作为轻量级的Connection极大减少了操作系统建立TCP connection的开销。  

**Default Exchange**  
rabbitTemplate.convertAndSend("hello rabbit");这种没有路由健的消息会丢失，不会给到任何queue --> mandatory  
rabbitTemplate.convertAndSend("simpleQueue", "hello simple");  指定了routing key 发送给指定的queue  
rabbitTemplate.convertAndSend("testQueue", "hello testQueue");  如果同一个queue有多个消费者 会按prefetch均匀分配  
消费者queue的缓冲数量  如果有多（n）个消费者 再n*10个消息内就是平均分配，多余的就是那个消费快给那个
### 消息持久化
1. exchange持久化，在声明时指定durable => true
2. queue持久化，在声明时指定durable => true
3. 消息持久化，在投递时指定delivery_mode=> 2（1是非持久化）
如果是在集群中，可以设置为高可用，即消息会存储在多台服务器中。

消息发布的性能权衡

平衡投递速度和可靠投递
使用mandatory设置，正确投递不会有相应，错误投递会抛异常。（待确认）
发布者确认，不与事务一起工作，是事务的轻量化代替方案
备用交换器，错误的消息会被你设置的路由到死信队列（dead-letter）如果此时设置了mandatory标志也不会给发布者返回信息，而是被认为正确投递。exhange的alternate-exchange
HA队列（集群模式）来避免单点故障
delivery-mode=2来持久化消息，性能影响比较大；可以增加RAM，以便将磁盘页面保留在内核磁盘缓存中。
TCP背压和连接阻塞，阻止单点发布者的恶意请求或者异常（太多）请求

消息接收的性能权衡

尽量不使用主动拉取，而是使用监听
使用no-ack会提高性能，却会丢失消息，权衡点：prefetch的大小
QoS，服务质量，调整预取值到合适
一次确认多条消息性能比单条提升1.5倍（在预取量达到1000及以上）
事务，不适用于金庸确认的消费者，影响性能不过在不设置qos时，使用事务批量确认消息时性能有略微提升
reject-拒绝单条消息可以设置redelivered重新投递或者丢弃，Nack批量不确认
死信交换器，过期或者被拒绝的消息，当作queue的x-dead-letter-exchange参数传入即可
自动删除队列auto-delete=true，单消费者队列exclusivetrue，自动过期队列x-expires=毫秒
### yml配置
spring.rabbitmq.addresses= # Comma-separated list of addresses to which the client should connect.
spring.rabbitmq.cache.channel.checkout-timeout= # Number of milliseconds to wait to obtain a channel if the cache size has been reached.
spring.rabbitmq.cache.channel.size= # Number of channels to retain in the cache.
spring.rabbitmq.cache.connection.mode=channel # Connection factory cache mode.
spring.rabbitmq.cache.connection.size= # Number of connections to cache.
spring.rabbitmq.connection-timeout= # Connection timeout, in milliseconds; zero for infinite.
spring.rabbitmq.dynamic=true # Create an AmqpAdmin bean.
spring.rabbitmq.host=localhost # RabbitMQ host.
spring.rabbitmq.listener.simple.acknowledge-mode= # Acknowledge mode of container.
spring.rabbitmq.listener.simple.auto-startup=true # Start the container automatically on startup.
spring.rabbitmq.listener.simple.concurrency= # Minimum number of consumers.
spring.rabbitmq.listener.simple.default-requeue-rejected= # Whether or not to requeue delivery failures; default true.
spring.rabbitmq.listener.simple.idle-event-interval= # How often idle container events should be published in milliseconds.
spring.rabbitmq.listener.simple.max-concurrency= # Maximum number of consumers.
spring.rabbitmq.listener.simple.prefetch= # Number of messages to be handled in a single request. It should be greater than or equal to the transaction size (if used).
spring.rabbitmq.listener.simple.retry.enabled=false # Whether or not publishing retries are enabled.
spring.rabbitmq.listener.simple.retry.initial-interval=1000 # Interval between the first and second attempt to deliver a message.
spring.rabbitmq.listener.simple.retry.max-attempts=3 # Maximum number of attempts to deliver a message.
spring.rabbitmq.listener.simple.retry.max-interval=10000 # Maximum interval between attempts.
spring.rabbitmq.listener.simple.retry.multiplier=1.0 # A multiplier to apply to the previous delivery retry interval.
spring.rabbitmq.listener.simple.retry.stateless=true # Whether or not retry is stateless or stateful.
spring.rabbitmq.listener.simple.transaction-size= # Number of messages to be processed in a transaction. For best results it should be less than or equal to the prefetch count.
spring.rabbitmq.password= # Login to authenticate against the broker.
spring.rabbitmq.port=5672 # RabbitMQ port.
spring.rabbitmq.publisher-confirms=false # Enable publisher confirms.
spring.rabbitmq.publisher-returns=false # Enable publisher returns.
spring.rabbitmq.requested-heartbeat= # Requested heartbeat timeout, in seconds; zero for none.
spring.rabbitmq.ssl.enabled=false # Enable SSL support.
spring.rabbitmq.ssl.key-store= # Path to the key store that holds the SSL certificate.
spring.rabbitmq.ssl.key-store-password= # Password used to access the key store.
spring.rabbitmq.ssl.trust-store= # Trust store that holds SSL certificates.
spring.rabbitmq.ssl.trust-store-password= # Password used to access the trust store.
spring.rabbitmq.ssl.algorithm= # SSL algorithm to use. By default configure by the rabbit client library.
spring.rabbitmq.template.mandatory=false # Enable mandatory messages.
spring.rabbitmq.template.receive-timeout=0 # Timeout for receive() methods.
spring.rabbitmq.template.reply-timeout=5000 # Timeout for sendAndReceive() methods.
spring.rabbitmq.template.retry.enabled=false # Set to true to enable retries in the RabbitTemplate.
spring.rabbitmq.template.retry.initial-interval=1000 # Interval between the first and second attempt to publish a message.
spring.rabbitmq.template.retry.max-attempts=3 # Maximum number of attempts to publish a message.
spring.rabbitmq.template.retry.max-interval=10000 # Maximum number of attempts to publish a message.
spring.rabbitmq.template.retry.multiplier=1.0 # A multiplier to apply to the previous publishing retry interval.
spring.rabbitmq.username= # Login user to authenticate to the broker.
spring.rabbitmq.virtual-host= # Virtual host to use when connecting to the broker.


---
### 备注
教程：https://www.rabbitmq.com/getstarted.html
博客：https://blog.csdn.net/anzhsoft2008/column/info/rabbitmq
publisher confirms: https://www.jianshu.com/p/0db95a3e972c