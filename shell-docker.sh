#!/bin/bash
#-Dspring.config.location=./config/
java -jar $JAVA_TOOL_OPTIONS -Xms3g -Xmx3g vertx-dev-1.0.0-SNAPSHOT-run.jar -cluster -Djava.net.preferIPv4Stack=true