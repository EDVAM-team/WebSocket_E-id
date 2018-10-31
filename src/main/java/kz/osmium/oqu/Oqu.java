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

package kz.osmium.oqu;

import kz.osmium.oqu.jdbc.JDBCDELETE;
import kz.osmium.oqu.jdbc.JDBCGET;
import kz.osmium.oqu.jdbc.JDBCPOST;
import kz.osmium.oqu.utils.HerokuAPI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static spark.Spark.*;

public class Oqu {

    /**
     * GET запросы.
     */
    public static void getAPI(Connection connection) {

        /*
         * Получить ключ.
         *
         * https://example.com/api/auth ?
         * & pass = <String>
         */
        path("/api", () ->
                get("/auth", "application/json", JDBCGET::getAuth));

        /*
         * Авторизовать приложение.
         *
         * https://example.com/api/account ?
         * & login = <String>
         * & pass = <String>
         */
        path("/api", () ->
                get("/account", "application/json", (request, response) ->
                        JDBCGET.getAccount(connection, request, response)));

        /*
         * Получить факультеты.
         *
         * https://example.com/api/faculty
         */
        path("/api", () ->
                get("/faculty", "application/json", (request, response) ->
                        JDBCGET.getFaculty(connection, response)));

        /*
         * Получить специальности.
         *
         * https://example.com/api/specialty ?
         * & faculty = <Integer>
         */
        path("/api", () ->
                get("/specialty", "application/json", (request, response) ->
                        JDBCGET.getSpecialty(connection, request, response)));

        /*
         * Получить группы.
         *
         * https://example.com/api/group ?
         * & specialty = <Integer>
         */
        path("/api", () ->
                get("/group", "application/json", (request, response) ->
                        JDBCGET.getGroup(connection, request, response)));

        /*
         * Получить куратора для группы.
         *
         * https://example.com/api/curator/group ?
         * & group = <Integer>
         */
        path("/api", () ->
                path("/curator", () ->
                        get("/group", "application/json", (request, response) ->
                JDBCGET.getCuratorGroup(connection, request, response))));

        /*
         * Получить группы для куратора.
         *
         * https://example.com/api/curator/teacher ?
         * & teacher = <Integer>
         */
        path("/api", () ->
                path("/curator", () ->
                        get("/teacher", "application/json", (request, response) ->
                JDBCGET.getCuratorTeacher(connection, request, response))));

        /*
         * Получить кабинеты.
         *
         * https://example.com/api/room
         */
        path("/api", () ->
                get("/room", "application/json", (request, response) ->
                        JDBCGET.getRoom(connection, response)));

        /*
         * Получить расписание.
         *
         * https://example.com/api/schedule ?
         * & group = <Integer>
         */
        path("/api", () ->
                get("/schedule", (request, response) ->
                        JDBCGET.getSchedule(connection, request, response)));

        /*
         * Получить расписание для преподователя.
         *
         * https://example.com/api/schedule/teacher ?
         * & teacher = <Integer>
         */
        path("/api", () ->
                path("/schedule", () ->
                        get("/teacher", (request, response) ->
                                JDBCGET.getScheduleTeacher(connection, request, response))));

        /*
         * Получить информацию аккаунта.
         *
         * https://example.com/api/account/id ?
         * & id_account = <Integer>
         */
        path("/api", () ->
                path("/account", () ->
                        get("/id", "application/json", (request, response) ->
                JDBCGET.getAccountID(connection, request, response))));

        /*
         * Получить список преподавателей.
         *
         * https://example.com/api/teacher/all
         */
        path("/api", () ->
                path("/teacher", () ->
                        get("/all", "application/json", (request, response) ->
                JDBCGET.getTeacherAll(connection, response))));

        /*
         * Получить список предметов.
         *
         * https://example.com/api/subject/list
         */
        path("/api", () ->
                path("/subject", () ->
                        get("/list", "application/json", (request, response) ->
                                JDBCGET.getListSubject(connection, response))));
    }

    /**
     * POST запросы.
     */
    public static void postAPI(Connection connection) {

        /*
         * Создает факультет.
         *
         * https://example.com/api/faculty ?
         * & key = <String>
         * & name = <String>
         */
        path("/api", () ->
                post("/faculty", "application/json", (request, response) ->
                        JDBCPOST.postFaculty(connection, request, response)));

        /*
         * Создает специальность.
         *
         * https://example.com/api/specialty ?
         * & key = <String>
         * & name = <String>
         * & id_faculty = <Integer>
         */
        path("/api", () ->
                post("/specialty", "application/json", (request, response) ->
                        JDBCPOST.postSpecialty(connection, request, response)));

        /*
         * Создает группу.
         *
         * https://example.com/api/group ?
         * & key = <String>
         * & name = <String>
         * & id_specialty = <Integer>
         */
        path("/api", () ->
                post("/group", "application/json", (request, response) ->
                        JDBCPOST.postGroup(connection, request, response)));

        /*
         * Создает кабинет.
         *
         * https://example.com/api/room ?
         * & key = <String>
         * & name = <String>
         */
        path("/api", () ->
                post("/room", "application/json", (request, response) ->
                        JDBCPOST.postRoom(connection, request, response)));

        /*
         * Создает куратора.
         *
         * https://example.com/api/curator ?
         * & key = <String>
         * & group = <Integer>
         * & teacher = <Integer>
         */
        path("/api", () ->
                post("/curator", "application/json", (request, response) ->
                        JDBCPOST.postCurator(connection, request, response)));

        /*
         * Создает преподавателя.
         *
         * https://example.com/api/teacher ?
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
        path("/api", () ->
                post("/teacher", "application/json", (request, response) ->
                        JDBCPOST.postTeacher(connection, request, response)));

        /*
         * Создает студента.
         *
         * https://example.com/api/student ?
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
        path("/api", () ->
                post("/student", "application/json", (request, response) ->
                        JDBCPOST.postStudent(connection, request, response)));

        /*
         * Создает предмет для расписания.
         *
         * https://example.com/api/subject
         */
        path("/api", () ->
                post("/subject", (request, response) ->
                        JDBCPOST.postSubject(connection, request)));

        /*
         * Создает предмет.
         *
         * https://example.com/api/subject/item
         */
        path("/api", () ->
                path("/subject", () ->
                        post("/item", (request, response) ->
                                JDBCPOST.postSubjectItem(connection, request))));

        /*
         * Создает расписание для группы.
         *
         * https://example.com/api/schedule
         */
        path("/api", () ->
                post("/schedule", (request, response) ->
                        JDBCPOST.postSchedule(connection, request)));

        /*
         * Создает замену для конкретного предмета в расписании группы.
         *
         * https://example.com/api/change
         */
        path("/api", () ->
                post("/change", (request, response) ->
                        JDBCPOST.postChange(connection, request)));
    }

    /**
     * PUT запросы.
     */
    public static void putAPI(Connection connection) {

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
    public static void deleteAPI(Connection connection) {

        /*
         * Удаляет куратора.
         *
         * https://example.com/api/curator ?
         * & key = <String>
         * & group = <Integer>
         */
        path("/api", () ->
                delete("/curator", "application/json", (request, response) ->
                        JDBCDELETE.deleteCurator(connection, request, response)));
    }
}
