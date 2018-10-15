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
import kz.eid.utils.sql.statement.GETStatement;
import spark.Request;
import spark.Response;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class JDBCGET {

    /**
     * Отправка ключа для редактирования
     *
     * @return возвращает список факультетов в JSON.
     */
    public static String getAuth(Request request, Response response) {

        if (request.queryParams("pass") != null &&
                HerokuAPI.pass.equals(request.queryParams("pass"))) {

            response.status(200);

            return "{\"key\":" + HerokuAPI.key + "}";
        } else {

            response.status(400);

            return "400 Bad Request";
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
            ResultSet resultSet = connection.prepareStatement(GETStatement.getFaculty()).executeQuery();

            while (resultSet.next())
                list.add(new Faculty(resultSet.getInt("id_faculty"), resultSet.getString("name")));

            response.status(200);
        } catch (SQLException e) {

            response.status(400);

            return "400 Bad Request";
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

        if (request.queryParams("faculty") != null) {
            ArrayList<Specialty> list = new ArrayList<>();

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(GETStatement.getSpecialty());

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

                return "400 Bad Request";
            }

            return new Gson().toJson(list);
        } else {

            response.status(400);

            return "400 Bad Request";
        }
    }

    /**
     * Получает информацию с таблицы "curator"
     *
     * @param connection
     * @return возвращает список конкретных специальностей в JSON.
     */
    public static String getCuratorGroup(Connection connection, Request request, Response response) {

        if (request.queryParams("group") != null) {
            Curator curator;

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(GETStatement.getCuratorGroup());

                preparedStatement.setInt(1, Integer.parseInt(request.queryParams("group")));

                ResultSet resultSet = preparedStatement.executeQuery();

                resultSet.next();

                curator = new Curator(
                        resultSet.getInt("id_group"),
                        resultSet.getInt("id_teacher")
                );

                response.status(200);
            } catch (SQLException | NumberFormatException e) {

                response.status(400);

                return "400 Bad Request";
            }

            return new Gson().toJson(curator);
        } else {

            response.status(400);

            return "400 Bad Request";
        }
    }

    /**
     * Получает информацию с таблицы "curator"
     *
     * @param connection
     * @return возвращает список конкретных специальностей в JSON.
     */
    public static String getCuratorTeacher(Connection connection, Request request, Response response) {

        if (request.queryParams("teacher") != null) {
            ArrayList<Curator> list = new ArrayList<>();

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(GETStatement.getCuratorTeacher());

                preparedStatement.setInt(1, Integer.parseInt(request.queryParams("teacher")));

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next())
                    list.add(new Curator(
                            resultSet.getInt("id_group"),
                            resultSet.getInt("id_teacher")
                    ));

                response.status(200);
            } catch (SQLException | NumberFormatException e) {

                response.status(400);

                return "400 Bad Request";
            }

            return new Gson().toJson(list);
        } else {

            response.status(400);

            return "400 Bad Request";
        }
    }

    /**
     * Получает информацию с таблицы "group"
     *
     * @param connection
     * @return возвращает конкретную группу в JSON.
     */
    public static String getGroup(Connection connection, Request request, Response response) {

        if (request.queryParams("specialty") != null) {
            ArrayList<Group> list = new ArrayList<>();

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(GETStatement.getGroup());

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

                return "400 Bad Request";
            }

            return new Gson().toJson(list);
        } else {

            response.status(400);

            return "400 Bad Request";
        }
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
            PreparedStatement preparedStatement = connection.prepareStatement(GETStatement.getRoom());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
                list.add(new Room(
                        resultSet.getInt("id_room"),
                        resultSet.getString("name")
                ));

            response.status(200);
        } catch (SQLException | NumberFormatException e) {

            response.status(400);

            return "400 Bad Request";
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
    public static String getSchedule(Connection connection, Request request, Response response) {

        if (request.queryParams("group") != null) {
            ArrayList<Schedule> list = new ArrayList<>();

            try {
                ResultSet resultSet = GETStatement.getReadDB(connection, GETStatement.getSchedule(), Integer.parseInt(request.queryParams("group")));

                while (resultSet.next()){

                    Schedule schedule = new Schedule();

                    schedule.setId_schedule(resultSet.getInt(1));
                    schedule.setD(resultSet.getInt(2));
                    schedule.setNum(resultSet.getInt(3));
                    schedule.setId_teacher(resultSet.getInt(5));
                    schedule.setName(resultSet.getString(6));
                    schedule.setS_name(resultSet.getString(7));
                    schedule.setL_name(resultSet.getString(8));
                    schedule.setPhone(resultSet.getString(9));
                    schedule.setEmail(resultSet.getString(10));

                    ResultSet resultSet2 = GETStatement.getReadDB(connection, GETStatement.getScheduleSubject(), resultSet.getInt(4));

                    resultSet2.next();

                    if (resultSet2.getInt("id_change") == 0){

                        schedule.setType(resultSet2.getInt("t"));
                        schedule.setChange(0);

                        ResultSet resultSet3 = GETStatement.getReadDB(connection, GETStatement.getRoom(), resultSet2.getInt("room"));

                        resultSet3.next();
                        schedule.setRoom(resultSet3.getString("name"));

                        resultSet3 = GETStatement.getReadDB(connection, GETStatement.getListSubject(), resultSet2.getInt("id_list_subject"));

                        resultSet3.next();

                        schedule.setSubject(resultSet3.getString("name"));
                    } else {

                        schedule.setChange(1);

                        ResultSet resultSet3 = GETStatement.getReadDB(connection, GETStatement.getChange(), resultSet2.getInt("id_change"));

                        resultSet3.next();
                        schedule.setType(resultSet3.getInt("t"));

                        ResultSet resultSet4 = GETStatement.getReadDB(connection, GETStatement.getListSubject(), resultSet3.getInt("id_list_subject"));

                        resultSet4.next();

                        schedule.setSubject(resultSet4.getString("name"));

                        resultSet4 = GETStatement.getReadDB(connection, GETStatement.getRoom(), resultSet3.getInt("id_room"));

                        resultSet4.next();
                        schedule.setRoom(resultSet4.getString("name"));

                        resultSet4 = GETStatement.getReadDB(connection, GETStatement.getTeacher(), resultSet3.getInt("id_teacher"));

                        schedule.setId_teacher(1);
                        schedule.setName(resultSet4.getString("name"));
                        schedule.setS_name(resultSet4.getString("s_name"));
                        schedule.setL_name(resultSet4.getString("l_name"));
                        schedule.setPhone(resultSet4.getString("phone"));
                        schedule.setEmail(resultSet4.getString("email"));
                    }

                    list.add(schedule);
                }

                response.status(200);
            } catch (SQLException | NumberFormatException e) {

                response.status(400);

                return "400 Bad Request: " + e.getMessage();
            }

            return new Gson().toJson(list);
        } else {

            response.status(400);

            return "400 Bad Request";
        }
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

        if (request.queryParams("id_teacher") != null) {

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(GETStatement.getTeacher());

                preparedStatement.setInt(1, Integer.parseInt(request.queryParams("id_teacher")));

                ResultSet resultSet = preparedStatement.executeQuery();

                resultSet.next();

                Teacher teacher = new Teacher(
                        resultSet.getInt("id_teacher"),
                        resultSet.getString("name"),
                        resultSet.getString("s_name"),
                        resultSet.getString("l_name"),
                        resultSet.getString("phone"),
                        resultSet.getString("email"),
                        resultSet.getInt("id_room")
                );

                response.status(200);

                return new Gson().toJson(teacher);
            } catch (SQLException | NumberFormatException e) {

                response.status(400);

                return "400 Bad Request";
            }
        } else {

            response.status(400);

            return "400 Bad Request";
        }
    }

    /**
     * Получает информацию с таблицы "teacher"
     *
     * @param connection
     * @return возвращает всех преподавателей в JSON.
     */
    public static String getAll(Connection connection, Response response) {
        ArrayList<Teacher> list = new ArrayList<>();

        try {
            ResultSet resultSet = connection.prepareStatement(GETStatement.getTeacherAll()).executeQuery();

            while (resultSet.next())
                list.add(new Teacher(
                        resultSet.getInt("id_teacher"),
                        resultSet.getString("name"),
                        resultSet.getString("s_name"),
                        resultSet.getString("l_name"),
                        resultSet.getString("phone"),
                        resultSet.getString("email"),
                        resultSet.getInt("id_room")
                ));

            response.status(200);
        } catch (SQLException e) {

            response.status(400);

            return "400 Bad Request";
        }

        return new Gson().toJson(list);
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
            ResultSet resultSet = connection.prepareStatement(GETStatement.getListSubjectAll()).executeQuery();

            while (resultSet.next())
                list.add(new ListSubject(resultSet.getInt("id_list_subject"), resultSet.getString("name")));

            response.status(200);
        } catch (SQLException e) {

            response.status(400);

            return "400 Bad Request";
        }

        return new Gson().toJson(list);
    }
}
