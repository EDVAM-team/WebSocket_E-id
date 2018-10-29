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
     * Получает информацию с таблицы "account"
     *
     * @param connection
     * @return возвращает список факультетов в JSON.
     */
    public static String getAccount(Connection connection, Request request, Response response) {

        if (request.queryParams("login") != null &&
                request.queryParams("pass") != null) {

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(GETStatement.getAccount());

                preparedStatement.setString(1, request.queryParams("login"));
                preparedStatement.setString(2, request.queryParams("pass"));

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Account account = new Account(
                            resultSet.getInt("id_account"),
                            resultSet.getString("name"),
                            resultSet.getString("s_name"),
                            resultSet.getString("l_name"),
                            resultSet.getString("phone"),
                            resultSet.getString("email"),
                            resultSet.getInt("id_room"),
                            resultSet.getInt("id_group"),
                            resultSet.getInt("t")
                    );

                    response.status(200);

                    return new Gson().toJson(account);
                }
            } catch (SQLException | NumberFormatException e) {

                response.status(400);

                return "400 Bad Request";
            }
        }

        response.status(400);

        return "400 Bad Request";
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
     * Получает информацию с таблицы "account"
     *
     * @param connection
     * @return возвращает список конкретных специальностей в JSON.
     */
    public static String getCuratorGroup(Connection connection, Request request, Response response) {

        if (request.queryParams("group") != null) {

            try {
                ResultSet resultSet = GETStatement.getReadDB(connection, GETStatement.getCuratorGroup(), Integer.parseInt(request.queryParams("group")));

                while (resultSet.next()) {
                    ResultSet resultSet2 = GETStatement.getReadDB(connection, GETStatement.getAccountID(), resultSet.getInt("id_account"));

                    while (resultSet2.next()) {

                        response.status(200);

                        return new Gson().toJson(new Teacher(
                                resultSet2.getInt("id_account"),
                                resultSet2.getString("name"),
                                resultSet2.getString("s_name"),
                                resultSet2.getString("l_name"),
                                resultSet2.getString("phone"),
                                resultSet2.getString("email"),
                                resultSet2.getString("id_room")
                        ));
                    }
                }
            } catch (SQLException | NumberFormatException e) {

                response.status(400);

                return "400 Bad Request";
            }

            response.status(400);

            return "400 Bad Request";
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

            try {
                ResultSet resultSet = GETStatement.getReadDB(connection, GETStatement.getCuratorTeacher(), Integer.parseInt(request.queryParams("teacher")));

                while (resultSet.next()) {
                    ArrayList<Group> list = new ArrayList<>();
                    ResultSet resultSet2 = GETStatement.getReadDB(connection, GETStatement.getGroup(), resultSet.getInt("id_group"));

                    while (resultSet2.next())
                        list.add(new Group(resultSet2.getInt("id_group"), resultSet2.getString("name")));

                    response.status(200);

                    return new Gson().toJson(list);
                }
            } catch (SQLException | NumberFormatException e) {

                response.status(400);

                return "400 Bad Request";
            }

            response.status(400);

            return "400 Bad Request";
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
            PreparedStatement preparedStatement = connection.prepareStatement(GETStatement.getRoomAll());

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
                ResultSet resultSet = GETStatement.getReadDB(
                        connection,
                        GETStatement.getSchedule(),
                        Integer.parseInt(request.queryParams("group"))
                );

                while (resultSet.next()) {

                    Schedule schedule = new Schedule();

                    schedule.setId_schedule(resultSet.getInt(1));
                    schedule.setD(resultSet.getInt(2));
                    schedule.setNum(resultSet.getInt(3));

                    if (resultSet.getInt("id_account") != 1) {
                        schedule.setId_teacher(resultSet.getInt(5));
                        schedule.setName(resultSet.getString(6));
                        schedule.setS_name(resultSet.getString(7));
                        schedule.setL_name(resultSet.getString(8));
                        schedule.setPhone(resultSet.getString(9));
                        schedule.setEmail(resultSet.getString(10));
                    } else {

                        schedule.setId_teacher(1);
                    }

                    ResultSet resultSet2 = GETStatement.getReadDB(connection, GETStatement.getScheduleSubject(), resultSet.getInt(4));

                    resultSet2.next();

                    if (resultSet2.getInt("id_change") == 0) {

                        schedule.setType(resultSet2.getInt("t"));
                        schedule.setChange(0);

                        ResultSet resultSet3 = GETStatement.getReadDB(connection, GETStatement.getRoom(), resultSet2.getInt("room"));

                        while (resultSet3.next())
                            schedule.setRoom(resultSet3.getString("name"));

                        resultSet3 = GETStatement.getReadDB(connection, GETStatement.getListSubject(), resultSet2.getInt("id_list_subject"));

                        resultSet3.next();

                        schedule.setSubject(resultSet3.getString("name"));
                    } else {

                        schedule.setChange(1);

                        ResultSet resultSet3 = GETStatement.getReadDB(connection, GETStatement.getChange(), resultSet2.getInt("id_change"));

                        resultSet3.next();
                        schedule.setType(resultSet3.getInt("t"));
                        schedule.setId_teacher(resultSet3.getInt("id_account"));

                        ResultSet resultSet4 = GETStatement.getReadDB(connection, GETStatement.getListSubject(), resultSet3.getInt("id_list_subject"));

                        resultSet4.next();

                        schedule.setSubject(resultSet4.getString("name"));

                        resultSet4 = GETStatement.getReadDB(connection, GETStatement.getRoom(), resultSet3.getInt("id_room"));

                        while (resultSet4.next())
                            schedule.setRoom(resultSet4.getString("name"));

                        if (resultSet3.getInt("id_account") != 1) {
                            resultSet4 = GETStatement.getReadDB(connection, GETStatement.getAccountID(), resultSet3.getInt("id_account"));

                            while (resultSet4.next()) {

                                schedule.setName(resultSet4.getString("name"));
                                schedule.setS_name(resultSet4.getString("s_name"));
                                schedule.setL_name(resultSet4.getString("l_name"));
                                schedule.setPhone(resultSet4.getString("phone"));
                                schedule.setEmail(resultSet4.getString("email"));
                            }
                        } else {

                            schedule.setId_teacher(1);
                        }
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
    public static String getScheduleTeacher(Connection connection, Request request, Response response) {

        if (request.queryParams("teacher") != null) {
            ArrayList<ScheduleTeacher> list = new ArrayList<>();

            try {
                ResultSet resultSet = GETStatement.getReadDB(connection, GETStatement.getScheduleTeacher(), Integer.parseInt(request.queryParams("teacher")));

                while (resultSet.next()) {

                    ScheduleTeacher scheduleTeacher = new ScheduleTeacher();

                    scheduleTeacher.setId_schedule(resultSet.getInt(1));
                    scheduleTeacher.setD(resultSet.getInt(2));
                    scheduleTeacher.setNum(resultSet.getInt(3));
                    scheduleTeacher.setName(resultSet.getString(6));

                    ResultSet resultSet2 = GETStatement.getReadDB(connection, GETStatement.getScheduleSubject(), resultSet.getInt(4));

                    while (resultSet2.next()) {
                        scheduleTeacher.setType(resultSet2.getInt("t"));
                        scheduleTeacher.setChange(0);
                    }

                    ResultSet resultSet3 = GETStatement.getReadDB(connection, GETStatement.getRoom(), resultSet2.getInt("room"));

                    while (resultSet3.next())
                        scheduleTeacher.setRoom(resultSet3.getString("name"));

                    resultSet3 = GETStatement.getReadDB(connection, GETStatement.getListSubject(), resultSet2.getInt("id_list_subject"));

                    while (resultSet3.next())
                        scheduleTeacher.setSubject(resultSet3.getString("name"));

//                    resultSet2 = GETStatement.getReadDB(connection, GETStatement.getGroupID(), resultSet.getInt(5));
//                    ArrayList<Group> groupList = new ArrayList<>();
//
//                    while (resultSet2.next())
//                        groupList.add(new Group(resultSet2.getInt("id_group"), resultSet2.getString("name")));
//
//                    scheduleTeacher.setGroup(groupList);

                    list.add(scheduleTeacher);
                }

                resultSet = GETStatement.getReadDB(connection, GETStatement.getChangeTeacher(), Integer.parseInt(request.queryParams("teacher")));

                while (resultSet.next()) {
                    ScheduleTeacher scheduleTeacher = new ScheduleTeacher();

                    scheduleTeacher.setType(resultSet.getInt("t"));
                    scheduleTeacher.setChange(1);

                    ResultSet resultSet2 = GETStatement.getReadDB(connection, GETStatement.getRoom(), resultSet.getInt("id_room"));

                    while (resultSet2.next())
                        scheduleTeacher.setRoom(resultSet2.getString("name"));

                    resultSet2 = GETStatement.getReadDB(connection, GETStatement.getScheduleChange(), resultSet.getInt("id_change"));

                    while (resultSet2.next()) {
                        ResultSet resultSet3 = GETStatement.getReadDB(connection, GETStatement.getSubjectSchedule(), resultSet2.getInt("id_schedule_subject"));

                        while (resultSet3.next()) {

                            scheduleTeacher.setId_schedule(resultSet3.getInt("id_schedule"));
                            scheduleTeacher.setD(resultSet3.getInt("d"));
                            scheduleTeacher.setNum(resultSet3.getInt("num"));

                            ResultSet resultSet4 = GETStatement.getReadDB(connection, GETStatement.getGroupID(), resultSet3.getInt("id_group"));
                            ArrayList<Group> groupList = new ArrayList<>();

                            while (resultSet4.next())
                                groupList.add(new Group(resultSet4.getInt("id_group"), resultSet4.getString("name")));

                            scheduleTeacher.setGroup(groupList);
                        }
                    }

                    list.add(scheduleTeacher);
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
     * Получает информацию с таблицы "account"
     *
     * @param connection
     * @return возвращает конкретного преподователя в JSON.
     */
    public static String getAccountID(Connection connection, Request request, Response response) {

        if (request.queryParams("id_account") != null) {

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(GETStatement.getAccountID());

                preparedStatement.setInt(1, Integer.parseInt(request.queryParams("id_account")));

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Account account = new Account(
                            resultSet.getInt("id_account"),
                            resultSet.getString("name"),
                            resultSet.getString("s_name"),
                            resultSet.getString("l_name"),
                            resultSet.getString("phone"),
                            resultSet.getString("email"),
                            resultSet.getInt("id_room"),
                            resultSet.getInt("id_group"),
                            resultSet.getInt("t")
                    );

                    response.status(200);

                    return new Gson().toJson(account);
                }
            } catch (SQLException | NumberFormatException e) {

                response.status(400);

                return "400 Bad Request";
            }
        }

        response.status(400);

        return "400 Bad Request";
    }

    /**
     * Получает информацию с таблицы "account"
     *
     * @param connection
     * @return возвращает всех преподавателей в JSON.
     */
    public static String getTeacherAll(Connection connection, Response response) {
        ArrayList<Account> list = new ArrayList<>();

        try {
            ResultSet resultSet = connection.prepareStatement(GETStatement.getTeacherAll()).executeQuery();

            while (resultSet.next())
                list.add(new Account(
                        resultSet.getInt("id_account"),
                        resultSet.getString("name"),
                        resultSet.getString("s_name"),
                        resultSet.getString("l_name"),
                        resultSet.getString("phone"),
                        resultSet.getString("email"),
                        resultSet.getInt("id_room"),
                        resultSet.getInt("id_group"),
                        resultSet.getInt("t")
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
    public static String getListSubject(Connection connection, Response response) {
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
