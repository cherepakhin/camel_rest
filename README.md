## Различные тесты Apache Camel с Kotlin и Spring Boot

### Оглавление:
[Цель](#target)<br/>
[Unit тестирование](#unit_test)

<a id="target"></a>
### Цель

Cоздать небольшое приложение на <b>Kotlin</b> с использованием <b>Spring Boot</b> и  [Camel](https://camel.apache.org/).

### Ручные тесты httpie

__ru.perm.v.camelrest.EchoCtrl__:

````shell
$ http :8980/camel_rest/api/echo/aaa
````

__ru.perm.v.camelrest.CamelConvertorCtrl__:

````shell
$ http :8980/camel_rest/api/camel/copyFile
````
