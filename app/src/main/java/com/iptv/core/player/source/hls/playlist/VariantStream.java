package com.iptv.core.player.source.hls.playlist;

import android.net.Uri;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class VariantStream {
    private Map<String, String> mAttributeTable;
    private Uri mUri;

    private VariantStream(Map<String, String> attributeTable, Uri uri) {
        mAttributeTable = attributeTable;
        mUri = uri;
    }

    /**
     * 获取带宽
     */
    public int getBandwidth() {
        String bandwidth = mAttributeTable.get(Attribute.ATTR_BANDWIDTH);

        return Integer.parseInt(bandwidth);
    }

    /**
     * 获取URI
     */
    public Uri getUri() {
        return mUri;
    }

    /**
     * 是否定义了VideoGroup
     */
    public boolean containsVideo() {
        return mAttributeTable.containsKey(Attribute.ATTR_VIDEO);
    }

    /**
     * 获取VideoGroup的ID
     */
    public String getVideo() {
        return mAttributeTable.get(Attribute.ATTR_VIDEO);
    }

    /**
     * 是否定义了AudioGroup
     */
    public boolean containsAudio() {
        return mAttributeTable.containsKey(Attribute.ATTR_AUDIO);
    }

    /**
     * 获取AudioGroup的ID
     */
    public String getAudio() {
        return mAttributeTable.get(Attribute.ATTR_AUDIO);
    }

    /**
     * 是否定义了SubtitleGroup
     */
    public boolean containsSubtitle() {
        return mAttributeTable.containsKey(Attribute.ATTR_SUBTITLE);
    }

    /**
     * 获取SubtitleGroup的ID
     */
    public String getSubtitle() {
        return mAttributeTable.get(Attribute.ATTR_SUBTITLE);
    }

    public static class Builder {
        private Map<String, String> mAttributeTable;
        private Uri mUri;

        public Builder() {
            mAttributeTable = new HashMap<String, String>();
        }

        public void setAttributeList(List<Attribute> attributeList) {
            for (int i = 0; i < attributeList.size(); i++) {
                Attribute attribute = attributeList.get(i);

                mAttributeTable.put(attribute.getKey(), attribute.getValue());
            }
        }

        public void setUri(String uri) {
            mUri = Uri.parse(uri);
        }

        public VariantStream build() {
            return new VariantStream(mAttributeTable, mUri);
        }
    }
}
