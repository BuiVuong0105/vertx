package com.vertx.vuong.grpc.controller;

import com.vertx.vuong.grpc.GrpcGetStampInformationRequest;
import com.vertx.vuong.grpc.GrpcGetStampInformationResponse;
import com.vertx.vuong.grpc.TransferServiceGrpc.TransferServiceImplBase;

import io.grpc.stub.StreamObserver;

public class TransferController extends TransferServiceImplBase {
	
	@Override
	public void getStampInformation(GrpcGetStampInformationRequest request, StreamObserver<GrpcGetStampInformationResponse> responseObserver) {
		
		System.out.println(String.format("Hanlder Get Stamp Information: %s", Thread.currentThread().getName()));
		
		responseObserver.onNext(GrpcGetStampInformationResponse.newBuilder().setCode(1).setMessage("a").build());
	    
		responseObserver.onCompleted();
	}
}
