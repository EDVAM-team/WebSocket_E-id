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
import kz.osmium.main.util.StatusResponse;
import kz.osmium.translit.objects.Translit;
import kz.osmium.translit.objects.gson.Symbol;
import kz.osmium.translit.objects.gson.Word;
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
    public static String getWord(HashMap<String, Connection> connection, Response response) {

            try {
                PreparedStatement preparedStatement = connection.get("translit").prepareStatement(GETStatement.getWord());
                ResultSet resultSet = preparedStatement.executeQuery();
                ArrayList<Word> wordArrayList = new ArrayList<>();

                while (resultSet.next())
                    wordArrayList.add(new Word(
                            resultSet.getString("cyrl"),
                            resultSet.getString("latn")
                    ));

                response.status(200);

                return new Gson().toJson(wordArrayList);
            } catch (SQLException e) {

                response.status(409);

                return StatusResponse.CONFLICT;
            }
    }

    /**
     * Выводит все символы с таблицы `symbol`
     *
     * @param response
     * @return
     */
    public static String getSymbol(HashMap<String, Connection> connection, Response response) {

            try {
                PreparedStatement preparedStatement = connection.get("translit").prepareStatement(GETStatement.getSymbol());
                ResultSet resultSet = preparedStatement.executeQuery();
                ArrayList<Symbol> wordArrayList = new ArrayList<>();

                while (resultSet.next())
                    wordArrayList.add(new Symbol(
                            resultSet.getString("cyrl"),
                            resultSet.getString("latn")
                    ));

                response.status(200);

                return new Gson().toJson(wordArrayList);
            } catch (SQLException e) {

                response.status(409);

                return StatusResponse.CONFLICT;
            }
    }
}
