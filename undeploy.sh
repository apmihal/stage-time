#!/usr/bin/env bash

# Kill application running on cf
cf stop stage-time

# Unbind database service
cf unbind-service stage-time stage-time-db

# Remove database service
cf delete-service stage-time-db