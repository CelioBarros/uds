# UDS test
To run this project, you will need to configure the host and install the dependencies.


## Running with Docker

### Dependencies
You need to have [docker](https://docs.docker.com/install/) and [docker-compose](https://docs.docker.com/compose/install/) installed.

### Preparing the files
Go to the root folder and run the command `./mvnw clean package`. It will create the `.jar` file of the service allowing you to run it in a container.

### Starting the service in a container
To run the generated file in a container, simply run `docker-compose up`.
For more informations about the docker compose usage, please go to the [docs](https://docs.docker.com/compose/).

### To run test
`./mvnw test`