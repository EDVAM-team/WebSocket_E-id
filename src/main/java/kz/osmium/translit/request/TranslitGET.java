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
import kz.osmium.main.util.HerokuAPI;
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
     * Транслитерация казахского текста.
     *
     * @param request
     * @param response
     * @return
     */
    public static String getTranslit(Request request, Response response) {

        if (request.queryParams("text") != null) {
            Map<String, String> map = new HashMap<>();

            map.put("text", Translit.сyrlToLatn(request.queryParams("text")));
            response.status(200);

            return new Gson().toJson(map);
        } else {

            response.status(400);

            return StatusResponse.BAD_REQUEST;
        }
    }

    /**
     * Выводит все слова с таблицы `words`
     *
     * @param response
     * @return
     */
    public static String getWord(Response response) {

        try (Connection connection = HerokuAPI.Translit.getDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GETStatement.getWordAll());
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Word> wordArrayList = new ArrayList<>();

            while (resultSet.next())
                wordArrayList.add(new Word(
                        resultSet.getInt("id"),
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
     * Выводит все символы с таблицы `symbols`
     *
     * @param response
     * @return
     */
    public static String getSymbol(Response response) {

        try (Connection connection = HerokuAPI.Translit.getDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GETStatement.getSymbolAll());
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Symbol> wordArrayList = new ArrayList<>();

            while (resultSet.next())
                wordArrayList.add(new Symbol(
                        resultSet.getInt("id"),
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
