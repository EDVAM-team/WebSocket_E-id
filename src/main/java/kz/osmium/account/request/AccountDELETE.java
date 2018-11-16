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
import kz.osmium.account.main.util.TokenGen;
import kz.osmium.account.statement.DELETEStatement;
import kz.osmium.account.statement.PUTStatement;
import kz.osmium.main.util.StatusResponse;
import spark.Request;
import spark.Response;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

public class AccountDELETE {

    /**
     * Удаляет сессию аккаунта в таблице "auth"
     *
     * @param connection
     * @return возвращает список конкретных специальностей в JSON.
     */
    public static String deleteAuth(HashMap<String, Connection> connection, Request request, Response response) {

        if (TokenCheck.checkAccount(connection, request.queryParams("token"), Integer.parseInt(request.queryParams("id_account")))) {

            if (request.queryParams("token") != null) {

                try {
                    PreparedStatement preparedStatement = connection.get("account").prepareStatement(DELETEStatement.deleteAuth());

                    preparedStatement.setString(1, request.queryParams("token"));

                    preparedStatement.execute();

                    response.status(200);
                } catch (SQLException | NumberFormatException e) {

                    response.status(400);

                    return StatusResponse.ERROR;
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
     * Удаляет сессии аккаунта в таблице "auth"
     *
     * @param connection
     * @return возвращает список конкретных специальностей в JSON.
     */
    public static String deleteAuthAll(HashMap<String, Connection> connection, Request request, Response response) {

        if (TokenCheck.checkAccountAdmin(connection, request.queryParams("token"), Integer.parseInt(request.queryParams("id_account")))) {

            if (request.queryParams("token") != null) {

                try {
                    PreparedStatement preparedStatement = connection.get("account").prepareStatement(DELETEStatement.deleteAuthAll());

                    preparedStatement.setInt(1, Integer.parseInt(request.queryParams("id_account")));

                    preparedStatement.execute();

                    response.status(200);
                } catch (SQLException | NumberFormatException e) {

                    response.status(400);

                    return StatusResponse.ERROR;
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
