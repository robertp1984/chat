FROM debian:trixie-slim AS builder

ARG VERSION=0.34.2
ARG URL=https://github.com/prometheus/alertmanager/releases/download/v${VERSION}/alertmanager-${VERSION}.linux-amd64.tar.gz

RUN apt update && apt install -y curl
RUN curl -L -o /tmp/alertmanager.tar.gz ${URL}
RUN mkdir -p /tmp/alertmanager && tar -C /tmp/alertmanager --strip-components=1 -xzvf /tmp/alertmanager.tar.gz

# Build actual image
FROM debian:trixie-slim

RUN apt update && apt install -y gettext


RUN mkdir -p /etc/alertmanager
COPY --from=builder /tmp/alertmanager/alertmanager /bin/alertmanager
COPY --from=builder /tmp/alertmanager/amtool /bin/amtool
COPY --from=builder /tmp/alertmanager/alertmanager.yml /etc/alertmanager/alertmanager.yml
RUN chown -R nobody:nogroup /etc/alertmanager

COPY entrypoint.sh /entrypoint.sh
RUN chmod a+x /entrypoint.sh

WORKDIR /alertmanager

USER nobody

ENTRYPOINT ["/entrypoint.sh"]
