package com.atimbo.netty.server

import com.atimbo.netty.handlers.DiscardServerHandler
import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.ChannelInitializer
import io.netty.channel.ChannelOption
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioServerSocketChannel

public class DiscardServer constructor(val port: Int) {
    public fun run() {
        val bossGroup = NioEventLoopGroup()     //1
        val workerGroup = NioEventLoopGroup()

        try {
            val serverBootstrap = ServerBootstrap()     //2
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel::class.java)  //3
                    .childHandler(object: ChannelInitializer<SocketChannel>() {     //4 TODO: Abstract this to a class
                        override fun initChannel(socketChannel: SocketChannel) {
                            socketChannel.pipeline().addLast(DiscardServerHandler())
                        }

                    })
                    .option(ChannelOption.SO_BACKLOG, 128)  //5
                    .childOption(ChannelOption.SO_KEEPALIVE, true)  //6

            // Bind and start to accept incoming connections
            val channelFuture = serverBootstrap.bind(port).sync()   //7

            // Wait until the server socket is closed
            // This doesn't happen here, but it can be done to gracefully shutdown the server
            channelFuture.channel().closeFuture().sync()
        } finally {
            workerGroup.shutdownGracefully()
            bossGroup.shutdownGracefully()
        }
    }

//    public fun main(args: Array<String>) {
//        var port = 8080
//
//        if (args.isNotEmpty()) {
//            port = Integer.parseInt(args[0])
//        }
//        DiscardServer(port).run()
//    }
}

