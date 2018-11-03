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

import kz.osmium.oqu.jdbc.OquDELETE;
import kz.osmium.oqu.jdbc.OquGET;
import kz.osmium.oqu.jdbc.OquPOST;

import java.sql.Connection;

import static spark.Spark.*;

public class Request {

    /**
     * GET запросы.
     */
    public static void getAPI(Connection connection) {

        /*
         * Получить ключ.
         *
         * https://*.example.com/api/auth ?
         * & pass = <String>
         */
        path("/api", () ->
                get("/auth", "application/json", OquGET::getAuth));

        /*
         * Авторизовать приложение.
         *
         * https://*.example.com/api/account ?
         * & login = <String>
         * & pass = <String>
         */
        path("/api", () ->
                get("/account", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainOqu(request.host()))
                                return OquGET.getAccount(connection, request, response);
                            else {

                                response.status(404);

                                return "404 Not Found";
                            }
                        }
                ));

        /*
         * Получить рейтинг студента.
         *
         * https://*.example.com/api/rating ?
         * & id_account = <Integer>
         * & num = <Integer>
         */
        path("/api", () ->
                get("/rating", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainOqu(request.host()))
                                return OquGET.getRatingStudent(connection, request, response);
                            else {

                                response.status(404);

                                return "404 Not Found";
                            }
                        }
                ));

        /*
         * Получить факультеты.
         *
         * https://*.example.com/api/faculty
         */
        path("/api", () ->
                get("/faculty", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainOqu(request.host()))
                                return OquGET.getFaculty(connection, response);
                            else {

                                response.status(404);

                                return "404 Not Found";
                            }
                        }
                ));

        /*
         * Получить специальности.
         *
         * https://*.example.com/api/specialty ?
         * & faculty = <Integer>
         */
        path("/api", () ->
                get("/specialty", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainOqu(request.host()))
                                return OquGET.getSpecialty(connection, request, response);
                            else {

                                response.status(404);

                                return "404 Not Found";
                            }
                        }
                ));

        /*
         * Получить группы.
         *
         * https://*.example.com/api/group ?
         * & specialty = <Integer>
         */
        path("/api", () ->
                get("/group", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainOqu(request.host()))
                                return OquGET.getGroup(connection, request, response);
                            else {

                                response.status(404);

                                return "404 Not Found";
                            }
                        }
                ));

        /*
         * Получить куратора для группы.
         *
         * https://*.example.com/api/curator/group ?
         * & group = <Integer>
         */
        path("/api", () ->
                path("/curator", () ->
                        get("/group", "application/json", (request, response) -> {
                                    if (HerokuDomain.getDomainOqu(request.host()))
                                        return OquGET.getCuratorGroup(connection, request, response);
                                    else {

                                        response.status(404);

                                        return "404 Not Found";
                                    }
                                }
                        )));

        /*
         * Получить группы для куратора.
         *
         * https://*.example.com/api/curator/teacher ?
         * & teacher = <Integer>
         */
        path("/api", () ->
                path("/curator", () ->
                        get("/teacher", "application/json", (request, response) -> {
                                    if (HerokuDomain.getDomainOqu(request.host()))
                                        return OquGET.getCuratorTeacher(connection, request, response);
                                    else {

                                        response.status(404);

                                        return "404 Not Found";
                                    }
                                }
                        )));

        /*
         * Получить кабинеты.
         *
         * https://*.example.com/api/room
         */
        path("/api", () ->
                get("/room", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainOqu(request.host()))
                                return OquGET.getRoom(connection, response);
                            else {

                                response.status(404);

                                return "404 Not Found";
                            }
                        }
                ));

        /*
         * Получить расписание.
         *
         * https://*.example.com/api/schedule ?
         * & group = <Integer>
         */
        path("/api", () ->
                get("/schedule", (request, response) -> {
                            if (HerokuDomain.getDomainOqu(request.host()))
                                return OquGET.getSchedule(connection, request, response);
                            else {

                                response.status(404);

                                return "404 Not Found";
                            }
                        }
                ));

        /*
         * Получить расписание для преподователя.
         *
         * https://*.example.com/api/schedule/teacher ?
         * & teacher = <Integer>
         */
        path("/api", () ->
                path("/schedule", () ->
                        get("/teacher", (request, response) -> {
                                    if (HerokuDomain.getDomainOqu(request.host()))
                                        return OquGET.getScheduleTeacher(connection, request, response);
                                    else {

                                        response.status(404);

                                        return "404 Not Found";
                                    }
                                }
                        )));

        /*
         * Получить информацию аккаунта.
         *
         * https://*.example.com/api/account/id ?
         * & id_account = <Integer>
         */
        path("/api", () ->
                path("/account", () ->
                        get("/id", "application/json", (request, response) -> {
                                    if (HerokuDomain.getDomainOqu(request.host()))
                                        return OquGET.getAccountID(connection, request, response);
                                    else {

                                        response.status(404);

                                        return "404 Not Found";
                                    }
                                }
                        )));

        /*
         * Получить список преподавателей.
         *
         * https://*.example.com/api/teacher/all
         */
        path("/api", () ->
                path("/teacher", () ->
                        get("/all", "application/json", (request, response) -> {
                                    if (HerokuDomain.getDomainOqu(request.host()))
                                        return OquGET.getTeacherAll(connection, response);
                                    else {

                                        response.status(404);

                                        return "404 Not Found";
                                    }
                                }
                        )));

        /*
         * Получить список предметов.
         *
         * https://*.example.com/api/subject/list
         */
        path("/api", () ->
                path("/subject", () ->
                        get("/list", "application/json", (request, response) -> {
                                    if (HerokuDomain.getDomainOqu(request.host()))
                                        return OquGET.getListSubject(connection, response);
                                    else {

                                        response.status(404);

                                        return "404 Not Found";
                                    }
                                }
                        )));
    }

    /**
     * POST запросы.
     */
    public static void postAPI(Connection connection) {

        /*
         * Создает факультет.
         *
         * https://*.example.com/api/faculty ?
         * & key = <String>
         * & name = <String>
         */
        path("/api", () ->
                post("/faculty", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainOqu(request.host()))
                                return OquPOST.postFaculty(connection, request, response);
                            else {

                                response.status(404);

                                return "404 Not Found";
                            }
                        }
                ));

        /*
         * Создает специальность.
         *
         * https://*.example.com/api/specialty ?
         * & key = <String>
         * & name = <String>
         * & id_faculty = <Integer>
         */
        path("/api", () ->
                post("/specialty", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainOqu(request.host()))
                                return OquPOST.postSpecialty(connection, request, response);
                            else {

                                response.status(404);

                                return "404 Not Found";
                            }
                        }
                ));

        /*
         * Создает группу.
         *
         * https://*.example.com/api/group ?
         * & key = <String>
         * & name = <String>
         * & id_specialty = <Integer>
         */
        path("/api", () ->
                post("/group", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainOqu(request.host()))
                                return OquPOST.postGroup(connection, request, response);
                            else {

                                response.status(404);

                                return "404 Not Found";
                            }
                        }
                ));

        /*
         * Создает кабинет.
         *
         * https://*.example.com/api/room ?
         * & key = <String>
         * & name = <String>
         */
        path("/api", () ->
                post("/room", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainOqu(request.host()))
                                return OquPOST.postRoom(connection, request, response);
                            else {

                                response.status(404);

                                return "404 Not Found";
                            }
                        }
                ));

        /*
         * Создает рейтинг студенту.
         *
         * https://*.example.com/api/rating ?
         * & key = <String>
         * & id_subject = <Integer>
         * & id_account = <Integer>
         * & num = <Integer>
         */
        path("/api", () ->
                post("/rating", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainOqu(request.host()))
                                return OquPOST.postRating(connection, request, response);
                            else {

                                response.status(404);

                                return "404 Not Found";
                            }
                        }
                ));

        /*
         * Создает оценки.
         *
         * https://*.example.com/api/mark ?
         * & key = <String>
         * & id_subject = <Integer>
         */
        path("/api", () ->
                post("/mark", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainOqu(request.host()))
                                return OquPOST.postMark(connection, request, response);
                            else {

                                response.status(404);

                                return "404 Not Found";
                            }
                        }
                ));

        /*
         * Создает куратора.
         *
         * https://*.example.com/api/curator ?
         * & key = <String>
         * & group = <Integer>
         * & teacher = <Integer>
         */
        path("/api", () ->
                post("/curator", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainOqu(request.host()))
                                return OquPOST.postCurator(connection, request, response);
                            else {

                                response.status(404);

                                return "404 Not Found";
                            }
                        }
                ));

        /*
         * Создает преподавателя.
         *
         * https://*.example.com/api/teacher ?
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
                post("/teacher", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainOqu(request.host()))
                                return OquPOST.postTeacher(connection, request, response);
                            else {

                                response.status(404);

                                return "404 Not Found";
                            }
                        }
                ));

        /*
         * Создает студента.
         *
         * https://*.example.com/api/student ?
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
                post("/student", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainOqu(request.host()))
                                return OquPOST.postStudent(connection, request, response);
                            else {

                                response.status(404);

                                return "404 Not Found";
                            }
                        }
                ));

        /*
         * Создает предмет для расписания.
         *
         * https://*.example.com/api/subject
         */
        path("/api", () ->
                post("/subject", (request, response) -> {
                            if (HerokuDomain.getDomainOqu(request.host()))
                                return OquPOST.postSubject(connection, request);
                            else {

                                response.status(404);

                                return "404 Not Found";
                            }
                        }
                ));

        /*
         * Создает предмет.
         *
         * https://*.example.com/api/subject/item
         */
        path("/api", () ->
                path("/subject", () ->
                        post("/item", (request, response) -> {
                                    if (HerokuDomain.getDomainOqu(request.host()))
                                        return OquPOST.postSubjectItem(connection, request);
                                    else {

                                        response.status(404);

                                        return "404 Not Found";
                                    }
                                }
                        )));

        /*
         * Создает расписание для группы.
         *
         * https://*.example.com/api/schedule
         */
        path("/api", () ->
                post("/schedule", (request, response) -> {
                            if (HerokuDomain.getDomainOqu(request.host()))
                                return OquPOST.postSchedule(connection, request);
                            else {

                                response.status(404);

                                return "404 Not Found";
                            }
                        }
                ));

        /*
         * Создает замену для конкретного предмета в расписании группы.
         *
         * https://*.example.com/api/change
         */
        path("/api", () ->
                post("/change", (request, response) -> {
                            if (HerokuDomain.getDomainOqu(request.host()))
                                return OquPOST.postChange(connection, request);
                            else {

                                response.status(404);

                                return "404 Not Found";
                            }
                        }
                ));
    }

    /**
     * PUT запросы.
     */
    public static void putAPI(Connection connection) {

        /*
         * Вносит изменения в расписании группы.
         *
         * https://*.example.com/schedule
         */
//        put("/schedule", (request, response) -> OquPUT.putSchedule(connection));

        /*
         * Вносит изменения группы.
         *
         * https://*.example.com/group
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
         * https://*.example.com/api/curator ?
         * & key = <String>
         * & group = <Integer>
         */
        path("/api", () ->
                delete("/curator", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainOqu(request.host()))
                                return OquDELETE.deleteCurator(connection, request, response);
                            else {

                                response.status(404);

                                return "404 Not Found";
                            }
                        }
                ));
    }
}
