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

package kz.osmium.oqu.request;

import com.google.gson.Gson;
import kz.osmium.main.util.HerokuAPI;
import kz.osmium.oqu.objects.gson.*;
import kz.osmium.oqu.statement.GETStatement;
import spark.Request;
import spark.Response;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OquGET {

    /**
     * Получает информацию с таблицы "faculty"
     *
     * @return возвращает список факультетов в JSON.
     */
    public static String getFaculty(Response response) {
        ArrayList<Faculty> list = new ArrayList<>();

        try (Connection connection = HerokuAPI.Oqu.getDB()) {
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
     * @return возвращает список конкретных специальностей в JSON.
     */
    public static String getSpecialty(Response response) {
            ArrayList<Specialty> list = new ArrayList<>();

            try (Connection connection = HerokuAPI.Oqu.getDB()) {
                PreparedStatement preparedStatement = connection.prepareStatement(GETStatement.getSpecialty());
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
    }

    /**
     * Получает информацию с таблицы "account"
     *
     * @return возвращает список конкретных специальностей в JSON.
     */
    public static String getCuratorGroup( Request request, Response response) {

        if (request.queryParams("group") != null) {

            try (Connection connection = HerokuAPI.Oqu.getDB()) {
                ResultSet resultSet = GETStatement.getReadDB(connection, GETStatement.getCuratorGroup(), Integer.parseInt(request.queryParams("group")));

                while (resultSet.next()) {

                    response.status(200);

                    return new Gson().toJson(new Teacher(
                            resultSet.getInt("id_account"),
                            resultSet.getString("name")
                    ));
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
     * @return возвращает список конкретных специальностей в JSON.
     */
    public static String getCuratorTeacher( Request request, Response response) {

        if (request.queryParams("teacher") != null) {

            try (Connection connection = HerokuAPI.Oqu.getDB()) {
                PreparedStatement preparedStatement = connection.prepareStatement(GETStatement.getCuratorTeacher());

                preparedStatement.setInt(1, Integer.parseInt(request.queryParams("teacher")));

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    ArrayList<Group> list = new ArrayList<>();
                    PreparedStatement preparedStatement2 = connection.prepareStatement(GETStatement.getGroupID());

                    preparedStatement2.setInt(1, Integer.parseInt(request.queryParams("id_group")));

                    ResultSet resultSet2 = preparedStatement.executeQuery();

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
     * @return возвращает конкретную группу в JSON.
     */
    public static String getGroup(Response response) {
            ArrayList<Group> list = new ArrayList<>();

            try (Connection connection = HerokuAPI.Oqu.getDB()) {
                ResultSet resultSet = connection.prepareStatement(GETStatement.getGroup()).executeQuery();

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
    }

    /**
     * Получает информацию с таблицы "room"
     *
     * @return возвращает конкретную группу в JSON.
     */
    public static String getRoom( Response response) {
        ArrayList<Room> list = new ArrayList<>();

        try (Connection connection = HerokuAPI.Oqu.getDB()) {
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
     * Получает информацию с таблицы "total"
     *
     * @return возвращает конкретную группу в JSON.
     */
    public static String getTotal( Request request, Response response) {

        if (request.queryParams("id_account") != null) {
            ArrayList<Total> list = new ArrayList<>();

            try (Connection connection = HerokuAPI.Oqu.getDB()) {
                ResultSet resultSet = GETStatement.getReadDB(connection, GETStatement.getTotal(), Integer.parseInt(request.queryParams("id_account")));

                while (resultSet.next()) {
                    Total total = new Total();
                    Total.Subject subject = new Total.Subject();

                    subject.setId(resultSet.getInt("id_list_subject"));
                    subject.setName(resultSet.getString("name"));
                    total.setIdTotal(resultSet.getInt("id_total"));
                    total.setCourse(resultSet.getInt("course"));
                    total.setSubject(subject);
                    list.add(total);
                }

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
     * Получает информацию с таблицы "rating"
     *
     * @return возвращает конкретную группу в JSON.
     */
    public static String getRatingStudent( Request request, Response response) {

        if (request.queryParams("id_account") != null &&
                request.queryParams("num") != null) {

            Rating rating = new Rating();
            ArrayList<Rating.RatingChild> ratingChildren = new ArrayList<>();

            try (Connection connection = HerokuAPI.Oqu.getDB()) {
                PreparedStatement preparedStatement = connection.prepareStatement(GETStatement.getRatingStudent());

                preparedStatement.setInt(1, Integer.parseInt(request.queryParams("id_account")));
                preparedStatement.setInt(2, Integer.parseInt(request.queryParams("num")));

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Rating.RatingChild ratingChild = new Rating.RatingChild();
                    Rating.RatingChild.Subject subject = new Rating.RatingChild.Subject();
                    ArrayList<Rating.RatingChild.Mark> markArrayList = new ArrayList<>();

                    ratingChild.setId(resultSet.getInt("id_rating"));
                    ratingChild.setNum(resultSet.getInt("num"));
                    subject.setId(resultSet.getInt("id_list_subject"));
                    subject.setName(resultSet.getString("name_list_subject"));

                    if (rating.getIdAccount() == 0)
                        rating.setIdAccount(resultSet.getInt("id_account"));

                    if (rating.getNameAccount() == null)
                        rating.setNameAccount(resultSet.getString("name_account"));

                    ResultSet resultSet2 = GETStatement.getReadDB(connection, GETStatement.getMark(), resultSet.getInt("id_rating"));

                    while (resultSet2.next())
                        markArrayList.add(new Rating.RatingChild.Mark(
                                resultSet2.getInt("id_mark"),
                                resultSet2.getInt("n"),
                                resultSet2.getInt("mark")
                        ));

                    ratingChild.setSubject(subject);
                    ratingChild.setMark(markArrayList);
                    ratingChildren.add(ratingChild);
                }

                rating.setRating(ratingChildren);

                response.status(200);
            } catch (SQLException | NumberFormatException e) {

                response.status(400);

                return "400 Bad Request " + e.getMessage();
            }

            return new Gson().toJson(rating);
        } else {

            response.status(400);

            return "400 Bad Request";
        }
    }

    /**
     * Обрабатывает информацию так, чтобы JSON отправлял ответ
     * расписания на всю неделю для ученика. Еще есть информация про замены.
     *
     * @return возвращает полную информацию расписания группы в JSON.
     */
    public static String getScheduleStudent( Request request, Response response) {

        if (request.queryParams("group") != null) {
            ArrayList<ScheduleStudent> list = new ArrayList<>();

            try (Connection connection = HerokuAPI.Oqu.getDB()) {
                PreparedStatement preparedStatement = connection.prepareStatement(GETStatement.getScheduleStudent());

                preparedStatement.setInt(1, Integer.parseInt(request.queryParams("group")));
                preparedStatement.setInt(2, Integer.parseInt(request.queryParams("group")));

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    ScheduleStudent scheduleStudent = new ScheduleStudent();
                    ScheduleStudent.SubjectSchedule subjectSchedule = new ScheduleStudent.SubjectSchedule();
                    ScheduleStudent.SubjectSchedule.SubjectList subjectList = new ScheduleStudent.SubjectSchedule.SubjectList();
                    ScheduleStudent.SubjectSchedule.Room room = new ScheduleStudent.SubjectSchedule.Room();
                    ScheduleStudent.Teacher teacher = new ScheduleStudent.Teacher();

                    subjectList.setId(resultSet.getInt("id_list_subject"));
                    subjectList.setName(resultSet.getString("name_list_subject"));

                    room.setId(resultSet.getInt("id_room"));
                    room.setName(resultSet.getString("name_room"));

                    teacher.setId(resultSet.getInt("id_account"));
                    teacher.setName(resultSet.getString("name_account"));

                    subjectSchedule.setId(resultSet.getInt("id_schedule_subject"));
                    subjectSchedule.setT(resultSet.getInt("t"));
                    subjectSchedule.setChange(resultSet.getInt("id_change"));
                    subjectSchedule.setRoom(room);
                    subjectSchedule.setSubjectList(subjectList);

                    scheduleStudent.setIdSchedule(resultSet.getInt("id_schedule"));
                    scheduleStudent.setD(resultSet.getInt("d"));
                    scheduleStudent.setNum(resultSet.getInt("num"));
                    scheduleStudent.setSubjectSchedule(subjectSchedule);
                    scheduleStudent.setTeacher(teacher);

                    list.add(scheduleStudent);
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
     * @return возвращает полную информацию расписания группы в JSON.
     */
    public static String getScheduleTeacher( Request request, Response response) {

        if (request.queryParams("teacher") != null) {
            ArrayList<ScheduleTeacher> list = new ArrayList<>();

            try (Connection connection = HerokuAPI.Oqu.getDB()) {
                ResultSet resultSet = GETStatement.getReadDB(connection, GETStatement.getScheduleTeacher(), Integer.parseInt(request.queryParams("teacher")));

                while (resultSet.next()) {

                    ScheduleTeacher scheduleTeacher = new ScheduleTeacher();

                    scheduleTeacher.setId_schedule(resultSet.getInt(1));
                    scheduleTeacher.setD(resultSet.getInt(2));
                    scheduleTeacher.setNum(resultSet.getInt(3));

                    ResultSet resultSet2 = GETStatement.getReadDB(connection, GETStatement.getScheduleSubject(), resultSet.getInt(4));

                    resultSet2.next();

                    scheduleTeacher.setType(resultSet2.getInt("t"));
                    scheduleTeacher.setChange(0);

                    ResultSet resultSet3 = GETStatement.getReadDB(connection, GETStatement.getRoom(), resultSet2.getInt("room"));

                    while (resultSet3.next())
                        scheduleTeacher.setRoom(resultSet3.getString("name"));

                    resultSet3 = GETStatement.getReadDB(connection, GETStatement.getListSubject(), resultSet2.getInt("id_list_subject"));

                    resultSet3.next();
                    scheduleTeacher.setSubject(resultSet3.getString("name"));

                    resultSet3 = GETStatement.getReadDB(connection, GETStatement.getGroupID(), resultSet.getInt(5));
                    ArrayList<Group> groupList = new ArrayList<>();

                    while (resultSet3.next())
                        groupList.add(new Group(resultSet3.getInt("id_group"), resultSet3.getString("name")));

                    scheduleTeacher.setGroup(groupList);

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

                            resultSet4 = GETStatement.getReadDB(connection, GETStatement.getListSubject(), resultSet.getInt("id_list_subject"));

                            resultSet4.next();
                            scheduleTeacher.setSubject(resultSet4.getString("name"));
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
     * Получает информацию с таблицы "list_subject"
     *
     * @return возвращает весь список предметов в JSON.
     */
    public static String getListSubject( Response response) {
        ArrayList<ListSubject> list = new ArrayList<>();

        try (Connection connection = HerokuAPI.Oqu.getDB()) {
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
