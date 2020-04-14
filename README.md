Тестовое задание:
Сделать серверное приложение на Java, использовать API (JSON, REST)

Framework: Spring Boot
База данных: MySQL
Клиентская часть: SwaggerUI

Общая история: Например, клиент и автомобиль. Клиент берет авто напрокат.

1) Модель для справочника. 
 У сущности автомобиля параметры - марка, год выпуска, владелец.
 У клиента - имя, год рождения, авто.
 
2)Развернуть приложение (Spring Boot), которое обрабатывает 2 запроса: 
добавление клиента в справочник, удаление клиента из справочника
 а) Добавление клиента в справочник: на вход подаются параметры имя клиента, год его рождения, марка авто, год выпуска авто.
 Проверяется что такого пользователя нет в базе, заносится новая запись о клиенте и обновляется запись забранного им автомобиля.
 б) Удаление клиента из справочника: на вход подаются параметры: имя клиента, марка авто,
 проверяется что данный клиент в базе присутствует, что указанное авто на данный момент занято и принадлежит ему,
 обновляется запись об автомобиле и удаляется запись о текущем клиенте.
 
3) Покрыть сервисы проверок unit тестами, общий функционал запросов - интеграционными тестами.

4) Подключить SwaggerUI к приложению.
