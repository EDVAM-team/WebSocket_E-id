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

import kz.osmium.account.main.util.TokenCheck;
import kz.osmium.main.util.HerokuAPI;
import kz.osmium.main.util.StatusResponse;
import kz.osmium.translit.statement.GETStatement;
import kz.osmium.translit.statement.PUTStatement;
import spark.Request;
import spark.Response;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class TranslitPUT {

    /**
     * Изменяет исключающие слово в таблице `word`
     *
     * @param request
     * @param response
     * @return
     */
    public static String putWord(Request request, Response response) {

        if (TokenCheck.checkTeacher( request.queryParams("token"))) {

            if (request.queryParams("id_word") != null &&
                    request.queryParams("cyrl") != null ||
                    request.queryParams("latn") != null) {

                    try (Connection connection = HerokuAPI.Translit.getDB()) {
                        PreparedStatement preparedStatement = connection.prepareStatement(GETStatement.getWord());

                        preparedStatement.setString(1, request.queryParams("cyrl"));
                        preparedStatement.setString(2, request.queryParams("latn"));

                        if (preparedStatement.executeQuery().next()) {

                            response.status(409);

                            return StatusResponse.CONFLICT;
                        }

                        preparedStatement = connection.prepareStatement(PUTStatement.putWord());

                        preparedStatement.setInt(5, Integer.parseInt(request.queryParams("id_word")));

                        if (request.queryParams("cyrl") != null) {
                            preparedStatement.setString(1, request.queryParams("cyrl"));
                            preparedStatement.setString(2, request.queryParams("cyrl"));
                        } else {
                            preparedStatement.setNull(1, Types.VARCHAR);
                            preparedStatement.setNull(2, Types.VARCHAR);
                        }

                        if (request.queryParams("latn") != null) {
                            preparedStatement.setString(3, request.queryParams("latn"));
                            preparedStatement.setString(4, request.queryParams("latn"));
                        } else {
                            preparedStatement.setNull(3, Types.VARCHAR);
                            preparedStatement.setNull(4, Types.VARCHAR);
                        }

                        preparedStatement.executeUpdate();

                        response.status(200);
                    } catch (SQLException | NumberFormatException e) {

                        response.status(409);

                        return StatusResponse.CONFLICT;
                    }

                    return StatusResponse.SUCCESS;
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
     * Изменяет символ в таблице `symbol`
     *
     * @param request
     * @param response
     * @return
     */
    public static String putSymbol(Request request, Response response) {

        if (TokenCheck.checkTeacher( request.queryParams("token"))) {

            if (request.queryParams("id_symbol") != null &&
                    request.queryParams("cyrl") != null ||
                    request.queryParams("latn") != null) {

                    try (Connection connection = HerokuAPI.Translit.getDB()) {
                        PreparedStatement preparedStatement = connection.prepareStatement(GETStatement.getSymbol());

                        preparedStatement.setString(1, request.queryParams("cyrl"));
                        preparedStatement.setString(2, request.queryParams("latn"));

                        if (preparedStatement.executeQuery().next()) {

                            response.status(409);

                            return StatusResponse.CONFLICT;
                        }

                        preparedStatement = connection.prepareStatement(PUTStatement.putSymbol());

                        preparedStatement.setInt(5, Integer.parseInt(request.queryParams("id_symbol")));

                        if (request.queryParams("cyrl") != null) {
                            preparedStatement.setString(1, request.queryParams("cyrl"));
                            preparedStatement.setString(2, request.queryParams("cyrl"));
                        } else {
                            preparedStatement.setNull(1, Types.VARCHAR);
                            preparedStatement.setNull(2, Types.VARCHAR);
                        }

                        if (request.queryParams("latn") != null) {
                            preparedStatement.setString(3, request.queryParams("latn"));
                            preparedStatement.setString(4, request.queryParams("latn"));
                        } else {
                            preparedStatement.setNull(3, Types.VARCHAR);
                            preparedStatement.setNull(4, Types.VARCHAR);
                        }

                        preparedStatement.executeUpdate();

                        response.status(200);
                    } catch (SQLException | NumberFormatException e) {

                        response.status(409);

                        return StatusResponse.CONFLICT;
                    }

                    return StatusResponse.SUCCESS;
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
