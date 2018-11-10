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

import com.google.gson.Gson;
import kz.osmium.main.HerokuAPI;
import kz.osmium.main.StatusResponse;
import kz.osmium.translit.objects.Translit;
import kz.osmium.translit.statement.GETStatement;
import spark.Request;
import spark.Response;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TranslitGET {

    /**
     * Транслитезирует казахский текст.
     *
     * @param request
     * @param response
     * @return
     */
    public static String getTranslit(Request request, Response response) {

        if (request.queryParams("text") != null &&
                request.queryParams("t") != null) {
            Map<String, String> map = new HashMap<>();

            switch (Integer.parseInt(request.queryParams("t"))) {
                case 1:
                    map.put("text", Translit.сyrlToLatn(request.queryParams("text")));
                    break;
                case 2:
                    map.put("text", Translit.latnToCyrl(request.queryParams("text")));
                    break;
            }

            response.status(200);

            return new Gson().toJson(map);
        } else {

            response.status(400);

            return "400 Bad Request";
        }
    }

    /**
     * Выводит все слова с таблицы `word`
     *
     * @param response
     * @return
     */
    public static String getWord(Response response) {
        try {
            Connection connection = DriverManager.getConnection(
                    HerokuAPI.Translit.url,
                    HerokuAPI.Translit.login,
                    HerokuAPI.Translit.password
            );

            try {

                PreparedStatement preparedStatement = connection.prepareStatement(GETStatement.getWord());
                ResultSet resultSet = preparedStatement.executeQuery();
                ArrayList<kz.osmium.translit.objects.gson.Translit> translits = new ArrayList<>();

                while (resultSet.next())
                    translits.add(new kz.osmium.translit.objects.gson.Translit(
                            resultSet.getString("cyrl"),
                            resultSet.getString("latn")
                    ));

                response.status(200);

                return new Gson().toJson(translits);
            } catch (SQLException | NumberFormatException e) {

                response.status(409);

                return e.getMessage();
            } finally {

                try {

                    connection.close();
                } catch (SQLException | NullPointerException e) {

                }
            }
        } catch (SQLException e) {

            response.status(500);

            return StatusResponse.internal_server_error;
        }
    }
}
