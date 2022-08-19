package com.vertx.vuong.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetServerOptions;

public class TcpServerVerticle extends AbstractVerticle {

	@Override
	public void start() throws Exception {

		System.out.println(String.format("Start TCPSERVER Verticle: %s", Thread.currentThread().getName()));

		NetServerOptions options = new NetServerOptions().setPort(4321);

		NetServer server = vertx.createNetServer(options);

		server.connectHandler(socket -> {

			socket.handler(buffer -> {

				System.out.println("I received some bytes: " + buffer.length());

				Buffer buffer1 = Buffer.buffer().appendFloat(12.34f).appendInt(123);

				socket.write(buffer1);

				socket.write("some data");

				// Write a string using the specified encoding
				socket.write("some data", "UTF-16");

			});

			socket.closeHandler(v -> {
				System.out.println("The socket has been closed");
			});
		});

		server.listen(4321, "0.0.0.0", res -> {

			if (res.succeeded()) {
				System.out.println("Server is now listening: " + server.actualPort());
			}

			else {
				System.out.println("Failed to bind!");
			}
		});

	}
}
