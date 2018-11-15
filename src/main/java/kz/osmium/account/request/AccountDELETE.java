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

import kz.osmium.account.main.util.TokenGen;
import kz.osmium.account.statement.PUTStatement;
import kz.osmium.main.util.StatusResponse;
import spark.Request;
import spark.Response;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;

public class AccountDELETE {


    /**
     * Вносит изменения в аккаунте.
     * Используется таблица "account"
     *
     * @param connection
     * @return
     */
    public static String putAccount(HashMap<String, Connection> connection, Request request, Response response) {

        if (TokenCheck.checkAccount(connection, request.queryParams("token"), Integer.parseInt(request.queryParams("id_account")))) {

            if (request.queryParams("id_account") != null &&
                    request.queryParams("type") != null ||
                    request.queryParams("id_group") != null ||
                    request.queryParams("f_name") != null ||
                    request.queryParams("l_name") != null ||
                    request.queryParams("patronymic") != null ||
                    request.queryParams("phone") != null ||
                    request.queryParams("email") != null ||
                    request.queryParams("id_room") != null ||
                    request.queryParams("login") != null ||
                    request.queryParams("pass") != null) {

                try {
                    PreparedStatement preparedStatement = connection.get("oqu").prepareStatement(PUTStatement.putAccount());

                    if (request.queryParams("type") != null)
                        preparedStatement.setInt(1, Integer.parseInt(request.queryParams("type")));
                    else
                        preparedStatement.setNull(1, Types.INTEGER);

                    if (request.queryParams("id_group") != null)
                        preparedStatement.setInt(2, Integer.parseInt(request.queryParams("id_group")));
                    else
                        preparedStatement.setNull(2, Types.INTEGER);

                    if (request.queryParams("f_name") != null) {
                        preparedStatement.setString(3, request.queryParams("f_name"));
                        preparedStatement.setString(4, request.queryParams("f_name"));
                    } else {
                        preparedStatement.setNull(3, Types.VARCHAR);
                        preparedStatement.setNull(4, Types.VARCHAR);
                    }

                    if (request.queryParams("l_name") != null)
                        preparedStatement.setString(5, request.queryParams("l_name"));
                        preparedStatement.setString(6, request.queryParams("l_name"));
                    else
                        preparedStatement.setNull(5, Types.VARCHAR);
                        preparedStatement.setNull(6, Types.VARCHAR);

                    if (request.queryParams("patronymic") != null)
                        preparedStatement.setString(7, request.queryParams("patronymic"));
                        preparedStatement.setString(8, request.queryParams("patronymic"));
                    else
                        preparedStatement.setNull(7, Types.VARCHAR);
                        preparedStatement.setNull(8, Types.VARCHAR);

                    if (request.queryParams("phone") != null)
                        preparedStatement.setString(9, request.queryParams("phone"));
                    else
                        preparedStatement.setNull(9, Types.VARCHAR);

                    if (request.queryParams("email") != null)
                        preparedStatement.setString(10, request.queryParams("email"));
                    else
                        preparedStatement.setNull(10, Types.VARCHAR);

                    if (request.queryParams("id_room") != null)
                        preparedStatement.setInt(11, Integer.parseInt(request.queryParams("id_room")));
                    else
                        preparedStatement.setNull(11, Types.INTEGER);

                    if (request.queryParams("login") != null) {
                        preparedStatement.setString(12, request.queryParams("login"));
                        preparedStatement.setString(13, request.queryParams("login"));
                    } else {
                        preparedStatement.setNull(12, Types.VARCHAR);
                        preparedStatement.setNull(13, Types.VARCHAR);
                    }

                    if (request.queryParams("pass") != null) {
                        preparedStatement.setString(14, request.queryParams("pass"));
                        preparedStatement.setString(15, request.queryParams("pass"));
                    } else {
                        preparedStatement.setNull(14, Types.VARCHAR);
                        preparedStatement.setNull(15, Types.VARCHAR);
                    }

                    preparedStatement.setInt(16, Integer.parseInt(request.queryParams("id_account")));
                    preparedStatement.executeUpdate();

                    response.status(201);
                } catch (SQLException | NumberFormatException e) {

                    response.status(400);

                    return e.getMessage();
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
