#!/bin/bash

# Script to run all JSON test files against the OMS microservices

echo "=== Running OMS Microservices API Tests ==="
echo ""

# Initialize H2 database
initialize_database() {
    echo "=== Initializing H2 Database ==="
    
    # Path to schema and data SQL files
    SCHEMA_SQL="../schema.sql"
    DATA_SQL="../data.sql"
    
    # Check if SQL files exist
    if [ ! -f "$SCHEMA_SQL" ]; then
        echo "❌ Schema SQL file not found: $SCHEMA_SQL"
        return 1
    fi
    
    if [ ! -f "$DATA_SQL" ]; then
        echo "❌ Data SQL file not found: $DATA_SQL"
        return 1
    fi
    
    # Find running H2 database containers
    H2_CONTAINER=$(docker ps | grep h2-database | awk '{print $1}')
    
    if [ -z "$H2_CONTAINER" ]; then
        echo "❌ H2 database container not running. Please start the containers first."
        return 1
    fi
    
    echo "Found H2 database container: $H2_CONTAINER"
    
    # Copy SQL files to container
    echo "Copying schema and data SQL files to container..."
    docker cp "$SCHEMA_SQL" "$H2_CONTAINER:/tmp/schema.sql"
    docker cp "$DATA_SQL" "$H2_CONTAINER:/tmp/data.sql"
    
    # Execute SQL files
    echo "Executing schema.sql..."
    docker exec "$H2_CONTAINER" java -cp /opt/h2/bin/h2-2.1.214.jar org.h2.tools.RunScript -url "jdbc:h2:tcp://localhost:1521/oms" -user sa -password "" -script "/tmp/schema.sql"
    
    if [ $? -ne 0 ]; then
        echo "❌ Failed to execute schema.sql"
        return 1
    fi
    
    echo "Executing data.sql..."
    docker exec "$H2_CONTAINER" java -cp /opt/h2/bin/h2-2.1.214.jar org.h2.tools.RunScript -url "jdbc:h2:tcp://localhost:1521/oms" -user sa -password "" -script "/tmp/data.sql"
    
    if [ $? -ne 0 ]; then
        echo "❌ Failed to execute data.sql"
        return 1
    fi
    
    echo "✅ Database initialization completed"
    echo ""
    return 0
}

# Function to run a test from a JSON file
run_test() {
    local test_file=$1
    local service_name=$2
    
    echo "Testing $service_name: $(basename $test_file)"
    
    # Extract request details from JSON
    method=$(jq -r '.request.method' $test_file)
    url=$(jq -r '.request.url' $test_file)
    
    # Prepare headers
    headers=""
    if jq -e '.request.headers' $test_file > /dev/null; then
        while IFS="=" read -r key value; do
            headers="$headers -H \"$key: $value\""
        done < <(jq -r '.request.headers | to_entries[] | "\(.key)=\(.value)"' $test_file)
    fi
    
    # Prepare body if it exists
    body=""
    if jq -e '.request.body' $test_file > /dev/null; then
        body="-d '$(jq -c '.request.body' $test_file)'"
    fi
    
    # Build and execute curl command
    cmd="curl -s -X $method $headers $body \"$url\""
    echo "Command: $cmd"
    
    # Execute the command and capture response
    response=$(eval $cmd)
    status=$?
    
    if [ $status -ne 0 ]; then
        echo "❌ Test failed: curl command failed with status $status"
        return 1
    fi
    
    echo "Response: $response"
    echo "✅ Test completed"
    echo ""
    
    return 0
}

# Initialize the database first
initialize_database
if [ $? -ne 0 ]; then
    echo "❌ Database initialization failed. Exiting tests."
    exit 1
fi

# Run tests for each microservice
echo "=== Inventory Controller Tests ==="
for test_file in inventory-controller/*.json; do
    run_test "$test_file" "Inventory Controller"
done

echo "=== Store Search Controller Tests ==="
for test_file in storesearch-controller/*.json; do
    run_test "$test_file" "Store Search Controller"
done

echo "=== Payment Service Tests ==="
for test_file in payment-service/create-payment.json; do
    run_test "$test_file" "Payment Service"
done

echo "=== Shipping Price Controller Tests ==="
for test_file in shippingprice-controller/*.json; do
    run_test "$test_file" "Shipping Price Controller"
done

echo "=== Order Controller Tests ==="
for test_file in order-controller/*.json; do
    run_test "$test_file" "Order Controller"
done

echo "=== Product Controller Tests ==="
for test_file in product-controller/*.json; do
    run_test "$test_file" "Product Controller"
done

echo "=== Fulfillment Controller Tests ==="
for test_file in fulfillment-controller/*.json; do
    run_test "$test_file" "Fulfillment Controller"
done

echo "=== Email Service Tests ==="
for test_file in email-service/*.json; do
    run_test "$test_file" "Email Service"
done

echo "=== All tests completed ==="
