# learn
springBoot learn project
#### 上下文
```
    //自动注入
    @Autowired
    WebApplicationContext webApplicationContext;
    
    //实现ApplicationContextAware接口
    @Component
    public class AppContextBean implements ApplicationContextAware {
    
        private static ApplicationContext context;
    
        @Override
        public synchronized void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            context = applicationContext;
        }
    
        public synchronized static ApplicationContext getApplicationContext() {
            return context;
        }
    }
    
    //启动类的返回值
    ConfigurableApplicationContext context = SpringApplication.run(LearningApplication.class, args);
```
