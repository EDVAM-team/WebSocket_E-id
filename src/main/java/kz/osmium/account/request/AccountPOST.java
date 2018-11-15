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

package kz.osmium.account.request;

import kz.osmium.account.main.util.TokenCheck;
import kz.osmium.account.statement.POSTStatement;
import kz.osmium.main.util.StatusResponse;
import spark.Request;
import spark.Response;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;

public class AccountPOST {

    /**
     * Создает аккаунт.
     * Используется таблица "account"
     *
     * @param connection
     * @return
     */
    public static String postAccount(HashMap<String, Connection> connection, Request request, Response response) {

        if (TokenCheck.checkAdmin(connection, request.queryParams("token"))) {

            if (request.queryParams("f_name") != null &&
                    request.queryParams("login") != null &&
                    request.queryParams("type") != null &&
                    request.queryParams("pass") != null) {

                try {
                    PreparedStatement preparedStatement = connection.get("account").prepareStatement(POSTStatement.postAccount());

                    preparedStatement.setString(1, request.queryParams("f_name"));
                    preparedStatement.setInt(6, Integer.parseInt(request.queryParams("type")));
                    preparedStatement.setString(7, request.queryParams("login"));
                    preparedStatement.setString(8, request.queryParams("pass"));

                    if (request.queryParams("l_name") != null)
                        preparedStatement.setString(2, request.queryParams("l_name"));
                    else
                        preparedStatement.setNull(2, Types.VARCHAR);

                    if (request.queryParams("patronymic") != null)
                        preparedStatement.setString(3, request.queryParams("patronymic"));
                    else
                        preparedStatement.setNull(3, Types.VARCHAR);

                    if (request.queryParams("phone") != null)
                        preparedStatement.setString(4, request.queryParams("phone"));
                    else
                        preparedStatement.setNull(4, Types.VARCHAR);

                    if (request.queryParams("email") != null)
                        preparedStatement.setString(5, request.queryParams("email"));
                    else
                        preparedStatement.setNull(5, Types.VARCHAR);

                    if (request.queryParams("id_group") != null &&
                            Integer.parseInt(request.queryParams("type")) == 1)
                        preparedStatement.setInt(10, Integer.parseInt(request.queryParams("id_group")));
                    else
                        preparedStatement.setNull(10, Types.INTEGER);

                    if (request.queryParams("id_room") != null &&
                            Integer.parseInt(request.queryParams("type")) == 2)
                        preparedStatement.setInt(9, Integer.parseInt(request.queryParams("id_room")));
                    else
                        preparedStatement.setNull(9, Types.INTEGER);

                    preparedStatement.execute();

                    response.status(201);
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
