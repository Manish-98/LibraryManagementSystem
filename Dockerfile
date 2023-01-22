FROM eclipse-temurin:17 AS BUILD_IMAGE
WORKDIR /LibraryManagementSystem
COPY . /LibraryManagementSystem
RUN ./gradlew clean ktlint build

FROM eclipse-temurin:17-jdk-focal
RUN set -e; \
    apt-get update -y && apt-get install -y \
    gnupg2 \
    tini \
    lsb-release; \
    gcsFuseRepo=gcsfuse-`lsb_release -c -s`; \
    echo "deb http://packages.cloud.google.com/apt $gcsFuseRepo main" | \
    tee /etc/apt/sources.list.d/gcsfuse.list; \
    curl https://packages.cloud.google.com/apt/doc/apt-key.gpg | \
    apt-key add -; \
    apt-get update; \
    apt-get install -y gcsfuse && apt-get clean \

ENV MNT_DIR /mnt/gcs
COPY --from=BUILD_IMAGE /LibraryManagementSystem/build/libs/LibraryManagementSystem.jar .
COPY run.sh ./run.sh
RUN chmod +x ./run.sh
ENTRYPOINT ["/usr/bin/tini", "--"]

CMD ["/run.sh"]
