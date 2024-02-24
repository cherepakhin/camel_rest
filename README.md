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

#### Собственные параметры конфигурации в application.yaml

Определение в [application.yaml](https://github.com/cherepakhin/camel_rest/blob/dev/src/main/resources/application.yaml):

````yaml
myconfig:
  testDirectory: file:~/temp/testarea
  camel:
    copyFile:
      srcDirectory: file:~/temp/testarea/srcDir
      dstDirectory: file:~/temp/testarea/dstDir
````

Использование: [ru.perm.v.camelrest.rest.ParamCtrl.kt](https://github.com/cherepakhin/camel_rest/blob/dev/src/main/kotlin/ru/perm/v/camelrest/rest/ParamCtrl.kt)

Тестирование получения:

````shell
$ http 8980/camel_rest/api/params/myconfig

HTTP/1.1 200 
{
    "camel": {
        "copyFile": {
            "dstDirectory": "file:~/temp/testarea/dstDir",
            "srcDirectory": "file:~/temp/testarea/srcDir"
        }
    },
    "testDirectory": "file:~/temp/testarea"
}

````

Совет взят тут: [https://stackoverflow.com/questions/71689261/how-do-i-set-and-read-properties-in-a-springboot-application-using-kotlin](https://stackoverflow.com/questions/71689261/how-do-i-set-and-read-properties-in-a-springboot-application-using-kotlin)


### Ссылки:
[Размещение параметров в applicaton.yaml](https://www.baeldung.com/spring-yaml)


