package com.vertx.vuong.verticle;

import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.Message;
import io.vertx.core.impl.ContextInternal;

public class HelloVerticle extends AbstractVerticle {
	
	private static final Logger LOGGER = LogManager.getLogger(HelloVerticle.class);

	private String verticleId = UUID.randomUUID().toString();

	@Override
	public void start(Promise<Void> start) {
		try {

			LOGGER.info("Deploy {} VerticleId: {}, Context: {}", this.getClass().getName() ,verticleId, context);

			this.vertx.eventBus().consumer("address.hello", new Handler<Message<Object>>() {

				public void handle(Message<Object> event) {
					
					ContextInternal context = (ContextInternal) vertx.getOrCreateContext();

					LOGGER.info("Consum address.hello context: {}", context.unwrap());
					
					event.reply("Hello world");
				};
			});

			
			this.vertx.eventBus().consumer("address.hello.name", new Handler<Message<Object>>() {

				public void handle(Message<Object> event) {
					
					ContextInternal context = (ContextInternal) vertx.getOrCreateContext();

					LOGGER.info("Consum address.hello.name context: {}", context.unwrap());

					String name = (String) event.body();

					event.reply(String.format("Hello: {} ; from: {} ", name, verticleId));
				};
			});
			start.complete();
		} catch (Exception e) {
			start.fail(e);
		}
	}
}
