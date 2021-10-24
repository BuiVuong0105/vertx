package com.vertx.vuong;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;

public class HelloVerticle extends AbstractVerticle {

	@Override
	public void start() {
		
		System.out.println(String.format("Start Vericle HELLO: %s", Thread.currentThread().getName()));

		this.vertx.eventBus().consumer("address.hello", new Handler<Message<Object>>() {
			
			public void handle(Message<Object> event) {
				
				System.out.println(String.format("Consum address.hello: %s", Thread.currentThread().getName()));
				
				event.reply("Hello world");
			};
		});
		
		this.vertx.eventBus().consumer("address.hello.name", new Handler<Message<Object>>() {
			
			public void handle(Message<Object> event) {
				
				System.out.println(String.format("Consum address.hello.name: %s", Thread.currentThread().getName()));
				
				String name = (String) event.body();
				
//				if("vuongbv".equals(name)) {
//					try {
//						Thread.sleep(5000);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
				
				event.reply(String.format("Hello: %s !", name));
			};
		});
	}
}
