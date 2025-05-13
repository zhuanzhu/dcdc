#!/bin/bash
DIR="$( cd "$( dirname "$0"  )" && pwd  )"
cd "$DIR"
echo $DIR
nohup java -Xmx512M -Xms512M -jar -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8212,suspend=y  "$DIR"/cms-service.jar --spring.profiles.active=dev   >> "$DIR"/nohup.out &
