package com.vertx.vuong.verticle;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

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

	private String verticleId = UUID.randomUUID().toString();

	@Override
	public void start(Promise<Void> start) throws Exception {

//		System.out.println(String.format("[%s] Deploy GrpcClientVerticle: %s, VerticleId: %s, Context: %s",Thread.currentThread().getName(), this.getClass().getName(), verticleId, context));
		
		GrpcClient client = GrpcClient.client(vertx);
		
		SocketAddress server = SocketAddress.inetSocketAddress(8885, "10.5.11.45");
		
		GrpcClientChannel channel = new GrpcClientChannel(client, server);
		
		TransferServiceGrpc.TransferServiceStub transferServiceStup = TransferServiceGrpc.newStub(channel);
		
		System.out.println("Pre Send");
		
		transferServiceStup.withDeadlineAfter(3, TimeUnit.SECONDS).getStampInformation(GrpcGetStampInformationRequest.newBuilder().setPrefix("BVV").setIdStart(1).setIdEnd(10).build(), new StreamObserver<GrpcGetStampInformationResponse>() {
			
			@Override
			public void onNext(GrpcGetStampInformationResponse value) {
				System.out.println(String.format("[%s] Response Process Item",Thread.currentThread().getName()));
				System.out.println(value);
			}
			
			@Override
			public void onError(Throwable t) {
				System.out.println(String.format("[%s] Response Error",Thread.currentThread().getName()));
			}
			
			@Override
			public void onCompleted() {
				System.out.println(String.format("[%s] Response Oncomplete",Thread.currentThread().getName()));
			}
		});
		
		System.out.println("End Send");
//
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
