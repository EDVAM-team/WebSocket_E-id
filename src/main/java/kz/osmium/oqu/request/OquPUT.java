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

import java.sql.*;

public class OquPUT {

    /**
     * Вносит изменения в аккаунте.
     * Используется таблица "account"
     *
     * @param connection
     * @return
     */
    public static String putAccount(Connection connection, Request request, Response response) {

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

                    if (request.queryParams("t") != null)
                        preparedStatement.setInt(1, Integer.parseInt(request.queryParams("t")));
                    else
                        preparedStatement.setNull(1, Types.INTEGER);

                    if (request.queryParams("id_group") != null)
                        preparedStatement.setInt(2, Integer.parseInt(request.queryParams("id_group")));
                    else
                        preparedStatement.setNull(2, Types.INTEGER);

                    if (request.queryParams("name") != null) {
                        preparedStatement.setString(3, request.queryParams("name"));
                        preparedStatement.setString(4, request.queryParams("name"));
                    } else {
                        preparedStatement.setNull(3, Types.VARCHAR);
                        preparedStatement.setNull(4, Types.VARCHAR);
                    }

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

                    if (request.queryParams("login") != null) {
                        preparedStatement.setString(10, request.queryParams("login"));
                        preparedStatement.setString(11, request.queryParams("login"));
                    } else {
                        preparedStatement.setNull(10, Types.VARCHAR);
                        preparedStatement.setNull(11, Types.VARCHAR);
                    }

                    if (request.queryParams("pass") != null) {
                        preparedStatement.setString(12, request.queryParams("pass"));
                        preparedStatement.setString(13, request.queryParams("pass"));
                    } else {
                        preparedStatement.setNull(12, Types.VARCHAR);
                        preparedStatement.setNull(13, Types.VARCHAR);
                    }

                    preparedStatement.setInt(14, Integer.parseInt(request.queryParams("id_account")));
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
    public static String putChange(Connection connection, Request request, Response response) {

        if (request.queryParams("key").equals(HerokuAPI.key)) {

            if (request.queryParams("id_change") != null &&
                    request.queryParams("id_list_subject") != null ||
                    request.queryParams("t") != null ||
                    request.queryParams("id_account") != null ||
                    request.queryParams("id_room") != null) {

                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(PUTStatement.putChange());

                    if (request.queryParams("id_list_subject") != null) {
                        preparedStatement.setInt(1, Integer.parseInt(request.queryParams("id_list_subject")));
                        preparedStatement.setInt(2, Integer.parseInt(request.queryParams("id_list_subject")));
                    } else {
                        preparedStatement.setNull(1, Types.INTEGER);
                        preparedStatement.setNull(2, Types.INTEGER);
                    }

                    if (request.queryParams("t") != null) {
                        preparedStatement.setInt(3, Integer.parseInt(request.queryParams("t")));
                        preparedStatement.setInt(4, Integer.parseInt(request.queryParams("t")));
                    } else {
                        preparedStatement.setNull(3, Types.INTEGER);
                        preparedStatement.setNull(4, Types.INTEGER);
                    }

                    if (request.queryParams("id_account") != null) {
                        preparedStatement.setInt(5, Integer.parseInt(request.queryParams("id_account")));
                        preparedStatement.setInt(6, Integer.parseInt(request.queryParams("id_account")));
                    } else {
                        preparedStatement.setNull(5, Types.INTEGER);
                        preparedStatement.setNull(6, Types.INTEGER);
                    }

                    if (request.queryParams("id_room") != null)
                        preparedStatement.setInt(7, Integer.parseInt(request.queryParams("id_room")));
                    else
                        preparedStatement.setNull(7, Types.INTEGER);

                    preparedStatement.setInt(8, Integer.parseInt(request.queryParams("id_change")));
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
    public static String putCurator(Connection connection, Request request, Response response) {

        if (request.queryParams("key").equals(HerokuAPI.key)) {

            if (request.queryParams("id_group") != null &&
                    request.queryParams("id_account") != null) {

                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(PUTStatement.putCurator());

                    if (request.queryParams("id_account") != null) {
                        preparedStatement.setInt(1, Integer.parseInt(request.queryParams("id_account")));
                        preparedStatement.setInt(2, Integer.parseInt(request.queryParams("id_account")));
                    } else {
                        preparedStatement.setNull(1, Types.INTEGER);
                        preparedStatement.setNull(2, Types.INTEGER);
                    }

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
    public static String putFaculty(Connection connection, Request request, Response response) {

        if (request.queryParams("key").equals(HerokuAPI.key)) {

            if (request.queryParams("id_faculty") != null &&
                    request.queryParams("name") != null) {

                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(PUTStatement.putFaculty());

                    if (request.queryParams("name") != null) {
                        preparedStatement.setString(1, request.queryParams("name"));
                        preparedStatement.setString(2, request.queryParams("name"));
                    } else {
                        preparedStatement.setNull(1, Types.VARCHAR);
                        preparedStatement.setNull(2, Types.VARCHAR);
                    }

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
    public static String putGroup(Connection connection, Request request, Response response) {

        if (request.queryParams("key").equals(HerokuAPI.key)) {

            if (request.queryParams("id_group") != null &&
                    request.queryParams("name") != null ||
                    request.queryParams("id_specialty") != null) {

                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(PUTStatement.putGroup());

                    if (request.queryParams("name") != null) {
                        preparedStatement.setString(1, request.queryParams("name"));
                        preparedStatement.setString(2, request.queryParams("name"));
                    } else {
                        preparedStatement.setNull(1, Types.VARCHAR);
                        preparedStatement.setNull(2, Types.VARCHAR);
                    }

                    if (request.queryParams("id_specialty") != null) {
                        preparedStatement.setInt(3, Integer.parseInt(request.queryParams("id_specialty")));
                        preparedStatement.setInt(4, Integer.parseInt(request.queryParams("id_specialty")));
                    } else {
                        preparedStatement.setNull(3, Types.INTEGER);
                        preparedStatement.setNull(4, Types.INTEGER);
                    }

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
    public static String putListSubject(Connection connection, Request request, Response response) {

        if (request.queryParams("key").equals(HerokuAPI.key)) {

            if (request.queryParams("id_list_subject") != null &&
                    request.queryParams("name") != null) {

                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(PUTStatement.putListSubject());

                    if (request.queryParams("name") != null) {
                        preparedStatement.setString(1, request.queryParams("name"));
                        preparedStatement.setString(2, request.queryParams("name"));
                    } else {
                        preparedStatement.setNull(1, Types.VARCHAR);
                        preparedStatement.setNull(2, Types.VARCHAR);
                    }

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
    public static String putMark(Connection connection, Request request, Response response) {

        if (request.queryParams("key").equals(HerokuAPI.key)) {

            if (request.queryParams("id_mark") != null &&
                    request.queryParams("id_rating") != null ||
                    request.queryParams("n") != null ||
                    request.queryParams("mark") != null) {

                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(PUTStatement.putMark());

                    if (request.queryParams("id_rating") != null) {
                        preparedStatement.setInt(1, Integer.parseInt(request.queryParams("id_rating")));
                        preparedStatement.setInt(2, Integer.parseInt(request.queryParams("id_rating")));
                    } else {
                        preparedStatement.setNull(1, Types.INTEGER);
                        preparedStatement.setNull(2, Types.INTEGER);
                    }

                    if (request.queryParams("n") != null) {
                        preparedStatement.setInt(3, Integer.parseInt(request.queryParams("n")));
                        preparedStatement.setInt(4, Integer.parseInt(request.queryParams("n")));
                    } else {
                        preparedStatement.setNull(3, Types.INTEGER);
                        preparedStatement.setNull(4, Types.INTEGER);
                    }


                    if (request.queryParams("mark") != null)
                        preparedStatement.setInt(5, Integer.parseInt(request.queryParams("mark")));
                    else
                        preparedStatement.setNull(5, Types.INTEGER);

                    preparedStatement.setInt(6, Integer.parseInt(request.queryParams("id_mark")));
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
    public static String putRating(Connection connection, Request request, Response response) {

        if (request.queryParams("key").equals(HerokuAPI.key)) {

            if (request.queryParams("id_rating") != null &&
                    request.queryParams("id_list_subject") != null ||
                    request.queryParams("id_account") != null ||
                    request.queryParams("num") != null) {

                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(PUTStatement.putRating());
                    if (request.queryParams("id_list_subject") != null) {
                        preparedStatement.setInt(1, Integer.parseInt(request.queryParams("id_list_subject")));
                        preparedStatement.setInt(2, Integer.parseInt(request.queryParams("id_list_subject")));
                    } else {
                        preparedStatement.setNull(1, Types.INTEGER);
                        preparedStatement.setNull(2, Types.INTEGER);
                    }

                    if (request.queryParams("id_account") != null) {
                        preparedStatement.setInt(3, Integer.parseInt(request.queryParams("id_account")));
                        preparedStatement.setInt(4, Integer.parseInt(request.queryParams("id_account")));
                    } else {
                        preparedStatement.setNull(3, Types.INTEGER);
                        preparedStatement.setNull(4, Types.INTEGER);
                    }

                    if (request.queryParams("num") != null) {
                        preparedStatement.setInt(5, Integer.parseInt(request.queryParams("num")));
                        preparedStatement.setInt(6, Integer.parseInt(request.queryParams("num")));
                    } else {
                        preparedStatement.setNull(5, Types.INTEGER);
                        preparedStatement.setNull(6, Types.INTEGER);
                    }

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
    public static String putRoom(Connection connection, Request request, Response response) {

        if (request.queryParams("key").equals(HerokuAPI.key)) {

            if (request.queryParams("id_room") != null &&
                    request.queryParams("name") != null) {

                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(PUTStatement.putRoom());

                    if (request.queryParams("name") != null) {
                        preparedStatement.setString(1, request.queryParams("name"));
                        preparedStatement.setString(2, request.queryParams("name"));
                    } else {
                        preparedStatement.setNull(1, Types.VARCHAR);
                        preparedStatement.setNull(2, Types.VARCHAR);
                    }

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
    public static String putSchedule(Connection connection, Request request, Response response) {

        if (request.queryParams("key").equals(HerokuAPI.key)) {

            if (request.queryParams("id_schedule") != null &&
                    request.queryParams("d") != null ||
                    request.queryParams("num") != null ||
                    request.queryParams("id_schedule_subject") != null ||
                    request.queryParams("id_group") != null ||
                    request.queryParams("id_account") != null) {

                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(PUTStatement.putSchedule());

                    if (request.queryParams("d") != null) {
                        preparedStatement.setInt(1, Integer.parseInt(request.queryParams("d")));
                        preparedStatement.setInt(2, Integer.parseInt(request.queryParams("d")));
                    } else {
                        preparedStatement.setNull(1, Types.INTEGER);
                        preparedStatement.setNull(2, Types.INTEGER);
                    }

                    if (request.queryParams("num") != null) {
                        preparedStatement.setInt(3, Types.INTEGER);
                        preparedStatement.setInt(4, Types.INTEGER);
                    } else {
                        preparedStatement.setNull(3, Types.INTEGER);
                        preparedStatement.setNull(4, Types.INTEGER);
                    }

                    if (request.queryParams("id_schedule_subject") != null) {
                        preparedStatement.setInt(5, Integer.parseInt(request.queryParams("id_schedule_subject")));
                        preparedStatement.setInt(6, Integer.parseInt(request.queryParams("id_schedule_subject")));
                    } else {
                        preparedStatement.setNull(5, Types.INTEGER);
                        preparedStatement.setNull(6, Types.INTEGER);
                    }

                    if (request.queryParams("id_group") != null) {
                        preparedStatement.setInt(7, Integer.parseInt(request.queryParams("id_group")));
                        preparedStatement.setInt(8, Integer.parseInt(request.queryParams("id_group")));
                    } else {
                        preparedStatement.setNull(7, Types.INTEGER);
                        preparedStatement.setNull(8, Types.INTEGER);
                    }

                    if (request.queryParams("id_account") != null) {
                        preparedStatement.setInt(9, Integer.parseInt(request.queryParams("id_account")));
                        preparedStatement.setInt(10, Integer.parseInt(request.queryParams("id_account")));
                    } else {
                        preparedStatement.setNull(9, Types.INTEGER);
                        preparedStatement.setNull(10, Types.INTEGER);
                    }

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
    public static String putScheduleSubject(Connection connection, Request request, Response response) {

        if (request.queryParams("key").equals(HerokuAPI.key)) {

            if (request.queryParams("id_schedule_subject") != null &&
                    request.queryParams("id_list_subject") != null ||
                    request.queryParams("t") != null ||
                    request.queryParams("id_room") != null ||
                    request.queryParams("id_change") != null) {

                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(PUTStatement.putScheduleSubject());

                    if (request.queryParams("id_list_subject") != null) {
                        preparedStatement.setInt(1, Integer.parseInt(request.queryParams("id_list_subject")));
                        preparedStatement.setInt(2, Integer.parseInt(request.queryParams("id_list_subject")));
                    } else {
                        preparedStatement.setNull(1, Types.INTEGER);
                        preparedStatement.setNull(2, Types.INTEGER);
                    }

                    if (request.queryParams("t") != null)
                        preparedStatement.setInt(3, Integer.parseInt(request.queryParams("t")));
                    else
                        preparedStatement.setNull(3, Types.INTEGER);

                    if (request.queryParams("id_room") != null)
                        preparedStatement.setInt(4, Integer.parseInt(request.queryParams("id_room")));
                    else
                        preparedStatement.setNull(4, Types.INTEGER);

                    if (request.queryParams("id_change") != null)
                        preparedStatement.setInt(5, Integer.parseInt(request.queryParams("id_change")));
                    else
                        preparedStatement.setNull(5, Types.INTEGER);

                    preparedStatement.setInt(6, Integer.parseInt(request.queryParams("id_schedule_subject")));
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
    public static String putSpecialty(Connection connection, Request request, Response response) {

        if (request.queryParams("key").equals(HerokuAPI.key)) {

            if (request.queryParams("id_specialty") != null &&
                    request.queryParams("name") != null ||
                    request.queryParams("id_faculty") != null) {

                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(PUTStatement.putSpecialty());

                    if (request.queryParams("name") != null) {
                        preparedStatement.setString(1, request.queryParams("name"));
                        preparedStatement.setString(2, request.queryParams("name"));
                    } else {
                        preparedStatement.setNull(1, Types.VARCHAR);
                        preparedStatement.setNull(2, Types.VARCHAR);
                    }

                    if (request.queryParams("id_faculty") != null) {
                        preparedStatement.setInt(3, Integer.parseInt(request.queryParams("id_faculty")));
                        preparedStatement.setInt(4, Integer.parseInt(request.queryParams("id_faculty")));
                    } else {
                        preparedStatement.setNull(3, Types.INTEGER);
                        preparedStatement.setNull(4, Types.INTEGER);
                    }

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
    public static String putTotal(Connection connection, Request request, Response response) {

        if (request.queryParams("key").equals(HerokuAPI.key)) {

            if (request.queryParams("id_total") != null &&
                    request.queryParams("id_list_subject") != null ||
                    request.queryParams("id_account") != null ||
                    request.queryParams("course") != null) {

                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(PUTStatement.putTotal());

                    if (request.queryParams("id_list_subject") != null) {
                        preparedStatement.setInt(1, Integer.parseInt(request.queryParams("id_list_subject")));
                        preparedStatement.setInt(2, Integer.parseInt(request.queryParams("id_list_subject")));
                    } else {
                        preparedStatement.setNull(1, Types.INTEGER);
                        preparedStatement.setNull(2, Types.INTEGER);
                    }

                    if (request.queryParams("id_account") != null) {
                        preparedStatement.setInt(3, Integer.parseInt(request.queryParams("id_account")));
                        preparedStatement.setInt(4, Integer.parseInt(request.queryParams("id_account")));
                    } else {
                        preparedStatement.setNull(3, Types.INTEGER);
                        preparedStatement.setNull(4, Types.INTEGER);
                    }

                    if (request.queryParams("course") != null) {
                        preparedStatement.setInt(5, Integer.parseInt(request.queryParams("course")));
                        preparedStatement.setInt(6, Integer.parseInt(request.queryParams("course")));
                    } else {
                        preparedStatement.setNull(5, Types.INTEGER);
                        preparedStatement.setNull(6, Types.INTEGER);
                    }

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
