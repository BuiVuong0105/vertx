package com.vertx.vuong;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class GatewayVerticle extends AbstractVerticle {
	
	@Override
	public void start() {
		
		System.out.println(String.format("Start Vericle GatewayVerticle: %s", Thread.currentThread().getName()));
		
		Router router = Router.router(vertx);
		
		router.get("/api/v1/hello").handler(this::hello);
		
		router.get("/api/v1/:name").handler(this::helloIdentity);
		
		vertx.createHttpServer().requestHandler(router).listen(8080);
	}
	
	public void hello(RoutingContext ctx ) {
		
		System.out.println(String.format("Request: /api/v1/hello: %s", Thread.currentThread().getName()));
		
		this.vertx.eventBus().request("address.hello", "", new Handler<AsyncResult<Message<Object>>>() {
			
			public void handle(AsyncResult<Message<Object>> event) {
				
				System.out.println(String.format("Response: /api/v1/hello: %s", Thread.currentThread().getName()));
				
				ctx.request().response().end((String) event.result().body());
			};
		});
	}
	
	public void helloIdentity(RoutingContext ctx ) {
		
		System.out.println(String.format("Request: /api/v1/:name: %s", Thread.currentThread().getName()));
		
		String name = ctx.pathParam("name");
		
		this.vertx.eventBus().request("address.hello.name", name, new Handler<AsyncResult<Message<Object>>>() {
			
			@Override
			public void handle(AsyncResult<Message<Object>> event) {
				
				System.out.println(String.format("Response: /api/v1/:name: %s", Thread.currentThread().getName()));
				
				ctx.request().response().end((String) event.result().body());
			}
		});
	}
}
