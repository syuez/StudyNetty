package com.syuez;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class NettyClient {

    private static final int MAX_RETRY = 10;

    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();

        AtomicReference<Channel> channelReference  = new AtomicReference<>();

        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) {
                        channelReference.set(ch);
                        ch.pipeline().addLast(new StringEncoder());
                    }
                });

        connect(bootstrap, "127.0.0.1", 8000, MAX_RETRY);

        while (true) {
            Channel channel = channelReference.get();
            if(channel != null && channel.isActive()) {
                channel.writeAndFlush(new Date() + ": hello world!");
            }
            Thread.sleep(5000);
        }
    }

    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
         bootstrap.connect(host, port).addListeners(future -> {
            if(future.isSuccess()) {
                System.out.println("连接成功");
            } else if(retry == 0) {
                System.err.println("重试次数已用完，放弃连接");
            } else {
                // 第几次重连
                int order = (MAX_RETRY - retry) + 1;
                // 本次重连的间隔
                int delay = 1 << order;
                System.err.println(new Date() + ": 连接失败，第" + order + "次重连......");
                bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit.SECONDS);
            }
        });
    }
}
