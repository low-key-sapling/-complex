package com.lowkey.complex.rpc;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yuanjifan
 * @description
 * @date 2021年12月24日 13:36
 */
public class RpcShortServer {

    private static final Logger logger = LoggerFactory.getLogger(RpcShortServer.class);

    private final int port;

    public RpcShortServer(int port) {
        this.port = port;
    }

    public void run() throws Exception {
        // 创建事件循环组，负责处理I/O操作
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            // 创建引导程序
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ChannelPipeline pipeline = ch.pipeline();
                            // 添加编解码器，此处示例使用字符串编码和解码，实际应用中应根据需要选择
                            pipeline.addLast(new StringEncoder(), new StringDecoder());
                            // 添加自定义的RPC处理器
                            pipeline.addLast(new RPCHandler());
                            // 添加日志记录器
                            pipeline.addLast(new LoggingHandler());
                            // 添加空闲状态处理器，防止连接长时间不活动导致资源浪费
                            pipeline.addLast(new IdleStateHandler(0, 0, 60));
                        }
                    });

            // 绑定端口并启动服务
            ChannelFuture future = bootstrap.bind(port).sync();
            logger.info("RPC server started on port {}", port);

            // 等待服务端口关闭
            future.channel().closeFuture().sync();
        } finally {
            // 关闭事件循环组
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        // 示例代码，创建RPC服务端实例并启动
        int port = 8080; // 默认端口
        if (args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                logger.error("Invalid port number", e);
                return;
            }
        }
        RpcShortServer server = new RpcShortServer(port);
        try {
            server.run();
        } catch (Exception e) {
            logger.error("Server failed to start", e);
        }
    }
}

// 示例RPC处理器，处理具体的RPC请求
class RPCHandler extends ChannelInboundHandlerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(RPCHandler.class);

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // 异常处理，记录日志并关闭连接
        logger.error("Exception caught in RPC handler", cause);
        ctx.close();
    }
}
