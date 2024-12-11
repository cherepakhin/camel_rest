## Различные тесты Apache Camel с Kotlin и Spring Boot

### Оглавление:
[Цель](#target)<br/>
[Запуск](#run)<br/>
[Ручная проверка работоспособности сервиса](#manual_test)<br/>
[Unit тестирование](#unit_test)<br/>
[Собственные параметры конфигурации в application.yaml](#yaml_params)<br/>
[Deploy to Nexus](#nexus)<br/>
[Запуск и разработка в offline режиме](#run_offline)<br/>
[Примечания](#tose)<br/>
[Ссылки](#links)<br/>

<a id="target"></a>
### Цель

Cоздать небольшое приложение на <b>Kotlin</b> с использованием <b>Spring Boot</b> и  [Camel](https://camel.apache.org/).

Подобный проект на __Java__ [https://github.com/cherepakhin/spring-boot-camel](https://github.com/cherepakhin/spring-boot-camel).

<a id="run"></a>
### Запуск

````shell
$ ./gradlew bootRun
````

Сделан скрипт запуска в корне проекта _run_project.sh_ 

````shell
$ ./run_project.sh
````

(Для отправки запросов в REST Controller использована утилита [httpie](https://httpie.io/))

<a id="manual_test"></a>
### Ручная проверка работоспособности сервиса

[ru.perm.v.camelrest.EchoCtrl](https://github.com/cherepakhin/camel_rest/blob/dev/src/main/kotlin/ru/perm/v/camelrest/rest/EchoCtrl.kt):

````shell
$ http :8980/camel_rest/api/echo/aaa
````

### Для демонстрации сделаны Rest контроллеры в пакете [ru.perm.v.camelrest.rest.camel](https://github.com/cherepakhin/camel_rest/blob/dev/src/main/kotlin/ru/perm/v/camelrest/rest/camel/).

## Функции Camel

### Копирование файлов

По GET запросу, файлы копируются из каталога в каталог. Каталоги указаны в application.yaml.  

````yaml
myconfig:
  testDirectory: file:/tmp/testarea
  camelContainer:
    jobParamCopyFile:
      srcDirectory: ${myconfig.testDirectory}/srcDir?noop=true
      dstDirectory: ${myconfig.testDirectory}/dstDir
````

[ru.perm.v.camelrest.camel.CamelCopyFileRoute](https://github.com/cherepakhin/camel_rest/blob/dev/src/main/kotlin/ru/perm/v/camelrest/rest/camel/CamelCopyFileRoute.kt):

(о задании СВОИХ параметров в application.yaml описано в [https://v.perm.ru/main/index.php/homepage/69-prostoj-proekt-na-kotlin-i-spring-boot#add_param_to_application_yaml](https://v.perm.ru/main/index.php/homepage/69-prostoj-proekt-na-kotlin-i-spring-boot#add_param_to_application_yaml))

Выполнение запроса:

````shell
$ http http://127.0.0.1:8980/camel_rest/api/camel/copy_file
````

<a id="yaml_params"></a>
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
$ http :8980/camel_rest/api/params/myconfig

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

<a id="nexus"></a>
### Deploy to Nexus

````shell
camel_rest/proj$ ./gradlew publish
````

<a id="run_offline"></a>
### Запуск и разработка в offline режиме:

````shell
./gradlew --offline test
./gradlew --offline bootRun
````

Библиотеки загружаются один раз.

<a id="tose"></a>
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

<a id="links"></a>
### Ссылки:
[Размещение СОБСТВЕННЫХ параметров в applicaton.yaml](https://www.baeldung.com/spring-yaml)
