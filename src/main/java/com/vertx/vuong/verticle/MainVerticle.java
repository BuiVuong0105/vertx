package com.vertx.vuong.verticle;

import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.CompositeFuture;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class MainVerticle extends AbstractVerticle {

	@Override
	public void start() {
		
		System.out.println(String.format("Start Main Verticle: %s", Thread.currentThread().getName()));
		
		doConfig(this.vertx).compose(jsonObject -> doDeployVerticle(vertx, jsonObject))
		
				.onComplete(result -> {
					
					System.out.println(String.format("Deploy Vericle Succes: %s", Thread.currentThread().getName()));
				})
				
				.onFailure(throwable -> {
					
					System.out.println(String.format("Deploy Vericle Fail: %s, Thread: %s", throwable.toString(), Thread.currentThread().getName()));
				});
	}
	
	private Future<JsonObject> doConfig(Vertx vertx) {
		
		ConfigStoreOptions defaultConfig = new ConfigStoreOptions().setType("file").setFormat("json").setConfig(new JsonObject().put("path", "config.json"));
		
//		ConfigStoreOptions evnConfig = new ConfigStoreOptions().setType("file").setFormat("json").setConfig(new JsonObject().put("path", "./config/testconfig.json"));
		
		ConfigStoreOptions cliConfig = new ConfigStoreOptions().setType("json").setConfig(config());
		
		ConfigRetrieverOptions opts = new ConfigRetrieverOptions().addStore(defaultConfig).addStore(cliConfig);
		
		ConfigRetriever configRetriever = ConfigRetriever.create(vertx, opts);
		
		Future<JsonObject> future = Future.future(new Handler<Promise<JsonObject>>() {
			
			@Override
			public void handle(Promise<JsonObject> promise) {
				configRetriever.getConfig(promise);
			}
		});
		
		return future;
	}
	
	private Future<Void> doDeployVerticle(Vertx vertx, JsonObject jsonObject) {
		
		System.out.println(String.format("In Progess Verticle Thread: %s", Thread.currentThread().getName()));
		
		Future<String> futureGw = Future.future(promise -> vertx.deployVerticle(GatewayVerticle.class.getName(), new DeploymentOptions().setWorker(false).setConfig(jsonObject), promise));
		
		Future<String> futureHello = Future.future(promise -> vertx.deployVerticle(HelloVerticle.class.getName(), new DeploymentOptions().setWorker(true).setInstances(1).setConfig(jsonObject), promise));
		
		return CompositeFuture.all(futureGw, futureHello).mapEmpty();
	}
}
