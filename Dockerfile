FROM hseeberger/scala-sbt:17.0.2_1.6.2_3.1.1

RUN apt-get update

WORKDIR /4-gewinnt
ADD . /4-gewinnt

CMD sbt run