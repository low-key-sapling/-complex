package com.lowkey.complex.socket;

/**
 * @author yuanjifan
 * @description
 * @date 2021年12月24日 10:36
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;

public class RpcLongServer {

    private static final int SERVER_PORT = 2010;

    private static Hashtable<String, Socket> ht = new Hashtable();

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        System.out.println("VNTCenter.main()===TCP SERVER==============");
        ServerSocket vntServer = null;
        try {
            vntServer = new ServerSocket(SERVER_PORT);

            System.out.println("Listening Port is " + vntServer.getLocalPort() + "...");
            while (true) {
                Socket vntClient = vntServer.accept();
                System.out.println("have a client entering , the IP is " + vntClient.getInetAddress() + "...");
                System.out.println("have a client entering , the Port is " + vntClient.getPort() + "...");
                new GetGpsThreadFun(vntClient).start();
                ht.put(vntClient.getInetAddress().toString(), vntClient);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            if (vntServer != null) {
                vntServer.close();
            }
        }
    }
}

class GetGpsThreadFun extends Thread {
    private final Socket vntThreadClient;

    public GetGpsThreadFun(Socket vntThreadSocket) {
        vntThreadClient = vntThreadSocket;
    }

    public void run() {
        try {
            byte[] data = new byte[128];
            while (true) {
                int len = vntThreadClient.getInputStream().read(data);
                if (len > 0) {
                    System.out.println(vntThreadClient.getInetAddress() + ":" + vntThreadClient.getPort() + ":" + new String(data, 0, len));
                    if ("shutdown".equals(vntThreadClient)) {
                        break;
                    }
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

}