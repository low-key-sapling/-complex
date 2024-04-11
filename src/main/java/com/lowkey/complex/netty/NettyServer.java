package com.lowkey.complex.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class NettyServer {

    public static void main(String[] args) throws Exception {
        // 配置服务端
        EventLoopGroup bossGroup = new NioEventLoopGroup(); // 接受客户端连接
        EventLoopGroup workerGroup = new NioEventLoopGroup(); // 处理已接受的连接
        try {
            //初始化服务器引导程序（ServerBootstrap）
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) {
                            // 添加处理器到pipeline
                            ch.pipeline().addLast(new StringDecoder()); // 解码器，将ByteBuf转换为String
                            ch.pipeline().addLast(new StringEncoder()); // 编码器，将String转换为ByteBuf
                            //设置通道处理器（ChannelHandler）
                            ch.pipeline().addLast(new MyServerHandler());
                        }
                    });

            // 绑定端口并启动服务
            ChannelFuture f = b.bind(8080).sync();
            System.out.println("Server started at port 8080");

            // 等待服务器套接字关闭
            f.channel().closeFuture().sync();
        } finally {
            // 关闭事件循环组，释放所有资源
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static class ServerHandler extends ChannelInboundHandlerAdapter {
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
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
            // 处理异常
            cause.printStackTrace();
            ctx.close();
        }
    }
}