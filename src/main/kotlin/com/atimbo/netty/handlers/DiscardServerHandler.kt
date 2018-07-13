package com.atimbo.netty.handlers

import io.netty.buffer.ByteBuf

import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter

/**
 * Handles a server-side channel.
 */
class DiscardServerHandler() : ChannelInboundHandlerAdapter() { // (1)

    override fun channelRead(ctx: ChannelHandlerContext, msg: Any) { // (2)
        // Discard the received data silently.
        (msg as ByteBuf).release() // (3)
    }

    override fun exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable) { // (4)
        // Close the connection when an exception is raised.
        cause.printStackTrace()
        ctx.close()
    }
}

