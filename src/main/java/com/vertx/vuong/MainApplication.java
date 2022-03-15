package com.vertx.vuong;

import com.vertx.vuong.verticle.ApiVerticle;
import com.vertx.vuong.verticle.HelloVerticle;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

public class MainApplication {
	
	public static void main(String[] args) {
		
		System.out.println(String.format("Start MainApplication: %s", Thread.currentThread().getName()));
		
		Vertx vertx = Vertx.vertx();
		
		vertx.deployVerticle(ApiVerticle.class.getName(), new DeploymentOptions().setInstances(1));
		
		vertx.deployVerticle(HelloVerticle.class.getName(), new DeploymentOptions().setInstances(2));
	}
}
