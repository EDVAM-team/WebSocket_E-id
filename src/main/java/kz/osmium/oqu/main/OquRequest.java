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

package kz.osmium.oqu.main;

import kz.osmium.main.util.HerokuDomain;
import kz.osmium.oqu.request.OquDELETE;
import kz.osmium.oqu.request.OquGET;
import kz.osmium.oqu.request.OquPOST;
import kz.osmium.oqu.request.OquPUT;

import static spark.Spark.*;
import static spark.Spark.get;

public class OquRequest {

    /**
     * Делается связь с API Oqu
     */
    public static void connectAPI(){

        getAPI();
        postAPI();
        putAPI();
        deleteAPI();
    }

    /**
     * GET запросы.
     */
    private static void getAPI() {

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
                                return OquGET.getRatingStudent(request, response);
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
                                return OquGET.getTotal(request, response);
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
                                return OquGET.getFaculty(response);
                            else {

                                response.status(404);

                                return "404 Not Found";
                            }
                        }
                ));

        /*
         * Получить специальности.
         *
         * https://*.example.com/api/specialty
         */
        path("/api", () ->
                get("/specialty", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainOqu(request.host()))
                                return OquGET.getSpecialty(response);
                            else {

                                response.status(404);

                                return "404 Not Found";
                            }
                        }
                ));

        /*
         * Получить группы.
         *
         * https://*.example.com/api/group
         */
        path("/api", () ->
                get("/group", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainOqu(request.host()))
                                return OquGET.getGroup(response);
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
                                        return OquGET.getCuratorGroup(request, response);
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
                                        return OquGET.getCuratorTeacher(request, response);
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
                                return OquGET.getRoom(response);
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
                                return OquGET.getScheduleStudent(request, response);
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
//                                        return OquGET.getScheduleTeacher(request, response);
//                                    else {
//
//                                        response.status(404);
//
//                                        return "404 Not Found";
//                                    }
//                                }
//                        )));

        /*
         * Получить список предметов.
         *
         * https://*.example.com/api/subject/list
         */
        path("/api", () ->
                path("/subject", () ->
                        get("/list", "application/json", (request, response) -> {
                                    if (HerokuDomain.getDomainOqu(request.host()))
                                        return OquGET.getListSubject(response);
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
    private static void postAPI() {

        /*
         * Создает факультет.
         *
         * https://*.example.com/api/faculty ?
         * & token = <String>
         * & name = <String>
         */
        path("/api", () ->
                post("/faculty", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainOqu(request.host()))
                                return OquPOST.postFaculty(request, response);
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
         * & token = <String>
         * & name = <String>
         * & id_faculty = <Integer>
         */
        path("/api", () ->
                post("/specialty", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainOqu(request.host()))
                                return OquPOST.postSpecialty(request, response);
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
         * & token = <String>
         * & name = <String>
         * & id_specialty = <Integer>
         */
        path("/api", () ->
                post("/group", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainOqu(request.host()))
                                return OquPOST.postGroup(request, response);
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
         * & token = <String>
         * & name = <String>
         */
        path("/api", () ->
                post("/room", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainOqu(request.host()))
                                return OquPOST.postRoom(request, response);
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
         * & token = <String>
         * & id_subject = <Integer>
         * & id_account = <Integer>
         * & num = <Integer>
         */
        path("/api", () ->
                post("/rating", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainOqu(request.host()))
                                return OquPOST.postRating(request, response);
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
         * & token = <String>
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
                                return OquPOST.postMark(request, response);
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
         * & token = <String>
         * & group = <Integer>
         * & teacher = <Integer>
         */
        path("/api", () ->
                post("/curator", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainOqu(request.host()))
                                return OquPOST.postCurator(request, response);
                            else {

                                response.status(404);

                                return "404 Not Found";
                            }
                        }
                ));

        /*
         * Создает аккаунт.
         *
         * https://*.example.com/api/account ?
         * & token = <String>
         * & name = <String>
         * & t = <Integer>
         * & login = <String>
         * & pass = <String>
         * - & s_name = <String>
         * - & l_name = <String>
         * - & phone = <String>
         * - & email = <String>
         * - & id_group = <Integer>
         * - & id_room = <Integer>
         */
        path("/api", () ->
                post("/account", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainOqu(request.host()))
                                return OquPOST.postAccount(request, response);
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
         * & token = <String>
         * & name = <String>
         */
        path("/api", () ->
                path("/subject", () ->
                        post("/item", (request, response) -> {
                                    if (HerokuDomain.getDomainOqu(request.host()))
                                        return OquPOST.postSubjectItem(request, response);
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
                                return OquPOST.postSchedule(request);
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
         * & token = <String>
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
    }

    /**
     * PUT запросы.
     */
    private static void putAPI() {

        /*
         * Изменить аккаунт.
         *
         * https://*.example.com/api/account ?
         * & token = <String>
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
                                return OquPUT.putAccount(request, response);
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
         * & token = <String>
         * & id_change = <Integer>
         * [-] & id_list_subject = <Integer>
         * [-] & t = <Integer>
         * [-] & id_account = <Integer>
         * - & id_room = <Integer>
         */
        path("/api", () ->
                put("/change", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainOqu(request.host()))
                                return OquPUT.putChange(request, response);
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
         * & token = <String>
         * & id_group = <Integer>
         * [-] & id_account = <Integer>
         */
        path("/api", () ->
                put("/curator", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainOqu(request.host()))
                                return OquPUT.putCurator(request, response);
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
         * & token = <String>
         * & id_faculty = <Integer>
         * [-] & name = <String>
         */
        path("/api", () ->
                put("/faculty", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainOqu(request.host()))
                                return OquPUT.putFaculty(request, response);
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
         * & token = <String>
         * & id_group = <Integer>
         * [-] & name = <String>
         * [-] & id_specialty = <Integer>
         */
        path("/api", () ->
                put("/group", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainOqu(request.host()))
                                return OquPUT.putGroup(request, response);
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
         * & token = <String>
         * & id_list_subject = <Integer>
         * [-] & name = <String>
         */
        path("/api", () ->
                path("/subject", () ->
                        put("/list", "application/json", (request, response) -> {
                                    if (HerokuDomain.getDomainOqu(request.host()))
                                        return OquPUT.putListSubject(request, response);
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
         * & token = <String>
         * & id_mark = <Integer>
         * [-] & id_rating = <Integer>
         * [-] & n = <Integer>
         * - & mark = <Integer>
         */
        path("/api", () ->
                put("/mark", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainOqu(request.host()))
                                return OquPUT.putMark(request, response);
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
         * & token = <String>
         * & id_rating = <Integer>
         * [-] & id_list_subject = <Integer>
         * [-] & id_account = <Integer>
         * [-] & num = <Integer>
         */
        path("/api", () ->
                put("/rating", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainOqu(request.host()))
                                return OquPUT.putRating(request, response);
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
         * & token = <String>
         * & id_room = <Integer>
         * [-] & name = <String>
         */
        path("/api", () ->
                put("/room", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainOqu(request.host()))
                                return OquPUT.putRoom(request, response);
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
         * & token = <String>
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
                                return OquPUT.putSchedule(request, response);
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
         * & token = <String>
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
                                        return OquPUT.putScheduleSubject(request, response);
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
         * & token = <String>
         * & id_specialty = <Integer>
         * [-] & name = <String>
         * [-] & id_faculty = <Integer>
         */
        path("/api", () ->
                put("/specialty", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainOqu(request.host()))
                                return OquPUT.putSpecialty(request, response);
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
         * & token = <String>
         * & id_total = <Integer>
         * [-] & id_list_subject = <Integer>
         * [-] & id_account = <Integer>
         * [-] & course = <Integer>
         */
        path("/api", () ->
                put("/total", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainOqu(request.host()))
                                return OquPUT.putTotal(request, response);
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
    private static void deleteAPI() {

        /*
         * Удаляет куратора.
         *
         * https://*.example.com/api/curator ?
         * & token = <String>
         * & group = <Integer>
         */
        path("/api", () ->
                delete("/curator", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainOqu(request.host()))
                                return OquDELETE.deleteCurator(request, response);
                            else {

                                response.status(404);

                                return "404 Not Found";
                            }
                        }
                ));
    }
}
