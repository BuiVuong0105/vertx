package com.vertx.vuong.verticle;

import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vertx.vuong.grpc.controller.TransferController;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.grpc.server.GrpcServer;
import io.vertx.grpc.server.GrpcServiceBridge;

public class GrpcServerVerticle extends AbstractVerticle {

	static final Logger LOGGER = LogManager.getLogger(GrpcServerVerticle.class);

	private String verticleId = UUID.randomUUID().toString();

	@Override
	public void start(Promise<Void> start) throws Exception {
		
		LOGGER.info("Deploy {} VerticleId: {}, Context: {}", this.getClass().getName() ,verticleId, context);

		GrpcServer grpcServer = GrpcServer.server(vertx);

		GrpcServiceBridge transferStup = GrpcServiceBridge.bridge(new TransferController());
		
		transferStup.bind(grpcServer);
		
		vertx.createHttpServer().requestHandler(grpcServer).listen(8081);

		start.complete();
	}
}
