## Различные тесты Apache Camel с Kotlin и Spring Boot

### Оглавление:
[Цель](#target)<br/>
[Unit тестирование](#unit_test)

<a id="target"></a>
### Цель

Cоздать небольшое приложение на <b>Kotlin</b> с использованием <b>Spring Boot</b> и  [Camel](https://camel.apache.org/).

Подобный проект на __Java__ [https://github.com/cherepakhin/spring-boot-camel](https://github.com/cherepakhin/spring-boot-camel).

### Ручная проверка работоспособности сервиса

[ru.perm.v.camelrest.EchoCtrl](https://github.com/cherepakhin/camel_rest/blob/dev/src/main/kotlin/ru/perm/v/camelrest/rest/EchoCtrl.kt):

````shell
$ http :8980/camel_rest/api/echo/aaa
````

### Для демонстрации сделаны Rest контроллеры в пакете [ru.perm.v.camelrest.rest.camel](https://github.com/cherepakhin/camel_rest/blob/dev/src/main/kotlin/ru/perm/v/camelrest/rest/camel/).

Для отправки запросов в REST Controller использована утилита [httpie](https://httpie.io/).

## Функции Camel

### Копирование файлов

[ru.perm.v.camelrest.camel.CamelCopyFileRoute](https://github.com/cherepakhin/camel_rest/blob/dev/src/main/kotlin/ru/perm/v/camelrest/rest/camel/CamelCopyFileRoute.kt):

````shell
$ http http://127.0.0.1:8980/camel_rest/api/camel/copy_file
````

### Примечания

[application.yaml](https://github.com/cherepakhin/spring-boot-camel/blob/main/src/main/resources/application.yaml):

````shell
camel:
    springboot:
        main-run-controller: true
````

Запускает контроллер Camel и заменяет:

````shell
val context = DefaultCamelContext()
context.addRoutes(camelCopyFileRoute)
context.start()
context.stop()
````

(см. [https://github.com/cherepakhin/camel_rest](https://github.com/cherepakhin/camel_rest/blob/dev/src/main/kotlin/ru/perm/v/camelrest/rest/CamelConvertorCtrl.kt))


#### Собственные параметры конфигурации в application.yaml

Определение в [application.yaml](https://github.com/cherepakhin/camel_rest/blob/dev/src/main/resources/application.yaml):

````yaml
myconfig:
  testDirectory: file:/tmp/testarea
  camelContainer:
    jobParamCopyFile:
      srcDirectory: ${myconfig.testDirectory}/srcDir
      dstDirectory: ${myconfig.testDirectory}/dstDir
````

> (в yaml файле использована общая переменная __myconfig.testDirectory__)

__myconfig__ использован в [ru.perm.v.camelrest.rest.ParamCtrl.kt](https://github.com/cherepakhin/camel_rest/blob/dev/src/main/kotlin/ru/perm/v/camelrest/rest/ParamCtrl.kt)

Тестирование получения параметров Camel параметров:

````shell
$ http 8980/camel_rest/api/params/myconfig

HTTP/1.1 200 
{
    "camel": {
        "copyFile": {
            "dstDirectory": "file:~/tmp/testarea/dstDir",
            "srcDirectory": "file:~/tmp/testarea/srcDir"
        }
    },
    "testDirectory": "file:~/tmp/testarea"
}

````

Совет взят тут: [https://stackoverflow.com/questions/71689261/how-do-i-set-and-read-properties-in-a-springboot-application-using-kotlin](https://stackoverflow.com/questions/71689261/how-do-i-set-and-read-properties-in-a-springboot-application-using-kotlin)


### Ссылки:
[Размещение параметров в applicaton.yaml](https://www.baeldung.com/spring-yaml)


