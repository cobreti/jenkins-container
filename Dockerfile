FROM jenkins/jenkins:lts
USER root
RUN apt-get update
RUN apt -y install apt-transport-https ca-certificates curl software-properties-common
RUN curl -O https://download.docker.com/linux/ubuntu/dists/bionic/pool/edge/amd64/containerd.io_1.2.2-3_amd64.deb
RUN apt -y install ./containerd.io_1.2.2-3_amd64.deb
RUN curl -fsSL https://download.docker.com/linux/ubuntu/gpg | apt-key add -
RUN add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu bionic stable"
RUN apt update
RUN apt-cache policy docker-ce
RUN apt -y install docker-ce
RUN systemctl enable docker
