package com.vertx.vuong.verticle;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.impl.ContextInternal;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.StaticHandler;

public class GatewayVerticle extends AbstractVerticle {
	
	private static final Logger LOGGER = LogManager.getLogger(GatewayVerticle.class);

	private String verticleId = UUID.randomUUID().toString();

	@Override
	public void start(Promise<Void> start) {
		
		LOGGER.info("Deploy {}  VerticleId: {}, Context: {}", this.getClass().getName() ,verticleId, context);
		
		Router router = Router.router(vertx);
		
		router.get("/api/v1/hello").handler(this::hello);
		
		router.get("/api/v1/hello/:name").handler(this::helloIdentity);
		
		router.get("/api/v1/encode").handler(this::encode);
		
		router.get("/api/v1/decode").handler(this::decode);
		
		router.route().handler(StaticHandler.create("web").setIndexPage("index.html"));
		
		int port = 8080;
		
		try {
			String portEvn = System.getenv("http.port");
			if(StringUtils.isNotBlank(portEvn)) {
				port = Integer.parseInt(portEvn);
			}
			else {
				port = Integer.parseInt(System.getProperty("http.port"));
			}
		} catch (Exception e) {
			LOGGER.error("ERROR: {}", e);
		}

		vertx.createHttpServer().requestHandler(router).listen(port);
		
		start.complete();

		LOGGER.info("HttpServer Start Success With Port: {}", port);
	}
	
	private void hello(RoutingContext ctx) {
		
		ContextInternal context = (ContextInternal) vertx.getOrCreateContext();

		LOGGER.info("Request: {}, Context: {}",ctx.request().path(),context.unwrap());

		this.vertx.eventBus().request("address.hello", "", event -> {
			ContextInternal contextSub = (ContextInternal) vertx.getOrCreateContext();
			LOGGER.info("Response: {}", ctx.request().path()  ,contextSub.unwrap());
			String rsp = (String) event.result().body();
			ctx.request().response().end(rsp);
		});
	}

	private void helloIdentity(RoutingContext ctx) {
		
		ContextInternal context = (ContextInternal) vertx.getOrCreateContext();
		
		LOGGER.info("Request: {}, Context: {}",ctx.request().path(),context.unwrap());
		
		String name = ctx.pathParam("name");
		
		this.vertx.eventBus().request("address.hello.name", name, event -> {
			ContextInternal contextSub = (ContextInternal) vertx.getOrCreateContext();
			LOGGER.info("Response: {}", ctx.request().path()  ,contextSub.unwrap());
			ctx.request().response().end((String) event.result().body());
		});
	}
	
	private void encode(RoutingContext ctx) {
		String name = ctx.queryParam("username").get(0);
		
		ContextInternal context = (ContextInternal) vertx.getOrCreateContext();
		LOGGER.info("Request: {}, Context: {}",ctx.request().path(),context.unwrap());
		
		this.vertx.eventBus().request("address.jwt.encode", name, event -> {
			ContextInternal contextSub = (ContextInternal) vertx.getOrCreateContext();
			LOGGER.info("Response: {}", ctx.request().path()  ,contextSub.unwrap());
			ctx.request().response().end((String) event.result().body());
		});
	}
	
	private void decode(RoutingContext ctx) {
		String token = ctx.queryParam("token").get(0);
		
		ContextInternal context = (ContextInternal) vertx.getOrCreateContext();
		LOGGER.info("Request: {}, Context: {}",ctx.request().path(),context.unwrap());
		
		this.vertx.eventBus().request("address.jwt.decode", token, event -> {
			ContextInternal contextSub = (ContextInternal) vertx.getOrCreateContext();
			LOGGER.info("Response: {}", ctx.request().path()  ,contextSub.unwrap());
			ctx.request().response().end((String) event.result().body());
		});
	}
}
