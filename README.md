Wilhelm
=======

[![Apache License Badge]][Apache License, Version 2.0]

__Wilhelm__ is a webservice for proxying the _atomic_ and _concurrent_ API operations on
[QubitPi/Kugelblitz](https://kugelblitz.qubitpi.org/) and [QubitPi/Horten](https://horten.qubitpi.org/) so that any
business operation is executed in one single request.

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

The use and distribution terms for [Kugelblitz]() are covered by the [Apache License, Version 2.0].

[Apache License Badge]: https://img.shields.io/badge/Apache%202.0-FE5D26.svg?style=for-the-badge&logo=Apache&logoColor=white
[Apache License, Version 2.0]: https://www.apache.org/licenses/LICENSE-2.0
