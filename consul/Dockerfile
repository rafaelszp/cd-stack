FROM progrium/consul
MAINTAINER Rafael Pereira <rafaelszp.info@gmail.com>

ENTRYPOINT ["/bin/start"]

RUN rm /bin/consul /bin/start /bin/check-* /ui -rf
ADD https://releases.hashicorp.com/consul/0.7.3/consul_0.7.3_linux_amd64.zip /tmp/consul.zip
RUN cd /bin && unzip /tmp/consul.zip && chmod +x /bin/consul && rm /tmp/consul.zip

ADD https://releases.hashicorp.com/consul/0.7.3/consul_0.7.3_web_ui.zip /tmp/webui.zip
RUN mkdir /ui && cd /tmp && unzip /tmp/webui.zip -d /ui && rm /tmp/webui.zip

ONBUILD ADD ./config /config/

ADD ./start /bin/start
ADD ./check-http /bin/check-http
ADD ./check-cmd /bin/check-cmd

EXPOSE 8300 8301 8301/udp 8302 8302/udp 8400 8500 53/udp
VOLUME ["/data"]

ENV SHELL /bin/bash
CMD []