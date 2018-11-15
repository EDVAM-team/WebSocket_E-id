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

import com.google.gson.Gson;
import kz.osmium.account.main.util.TokenGen;
import kz.osmium.account.objects.gson.Auth;
import kz.osmium.account.statement.GETStatement;
import spark.Request;
import spark.Response;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class AccountGET {

    /**
     * Получает информацию с таблицы "account"
     *
     * @param connection
     * @return возвращает список факультетов в JSON.
     */
    public static String getAccount(HashMap<String, Connection> connection, Request request, Response response) {

        if (request.queryParams("login") != null &&
                request.queryParams("pass") != null) {

            try {
                PreparedStatement preparedStatement = connection.get("account").prepareStatement(GETStatement.getAccount());

                preparedStatement.setString(1, request.queryParams("login"));
                preparedStatement.setString(2, request.queryParams("pass"));

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    PreparedStatement preparedStatement2 = connection.get("account").prepareStatement(GETStatement.addToken());
                    String token = TokenGen.generate(request.queryParams("login"));

                    preparedStatement2.setString(1, token);
                    preparedStatement2.setInt(2, resultSet.getInt("id_account"));

                    if (preparedStatement2.execute()){
                        Auth account = new Auth(
                                resultSet.getInt("id_account"),
                                resultSet.getString("f_name"),
                                resultSet.getString("l_name"),
                                resultSet.getString("patronymic"),
                                resultSet.getString("phone"),
                                resultSet.getString("email"),
                                resultSet.getInt("id_room"),
                                resultSet.getInt("id_group"),
                                resultSet.getInt("type"),
                                token
                        );

                        response.status(200);

                        return new Gson().toJson(account);
                    } else {

                        response.status(400);

                        return "400 Bad Request";
                    }
                }
            } catch (SQLException | NumberFormatException e) {

                response.status(400);

                return "400 Bad Request";
            }
        }

        response.status(400);

        return "400 Bad Request";
    }
}
