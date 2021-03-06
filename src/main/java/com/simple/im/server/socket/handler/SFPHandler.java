package com.simple.im.server.socket.handler;

import com.simple.im.server.processor.MessageProcessor;
import com.simple.im.server.message.MessageObject;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class SFPHandler extends SimpleChannelInboundHandler<MessageObject> {

	private MessageProcessor processor = new MessageProcessor();

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, MessageObject msg) throws Exception {
		processor.messageHandler(ctx.channel(), msg);
	}

}
