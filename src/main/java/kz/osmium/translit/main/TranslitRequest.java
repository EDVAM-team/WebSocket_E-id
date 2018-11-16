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

package kz.osmium.translit.main;

import kz.osmium.main.util.HerokuDomain;
import kz.osmium.translit.request.TranslitDELETE;
import kz.osmium.translit.request.TranslitGET;
import kz.osmium.translit.request.TranslitPOST;
import kz.osmium.translit.request.TranslitPUT;

import java.sql.Connection;
import java.util.HashMap;

import static spark.Spark.*;
import static spark.Spark.post;

public class TranslitRequest {


    /**
     * Делается связь с API Translit
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
                        return TranslitGET.getWord(connection, response);
                    else {

                        response.status(404);

                        return "404 Not Found";
                    }
                }));

        /*
         * Вывод символа казахского языка в транслитизации.
         *
         * https://*.example.com/api/symbol
         */
        path("/api", () ->
                get("/symbol", "application/json", (request, response) -> {
                    if (HerokuDomain.getDomainTranslit(request.host()))
                        return TranslitGET.getSymbol(connection, response);
                    else {

                        response.status(404);

                        return "404 Not Found";
                    }
                }));
    }

    /**
     * POST запросы.
     */
    private static void postAPI(HashMap<String, Connection> connection) {

        /*
         * Создает исключающие слово казахского слово на латинице.
         *
         * https://*.example.com/api/word
         * & token = <String>
         * & cyrl = <String>
         * & latn = <String>
         */
        path("/api", () ->
                post("/word", (request, response) -> {
                            if (HerokuDomain.getDomainTranslit(request.host()))
                                return TranslitPOST.postWord(connection, request, response);
                            else {

                                response.status(404);

                                return "404 Not Found";
                            }
                        }
                ));

        /*
         * Создает казахский символ на латинице.
         *
         * https://*.example.com/api/symbol
         * & token = <String>
         * & cyrl = <String>
         * & latn = <String>
         */
        path("/api", () ->
                post("/symbol", (request, response) -> {
                            if (HerokuDomain.getDomainTranslit(request.host()))
                                return TranslitPOST.postSymbol(connection, request, response);
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
         * Изменяет слово в базе транслитизации
         *
         * https://*.example.com/api/word ?
         * & token = <String>
         * & id_word = <Integer>
         * [-] & cyrl = <String>
         * [-] & latn = <String>
         */
        path("/api", () ->
                put("/word", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainTranslit(request.host()))
                                return TranslitPUT.putWord(connection, request, response);
                            else {

                                response.status(404);

                                return "404 Not Found";
                            }
                        }
                ));

        /*
         * Изменяет символ в базе данных
         *
         * https://*.example.com/api/symbol ?
         * & token = <String>
         * & id_symbol = <Integer>
         * [-] & cyrl = <String>
         * [-] & latn = <String>
         */
        path("/api", () ->
                put("/symbol", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainTranslit(request.host()))
                                return TranslitPUT.putSymbol(connection, request, response);
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
         * Удаляет слово с базы транслитизации.
         *
         * https://*.example.com/api/word ?
         * & token = <String>
         * & cyrl = <String>
         */
        path("/api", () ->
                delete("/word", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainTranslit(request.host()))
                                return TranslitDELETE.deleteWord(connection, request, response);
                            else {

                                response.status(404);

                                return "404 Not Found";
                            }
                        }
                ));

        /*
         * Удаляет символ с базы данных.
         *
         * https://*.example.com/api/symbol ?
         * & token = <String>
         * & cyrl = <String>
         */
        path("/api", () ->
                delete("/symbol", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainTranslit(request.host()))
                                return TranslitDELETE.deleteSymbol(connection, request, response);
                            else {

                                response.status(404);

                                return "404 Not Found";
                            }
                        }
                ));
    }
}
