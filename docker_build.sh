#./gradlew build
# size=52914

./gradlew bootBuildImage
# size=56783818
# BUILD SUCCESSFUL in 3m 8s

docker build --build-arg JAR_FILE=build/libs/camel_rest-0.24.0105.jar -t shop_kotlin/app .
docker run -p 8980:8980 -p 8988:8988  docker.io/library/camel_rest:0.24.0105
http :8980/shop_kotlin/api/echo/aaaa