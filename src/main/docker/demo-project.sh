#!/usr/bin/env bash

#部署脚本不是很熟悉

case $1 in
    "start")
        echo "start...."
        docker run -d -p 8080:8080 -p 8081:8081 mobvoi-demo/mobvoi-demo
    ;;
    "stop")
        echo "stop..."
        docker stop `docker ps | grep mobvoi-demo | awk '{print $1}'`
    ;;
    "restart")
        echo "restart..."
        docker restart `docker ps | grep mobvoi-demo | awk '{print $1}'`
    ;;
    "status")
        echo "status"
        docker stats `docker ps | grep mobvoi-demo | awk '{print $1}'`
    ;;
    *)
    echo "Usage $0 (start|stop|restart|status)"
    ;;
esac