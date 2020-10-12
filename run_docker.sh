#mvn com.google.cloud.tools:jib-maven-plugin:dockerBuild -Dimage=windprofiler

docker build . -t springbootdefault

docker rm -f springbootdefault

docker volume create --name maven-repo

# docker local db
#docker run -d --restart unless-stopped --name windprofiler -p 8000:8000 \
#  --link docker_mariadb \
#  -v maven-repo:/root/.m2 \
#  -v /docker_data/springbootdefault/logs:/springbootdefault/logs \
#  -v /mnt:/dv-datadirectory \
#  springbootdefault

docker run -d --restart unless-stopped --name springbootdefault -p 8000:8000 \
-v maven-repo:/root/.m2 \
-v /docker_data/springbootdefault/logs:/springbootdefault/logs \
-v /mnt:/dv-datadirectory \
springbootdefault