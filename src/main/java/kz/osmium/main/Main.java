/*
 * Copyright 2018 Osmium
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package kz.osmium.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static spark.Spark.*;

public class Main {

    /* Подключается к базе данных. */
    private static Connection connection;

    /**
     * Запускает WebSocket
     * Port и запросы настроенны именно в этом методе.
     */
    public static void main(String[] args) {

        /* Изменяет port. */
        port(getHerokuAssignedPort());

        /* Конфигурация WebSocket */
        config();

        /* Подключение к БД. */
        connectDB();

        /* Разрешаю лок. серверу доступ к API */
        preferences();

        /* 'src/main/resources/public' */
//        staticFiles.location("/public");

        /* GET запросы */
        Request.getAPI(connection);

        /* POST запросы */
        Request.postAPI(connection);

        /* PUT запросы */
        Request.putAPI(connection);

        /* DELETE запросы */
        Request.deleteAPI(connection);
    }

    /**
     * Подключение к БД.
     */
    private static void connectDB() {

        try {
            connection = DriverManager.getConnection(HerokuAPI.url, HerokuAPI.login, HerokuAPI.password);
        } catch (SQLException e) {

            System.out.println("Error SQL Connecting");
        }
    }

    /**
     * Конфигурация WebSocket
     */
    private static void config() {

        after("/api/*", (req, res) -> res.type("application/json"));
    }

    /**
     * Настройка сервера "Access-Control-Allow-Origin"
     */
    private static void preferences() {

        options("/*",
                (request, response) -> {

                    String accessControlRequestHeaders = request
                            .headers("Access-Control-Request-Headers");
                    if (accessControlRequestHeaders != null) {
                        response.header("Access-Control-Allow-Headers",
                                accessControlRequestHeaders);
                    }

                    String accessControlRequestMethod = request
                            .headers("Access-Control-Request-Method");
                    if (accessControlRequestMethod != null) {
                        response.header("Access-Control-Allow-Methods",
                                accessControlRequestMethod);
                    }

                    return "OK";
                });

        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));
    }

    /**
     * Настройка port'а
     * С heroku.com приходит ответ зарег. порта для WebSocket'а
     *
     * @return если сервер запущен на стороне heroku.com, то будет использован port от heroku.com.
     */
    private static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();

        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }

        return 4567;
    }
}

