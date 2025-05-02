#!/bin/bash

# Wait for H2 database to be ready
echo "Waiting for H2 database to start..."
until nc -z h2-database 1521; do
  echo "H2 database is unavailable - sleeping"
  sleep 2
done

echo "H2 database is up - initializing"

# Execute schema.sql
echo "Executing schema.sql..."
java -cp /opt/h2/bin/h2-2.1.214.jar org.h2.tools.RunScript \
  -url "jdbc:h2:tcp://h2-database:1521/inventory" \
  -user sa \
  -password "" \
  -script "/docker-entrypoint-initdb.d/schema.sql"

# Execute data.sql
echo "Executing data.sql..."
java -cp /opt/h2/bin/h2-2.1.214.jar org.h2.tools.RunScript \
  -url "jdbc:h2:tcp://h2-database:1521/inventory" \
  -user sa \
  -password "" \
  -script "/docker-entrypoint-initdb.d/data.sql"

echo "Database initialization completed"
