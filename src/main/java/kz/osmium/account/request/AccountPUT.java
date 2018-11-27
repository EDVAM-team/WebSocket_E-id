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
import kz.osmium.account.statement.PUTStatement;
import kz.osmium.main.util.HerokuAPI;
import kz.osmium.main.util.StatusResponse;
import spark.Request;
import spark.Response;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class AccountPUT {

    /**
     * Вносит изменения в аккаунте.
     * Используется таблица "accounts"
     *
     * @return
     */
    public static String putAccount(Request request, Response response) {

        if (TokenCheck.checkAccount(request.queryParams("token"), Integer.parseInt(request.queryParams("id")))) {

            if (request.queryParams("id") != null &&
                    request.queryParams("type") != null ||
                    request.queryParams("group_id") != null ||
                    request.queryParams("name_f") != null ||
                    request.queryParams("name_l") != null ||
                    request.queryParams("patronymic") != null ||
                    request.queryParams("phone") != null ||
                    request.queryParams("email") != null ||
                    request.queryParams("room_id") != null ||
                    request.queryParams("login") != null ||
                    request.queryParams("pass") != null) {

                try (Connection connection = HerokuAPI.Account.getDB()) {
                    PreparedStatement preparedStatement = connection.prepareStatement(PUTStatement.putAccount());

                    if (request.queryParams("type") != null) {
                        preparedStatement.setInt(1, Integer.parseInt(request.queryParams("type")));
                        preparedStatement.setInt(2, Integer.parseInt(request.queryParams("type")));
                    } else {
                        preparedStatement.setNull(1, Types.INTEGER);
                        preparedStatement.setNull(2, Types.INTEGER);
                    }

                    if (request.queryParams("group_id") != null)
                        preparedStatement.setInt(3, Integer.parseInt(request.queryParams("group_id")));
                    else
                        preparedStatement.setNull(3, Types.INTEGER);

                    if (request.queryParams("name_f") != null) {
                        preparedStatement.setString(4, request.queryParams("name_f"));
                        preparedStatement.setString(5, request.queryParams("name_f"));
                    } else {
                        preparedStatement.setNull(4, Types.VARCHAR);
                        preparedStatement.setNull(5, Types.VARCHAR);
                    }

                    if (request.queryParams("name_l") != null) {
                        preparedStatement.setString(6, request.queryParams("name_l"));
                        preparedStatement.setString(7, request.queryParams("name_l"));
                    } else {
                        preparedStatement.setNull(6, Types.VARCHAR);
                        preparedStatement.setNull(7, Types.VARCHAR);
                    }

                    if (request.queryParams("patronymic") != null) {
                        preparedStatement.setString(8, request.queryParams("patronymic"));
                        preparedStatement.setString(9, request.queryParams("patronymic"));
                    } else {
                        preparedStatement.setNull(8, Types.VARCHAR);
                        preparedStatement.setNull(9, Types.VARCHAR);
                    }

                    if (request.queryParams("phone") != null)
                        preparedStatement.setString(10, request.queryParams("phone"));
                    else
                        preparedStatement.setNull(10, Types.VARCHAR);

                    if (request.queryParams("email") != null)
                        preparedStatement.setString(11, request.queryParams("email"));
                    else
                        preparedStatement.setNull(11, Types.VARCHAR);

                    if (request.queryParams("room_id") != null)
                        preparedStatement.setInt(12, Integer.parseInt(request.queryParams("room_id")));
                    else
                        preparedStatement.setNull(12, Types.INTEGER);

                    if (request.queryParams("login") != null) {
                        preparedStatement.setString(13, request.queryParams("login"));
                        preparedStatement.setString(14, request.queryParams("login"));
                    } else {
                        preparedStatement.setNull(13, Types.VARCHAR);
                        preparedStatement.setNull(14, Types.VARCHAR);
                    }

                    if (request.queryParams("pass") != null) {
                        preparedStatement.setString(15, request.queryParams("pass"));
                        preparedStatement.setString(16, request.queryParams("pass"));
                    } else {
                        preparedStatement.setNull(15, Types.VARCHAR);
                        preparedStatement.setNull(16, Types.VARCHAR);
                    }

                    preparedStatement.setInt(17, Integer.parseInt(request.queryParams("id")));
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

            return StatusResponse.UNAUTHORIZED;
        }
    }
}
