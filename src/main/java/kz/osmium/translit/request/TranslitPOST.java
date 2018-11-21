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

package kz.osmium.translit.request;

import com.google.gson.Gson;
import kz.osmium.account.main.util.TokenCheck;
import kz.osmium.main.util.HerokuAPI;
import kz.osmium.main.util.StatusResponse;
import kz.osmium.translit.objects.gson.Symbol;
import kz.osmium.translit.statement.GETStatement;
import kz.osmium.translit.statement.POSTStatement;
import spark.Request;
import spark.Response;

import java.sql.*;

public class TranslitPOST {

    /**
     * Создает исключающие слово в таблице `word`
     *
     * @param request
     * @param response
     * @return
     */
    public static String postWord(Request request, Response response) {

        if (TokenCheck.checkTeacher(request.queryParams("token"))) {

            if (request.queryParams("cyrl") != null &&
                    request.queryParams("latn") != null) {

                try (Connection connection = HerokuAPI.Translit.getDB()) {
                    PreparedStatement preparedStatement = connection.prepareStatement(GETStatement.getWord());

                    preparedStatement.setString(1, request.queryParams("cyrl"));
                    preparedStatement.setString(2, request.queryParams("latn"));

                    if (preparedStatement.executeQuery().next()) {

                        response.status(409);

                        return StatusResponse.CONFLICT;
                    }

                    preparedStatement = connection.prepareStatement(POSTStatement.postWord(), Statement.RETURN_GENERATED_KEYS);

                    preparedStatement.setString(1, request.queryParams("cyrl"));
                    preparedStatement.setString(2, request.queryParams("latn"));

                    if (preparedStatement.executeUpdate() == 0) {

                        response.status(500);

                        return StatusResponse.INTERNAL_SERVER_ERROR;
                    }

                    try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {

                            response.status(201);

                            return new Gson().toJson(
                                    new Symbol(
                                            Math.toIntExact(generatedKeys.getLong(1)),
                                            request.queryParams("cyrl"),
                                            request.queryParams("latn")
                                    )
                            );
                        } else {

                            response.status(500);

                            return StatusResponse.INTERNAL_SERVER_ERROR;
                        }
                    }
                } catch (SQLException | NumberFormatException e) {

                    response.status(409);

                    return StatusResponse.CONFLICT;
                }
            } else {

                response.status(204);

                return StatusResponse.NO_CONTENT;
            }
        } else {

            response.status(401);

            return StatusResponse.ERROR;
        }

    }

    /**
     * Создает символ в таблице `symbol`
     *
     * @param request
     * @param response
     * @return
     */
    public static String postSymbol(Request request, Response response) {

        if (TokenCheck.checkTeacher(request.queryParams("token"))) {

            if (request.queryParams("cyrl") != null &&
                    request.queryParams("latn") != null) {

                try (Connection connection = HerokuAPI.Translit.getDB()) {
                    PreparedStatement preparedStatement = connection.prepareStatement(GETStatement.getSymbol());

                    preparedStatement.setString(1, request.queryParams("cyrl"));
                    preparedStatement.setString(2, request.queryParams("latn"));

                    if (preparedStatement.executeQuery().next()) {

                        response.status(409);

                        return StatusResponse.CONFLICT;
                    }

                    preparedStatement = connection
                            .prepareStatement(POSTStatement.postSymbol(), Statement.RETURN_GENERATED_KEYS);

                    preparedStatement.setString(1, request.queryParams("cyrl"));
                    preparedStatement.setString(2, request.queryParams("latn"));

                    if (preparedStatement.executeUpdate() == 0) {

                        response.status(500);

                        return StatusResponse.INTERNAL_SERVER_ERROR;
                    }

                    try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {

                            response.status(201);

                            return new Gson().toJson(
                                    new Symbol(
                                            Math.toIntExact(generatedKeys.getLong(1)),
                                            request.queryParams("cyrl"),
                                            request.queryParams("latn")
                                    )
                            );
                        } else {

                            response.status(500);

                            return StatusResponse.INTERNAL_SERVER_ERROR;
                        }
                    }
                } catch (SQLException | NumberFormatException e) {

                    response.status(409);

                    return StatusResponse.CONFLICT;
                }
            } else {

                response.status(204);

                return StatusResponse.NO_CONTENT;
            }
        } else {

            response.status(401);

            return StatusResponse.ERROR;
        }

    }
}
