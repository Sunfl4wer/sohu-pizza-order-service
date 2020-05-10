# Sohu Pizza Ordering Website
## Order Service
This service provides APIs for order service that can be used save order information to database
## API contract
See the [wiki](https://github.com/Sunfl4wer/sohu-pizza-order-service/wiki) of this repository for the API contract
## How to run
1. Access API from Heroku
* The service is currently being host on Heroku, you can access the API using these server name
    1. Directly to the service: `https://sohu-pizza-order-service.herokuapp.com`
    2. Through a NGINX gateway: `https://sohu-pizza-backend.herokuapp.com`
2. Build and run docker image
    1. Open powershell(window) or terminal(linux) change directory to the cloned local repository of this repository
    2. In the application-prod.properties
        * Comment this [line](https://github.com/Sunfl4wer/sohu-pizza-order-service/blob/3a8131c3b0388b9dd12857bf92149de605b33f34/src/main/resources/application-prod.properties#L10)
        * Uncomment this [line](https://github.com/Sunfl4wer/sohu-pizza-order-service/blob/3a8131c3b0388b9dd12857bf92149de605b33f34/src/main/resources/application-prod.properties#L9)
    3. Run this command `mvn package`
    4. Run this command `docker build -f Dockerfile -t docker-order-service .`
    5. Run this command `docker volume create mongo_order`. This will create a volume name _mongo_product_ in docker
    6. Run this command `docker-compose up` to run the service on docker
