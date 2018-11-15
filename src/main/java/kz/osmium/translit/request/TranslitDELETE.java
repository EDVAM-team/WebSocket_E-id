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
import kz.osmium.main.util.StatusResponse;
import kz.osmium.translit.statement.DELETEStatement;
import spark.Request;
import spark.Response;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

public class TranslitDELETE {

    /**
     * Удаляет слово в таблице `word`
     *
     * @param request
     * @param response
     * @return
     */
    public static String deleteWord(HashMap<String, Connection> connection, Request request, Response response) {

        if (TokenCheck.checkTeacher(connection, request.queryParams("token"))) {

            if (request.queryParams("cyrl") != null) {

                    try {
                        PreparedStatement preparedStatement = connection.get("translit").prepareStatement(DELETEStatement.deleteWord());

                        preparedStatement.setString(1, request.queryParams("cyrl"));
                        preparedStatement.execute();

                        response.status(200);
                    } catch (SQLException | NumberFormatException e) {

                        response.status(409);

                        return StatusResponse.CONFLICT;
                    }

                    return StatusResponse.SUCCESS;
            } else {

                response.status(400);

                return StatusResponse.ERROR;
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
    public static String deleteSymbol(HashMap<String, Connection> connection, Request request, Response response) {

        if (TokenCheck.checkTeacher(connection, request.queryParams("token"))) {

            if (request.queryParams("cyrl") != null) {

                    try {
                        PreparedStatement preparedStatement = connection.get("translit").prepareStatement(DELETEStatement.deleteSymbol());

                        preparedStatement.setString(1, request.queryParams("cyrl"));
                        preparedStatement.execute();

                        response.status(200);
                    } catch (SQLException | NumberFormatException e) {

                        response.status(409);

                        return StatusResponse.CONFLICT;
                    }

                    return StatusResponse.SUCCESS;

            } else {

                response.status(400);

                return StatusResponse.ERROR;
            }
        } else {

            response.status(401);

            return StatusResponse.ERROR;
        }
    }
}
