package com.vertx.vuong.verticle;

import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.shareddata.Lock;
import io.vertx.core.shareddata.SharedData;

public class HelloVerticle extends AbstractVerticle {
	
	private static final Logger LOGGER = LogManager.getLogger(HelloVerticle.class);

	private String verticleId = UUID.randomUUID().toString();

	@Override
	public void start(Promise<Void> start) {
		try {

			LOGGER.info("Deploy {} VerticleId: {}, Context: {}", this.getClass().getName() ,verticleId, context);

			this.vertx.eventBus().consumer("address.hello", event -> {
				ContextInternal context = (ContextInternal) vertx.getOrCreateContext();
				LOGGER.info("Consum address.hello context: {}", context.unwrap());
				
				SharedData sharedData = vertx.sharedData();

				sharedData.getLockWithTimeout("lockAddress.lock", 10000, res -> {
					if (res.succeeded()) {
						Lock lock = res.result();
						try {
							event.reply("Hello world");
						} catch (Exception e) {
							LOGGER.error("ERROR: {}", e);
						} finally {
							lock.release();
						}
						
					} else {
						LOGGER.info("Error: {}", res.cause());
						event.reply("lock fail");
					}
				});
			});

			
			this.vertx.eventBus().consumer("address.hello.name", event -> {
				ContextInternal context = (ContextInternal) vertx.getOrCreateContext();
				LOGGER.info("Consum address.hello.name context: {}", context.unwrap());
				String name = (String) event.body();
				event.reply(String.format("Hello: {} ; from: {} ", name, verticleId));
			});
			start.complete();
		} catch (Exception e) {
			start.fail(e);
		}
	}
}
