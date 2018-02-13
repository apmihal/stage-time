#!/usr/bin/env bash

# Clean up any old artifacts, and rebuild the jar
gradle clean assemble

# Create the database (will give a warning if already exists)
cf create-service cleardb spark stage-time-db

# Deploy to cf
cf push