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

package kz.eid.jdbc;

import kz.eid.utils.HerokuAPI;
import kz.eid.utils.StatusResponse;
import kz.eid.utils.sql.statement.DELETEStatement;
import spark.Request;
import spark.Response;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCDELETE {

    /**
     * Получает информацию с таблицы "curator"
     *
     * @param connection
     * @return возвращает список конкретных специальностей в JSON.
     */
    public static String deleteCurator(Connection connection, Request request, Response response) {

        if (request.queryParams("key").equals(HerokuAPI.key)) {

            if (request.queryParams("group") != null) {

                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(DELETEStatement.deleteCurator());

                    preparedStatement.setInt(1, Integer.parseInt(request.queryParams("group")));

                    preparedStatement.execute();

                    response.status(200);
                } catch (SQLException | NumberFormatException e) {

                    response.status(400);

                    return StatusResponse.error;
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
