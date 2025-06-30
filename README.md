Wilhelm
=======

[![GitHub Workflow Status]][GitHub Workflow URL]
[![Docker Hub][Docker Pulls Badge]][Docker Hub URL]
[![Apache License Badge]][Apache License, Version 2.0]

__Wilhelm__ is a webservice for proxying the _concurrent_ API calls to [QubitPi/Kugelblitz] and [QubitPi/Horten] so that
any business operation is executed in one atomic request.

Wilhelm is designed for mapping __one__ UI action to __multiple__ backend requests to ensure the business operation
is correctly and efficiently carried out. It does NOT handle security aspect, such as Authentication or Authorization,
or caching which should all be pushed down to its delegating services, i.e. [QubitPi/Kugelblitz] and [QubitPi/Horten].
For this reason, Wilhelm is suitable for a microservice architecture. More details about the design of Wilhelm can be
found at [![DeepWiki badge]][DeepWiki URL]

🚀 Quick Start
--------------

### Bootstrapping the Service

```console
docker run -d -it -p 8080:8080 -e WILHELM_NOTIFICATION_SERVICE_URL=http://my-horten:8080 jack20191124/wilhelm
```

where `my-horten` is the [QubitPi/Horten] service identifiers in Docker Compose

### Healthcheck

A healthcheck is right at your service:

```console
$ curl -v localhost:8080/health

...
< HTTP/1.1 200 OK
...
```

Development
-----------

### Running Locally

```console
make run
```

A sanity-check of `curl -v localhost:8080/health` should tell us the webservice is up and running:

```console
$ curl -v localhost:8080/health

...
< HTTP/1.1 200 OK
...
```

### Installing Code Style Checker

Wilhelm utilizes [pre-commit](https://pre-commit.com/) which sometimes doesn't give very informative
messages when its check fails in Wilhelm's CI/CD. One approach to make sure pre-commit passes is to run it locally
by installing it first:

```console
pip3 install pre-commit
```

then run pre-commit check which would fix everything in one go:

```console
pre-commit run -a
```

License
-------

The use and distribution terms for [Wilhelm]() are covered by the [Apache License, Version 2.0].

[Apache License Badge]: https://img.shields.io/badge/Apache%202.0-FE5D26.svg?style=for-the-badge&logo=Apache&logoColor=white
[Apache License, Version 2.0]: https://www.apache.org/licenses/LICENSE-2.0

[DeepWiki badge]: https://img.shields.io/badge/DeepWiki-QubitPi%2FWilhelm-2F2F2F.svg?style=for-the-badge&labelColor=FE6F26&logo=data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACwAAAAyCAYAAAAnWDnqAAAAAXNSR0IArs4c6QAAA05JREFUaEPtmUtyEzEQhtWTQyQLHNak2AB7ZnyXZMEjXMGeK/AIi+QuHrMnbChYY7MIh8g01fJoopFb0uhhEqqcbWTp06/uv1saEDv4O3n3dV60RfP947Mm9/SQc0ICFQgzfc4CYZoTPAswgSJCCUJUnAAoRHOAUOcATwbmVLWdGoH//PB8mnKqScAhsD0kYP3j/Yt5LPQe2KvcXmGvRHcDnpxfL2zOYJ1mFwrryWTz0advv1Ut4CJgf5uhDuDj5eUcAUoahrdY/56ebRWeraTjMt/00Sh3UDtjgHtQNHwcRGOC98BJEAEymycmYcWwOprTgcB6VZ5JK5TAJ+fXGLBm3FDAmn6oPPjR4rKCAoJCal2eAiQp2x0vxTPB3ALO2CRkwmDy5WohzBDwSEFKRwPbknEggCPB/imwrycgxX2NzoMCHhPkDwqYMr9tRcP5qNrMZHkVnOjRMWwLCcr8ohBVb1OMjxLwGCvjTikrsBOiA6fNyCrm8V1rP93iVPpwaE+gO0SsWmPiXB+jikdf6SizrT5qKasx5j8ABbHpFTx+vFXp9EnYQmLx02h1QTTrl6eDqxLnGjporxl3NL3agEvXdT0WmEost648sQOYAeJS9Q7bfUVoMGnjo4AZdUMQku50McDcMWcBPvr0SzbTAFDfvJqwLzgxwATnCgnp4wDl6Aa+Ax283gghmj+vj7feE2KBBRMW3FzOpLOADl0Isb5587h/U4gGvkt5v60Z1VLG8BhYjbzRwyQZemwAd6cCR5/XFWLYZRIMpX39AR0tjaGGiGzLVyhse5C9RKC6ai42ppWPKiBagOvaYk8lO7DajerabOZP46Lby5wKjw1HCRx7p9sVMOWGzb/vA1hwiWc6jm3MvQDTogQkiqIhJV0nBQBTU+3okKCFDy9WwferkHjtxib7t3xIUQtHxnIwtx4mpg26/HfwVNVDb4oI9RHmx5WGelRVlrtiw43zboCLaxv46AZeB3IlTkwouebTr1y2NjSpHz68WNFjHvupy3q8TFn3Hos2IAk4Ju5dCo8B3wP7VPr/FGaKiG+T+v+TQqIrOqMTL1VdWV1DdmcbO8KXBz6esmYWYKPwDL5b5FA1a0hwapHiom0r/cKaoqr+27/XcrS5UwSMbQAAAABJRU5ErkJggg==
[DeepWiki URL]: https://deepwiki.com/QubitPi/Wilhelm
[Docker Pulls Badge]: https://img.shields.io/docker/pulls/jack20191124/wilhelm?style=for-the-badge&logo=docker&logoColor=white&labelColor=5BBCFF&color=7EA1FF
[Docker Hub URL]: https://hub.docker.com/r/jack20191124/wilhelm

[GitHub Workflow Status]: https://img.shields.io/github/actions/workflow/status/QubitPi/Wilhelm/ci-cd.yaml?branch=master&logo=github&style=for-the-badge&label=CI/CD&labelColor=2088FF
[GitHub Workflow URL]: https://github.com/QubitPi/Wilhelm/actions/workflows/ci-cd.yaml

[QubitPi/Kugelblitz]: https://kugelblitz.qubitpi.org/
[QubitPi/Horten]: https://horten.qubitpi.org/
