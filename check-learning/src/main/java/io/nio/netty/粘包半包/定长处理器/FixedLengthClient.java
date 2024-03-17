package io.nio.netty.粘包半包.定长处理器;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class FixedLengthClient {

    public static void main(String[] args) {
        try {
            Bootstrap bootstrap = new Bootstrap();
            NioEventLoopGroup worker = new NioEventLoopGroup();
            bootstrap.group(worker);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {

                    socketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter(){

                        @Override
                        public void channelActive(ChannelHandlerContext ctx) throws Exception {
                            super.channelActive(ctx);
                        }
                    });
                }
            });

            bootstrap.bind("localhost", 8080).sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }

    }
}
