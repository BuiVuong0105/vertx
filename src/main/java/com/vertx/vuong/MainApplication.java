package com.vertx.vuong;

import com.vertx.vuong.verticle.GatewayVerticle;
import com.vertx.vuong.verticle.HelloVerticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;

// Clustering dựa vào lệnh java -jar target/vertx-dev-1.0.0-SNAPSHOT.jar -cluster -Djava.net.preferIPv4Stack=true -Dhttp.port=8090
// Đây là khai báo cluster nếu chạy dựa trên đối tượng vertx của hệ thống tạo ra sẵn, còn nếu tự tạo vertx riêng thì phải cấu hình cluster
// Cluster application phân tải công việc đến từng node thông qua eventbus
public class MainApplication extends AbstractVerticle {

	@Override
	public void start(Promise<Void> start) {

		System.out.println(String.format("Start MainApplication: %s", Thread.currentThread().getName()));

		vertx.deployVerticle(GatewayVerticle.class.getName());

		vertx.deployVerticle(HelloVerticle.class.getName(), new DeploymentOptions().setInstances(2));

		start.complete();
	}

//	public static void main(String[] args) {
//		
//		System.out.println(String.format("Start MainApplication: %s", Thread.currentThread().getName()));
//		
//		Vertx vertx = Vertx.vertx();
//		
//		vertx.deployVerticle(GatewayVerticle.class.getName());
//		
//		vertx.deployVerticle(HelloVerticle.class.getName(), new DeploymentOptions().setInstances(2));
//	}
}
