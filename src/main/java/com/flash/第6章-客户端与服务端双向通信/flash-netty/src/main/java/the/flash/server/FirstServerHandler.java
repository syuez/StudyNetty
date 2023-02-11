package the.flash.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @author chao.yu
 * chao.yu@dianping.com
 * @date 2018/08/04 06:21.
 */
public class FirstServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ByteBuf buffer = getByteBuf(ctx);
        System.out.println(new Date() + ": 服务端写出数据 -> " + buffer.toString(StandardCharsets.UTF_8));
        ctx.channel().writeAndFlush(buffer);
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        byte[] bytes = "你好，我是钢铁侠，收到请回复！".getBytes(StandardCharsets.UTF_8);

        ByteBuf buffer = ctx.alloc().buffer();

        buffer.writeBytes(bytes);

        return buffer;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println(new Date() + ": 服务端读到数据 -> " + byteBuf.toString(StandardCharsets.UTF_8));
    }

//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) {
//        ByteBuf byteBuf = (ByteBuf) msg;
//
//        System.out.println(new Date() + ": 服务端读到数据 -> " + byteBuf.toString(StandardCharsets.UTF_8));
//
//        // 回复数据到客户端
//        System.out.println(new Date() + ": 服务端写出数据");
//        ByteBuf out = getByteBuf(ctx);
//        // 写入数据
//        ctx.channel().writeAndFlush(out);
//    }
//
//    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
//        byte[] bytes = "你好，欢迎关注我的微信公众号，《闪电侠的博客》!".getBytes(StandardCharsets.UTF_8);
//
//        ByteBuf buffer = ctx.alloc().buffer();
//
//        buffer.writeBytes(bytes);
//
//        return buffer;
//    }
}
