О проекте
Java-проект с реализацией CRUD для пользователей на базе JDBC, JUnit и H2. Проект построен с использованием Maven.
Тесты написаны с использованием JUnit 5 и Mockito.

Требования
Java 17+
Maven 3.8+


Как запустить проект локально

1. Склонируйте ветку home-task5 репозитория https://github.com/AntonFeklichev/java-intensive-102.git

Команда для скачивания только ветки home-task5, без остальных веток:

git clone --branch home-task5 --single-branch https://github.com/AntonFeklichev/java-intensive-102.git


2. Откройте проект в своей IDE или терминале

Перейдите в папку проекта:

cd java-intensive-102


3. Соберите и запустите тесты

mvn clean test

Будет создана in-memory база данных H2 и запущены JUnit-тесты.


4. Собрать проект без запуска тестов

mvn clean package -DskipTests

Сборка создаст артефакты в папке target/.
