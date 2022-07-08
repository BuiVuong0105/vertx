package com.vertx.vuong.verticle;

import java.util.UUID;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.WorkerExecutor;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.CSRFHandler;
import io.vertx.ext.web.handler.CorsHandler;
import io.vertx.ext.web.handler.LoggerHandler;
import io.vertx.ext.web.handler.SessionHandler;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.sstore.LocalSessionStore;
import io.vertx.ext.web.sstore.SessionStore;

public class GatewayVerticle extends AbstractVerticle {

	private String verticleId = UUID.randomUUID().toString();

	@Override
	public void start(Promise<Void> start) {
		
		System.out.println(String.format("Deploy Verticle: %s, VerticleId: %s, Thread: %s", this.getClass().getName() ,verticleId, Thread.currentThread().getName()));
		
		Router router = Router.router(vertx);
		
//		router.route().handler(context -> {
//			String authToken = context.request().getHeader("auth_token");
//			if ("vuongbv0105".equals(authToken)) {
//				context.next();
//			}else {
//				context.response().setStatusCode(401).setStatusMessage("Access Denied...! ").end("Access Denied...! ");
//			}
//		});
		
//		WorkerExecutor executor = vertx.createSharedWorkerExecutor("bvv-worker");
		router.get("/api/v1/hello").handler(this::hello);
		router.get("/api/v1/hello/:name").handler(this::helloIdentity);
		router.get("/api/v1/execute/:name").handler(r -> execute(r, null));
		
		router.route().handler(StaticHandler.create("web").setIndexPage("index.html"));
		
		JsonObject config = config();

		JsonObject httpJsonObject = config.getJsonObject("http");

		int port = httpJsonObject.getInteger("port", 8080);

		vertx.createHttpServer().requestHandler(router).listen(port);
		
		start.complete();

		System.out.println(String.format("HttpServer Start Success With Port: %s", port));
	}
	
	public void hello(RoutingContext ctx) {

		System.out.println(String.format("Request: /api/v1/hello: %s", Thread.currentThread().getName()));

		this.vertx.eventBus().request("address.hello", "", new Handler<AsyncResult<Message<Object>>>() {

			public void handle(AsyncResult<Message<Object>> event) {

				System.out.println(String.format("Response: /api/v1/hello: %s", Thread.currentThread().getName()));

				ctx.request().response().end((String) event.result().body());
			};
		});
	}

	public void helloIdentity(RoutingContext ctx) {
		
		System.out.println(String.format("Request: /api/v1/hello/:name: %s", Thread.currentThread().getName()));
		
		String name = ctx.pathParam("name");
		
		this.vertx.eventBus().request("address.hello.name", name, new Handler<AsyncResult<Message<Object>>>() {
			
			@Override
			public void handle(AsyncResult<Message<Object>> event) {
				
				System.out.println(String.format("Response: /api/v1/:name: %s", Thread.currentThread().getName()));
				
				ctx.request().response().end((String) event.result().body());
			}
		});
	}
	
	public void execute(RoutingContext ctx, WorkerExecutor executor) {
		
		System.out.println(String.format("Request: /api/v1/execute/:name: %s", Thread.currentThread().getName()));
		
		executeBlockingCode(executor);
		
		ctx.request().response().end("ok");
	}
	
	// Run blocking code trong worker thread and execute handle result in eventloop
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
	
	// Sequential Composition - Do A, Then B, Then C .... Handler Errors
	// Concurrent Composition - Do A and B and C and D .... and once all/any complete - So something else ...
	private void executeAsynchronous() {
		
	}
	
	private void configExtra(Router router) {
		
		SessionStore sessionStore = LocalSessionStore.create(vertx); // Session trong local || ClusteredSessionStore.create(vertx); // Session trong cluster
		
		router.route().handler(SessionHandler.create(sessionStore));
		
		router.route().handler(CorsHandler.create("*"));
		
		router.route().handler(LoggerHandler.create());
		
		router.route().handler(CSRFHandler.create(vertx, "asdashdlasd4a45d4a2df45sdf2sdf1s53d@@@!##$#%#$%"));
	}
}
