package com.vertx.vuong;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.ext.cluster.infinispan.InfinispanClusterManager;

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
		
		LOGGER.info("[%s] Start MainApplication--------------");
		
//		Vertx vertx = Vertx.vertx(new VertxOptions().setPreferNativeTransport(true).setEventLoopPoolSize(10).setWorkerPoolSize(3).setBlockedThreadCheckInterval(5 * 10 * 1000));
		
		ClusterManager mgr = new InfinispanClusterManager();
		
		VertxOptions options = new VertxOptions().setClusterManager(mgr);;
		
		Vertx.clusteredVertx(options, res -> {

			if (res.succeeded()) {

				Vertx vertx = res.result();

				doConfig(vertx).compose(jsonObject -> doDeployVerticle(vertx, jsonObject))

						.onComplete(result -> {
							LOGGER.info("Deploy All Vericle Succes: {}");
						})

						.onFailure(throwable -> {
							LOGGER.info("Deploy All Vericle Fail: {}");
						});

			} else {
				LOGGER.info("Cluster Fail: {}", res.cause());
			}
		});

		
//		doConfig(vertx).compose(jsonObject -> doDeployVerticle(vertx, jsonObject))
//		
//				.onComplete(result -> {
//					LOGGER.info("Deploy All Vericle Succes: {}");
//				})
//				
//				.onFailure(throwable -> {
//					LOGGER.info("Deploy All Vericle Fail: {}");
//				});
	}
	
	private Future<JsonObject> doConfig(Vertx vertx) {
		
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
			vertx.deployVerticle(HelloVerticle.class.getName(), new DeploymentOptions().setWorker(false).setInstances(1).setConfig(jsonObject), promise);
		});
		
		Future<String> futureTcpServer = Future.future(promise -> {
			vertx.deployVerticle(TcpServerVerticle.class.getName(), new DeploymentOptions().setWorker(false).setConfig(jsonObject), promise);
		});
		
		Future<String> futureDatabase = Future.future(promise -> {
			vertx.deployVerticle(DatabaseVerticle.class.getName(), new DeploymentOptions().setWorker(false).setConfig(jsonObject), promise);
		});
		
		Future<String> futureGrpcServer = Future.future(promise -> {
			vertx.deployVerticle(GrpcServerVerticle.class.getName(), new DeploymentOptions().setInstances(1).setConfig(jsonObject), promise);
		});
		
		Future<String> futureGrpcClient = Future.future(promise -> {
			vertx.deployVerticle(GrpcClientVerticle.class.getName(), new DeploymentOptions().setInstances(1).setConfig(jsonObject), promise);
		});
		
		return CompositeFuture.all(futureGw, futureHello, futureTcpServer, futureDatabase, futureGrpcServer, futureGrpcClient).mapEmpty();
	}
	
}
