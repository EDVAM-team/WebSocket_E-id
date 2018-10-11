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
import kz.eid.utils.SQLStatement;
import spark.Request;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class JDBCGET {

    /**
     * Отправка ключа для редактирования
     *
     * @return возвращает список факультетов в JSON.
     */
    public static String getAuth(Request request){
        return "JDBCGET getAuth";
    }

    /**
     * Получает информацию с таблицы "faculty"
     *
     * @param connection
     * @return возвращает список факультетов в JSON.
     */
    public static String getFaculty(Connection connection) throws SQLException {
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        ResultSet resultSet = connection.prepareStatement(SQLStatement.getFaculty()).executeQuery();

        while (resultSet.next()){
            HashMap<String, String> hash = new HashMap<>();

            hash.put("id_faculty", resultSet.getString("id_faculty"));
            hash.put("name", resultSet.getString("name"));
            list.add(hash);
        }

        return new Gson().toJson(list);
    }

    /**
     * Получает информацию с таблицы "specialty"
     *
     * @param connection
     * @return возвращает список конкретных специальностей в JSON.
     */
    public static String getSpecialty(Connection connection, Request request){
        return "JDBCGET getSpecialty";
    }

    /**
     * Получает информацию с таблицы "group"
     *
     * @param connection
     * @return возвращает конкретную группу в JSON.
     */
    public static String getGroup(Connection connection, Request request){
        return "JDBCGET getGroup";
    }

    /**
     * Обрабатывает информацию так, чтобы JSON отправлял ответ
     * расписания на всю неделю для ученика. Еще есть информация про замены.
     *
     * @param connection
     * @return возвращает полную информацию расписания группы в JSON.
     */
    public static String getSchedule(Connection connection, Request request){
        return "JDBCGET getSchedule";
    }

    /**
     * Обрабатывает информацию так, чтобы JSON отправлял ответ
     * расписания на всю неделю для преподавателя. Еще есть информация про замены.
     *
     * @param connection
     * @return возвращает полную информацию расписания группы в JSON.
     */
    public static String getScheduleTeacher(Connection connection, Request request){
        return "JDBCGET getScheduleTeacher";
    }

    /**
     * Получает информацию с таблицы "teacher"
     *
     * @param connection
     * @return возвращает конкретного преподователя в JSON.
     */
    public static String getTeacher(Connection connection, Request request){
        return "JDBCGET getTeacher";
    }

    /**
     * Получает информацию с таблицы "teacher"
     *
     * @param connection
     * @return возвращает всех преподавателей в JSON.
     */
    public static String getAll(Connection connection){
        return "JDBCGET getTeacherAll";
    }

    /**
     * Получает информацию с таблицы "list_subject"
     *
     * @param connection
     * @return возвращает весь список предметов в JSON.
     */
    public static String getList(Connection connection){
        return "JDBCGET getListAll";
    }
}
