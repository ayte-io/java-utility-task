# Ayte :: Utility :: Task

[![CircleCI](https://img.shields.io/circleci/project/github/ayte-io/java-utility-task.svg?style=flat-square)](https://circleci.com/gh/ayte-io/java-utility-task)
[![Maven Central](https://img.shields.io/maven-central/v/io.ayte.utility.task/parent.svg?style=flat-square)](https://mvnrepository.com/artifact/io.ayte.utility.task)
[![Code Climate maintainability](https://img.shields.io/codeclimate/maintainability/ayte-io/java-utility-task.svg?style=flat-square)](https://codeclimate.com/github/ayte-io/java-utility-task)
[![Sonar Tech Debt](https://img.shields.io/sonar/https/sonarcloud.io/io.ayte.utility.task:parent/tech_debt.svg?style=flat-square)](https://sonarcloud.io/dashboard?id=io.ayte.utility.task%3Aparent)

[![MIT License](https://img.shields.io/badge/license-MIT-brightgreen.svg?style=flat-square)](LICENSE-MIT)
[![UPL-1.0 License](https://img.shields.io/badge/license-UPL&dash;1.0-brightgreen.svg?style=flat-square)](LICENSE-UPL-1.0)

A throwing runnable, a non-returning callable.

## Coordinates

Project is divided into API (interfaces only), 
[`io.ayte.utility.task:api`](https://mvnrepository.com/artifact/io.ayte.utility.task/api), and implementations,
[`io.ayte.utility.task:kit`](https://mvnrepository.com/artifact/io.ayte.utility.task/kit).

Both artifacts have same module names, `io.ayte.utility.task.api` and
`io.ayte.utility.task.kit`.

The project itself is part of Ayte 
[utility](https://github.com/ayte-io/java-utility) library collection.

## Description

This project contains two interfaces (and related infrastructure) for
two interfaces, `io.ayte.utility.task.api.Task` and 
`io.ayte.utility.task.api.AsyncTask`. Task is a Supplier counterpart 
between runnable and callable: it doesn't return value, but it may throw 
an exception, so it has signature of

```java
interface Task<E extends Throwable> {
    void execute() throws E;
}
```

AsyncTask transfers this contract onto returned CompletableFuture, thus
being non-generic (since it can pass exception by other means):

```java
interface AsyncTask {
    CompletableFuture<Void> execute();
}
```

Project is split into two artifacts: `io.ayte.utility.task:api`
contains just interfaces, while `io.ayte.utility.task:kit` contains
both API dependency and helper classes (`Tasks` and `AsyncTasks`). 
Project is fully java 8-compatible, exposing `io.ayte.utility.task.api`
and `io.ayte.utility.task.kit` modules.

If you're looking for functions that may throw, then you probably want 
to look into [io.ayte.utility.action](https://github.com/ayte-io/java-utility-action)
repository.

## Licensing

MIT & UPL-1.0

Ayte Labs, 2019

Do whatever you want.
