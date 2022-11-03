package com.vertx.vuong;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hazelcast.config.Config;
import com.vertx.vuong.verticle.GatewayVerticle;
import com.vertx.vuong.verticle.HelloVerticle;

import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.CompositeFuture;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.spi.cluster.hazelcast.ConfigUtil;
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager;

// Clustering dựa vào lệnh java -jar target/vertx-dev-1.0.0-SNAPSHOT.jar --conf config/testconfig1.json -cluster -Djava.net.preferIPv4Stack=true -Dhttp.port=8090
// Đây là khai báo cluster nếu chạy dựa trên đối tượng vertx của hệ thống tạo ra sẵn, còn nếu tự tạo vertx riêng thì phải cấu hình cluster
// Cluster application phân tải công việc đến từng node thông qua eventbus
// java -jar target/vertx-dev-1.0.0-SNAPSHOT.jar --conf config/testconfig1.json --options config/vertxoption.json
// java -Dvertx.hazelcast.config=./config/cluster-custom.xml -Dhttp.port=8082 -jar target/vertx-dev-1.0.0-SNAPSHOT-run.jar -cluster -Djava.net.preferIPv4Stack=true
// docker run -p 8080:8080 -e JAVA_TOOL_OPTIONS="-Dvertx.eventloop.poolsize=20 -Dvertx.worker.poolsize=10 -Dvertx.profile.active=prod" imageId
public class MainApplication  {
	
	private static final Logger LOGGER = LogManager.getLogger(MainApplication.class);

	public static void main(String[] args) throws Exception {
		new MainApplication().start();
	}
	
	public void start() throws Exception {
		
		LOGGER.info("[%s] Start MainApplication--------------");
		
		Config hazelcastConfig =  ConfigUtil.loadConfig(); // mặc định load default-cluster trong class path, hoặc load theo config
		hazelcastConfig.setClusterName("MIT-Cluster");
//		hazelcastConfig.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(false);
//		hazelcastConfig.getNetworkConfig().getJoin().getKubernetesConfig().setEnabled(true);
		hazelcastConfig.setProperty("service-dns", "vertx01.test-tech.svc.cluster.local");
		
		// some configuration settings
		ClusterManager mgr = new HazelcastClusterManager(hazelcastConfig);

		VertxOptions options = new VertxOptions().setPreferNativeTransport(true).setEventLoopPoolSize(10).setWorkerPoolSize(3).setBlockedThreadCheckInterval(5 * 10 * 1000).setClusterManager(mgr);
		
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
	}
	
	private Future<JsonObject> doConfig(Vertx vertx) {
		
		ConfigStoreOptions defaultConfig = new ConfigStoreOptions().setType("file").setFormat("json").setConfig(new JsonObject().put("path", "config.json"));
		
		ConfigRetrieverOptions opts = new ConfigRetrieverOptions().addStore(defaultConfig);
		
		ConfigRetriever configRetriever = ConfigRetriever.create(vertx, opts);
		
		Future<JsonObject> future = Future.future(promise -> configRetriever.getConfig(promise));
		
		return future;
	}
	
	private Future<Void> doDeployVerticle(Vertx vertx, JsonObject jsonObject) {
		
		Future<String> futureGw = Future.future(promise -> {
			vertx.deployVerticle(GatewayVerticle.class.getName(), new DeploymentOptions().setWorker(false).setConfig(jsonObject), promise);
		});
		
		Future<String> futureHello = Future.future(promise -> {
			vertx.deployVerticle(HelloVerticle.class.getName(), new DeploymentOptions().setWorker(false).setInstances(2).setConfig(jsonObject), promise);
		});
		
//		Future<String> futureTcpServer = Future.future(promise -> {
//			vertx.deployVerticle(TcpServerVerticle.class.getName(), new DeploymentOptions().setWorker(false).setConfig(jsonObject), promise);
//		});
		
//		Future<String> futureDatabase = Future.future(promise -> {
//			vertx.deployVerticle(DatabaseVerticle.class.getName(), new DeploymentOptions().setWorker(false).setConfig(jsonObject), promise);
//		});
		
//		Future<String> futureGrpcServer = Future.future(promise -> {
//			vertx.deployVerticle(GrpcServerVerticle.class.getName(), new DeploymentOptions().setInstances(1).setConfig(jsonObject), promise);
//		});
		
//		Future<String> futureGrpcClient = Future.future(promise -> {
//			vertx.deployVerticle(GrpcClientVerticle.class.getName(), new DeploymentOptions().setInstances(1).setConfig(jsonObject), promise);
//		});
		
		return CompositeFuture.all(futureGw, futureHello).mapEmpty();
	}
	
}
