#!/bin/bash

# Uncomment this line for debugging. Change to suspend=y to wait for debugger before starting
# export JAVA_OPTS="$JAVA_OPTS -Xdebug -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=n"

# This line must be uncommented for stateful apps
# export JAVA_OPTS="$JAVA_OPTS -Dopenejb.session-context=http -Dtomee.session-context.wrapper=http"

# Standard JVM Tuning options
export JAVA_OPTS="$JAVA_OPTS\
 -Djava.library.path=/usr/local/apr/lib\
 -Djava.net.preferIPv4Stack=true\
 -XX:+AggressiveOpts\
 -XX:+OptimizeStringConcat\
 -XX:+ResizeTLAB\
 -XX:+UseAdaptiveGCBoundary\
 -XX:+UseCompressedOops\
 -XX:+UseFastAccessorMethods\
 -XX:+UseG1GC\
 -XX:+UseNUMA\
 -XX:+UseStringDeduplication\
 -XX:+UseTLAB\
 -Xmx32M\
"

# Enable Remote JMX on port 1099
export CATALINA_OPTS="$CATALINA_OPTS\
 -Dcom.sun.management.jmxremote\
 -Dcom.sun.management.jmxremote.authenticate=false\
 -Dcom.sun.management.jmxremote.port=1099\
 -Dcom.sun.management.jmxremote.rmi.port=1099\
 -Dcom.sun.management.jmxremote.ssl=false\
 -Djava.rmi.server.hostname=127.0.0.1\
"
