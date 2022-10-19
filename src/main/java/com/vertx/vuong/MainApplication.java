package com.vertx.vuong;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import com.vertx.vuong.verticle.DatabaseVerticle;
import com.vertx.vuong.verticle.GatewayVerticle;
import com.vertx.vuong.verticle.GrpcClientVerticle;
import com.vertx.vuong.verticle.GrpcServerVerticle;
import com.vertx.vuong.verticle.HelloVerticle;
import com.vertx.vuong.verticle.TcpServerVerticle;

import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.CompositeFuture;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.json.JsonObject;

// Clustering dựa vào lệnh java -jar target/vertx-dev-1.0.0-SNAPSHOT.jar --conf config/testconfig1.json -cluster -Djava.net.preferIPv4Stack=true -Dhttp.port=8090
// Đây là khai báo cluster nếu chạy dựa trên đối tượng vertx của hệ thống tạo ra sẵn, còn nếu tự tạo vertx riêng thì phải cấu hình cluster
// Cluster application phân tải công việc đến từng node thông qua eventbus
// java -jar target/vertx-dev-1.0.0-SNAPSHOT.jar --conf config/testconfig1.json --options config/vertxoption.json
public class MainApplication  {
	
	private static final Logger LOGGER = LogManager.getLogger(MainApplication.class);

	public static void main(String[] args) throws Exception {
		new MainApplication().start();
	}
	
	public void start() throws Exception {
		
		System.out.println(String.format("[%s] Start MainApplication--------------", Thread.currentThread().getName()));
		
		loadLogging();
		
		Vertx vertx = Vertx.vertx(new VertxOptions().setPreferNativeTransport(true).setEventLoopPoolSize(10).setWorkerPoolSize(3).setBlockedThreadCheckInterval(5 * 10 * 1000));
		
//		VertxOptions options = new VertxOptions();
//		Vertx.clusteredVertx(options, res -> {
//			  if (res.succeeded()) {
//			    Vertx vertx = res.result();
//			    
//			    Future<JsonObject> futureConfig = doConfig(vertx);
//				
//				futureConfig.onSuccess(jsonObject -> {
//					
//					System.out.println(String.format("[%s] Do config success", Thread.currentThread().getName()));
//					
//					Future<Void> futureVericle = doDeployVerticle(vertx, jsonObject);
//					
//					futureVericle.onSuccess(unuse -> {
//						System.out.println(String.format("[%s] Do Deploy Verticle Success", Thread.currentThread().getName()));
//					});
//					
//					futureVericle.onFailure(throwable -> {
//						System.out.println(String.format("[%s] Do Deploy Verticle fail: %s", Thread.currentThread().getName(), throwable.getMessage()));
//					});
//				});
//				
//				futureConfig.onFailure(throwable -> {
//					System.out.println(String.format("[%s] Do config fail: %s", Thread.currentThread().getName(), throwable.getMessage()));
//				});
//
//			  } else {
//			    System.out.println("Failed: " + res.cause());
//			  }
//			});

		
		doConfig(vertx).compose(jsonObject -> doDeployVerticle(vertx, jsonObject))
		
				.onComplete(result -> {
					System.out.println(String.format("Deploy Vericle Succes: %s", Thread.currentThread().getName()));
				})
				
				.onFailure(throwable -> {
					System.out.println(String.format("Deploy Vericle Fail: %s, Thread: %s", throwable.toString(), Thread.currentThread().getName()));
				});
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
				configRetriever.getConfig(promise);
			}
		});
		
		return future;
	}
	
	private Future<Void> doDeployVerticle(Vertx vertx, JsonObject jsonObject) {
		
		Future<String> futureGw = Future.future(promise -> {
			vertx.deployVerticle(GatewayVerticle.class.getName(), new DeploymentOptions().setWorker(false).setConfig(jsonObject), promise);
		});
		
		Future<String> futureHello = Future.future(promise -> {
			vertx.deployVerticle(HelloVerticle.class.getName(), new DeploymentOptions().setWorker(true).setConfig(jsonObject), promise);
		});
		
		Future<String> futureTcpServer = Future.future(promise -> {
			vertx.deployVerticle(TcpServerVerticle.class.getName(), new DeploymentOptions().setWorker(false).setConfig(jsonObject), promise);
		});
		
		Future<String> futureDatabase = Future.future(promise -> {
			vertx.deployVerticle(DatabaseVerticle.class.getName(), new DeploymentOptions().setWorker(false).setConfig(jsonObject), promise);
		});
		
		Future<String> futureGrpcServer = Future.future(promise -> {
			vertx.deployVerticle(GrpcServerVerticle.class.getName(), new DeploymentOptions().setInstances(3).setConfig(jsonObject), promise);
		});
		
		Future<String> futureGrpcClient = Future.future(promise -> {
			vertx.deployVerticle(GrpcClientVerticle.class.getName(), new DeploymentOptions().setInstances(1).setConfig(jsonObject), promise);
		});
		
		return CompositeFuture.all(futureGw, futureHello, futureTcpServer, futureDatabase, futureGrpcServer, futureGrpcClient).mapEmpty();
	}
	
	private void loadLogging() throws URISyntaxException {

		String loggingConfig = System.getProperty("vertx.logging.config");

		File file = null;

		if (StringUtils.isBlank(loggingConfig)) {
			
			LOGGER.info("Load config from class path");

			ClassLoader classLoader = getClass().getClassLoader();

			URL resource = classLoader.getResource("log4j.xml");

			file = new File(resource.toURI());
		}

		else {
			LOGGER.info("Load config from: {}", loggingConfig);
			file = new File(loggingConfig);
		}

		LoggerContext context = (org.apache.logging.log4j.core.LoggerContext) LogManager.getContext(false);

		context.setConfigLocation(file.toURI());
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
