FROM nginx:latest

ENTRYPOINT ["/bin/start.sh"]
EXPOSE 80
VOLUME /templates
ENV CONSUL_URL consul:8500

ADD start.sh /bin/start.sh
RUN rm -v /etc/nginx/conf.d/*.conf

ADD https://releases.hashicorp.com/consul-template/0.18.0/consul-template_0.18.0_linux_amd64.tgz /usr/bin/
RUN tar zvxf /usr/bin/consul-template_0.18.0_linux_amd64.tgz -C /usr/bin
