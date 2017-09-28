#!/bin/sh
# loading dependency jar in lib directory

base_dir=$(dirname $0)/..
main_class=com.ehl.tvc.lxw.service.RealServiceWithKafka
service_name=test-spark
args="-d 1 -path /app/test -isYarn true  -yarnFile service.conf"
executeJar=$base_dir/lib/realtime-1.0-SNAPSHOT.jar

for file in $base_dir/lib/*.jar;
do
    #if [ "$file" != "$base_dir/lib/offline-base-1.0-SNAPSHOT.jar" ]; then
      JARS=$JARS,$file
    #fi
done
  JARS=${JARS:1}

if [ -z "$BASE_OPTS" ]; then
  BASE_OPTS="-Dservice-conf=$base_dir/conf/service.conf"
fi

java_options="$base_dir/conf/service.conf"

$base_dir/bin/spark-submit.sh  "$main_class" "$service_name" "$executeJar" "${args}" "${java_options}" "${JARS}"