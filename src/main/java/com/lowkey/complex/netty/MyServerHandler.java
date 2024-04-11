package com.lowkey.complex.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.ConcurrentHashMap;

public class MyServerHandler extends ChannelInboundHandlerAdapter {

    private static final ConcurrentHashMap<String, ChannelHandlerContext> clientChannels = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, String> clientAddress = new ConcurrentHashMap<>();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        // 保存客户端的ChannelHandlerContext
        System.out.println(ctx.channel().remoteAddress());
        clientChannels.put(ctx.channel().remoteAddress().toString(), ctx);
        clientAddress.put("one", ctx.channel().remoteAddress().toString());
        MyServerHandler.sendMessageToClient("主动发消息，嘿嘿嘿。", ctx.channel().remoteAddress().toString());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        // 处理客户端发送的消息
        String message = (String) msg;
        System.out.println("Received message from client: " + message);
        System.out.println("Received message from client id: " + ctx.channel().id());

        // 可以选择向客户端回复消息
        ctx.writeAndFlush("Server received: " + message);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        // 移除客户端的ChannelHandlerContext
        clientChannels.remove(ctx);
    }

    public static void sendMessageToClient(String message, String clientAddress) {
        ChannelHandlerContext context = clientChannels.get(clientAddress);
        if (context != null) {
            context.channel().writeAndFlush(message);
        }
    }
}