# tomee-jms-perf


The purpose of this project is to showcase a possible bug in TomEE. Making a JMS Session object avialable via CDI seems to create problems.

## Setup

```
git clone git@github.com:exabrial/tomee-jms-perf.git
cd tomee-jms-perf
```

## Reproduce the issue

```
git checkout does-not-work
mvn clean package tomee:exec && java -jar target/tomee-jms-perf-1.0.0-SNAPSHOT-exec.jar
```

## Don't reproduce the issue

```
git checkout works-just-fine
mvn clean package tomee:exec && java -jar target/tomee-jms-perf-1.0.0-SNAPSHOT-exec.jar
```
