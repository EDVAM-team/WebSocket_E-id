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

package kz.osmium.oqu.statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GETStatement {

    /**
     * Запрос на авторизацию приложения.
     * Истользуется таблица "account"
     *
     * @return
     */
    public static String getAccount() {
        return "SELECT * FROM `account` WHERE `account`.login=? AND `account`.pass=?";
    }

    /**
     * Запрос на вывод всех факультетов.
     * Истользуется таблица "faculty"
     *
     * @return
     */
    public static String getFaculty() {
        return "SELECT * FROM `faculty`";
    }

    /**
     * Запрос на вывод специальностей по `id_faculty`
     * Истользуется таблица "specialty"
     *
     * @return
     */
    public static String getSpecialty() {
        return "SELECT * FROM `specialty` WHERE `id_faculty`=?";
    }

    /**
     * Запрос на вывод групп по `id_specialty`
     * Истользуется таблица "group"
     *
     * @return
     */
    public static String getGroup() {
        return "SELECT * FROM `group` WHERE `id_specialty`=?";
    }


    /**
     * Запрос на вывод групп по `id_group`
     * Истользуется таблица "group"
     *
     * @return
     */
    public static String getGroupID() {
        return "SELECT * FROM `group` WHERE `id_group`=?";
    }

    /**
     * Запрос на вывод кабинетов
     * Истользуется таблица "room"
     *
     * @return
     */
    public static String getRoomAll() {
        return "SELECT * FROM `room`";
    }

    /**
     * Запрос на вывод конкретного кабинета
     * Истользуется таблица "room"
     *
     * @return
     */
    public static String getRoom() {
        return "SELECT * FROM `room` WHERE `room`.id_room=?";
    }

    /**
     * Запрос на вывод куратора группе
     * Истользуется таблица "curator"
     *
     * @return
     */
    public static String getCuratorGroup() {
        return "SELECT * FROM `curator` WHERE `id_group`=?";
    }

    /**
     * Запрос на вывод групп куратору
     * Истользуется таблица "curator"
     *
     * @return
     */
    public static String getCuratorTeacher() {
        return "SELECT * FROM `curator` WHERE `id_account`=?";
    }

    /**
     * Запрос на вывод расписания группы по `id_group`
     * Истользуется таблица "schedule" и "account"
     *
     * @return
     */
    public static String getSchedule() {
        return "SELECT `schedule`.id_schedule, `schedule`.d , `schedule`.num, `schedule`.id_schedule_subject, " +
                "`schedule`.id_account, `account`.name, `account`.s_name, `account`.l_name, `account`.phone , " +
                "`account`.email, `account`.id_room " +
                "FROM `schedule`, `account` " +
                "WHERE `schedule`.id_account = `account`.id_account " +
                "AND `schedule`.id_group = ?";
    }

    /**
     * Запрос на вывод расписания преподавателя по `id_teacher`
     * Истользуется таблица "schedule"
     *
     * @return
     */
    public static String getScheduleTeacher() {
        return "SELECT `schedule`.id_schedule, `schedule`.d , `schedule`.num, `schedule`.id_schedule_subject, " +
                "`schedule`.id_group, `group`.name " +
                "FROM `schedule`, `group` " +
                "WHERE `schedule`.id_group = `group`.id_group " +
                "AND `schedule`.id_account = ?";
    }

    /**
     * Запрос на вывод замены преподавателя по `id_teacher`
     * Истользуется таблица "change"
     *
     * @return
     */
    public static String getChangeTeacher() {
        return "SELECT * FROM `change` WHERE `id_account`=?";
    }

    /**
     * Запрос на вывод замены по `id_change`
     * Истользуется таблица "change"
     *
     * @return
     */
    public static String getChange() {
        return "SELECT * FROM `change` WHERE `id_change`=?";
    }

    /**
     * Запрос на вывод расписания по `id_schedule_subject`
     * Истользуется таблица "schedule"
     *
     * @return
     */
    public static String getSubjectSchedule() {
        return "SELECT * FROM `schedule` WHERE `id_schedule_subject`=?";
    }

    /**
     * Запрос на вывод расписания замены преподавателя по `id_change`
     * Истользуется таблица "schedule"
     *
     * @return
     */
    public static String getScheduleChange() {
        return "SELECT * FROM `schedule_subject` WHERE `id_change`=?";
    }

    /**
     * Запрос на вывод предмета по `id_schedule_subject`
     * Истользуется таблица "schedule_subject"
     *
     * @return
     */
    public static String getScheduleSubject() {
        return "SELECT * FROM `schedule_subject` WHERE `id_schedule_subject`=?";
    }

    /**
     * Запрос на вывод конкретного аккаунта по `id_account`
     * Истользуется таблица "account"
     *
     * @return
     */
    public static String getAccountID() {
        return "SELECT * FROM `account` WHERE `id_account`=?";
    }

    /**
     * Запрос на вывод всех преподавателей
     * Истользуется таблица "teacher"
     *
     * @return
     */
    public static String getTeacherAll() {
        return "SELECT * FROM `account` WHERE `account`.t=2";
    }

    /**
     * Запрос на вывод предмета из списка предметов по `id_list_subject`
     * Истользуется таблица "list_subject"
     *
     * @return
     */
    public static String getListSubject() {
        return "SELECT * FROM `list_subject` WHERE `id_list_subject`=?";
    }

    /**
     * Запрос на вывод списка всех предметов
     * Истользуется таблица "list_subject"
     *
     * @return
     */
    public static String getListSubjectAll() {
        return "SELECT * FROM `list_subject`";
    }

    /**
     * Запрос на вывод рейтинга студента
     * Истользуется таблица "rating"
     *
     * @return
     */
    public static String getRatingStudent() {
        return "SELECT * FROM `rating` WHERE `id_account`=? AND `num`=?";
    }

    /**
     * Запрос на вывод текущего рейтинга
     * Истользуется таблица "rating"
     *
     * @return
     */
    public static String getRatingCurrent() {
        return "SELECT `rating`.`id_rating` FROM `rating` WHERE `id_account`=? AND `id_subject`=? ORDER BY `num` DESC LIMIT 1";
    }

    /**
     * Запрос на вывод итоговых оценок
     * Истользуется таблица "total"
     *
     * @return
     */
    public static String getTotal() {
        return "SELECT * FROM `total` WHERE `id_account`=?";
    }

    /**
     * Запрос на вывод оценок рейтинга
     * Истользуется таблица "mark"
     *
     * @return
     */
    public static String getMark() {
        return "SELECT * FROM `mark` WHERE `id_rating`=?";
    }

    /**
     * Запрос на вывод куратора группы по `i
	`id_student` INT NOT NULL, d_group`
     * Истользуется таблица "search"
     *
     * @return
     */
    public static String getSearchTeacher() {
        return "SELECT * FROM `search` WHERE `id_group`=?";
    }

    /**
     * Запрос на вывод групп куратора
     * Истользуется таблица "search"
     *
     * @return
     */
    public static String getSearchGroup() {
        return "SELECT * FROM `search` WHERE `id_account`=?";
    }

    public static ResultSet getReadDB(Connection connection, String sql, int id) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, id);

        return preparedStatement.executeQuery();
    }
}
