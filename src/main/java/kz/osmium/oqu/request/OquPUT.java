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

import kz.osmium.main.HerokuAPI;
import kz.osmium.main.StatusResponse;
import kz.osmium.oqu.statement.PUTStatement;
import spark.Request;
import spark.Response;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OquPUT {

    /**
     * Вносит изменения в аккаунте.
     * Используется таблица "account"
     *
     * @param connection
     * @return
     */
    public static String putAccount(Connection connection, Request request, Response response){

        if (request.queryParams("key").equals(HerokuAPI.key)) {

            if (request.queryParams("id_account") != null &&
                    request.queryParams("t") != null ||
                    request.queryParams("id_group") != null ||
                    request.queryParams("name") != null ||
                    request.queryParams("s_name") != null ||
                    request.queryParams("l_name") != null ||
                    request.queryParams("phone") != null ||
                    request.queryParams("email") != null ||
                    request.queryParams("id_room") != null ||
                    request.queryParams("login") != null ||
                    request.queryParams("pass") != null) {

                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(PUTStatement.putAccount());

                    preparedStatement.setInt(1, Integer.parseInt(request.queryParams("t")));
                    preparedStatement.setInt(2, Integer.parseInt(request.queryParams("t")));
                    preparedStatement.setInt(3, Integer.parseInt(request.queryParams("id_group")));
                    preparedStatement.setInt(4, Integer.parseInt(request.queryParams("id_group")));
                    preparedStatement.setString(5, request.queryParams("name"));
                    preparedStatement.setString(6, request.queryParams("name"));
                    preparedStatement.setString(7, request.queryParams("s_name"));
                    preparedStatement.setString(8, request.queryParams("s_name"));
                    preparedStatement.setString(9, request.queryParams("l_name"));
                    preparedStatement.setString(10, request.queryParams("l_name"));
                    preparedStatement.setString(11, request.queryParams("phone"));
                    preparedStatement.setString(12, request.queryParams("phone"));
                    preparedStatement.setString(13, request.queryParams("email"));
                    preparedStatement.setString(14, request.queryParams("email"));
                    preparedStatement.setInt(15, Integer.parseInt(request.queryParams("id_room")));
                    preparedStatement.setInt(16, Integer.parseInt(request.queryParams("id_room")));
                    preparedStatement.setString(17, request.queryParams("login"));
                    preparedStatement.setString(18, request.queryParams("login"));
                    preparedStatement.setString(19, request.queryParams("pass"));
                    preparedStatement.setString(20, request.queryParams("pass"));
                    preparedStatement.setInt(21, Integer.parseInt(request.queryParams("id_account")));
                    preparedStatement.executeUpdate();

                    response.status(201);
                } catch (SQLException | NumberFormatException e) {

                    response.status(400);

                    return e.getMessage();
                }

                return StatusResponse.success;
            } else {

                response.status(204);

                return StatusResponse.no_content;
            }
        } else {

            response.status(401);

            return StatusResponse.error;
        }
    }

    /**
     * Вносит изменения в замене.
     * Используется таблица "change"
     *
     * @param connection
     * @return
     */
    public static String putChange(Connection connection, Request request, Response response){

        if (request.queryParams("key").equals(HerokuAPI.key)) {

            if (request.queryParams("id_change") != null &&
                    request.queryParams("id_list_subject") != null ||
                    request.queryParams("t") != null ||
                    request.queryParams("id_account") != null ||
                    request.queryParams("id_room") != null) {

                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(PUTStatement.putChange());

                    preparedStatement.setInt(1, Integer.parseInt(request.queryParams("id_list_subject")));
                    preparedStatement.setInt(2, Integer.parseInt(request.queryParams("id_list_subject")));
                    preparedStatement.setInt(3, Integer.parseInt(request.queryParams("t")));
                    preparedStatement.setInt(4, Integer.parseInt(request.queryParams("t")));
                    preparedStatement.setInt(5, Integer.parseInt(request.queryParams("id_account")));
                    preparedStatement.setInt(6, Integer.parseInt(request.queryParams("id_account")));
                    preparedStatement.setInt(7, Integer.parseInt(request.queryParams("id_room")));
                    preparedStatement.setInt(8, Integer.parseInt(request.queryParams("id_room")));
                    preparedStatement.setInt(9, Integer.parseInt(request.queryParams("id_change")));
                    preparedStatement.executeUpdate();

                    response.status(201);
                } catch (SQLException | NumberFormatException e) {

                    response.status(400);

                    return StatusResponse.error;
                }

                return StatusResponse.success;
            } else {

                response.status(204);

                return StatusResponse.no_content;
            }
        } else {

            response.status(401);

            return StatusResponse.error;
        }
    }

    /**
     * Вносит изменения в кураторе.
     * Используется таблица "curator"
     *
     * @param connection
     * @return
     */
    public static String putCurator(Connection connection, Request request, Response response){

        if (request.queryParams("key").equals(HerokuAPI.key)) {

            if (request.queryParams("id_group") != null &&
                    request.queryParams("id_account") != null) {

                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(PUTStatement.putCurator());

                    preparedStatement.setInt(1, Integer.parseInt(request.queryParams("id_account")));
                    preparedStatement.setInt(2, Integer.parseInt(request.queryParams("id_account")));
                    preparedStatement.setInt(3, Integer.parseInt(request.queryParams("id_group")));
                    preparedStatement.executeUpdate();

                    response.status(201);
                } catch (SQLException | NumberFormatException e) {

                    response.status(400);

                    return StatusResponse.error;
                }

                return StatusResponse.success;
            } else {

                response.status(204);

                return StatusResponse.no_content;
            }
        } else {

            response.status(401);

            return StatusResponse.error;
        }
    }

    /**
     * Вносит изменения в факультете.
     * Используется таблица "faculty"
     *
     * @param connection
     * @return
     */
    public static String putFaculty(Connection connection, Request request, Response response){

        if (request.queryParams("key").equals(HerokuAPI.key)) {

            if (request.queryParams("id_faculty") != null &&
                    request.queryParams("name") != null) {

                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(PUTStatement.putFaculty());

                    preparedStatement.setString(1, request.queryParams("name"));
                    preparedStatement.setString(2, request.queryParams("name"));
                    preparedStatement.setInt(3, Integer.parseInt(request.queryParams("id_faculty")));
                    preparedStatement.executeUpdate();

                    response.status(201);
                } catch (SQLException | NumberFormatException e) {

                    response.status(400);

                    return StatusResponse.error;
                }

                return StatusResponse.success;
            } else {

                response.status(204);

                return StatusResponse.no_content;
            }
        } else {

            response.status(401);

            return StatusResponse.error;
        }
    }

    /**
     * Вносит изменения в группе.
     * Используется таблица "group"
     *
     * @param connection
     * @return
     */
    public static String putGroup(Connection connection, Request request, Response response){

        if (request.queryParams("key").equals(HerokuAPI.key)) {

            if (request.queryParams("id_group") != null &&
                    request.queryParams("name") != null ||
                    request.queryParams("id_specialty") != null) {

                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(PUTStatement.putGroup());

                    preparedStatement.setString(1, request.queryParams("name"));
                    preparedStatement.setString(2, request.queryParams("name"));
                    preparedStatement.setInt(3, Integer.parseInt(request.queryParams("id_specialty")));
                    preparedStatement.setInt(4, Integer.parseInt(request.queryParams("id_specialty")));
                    preparedStatement.setInt(5, Integer.parseInt(request.queryParams("id_group")));
                    preparedStatement.executeUpdate();

                    response.status(201);
                } catch (SQLException | NumberFormatException e) {

                    response.status(400);

                    return StatusResponse.error;
                }

                return StatusResponse.success;
            } else {

                response.status(204);

                return StatusResponse.no_content;
            }
        } else {

            response.status(401);

            return StatusResponse.error;
        }
    }

    /**
     * Вносит изменения в списке предметов.
     * Используется таблица "list_subject"
     *
     * @param connection
     * @return
     */
    public static String putListSubject(Connection connection, Request request, Response response){

        if (request.queryParams("key").equals(HerokuAPI.key)) {

            if (request.queryParams("id_list_subject") != null &&
                    request.queryParams("name") != null) {

                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(PUTStatement.putListSubject());

                    preparedStatement.setString(1, request.queryParams("name"));
                    preparedStatement.setString(2, request.queryParams("name"));
                    preparedStatement.setInt(3, Integer.parseInt(request.queryParams("id_list_subject")));
                    preparedStatement.executeUpdate();

                    response.status(201);
                } catch (SQLException | NumberFormatException e) {

                    response.status(400);

                    return StatusResponse.error;
                }

                return StatusResponse.success;
            } else {

                response.status(204);

                return StatusResponse.no_content;
            }
        } else {

            response.status(401);

            return StatusResponse.error;
        }
    }

    /**
     * Вносит изменения в оценках.
     * Используется таблица "mark"
     *
     * @param connection
     * @return
     */
    public static String putMark(Connection connection, Request request, Response response){

        if (request.queryParams("key").equals(HerokuAPI.key)) {

            if (request.queryParams("id_mark") != null &&
                    request.queryParams("id_rating") != null ||
                    request.queryParams("n") != null ||
                    request.queryParams("mark") != null) {

                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(PUTStatement.putMark());

                    preparedStatement.setInt(1, Integer.parseInt(request.queryParams("id_rating")));
                    preparedStatement.setInt(2, Integer.parseInt(request.queryParams("id_rating")));
                    preparedStatement.setInt(3, Integer.parseInt(request.queryParams("n")));
                    preparedStatement.setInt(4, Integer.parseInt(request.queryParams("n")));
                    preparedStatement.setInt(5, Integer.parseInt(request.queryParams("mark")));
                    preparedStatement.setInt(6, Integer.parseInt(request.queryParams("mark")));
                    preparedStatement.setInt(7, Integer.parseInt(request.queryParams("id_mark")));
                    preparedStatement.executeUpdate();

                    response.status(201);
                } catch (SQLException | NumberFormatException e) {

                    response.status(400);

                    return StatusResponse.error;
                }

                return StatusResponse.success;
            } else {

                response.status(204);

                return StatusResponse.no_content;
            }
        } else {

            response.status(401);

            return StatusResponse.error;
        }
    }

    /**
     * Вносит изменения в рейтинге.
     * Используется таблица "rating"
     *
     * @param connection
     * @return
     */
    public static String putRating(Connection connection, Request request, Response response){

        if (request.queryParams("key").equals(HerokuAPI.key)) {

            if (request.queryParams("id_rating") != null &&
                    request.queryParams("id_list_subject") != null ||
                    request.queryParams("id_account") != null ||
                    request.queryParams("num") != null) {

                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(PUTStatement.putRating());

                    preparedStatement.setInt(1, Integer.parseInt(request.queryParams("id_list_subject")));
                    preparedStatement.setInt(2, Integer.parseInt(request.queryParams("id_list_subject")));
                    preparedStatement.setInt(3, Integer.parseInt(request.queryParams("id_account")));
                    preparedStatement.setInt(4, Integer.parseInt(request.queryParams("id_account")));
                    preparedStatement.setInt(5, Integer.parseInt(request.queryParams("num")));
                    preparedStatement.setInt(6, Integer.parseInt(request.queryParams("num")));
                    preparedStatement.setInt(7, Integer.parseInt(request.queryParams("id_rating")));
                    preparedStatement.executeUpdate();

                    response.status(201);
                } catch (SQLException | NumberFormatException e) {

                    response.status(400);

                    return StatusResponse.error;
                }

                return StatusResponse.success;
            } else {

                response.status(204);

                return StatusResponse.no_content;
            }
        } else {

            response.status(401);

            return StatusResponse.error;
        }
    }

    /**
     * Вносит изменения в аудитории.
     * Используется таблица "room"
     *
     * @param connection
     * @return
     */
    public static String putRoom(Connection connection, Request request, Response response){

        if (request.queryParams("key").equals(HerokuAPI.key)) {

            if (request.queryParams("id_room") != null &&
                    request.queryParams("name") != null) {

                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(PUTStatement.putRoom());

                    preparedStatement.setString(1, request.queryParams("name"));
                    preparedStatement.setString(2, request.queryParams("name"));
                    preparedStatement.setInt(3, Integer.parseInt(request.queryParams("id_room")));
                    preparedStatement.executeUpdate();

                    response.status(201);
                } catch (SQLException | NumberFormatException e) {

                    response.status(400);

                    return StatusResponse.error;
                }

                return StatusResponse.success;
            } else {

                response.status(204);

                return StatusResponse.no_content;
            }
        } else {

            response.status(401);

            return StatusResponse.error;
        }
    }

    /**
     * Вносит изменения в расписании.
     * Используется таблица "schedule"
     *
     * @param connection
     * @return
     */
    public static String putSchedule(Connection connection, Request request, Response response){

        if (request.queryParams("key").equals(HerokuAPI.key)) {

            if (request.queryParams("id_schedule") != null &&
                    request.queryParams("d") != null ||
                    request.queryParams("num") != null ||
                    request.queryParams("id_schedule_subject") != null ||
                    request.queryParams("id_group") != null ||
                    request.queryParams("id_account") != null) {

                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(PUTStatement.putSchedule());

                    preparedStatement.setInt(1, Integer.parseInt(request.queryParams("d")));
                    preparedStatement.setInt(2, Integer.parseInt(request.queryParams("d")));
                    preparedStatement.setInt(3, Integer.parseInt(request.queryParams("num")));
                    preparedStatement.setInt(4, Integer.parseInt(request.queryParams("num")));
                    preparedStatement.setInt(5, Integer.parseInt(request.queryParams("id_schedule_subject")));
                    preparedStatement.setInt(6, Integer.parseInt(request.queryParams("id_schedule_subject")));
                    preparedStatement.setInt(7, Integer.parseInt(request.queryParams("id_group")));
                    preparedStatement.setInt(8, Integer.parseInt(request.queryParams("id_group")));
                    preparedStatement.setInt(9, Integer.parseInt(request.queryParams("id_account")));
                    preparedStatement.setInt(10, Integer.parseInt(request.queryParams("id_account")));
                    preparedStatement.setInt(11, Integer.parseInt(request.queryParams("id_schedule")));
                    preparedStatement.executeUpdate();

                    response.status(201);
                } catch (SQLException | NumberFormatException e) {

                    response.status(400);

                    return StatusResponse.error;
                }

                return StatusResponse.success;
            } else {

                response.status(204);

                return StatusResponse.no_content;
            }
        } else {

            response.status(401);

            return StatusResponse.error;
        }
    }

    /**
     * Вносит изменения в паре.
     * Используется таблица "schedule_subject"
     *
     * @param connection
     * @return
     */
    public static String putScheduleSubject(Connection connection, Request request, Response response){

        if (request.queryParams("key").equals(HerokuAPI.key)) {

            if (request.queryParams("id_schedule_subject") != null &&
                    request.queryParams("id_list_subject") != null ||
                    request.queryParams("t") != null ||
                    request.queryParams("id_room") != null ||
                    request.queryParams("id_change") != null) {

                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(PUTStatement.putScheduleSubject());

                    preparedStatement.setInt(1, Integer.parseInt(request.queryParams("id_list_subject")));
                    preparedStatement.setInt(2, Integer.parseInt(request.queryParams("id_list_subject")));
                    preparedStatement.setInt(3, Integer.parseInt(request.queryParams("t")));
                    preparedStatement.setInt(4, Integer.parseInt(request.queryParams("t")));
                    preparedStatement.setInt(5, Integer.parseInt(request.queryParams("id_room")));
                    preparedStatement.setInt(6, Integer.parseInt(request.queryParams("id_room")));
                    preparedStatement.setInt(7, Integer.parseInt(request.queryParams("id_change")));
                    preparedStatement.setInt(8, Integer.parseInt(request.queryParams("id_change")));
                    preparedStatement.setInt(9, Integer.parseInt(request.queryParams("id_schedule_subject")));
                    preparedStatement.executeUpdate();

                    response.status(201);
                } catch (SQLException | NumberFormatException e) {

                    response.status(400);

                    return StatusResponse.error;
                }

                return StatusResponse.success;
            } else {

                response.status(204);

                return StatusResponse.no_content;
            }
        } else {

            response.status(401);

            return StatusResponse.error;
        }
    }

    /**
     * Вносит изменения в специальности.
     * Используется таблица "specialty"
     *
     * @param connection
     * @return
     */
    public static String putSpecialty(Connection connection, Request request, Response response){

        if (request.queryParams("key").equals(HerokuAPI.key)) {

            if (request.queryParams("id_specialty") != null &&
                    request.queryParams("name") != null ||
                    request.queryParams("id_faculty") != null) {

                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(PUTStatement.putSpecialty());

                    preparedStatement.setString(1, request.queryParams("name"));
                    preparedStatement.setString(2, request.queryParams("name"));
                    preparedStatement.setInt(3, Integer.parseInt(request.queryParams("id_faculty")));
                    preparedStatement.setInt(4, Integer.parseInt(request.queryParams("id_faculty")));
                    preparedStatement.setInt(5, Integer.parseInt(request.queryParams("id_specialty")));
                    preparedStatement.executeUpdate();

                    response.status(201);
                } catch (SQLException | NumberFormatException e) {

                    response.status(400);

                    return StatusResponse.error;
                }

                return StatusResponse.success;
            } else {

                response.status(204);

                return StatusResponse.no_content;
            }
        } else {

            response.status(401);

            return StatusResponse.error;
        }
    }

    /**
     * Вносит изменения в семестре.
     * Используется таблица "total"
     *
     * @param connection
     * @return
     */
    public static String putTotal(Connection connection, Request request, Response response){

        if (request.queryParams("key").equals(HerokuAPI.key)) {

            if (request.queryParams("id_total") != null &&
                    request.queryParams("id_list_subject") != null ||
                    request.queryParams("id_account") != null ||
                    request.queryParams("course") != null) {

                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(PUTStatement.putTotal());

                    preparedStatement.setInt(1, Integer.parseInt(request.queryParams("id_list_subject")));
                    preparedStatement.setInt(2, Integer.parseInt(request.queryParams("id_list_subject")));
                    preparedStatement.setInt(3, Integer.parseInt(request.queryParams("id_account")));
                    preparedStatement.setInt(4, Integer.parseInt(request.queryParams("id_account")));
                    preparedStatement.setInt(5, Integer.parseInt(request.queryParams("course")));
                    preparedStatement.setInt(6, Integer.parseInt(request.queryParams("course")));
                    preparedStatement.setInt(7, Integer.parseInt(request.queryParams("id_total")));
                    preparedStatement.executeUpdate();

                    response.status(201);
                } catch (SQLException | NumberFormatException e) {

                    response.status(400);

                    return StatusResponse.error;
                }

                return StatusResponse.success;
            } else {

                response.status(204);

                return StatusResponse.no_content;
            }
        } else {

            response.status(401);

            return StatusResponse.error;
        }
    }
}
