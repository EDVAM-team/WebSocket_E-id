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

package kz.osmium.oqu.request;

import kz.osmium.main.util.StatusResponse;
import kz.osmium.main.util.TokenCheck;
import kz.osmium.oqu.statement.DELETEStatement;
import spark.Request;
import spark.Response;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

public class OquDELETE {

    /**
     * Получает информацию с таблицы "curator"
     *
     * @param connection
     * @return возвращает список конкретных специальностей в JSON.
     */
    public static String deleteCurator(HashMap<String, Connection> connection, Request request, Response response) {

        if (TokenCheck.checkAdmin(connection, request.queryParams("token"))) {

            if (request.queryParams("group") != null) {

                try {
                    PreparedStatement preparedStatement = connection.get("oqu").prepareStatement(DELETEStatement.deleteCurator());

                    preparedStatement.setInt(1, Integer.parseInt(request.queryParams("group")));

                    preparedStatement.execute();

                    response.status(200);
                } catch (SQLException | NumberFormatException e) {

                    response.status(400);

                    return StatusResponse.ERROR;
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
