// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: transfer.proto

package com.vertx.vuong.grpc;

/**
 * Protobuf type {@code com.icheck.qr.read.grpc.GrpcRestoreInfo}
 */
public  final class GrpcRestoreInfo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.icheck.qr.read.grpc.GrpcRestoreInfo)
    GrpcRestoreInfoOrBuilder {
  // Use GrpcRestoreInfo.newBuilder() to construct.
  private GrpcRestoreInfo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private GrpcRestoreInfo() {
    reason_ = "";
    userCreatedById_ = 0L;
    userCreatedByName_ = "";
    createdDate_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private GrpcRestoreInfo(
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

            reason_ = s;
            break;
          }
          case 16: {

            userCreatedById_ = input.readInt64();
            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();

            userCreatedByName_ = s;
            break;
          }
          case 34: {
            java.lang.String s = input.readStringRequireUtf8();

            createdDate_ = s;
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
    return com.vertx.vuong.grpc.Transfer.internal_static_com_icheck_qr_read_grpc_GrpcRestoreInfo_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.vertx.vuong.grpc.Transfer.internal_static_com_icheck_qr_read_grpc_GrpcRestoreInfo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.vertx.vuong.grpc.GrpcRestoreInfo.class, com.vertx.vuong.grpc.GrpcRestoreInfo.Builder.class);
  }

  public static final int REASON_FIELD_NUMBER = 1;
  private volatile java.lang.Object reason_;
  /**
   * <code>string reason = 1;</code>
   */
  public java.lang.String getReason() {
    java.lang.Object ref = reason_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      reason_ = s;
      return s;
    }
  }
  /**
   * <code>string reason = 1;</code>
   */
  public com.google.protobuf.ByteString
      getReasonBytes() {
    java.lang.Object ref = reason_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      reason_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int USERCREATEDBYID_FIELD_NUMBER = 2;
  private long userCreatedById_;
  /**
   * <code>int64 userCreatedById = 2;</code>
   */
  public long getUserCreatedById() {
    return userCreatedById_;
  }

  public static final int USERCREATEDBYNAME_FIELD_NUMBER = 3;
  private volatile java.lang.Object userCreatedByName_;
  /**
   * <code>string userCreatedByName = 3;</code>
   */
  public java.lang.String getUserCreatedByName() {
    java.lang.Object ref = userCreatedByName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      userCreatedByName_ = s;
      return s;
    }
  }
  /**
   * <code>string userCreatedByName = 3;</code>
   */
  public com.google.protobuf.ByteString
      getUserCreatedByNameBytes() {
    java.lang.Object ref = userCreatedByName_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      userCreatedByName_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int CREATEDDATE_FIELD_NUMBER = 4;
  private volatile java.lang.Object createdDate_;
  /**
   * <code>string createdDate = 4;</code>
   */
  public java.lang.String getCreatedDate() {
    java.lang.Object ref = createdDate_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      createdDate_ = s;
      return s;
    }
  }
  /**
   * <code>string createdDate = 4;</code>
   */
  public com.google.protobuf.ByteString
      getCreatedDateBytes() {
    java.lang.Object ref = createdDate_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      createdDate_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
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
    if (!getReasonBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, reason_);
    }
    if (userCreatedById_ != 0L) {
      output.writeInt64(2, userCreatedById_);
    }
    if (!getUserCreatedByNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, userCreatedByName_);
    }
    if (!getCreatedDateBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 4, createdDate_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getReasonBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, reason_);
    }
    if (userCreatedById_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(2, userCreatedById_);
    }
    if (!getUserCreatedByNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, userCreatedByName_);
    }
    if (!getCreatedDateBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, createdDate_);
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
    if (!(obj instanceof com.vertx.vuong.grpc.GrpcRestoreInfo)) {
      return super.equals(obj);
    }
    com.vertx.vuong.grpc.GrpcRestoreInfo other = (com.vertx.vuong.grpc.GrpcRestoreInfo) obj;

    boolean result = true;
    result = result && getReason()
        .equals(other.getReason());
    result = result && (getUserCreatedById()
        == other.getUserCreatedById());
    result = result && getUserCreatedByName()
        .equals(other.getUserCreatedByName());
    result = result && getCreatedDate()
        .equals(other.getCreatedDate());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + REASON_FIELD_NUMBER;
    hash = (53 * hash) + getReason().hashCode();
    hash = (37 * hash) + USERCREATEDBYID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getUserCreatedById());
    hash = (37 * hash) + USERCREATEDBYNAME_FIELD_NUMBER;
    hash = (53 * hash) + getUserCreatedByName().hashCode();
    hash = (37 * hash) + CREATEDDATE_FIELD_NUMBER;
    hash = (53 * hash) + getCreatedDate().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.vertx.vuong.grpc.GrpcRestoreInfo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.vertx.vuong.grpc.GrpcRestoreInfo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.vertx.vuong.grpc.GrpcRestoreInfo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.vertx.vuong.grpc.GrpcRestoreInfo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.vertx.vuong.grpc.GrpcRestoreInfo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.vertx.vuong.grpc.GrpcRestoreInfo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.vertx.vuong.grpc.GrpcRestoreInfo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.vertx.vuong.grpc.GrpcRestoreInfo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.vertx.vuong.grpc.GrpcRestoreInfo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.vertx.vuong.grpc.GrpcRestoreInfo parseFrom(
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
  public static Builder newBuilder(com.vertx.vuong.grpc.GrpcRestoreInfo prototype) {
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
   * Protobuf type {@code com.icheck.qr.read.grpc.GrpcRestoreInfo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.icheck.qr.read.grpc.GrpcRestoreInfo)
      com.vertx.vuong.grpc.GrpcRestoreInfoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.vertx.vuong.grpc.Transfer.internal_static_com_icheck_qr_read_grpc_GrpcRestoreInfo_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.vertx.vuong.grpc.Transfer.internal_static_com_icheck_qr_read_grpc_GrpcRestoreInfo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.vertx.vuong.grpc.GrpcRestoreInfo.class, com.vertx.vuong.grpc.GrpcRestoreInfo.Builder.class);
    }

    // Construct using com.vertx.vuong.grpc.GrpcRestoreInfo.newBuilder()
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
      reason_ = "";

      userCreatedById_ = 0L;

      userCreatedByName_ = "";

      createdDate_ = "";

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.vertx.vuong.grpc.Transfer.internal_static_com_icheck_qr_read_grpc_GrpcRestoreInfo_descriptor;
    }

    public com.vertx.vuong.grpc.GrpcRestoreInfo getDefaultInstanceForType() {
      return com.vertx.vuong.grpc.GrpcRestoreInfo.getDefaultInstance();
    }

    public com.vertx.vuong.grpc.GrpcRestoreInfo build() {
      com.vertx.vuong.grpc.GrpcRestoreInfo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.vertx.vuong.grpc.GrpcRestoreInfo buildPartial() {
      com.vertx.vuong.grpc.GrpcRestoreInfo result = new com.vertx.vuong.grpc.GrpcRestoreInfo(this);
      result.reason_ = reason_;
      result.userCreatedById_ = userCreatedById_;
      result.userCreatedByName_ = userCreatedByName_;
      result.createdDate_ = createdDate_;
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
      if (other instanceof com.vertx.vuong.grpc.GrpcRestoreInfo) {
        return mergeFrom((com.vertx.vuong.grpc.GrpcRestoreInfo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.vertx.vuong.grpc.GrpcRestoreInfo other) {
      if (other == com.vertx.vuong.grpc.GrpcRestoreInfo.getDefaultInstance()) return this;
      if (!other.getReason().isEmpty()) {
        reason_ = other.reason_;
        onChanged();
      }
      if (other.getUserCreatedById() != 0L) {
        setUserCreatedById(other.getUserCreatedById());
      }
      if (!other.getUserCreatedByName().isEmpty()) {
        userCreatedByName_ = other.userCreatedByName_;
        onChanged();
      }
      if (!other.getCreatedDate().isEmpty()) {
        createdDate_ = other.createdDate_;
        onChanged();
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
      com.vertx.vuong.grpc.GrpcRestoreInfo parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.vertx.vuong.grpc.GrpcRestoreInfo) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object reason_ = "";
    /**
     * <code>string reason = 1;</code>
     */
    public java.lang.String getReason() {
      java.lang.Object ref = reason_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        reason_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string reason = 1;</code>
     */
    public com.google.protobuf.ByteString
        getReasonBytes() {
      java.lang.Object ref = reason_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        reason_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string reason = 1;</code>
     */
    public Builder setReason(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      reason_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string reason = 1;</code>
     */
    public Builder clearReason() {
      
      reason_ = getDefaultInstance().getReason();
      onChanged();
      return this;
    }
    /**
     * <code>string reason = 1;</code>
     */
    public Builder setReasonBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      reason_ = value;
      onChanged();
      return this;
    }

    private long userCreatedById_ ;
    /**
     * <code>int64 userCreatedById = 2;</code>
     */
    public long getUserCreatedById() {
      return userCreatedById_;
    }
    /**
     * <code>int64 userCreatedById = 2;</code>
     */
    public Builder setUserCreatedById(long value) {
      
      userCreatedById_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 userCreatedById = 2;</code>
     */
    public Builder clearUserCreatedById() {
      
      userCreatedById_ = 0L;
      onChanged();
      return this;
    }

    private java.lang.Object userCreatedByName_ = "";
    /**
     * <code>string userCreatedByName = 3;</code>
     */
    public java.lang.String getUserCreatedByName() {
      java.lang.Object ref = userCreatedByName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        userCreatedByName_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string userCreatedByName = 3;</code>
     */
    public com.google.protobuf.ByteString
        getUserCreatedByNameBytes() {
      java.lang.Object ref = userCreatedByName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        userCreatedByName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string userCreatedByName = 3;</code>
     */
    public Builder setUserCreatedByName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      userCreatedByName_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string userCreatedByName = 3;</code>
     */
    public Builder clearUserCreatedByName() {
      
      userCreatedByName_ = getDefaultInstance().getUserCreatedByName();
      onChanged();
      return this;
    }
    /**
     * <code>string userCreatedByName = 3;</code>
     */
    public Builder setUserCreatedByNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      userCreatedByName_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object createdDate_ = "";
    /**
     * <code>string createdDate = 4;</code>
     */
    public java.lang.String getCreatedDate() {
      java.lang.Object ref = createdDate_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        createdDate_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string createdDate = 4;</code>
     */
    public com.google.protobuf.ByteString
        getCreatedDateBytes() {
      java.lang.Object ref = createdDate_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        createdDate_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string createdDate = 4;</code>
     */
    public Builder setCreatedDate(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      createdDate_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string createdDate = 4;</code>
     */
    public Builder clearCreatedDate() {
      
      createdDate_ = getDefaultInstance().getCreatedDate();
      onChanged();
      return this;
    }
    /**
     * <code>string createdDate = 4;</code>
     */
    public Builder setCreatedDateBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      createdDate_ = value;
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


    // @@protoc_insertion_point(builder_scope:com.icheck.qr.read.grpc.GrpcRestoreInfo)
  }

  // @@protoc_insertion_point(class_scope:com.icheck.qr.read.grpc.GrpcRestoreInfo)
  private static final com.vertx.vuong.grpc.GrpcRestoreInfo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.vertx.vuong.grpc.GrpcRestoreInfo();
  }

  public static com.vertx.vuong.grpc.GrpcRestoreInfo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<GrpcRestoreInfo>
      PARSER = new com.google.protobuf.AbstractParser<GrpcRestoreInfo>() {
    public GrpcRestoreInfo parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new GrpcRestoreInfo(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<GrpcRestoreInfo> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<GrpcRestoreInfo> getParserForType() {
    return PARSER;
  }

  public com.vertx.vuong.grpc.GrpcRestoreInfo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

