package com.iptv.core.ts;

/**
 * transport_packet
 */
public class TransportPacket {
    private static final int PACKET_SIZE = 188;

    private int mPacketId;

    private boolean mIsPayloadUnitStart;
    private int mContinuityCounter;

    private long mProgramClockReference;
    private byte[] mPayloadData;

    private TransportPacket(int packetId, boolean isPayloadUnitStart, int continuityCounter,
                            long programClockReference, byte[] payloadData) {
        mPacketId = packetId;

        mIsPayloadUnitStart = isPayloadUnitStart;
        mContinuityCounter = continuityCounter;

        mProgramClockReference = programClockReference;
        mPayloadData = payloadData;
    }

    /**
     * 获取PID
     */
    public int getPacketId() {
        return mPacketId;
    }

    /**
     * 是否包含负载数据
     */
    public boolean containsPayloadData() {
        return mPayloadData != null;
    }

    /**
     * 获取连续性计数
     */
    public int getContinuityCounter() {
        return mContinuityCounter;
    }

    /**
     * 负载数据是否是负载单元的起始
     */
    public boolean isPayloadUnitStart() {
        return mIsPayloadUnitStart;
    }

    /**
     * 获取负载数据
     */
    public byte[] getPayloadData() {
        return mPayloadData;
    }

    /**
     * 获取节目时钟参考
     */
    public long getProgramClockReference() {
        return mProgramClockReference;
    }

    public static TransportPacket parse(byte[] data) {
        return null;
    }
}
