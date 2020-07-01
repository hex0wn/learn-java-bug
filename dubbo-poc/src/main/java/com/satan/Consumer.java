package com.satan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class Consumer 
{
    public static void main( String[] args )
    {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"META-INF/spring/dubbo-demo-consumer.xml"});
        context.start();
        DefaultService myService = (DefaultService)context.getBean("myService"); // 获取远程服务代理
        String hello = myService.sayHello("world"); // 执行远程方法
        System.out.println( hello ); // 显示调用结果
    }
}
