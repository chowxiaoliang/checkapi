package io.nio.netty.粘包半包;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorldClient {

    private static final Logger logger = LoggerFactory.getLogger(HelloWorldClient.class);

    public static void main(String[] args) {
        for (int i=0;i<10;i++){
            send();
        }
    }

    private static void send(){
        NioEventLoopGroup worker = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(worker);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                        // 连接建立成功后会触发active事件
                        @Override
                        public void channelActive(ChannelHandlerContext ctx) throws Exception {
//                            for (int i=0;i<100;i++){
//                                ByteBuf byteBuf = ctx.alloc().buffer(16);
//                                byteBuf.writeBytes(new byte[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15});
//                                ctx.writeAndFlush(byteBuf);
//                                // 发一次关一次
//                                ctx.channel().close();
//                            }

                            ByteBuf byteBuf = ctx.alloc().buffer(16);
                            byteBuf.writeBytes(new byte[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15});
                            ctx.writeAndFlush(byteBuf);
                            // 发一次关一次
                            ctx.channel().close();
                        }
                    });
                }
            });

            ChannelFuture channelFuture = bootstrap.connect("localhost", 8080).sync();
            channelFuture.channel().closeFuture().sync();
        }catch (Exception e){
            logger.error("error", e);
        }finally {
            worker.shutdownGracefully();
        }
    }
}
