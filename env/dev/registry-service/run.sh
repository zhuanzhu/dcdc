#!/bin/bash
DIR="$( cd "$( dirname "$0"  )" && pwd  )"
cd "$DIR"
echo $DIR
nohup java -Xmx128M -Xms128M -jar "$DIR"/registry-server.jar --spring.profiles.active=prd   >> "$DIR"/nohup.out &
