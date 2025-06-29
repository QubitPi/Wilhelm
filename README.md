Wilhelm
=======

[![GitHub Workflow Status]][GitHub Workflow URL]
[![Docker Hub][Docker Pulls Badge]][Docker Hub URL]
[![Apache License Badge]][Apache License, Version 2.0]

__Wilhelm__ is a webservice for proxying the _concurrent_ API requests on
[QubitPi/Kugelblitz](https://kugelblitz.qubitpi.org/) and [QubitPi/Horten](https://horten.qubitpi.org/) so that any
business operation is executed in one atomic request.

🚀 Quick Start
--------------

```console
docker run -d -it -p 8080:8080 jack20191124/wilhelm
```

A healthcheck is right at your service:

```console
$ curl -v localhost:8080/health

...
< HTTP/1.1 200 OK
...
```

Development
-----------

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

License
-------

The use and distribution terms for [Wilhelm]() are covered by the [Apache License, Version 2.0].

[Apache License Badge]: https://img.shields.io/badge/Apache%202.0-FE5D26.svg?style=for-the-badge&logo=Apache&logoColor=white
[Apache License, Version 2.0]: https://www.apache.org/licenses/LICENSE-2.0

[Docker Pulls Badge]: https://img.shields.io/docker/pulls/jack20191124/wilhelm?style=for-the-badge&logo=docker&logoColor=white&labelColor=5BBCFF&color=7EA1FF
[Docker Hub URL]: https://hub.docker.com/r/jack20191124/wilhelm

[GitHub Workflow Status]: https://img.shields.io/github/actions/workflow/status/QubitPi/Wilhelm/ci-cd.yaml?branch=master&logo=github&style=for-the-badge&label=CI/CD&labelColor=2088FF
[GitHub Workflow URL]: https://github.com/QubitPi/Wilhelm/actions/workflows/ci-cd.yaml
