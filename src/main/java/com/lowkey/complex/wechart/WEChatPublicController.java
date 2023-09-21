package com.lowkey.complex.wechart;

import com.alibaba.fastjson.JSONObject;
import com.github.benmanes.caffeine.cache.Cache;
import com.lowkey.complex.util.HttpClientWrapper;
import com.lowkey.complex.wechart.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Date;

/**
 * @author yuanjifan
 * @description 微信公众号控制器
 * @date 2021年11月18日 9:56
 */
@Slf4j
@RestController
@RequestMapping("/test/wechat/public")
public class WEChatPublicController {
    private final WechatUtil wechatUtil;
    private final Cache<String, Object> caffeineCache;

    public WEChatPublicController(WechatUtil wechatUtil, Cache<String, Object> caffeineCache) {
        this.wechatUtil = wechatUtil;
        this.caffeineCache = caffeineCache;
    }

    /**
     * @return java.lang.String
     * @author yuanjifan
     * @description 从ES获取Nba数据
     * @date 2021/11/18 11:04
     */
    @RequestMapping("/access/token")
    public void getAccessToken() throws IOException, URISyntaxException {
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=" + wechatUtil.getGrantType() + "&appid=" + wechatUtil.getAppid() + "&secret=" + wechatUtil.getAppsecret();
        HttpClientWrapper client = new HttpClientWrapper();
        String response = client.doGet(url);
        JSONObject jsonObject = JSONObject.parseObject(response);
        String accessToken = jsonObject.getString("access_token");
        log.info("accessToken = {}", accessToken);
        WechatUtil.getWechatCache().put("accessToken", accessToken);
    }

    /**
     * 微信公众号接口配置验证
     *
     * @return 验证信息
     */
    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public String checkSignature(String signature, String timestamp, String nonce, String echostr) {
        log.info("signature = {}", signature);
        log.info("timestamp = {}", timestamp);
        log.info("nonce = {}", nonce);
        log.info("echostr = {}", echostr);
        // 第一步：自然排序
        String[] tmp = {wechatUtil.getToken(), timestamp, nonce};
        Arrays.sort(tmp);
        // 第二步：sha1 加密
        String sourceStr = StringUtils.join(tmp);
        String localSignature = DigestUtils.sha1Hex(sourceStr);
        // 第三步：验证签名
        if (signature.equals(localSignature)) {
            return echostr;
        }
        return null;
    }

    /**
     * 接收用户消息
     *
     * @param receiveMsgBody 消息
     * @return
     */
    @RequestMapping(value = "/check", method = RequestMethod.POST, produces = {"application/xml; charset=UTF-8"})
    public ResponseMsgBody getUserMessage(@RequestBody ReceiveMsgBody receiveMsgBody) {
        log.info("接收到的消息：{}", receiveMsgBody);
        MsgType msgType = MsgType.getMsgType(receiveMsgBody.getMsgType());
        switch (msgType) {
            case text:
                log.info("接收到的消息类型为{}", msgType.getMsgTypeDesc());
                ResponseMsgBody textMsg = new ResponseMsgBody();
                textMsg.setToUserName(receiveMsgBody.getFromUserName());
                textMsg.setFromUserName(receiveMsgBody.getToUserName());
                textMsg.setCreateTime(new Date().getTime());
                textMsg.setMsgType(MsgType.text.getMsgType());
                textMsg.setContent(receiveMsgBody.getContent());
                log.info("返回的消息为{}", textMsg);
                return textMsg;
            case image:
                log.info("接收到的消息类型为{}", MsgType.image.getMsgTypeDesc());
                ResponseImageMsg imageMsg = new ResponseImageMsg();
                imageMsg.setToUserName(receiveMsgBody.getFromUserName());
                imageMsg.setFromUserName(receiveMsgBody.getToUserName());
                imageMsg.setCreateTime(new Date().getTime());
                imageMsg.setMsgType(MsgType.image.getMsgType());
                imageMsg.setMediaId(new String[]{receiveMsgBody.getMediaId()});
                return imageMsg;
            case voice:
                log.info("接收到的消息类型为{}", MsgType.voice.getMsgTypeDesc());
                ResponseVoiceMsg voiceMsg = new ResponseVoiceMsg();
                voiceMsg.setToUserName(receiveMsgBody.getFromUserName());
                voiceMsg.setFromUserName(receiveMsgBody.getToUserName());
                voiceMsg.setCreateTime(new Date().getTime());
                voiceMsg.setMsgType(MsgType.voice.getMsgType());
                voiceMsg.setMediaId(new String[]{receiveMsgBody.getMediaId()});
                return voiceMsg;
            default:
                // 其他类型
                break;
        }
        return null;
    }
}
