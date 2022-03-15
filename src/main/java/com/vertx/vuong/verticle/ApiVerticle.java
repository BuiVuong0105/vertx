package com.vertx.vuong.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

public class ApiVerticle extends AbstractVerticle {

	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		System.out.println(String.format("Start Vericle ApiVerticle: %s", Thread.currentThread().getName()));
		Router router = Router.router(vertx);
		Future<HttpServer> futureHttpServer = vertx.createHttpServer().requestHandler(router).listen(8080);
		futureHttpServer.onComplete(new Handler<AsyncResult<HttpServer>>() {
			@Override
			public void handle(AsyncResult<HttpServer> event) {
				System.out.println(String.format("Deloy Server Success: %s", Thread.currentThread().getName()));
				startPromise.complete();
			}
		}).onFailure(new Handler<Throwable>() {
			@Override
			public void handle(Throwable event) {
				System.out.println(String.format("Deloy Server Fail: %s", Thread.currentThread().getName()));
				startPromise.fail("fail");
			}
		});
	}
}
