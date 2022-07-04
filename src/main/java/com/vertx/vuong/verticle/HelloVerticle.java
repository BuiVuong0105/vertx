package com.vertx.vuong.verticle;

import java.util.UUID;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.Message;

public class HelloVerticle extends AbstractVerticle {

	private String verticleId = UUID.randomUUID().toString();

	@Override
	public void start(Promise<Void> start) {

		try {

			System.out.println(String.format("Deploy Verticle: %s, VerticleId: %s, Thread: %s", this.getClass().getName() ,verticleId, Thread.currentThread().getName()));

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

					event.reply(String.format("Hello: %s ; from: %s ", name, verticleId));
				};
			});
			start.complete();
		} catch (Exception e) {
			start.fail(e);
		}
	}
}
