package io.grpc.wyq.mycat;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 * <pre>
 *定义服务名
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.0.1)",
    comments = "Source: mycat.proto")
public class MycatGrpc {

  private MycatGrpc() {}

  public static final String SERVICE_NAME = "helloworld.Mycat";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<io.grpc.wyq.mycat.MycatRequest,
      io.grpc.wyq.mycat.MycatReply> METHOD_ADD_TABLE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "helloworld.Mycat", "addTable"),
          io.grpc.protobuf.ProtoUtils.marshaller(io.grpc.wyq.mycat.MycatRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(io.grpc.wyq.mycat.MycatReply.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<io.grpc.wyq.mycat.MycatRequest,
      io.grpc.wyq.mycat.MycatReply> METHOD_DEL_TABLE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "helloworld.Mycat", "delTable"),
          io.grpc.protobuf.ProtoUtils.marshaller(io.grpc.wyq.mycat.MycatRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(io.grpc.wyq.mycat.MycatReply.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<io.grpc.wyq.mycat.MycatRequest,
      io.grpc.wyq.mycat.MycatReply> METHOD_HANDLE_TABLE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "helloworld.Mycat", "handleTable"),
          io.grpc.protobuf.ProtoUtils.marshaller(io.grpc.wyq.mycat.MycatRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(io.grpc.wyq.mycat.MycatReply.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<io.grpc.wyq.mycat.MycatRequest,
      io.grpc.wyq.mycat.MycatReply> METHOD_UPDATE_TABLE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "helloworld.Mycat", "updateTable"),
          io.grpc.protobuf.ProtoUtils.marshaller(io.grpc.wyq.mycat.MycatRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(io.grpc.wyq.mycat.MycatReply.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MycatStub newStub(io.grpc.Channel channel) {
    return new MycatStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MycatBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new MycatBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static MycatFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new MycatFutureStub(channel);
  }

  /**
   * <pre>
   *定义服务名
   * </pre>
   */
  public static abstract class MycatImplBase implements io.grpc.BindableService {

    /**
     */
    public void addTable(io.grpc.wyq.mycat.MycatRequest request,
        io.grpc.stub.StreamObserver<io.grpc.wyq.mycat.MycatReply> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_ADD_TABLE, responseObserver);
    }

    /**
     */
    public void delTable(io.grpc.wyq.mycat.MycatRequest request,
        io.grpc.stub.StreamObserver<io.grpc.wyq.mycat.MycatReply> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_DEL_TABLE, responseObserver);
    }

    /**
     */
    public void handleTable(io.grpc.wyq.mycat.MycatRequest request,
        io.grpc.stub.StreamObserver<io.grpc.wyq.mycat.MycatReply> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_HANDLE_TABLE, responseObserver);
    }

    /**
     */
    public void updateTable(io.grpc.wyq.mycat.MycatRequest request,
        io.grpc.stub.StreamObserver<io.grpc.wyq.mycat.MycatReply> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_UPDATE_TABLE, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_ADD_TABLE,
            asyncUnaryCall(
              new MethodHandlers<
                io.grpc.wyq.mycat.MycatRequest,
                io.grpc.wyq.mycat.MycatReply>(
                  this, METHODID_ADD_TABLE)))
          .addMethod(
            METHOD_DEL_TABLE,
            asyncUnaryCall(
              new MethodHandlers<
                io.grpc.wyq.mycat.MycatRequest,
                io.grpc.wyq.mycat.MycatReply>(
                  this, METHODID_DEL_TABLE)))
          .addMethod(
            METHOD_HANDLE_TABLE,
            asyncUnaryCall(
              new MethodHandlers<
                io.grpc.wyq.mycat.MycatRequest,
                io.grpc.wyq.mycat.MycatReply>(
                  this, METHODID_HANDLE_TABLE)))
          .addMethod(
            METHOD_UPDATE_TABLE,
            asyncUnaryCall(
              new MethodHandlers<
                io.grpc.wyq.mycat.MycatRequest,
                io.grpc.wyq.mycat.MycatReply>(
                  this, METHODID_UPDATE_TABLE)))
          .build();
    }
  }

  /**
   * <pre>
   *定义服务名
   * </pre>
   */
  public static final class MycatStub extends io.grpc.stub.AbstractStub<MycatStub> {
    private MycatStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MycatStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MycatStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MycatStub(channel, callOptions);
    }

    /**
     */
    public void addTable(io.grpc.wyq.mycat.MycatRequest request,
        io.grpc.stub.StreamObserver<io.grpc.wyq.mycat.MycatReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_ADD_TABLE, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void delTable(io.grpc.wyq.mycat.MycatRequest request,
        io.grpc.stub.StreamObserver<io.grpc.wyq.mycat.MycatReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_DEL_TABLE, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void handleTable(io.grpc.wyq.mycat.MycatRequest request,
        io.grpc.stub.StreamObserver<io.grpc.wyq.mycat.MycatReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_HANDLE_TABLE, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateTable(io.grpc.wyq.mycat.MycatRequest request,
        io.grpc.stub.StreamObserver<io.grpc.wyq.mycat.MycatReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_UPDATE_TABLE, getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   *定义服务名
   * </pre>
   */
  public static final class MycatBlockingStub extends io.grpc.stub.AbstractStub<MycatBlockingStub> {
    private MycatBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MycatBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MycatBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MycatBlockingStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.wyq.mycat.MycatReply addTable(io.grpc.wyq.mycat.MycatRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_ADD_TABLE, getCallOptions(), request);
    }

    /**
     */
    public io.grpc.wyq.mycat.MycatReply delTable(io.grpc.wyq.mycat.MycatRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_DEL_TABLE, getCallOptions(), request);
    }

    /**
     */
    public io.grpc.wyq.mycat.MycatReply handleTable(io.grpc.wyq.mycat.MycatRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_HANDLE_TABLE, getCallOptions(), request);
    }

    /**
     */
    public io.grpc.wyq.mycat.MycatReply updateTable(io.grpc.wyq.mycat.MycatRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_UPDATE_TABLE, getCallOptions(), request);
    }
  }

  /**
   * <pre>
   *定义服务名
   * </pre>
   */
  public static final class MycatFutureStub extends io.grpc.stub.AbstractStub<MycatFutureStub> {
    private MycatFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MycatFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MycatFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MycatFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.grpc.wyq.mycat.MycatReply> addTable(
        io.grpc.wyq.mycat.MycatRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_ADD_TABLE, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.grpc.wyq.mycat.MycatReply> delTable(
        io.grpc.wyq.mycat.MycatRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_DEL_TABLE, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.grpc.wyq.mycat.MycatReply> handleTable(
        io.grpc.wyq.mycat.MycatRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_HANDLE_TABLE, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.grpc.wyq.mycat.MycatReply> updateTable(
        io.grpc.wyq.mycat.MycatRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_UPDATE_TABLE, getCallOptions()), request);
    }
  }

  private static final int METHODID_ADD_TABLE = 0;
  private static final int METHODID_DEL_TABLE = 1;
  private static final int METHODID_HANDLE_TABLE = 2;
  private static final int METHODID_UPDATE_TABLE = 3;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final MycatImplBase serviceImpl;
    private final int methodId;

    public MethodHandlers(MycatImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ADD_TABLE:
          serviceImpl.addTable((io.grpc.wyq.mycat.MycatRequest) request,
              (io.grpc.stub.StreamObserver<io.grpc.wyq.mycat.MycatReply>) responseObserver);
          break;
        case METHODID_DEL_TABLE:
          serviceImpl.delTable((io.grpc.wyq.mycat.MycatRequest) request,
              (io.grpc.stub.StreamObserver<io.grpc.wyq.mycat.MycatReply>) responseObserver);
          break;
        case METHODID_HANDLE_TABLE:
          serviceImpl.handleTable((io.grpc.wyq.mycat.MycatRequest) request,
              (io.grpc.stub.StreamObserver<io.grpc.wyq.mycat.MycatReply>) responseObserver);
          break;
        case METHODID_UPDATE_TABLE:
          serviceImpl.updateTable((io.grpc.wyq.mycat.MycatRequest) request,
              (io.grpc.stub.StreamObserver<io.grpc.wyq.mycat.MycatReply>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    return new io.grpc.ServiceDescriptor(SERVICE_NAME,
        METHOD_ADD_TABLE,
        METHOD_DEL_TABLE,
        METHOD_HANDLE_TABLE,
        METHOD_UPDATE_TABLE);
  }

}
