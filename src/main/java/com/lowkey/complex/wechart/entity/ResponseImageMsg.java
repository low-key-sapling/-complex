package com.lowkey.complex.wechart.entity;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 图片消息响应实体类
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class ResponseImageMsg  extends ResponseMsgBody {
    /** 图片媒体ID */
    @XmlElementWrapper(name = "Image")
    private String[] MediaId;
    public String[] getMediaId() {return MediaId;}
    public void setMediaId(String[] mediaId) {MediaId = mediaId;}
}