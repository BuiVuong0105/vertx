package com.vertx.vuong.verticle;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vertx.vuong.grpc.GrpcGetStampInformationRequest;
import com.vertx.vuong.grpc.GrpcGetStampInformationResponse;
import com.vertx.vuong.grpc.TransferServiceGrpc;

import io.grpc.stub.StreamObserver;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.net.SocketAddress;
import io.vertx.grpc.client.GrpcClient;
import io.vertx.grpc.client.GrpcClientChannel;

public class GrpcClientVerticle extends AbstractVerticle {

	static final Logger LOGGER = LogManager.getLogger(GrpcClientVerticle.class);

	private String verticleId = UUID.randomUUID().toString();

	@Override
	public void start(Promise<Void> start) throws Exception {

		LOGGER.info("Deploy {} VerticleId: {}, Context: {}", this.getClass().getName() , verticleId, context);
		
		GrpcClient client = GrpcClient.client(vertx);
		
		SocketAddress server = SocketAddress.inetSocketAddress(8885, "10.5.11.45");
		
		GrpcClientChannel channel = new GrpcClientChannel(client, server);
		
		TransferServiceGrpc.TransferServiceStub transferServiceStup = TransferServiceGrpc.newStub(channel);
		
		transferServiceStup.withDeadlineAfter(3, TimeUnit.SECONDS).getStampInformation(GrpcGetStampInformationRequest.newBuilder().setPrefix("BVV").setIdStart(1).setIdEnd(10).build(), new StreamObserver<GrpcGetStampInformationResponse>() {
			
			@Override
			public void onNext(GrpcGetStampInformationResponse value) {
				LOGGER.info("Response onNext({})", value);
			}
			
			@Override
			public void onError(Throwable t) {
				LOGGER.info("Response onError({})", t);
			}
			
			@Override
			public void onCompleted() {
				LOGGER.info("Response onCompleted()");
			}
		});

//		MethodDescriptor<GrpcGetStampInformationRequest, GrpcGetStampInformationResponse> sayHelloMethod = TransferServiceGrpc.getGetStampInformationMethod();
//
//		Future<GrpcClientRequest<GrpcGetStampInformationRequest, GrpcGetStampInformationResponse>> fut = client.request(server, sayHelloMethod);
//
//		fut.onSuccess(request -> {
//			
//			System.out.println(String.format("[%s] SendRequest Get Stamp",Thread.currentThread().getName()));
//			
//			request.end(GrpcGetStampInformationRequest.newBuilder().setPrefix("BVV").setIdStart(1).setIdEnd(10).build());
//			
//			request.response().onSuccess(response -> {
//				
//				System.out.println(String.format("[%s] Response Get Stamp",Thread.currentThread().getName()));
//				
//				Future<GrpcGetStampInformationResponse> futLast = response.last();
//				
//				futLast.onSuccess(reply -> {
//				
//					System.out.println("Received " + reply + "- " + Thread.currentThread().getName());
//				});
//			});
//		});

		start.complete();
	}
}
