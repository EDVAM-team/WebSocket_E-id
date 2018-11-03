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

public class POSTStatement {

    /**
     * Запрос на создание ячейки данных в таблице `faculty`
     *
     * @return
     */
    public static String postFaculty() {
        return "INSERT INTO `faculty` " +
                "(`name`) " +
                "VALUE (?)";
    }

    /**
     * Запрос на создание ячейки данных в таблице `specialty`
     *
     * @return
     */
    public static String postSpecialty() {
        return "INSERT INTO `specialty` " +
                "(`name`, `id_faculty`) " +
                "VALUE (?, ?)";
    }

    /**
     * Запрос на создание ячейки данных в таблице `group`
     *
     * @return
     */
    public static String postGroup() {
        return "INSERT INTO `group` " +
                "(`name`, `id_specialty`) " +
                "VALUE (?, ?)";
    }

    /**
     * Запрос на создание ячейки данных в таблице `search`
     *
     * @return
     */
    public static String postCurator() {
        return "INSERT INTO `curator` " +
                "(`id_group`, `id_account`) " +
                "VALUE (?, ?)";
    }

    /**
     * Запрос на создание ячейки данных в таблице `account`
     *
     * @return
     */
    public static String postTeacher() {
        return "INSERT INTO `account` " +
                "(`name`, `t`, `login`, `pass`, `s_name`, `l_name`, `phone`, `email`, `id_room`) " +
                "VALUE (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    /**
     * Запрос на создание ячейки данных в таблице `account`
     *
     * @return
     */
    public static String postStudent() {
        return "INSERT INTO `account` " +
                "(`name`, `t`, `login`, `pass`, `s_name`, `l_name`, `phone`, `email`, `id_group`) " +
                "VALUE (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    /**
     * Запрос на создание ячейки данных в таблице `room`
     *
     * @return
     */
    public static String postRoom() {
        return "INSERT INTO `room` " +
                "(`name`) " +
                "VALUE (?)";
    }

    /**
     * Запрос на создание ячейки данных в таблице `rating`
     *
     * @return
     */
    public static String postRating() {
        return "INSERT INTO `rating` " +
                "(`id_subject`, `id_account`, `num`) " +
                "VALUE (?, ?, ?)";
    }

    /**
     * Запрос на создание ячейки данных в таблице `mark`
     *
     * @return
     */
    public static String postMark() {
        return "INSERT INTO `mark` " +
                "(`id_rating`, `n`, `mark`) " +
                "VALUE (?, ?, ?)";
    }

    /**
     * Запрос на создание ячейки данных в таблице `schedule_subject`
     *
     * @return
     */
    public static String postSubject() {
        return "INSERT INTO `schedule_subject` " +
                "(`id_list_subject`, `type`, `room`, `id_change`) " +
                "VALUE (?, ?, ?, ?)";
    }

    /**
     * Запрос на создание ячейки данных в таблице `schedule`
     *
     * @return
     */
    public static String postSchedule() {
        return "INSERT INTO `schedule` " +
                "(`d`, `num`, `id_schedule_subject`, `id_group`, `id_account`) " +
                "VALUE (?, ?, ?, ?, ?)";
    }

    /**
     * Запрос на создание ячейки данных в таблице `change`
     *
     * @return
     */
    public static String postChange() {
        return "INSERT INTO `change` " +
                "(`id_list_subject`, `type`, `id_account`, `room`) " +
                "VALUE (?, ?, ?, ?)";
    }

    /**
     * Запрос на создание ячейки данных в таблице `list_subject`
     *
     * @return
     */
    public static String postItemSubject() {
        return "INSERT INTO `list_subject` " +
                "(`name`) " +
                "VALUE (?)";
    }
}
