package com.vertx.vuong.verticle;

import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.Message;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.JWTOptions;
import io.vertx.ext.auth.PubSecKeyOptions;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.auth.jwt.JWTAuthOptions;

public class JwtVerticle extends AbstractVerticle {

	private static final Logger LOGGER = LogManager.getLogger(HelloVerticle.class);

	private String verticleId = UUID.randomUUID().toString();

	private JWTAuth provider;

	@Override
	public void start(Promise<Void> start) {

		try {

			LOGGER.info("Deploy {} VerticleId: {}, Context: {}", this.getClass().getName(), verticleId, context);

			provider = JWTAuth.create(vertx, new JWTAuthOptions().addPubSecKey(new PubSecKeyOptions().setAlgorithm("HS256").setBuffer("+#A2m79S&wx3^uW")));

			this.vertx.eventBus().consumer("address.jwt.encode", this::handleEncode);

			this.vertx.eventBus().consumer("address.jwt.decode", this::handleDecode);

			start.complete();
		} catch (Exception e) {
			e.printStackTrace();
			start.fail(e);
		}
	}

	private void handleEncode(Message<String> event) {

		ContextInternal context = (ContextInternal) vertx.getOrCreateContext();

		LOGGER.info("Consum: {} ,context: {}", "address.jwt.encode", context.unwrap());

		String username = (String) event.body();

		JsonObject claims = new JsonObject();
		claims.put("userId", 123);
		claims.put("username", username);
		claims.put("email", "vuongbv@gmail.com");
		claims.put("phone", "0986739567");
		claims.put("type", 1);

		String token = provider.generateToken(claims, new JWTOptions().setExpiresInMinutes(500));

		event.reply(token);
	}

	private void handleDecode(Message<String> event) {

		ContextInternal context = (ContextInternal) vertx.getOrCreateContext();

		LOGGER.info("Consum: {} ,context: {}", "address.jwt.decode", context.unwrap());

		String token = (String) event.body();

		provider.authenticate(new JsonObject().put("token", token)).onSuccess(user -> {

			LOGGER.info("Decode Success: {}", user);

			JsonObject data = new JsonObject();

			data.put("userId", user.get("userId"));
			data.put("username", user.get("username"));
			data.put("email", user.get("email"));
			data.put("phone", user.get("phone"));
			data.put("type", user.get("type"));

			event.reply(data.toString());
		}).onFailure(throwable -> {
			LOGGER.info("Decode Fail: {}", throwable);
			event.reply("fail");
		});

	}
}
