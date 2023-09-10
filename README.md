<h1 >Проект автоматизации тестирования интернет-магазина <a href="https://www.chitai-gorod.ru/ ">Читай-город</a></h1>

## Содержание

* <a href="#annotation">Описание</a>
* <a href="#tools">Технологии и инструменты</a>
* <a href="#console">Запуск тестов из терминала</a>
* <a href="#jenkins">Запуск тестов в Jenkins</a>
* <a href="#allure">Отчеты в Allure</a>
* <a href="#testops">Интеграция с Allure TestOps</a>
* <a href="#testops">Интеграция с Jira</a>
* <a href="#telegram">Уведомления в Telegram с использованием бота</a>
* <a href="#video">Пример прогона теста в Selenoid</a>

<a id="annotation"></a>
## Описание
Проект включает в себя: веб-тесты (UI), тесты API и мобильные тесты (Android).\
Краткий список интересных фактов о проекте:
- [x] Конфигурация с использованием библиотеки `Owner` для запуска тестов в зависимости от параметров сборки
- [x] Возможность локального и удалённого запуска тестов
- [x] Использование `Lombok` для моделей в API тестах
- [x] Использование request/response спецификаций для API тестов
- [x] Custom Allure listener для API requests/responses логов
- [x] Интеграция с `Allure TestOps`
- [x] Возможность запуска тестов напрямую из `Allure TestOps` через интеграцию с `Jenkins`
- [x] Интеграция с `Jira`


<a id="tools"></a>
## Технологии и инструменты

<div align="center">
<a href="https://www.jetbrains.com/idea/"><img alt="InteliJ IDEA" height="50" src="images/logo/IntelliJ_IDEA.png" width="50"/></a>
<a href="https://github.com/"><img alt="GitHub" height="50" src="images/logo/GitHub.png" width="50"/></a>  
<a href="https://www.java.com/"><img alt="Java" height="50" src="images/logo/Java_logo.png" width="50"/></a>
<a href="https://gradle.org/"><img alt="Gradle" height="50" src="images/logo/Gradle.png" width="50"/></a>  
<a href="https://junit.org/junit5/"><img alt="JUnit 5" height="50" src="images/logo/JUnit5.png" width="50"/></a>
<a href="https://selenide.org/"><img alt="Selenide" height="50" src="images/logo/Selenide.png" width="50"/></a>
<a href="https://aerokube.com/selenoid/"><img alt="Selenoid" height="50" src="images/logo/Selenoid.png" width="50"/></a>
<a href="https://rest-assured.io/"><img alt="RestAssured" height="50" src="images/logo/RestAssured.png" width="50"/></a>
<a href="https://www.browserstack.com/"><img alt="Browserstack" height="50" src="images/logo/Browserstack.png" width="50"/></a>
<a href="https://appium.io/"><img alt="Appium" height="50" src="images/logo/Appium.png" width="50"/></a>
<a href="https://developer.android.com/studio"><img alt="Android Studio" height="50" src="images/logo/AndroidStudio.png" width="50"/></a>
<a href="https://www.jenkins.io/"><img alt="Jenkins" height="50" src="images/logo/Jenkins.png" width="50"/></a>
<a href="https://github.com/allure-framework/"><img alt="Allure Report" height="50" src="images/logo/AllureReports.png" width="50"/></a>
<a href="https://qameta.io/"><img alt="Allure TestOps" height="50" src="images/logo/AllureTestOps.svg" width="50"/></a>
<a href="https://www.atlassian.com/software/jira"><img alt="Jira" height="50" src="images/logo/Jira.png" width="50"/></a>  
<a href="https://telegram.org/"><img alt="Telegram" height="50" src="images/logo/Telegram.png" width="50"/></a>
</div>

Автотесты в этом проекте написаны на Java с использованием фреймворка [Selenide](https://selenide.org/).\
<code>Gradle</code> — используется как инструмент автоматизации сборки.\
<code>JUnit5</code> — для выполнения тестов.\
<code>Selenoid</code> — для удаленного запуска браузера в Docker контейнерах.\
<code>REST Assured</code> — для тестирования REST-API сервисов.\
<code>Jenkins</code> — CI/CD для запуска тестов удаленно.\
<code>Browserstack</code> — для запуска мобильных тестов удаленно.\
<code>Appium</code>, <code>Android Studio</code> — для запуска мобильных тестов локально на эмуляторе мобильных устройств.\
<code>Allure Report</code> — для визуализации результатов тестирования.\
<code>Allure TestOps</code> — как система управления тестированием.\
<code>Jira</code> — как инструмент управления проектом и таск-трекер.\
<code>Telegram Bot</code> — для уведомлений о результатах тестирования.

Allure-отчет включает в себя:
* шаги выполнения тестов;
* скриншот страницы в браузере в момент окончания автотеста;
* Page Source;
* логи браузерной консоли;
* видео выполнения автотеста.




<a id="console"></a>
##  Запуск тестов из терминала
### Локальный запуск тестов
#### WEB

```
./gradlew clean ui_tests Denv=local{ENV}

Для запуска браузерных тестов требуется дополнительно определить переменную `env`, с помощью которой
можно переключаться между локальным и удалённым запуском тестов с параметрами по умолчанию. (_`-Denv=remote`
для удалённого запуска тестов на Selenoid, `-Denv=local` - для локального запуска_).
```
#### API

```
./gradlew  clean api_tests
```
#### MOBILE

```
./gradlew  clean mobile_tests -DenvMobile=${ENV_MOBILE}
```

Для запуска мобильных тестов требуется дополнительно определить переменную `env_mobile`, с помощью которой
можно переключаться между локальным и удалённым запуском тестов с параметрами по умолчанию. (_`-DenvMobile=android_remote`
для удалённого запуска тестов, `-DenvMobile=android_local` - для локального запуска с помощью эмулятора_).
...

<a id="jenkins"></a>
## Запуск тестов в <a target="_blank" href="https://jenkins.autotests.cloud/job/o.evstigneev_chitai-gorod/"> Jenkins </a>

> Сборка с параметрами позволяет перед запуском изменить параметры для сборки (путем выбора из списка или прямым указанием значения).

<p align="center">
<img src="images/screenshots/jenkinsjob.png"/>
</p>

<a id="allure"></a>
## Отчеты в <a target="_blank" href="https://jenkins.autotests.cloud/job/o.evstigneev_chitai-gorod/47/allure/"> Allure report </a>

### Основное окно

<p align="center">
<img src="images/screenshots/allureReport.png">
</p>

### Тесты

<p align="center">
<img src="images/screenshots/allureTest.png">
</p>

### Графики

<p align="center">
<img src="images/screenshots/allureGraphics.png">
</p>

<a id="testops"></a>
## Интеграция с <a target="_blank" href="https://allure.autotests.cloud/project/3660/dashboards"> Allure TestOps </a>

### Доска
<p align="center">
<img src="images/screenshots/testOpsDash.png">
</p>

### Тест-кейсы
<p align="center">
<img src="images/screenshots/testOpsTestCase.png">
</p>


### Запуски сборок
<p align="center">
<img src="images/screenshots/testOpsLaunches.png">
</p>


<a id="jira"></a>
## Интеграция с <a target="_blank" href="https://jira.autotests.cloud/browse/HOMEWORK-638"> Jira </a>
<p align="center">
<img src="images/screenshots/jiraIssue.png">
</p>

<a id="telegram"></a>
## Уведомления в Telegram с использованием бота

<p>
<img src="images/screenshots/telegram.png">
</p>

<a id="video"></a>
## Пример прогона теста в Selenoid

> К каждому web-тесту в отчете прилагается видео
<p align="center">
  <img src="images/videos/selenoidVideo.gif">
</p>

> В случае запуска мобильного теста с помощью Browserstack к отчету также будет прилагаться видео
<p align="center">
  <img src="images/videos/browserstackVideo.gif">
</p>