package com.vertx.vuong;

import io.vertx.core.Vertx;

public class MainApplication {
	
	public static void main(String[] args) {
		
		System.out.println(String.format("Start MainApplication: %s", Thread.currentThread().getName()));
		
		Vertx vertx = Vertx.vertx();
		
		vertx.deployVerticle(new GatewayVerticle());
		
	}
}
