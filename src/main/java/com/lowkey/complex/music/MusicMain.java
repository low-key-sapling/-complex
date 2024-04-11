package com.lowkey.complex.music;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lowkey.complex.util.HttpClientUtil;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

public class MusicMain {
    static Player player = null;

    public static void main(String[] args) throws Exception {

        getMusic();
        // 等待播放结束（这里仅作为示例，实际应用中可能需要更复杂的控制逻辑）
        while (player.isComplete()) {
            getMusic();
        }
    }

    public static void getMusic() {
        try {
           // 1、根据歌曲id解析歌曲
            //https://dataiqs.com/api/netease/music/?type=songid&id=2026224214
            String randomMusic = "https://dataiqs.com/api/netease/music/?type=random";
            HttpClientUtil httpClientUtil = new HttpClientUtil();
            String post = httpClientUtil.post(randomMusic);
            System.out.println(post);
            HashMap<String, JSONObject> hashMap = JSON.parseObject(post, HashMap.class);

            JSONObject data = hashMap.get("data");
            String musicUrl = data.get("song_url").toString();
            //编写一个音乐播放的程序
            String url = "http://m10.music.126.net/20240410171109/2df65763e8e66dbea0ce10e9ab3d8cee/ymusic/0630/fa15/769e/e739439db8aa7cebfe82e1f5800eca3d.mp3";
            // 打开网络连接并获取输入流
            URLConnection connection = new URL(musicUrl).openConnection();
            BufferedInputStream in = new BufferedInputStream(connection.getInputStream());

            // 创建Player对象并开始播放
            player = new Player(in);
            player.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
