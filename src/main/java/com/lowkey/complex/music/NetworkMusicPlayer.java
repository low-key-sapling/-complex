package com.lowkey.complex.music;

import javazoom.jl.decoder.JavaLayerException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import javazoom.jl.player.Player;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class NetworkMusicPlayer {

    public static void main(String[] args) {
        String musicUrl = "http://m701.music.126.net/20240410161252/7cd39d631d016b1662e786cdf4e4384d/jdymusic/obj/wo3DlMOGwrbDjj7DisKw/14096412579/a56d/b679/d160/c69335efd8762f54b410a513dafe2d66.mp3";

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(musicUrl);
            CloseableHttpResponse response = httpClient.execute(httpGet);

            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                byte[] musicData = EntityUtils.toByteArray(entity);

                // 创建ByteArrayInputStream以供JLayer读取
                ByteArrayInputStream inputStream = new ByteArrayInputStream(musicData);

                // 创建Player对象并开始播放
                Player player = new Player(inputStream);
                player.play();

                // 等待播放结束（这里仅作为示例，实际应用中可能需要更复杂的控制逻辑）
                while (player.isComplete()) {
                    Thread.sleep(1000);
                }
            } else {
                System.err.println("Failed to fetch music: " + response.getStatusLine().toString());
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error playing music: " + e.getMessage());
        } catch (JavaLayerException e) {
            throw new RuntimeException(e);
        }
    }
}
