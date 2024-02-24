## Различные тесты Apache Camel с Kotlin и Spring Boot

### Оглавление:
[Цель](#target)<br/>
[Unit тестирование](#unit_test)

<a id="target"></a>
### Цель

Cоздать небольшое приложение на <b>Kotlin</b> с использованием <b>Spring Boot</b> и  [Camel](https://camel.apache.org/).

### Ручные тесты httpie

[ru.perm.v.camelrest.EchoCtrl](https://github.com/cherepakhin/camel_rest/blob/dev/src/main/kotlin/ru/perm/v/camelrest/rest/EchoCtrl.kt):

````shell
$ http :8980/camel_rest/api/echo/aaa
````

[ru.perm.v.camelrest.CamelConvertorCtrl](https://github.com/cherepakhin/camel_rest/blob/dev/src/main/kotlin/ru/perm/v/camelrest/rest/CamelConvertorCtrl.kt):

````shell
$ http :8980/camel_rest/api/camel/copy_file
````

### Дополнительно

В application.yaml добавлен параметр конфигурации:

````yaml
myconfig:
  testDirectory: file:~/temp/testarea
````

````kotlin
@ConfigurationProperties("myconfig")
@ConstructorBinding
data class MyConfig(val testDirectory: String)
````

### Ссылки:
[Размещение параметров в applicaton.yaml](https://www.baeldung.com/spring-yaml)


