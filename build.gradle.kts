plugins {
	application
	kotlin("jvm") version "1.2.50"
}

application {
	mainClassName = "com.atimbo.netty.NettyServerKt"
}

dependencies {
	compile(kotlin("stdlib"))
	compile("io.netty:netty-all:5.0.0.Alpha2")
}

repositories {
	mavenLocal()
	jcenter()
	mavenCentral()
}
