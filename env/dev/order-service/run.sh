#!/bin/bash
DIR="$( cd "$( dirname "$0"  )" && pwd  )"
cd "$DIR"
echo $DIR
nohup java -Xmx512M -Xms512M -jar "$DIR"/order-service.jar --spring.profiles.active=prd   >> "$DIR"/nohup.out &
