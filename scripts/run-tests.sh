#!/bin/bash

echo -e "\nSpinning up resources..."
echo -e "------------------------\n"
docker-compose -f scripts/docker-test.yml up -d

echo -e "\nRunning tests..."
echo -e "----------------"
./gradlew test

echo -e "\nCleaning up resources..."
echo -e "------------------------\n"
docker-compose -f scripts/docker-test.yml down
