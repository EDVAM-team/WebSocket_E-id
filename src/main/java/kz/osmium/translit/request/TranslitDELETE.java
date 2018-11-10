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

import kz.osmium.main.HerokuAPI;
import kz.osmium.main.StatusResponse;
import kz.osmium.translit.statement.DELETEStatement;
import kz.osmium.translit.statement.POSTStatement;
import spark.Request;
import spark.Response;

import java.sql.Connection;
import java.sql.DriverManager;
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

        if (request.queryParams("key").equals(HerokuAPI.Translit.key)) {

            if (request.queryParams("cyrl") != null) {
                Connection connection = null;

                try {
                    connection = DriverManager.getConnection(
                            HerokuAPI.Translit.url,
                            HerokuAPI.Translit.login,
                            HerokuAPI.Translit.password
                    );

                    PreparedStatement preparedStatement = connection.prepareStatement(DELETEStatement.deleteWord());

                    preparedStatement.setString(1, request.queryParams("cyrl"));
                    preparedStatement.execute();

                    response.status(200);
                } catch (SQLException | NumberFormatException e) {

                    response.status(409);

                    return StatusResponse.conflict;
                } finally {

                    try {

                        connection.close();
                    } catch (SQLException | NullPointerException e) {

                    }
                }

                return StatusResponse.success;
            } else {

                response.status(400);

                return StatusResponse.error;
            }
        } else {

            response.status(401);

            return StatusResponse.error;
        }
    }
}
