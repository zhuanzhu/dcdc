#!/bin/bash
DIR="$( cd "$( dirname "$0"  )" && pwd  )"
cd "$DIR"
echo $DIR
nohup java -Xmx512M -Xms512M -jar "$DIR"/cms-service.jar --spring.profiles.active=dev   >> "$DIR"/nohup.out &
