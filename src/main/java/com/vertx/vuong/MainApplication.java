package com.vertx.vuong;

import com.vertx.vuong.verticle.DatabaseVerticle;
import com.vertx.vuong.verticle.GatewayVerticle;
import com.vertx.vuong.verticle.HelloVerticle;

import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Context;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.impl.VertxImpl;
import io.vertx.core.json.JsonObject;
import io.vertx.pgclient.PgPool;

// Clustering dựa vào lệnh java -jar target/vertx-dev-1.0.0-SNAPSHOT.jar -cluster -Djava.net.preferIPv4Stack=true -Dhttp.port=8090
// Đây là khai báo cluster nếu chạy dựa trên đối tượng vertx của hệ thống tạo ra sẵn, còn nếu tự tạo vertx riêng thì phải cấu hình cluster
// Cluster application phân tải công việc đến từng node thông qua eventbus
// java -jar target/vertx-dev-1.0.0-SNAPSHOT.jar --conf config/testconfig1.json --options config/vertxoption.json
public class MainApplication extends AbstractVerticle {

	public static void main(String[] args) throws InterruptedException {
		new MainApplication().start();
	}
	
	@Override
	public void start() throws InterruptedException {
		
		System.out.println(String.format("Start MainApplication: %s", Thread.currentThread().getName()));
		
//		final PgPool dbClient = DbUtils.buildDbClient(vertx);
		
		Vertx vertx = Vertx.vertx(new VertxOptions().setEventLoopPoolSize(10).setWorkerPoolSize(3).setBlockedThreadCheckInterval(5 * 10 * 1000));
		
//		vertx.executeBlocking(new Handler<Promise<String>>() {
//			@Override
//			public void handle(Promise<String> event) {
//				System.out.println(String.format("Blocking Handler %s, Thread: %s", 1, Thread.currentThread().getName()));
//				event.complete("ok");
//			}
//		}, false, new Handler<AsyncResult<String>>() {
//			@Override
//			public void handle(AsyncResult<String> event) {
//				System.out.println(String.format("Result Handler %s, Thread: %s", 1, Thread.currentThread().getName()));
//			}
//		});

		
		final Context context = ((VertxImpl)vertx).createWorkerContext();
		context.put("data", "hello");
		context.runOnContext((v) -> {
		  String hello = context.get("data");
		  System.out.println("Run worker lan 1" + Thread.currentThread().getName());
		});
		
		Thread.sleep(10000);

		context.runOnContext((v) -> {
			  String hello = context.get("data");
			  System.out.println("Run worker lan 2" + Thread.currentThread().getName());
			});

		
//		doConfig(vertx).compose(jsonObject -> doDeployVerticle(vertx, jsonObject))
//		
//				.onComplete(result -> {
//					System.out.println(String.format("Deploy Vericle Succes: %s", Thread.currentThread().getName()));
//				})
//				
//				.onFailure(throwable -> {
//					System.out.println(String.format("Deploy Vericle Fail: %s, Thread: %s", throwable.toString(), Thread.currentThread().getName()));
//				});
	}
	
	private Future<JsonObject> doConfig(Vertx vertx) {
		
//		ConfigStoreOptions evnConfig = new ConfigStoreOptions().setType("file").setFormat("json").setConfig(new JsonObject().put("path", "./config/testconfig.json"));
//		ConfigStoreOptions cliConfig = new ConfigStoreOptions().setType("json").setConfig(config());
		
		ConfigStoreOptions defaultConfig = new ConfigStoreOptions().setType("file").setFormat("json").setConfig(new JsonObject().put("path", "./config/testconfig.json"));
		
		ConfigRetrieverOptions opts = new ConfigRetrieverOptions().addStore(defaultConfig);
		
		ConfigRetriever configRetriever = ConfigRetriever.create(vertx, opts);
		
		Future<JsonObject> future = Future.future(new Handler<Promise<JsonObject>>() {
			@Override
			public void handle(Promise<JsonObject> promise) {
				System.out.println(String.format("Future Get Config Thread: %s", Thread.currentThread().getName()));
				configRetriever.getConfig(promise);
			}
		});
		
		return future;
	}
	
	private Future<Void> doDeployVerticle(Vertx vertx, JsonObject jsonObject) {
		
		System.out.println(String.format("In Progess Verticle Thread: %s", Thread.currentThread().getName()));
		
		Future<String> futureGw = Future.future(promise -> {
			System.out.println(String.format("Future GatewayVerticle Thread: %s", Thread.currentThread().getName()));
			vertx.deployVerticle(GatewayVerticle.class.getName(), new DeploymentOptions().setConfig(jsonObject), promise);
		});
		
		Future<String> futureHello = Future.future(promise -> {
			System.out.println(String.format("Future HelloVerticle Thread: %s", Thread.currentThread().getName()));
			vertx.deployVerticle(HelloVerticle.class.getName(), new DeploymentOptions().setInstances(3).setWorker(true).setConfig(jsonObject), promise);
		});
		
		Future<String> futureDatabase = Future.future(promise -> {
			System.out.println(String.format("Future DatabaseVerticle Thread: %s", Thread.currentThread().getName()));
			vertx.deployVerticle(DatabaseVerticle.class.getName(), new DeploymentOptions().setWorker(true).setConfig(jsonObject), promise);
		});
		
		return CompositeFuture.all(futureGw, futureHello, futureDatabase).mapEmpty();
	}
}

//ConfigStoreOptions defaultConfig = new ConfigStoreOptions().setType("file").setFormat("json").setConfig(new JsonObject().put("path", "config.json"));
//
//ConfigStoreOptions cliConfig = new ConfigStoreOptions().setType("file").setFormat("json").setConfig(new JsonObject().put("path", "config.json") );
//
//ConfigRetrieverOptions opts = new ConfigRetrieverOptions().addStore(defaultConfig).addStore(cliConfig);
//
//ConfigRetriever configRetriever = ConfigRetriever.create(vertx, opts);
//
//configRetriever.getConfig(new Handler<AsyncResult<JsonObject>>() {
//	
//	@Override
//	public void handle(AsyncResult<JsonObject> event) {
//
//		JsonObject jsonObject = event.result();
//
//		if (event.succeeded()) {
//			
//			System.out.println(String.format("Load Config Success: %s, Thread: %s", event.toString(), Thread.currentThread().getName()));
//			
//			System.out.println(String.format("In Progess Verticle Thread: %s", Thread.currentThread().getName()));
//
//			Future<String> futureGw = vertx.deployVerticle(GatewayVerticle.class.getName(), new DeploymentOptions().setConfig(jsonObject));
//
//			Future<String> futureHello = vertx.deployVerticle(HelloVerticle.class.getName(), new DeploymentOptions().setInstances(2).setConfig(jsonObject));
//
//			CompositeFuture.all(futureGw, futureHello)
//
//					.onComplete(new Handler<AsyncResult<CompositeFuture>>() {
//						
//						@Override
//						public void handle(AsyncResult<CompositeFuture> event) {
//							System.out.println(String.format("Deploy Vericle Succes: %s", Thread.currentThread().getName()));
//						};
//					})
//
//					.onFailure(new Handler<Throwable>() {
//						
//						@Override
//						public void handle(Throwable event) {
//							System.out.println(String.format("Deploy Vericle Fail: %s, Thread: %s", event.toString(), Thread.currentThread().getName()));
//						}
//					});
//		}
//
//		else {
//			System.out.println(String.format("Load Config Fail: %s, Thread: %s", event.toString(), Thread.currentThread().getName()));
//		}
//	}
//});
