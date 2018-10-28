/*
 * Copyright 2018 EDVAM
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

package kz.eid;

import kz.eid.jdbc.JDBCDELETE;
import kz.eid.jdbc.JDBCGET;
import kz.eid.jdbc.JDBCPOST;
import kz.eid.utils.HerokuAPI;

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

        /* GET запрос на получение статуса WebSocket'а */
        get("/", (req, res) -> "Status: Online");

        /* GET запросы */
        getAPI();

        /* POST запросы */
        postAPI();

        /* PUT запросы */
        putAPI();

        /* DELETE запросы */
        deleteAPI();
    }

    /**
     * GET запросы.
     */
    private static void getAPI() {

        /*
         * Получить ключ.
         *
         * https://example.com/auth ?
         * & pass = <String>
         */
        get("/auth", "application/json", JDBCGET::getAuth);

        /*
         * Авторизовать приложение.
         *
         * https://example.com/account ?
         * & login = <String>
         * & pass = <String>
         */
        get("/account", "application/json", (request, response) -> JDBCGET.getAccount(connection, request, response));

        /*
         * Получить факультеты.
         *
         * https://example.com/faculty
         */
        get("/faculty", "application/json", (request, response) -> JDBCGET.getFaculty(connection, response));

        /*
         * Получить специальности.
         *
         * https://example.com/specialty ?
         * & faculty = <Integer>
         */
        get("/specialty", "application/json", (request, response) -> JDBCGET.getSpecialty(connection, request, response));

        /*
         * Получить группы.
         *
         * https://example.com/group ?
         * & specialty = <Integer>
         */
        get("/group", "application/json", (request, response) -> JDBCGET.getGroup(connection, request, response));

        /*
         * Получить куратора для группы.
         *
         * https://example.com/curator/group ?
         * & group = <Integer>
         */
        path("/curator", () -> get("/group", "application/json", (request, response) ->
                JDBCGET.getCuratorGroup(connection, request, response)));

        /*
         * Получить группы для куратора.
         *
         * https://example.com/curator/teacher ?
         * & teacher = <Integer>
         */
        path("/curator", () -> get("/teacher", "application/json", (request, response) ->
                JDBCGET.getCuratorTeacher(connection, request, response)));

        /*
         * Получить кабинеты.
         *
         * https://example.com/room
         */
        get("/room", "application/json", (request, response) -> JDBCGET.getRoom(connection, response));

        /*
         * Получить расписание.
         *
         * https://example.com/schedule ?
         * & group = <Integer>
         */
        get("/schedule", (request, response) -> JDBCGET.getSchedule(connection, request, response));

        /*
         * Получить расписание для преподователя.
         *
         * https://example.com/schedule/teacher ?
         * & teacher = <Integer>
         */
        path("/schedule", () -> get("/teacher", (request, response) -> JDBCGET.getScheduleTeacher(connection, request, response)));

        /*
         * Получить преподавателя.
         *
         * https://example.com/teacher ?
         * & id_teacher = <Integer>
         */
        get("/teacher", "application/json", (request, response) -> JDBCGET.getTeacher(connection, request, response));

        /*
         * Получить список преподавателей.
         *
         * https://example.com/teacher/all
         */
        path("/teacher", () -> get("/all", "application/json", (request, response) ->
                JDBCGET.getAll(connection, response)));

        /*
         * Получить список предметов.
         *
         * https://example.com/list
         */
        get("/list", "application/json", (request, response) -> JDBCGET.getList(connection, response));
    }

    /**
     * POST запросы.
     */
    private static void postAPI() {

        /*
         * Создает факультет.
         *
         * https://example.com/faculty ?
         * & key = <String>
         * & name = <String>
         */
        post("/faculty", "application/json", (request, response) -> JDBCPOST.postFaculty(connection, request, response));

        /*
         * Создает специальность.
         *
         * https://example.com/specialty ?
         * & key = <String>
         * & name = <String>
         * & id_faculty = <Integer>
         */
        post("/specialty", "application/json", (request, response) -> JDBCPOST.postSpecialty(connection, request, response));

        /*
         * Создает группу.
         *
         * https://example.com/group ?
         * & key = <String>
         * & name = <String>
         * & id_specialty = <Integer>
         */
        post("/group", "application/json", (request, response) -> JDBCPOST.postGroup(connection, request, response));

        /*
         * Создает кабинет.
         *
         * https://example.com/room ?
         * & key = <String>
         * & name = <String>
         */
        post("/room", "application/json", (request, response) -> JDBCPOST.postRoom(connection, request, response));

        /*
         * Создает куратора.
         *
         * https://example.com/curator ?
         * & key = <String>
         * & group = <Integer>
         * & teacher = <Integer>
         */
        post("/curator", "application/json", (request, response) -> JDBCPOST.postCurator(connection, request, response));

        /*
         * Создает преподавателя.
         *
         * https://example.com/teacher ?
         * & key = <String>
         * & name = <String>
         * & login = <String>
         * & pass = <String>
         * - & s_name = <String>
         * - & l_name = <String>
         * - & phone = <String>
         * - & email = <String>
         * - & id_room = <Integer>
         */
        post("/teacher", "application/json", (request, response) -> JDBCPOST.postTeacher(connection, request, response));

        /*
         * Создает студента.
         *
         * https://example.com/student ?
         * & key = <String>
         * & name = <String>
         * & login = <String>
         * & pass = <String>
         * - & s_name = <String>
         * - & l_name = <String>
         * - & phone = <String>
         * - & email = <String>
         * - & id_group = <Integer>
         */
        post("/student", "application/json", (request, response) -> JDBCPOST.postStudent(connection, request, response));

        /*
         * Создает предмет для расписания.
         *
         * https://example.com/subject
         */
        post("/subject", (request, response) -> JDBCPOST.postSubject(connection, request));

        /*
         * Создает предмет.
         *
         * https://example.com/subject/item
         */
        path("/subject", () -> post("/item", (request, response) -> JDBCPOST.postSubjectItem(connection, request)));

        /*
         * Создает расписание для группы.
         *
         * https://example.com/schedule
         */
        post("/schedule", (request, response) -> JDBCPOST.postSchedule(connection, request));

        /*
         * Создает замену для конкретного предмета в расписании группы.
         *
         * https://example.com/change
         */
        post("/change", (request, response) -> JDBCPOST.postChange(connection, request));
    }

    /**
     * PUT запросы.
     */
    private static void putAPI() {

        /*
         * Вносит изменения в расписании группы.
         *
         * https://example.com/schedule
         */
//        put("/schedule", (request, response) -> JDBCPUT.putSchedule(connection));

        /*
         * Вносит изменения группы.
         *
         * https://example.com/group
         */
//        put("/group", (request, response) -> "return");
    }

    /**
     * DELETE запросы.
     */
    private static void deleteAPI() {

        /*
         * Удаляет куратора.
         *
         * https://example.com/curator ?
         * & key = <String>
         * & group = <Integer>
         */
        delete("/curator", "application/json", (request, response) -> JDBCDELETE.deleteCurator(connection, request, response));
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

        after((req, res) -> res.type("application/json"));
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

