docker build --build-arg ENV=sit -t microservice .
docker build --build-arg PROJECT_NAME=BaseSpringBoot -t dockersb -f DockerImage .
#docker build --build-arg PROJECT_NAME=Login -t dockerlogin -f DockerImage .
docker rmi microservice
