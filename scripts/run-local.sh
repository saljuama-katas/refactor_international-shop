#!/bin/bash

trap ctrl_c INT
trap ctrl_c SIGINT
trap ctrl_c SIGTERM

function ctrl_c() {
        echo -e "\n\nCleaning up resources..."
        echo -e "------------------------\n"
        docker-compose -f scripts/docker-local.yml down
        exit 0
}

echo -e "\nSpinning up resources..."
echo -e "------------------------\n"
docker-compose -f scripts/docker-local.yml up -d

echo -e "\nRunning application... (press Ctrl-C to interrupt)"
echo -e "----------------------------------------------------"
./gradlew bootRun
