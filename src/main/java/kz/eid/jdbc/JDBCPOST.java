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

import static spark.Spark.*;

public class JDBCPOST {

    /**
     * Создает факультет.
     * Используется таблица "faculty"
     *
     * @param connection
     * @return
     */
    public static String postFaculty(Connection connection, Request request, Response response){

        if (request.queryParams("key").equals(HerokuAPI.key)){

            if (request.queryParams("name") != null){

                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(SQLStatement.postFaculty());

                    preparedStatement.setString(1, request.queryParams("name"));
                    preparedStatement.execute();
                } catch (SQLException e){

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
    public static String postSpecialty(Connection connection, Request request){
        return "JDBCPOST postSpecialty";
    }

    /**
     * Создает группу.
     * Используется таблица "group"
     *
     * @param connection
     * @return
     */
    public static String postGroup(Connection connection, Request request){
        return "JDBCPOST postGroup";
    }

    /**
     * Создает преподавателя.
     * Используется таблица "teacher"
     *
     * @param connection
     * @return
     */
    public static String postTeacher(Connection connection, Request request){
        return "JDBCPOST postTeacher";
    }

    /**
     * Создает предмет для расписания.
     * Используется таблица "schedule_subject"
     *
     * @param connection
     * @return
     */
    public static String postSubject(Connection connection, Request request){
        return "JDBCPOST postSubject";
    }

    /**
     * Создает предмет для расписания.
     * Используется таблица "list_subject"
     *
     * @param connection
     * @return
     */
    public static String postSubjectItem(Connection connection, Request request){
        return "JDBCPOST postSubjectItem";
    }

    /**
     * Создает расписание для группы.
     * Используется таблица "schedule"
     *
     * @param connection
     * @return
     */
    public static String postSchedule(Connection connection, Request request){
        return "JDBCPOST postSchedule";
    }

    /**
     * Создает замену для конкретного предмета в расписании группы.
     * Используется таблица "change"
     *
     * @param connection
     * @return
     */
    public static String postChange(Connection connection, Request request){
        return "JDBCPOST postChange";
    }
}
