package com.lowkey.complex.music;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import cn.hutool.core.map.MapUtil;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class NetworkMusicPlayer {
    //设置Main函数的日志级别
    static {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        List<Logger> loggerList = loggerContext.getLoggerList();
        loggerList.forEach(logger -> logger.setLevel(Level.INFO));
    }

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(NetworkMusicPlayer.class);
    static final HttpClientUtil httpClientUtil = new HttpClientUtil();

    //新歌曲榜单URL
    static final String newSongListUrl = "https://m.kugou.com/?json=true";
    //音乐排行榜,包含rankId
    static final String songUrl = "https://m.kugou.com/rank/list&json=true";
    //音乐排行榜,rankid 排行榜分类下id,json 返回类型
    //rankid=8888/按歌曲喜爱用户数的总量排序
    //rankid=6666/按歌曲喜爱用户数的涨幅排序
    //rankid=59703/酷狗音乐发布蜂鸟流行音乐榜
    //rankid=52144/抖音热门歌曲
    //rankid=52767/快手热门歌曲
    //rankid=24971/DJ
    //rankid=44412/说唱
    static List<String> rankidList = Lists.newArrayList("8888", "6666", "59703", "52144", "52767", "24971", "44412");
    static String rankid = "8888";
    static int rankidCount = 0;
    static final String rankSongListUrl = "https://m.kugou.com/rank/info/?json=true&rankid=";
    //音乐歌单,音乐排行榜:返回结果包含specialid
    static final String songListUrl = "https://m.kugou.com/plist/index&json=true";
    //获取 歌单下的音乐列表，需要添加 specialid:歌单ID:specialid 125032
    static final String specialSongListUrl = "https://m.kugou.com/plist/list/125032?json=true";
    //获取 歌手分类：包含classid
    static final String singerUrl = "https://m.kugou.com/singer/class&json=true";
    //歌手分类下面的歌手列表:classid:88
    static final String singerListUrl = "https://m.kugou.com/singer/list/88?json=true";
    //歌手分类下面的歌手歌曲列表:singerid : 歌手id 3060
    static final String singerSongListUrl = "https://m.kugou.com/singer/info/3060?json=true";
    //获取 歌曲音乐详情信息:hash : 音乐列表下的 音乐id
    static final String songDetailUrl = "https://m.kugou.com/app/i/getSongInfo.php?cmd=playInfo&hash=e257bfc0bf14495d26f2f82eba7d2fcb";
    //获取 音乐详情-带歌词版本:hash : 音乐列表下的 音乐id
    static final String songLyricDetailUrl = "https://www.kugou.com/yy/index.php?r=play/getdata&hash=e257bfc0bf14495d26f2f82eba7d2fcb";
    //说明: 获取 热门搜索列表
    //必选参数:
    //plat :开始数
    //count : 热门搜索关键字返回
    static final String songHotUrl = "https://mobilecdn.kugou.com/api/v3/search/hot?format=json&plat=0&count=30";
    //说明: 获取 音乐搜索结果
    //必选参数:
    //keyword : 关键字
    static final String songSearchUrl = "https://mobilecdn.kugou.com/api/v3/search/song?format=json&keyword=%E7%8E%8B%E5%8A%9B%E5%AE%8F&page=1&pagesize=20&showtype=1";
    static int page = 1;

    public static void main(String[] args) throws Exception {
        while (true) {
            try {
                List<String> songIdList = getSongIdList();
                playSong(songIdList);
                page++;
            } catch (Exception e) {
                logger.error("exception happen. exit.", e);
                break;
            }
        }
    }

    private static List<String> getSongIdList() throws Exception {
        ArrayList<String> songIdList = Lists.newArrayList();
        String post = httpClientUtil.post(rankSongListUrl + rankid + "&page=" + page);
        logger.debug("post:{}", post);
        HashMap<String, JSONObject> hashMap = JSON.parseObject(post, HashMap.class);
        JSONObject songs = hashMap.get("songs");
        List<HashMap> list = JSON.parseArray(songs.get("list").toString(), HashMap.class);
        for (HashMap map : list) {
            if (StringUtils.isNoneBlank(MapUtil.getStr(map, "sqhash"))) {
                songIdList.add(MapUtil.getStr(map, "sqhash"));
            }
        }
        logger.info("songIdList size = {}", songIdList.size());
        if (songIdList.size() == 0) {
            page = 1;
            rankid = rankidList.get(rankidCount);
            rankidCount++;
            if (rankidCount == 7) {
                rankidCount = 0;
            }
        }
        return songIdList;
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
                if (Objects.isNull(songDetail1.get("backup_url"))) {
                    continue;
                }
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
