#!/usr/bin/env bash

PROFILE=$1

if [[ $PROFILE != "android" && $PROFILE != "ios" ]]; then
  echo "Profile not found. it should be 'ios' or 'android'"
  exit 0
  fi

echo "Generating sources for platform ${PROFILE} started"

mvn clean package -DskipTests -P prepare-for-upload,$PROFILE

echo "Generating sources for platform ${PROFILE} finished"
