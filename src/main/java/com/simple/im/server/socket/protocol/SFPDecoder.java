package com.simple.im.server.socket.protocol;

import java.util.List;

import com.simple.im.server.message.MessageObject;
import com.simple.im.server.message.MessageStatus;
import org.msgpack.MessagePack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class SFPDecoder extends ByteToMessageDecoder {
	
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		try {
			// 实现反序列化
			int length = in.readableBytes();
			byte[] array = new byte[length];
			String content = new String(array, in.readerIndex(), length);
			
			if(content != null && !"".equals(content.trim())){
				//判断是否是我的自定义协议
				if(!MessageStatus.isSFP(content)){
					ctx.channel().pipeline().remove(this);
					return;
				}
			}

			in.getBytes(in.readerIndex(), array, 0, length);
			out.add(new MessagePack().read(array, MessageObject.class));
			in.clear();
		} catch (Exception e) {
			ctx.channel().pipeline().remove(this);
		}
	}
	
}
