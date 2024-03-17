package io.nio.netty.粘包半包;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.AdaptiveRecvByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LoggingHandler;

public class HelloWorldServer {

    public static void main(String[] args) throws InterruptedException {

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();

        serverBootstrap.channel(NioServerSocketChannel.class);
        // 调整系统的接收缓冲器（滑动窗口）
//        serverBootstrap.option(ChannelOption.SO_RCVBUF, 5);
        // 调整netty的接收缓冲区（byteBuf）
        serverBootstrap.childOption(ChannelOption.RCVBUF_ALLOCATOR, new AdaptiveRecvByteBufAllocator(16,16,16));
        serverBootstrap.group(boss, worker);
        serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                socketChannel.pipeline().addLast(new LoggingHandler());
            }
        });

        ChannelFuture channelFuture = serverBootstrap.bind(8080).sync();
        channelFuture.channel().closeFuture().sync();

        boss.shutdownGracefully();
        worker.shutdownGracefully();
    }
}
