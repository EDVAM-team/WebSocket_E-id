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
import kz.osmium.main.util.StatusResponse;
import kz.osmium.translit.request.TranslitDELETE;
import kz.osmium.translit.request.TranslitGET;
import kz.osmium.translit.request.TranslitPOST;
import kz.osmium.translit.request.TranslitPUT;

import static spark.Spark.*;
import static spark.Spark.post;

public class TranslitRequest {


    /**
     * Делается связь с API Translit
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
         * Транслитезация казахского текста.
         *
         * https://*.example.com/api/text ?
         * & text = <String>
         */
        path("/api", () ->
                get("/text", "application/json", (request, response) -> {
                    if (HerokuDomain.getDomainTranslit(request.host()))
                        return TranslitGET.getTranslit(request, response);
                    else {

                        response.status(404);

                        return StatusResponse.NOT_FOUND;
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

                        return StatusResponse.NOT_FOUND;
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
                        return TranslitGET.getSymbol(response);
                    else {

                        response.status(404);

                        return StatusResponse.NOT_FOUND;
                    }
                }));
    }

    /**
     * POST запросы.
     */
    private static void postAPI() {

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
                                return TranslitPOST.postWord(request, response);
                            else {

                                response.status(404);

                                return StatusResponse.NOT_FOUND;
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
                                return TranslitPOST.postSymbol(request, response);
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
         * Изменяет слово в базе транслитизации
         *
         * https://*.example.com/api/word ?
         * & token = <String>
         * & id = <Integer>
         * [-] & cyrl = <String>
         * [-] & latn = <String>
         */
        path("/api", () ->
                put("/word", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainTranslit(request.host()))
                                return TranslitPUT.putWord(request, response);
                            else {

                                response.status(404);

                                return StatusResponse.NOT_FOUND;
                            }
                        }
                ));

        /*
         * Изменяет символ в базе данных
         *
         * https://*.example.com/api/symbol ?
         * & token = <String>
         * & id = <Integer>
         * [-] & cyrl = <String>
         * [-] & latn = <String>
         */
        path("/api", () ->
                put("/symbol", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainTranslit(request.host()))
                                return TranslitPUT.putSymbol(request, response);
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
         * Удаляет слово с базы транслитизации.
         *
         * https://*.example.com/api/word ?
         * & token = <String>
         * & id = <Integer>
         */
        path("/api", () ->
                delete("/word", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainTranslit(request.host()))
                                return TranslitDELETE.deleteWord(request, response);
                            else {

                                response.status(404);

                                return StatusResponse.NOT_FOUND;
                            }
                        }
                ));

        /*
         * Удаляет символ с базы данных.
         *
         * https://*.example.com/api/symbol ?
         * & token = <String>
         * & id = <Integer>
         */
        path("/api", () ->
                delete("/symbol", "application/json", (request, response) -> {
                            if (HerokuDomain.getDomainTranslit(request.host()))
                                return TranslitDELETE.deleteSymbol(request, response);
                            else {

                                response.status(404);

                                return StatusResponse.NOT_FOUND;
                            }
                        }
                ));
    }
}
