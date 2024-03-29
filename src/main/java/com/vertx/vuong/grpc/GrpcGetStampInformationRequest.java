// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: transfer.proto

package com.vertx.vuong.grpc;

/**
 * Protobuf type {@code com.icheck.qr.read.grpc.GrpcGetStampInformationRequest}
 */
public  final class GrpcGetStampInformationRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.icheck.qr.read.grpc.GrpcGetStampInformationRequest)
    GrpcGetStampInformationRequestOrBuilder {
  // Use GrpcGetStampInformationRequest.newBuilder() to construct.
  private GrpcGetStampInformationRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private GrpcGetStampInformationRequest() {
    prefix_ = "";
    idStart_ = 0L;
    idEnd_ = 0L;
    ownerId_ = 0L;
    excludeQr_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private GrpcGetStampInformationRequest(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    int mutable_bitField0_ = 0;
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!input.skipField(tag)) {
              done = true;
            }
            break;
          }
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            prefix_ = s;
            break;
          }
          case 16: {

            idStart_ = input.readInt64();
            break;
          }
          case 24: {

            idEnd_ = input.readInt64();
            break;
          }
          case 32: {

            ownerId_ = input.readInt64();
            break;
          }
          case 40: {

            excludeQr_ = input.readInt32();
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.vertx.vuong.grpc.Transfer.internal_static_com_icheck_qr_read_grpc_GrpcGetStampInformationRequest_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.vertx.vuong.grpc.Transfer.internal_static_com_icheck_qr_read_grpc_GrpcGetStampInformationRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.vertx.vuong.grpc.GrpcGetStampInformationRequest.class, com.vertx.vuong.grpc.GrpcGetStampInformationRequest.Builder.class);
  }

  public static final int PREFIX_FIELD_NUMBER = 1;
  private volatile java.lang.Object prefix_;
  /**
   * <code>string prefix = 1;</code>
   */
  public java.lang.String getPrefix() {
    java.lang.Object ref = prefix_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      prefix_ = s;
      return s;
    }
  }
  /**
   * <code>string prefix = 1;</code>
   */
  public com.google.protobuf.ByteString
      getPrefixBytes() {
    java.lang.Object ref = prefix_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      prefix_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int IDSTART_FIELD_NUMBER = 2;
  private long idStart_;
  /**
   * <code>int64 idStart = 2;</code>
   */
  public long getIdStart() {
    return idStart_;
  }

  public static final int IDEND_FIELD_NUMBER = 3;
  private long idEnd_;
  /**
   * <code>int64 idEnd = 3;</code>
   */
  public long getIdEnd() {
    return idEnd_;
  }

  public static final int OWNERID_FIELD_NUMBER = 4;
  private long ownerId_;
  /**
   * <code>int64 ownerId = 4;</code>
   */
  public long getOwnerId() {
    return ownerId_;
  }

  public static final int EXCLUDEQR_FIELD_NUMBER = 5;
  private int excludeQr_;
  /**
   * <code>int32 excludeQr = 5;</code>
   */
  public int getExcludeQr() {
    return excludeQr_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!getPrefixBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, prefix_);
    }
    if (idStart_ != 0L) {
      output.writeInt64(2, idStart_);
    }
    if (idEnd_ != 0L) {
      output.writeInt64(3, idEnd_);
    }
    if (ownerId_ != 0L) {
      output.writeInt64(4, ownerId_);
    }
    if (excludeQr_ != 0) {
      output.writeInt32(5, excludeQr_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getPrefixBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, prefix_);
    }
    if (idStart_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(2, idStart_);
    }
    if (idEnd_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(3, idEnd_);
    }
    if (ownerId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(4, ownerId_);
    }
    if (excludeQr_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(5, excludeQr_);
    }
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.vertx.vuong.grpc.GrpcGetStampInformationRequest)) {
      return super.equals(obj);
    }
    com.vertx.vuong.grpc.GrpcGetStampInformationRequest other = (com.vertx.vuong.grpc.GrpcGetStampInformationRequest) obj;

    boolean result = true;
    result = result && getPrefix()
        .equals(other.getPrefix());
    result = result && (getIdStart()
        == other.getIdStart());
    result = result && (getIdEnd()
        == other.getIdEnd());
    result = result && (getOwnerId()
        == other.getOwnerId());
    result = result && (getExcludeQr()
        == other.getExcludeQr());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + PREFIX_FIELD_NUMBER;
    hash = (53 * hash) + getPrefix().hashCode();
    hash = (37 * hash) + IDSTART_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getIdStart());
    hash = (37 * hash) + IDEND_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getIdEnd());
    hash = (37 * hash) + OWNERID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getOwnerId());
    hash = (37 * hash) + EXCLUDEQR_FIELD_NUMBER;
    hash = (53 * hash) + getExcludeQr();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.vertx.vuong.grpc.GrpcGetStampInformationRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.vertx.vuong.grpc.GrpcGetStampInformationRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.vertx.vuong.grpc.GrpcGetStampInformationRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.vertx.vuong.grpc.GrpcGetStampInformationRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.vertx.vuong.grpc.GrpcGetStampInformationRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.vertx.vuong.grpc.GrpcGetStampInformationRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.vertx.vuong.grpc.GrpcGetStampInformationRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.vertx.vuong.grpc.GrpcGetStampInformationRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.vertx.vuong.grpc.GrpcGetStampInformationRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.vertx.vuong.grpc.GrpcGetStampInformationRequest parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.vertx.vuong.grpc.GrpcGetStampInformationRequest prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code com.icheck.qr.read.grpc.GrpcGetStampInformationRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.icheck.qr.read.grpc.GrpcGetStampInformationRequest)
      com.vertx.vuong.grpc.GrpcGetStampInformationRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.vertx.vuong.grpc.Transfer.internal_static_com_icheck_qr_read_grpc_GrpcGetStampInformationRequest_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.vertx.vuong.grpc.Transfer.internal_static_com_icheck_qr_read_grpc_GrpcGetStampInformationRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.vertx.vuong.grpc.GrpcGetStampInformationRequest.class, com.vertx.vuong.grpc.GrpcGetStampInformationRequest.Builder.class);
    }

    // Construct using com.vertx.vuong.grpc.GrpcGetStampInformationRequest.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    public Builder clear() {
      super.clear();
      prefix_ = "";

      idStart_ = 0L;

      idEnd_ = 0L;

      ownerId_ = 0L;

      excludeQr_ = 0;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.vertx.vuong.grpc.Transfer.internal_static_com_icheck_qr_read_grpc_GrpcGetStampInformationRequest_descriptor;
    }

    public com.vertx.vuong.grpc.GrpcGetStampInformationRequest getDefaultInstanceForType() {
      return com.vertx.vuong.grpc.GrpcGetStampInformationRequest.getDefaultInstance();
    }

    public com.vertx.vuong.grpc.GrpcGetStampInformationRequest build() {
      com.vertx.vuong.grpc.GrpcGetStampInformationRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.vertx.vuong.grpc.GrpcGetStampInformationRequest buildPartial() {
      com.vertx.vuong.grpc.GrpcGetStampInformationRequest result = new com.vertx.vuong.grpc.GrpcGetStampInformationRequest(this);
      result.prefix_ = prefix_;
      result.idStart_ = idStart_;
      result.idEnd_ = idEnd_;
      result.ownerId_ = ownerId_;
      result.excludeQr_ = excludeQr_;
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.vertx.vuong.grpc.GrpcGetStampInformationRequest) {
        return mergeFrom((com.vertx.vuong.grpc.GrpcGetStampInformationRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.vertx.vuong.grpc.GrpcGetStampInformationRequest other) {
      if (other == com.vertx.vuong.grpc.GrpcGetStampInformationRequest.getDefaultInstance()) return this;
      if (!other.getPrefix().isEmpty()) {
        prefix_ = other.prefix_;
        onChanged();
      }
      if (other.getIdStart() != 0L) {
        setIdStart(other.getIdStart());
      }
      if (other.getIdEnd() != 0L) {
        setIdEnd(other.getIdEnd());
      }
      if (other.getOwnerId() != 0L) {
        setOwnerId(other.getOwnerId());
      }
      if (other.getExcludeQr() != 0) {
        setExcludeQr(other.getExcludeQr());
      }
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.vertx.vuong.grpc.GrpcGetStampInformationRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.vertx.vuong.grpc.GrpcGetStampInformationRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object prefix_ = "";
    /**
     * <code>string prefix = 1;</code>
     */
    public java.lang.String getPrefix() {
      java.lang.Object ref = prefix_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        prefix_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string prefix = 1;</code>
     */
    public com.google.protobuf.ByteString
        getPrefixBytes() {
      java.lang.Object ref = prefix_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        prefix_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string prefix = 1;</code>
     */
    public Builder setPrefix(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      prefix_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string prefix = 1;</code>
     */
    public Builder clearPrefix() {
      
      prefix_ = getDefaultInstance().getPrefix();
      onChanged();
      return this;
    }
    /**
     * <code>string prefix = 1;</code>
     */
    public Builder setPrefixBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      prefix_ = value;
      onChanged();
      return this;
    }

    private long idStart_ ;
    /**
     * <code>int64 idStart = 2;</code>
     */
    public long getIdStart() {
      return idStart_;
    }
    /**
     * <code>int64 idStart = 2;</code>
     */
    public Builder setIdStart(long value) {
      
      idStart_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 idStart = 2;</code>
     */
    public Builder clearIdStart() {
      
      idStart_ = 0L;
      onChanged();
      return this;
    }

    private long idEnd_ ;
    /**
     * <code>int64 idEnd = 3;</code>
     */
    public long getIdEnd() {
      return idEnd_;
    }
    /**
     * <code>int64 idEnd = 3;</code>
     */
    public Builder setIdEnd(long value) {
      
      idEnd_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 idEnd = 3;</code>
     */
    public Builder clearIdEnd() {
      
      idEnd_ = 0L;
      onChanged();
      return this;
    }

    private long ownerId_ ;
    /**
     * <code>int64 ownerId = 4;</code>
     */
    public long getOwnerId() {
      return ownerId_;
    }
    /**
     * <code>int64 ownerId = 4;</code>
     */
    public Builder setOwnerId(long value) {
      
      ownerId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 ownerId = 4;</code>
     */
    public Builder clearOwnerId() {
      
      ownerId_ = 0L;
      onChanged();
      return this;
    }

    private int excludeQr_ ;
    /**
     * <code>int32 excludeQr = 5;</code>
     */
    public int getExcludeQr() {
      return excludeQr_;
    }
    /**
     * <code>int32 excludeQr = 5;</code>
     */
    public Builder setExcludeQr(int value) {
      
      excludeQr_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 excludeQr = 5;</code>
     */
    public Builder clearExcludeQr() {
      
      excludeQr_ = 0;
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:com.icheck.qr.read.grpc.GrpcGetStampInformationRequest)
  }

  // @@protoc_insertion_point(class_scope:com.icheck.qr.read.grpc.GrpcGetStampInformationRequest)
  private static final com.vertx.vuong.grpc.GrpcGetStampInformationRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.vertx.vuong.grpc.GrpcGetStampInformationRequest();
  }

  public static com.vertx.vuong.grpc.GrpcGetStampInformationRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<GrpcGetStampInformationRequest>
      PARSER = new com.google.protobuf.AbstractParser<GrpcGetStampInformationRequest>() {
    public GrpcGetStampInformationRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new GrpcGetStampInformationRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<GrpcGetStampInformationRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<GrpcGetStampInformationRequest> getParserForType() {
    return PARSER;
  }

  public com.vertx.vuong.grpc.GrpcGetStampInformationRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

