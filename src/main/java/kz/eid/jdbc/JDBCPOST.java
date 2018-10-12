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

import kz.eid.utils.HerokuAPI;
import kz.eid.utils.SQLStatement;
import kz.eid.utils.StatusResponse;
import spark.Request;
import spark.Response;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import static spark.Spark.*;

public class JDBCPOST {

    /**
     * Создает факультет.
     * Используется таблица "faculty"
     *
     * @param connection
     * @return
     */
    public static String postFaculty(Connection connection, Request request, Response response) {

        if (request.queryParams("key").equals(HerokuAPI.key)) {

            if (request.queryParams("name") != null) {

                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(SQLStatement.postFaculty());

                    preparedStatement.setString(1, request.queryParams("name"));
                    preparedStatement.execute();

                    response.status(201);

                    return StatusResponse.success;
                } catch (SQLException e) {

                    response.status(400);

                    return StatusResponse.error;
                }
            } else {

                response.status(400);

                return StatusResponse.error;
            }
        } else {

            response.status(401);

            return StatusResponse.error;
        }
    }

    /**
     * Создает факультет.
     * Используется таблица "specialty"
     *
     * @param connection
     * @return
     */
    public static String postSpecialty(Connection connection, Request request, Response response) {

        if (request.queryParams("key").equals(HerokuAPI.key)) {

            if (request.queryParams("name") != null &&
                    request.queryParams("id_faculty") != null) {

                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(SQLStatement.postSpecialty());

                    preparedStatement.setString(1, request.queryParams("name"));
                    preparedStatement.setInt(2, Integer.parseInt(request.queryParams("id_faculty")));
                    preparedStatement.execute();

                    response.status(201);

                    return StatusResponse.success;
                } catch (SQLException | NumberFormatException e) {

                    response.status(400);

                    return StatusResponse.error;
                }
            } else {

                response.status(400);

                return StatusResponse.error;
            }
        } else {

            response.status(401);

            return StatusResponse.error;
        }
    }

    /**
     * Создает группу.
     * Используется таблица "group"
     *
     * @param connection
     * @return
     */
    public static String postGroup(Connection connection, Request request, Response response) {

        if (request.queryParams("key").equals(HerokuAPI.key)) {

            if (request.queryParams("name") != null &&
                    request.queryParams("id_specialty") != null) {

                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(SQLStatement.postGroup());

                    preparedStatement.setString(1, request.queryParams("name"));
                    preparedStatement.setInt(2, Integer.parseInt(request.queryParams("id_specialty")));
                    preparedStatement.execute();

                    response.status(201);

                    return StatusResponse.success;
                } catch (SQLException | NumberFormatException e) {

                    response.status(400);

                    return StatusResponse.error;
                }
            } else {

                response.status(400);

                return StatusResponse.error;
            }
        } else {

            response.status(401);

            return StatusResponse.error;
        }
    }

    /**
     * Создает кабинет.
     * Используется таблица "room"
     *
     * @param connection
     * @return
     */
    public static String postRoom(Connection connection, Request request, Response response) {

        if (request.queryParams("key").equals(HerokuAPI.key)) {

            if (request.queryParams("name") != null) {

                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(SQLStatement.postRoom());

                    preparedStatement.setString(1, request.queryParams("name"));
                    preparedStatement.execute();

                    response.status(201);

                    return StatusResponse.success;
                } catch (SQLException | NumberFormatException e) {

                    response.status(400);

                    return StatusResponse.error;
                }
            } else {

                response.status(400);

                return StatusResponse.error;
            }
        } else {

            response.status(401);

            return StatusResponse.error;
        }
    }

    /**
     * Создает преподавателя.
     * Используется таблица "teacher"
     *
     * @param connection
     * @return
     */
    public static String postTeacher(Connection connection, Request request, Response response) {

        if (request.queryParams("key").equals(HerokuAPI.key)) {

            if (request.queryParams("name") != null) {

                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(SQLStatement.postTeacher());

                    preparedStatement.setString(1, request.queryParams("name"));

                    if (request.queryParams("s_name") != null)
                        preparedStatement.setString(2, request.queryParams("s_name"));
                    else
                        preparedStatement.setNull(2, Types.VARCHAR);

                    if (request.queryParams("l_name") != null)
                        preparedStatement.setString(3, request.queryParams("l_name"));
                    else
                        preparedStatement.setNull(3, Types.VARCHAR);

                    if (request.queryParams("phone") != null)
                        preparedStatement.setString(4, request.queryParams("phone"));
                    else
                        preparedStatement.setNull(4, Types.VARCHAR);

                    if (request.queryParams("email") != null)
                        preparedStatement.setString(5, request.queryParams("email"));
                    else
                        preparedStatement.setNull(5, Types.VARCHAR);

                    if (request.queryParams("id_room") != null)
                        preparedStatement.setInt(6, Integer.parseInt(request.queryParams("id_room")));
                    else
                        preparedStatement.setNull(6, Types.INTEGER);

                    preparedStatement.execute();

                    response.status(201);

                    return StatusResponse.success;
                } catch (SQLException | NumberFormatException e) {

                    response.status(400);

                    return StatusResponse.error;
                }
            } else {

                response.status(400);

                return StatusResponse.error;
            }
        } else {

            response.status(401);

            return StatusResponse.error;
        }
    }

    /**
     * Создает предмет для расписания.
     * Используется таблица "schedule_subject"
     *
     * @param connection
     * @return
     */
    public static String postSubject(Connection connection, Request request) {
        return "JDBCPOST postSubject";
    }

    /**
     * Создает предмет для расписания.
     * Используется таблица "list_subject"
     *
     * @param connection
     * @return
     */
    public static String postSubjectItem(Connection connection, Request request) {
        return "JDBCPOST postSubjectItem";
    }

    /**
     * Создает расписание для группы.
     * Используется таблица "schedule"
     *
     * @param connection
     * @return
     */
    public static String postSchedule(Connection connection, Request request) {
        return "JDBCPOST postSchedule";
    }

    /**
     * Создает замену для конкретного предмета в расписании группы.
     * Используется таблица "change"
     *
     * @param connection
     * @return
     */
    public static String postChange(Connection connection, Request request) {
        return "JDBCPOST postChange";
    }
}
