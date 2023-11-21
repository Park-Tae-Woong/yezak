FROM nginx
LABEL authors="hgchoi"

#RUN rm -rf /etc/nginx/conf.d/default.conf
#WORKDIR /var/lib/jenkins/workspace/api-dev-git-puller/nginx

CMD ["echo ls"]

COPY  conf.d /etc/nginx/conf.d
#COPY ./app.conf  /var/lib/jenkins/workspace/nginx/conf.d/app.conf
#COPY ./nginx.conf  /var/lib/jenkins/workspace/nginx/conf.d/nginx.conf
#
#VOLUME ["/data", "/etc/nginx", "/var/log/nginx"]



CMD ["nginx"]

#ENTRYPOINT ["top", "-b"]