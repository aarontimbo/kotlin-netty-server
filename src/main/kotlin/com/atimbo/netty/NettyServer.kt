package com.atimbo.netty

import com.atimbo.netty.server.DiscardServer

fun main(args: Array<String>) {
    println("Hello, world!")
    var port = 8080

    if (args.isNotEmpty()) {
        port = Integer.parseInt(args[0])
    }
    DiscardServer(port).run()
}
