package com.iptv.core.hls.playlist.tag;

import com.iptv.core.hls.playlist.attribute.Attribute;
import com.iptv.core.hls.playlist.attribute.AttributeList;
import com.iptv.core.hls.playlist.datatype.QuotedString;
import com.iptv.core.hls.playlist.datatype.Resolution;

/**
 * 流标签
 */
public final class StreamInfTag extends Tag {
    private AttributeList mAttributeList;

    /**
     * 构造函数
     */
    public StreamInfTag(AttributeList attributeList) {
        super(Name.STREAM_INF);

        mAttributeList = attributeList;
    }

    /**
     * 是否定义了属性
     */
    public boolean containsAttribute(String attributeName) {
        return mAttributeList.containsAttribute(attributeName);
    }

    /**
     * 获取带宽
     */
    public int getBandwidth() {
        Attribute attribute = mAttributeList.get(Attribute.Name.BANDWIDTH);
        return attribute.getIntegerValue();
    }

    /**
     * 获取平均带宽
     */
    public int getAvgBandwidth() {
        Attribute attribute = mAttributeList.get(Attribute.Name.AVG_BANDWIDTH);
        return attribute.getIntegerValue();
    }

    /**
     * 获取媒体编码格式
     */
    public String[] getCodecs() {
        Attribute attribute = mAttributeList.get(Attribute.Name.CODECS);
        return attribute.getQuotedStringValue().getContent().split(",");
    }

    /**
     * 获取视频图像宽
     */
    public int getVideoWidth() {
        Attribute attribute = mAttributeList.get(Attribute.Name.RESOLUTION);
        return attribute.getResolutionValue().getWidth();
    }

    /**
     * 获取视频图像高
     */
    public int getVideoHeight() {
        Attribute attribute = mAttributeList.get(Attribute.Name.RESOLUTION);
        return attribute.getResolutionValue().getHeight();
    }

    /**
     * 获取视频帧率
     */
    public float getVideoFrameRate() {
        Attribute attribute = mAttributeList.get(Attribute.Name.FRAME_RATE);
        return attribute.getFloatValue();
    }

    /**
     * 获取HDCP层次
     */
    public String getHDCPLevel() {
        Attribute attribute = mAttributeList.get(Attribute.Name.HDCP_LEVEL);
        return attribute.getQuotedStringValue().getContent();
    }

    /**
     * 获取音频（展示）组的id
     */
    public String getAudioGroupId() {
        Attribute attribute = mAttributeList.get(Attribute.Name.AUDIO);
        return attribute.getQuotedStringValue().getContent();
    }

    /**
     * 获取视频（展示）组的id
     */
    public String getVideoGroupId() {
        Attribute attribute = mAttributeList.get(Attribute.Name.VIDEO);
        return attribute.getQuotedStringValue().getContent();
    }

    /**
     * 获取字幕（展示）组的id
     */
    public String getSubtitleGroupId() {
        Attribute attribute = mAttributeList.get(Attribute.Name.SUBTITLES);
        return attribute.getQuotedStringValue().getContent();
    }

    /**
     * 获取隱藏字幕（展示）组的id
     */
    public String getClosedCaptionGroupId() {
        Attribute attribute = mAttributeList.get(Attribute.Name.CLOSED_CAPTIONS);
        return attribute.getQuotedStringValue().getContent();
    }

    @Override
    public int getProtocolVersion() {
        int protocolVersion = 1;

        if (containsAttribute(Attribute.Name.RESOLUTION)) {
            protocolVersion = 2;
        }

        if (containsAttribute(Attribute.Name.AUDIO)
                || containsAttribute(Attribute.Name.VIDEO)) {
            protocolVersion = 4;
        }

        if (containsAttribute(Attribute.Name.SUBTITLES)) {
            protocolVersion = 5;
        }

        if (containsAttribute(Attribute.Name.CLOSED_CAPTIONS)) {
            protocolVersion = 6;
        }

        if (containsAttribute(Attribute.Name.AVG_BANDWIDTH)
                || containsAttribute(Attribute.Name.FRAME_RATE)
                || containsAttribute(Attribute.Name.HDCP_LEVEL)) {
            protocolVersion = 7;
        }

        return protocolVersion;
    }

    @Override
    public String toString() {
        return mName + ":" + mAttributeList.toString();
    }

    /**
     * 建造者
     */
    public static class Builder {
        private AttributeList mAttributeList = new AttributeList();

        /**
         * 构造函数
         */
        public Builder() {
            /**
             * nothing
             */
        }

        /**
         * 设置带宽
         */
        public void setBandwidth(int bandwidth) {
            Attribute attribute = new Attribute(Attribute.Name.BANDWIDTH, bandwidth);
            mAttributeList.put(attribute);
        }

        /**
         * 设置平均带宽
         */
        public void setAvgBandwidth(int bandwidth) {
            Attribute attribute = new Attribute(Attribute.Name.AVG_BANDWIDTH, bandwidth);
            mAttributeList.put(attribute);
        }

        /**
         * 设置媒体编码格式
         */
        public void setCodecs(String[] codecs) {
            Attribute attribute = new Attribute(Attribute.Name.CODECS,
                    new QuotedString(codecs, ','));
            mAttributeList.put(attribute);
        }

        /**
         * 设置视频分辨率
         */
        public void setVideoResolution(int width, int height) {
            Attribute attribute = new Attribute(Attribute.Name.RESOLUTION,
                    new Resolution(width, height));
            mAttributeList.put(attribute);
        }

        /**
         * 设置视频帧率
         */
        public void setVideoFrameRate(float frameRate) {
            Attribute attribute = new Attribute(Attribute.Name.FRAME_RATE, frameRate);
            mAttributeList.put(attribute);
        }

        /**
         * 设置HDCP层次
         */
        public void setHDCPLevel(String level) {
            Attribute attribute = new Attribute(Attribute.Name.HDCP_LEVEL, level);
            mAttributeList.put(attribute);
        }

        /**
         * 设置音频（展示）组的id
         */
        public void setAudioGroupId(String groupId) {
            Attribute attribute = new Attribute(Attribute.Name.AUDIO,
                    new QuotedString(groupId));
            mAttributeList.put(attribute);
        }

        /**
         * 设置视频（展示）组的id
         */
        public void setVideoGroupId(String groupId) {
            Attribute attribute = new Attribute(Attribute.Name.VIDEO,
                    new QuotedString(groupId));
            mAttributeList.put(attribute);
        }

        /**
         * 设置字幕（展示）组的id
         */
        public void setSubtitleGroupId(String groupId) {
            Attribute attribute = new Attribute(Attribute.Name.SUBTITLES,
                    new QuotedString(groupId));
            mAttributeList.put(attribute);
        }

        /**
         * 设置隱藏字幕（展示）组的id
         */
        public void setClosedCaptionGroupId(String groupId) {
            Attribute attribute = new Attribute(Attribute.Name.CLOSED_CAPTIONS,
                    new QuotedString(groupId));
            mAttributeList.put(attribute);
        }

        /**
         * 创建
         */
        public StreamInfTag build() {
            return new StreamInfTag(mAttributeList);
        }
    }
}
