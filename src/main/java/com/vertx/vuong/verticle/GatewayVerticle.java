package com.vertx.vuong.verticle;

import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.WorkerExecutor;
import io.vertx.core.eventbus.Message;
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
		
//		router.route().handler(context -> {
//			String authToken = context.request().getHeader("auth_token");
//			if ("vuongbv0105".equals(authToken)) {
//				context.next();
//			}else {
//				context.response().setStatusCode(401).setStatusMessage("Access Denied...! ").end("Access Denied...! ");
//			}
//		});
		
		router.get("/api/v1/hello").handler(this::hello);
		router.get("/api/v1/hello/:name").handler(this::helloIdentity);
		router.get("/api/v1/execute/:name").handler(r -> execute(r, null));
		
		router.route().handler(StaticHandler.create("web").setIndexPage("index.html"));
		
		int port = Integer.parseInt(System.getProperty("http.port", "8080"));

		vertx.createHttpServer().requestHandler(router).listen(port);
		
		start.complete();

		LOGGER.info("HttpServer Start Success With Port: {}", port);
	}
	
	public void hello(RoutingContext ctx) {
		
		ContextInternal context = (ContextInternal) vertx.getOrCreateContext();

		LOGGER.info("Request: /api/v1/hello, Context: {}", context.unwrap());

		this.vertx.eventBus().request("address.hello", "", new Handler<AsyncResult<Message<Object>>>() {

			public void handle(AsyncResult<Message<Object>> event) {
				ContextInternal context = (ContextInternal) vertx.getOrCreateContext();
				LOGGER.info("Response: /api/v1/hello: {}", context.unwrap());
				String rsp = (String) event.result().body();
				ctx.request().response().end(rsp);
			};
		});
	}

	public void helloIdentity(RoutingContext ctx) {
		
		ContextInternal context = (ContextInternal) vertx.getOrCreateContext();
		
		LOGGER.info("Request: /api/v1/hello/:name, Context: {}",context.unwrap());
		
		String name = ctx.pathParam("name");
		
		this.vertx.eventBus().request("address.hello.name", name, new Handler<AsyncResult<Message<Object>>>() {
			
			@Override
			public void handle(AsyncResult<Message<Object>> event) {
				ContextInternal context = (ContextInternal) vertx.getOrCreateContext();
				System.out.println(String.format("Response: /api/v1/:name: %s, context: %s", Thread.currentThread().getName(), context.unwrap()));
				ctx.request().response().end((String) event.result().body());
			}
		});
	}
	
	public void execute(RoutingContext ctx, WorkerExecutor executor) {
		
		executeBlockingCode(executor);
		
		ctx.request().response().end("ok");
	}
	
	private void executeBlockingCode(WorkerExecutor executor) {
		for (int i = 0; i <= 0; i++) {
			int index = i;
			vertx.executeBlocking(new Handler<Promise<String>>() {
				@Override
				public void handle(Promise<String> event) {
					System.out.println(String.format("Blocking Handler %s, Thread: %s", index, Thread.currentThread().getName()));
					try {
						Thread.sleep(500000);
					} catch (InterruptedException e) {
						e.printStackTrace();
						event.fail(e);
					}
					event.complete("ok");
				}
			}, false, new Handler<AsyncResult<String>>() {
				@Override
				public void handle(AsyncResult<String> event) {
					System.out.println(String.format("Result Handler %s, Thread: %s", index, Thread.currentThread().getName()));
				}
			});
			System.out.println(String.format("End Index %s, Thread: %s", index, Thread.currentThread().getName()));
		}
	}
}
