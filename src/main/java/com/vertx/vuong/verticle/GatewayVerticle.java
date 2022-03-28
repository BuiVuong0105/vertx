package com.vertx.vuong.verticle;

import java.util.UUID;

import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
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
		
		System.out.println(String.format("Start Vericle GatewayVerticle, VerticleId: %s, Thread: %s", verticleId, Thread.currentThread().getName()));

		Router router = Router.router(vertx);
		
//		SessionStore sessionStore = ClusteredSessionStore.create(vertx); // Session trong cluster
		
		SessionStore sessionStore = LocalSessionStore.create(vertx); // Session trong local
		
		router.route().handler(SessionHandler.create(sessionStore));
		
		router.route().handler(CorsHandler.create("*"));
		
		router.route().handler(LoggerHandler.create());
		
		router.route().handler(CSRFHandler.create(vertx, "asdashdlasd4a45d4a2df45sdf2sdf1s53d@@@!##$#%#$%"));
		
		router.route().handler(context -> {

			String authToken = context.request().getHeader("auth_token");

			System.out.println(String.format("GatewayVerticle, Authen Token: %s, Thread: %s", authToken, Thread.currentThread().getName()));

			if ("vuongbv0105".equals(authToken)) {
				context.next();
			}

			else {
				context.response().setStatusCode(401).setStatusMessage("Access Denied...! ").end("Access Denied...! ");
			}
		});
		
		router.get("/api/v1/hello").handler(this::hello);

		router.get("/api/v1/:name").handler(this::helloIdentity);
		
		router.route().handler(StaticHandler.create("web").setIndexPage("index.html"));
		

		ConfigStoreOptions defaultConfig = new ConfigStoreOptions().setType("file").setFormat("json").setConfig(new JsonObject().put("path", "config.json"));
		
		ConfigStoreOptions cliConfig = new ConfigStoreOptions().setType("file").setFormat("json").setConfig(new JsonObject().put("path", "config.json") );
		
		ConfigRetrieverOptions opts = new ConfigRetrieverOptions().addStore(defaultConfig).addStore(cliConfig);
		
		ConfigRetriever configRetriever = ConfigRetriever.create(vertx, opts);
		
		Handler<AsyncResult<JsonObject>> handler =  new Handler<AsyncResult<JsonObject>>() {
			
			@Override
			public void handle(AsyncResult<JsonObject> asyncResult) {
				handleConfigResult(start, router, asyncResult);
			}
		};

		configRetriever.getConfig(handler);
		
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
	
	public void handleConfigResult(Promise<Void> start, Router router, AsyncResult<JsonObject> asyncResult) {

		if (!asyncResult.succeeded()) {

			start.fail("Unable to load configuration... !");

			return;
		}

		JsonObject config = asyncResult.result();

		JsonObject httpJsonObject = config.getJsonObject("http");

		int port = httpJsonObject.getInteger("port", 8080);

		System.out.println(String.format("PORT: %s", port));

		vertx.createHttpServer().requestHandler(router).listen(port);

		start.complete();
	}
}
