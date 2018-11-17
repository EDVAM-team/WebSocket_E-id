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
        return "SELECT * FROM `specialty`";
    }

    /**
     * Запрос на вывод групп по `id_specialty`
     * Истользуется таблица "group"
     *
     * @return
     */
    public static String getGroup() {
        return "SELECT * FROM `group`";
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
        return "SELECT * FROM `curator` WHERE `curator`.`id_group`=?";
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
    public static String getScheduleStudent() {
        return "(SELECT `schedule`.`id_schedule`, `schedule`.`day`, `schedule`.`num`, `schedule_subject`.`id_schedule_subject`, `schedule_subject`.`type`, `schedule_subject`.`id_change`, `list_subject`.`id_list_subject`, `list_subject`.`name` AS \"name_list_subject\", `room`.`id_room`, `room`.`name` AS \"name_room\", `schedule`.`id_account`\n" +
                "FROM `schedule`\n" +
                "INNER JOIN `schedule_subject` ON `schedule`.`id_schedule_subject`=`schedule_subject`.`id_schedule_subject`\n" +
                "INNER JOIN `list_subject` ON `schedule_subject`.`id_list_subject`=`list_subject`.`id_list_subject`\n" +
                "LEFT JOIN `room` ON `schedule_subject`.`id_room`=`room`.`id_room`\n" +
                "WHERE `schedule_subject`.`id_change` IS NULL AND `schedule`.`id_group`=?) \n" +
                "UNION \n" +
                "(SELECT `schedule`.`id_schedule`, `schedule`.`day`, `schedule`.`num`, `schedule_subject`.`id_schedule_subject`, `change`.`type`, `change`.`id_change`, `list_subject`.`id_list_subject`, `list_subject`.`name` AS \"name_list_subject\", `room`.`id_room`, `room`.`name` AS \"name_room\", `schedule`.`id_account`\n" +
                "FROM `schedule`\n" +
                "INNER JOIN `schedule_subject` ON `schedule`.`id_schedule_subject`=`schedule_subject`.`id_schedule_subject`\n" +
                "INNER JOIN `change` ON `schedule_subject`.`id_change`=`change`.`id_change`\n" +
                "INNER JOIN `list_subject` ON `change`.`id_list_subject`=`list_subject`.`id_list_subject`\n" +
                "LEFT JOIN `room` ON `change`.`id_room`=`room`.`id_room`\n" +
                "WHERE `schedule_subject`.`id_change` IS NOT NULL AND `schedule`.`id_group`=?)";
    }

    /**
     * Запрос на вывод расписания преподавателя по `id_teacher`
     * Истользуется таблица "schedule"
     *
     * @return
     */
    public static String getScheduleTeacher() {
        return "null";
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
        return "SELECT `rating`.`id_rating`, `rating`.`num`, `list_subject`.`id_list_subject`, `list_subject`.`name` AS \"name_list_subject\", `account`.`id_account`, `account`.`name` AS \"name_account\"\n" +
                "FROM `rating` \n" +
                "INNER JOIN `list_subject` ON `rating`.`id_list_subject`=`list_subject`.`id_list_subject`\n" +
                "INNER JOIN `account` ON `rating`.`id_account`=`account`.`id_account`\n" +
                "WHERE `rating`.`id_account`=? AND `rating`.`num`=?";
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
        return "SELECT `total`.`id_total`, `total`.`course`, `list_subject`.`id_list_subject`, `list_subject`.`name`\n" +
                "FROM `total` \n" +
                "INNER JOIN `list_subject` ON `total`.`id_list_subject`=`list_subject`.`id_list_subject`\n" +
                "WHERE `id_account`=?";
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
}
