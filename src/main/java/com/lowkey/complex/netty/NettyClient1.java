package com.lowkey.complex.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;

public class NettyClient1 {

    public static void main(String[] args) throws Exception {
        // 配置客户端线程组
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            // 创建Bootstrap实例
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // 配置ChannelPipeline
                            ch.pipeline().addLast(new StringDecoder()); // 添加解码器
                            ch.pipeline().addLast(new StringEncoder()); // 添加编码器
                            ch.pipeline().addLast(new ClientHandler()); // 添加自定义的处理器
                        }
                    });

            // 连接到服务器
            ChannelFuture future = bootstrap.connect("localhost", 8080).sync();

            // 发送消息到服务器
            future.channel().writeAndFlush("Hello from client!");

            // 等待关闭
            future.channel().closeFuture().sync();
        } finally {
            // 关闭线程组，释放资源
            group.shutdownGracefully();
        }
    }

    public static class ClientHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) {
            // 处理从服务器接收到的消息
            String response = (String) msg;
            System.out.println("Client received: " + response);
            ChannelId id = ctx.channel().id();
            System.out.println("我的ID: "+id);
            ctx.channel().writeAndFlush(id);
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入向服务端发送的消息: ");
            String s = sc.nextLine();
            ctx.channel().writeAndFlush(s);
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
            // 处理异常
            cause.printStackTrace();
            ctx.close();
        }
    }
}