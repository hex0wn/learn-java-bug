package com.satan;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Provider {
    public static void main(String[] args) throws Exception {
		System.setProperty("java.net.preferIPv4Stack", "true");
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "META-INF/spring/dubbo-demo-provider.xml" });
		context.start();
		System.out.println("Provider started.");
		System.in.read(); // press any key to exit
	}
}