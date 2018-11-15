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

import kz.osmium.account.request.AccountGET;
import kz.osmium.main.util.HerokuDomain;

import java.sql.Connection;
import java.util.HashMap;

import static spark.Spark.get;
import static spark.Spark.path;

public class AccountRequest {

    /**
     * Делается связь с API Account
     */
    public static void connectAPI(HashMap<String, Connection> connection){

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
         * https://*.example.com/api/account ?
         * & login = <String>
         * & pass = <String>
         */
        path("/api", () ->
                get("/account", "application/json", (request, response) -> {
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

    }

    /**
     * PUT запросы.
     */
    private static void putAPI(HashMap<String, Connection> connection) {

    }

    /**
     * DELETE запросы.
     */
    private static void deleteAPI(HashMap<String, Connection> connection) {

    }
}
