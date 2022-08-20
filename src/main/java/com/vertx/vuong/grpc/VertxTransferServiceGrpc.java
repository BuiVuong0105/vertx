package com.vertx.vuong.grpc;

import static com.vertx.vuong.grpc.TransferServiceGrpc.getServiceDescriptor;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;


@javax.annotation.Generated(
value = "by VertxGrpc generator",
comments = "Source: transfer.proto")
public final class VertxTransferServiceGrpc {
    private VertxTransferServiceGrpc() {}

    public static TransferServiceVertxStub newVertxStub(io.grpc.Channel channel) {
        return new TransferServiceVertxStub(channel);
    }

    
    public static final class TransferServiceVertxStub extends io.grpc.stub.AbstractStub<TransferServiceVertxStub> {
        private final io.vertx.core.impl.ContextInternal ctx;
        private TransferServiceGrpc.TransferServiceStub delegateStub;

        private TransferServiceVertxStub(io.grpc.Channel channel) {
            super(channel);
            delegateStub = TransferServiceGrpc.newStub(channel);
            this.ctx = (io.vertx.core.impl.ContextInternal) io.vertx.core.Vertx.currentContext();
        }

        private TransferServiceVertxStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
            delegateStub = TransferServiceGrpc.newStub(channel).build(channel, callOptions);
            this.ctx = (io.vertx.core.impl.ContextInternal) io.vertx.core.Vertx.currentContext();
        }

        @Override
        protected TransferServiceVertxStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new TransferServiceVertxStub(channel, callOptions);
        }

        
        public io.vertx.core.Future<com.vertx.vuong.grpc.GrpcGetStampInformationResponse> getStampInformation(com.vertx.vuong.grpc.GrpcGetStampInformationRequest request) {
            return io.vertx.grpc.stub.ClientCalls.oneToOne(ctx, request, delegateStub::getStampInformation);
        }

    }

    
    public static abstract class TransferServiceVertxImplBase implements io.grpc.BindableService {
        private String compression;

        /**
         * Set whether the server will try to use a compressed response.
         *
         * @param compression the compression, e.g {@code gzip}
         */
        public TransferServiceVertxImplBase withCompression(String compression) {
            this.compression = compression;
            return this;
        }

        
        public io.vertx.core.Future<com.vertx.vuong.grpc.GrpcGetStampInformationResponse> getStampInformation(com.vertx.vuong.grpc.GrpcGetStampInformationRequest request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
                    .addMethod(
                            com.vertx.vuong.grpc.TransferServiceGrpc.getGetStampInformationMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.vertx.vuong.grpc.GrpcGetStampInformationRequest,
                                            com.vertx.vuong.grpc.GrpcGetStampInformationResponse>(
                                            this, METHODID_GET_STAMP_INFORMATION, compression)))
                    .build();
        }
    }

    private static final int METHODID_GET_STAMP_INFORMATION = 0;

    private static final class MethodHandlers<Req, Resp> implements
            io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {

        private final TransferServiceVertxImplBase serviceImpl;
        private final int methodId;
        private final String compression;

        MethodHandlers(TransferServiceVertxImplBase serviceImpl, int methodId, String compression) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
            this.compression = compression;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_GET_STAMP_INFORMATION:
                    io.vertx.grpc.stub.ServerCalls.oneToOne(
                            (com.vertx.vuong.grpc.GrpcGetStampInformationRequest) request,
                            (io.grpc.stub.StreamObserver<com.vertx.vuong.grpc.GrpcGetStampInformationResponse>) responseObserver,
                            compression,
                            serviceImpl::getStampInformation);
                    break;
                default:
                    throw new java.lang.AssertionError();
            }
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public io.grpc.stub.StreamObserver<Req> invoke(io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                default:
                    throw new java.lang.AssertionError();
            }
        }
    }

}
