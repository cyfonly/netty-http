
package com.decoder.protobuf;

public final class UserProbuf {
  private UserProbuf() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface UserOrBuilder extends
      // @@protoc_insertion_point(interface_extends:User)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>required int32 id = 1;</code>
     */
    boolean hasId();
    /**
     * <code>required int32 id = 1;</code>
     */
    int getId();

    /**
     * <code>required string name = 2;</code>
     */
    boolean hasName();
    /**
     * <code>required string name = 2;</code>
     */
    java.lang.String getName();
    /**
     * <code>required string name = 2;</code>
     */
    com.google.protobuf.ByteString
        getNameBytes();

    /**
     * <code>required .User.Sex sex = 3;</code>
     */
    boolean hasSex();
    /**
     * <code>required .User.Sex sex = 3;</code>
     */
    com.decoder.protobuf.UserProbuf.User.Sex getSex();
  }
  /**
   * Protobuf type {@code User}
   */
  public static final class User extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:User)
      UserOrBuilder {
    // Use User.newBuilder() to construct.
    private User(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private User(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final User defaultInstance;
    public static User getDefaultInstance() {
      return defaultInstance;
    }

    public User getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private User(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 8: {
              bitField0_ |= 0x00000001;
              id_ = input.readInt32();
              break;
            }
            case 18: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000002;
              name_ = bs;
              break;
            }
            case 24: {
              int rawValue = input.readEnum();
              com.decoder.protobuf.UserProbuf.User.Sex value = com.decoder.protobuf.UserProbuf.User.Sex.valueOf(rawValue);
              if (value == null) {
                unknownFields.mergeVarintField(3, rawValue);
              } else {
                bitField0_ |= 0x00000004;
                sex_ = value;
              }
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e.getMessage()).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.decoder.protobuf.UserProbuf.internal_static_User_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.decoder.protobuf.UserProbuf.internal_static_User_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.decoder.protobuf.UserProbuf.User.class, com.decoder.protobuf.UserProbuf.User.Builder.class);
    }

    public static com.google.protobuf.Parser<User> PARSER =
        new com.google.protobuf.AbstractParser<User>() {
      public User parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new User(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<User> getParserForType() {
      return PARSER;
    }

    /**
     * Protobuf enum {@code User.Sex}
     */
    public enum Sex
        implements com.google.protobuf.ProtocolMessageEnum {
      /**
       * <code>F = 0;</code>
       */
      F(0, 0),
      /**
       * <code>M = 1;</code>
       */
      M(1, 1),
      ;

      /**
       * <code>F = 0;</code>
       */
      public static final int F_VALUE = 0;
      /**
       * <code>M = 1;</code>
       */
      public static final int M_VALUE = 1;


      public final int getNumber() { return value; }

      public static Sex valueOf(int value) {
        switch (value) {
          case 0: return F;
          case 1: return M;
          default: return null;
        }
      }

      public static com.google.protobuf.Internal.EnumLiteMap<Sex>
          internalGetValueMap() {
        return internalValueMap;
      }
      private static com.google.protobuf.Internal.EnumLiteMap<Sex>
          internalValueMap =
            new com.google.protobuf.Internal.EnumLiteMap<Sex>() {
              public Sex findValueByNumber(int number) {
                return Sex.valueOf(number);
              }
            };

      public final com.google.protobuf.Descriptors.EnumValueDescriptor
          getValueDescriptor() {
        return getDescriptor().getValues().get(index);
      }
      public final com.google.protobuf.Descriptors.EnumDescriptor
          getDescriptorForType() {
        return getDescriptor();
      }
      public static final com.google.protobuf.Descriptors.EnumDescriptor
          getDescriptor() {
        return com.decoder.protobuf.UserProbuf.User.getDescriptor().getEnumTypes().get(0);
      }

      private static final Sex[] VALUES = values();

      public static Sex valueOf(
          com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
        if (desc.getType() != getDescriptor()) {
          throw new java.lang.IllegalArgumentException(
            "EnumValueDescriptor is not for this type.");
        }
        return VALUES[desc.getIndex()];
      }

      private final int index;
      private final int value;

      private Sex(int index, int value) {
        this.index = index;
        this.value = value;
      }

      // @@protoc_insertion_point(enum_scope:User.Sex)
    }

    private int bitField0_;
    public static final int ID_FIELD_NUMBER = 1;
    private int id_;
    /**
     * <code>required int32 id = 1;</code>
     */
    public boolean hasId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int32 id = 1;</code>
     */
    public int getId() {
      return id_;
    }

    public static final int NAME_FIELD_NUMBER = 2;
    private java.lang.Object name_;
    /**
     * <code>required string name = 2;</code>
     */
    public boolean hasName() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required string name = 2;</code>
     */
    public java.lang.String getName() {
      java.lang.Object ref = name_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          name_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string name = 2;</code>
     */
    public com.google.protobuf.ByteString
        getNameBytes() {
      java.lang.Object ref = name_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        name_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int SEX_FIELD_NUMBER = 3;
    private com.decoder.protobuf.UserProbuf.User.Sex sex_;
    /**
     * <code>required .User.Sex sex = 3;</code>
     */
    public boolean hasSex() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>required .User.Sex sex = 3;</code>
     */
    public com.decoder.protobuf.UserProbuf.User.Sex getSex() {
      return sex_;
    }

    private void initFields() {
      id_ = 0;
      name_ = "";
      sex_ = com.decoder.protobuf.UserProbuf.User.Sex.F;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      if (!hasId()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasName()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasSex()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeInt32(1, id_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeBytes(2, getNameBytes());
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeEnum(3, sex_.getNumber());
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, id_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(2, getNameBytes());
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeEnumSize(3, sex_.getNumber());
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static com.decoder.protobuf.UserProbuf.User parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.decoder.protobuf.UserProbuf.User parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.decoder.protobuf.UserProbuf.User parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.decoder.protobuf.UserProbuf.User parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.decoder.protobuf.UserProbuf.User parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.decoder.protobuf.UserProbuf.User parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.decoder.protobuf.UserProbuf.User parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.decoder.protobuf.UserProbuf.User parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.decoder.protobuf.UserProbuf.User parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.decoder.protobuf.UserProbuf.User parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.decoder.protobuf.UserProbuf.User prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code User}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:User)
        com.decoder.protobuf.UserProbuf.UserOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.decoder.protobuf.UserProbuf.internal_static_User_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.decoder.protobuf.UserProbuf.internal_static_User_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.decoder.protobuf.UserProbuf.User.class, com.decoder.protobuf.UserProbuf.User.Builder.class);
      }

      // Construct using com.decoder.protobuf.UserProbuf.User.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        id_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        name_ = "";
        bitField0_ = (bitField0_ & ~0x00000002);
        sex_ = com.decoder.protobuf.UserProbuf.User.Sex.F;
        bitField0_ = (bitField0_ & ~0x00000004);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.decoder.protobuf.UserProbuf.internal_static_User_descriptor;
      }

      public com.decoder.protobuf.UserProbuf.User getDefaultInstanceForType() {
        return com.decoder.protobuf.UserProbuf.User.getDefaultInstance();
      }

      public com.decoder.protobuf.UserProbuf.User build() {
        com.decoder.protobuf.UserProbuf.User result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.decoder.protobuf.UserProbuf.User buildPartial() {
        com.decoder.protobuf.UserProbuf.User result = new com.decoder.protobuf.UserProbuf.User(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.id_ = id_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.name_ = name_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.sex_ = sex_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.decoder.protobuf.UserProbuf.User) {
          return mergeFrom((com.decoder.protobuf.UserProbuf.User)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.decoder.protobuf.UserProbuf.User other) {
        if (other == com.decoder.protobuf.UserProbuf.User.getDefaultInstance()) return this;
        if (other.hasId()) {
          setId(other.getId());
        }
        if (other.hasName()) {
          bitField0_ |= 0x00000002;
          name_ = other.name_;
          onChanged();
        }
        if (other.hasSex()) {
          setSex(other.getSex());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasId()) {
          
          return false;
        }
        if (!hasName()) {
          
          return false;
        }
        if (!hasSex()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.decoder.protobuf.UserProbuf.User parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.decoder.protobuf.UserProbuf.User) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private int id_ ;
      /**
       * <code>required int32 id = 1;</code>
       */
      public boolean hasId() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required int32 id = 1;</code>
       */
      public int getId() {
        return id_;
      }
      /**
       * <code>required int32 id = 1;</code>
       */
      public Builder setId(int value) {
        bitField0_ |= 0x00000001;
        id_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 id = 1;</code>
       */
      public Builder clearId() {
        bitField0_ = (bitField0_ & ~0x00000001);
        id_ = 0;
        onChanged();
        return this;
      }

      private java.lang.Object name_ = "";
      /**
       * <code>required string name = 2;</code>
       */
      public boolean hasName() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required string name = 2;</code>
       */
      public java.lang.String getName() {
        java.lang.Object ref = name_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            name_ = s;
          }
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>required string name = 2;</code>
       */
      public com.google.protobuf.ByteString
          getNameBytes() {
        java.lang.Object ref = name_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          name_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string name = 2;</code>
       */
      public Builder setName(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        name_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string name = 2;</code>
       */
      public Builder clearName() {
        bitField0_ = (bitField0_ & ~0x00000002);
        name_ = getDefaultInstance().getName();
        onChanged();
        return this;
      }
      /**
       * <code>required string name = 2;</code>
       */
      public Builder setNameBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        name_ = value;
        onChanged();
        return this;
      }

      private com.decoder.protobuf.UserProbuf.User.Sex sex_ = com.decoder.protobuf.UserProbuf.User.Sex.F;
      /**
       * <code>required .User.Sex sex = 3;</code>
       */
      public boolean hasSex() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>required .User.Sex sex = 3;</code>
       */
      public com.decoder.protobuf.UserProbuf.User.Sex getSex() {
        return sex_;
      }
      /**
       * <code>required .User.Sex sex = 3;</code>
       */
      public Builder setSex(com.decoder.protobuf.UserProbuf.User.Sex value) {
        if (value == null) {
          throw new NullPointerException();
        }
        bitField0_ |= 0x00000004;
        sex_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required .User.Sex sex = 3;</code>
       */
      public Builder clearSex() {
        bitField0_ = (bitField0_ & ~0x00000004);
        sex_ = com.decoder.protobuf.UserProbuf.User.Sex.F;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:User)
    }

    static {
      defaultInstance = new User(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:User)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_User_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_User_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\020UserProbuf.proto\"M\n\004User\022\n\n\002id\030\001 \002(\005\022\014" +
      "\n\004name\030\002 \002(\t\022\026\n\003sex\030\003 \002(\0162\t.User.Sex\"\023\n\003" +
      "Sex\022\005\n\001F\020\000\022\005\n\001M\020\001B\"\n\024com.decoder.protobu" +
      "fB\nUserProbuf"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_User_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_User_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_User_descriptor,
        new java.lang.String[] { "Id", "Name", "Sex", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
