FROM openjdk:8

ENTRYPOINT ["/bin/start.sh"]
EXPOSE 10080
EXPOSE 10443
VOLUME /timeservice

ADD start.sh /bin/start.sh
ADD https://releases.hashicorp.com/consul/0.7.3/consul_0.7.3_linux_amd64.zip /usr/bin/
RUN unzip /usr/bin/consul_0.7.3_linux_amd64.zip -d /usr/bin/ && chmod +x /usr/bin/consul

