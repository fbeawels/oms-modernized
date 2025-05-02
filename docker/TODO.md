# Docker Compose Setup for OMS Microservices

## Tasks

- [x] Create a comprehensive docker-compose.yaml file
  - [x] Configure all 8 microservices:
    - [x] inventory-controller
    - [x] storesearch-controller
    - [x] payment-service
    - [x] shippingprice-controller
    - [x] order-controller
    - [x] product-controller
    - [x] fulfillment-controller
    - [x] email-service
  - [x] Configure H2 database service
  - [x] Configure Nginx proxy service
  - [x] Set up network configuration
  - [x] Configure ports for all services
  - [x] Set up environment variables
  - [x] Configure volumes for persistent storage

- [x] Create schema.sql file for database initialization
  - [x] Combine schema definitions from all microservices
  - [x] Ensure no table conflicts

- [x] Create data.sql file for test data
  - [x] Format data as SQL insert statements

- [x] Configure Nginx as a reverse proxy
  - [x] Create nginx.conf file
  - [x] Set up endpoints for each microservice
  - [x] Configure OpenAPI endpoint for Swagger documentation
  - [x] Create unified Swagger UI for all microservices

- [x] Test the complete setup
  - [x] Verify all services start correctly
  - [x] Confirm services can communicate with each other
  - [x] Validate Nginx proxy routing
