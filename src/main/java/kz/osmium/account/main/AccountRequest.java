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

package kz.osmium.account.main;

import kz.osmium.account.request.AccountDELETE;
import kz.osmium.account.request.AccountGET;
import kz.osmium.account.request.AccountPOST;
import kz.osmium.account.request.AccountPUT;
import kz.osmium.main.util.HerokuDomain;
import kz.osmium.main.util.StatusResponse;

import static spark.Spark.*;

public class AccountRequest {

    /**
     * Делается связь с API Account
     */
    public static void connectAPI() {

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
         * Авторизовать приложение.
         *
         * https://*.example.com/api/auth ?
         * & login = <String>
         * & pass = <String>
         */
        path("/api", () ->
                get("/auth", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainAccount(request.host()))
                                return AccountGET.getAccount(request, response);
                            else {

                                response.status(404);

                                return StatusResponse.NOT_FOUND;
                            }
                        }
                ));

        /*
         * Получить информацию аккаунта.
         *
         * https://*.example.com/api/account/id ?
         * & id = <Integer>
         */
        path("/api", () ->
                path("/account", () ->
                        get("/id", "application/json", (request, response) -> {
                                    if (HerokuDomain.getDomainAccount(request.host()))
                                        return AccountGET.getAccountID(request, response);
                                    else {

                                        response.status(404);

                                        return StatusResponse.NOT_FOUND;
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
                                    if (HerokuDomain.getDomainAccount(request.host()))
                                        return AccountGET.getTeacherAll(response);
                                    else {

                                        response.status(404);

                                        return StatusResponse.NOT_FOUND;
                                    }
                                }
                        )));
    }

    /**
     * POST запросы.
     */
    private static void postAPI() {

        /*
         * Создает аккаунт.
         *
         * https://*.example.com/api/account ?
         * & token = <String>
         * & name_f = <String>
         * & type = <Integer>
         * & login = <String>
         * & pass = <String>
         * & name_l = <String>
         * & patronymic = <String>
         * - & phone = <String>
         * - & email = <String>
         * - & group_id = <Integer>
         * - & room_id = <Integer>
         */
        path("/api", () ->
                post("/account", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainAccount(request.host()))
                                return AccountPOST.postAccount(request, response);
                            else {

                                response.status(404);

                                return StatusResponse.NOT_FOUND;
                            }
                        }
                ));
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
         * & id = <Integer>
         * [-] & name_f = <String>
         * [-] & login = <String>
         * [-] & pass = <String>
         * - & type = <Integer>
         * - & group_id = <Integer>
         * - & name_l = <String>
         * - & patronymic = <String>
         * - & phone = <String>
         * - & email = <String>
         * - & room_id = <Integer>
         */
        path("/api", () ->
                put("/account", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainAccount(request.host()))
                                return AccountPUT.putAccount(request, response);
                            else {

                                response.status(404);

                                return StatusResponse.NOT_FOUND;
                            }
                        }
                ));
    }

    /**
     * DELETE запросы.
     */
    private static void deleteAPI() {

        /*
         * Удаляет сессию аккаунта.
         *
         * https://*.example.com/api/auth ?
         * & token = <String>
         * & id = <Integer>
         */
        path("/api", () ->
                delete("/auth", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainAccount(request.host()))
                                return AccountDELETE.deleteAuth(request, response);
                            else {

                                response.status(404);

                                return StatusResponse.NOT_FOUND;
                            }
                        }
                ));

        /*
         * Удаляет сессии аккаунта.
         *
         * https://*.example.com/api/auth/all ?
         * & token = <String>
         * & id = <Integer>
         */
        path("/api", () ->
                path("/auth", () ->
                        delete("/all","application/json", (request, response) -> {
                                    if (HerokuDomain.getDomainAccount(request.host()))
                                        return AccountDELETE.deleteAuthAll(request, response);
                                    else {

                                        response.status(404);

                                        return StatusResponse.NOT_FOUND;
                                    }
                                }
                        )));
    }
}
