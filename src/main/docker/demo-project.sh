#!/usr/bin/env bash

#部署脚本不是很熟悉

case $1 in
    "start")
        echo "start...."
        docker run -d -p 8080:8080 -p 8081:8081 mobvoi-demo/mobvoi-demo
    ;;
    "stop")
        echo "stop"
        docker stop mobvoi-demo/mobvoi-demo
    ;;
    "restart")
        echo "restart"
        docker restart mobvoi-demo/mobvoi-demo
    ;;
    "status")
        echo "status"
        docker stats
    ;;
    *)
    echo "Usage $0 (start|stop|restart|status)"
    ;;
esac