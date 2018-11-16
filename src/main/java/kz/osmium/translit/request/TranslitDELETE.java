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
import kz.osmium.translit.statement.DELETEStatement;
import spark.Request;
import spark.Response;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TranslitDELETE {

    /**
     * Удаляет слово в таблице `word`
     *
     * @param request
     * @param response
     * @return
     */
    public static String deleteWord(Request request, Response response) {

        if (TokenCheck.checkTeacher( request.queryParams("token"))) {

            if (request.queryParams("id_word") != null) {

                    try (Connection connection = HerokuAPI.Translit.getDB()) {
                        PreparedStatement preparedStatement = connection.prepareStatement(DELETEStatement.deleteWord());

                        preparedStatement.setInt(1, Integer.parseInt(request.queryParams("id_word")));
                        preparedStatement.execute();

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
     * Удаляет символ в таблице `symbol`
     *
     * @param request
     * @param response
     * @return
     */
    public static String deleteSymbol(Request request, Response response) {

        if (TokenCheck.checkTeacher( request.queryParams("token"))) {

            if (request.queryParams("id_symbol") != null) {

                    try (Connection connection = HerokuAPI.Translit.getDB()) {
                        PreparedStatement preparedStatement = connection.prepareStatement(DELETEStatement.deleteSymbol());

                        preparedStatement.setInt(1, Integer.parseInt(request.queryParams("id_symbol")));
                        preparedStatement.execute();

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
