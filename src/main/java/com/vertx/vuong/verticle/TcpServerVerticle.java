package com.vertx.vuong.verticle;

import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetServerOptions;

public class TcpServerVerticle extends AbstractVerticle {
	
	private static final Logger LOGGER = LogManager.getLogger(TcpServerVerticle.class);
	
	private String verticleId = UUID.randomUUID().toString();

	@Override
	public void start() throws Exception {
		
		LOGGER.info("Deploy {} VerticleId: {}, Context: {}", this.getClass().getName() ,verticleId, context);

		NetServerOptions options = new NetServerOptions().setPort(4321);

		NetServer server = vertx.createNetServer(options);

		server.connectHandler(socket -> {

			socket.handler(buffer -> {

				LOGGER.info("I received some bytes: {}", buffer.length());

				Buffer buffer1 = Buffer.buffer().appendFloat(12.34f).appendInt(123);

				socket.write(buffer1);

				socket.write("some data");

				// Write a string using the specified encoding
				socket.write("some data", "UTF-16");

			});

			socket.closeHandler(v -> {
				LOGGER.info("The socket has been closed");
			});
		});

		server.listen(4321, "0.0.0.0", res -> {

			if (res.succeeded()) {
				LOGGER.info("Server is now listening: {}", server.actualPort());
			}

			else {
				LOGGER.info("Failed to bind!");
			}
		});

	}
}
