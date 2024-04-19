package com.lowkey.complex.music;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lowkey.complex.util.HttpClientUtil;
import javazoom.jl.player.Player;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.util.*;

public class MusicSearchPlayer {
    //设置Main函数的日志级别
    static {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        List<Logger> loggerList = loggerContext.getLoggerList();
        loggerList.forEach(logger -> logger.setLevel(Level.INFO));
    }

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(NetworkMusicPlayer.class);
    static final HttpClientUtil httpClientUtil = new HttpClientUtil();
    static final List<String> songName = Arrays.asList("stay%2Bwith%2Bme", "写给辛夷的歌", "遗憾", "天下的乌鸦一般黑");
    //keyword : 关键字
    static final String songSearchUrl = "https://mobilecdn.kugou.com/api/v3/search/song?format=json&page=1&pagesize=20&showtype=1&keyword=";

    public static void main(String[] args) throws Exception {
        while (true) {
            try {
                for (String name : songName) {
                    String result = httpClientUtil.post(songSearchUrl + name);
                    logger.debug(result);
                    HashMap<String, JSONObject> hashMap = JSON.parseObject(result, HashMap.class);
                    JSONObject songs = hashMap.get("data");
                    List<HashMap> list = JSON.parseArray(songs.get("info").toString(), HashMap.class);
                    if (list != null) {
                        //取前五个
                        ArrayList<String> objects = Lists.newArrayList();
                        for (int i = 0; i < 3; i++) {
                            if (list.get(i) != null) {
                                objects.add(list.get(i).get("hash").toString());
                            }
                        }
                        playSong(objects);
                    }
                }
            } catch (Exception e) {
                logger.error("error", e);
                break;
            }
        }
    }

    private static void playSong(List<String> songIdList) throws Exception {
        for (String songId : songIdList) {
            //获取详细信息
            String songUrl = "http://m.kugou.com/app/i/getSongInfo.php?cmd=playInfo&hash=" + songId;
            String song = httpClientUtil.post(songUrl);
            HashMap<String, String> songDetail = JSON.parseObject(song, HashMap.class);
            String fileName = songDetail.get("fileName");
            String fileUrl = songDetail.get("url");
            logger.info("fileName = {}, fileUrl = {}", fileName, fileUrl);
            String error = songDetail.get("error");
            if ("需要付费".equals(error)) {
                logger.error("需要付费,下一个...wait...");
                continue;
            }

            HashMap<String, String> songMap = Maps.newHashMap();
            songMap.put("fileName", fileName);
            songMap.put("fileUrl", fileUrl);
            ArrayList<HashMap<String, String>> songList = Lists.newArrayList();
            songList.add(songMap);
            if (StringUtils.isBlank(fileUrl)) {
                HashMap<String, Object> songDetail1 = JSON.parseObject(song, HashMap.class);
                String s = songDetail1.get("backup_url").toString();
                if (StringUtils.isNotBlank(s) && !"{}".equals(s)) {
                    JSONArray backupUrl = JSON.parseArray(songDetail1.get("backup_url").toString());
                    fileUrl = backupUrl.getString(0);
                } else {
                    continue;
                }
            }

            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                HttpGet httpGet = new HttpGet(fileUrl);
                CloseableHttpResponse response = httpClient.execute(httpGet);

                if (response.getStatusLine().getStatusCode() == 200) {
                    HttpEntity entity = response.getEntity();
                    byte[] musicData = EntityUtils.toByteArray(entity);

                    // 创建ByteArrayInputStream以供JLayer读取
                    ByteArrayInputStream inputStream = new ByteArrayInputStream(musicData);

                    // 创建Player对象并开始播放
                    Player player = new Player(inputStream);
                    player.play();
                    logger.info("播放完毕,等待下一个,wait......");
                    // 等待播放结束（这里仅作为示例，实际应用中可能需要更复杂的控制逻辑）
                    Thread.sleep(1000);
                } else {
                    logger.error("Failed to fetch music: " + response.getStatusLine().toString());
                }
            } catch (Exception e) {
                logger.error("Error playing music: " + e.getMessage(), e);
            }
        }
    }
}
