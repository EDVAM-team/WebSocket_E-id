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

package kz.osmium.oqu.jdbc;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import kz.osmium.main.HerokuAPI;
import kz.osmium.main.StatusResponse;
import kz.osmium.oqu.gson.MarkJSON;
import kz.osmium.oqu.statement.GETStatement;
import kz.osmium.oqu.statement.POSTStatement;
import spark.Request;
import spark.Response;

import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OquPOST {

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
                    PreparedStatement preparedStatement = connection.prepareStatement(POSTStatement.postFaculty());

                    preparedStatement.setString(1, request.queryParams("name"));
                    preparedStatement.execute();

                    response.status(201);
                } catch (SQLException e) {

                    response.status(400);

                    return StatusResponse.error;
                }

                return StatusResponse.success;
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
                    PreparedStatement preparedStatement = connection.prepareStatement(POSTStatement.postSpecialty());

                    preparedStatement.setString(1, request.queryParams("name"));
                    preparedStatement.setInt(2, Integer.parseInt(request.queryParams("id_faculty")));
                    preparedStatement.execute();

                    response.status(201);
                } catch (SQLException | NumberFormatException e) {

                    response.status(400);

                    return StatusResponse.error;
                }

                return StatusResponse.success;
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
                    PreparedStatement preparedStatement = connection.prepareStatement(POSTStatement.postGroup());

                    preparedStatement.setString(1, request.queryParams("name"));
                    preparedStatement.setInt(2, Integer.parseInt(request.queryParams("id_specialty")));
                    preparedStatement.execute();

                    response.status(201);
                } catch (SQLException | NumberFormatException e) {

                    response.status(400);

                    return StatusResponse.error;
                }

                return StatusResponse.success;
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
     * Создает куратора.
     * Используется таблица "search"
     *
     * @param connection
     * @return
     */
    public static String postCurator(Connection connection, Request request, Response response) {

        if (request.queryParams("key").equals(HerokuAPI.key)) {

            if (request.queryParams("group") != null &&
                    request.queryParams("teacher") != null) {

                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(POSTStatement.postCurator());

                    preparedStatement.setInt(1, Integer.parseInt(request.queryParams("group")));
                    preparedStatement.setInt(2, Integer.parseInt(request.queryParams("teacher")));
                    preparedStatement.execute();

                    response.status(201);
                } catch (SQLException | NumberFormatException e) {

                    response.status(400);

                    return StatusResponse.error;
                }

                return StatusResponse.success;
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
                    PreparedStatement preparedStatement = connection.prepareStatement(POSTStatement.postRoom());

                    preparedStatement.setString(1, request.queryParams("name"));
                    preparedStatement.execute();

                    response.status(201);
                } catch (SQLException | NumberFormatException e) {

                    response.status(400);

                    return StatusResponse.error;
                }

                return StatusResponse.success;
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
     * Используется таблица "account"
     *
     * @param connection
     * @return
     */
    public static String postTeacher(Connection connection, Request request, Response response) {

        if (request.queryParams("key").equals(HerokuAPI.key)) {

            if (request.queryParams("name") != null &&
                    request.queryParams("login") != null &&
                    request.queryParams("pass") != null) {

                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(POSTStatement.postTeacher());

                    preparedStatement.setString(1, request.queryParams("name"));
                    preparedStatement.setInt(2, 2);
                    preparedStatement.setString(3, request.queryParams("login"));
                    preparedStatement.setString(4, request.queryParams("pass"));

                    if (request.queryParams("s_name") != null)
                        preparedStatement.setString(5, request.queryParams("s_name"));
                    else
                        preparedStatement.setNull(5, Types.VARCHAR);

                    if (request.queryParams("l_name") != null)
                        preparedStatement.setString(6, request.queryParams("l_name"));
                    else
                        preparedStatement.setNull(6, Types.VARCHAR);

                    if (request.queryParams("phone") != null)
                        preparedStatement.setString(7, request.queryParams("phone"));
                    else
                        preparedStatement.setNull(7, Types.VARCHAR);

                    if (request.queryParams("email") != null)
                        preparedStatement.setString(8, request.queryParams("email"));
                    else
                        preparedStatement.setNull(8, Types.VARCHAR);

                    if (request.queryParams("id_room") != null)
                        preparedStatement.setInt(9, Integer.parseInt(request.queryParams("id_room")));
                    else
                        preparedStatement.setNull(9, Types.INTEGER);

                    preparedStatement.execute();

                    response.status(201);
                } catch (SQLException | NumberFormatException e) {

                    response.status(400);

                    return StatusResponse.error;
                }

                return StatusResponse.success;
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
     * Создает рейтинг студента.
     * Используется таблица "rating"
     *
     * @param connection
     * @return
     */
    public static String postRating(Connection connection, Request request, Response response) {

        if (request.queryParams("key").equals(HerokuAPI.key)) {

            if (request.queryParams("id_subject") != null &&
                    request.queryParams("id_account") != null &&
                    request.queryParams("num") != null) {

                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(POSTStatement.postRating());

                    preparedStatement.setInt(1, Integer.parseInt(request.queryParams("id_subject")));
                    preparedStatement.setInt(2, Integer.parseInt(request.queryParams("id_account")));
                    preparedStatement.setInt(3, Integer.parseInt(request.queryParams("num")));
                    preparedStatement.execute();

                    response.status(201);
                } catch (SQLException | NumberFormatException e) {

                    response.status(400);

                    return StatusResponse.error;
                }

                return StatusResponse.success;
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
     * Создает оценку студентам.
     * Используется таблица "mark"
     *
     * @param connection
     * @return
     */
    public static String postMark(Connection connection, Request request, Response response) {

        if (request.queryParams("key").equals(HerokuAPI.key)) {

            if (request.queryParams("id_subject") != null) {

                try {
                    Type type = new TypeToken<List<MarkJSON>>() {
                    }.getType();
                    ArrayList<MarkJSON> list = new Gson().fromJson(request.body(), type);

                    for (MarkJSON markJSON : list) {
                        PreparedStatement preparedStatement = connection.prepareStatement(GETStatement.getRatingCurrent());

                        preparedStatement.setInt(1, markJSON.getIdAccount());
                        preparedStatement.setInt(2, Integer.parseInt(request.queryParams("id_subject")));

                        ResultSet resultSet = preparedStatement.executeQuery();

                        while (resultSet.next()) {
                            PreparedStatement preparedStatement2 = connection.prepareStatement(POSTStatement.postMark());

                            preparedStatement2.setInt(1, resultSet.getInt("id_rating"));
                            preparedStatement2.setInt(2, markJSON.getN());
                            preparedStatement2.setInt(3, markJSON.getMark());
                            preparedStatement2.execute();
                        }
                    }

                    response.status(201);
                } catch (SQLException | NumberFormatException e) {

                    response.status(400);

                    return StatusResponse.error;
                }

                return StatusResponse.success;
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
     * Создает студента.
     * Используется таблица "account"
     *
     * @param connection
     * @return
     */
    public static String postStudent(Connection connection, Request request, Response response) {

        if (request.queryParams("key").equals(HerokuAPI.key)) {

            if (request.queryParams("name") != null &&
                    request.queryParams("login") != null &&
                    request.queryParams("pass") != null) {

                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(POSTStatement.postStudent());

                    preparedStatement.setString(1, request.queryParams("name"));
                    preparedStatement.setInt(2, 1);
                    preparedStatement.setString(3, request.queryParams("login"));
                    preparedStatement.setString(4, request.queryParams("pass"));

                    if (request.queryParams("s_name") != null)
                        preparedStatement.setString(5, request.queryParams("s_name"));
                    else
                        preparedStatement.setNull(5, Types.VARCHAR);

                    if (request.queryParams("l_name") != null)
                        preparedStatement.setString(6, request.queryParams("l_name"));
                    else
                        preparedStatement.setNull(6, Types.VARCHAR);

                    if (request.queryParams("phone") != null)
                        preparedStatement.setString(7, request.queryParams("phone"));
                    else
                        preparedStatement.setNull(7, Types.VARCHAR);

                    if (request.queryParams("email") != null)
                        preparedStatement.setString(8, request.queryParams("email"));
                    else
                        preparedStatement.setNull(8, Types.VARCHAR);

                    if (request.queryParams("id_group") != null)
                        preparedStatement.setInt(9, Integer.parseInt(request.queryParams("id_group")));
                    else
                        preparedStatement.setNull(9, Types.INTEGER);

                    preparedStatement.execute();

                    response.status(201);
                } catch (SQLException | NumberFormatException e) {

                    response.status(400);

                    return StatusResponse.error;
                }

                return StatusResponse.success;
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
     * Используется таблица "list_subject"
     *
     * @param connection
     * @return
     */
    public static String postSubjectItem(Connection connection, Request request, Response response) {
        if (request.queryParams("key").equals(HerokuAPI.key)) {

            if (request.queryParams("name") != null) {

                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(POSTStatement.postItemSubject());

                    preparedStatement.setString(1, request.queryParams("name"));
                    preparedStatement.execute();

                    response.status(201);
                } catch (SQLException | NumberFormatException e) {

                    response.status(400);

                    return StatusResponse.error;
                }

                return StatusResponse.success;
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
     * Создает замену для конкретного предмета в расписании группы.
     * Используется таблица "change"
     *
     * @param connection
     * @return
     */
    public static String postChange(Connection connection, Request request, Response response) {
        return "OquPOST postChange";
    }

    /**
     * Создает расписание для группы.
     * Используется таблица "schedule"
     *
     * @param connection
     * @return
     */
    public static String postSchedule(Connection connection, Request request) {
        return "OquPOST postSchedule";
    }
}
