package com.vertx.vuong.verticle;

import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.sql.SQLClient;
import io.vertx.ext.sql.SQLConnection;

public class DatabaseVerticle extends AbstractVerticle {
	
	static final Logger LOGGER = LogManager.getLogger(DatabaseVerticle.class);

	private String verticleId = UUID.randomUUID().toString();

	private SQLClient sqlClient;
	
	@Override
	public void start(Promise<Void> start) {
		
		LOGGER.info("Deploy {} VerticleId: {}, Context: {}", this.getClass().getName() ,verticleId, context);
		
		configSqlClient()
		
		.onComplete(handler -> {
			LOGGER.info("Connect DB Success");
			start.complete();
		})
		
		.onFailure(handler -> {
			LOGGER.info("Connect DB Fail");
			start.fail(handler.getCause());
		});
	}
	
	private Future<Void> configSqlClient() {
		return Future.future(promise -> {
			sqlClient = JDBCClient.createShared(vertx, config().getJsonObject("db"));
			promise.complete();
		});
	}
	
	private Future<ResultSet> querytWithParameters(SQLConnection sqlConnection, String query, JsonArray params) {
		return Future.future(promise -> sqlConnection.queryWithParams(query, params, promise));
	}
	
	private Future<JsonObject> mapToFirstResult(ResultSet rs) {
		
		if(rs.getNumRows() >= 1) {
			return Future.succeededFuture(rs.getRows().get(0));
		}
		else {
			return Future.failedFuture("No result");
		}
	}
	
	private Future<JsonArray> mapToJsonArray(ResultSet rs) {
		return Future.succeededFuture(new JsonArray(rs.getRows()));
	}
}
