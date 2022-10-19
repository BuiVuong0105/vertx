package com.vertx.vuong.verticle;

import java.util.UUID;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.Message;

public class HelloVerticle extends AbstractVerticle {

	private String verticleId = UUID.randomUUID().toString();

	@Override
	public void start(Promise<Void> start) {
		try {

//			System.out.println(String.format("[%s] Deploy HelloVerticle: %s, VerticleId: %s, Context: %s", Thread.currentThread().getName(), this.getClass().getName() ,verticleId, context));

			this.vertx.eventBus().consumer("address.hello", new Handler<Message<Object>>() {

				public void handle(Message<Object> event) {
					
					event.reply("Hello world");
				};
			});

			
			this.vertx.eventBus().consumer("address.hello.name", new Handler<Message<Object>>() {

				public void handle(Message<Object> event) {
					
					Context context = vertx.getOrCreateContext();

					System.out.println(String.format("Consum address.hello.name: %s, context: %s", Thread.currentThread().getName(), context));

					String name = (String) event.body();

					event.reply(String.format("Hello: %s ; from: %s ", name, verticleId));
				};
			});
			start.complete();
		} catch (Exception e) {
			start.fail(e);
		}
	}
}
