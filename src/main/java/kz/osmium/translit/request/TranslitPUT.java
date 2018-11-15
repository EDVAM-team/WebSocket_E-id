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

import kz.osmium.main.util.StatusResponse;
import kz.osmium.main.util.TokenCheck;
import kz.osmium.translit.statement.PUTStatement;
import spark.Request;
import spark.Response;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

public class TranslitPUT {

    /**
     * Изменяет исключающие слово в таблице `word`
     *
     * @param request
     * @param response
     * @return
     */
    public static String putWord(HashMap<String, Connection> connection, Request request, Response response) {

        if (TokenCheck.checkTeacher(connection, request.queryParams("token"))) {

            if (request.queryParams("cyrl") != null &&
                    request.queryParams("latn") != null) {

                    try {
                        PreparedStatement preparedStatement = connection.get("translit").prepareStatement(PUTStatement.putWord());

                        preparedStatement.setString(1, request.queryParams("latn"));
                        preparedStatement.setString(2, request.queryParams("cyrl"));
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
     * Изменяет символ в таблице `symbol`
     *
     * @param request
     * @param response
     * @return
     */
    public static String putSymbol(HashMap<String, Connection> connection, Request request, Response response) {

        if (TokenCheck.checkTeacher(connection, request.queryParams("token"))) {

            if (request.queryParams("cyrl") != null &&
                    request.queryParams("latn") != null) {

                    try {
                        PreparedStatement preparedStatement = connection.get("translit").prepareStatement(PUTStatement.putSymbol());

                        preparedStatement.setString(1, request.queryParams("latn"));
                        preparedStatement.setString(2, request.queryParams("cyrl"));
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
