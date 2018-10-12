/*
 * Copyright 2018 EDVAM
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

import com.google.gson.Gson;
import kz.eid.objects.*;
import kz.eid.utils.HerokuAPI;
import kz.eid.utils.SQLStatement;
import spark.Request;
import spark.Response;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class JDBCGET {

    /**
     * Отправка ключа для редактирования
     *
     * @return возвращает список факультетов в JSON.
     */
    public static String getAuth(Request request, Response response) {

        if (HerokuAPI.pass.equals(request.queryParams("pass"))) {

            response.status(200);

            return "{\"key\":" + HerokuAPI.key + "}";
        } else {

            response.status(400);

            return null;
        }
    }

    /**
     * Получает информацию с таблицы "faculty"
     *
     * @param connection
     * @return возвращает список факультетов в JSON.
     */
    public static String getFaculty(Connection connection, Response response) {
        ArrayList<Faculty> list = new ArrayList<>();

        try {
            ResultSet resultSet = connection.prepareStatement(SQLStatement.getFaculty()).executeQuery();

            while (resultSet.next())
                list.add(new Faculty(resultSet.getInt("id_faculty"), resultSet.getString("name")));

            response.status(200);
        } catch (SQLException e) {

            response.status(400);

            return null;
        }

        return new Gson().toJson(list);
    }

    /**
     * Получает информацию с таблицы "specialty"
     *
     * @param connection
     * @return возвращает список конкретных специальностей в JSON.
     */
    public static String getSpecialty(Connection connection, Request request, Response response) {
        ArrayList<Specialty> list = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLStatement.getSpecialty());

            preparedStatement.setInt(1, Integer.parseInt(request.queryParams("faculty")));

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
                list.add(new Specialty(
                        resultSet.getInt("id_specialty"),
                        resultSet.getString("name")
                ));

            response.status(200);
        } catch (SQLException | NumberFormatException e) {

            response.status(400);

            return null;
        }

        return new Gson().toJson(list);
    }

    /**
     * Получает информацию с таблицы "group"
     *
     * @param connection
     * @return возвращает конкретную группу в JSON.
     */
    public static String getGroup(Connection connection, Request request, Response response) {
        ArrayList<Group> list = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLStatement.getGroup());

            preparedStatement.setInt(1, Integer.parseInt(request.queryParams("specialty")));

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
                list.add(new Group(
                        resultSet.getInt("id_group"),
                        resultSet.getString("name")
                ));

            response.status(200);
        } catch (SQLException | NumberFormatException e) {

            response.status(400);

            return null;
        }

        return new Gson().toJson(list);
    }

    /**
     * Получает информацию с таблицы "room"
     *
     * @param connection
     * @return возвращает конкретную группу в JSON.
     */
    public static String getRoom(Connection connection, Response response) {
        ArrayList<Room> list = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLStatement.getRoom());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
                list.add(new Room(
                        resultSet.getInt("id_room"),
                        resultSet.getString("name")
                ));

            response.status(200);
        } catch (SQLException | NumberFormatException e) {

            response.status(400);

            return null;
        }

        return new Gson().toJson(list);
    }

    /**
     * Обрабатывает информацию так, чтобы JSON отправлял ответ
     * расписания на всю неделю для ученика. Еще есть информация про замены.
     *
     * @param connection
     * @return возвращает полную информацию расписания группы в JSON.
     */
    public static String getSchedule(Connection connection, Request request) {
        return "JDBCGET getSchedule";
    }

    /**
     * Обрабатывает информацию так, чтобы JSON отправлял ответ
     * расписания на всю неделю для преподавателя. Еще есть информация про замены.
     *
     * @param connection
     * @return возвращает полную информацию расписания группы в JSON.
     */
    public static String getScheduleTeacher(Connection connection, Request request) {
        return "JDBCGET getScheduleTeacher";
    }

    /**
     * Получает информацию с таблицы "teacher"
     *
     * @param connection
     * @return возвращает конкретного преподователя в JSON.
     */
    public static String getTeacher(Connection connection, Request request, Response response) {
        return "JDBCGET getTeacher";
    }

    /**
     * Получает информацию с таблицы "teacher"
     *
     * @param connection
     * @return возвращает всех преподавателей в JSON.
     */
    public static String getAll(Connection connection, Response response) {
        return "JDBCGET getTeacherAll";
    }

    /**
     * Получает информацию с таблицы "list_subject"
     *
     * @param connection
     * @return возвращает весь список предметов в JSON.
     */
    public static String getList(Connection connection, Response response) {
        ArrayList<ListSubject> list = new ArrayList<>();

        try {
            ResultSet resultSet = connection.prepareStatement(SQLStatement.getListSubjectAll()).executeQuery();

            while (resultSet.next())
                list.add(new ListSubject(resultSet.getInt("id_list_subject"), resultSet.getString("name")));

            response.status(200);
        } catch (SQLException e) {

            response.status(400);

            return null;
        }

        return new Gson().toJson(list);
    }
}
