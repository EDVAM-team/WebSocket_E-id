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

import kz.osmium.oqu.request.OquDELETE;
import kz.osmium.oqu.request.OquGET;
import kz.osmium.oqu.request.OquPOST;
import kz.osmium.oqu.request.OquPUT;
import kz.osmium.translit.request.TranslitDELETE;
import kz.osmium.translit.request.TranslitGET;
import kz.osmium.translit.request.TranslitPOST;
import kz.osmium.translit.request.TranslitPUT;

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
         * Получить рейтинг студента.
         *
         * https://*.example.com/api/total ?
         * & id_account = <Integer>
         */
        path("/api", () ->
                get("/total", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainOqu(request.host()))
                                return OquGET.getTotal(connection, request, response);
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
                                return OquGET.getScheduleStudent(connection, request, response);
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
//        path("/api", () ->
//                path("/schedule", () ->
//                        get("/teacher", (request, response) -> {
//                                    if (HerokuDomain.getDomainOqu(request.host()))
//                                        return OquGET.getScheduleTeacher(connection, request, response);
//                                    else {
//
//                                        response.status(404);
//
//                                        return "404 Not Found";
//                                    }
//                                }
//                        )));

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

        /*
         * Транслитезация казахского текста.
         *
         * https://*.example.com/api/text ?
         * & text = <String>
         * & t = <Integer>
         */
        path("/api", () ->
                get("/text", "application/json", (request, response) -> {
                    if (HerokuDomain.getDomainTranslit(request.host()))
                        return TranslitGET.getTranslit(request, response);
                    else {

                        response.status(404);

                        return "404 Not Found";
                    }
                }));

        /*
         * Вывод исключающих слов казахского языка в транслитизации.
         *
         * https://*.example.com/api/word
         */
        path("/api", () ->
                get("/word", "application/json", (request, response) -> {
                    if (HerokuDomain.getDomainTranslit(request.host()))
                        return TranslitGET.getWord(response);
                    else {

                        response.status(404);

                        return "404 Not Found";
                    }
                }));
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
         *
         * JSON <JSON, raw, application/json>
         * [{
         * "id_account":<Integer>,   - Студент
         * "n":<Integer>,            - Пропуск пары
         * "mark":<Integer>          - Оценка (Не обязательно)
         * }]
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
         * Создает предмет.
         *
         * https://*.example.com/api/subject/item ?
         * & key = <String>
         * & name = <String>
         */
        path("/api", () ->
                path("/subject", () ->
                        post("/item", (request, response) -> {
                                    if (HerokuDomain.getDomainOqu(request.host()))
                                        return OquPOST.postSubjectItem(connection, request, response);
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
         * & key = <String>
         * & id_list_subject = <Integer>
         * & t = <Integer>
         * & id_account = <Integer>
         * - & id_room = <String>
         */
//        path("/api", () ->
//                post("/change", (request, response) -> {
//                            if (HerokuDomain.getDomainOqu(request.host()))
//                                return OquPOST.postChange(connection, request,response);
//                            else {
//
//                                response.status(404);
//
//                                return "404 Not Found";
//                            }
//                        }
//                ));

        /*
         * Создает исключающие слово казахского слово на латинском.
         *
         * https://*.example.com/api/word
         * & key = <String>
         * & cyrl = <String>
         * & latn = <String>
         */
        path("/api", () ->
                post("/word", (request, response) -> {
                            if (HerokuDomain.getDomainTranslit(request.host()))
                                return TranslitPOST.postWord(request, response);
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
         * Изменить аккаунт.
         *
         * https://*.example.com/api/account ?
         * & key = <String>
         * & id_account = <Integer>
         * [-] & name = <String>
         * [-] & login = <String>
         * [-] & pass = <String>
         * - & t = <Integer>
         * - & id_group = <Integer>
         * - & s_name = <String>
         * - & l_name = <String>
         * - & phone = <String>
         * - & email = <String>
         * - & id_room = <Integer>
         */
        path("/api", () ->
                put("/account", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainOqu(request.host()))
                                return OquPUT.putAccount(connection, request, response);
                            else {

                                response.status(404);

                                return "404 Not Found";
                            }
                        }
                ));

        /*
         * Изменить замену.
         *
         * https://*.example.com/api/change ?
         * & key = <String>
         * & id_change = <Integer>
         * [-] & id_list_subject = <Integer>
         * [-] & t = <Integer>
         * [-] & id_account = <Integer>
         * - & id_room = <Integer>
         */
        path("/api", () ->
                put("/change", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainOqu(request.host()))
                                return OquPUT.putChange(connection, request, response);
                            else {

                                response.status(404);

                                return "404 Not Found";
                            }
                        }
                ));

        /*
         * Изменить куратора.
         *
         * https://*.example.com/api/curator ?
         * & key = <String>
         * & id_group = <Integer>
         * [-] & id_account = <Integer>
         */
        path("/api", () ->
                put("/curator", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainOqu(request.host()))
                                return OquPUT.putCurator(connection, request, response);
                            else {

                                response.status(404);

                                return "404 Not Found";
                            }
                        }
                ));

        /*
         * Изменить факультет.
         *
         * https://*.example.com/api/faculty ?
         * & key = <String>
         * & id_faculty = <Integer>
         * [-] & name = <String>
         */
        path("/api", () ->
                put("/faculty", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainOqu(request.host()))
                                return OquPUT.putFaculty(connection, request, response);
                            else {

                                response.status(404);

                                return "404 Not Found";
                            }
                        }
                ));

        /*
         * Изменить группу.
         *
         * https://*.example.com/api/group ?
         * & key = <String>
         * & id_group = <Integer>
         * [-] & name = <String>
         * [-] & id_specialty = <Integer>
         */
        path("/api", () ->
                put("/group", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainOqu(request.host()))
                                return OquPUT.putGroup(connection, request, response);
                            else {

                                response.status(404);

                                return "404 Not Found";
                            }
                        }
                ));

        /*
         * Изменить предмет.
         *
         * https://*.example.com/api/subject/list ?
         * & key = <String>
         * & id_list_subject = <Integer>
         * [-] & name = <String>
         */
        path("/api", () ->
                path("/subject", () ->
                        put("/list", "application/json", (request, response) -> {
                                    if (HerokuDomain.getDomainOqu(request.host()))
                                        return OquPUT.putListSubject(connection, request, response);
                                    else {

                                        response.status(404);

                                        return "404 Not Found";
                                    }
                                }
                        )));

        /*
         * Изменить оценку.
         *
         * https://*.example.com/api/mark ?
         * & key = <String>
         * & id_mark = <Integer>
         * [-] & id_rating = <Integer>
         * [-] & n = <Integer>
         * - & mark = <Integer>
         */
        path("/api", () ->
                put("/mark", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainOqu(request.host()))
                                return OquPUT.putMark(connection, request, response);
                            else {

                                response.status(404);

                                return "404 Not Found";
                            }
                        }
                ));

        /*
         * Изменить рейтинг.
         *
         * https://*.example.com/api/rating ?
         * & key = <String>
         * & id_rating = <Integer>
         * [-] & id_list_subject = <Integer>
         * [-] & id_account = <Integer>
         * [-] & num = <Integer>
         */
        path("/api", () ->
                put("/rating", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainOqu(request.host()))
                                return OquPUT.putRating(connection, request, response);
                            else {

                                response.status(404);

                                return "404 Not Found";
                            }
                        }
                ));

        /*
         * Изменить аудиторию.
         *
         * https://*.example.com/api/room ?
         * & key = <String>
         * & id_room = <Integer>
         * [-] & name = <String>
         */
        path("/api", () ->
                put("/room", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainOqu(request.host()))
                                return OquPUT.putRoom(connection, request, response);
                            else {

                                response.status(404);

                                return "404 Not Found";
                            }
                        }
                ));

        /*
         * Изменить расписание.
         *
         * https://*.example.com/api/schedule ?
         * & key = <String>
         * & id_schedule = <Integer>
         * [-] & d = <Integer>
         * [-] & num = <Integer>
         * [-] & id_schedule_subject = <Integer>
         * [-] & id_group = <Integer>
         * [-] & id_account = <Integer>
         */
        path("/api", () ->
                put("/schedule", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainOqu(request.host()))
                                return OquPUT.putSchedule(connection, request, response);
                            else {

                                response.status(404);

                                return "404 Not Found";
                            }
                        }
                ));

        /*
         * Изменить пару.
         *
         * https://*.example.com/api/subject/schedule ?
         * & key = <String>
         * & id_schedule_subject = <Integer>
         * [-] & id_list_subject = <Integer>
         * - & t = <Integer>
         * - & id_room = <Integer>
         * - & id_change = <Integer>
         */
        path("/api", () ->
                path("/subject", () ->
                        put("/schedule", "application/json", (request, response) -> {
                                    if (HerokuDomain.getDomainOqu(request.host()))
                                        return OquPUT.putScheduleSubject(connection, request, response);
                                    else {

                                        response.status(404);

                                        return "404 Not Found";
                                    }
                                }
                        )));

        /*
         * Изменить специальность.
         *
         * https://*.example.com/api/specialty ?
         * & key = <String>
         * & id_specialty = <Integer>
         * [-] & name = <String>
         * [-] & id_faculty = <Integer>
         */
        path("/api", () ->
                put("/specialty", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainOqu(request.host()))
                                return OquPUT.putSpecialty(connection, request, response);
                            else {

                                response.status(404);

                                return "404 Not Found";
                            }
                        }
                ));

        /*
         * Изменить оценку семестра.
         *
         * https://*.example.com/api/total ?
         * & key = <String>
         * & id_total = <Integer>
         * [-] & id_list_subject = <Integer>
         * [-] & id_account = <Integer>
         * [-] & course = <Integer>
         */
        path("/api", () ->
                put("/total", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainOqu(request.host()))
                                return OquPUT.putTotal(connection, request, response);
                            else {

                                response.status(404);

                                return "404 Not Found";
                            }
                        }
                ));

        /*
         * Изменяет слово в базе транслитизации
         *
         * https://*.example.com/api/word ?
         * & key = <String>
         * & cyrl = <String>
         * & latn = <String>
         */
        path("/api", () ->
                put("/word", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainTranslit(request.host()))
                                return TranslitPUT.putWord(request, response);
                            else {

                                response.status(404);

                                return "404 Not Found";
                            }
                        }
                ));
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

        /*
         * Удаляет слово с базы транслитизации.
         *
         * https://*.example.com/api/word ?
         * & key = <String>
         * & cyrl = <String>
         */
        path("/api", () ->
                delete("/word", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainTranslit(request.host()))
                                return TranslitDELETE.deleteWord(request, response);
                            else {

                                response.status(404);

                                return "404 Not Found";
                            }
                        }
                ));
    }
}
