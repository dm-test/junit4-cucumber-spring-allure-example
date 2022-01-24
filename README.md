# junit4-cucumber-spring-allure-example
Скелет проекта автоматизированных тестов на Cucumber, с поддержкой Allure-reporting и многопоточности.
В качестве DI выступает Spring

## Требования к ПО разработчика
* [OpenJDK11 Hotspot](https://adoptium.net/?variant=openjdk11&jvmVariant=hotspot)
* [Maven3](https://maven.apache.org/download.cgi)
* [IntelliJ Cucumber support](https://www.jetbrains.com/help/idea/enabling-cucumber-support-in-project.html)

## Запуск тестов
```
mvn clean test -Dcucumber.filter.tags=@allure.id:1629681
```

## Генерация Allure-отчета
```
mvn allure:serve
```