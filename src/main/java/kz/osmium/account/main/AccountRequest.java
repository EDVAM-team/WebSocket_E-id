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

import java.sql.Connection;
import java.util.HashMap;

import static spark.Spark.*;

public class AccountRequest {

    /**
     * Делается связь с API Account
     */
    public static void connectAPI(HashMap<String, Connection> connection) {

        getAPI(connection);
        postAPI(connection);
        putAPI(connection);
        deleteAPI(connection);
    }

    /**
     * GET запросы.
     */
    private static void getAPI(HashMap<String, Connection> connection) {

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
                                return AccountGET.getAccount(connection, request, response);
                            else {

                                response.status(404);

                                return "404 Not Found";
                            }
                        }
                ));
    }

    /**
     * POST запросы.
     */
    private static void postAPI(HashMap<String, Connection> connection) {

        /*
         * Создает аккаунт.
         *
         * https://*.example.com/api/account ?
         * & token = <String>
         * & f_name = <String>
         * & type = <Integer>
         * & login = <String>
         * & pass = <String>
         * & l_name = <String>
         * & patronymic = <String>
         * - & phone = <String>
         * - & email = <String>
         * - & id_group = <Integer>
         * - & id_room = <Integer>
         */
        path("/api", () ->
                post("/account", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainAccount(request.host()))
                                return AccountPOST.postAccount(connection, request, response);
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
    private static void putAPI(HashMap<String, Connection> connection) {

        /*
         * Изменить аккаунт.
         *
         * https://*.example.com/api/account ?
         * & token = <String>
         * & id_account = <Integer>
         * [-] & f_name = <String>
         * [-] & login = <String>
         * [-] & pass = <String>
         * - & type = <Integer>
         * - & id_group = <Integer>
         * - & l_name = <String>
         * - & patronymic = <String>
         * - & phone = <String>
         * - & email = <String>
         * - & id_room = <Integer>
         */
        path("/api", () ->
                put("/account", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainAccount(request.host()))
                                return AccountPUT.putAccount(connection, request, response);
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
    private static void deleteAPI(HashMap<String, Connection> connection) {

        /*
         * Удаляет сессию аккаунта.
         *
         * https://*.example.com/api/auth ?
         * & token = <String>
         * & id_account = <Integer>
         */
        path("/api", () ->
                delete("/auth", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainAccount(request.host()))
                                return AccountDELETE.deleteAuth(connection, request, response);
                            else {

                                response.status(404);

                                return "404 Not Found";
                            }
                        }
                ));

        /*
         * Удаляет сессии аккаунта.
         *
         * https://*.example.com/api/auth/all ?
         * & token = <String>
         * & id_account = <Integer>
         */
        path("/api", () ->
                path("/auth", () ->
                        delete("/all","application/json", (request, response) -> {
                                    if (HerokuDomain.getDomainAccount(request.host()))
                                        return AccountDELETE.deleteAuthAll(connection, request, response);
                                    else {

                                        response.status(404);

                                        return "404 Not Found";
                                    }
                                }
                        )));
    }
}
