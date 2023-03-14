package com.lowkey.complex.socket;

/**
 * @author yuanjifan
 * @description
 * @date 2021年12月24日 10:38
 */

import java.io.OutputStream;
import java.net.Socket;

public class RpcLongClient {

    private static final int SERVER_PORT = 2010;

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("127.0.0.1", SERVER_PORT);
        System.out.println("Connected to server...sending command string");
        OutputStream out = socket.getOutputStream();
        int i = 0;
        while (i < 10) {
            out.write(("test" + i).getBytes());  // 发送
            out.flush();
            Thread.sleep(1000);
            i++;
        }
        out.close();
        socket.close();
    }
}