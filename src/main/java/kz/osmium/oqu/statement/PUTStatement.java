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

public class PUTStatement {

    /**
     * Вносит изменения в аккаунте.
     * Используется таблица "account"
     *
     * @return
     */
    public static String putAccount(){
        return "UPDATE `account`\n" +
                "SET `account`.`t` = CASE WHEN ? IS NULL THEN `account`.`t` ELSE 1 END,\n" +
                "\t`account`.`id_group` = CASE WHEN ? IS NULL THEN `account`.`id_group` ELSE 1 END,\n" +
                "\t`account`.`name` = CASE WHEN ? IS NULL THEN `account`.`name` ELSE 'Нет' END,\n" +
                "\t`account`.`s_name` = CASE WHEN ? IS NULL THEN `account`.`s_name` ELSE '1' END,\n" +
                "\t`account`.`l_name` = CASE WHEN ? IS NULL THEN `account`.`l_name` ELSE '1' END,\n" +
                "\t`account`.`phone` = CASE WHEN ? IS NULL THEN `account`.`phone` ELSE '1' END,\n" +
                "\t`account`.`email` = CASE WHEN ? IS NULL THEN `account`.`email` ELSE '1' END,\n" +
                "\t`account`.`id_room` = CASE WHEN ? IS NULL THEN `account`.`id_room` ELSE 1 END,\n" +
                "\t`account`.`login` = CASE WHEN ? IS NULL THEN `account`.`login` ELSE '1' END,\n" +
                "\t`account`.`pass` = CASE WHEN ? IS NULL THEN `account`.`pass` ELSE '1' END\n" +
                "WHERE `account`.`id_account`=?";
    }

    /**
     * Вносит изменения в замене.
     * Используется таблица "change"
     *
     * @return
     */
    public static String putChange(){
        return "UPDATE `change`\n" +
                "SET `change`.`id_list_subject` = CASE WHEN ? IS NULL THEN `change`.`id_list_subject` ELSE ? END,\n" +
                "\t`change`.`t` = CASE WHEN ? IS NULL THEN `change`.`t` ELSE ? END,\n" +
                "\t`change`.`id_account` = CASE WHEN ? IS NULL THEN `change`.`id_account` ELSE ? END,\n" +
                "\t`change`.`id_room` = CASE WHEN ? IS NULL THEN `change`.`id_room` ELSE ? END\n" +
                "WHERE `change`.`id_change`=?";
    }

    /**
     * Вносит изменения в кураторе.
     * Используется таблица "curator"
     *
     * @return
     */
    public static String putCurator(){
        return "UPDATE `curator`\n" +
                "SET `curator`.`id_account` = CASE WHEN ? IS NULL THEN `curator`.`id_account` ELSE ? END\n" +
                "WHERE `curator`.`id_group`=?";
    }

    /**
     * Вносит изменения в факультете.
     * Используется таблица "faculty"
     *
     * @return
     */
    public static String putFaculty(){
        return "UPDATE `faculty`\n" +
                "SET `faculty`.`name` = CASE WHEN ? IS NULL THEN `faculty`.`name` ELSE ? END\n" +
                "WHERE `faculty`.`id_faculty`=?";
    }

    /**
     * Вносит изменения в группе.
     * Используется таблица "group"
     *
     * @return
     */
    public static String putGroup(){
        return "UPDATE `group`\n" +
                "SET `group`.`name` = CASE WHEN ? IS NULL THEN `group`.`name` ELSE ? END,\n" +
                "\t`group`.`id_specialty` = CASE WHEN ? IS NULL THEN `group`.`id_specialty` ELSE ? END\n" +
                "WHERE `group`.`id_group`=?";
    }

    /**
     * Вносит изменения в списке предметов.
     * Используется таблица "list_subject"
     *
     * @return
     */
    public static String putListSubject(){
        return "UPDATE `list_subject`\n" +
                "SET `list_subject`.`name` = CASE WHEN ? IS NULL THEN `list_subject`.`name` ELSE ? END\n" +
                "WHERE `list_subject`.`id_list_subject`=?";
    }

    /**
     * Вносит изменения в оценках.
     * Используется таблица "mark"
     *
     * @return
     */
    public static String putMark(){
        return "UPDATE `mark`\n" +
                "SET `mark`.`id_rating` = CASE WHEN ? IS NULL THEN `mark`.`id_rating` ELSE ? END,\n" +
                "\t`mark`.`n` = CASE WHEN ? IS NULL THEN `mark`.`n` ELSE ? END,\n" +
                "\t`mark`.`mark` = CASE WHEN ? IS NULL THEN `mark`.`mark` ELSE ? END\n" +
                "WHERE `mark`.`id_mark`=?";
    }

    /**
     * Вносит изменения в рейтинге.
     * Используется таблица "rating"
     *
     * @return
     */
    public static String putRating(){
        return "UPDATE `rating`\n" +
                "SET `rating`.`id_list_subject` = CASE WHEN ? IS NULL THEN `rating`.`id_list_subject` ELSE ? END,\n" +
                "\t`rating`.`id_account` = CASE WHEN ? IS NULL THEN `rating`.`id_account` ELSE ? END,\n" +
                "\t`rating`.`num` = CASE WHEN ? IS NULL THEN `rating`.`num` ELSE ? END\n" +
                "WHERE `rating`.`id_rating`=?";
    }

    /**
     * Вносит изменения в аудитории.
     * Используется таблица "room"
     *
     * @return
     */
    public static String putRoom(){
        return "UPDATE `room`\n" +
                "SET `room`.`name` = CASE WHEN ? IS NULL THEN `room`.`name` ELSE ? END\n" +
                "WHERE `room`.`id_room`=?";
    }

    /**
     * Вносит изменения в расписании.
     * Используется таблица "schedule"
     *
     * @return
     */
    public static String putSchedule(){
        return "UPDATE `schedule`\n" +
                "SET `schedule`.`d` = CASE WHEN ? IS NULL THEN `schedule`.`d` ELSE ? END,\n" +
                "\t`schedule`.`num` = CASE WHEN ? IS NULL THEN `schedule`.`num` ELSE ? END,\n" +
                "\t`schedule`.`id_schedule_subject` = CASE WHEN ? IS NULL THEN `schedule`.`id_schedule_subject` ELSE ? END,\n" +
                "\t`schedule`.`id_group` = CASE WHEN ? IS NULL THEN `schedule`.`id_group` ELSE ? END,\n" +
                "\t`schedule`.`id_account` = CASE WHEN ? IS NULL THEN `schedule`.`id_account` ELSE ? END\n" +
                "WHERE `schedule`.`id_schedule`=?";
    }

    /**
     * Вносит изменения в паре.
     * Используется таблица "schedule_subject"
     *
     * @return
     */
    public static String putScheduleSubject(){
        return "UPDATE `schedule_subject`\n" +
                "SET `schedule_subject`.`id_list_subject` = CASE WHEN ? IS NULL THEN `schedule_subject`.`id_list_subject` ELSE ? END,\n" +
                "\t`schedule_subject`.`t` = CASE WHEN ? IS NULL THEN `schedule_subject`.`t` ELSE ? END,\n" +
                "\t`schedule_subject`.`id_room` = CASE WHEN ? IS NULL THEN `schedule_subject`.`id_room` ELSE ? END,\n" +
                "\t`schedule_subject`.`id_change` = CASE WHEN ? IS NULL THEN `schedule_subject`.`id_change` ELSE ? END\n" +
                "WHERE `schedule_subject`.`id_schedule_subject`=?";
    }

    /**
     * Вносит изменения в специальности.
     * Используется таблица "specialty"
     *
     * @return
     */
    public static String putSpecialty(){
        return "UPDATE `specialty`\n" +
                "SET `specialty`.`name` = CASE WHEN ? IS NULL THEN `specialty`.`name` ELSE ? END,\n" +
                "\t`specialty`.`id_faculty` = CASE WHEN ? IS NULL THEN `specialty`.`id_faculty` ELSE ? END\n" +
                "WHERE `specialty`.`id_specialty`=?";
    }

    /**
     * Вносит изменения в семестре.
     * Используется таблица "total"
     *
     * @return
     */
    public static String putTotal(){
        return "UPDATE `total`\n" +
                "SET `total`.`id_list_subject` = CASE WHEN ? IS NULL THEN `total`.`id_list_subject` ELSE ? END,\n" +
                "\t`total`.`id_account` = CASE WHEN ? IS NULL THEN `total`.`id_account` ELSE ? END,\n" +
                "\t`total`.`course` = CASE WHEN ? IS NULL THEN `total`.`course` ELSE ? END\n" +
                "WHERE `total`.`id_total`=?";
    }
}
