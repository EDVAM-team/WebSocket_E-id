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

package kz.eid.utils;

public class SQLStatement {

    /**
     * Запрос на вывод всех факультетов.
     * Истользуется таблица "faculty"
     *
     * @return
     */
    public static String getFaculty(){
        return "SELECT * FROM `faculty`";
    }

    /**
     * Запрос на вывод специальностей по `id_faculty`
     * Истользуется таблица "specialty"
     *
     * @return
     */
    public static String getSpecialty(){
        return "SELECT * FROM `specialty` WHERE `id_faculty`=?";
    }

    /**
     * Запрос на вывод групп по `id_specialty`
     * Истользуется таблица "group"
     *
     * @return
     */
    public static String getGroup(){
        return "SELECT * FROM `group` WHERE `id_specialty`=?";
    }

    /**
     * Запрос на вывод расписания группы по `id_group`
     * Истользуется таблица "schedule"
     *
     * @return
     */
    public static String getSchedule(){
        return "SELECT * FROM `schedule` WHERE `id_group`=?";
    }

    /**
     * Запрос на вывод расписания преподавателя по `id_teacher`
     * Истользуется таблица "schedule"
     *
     * @return
     */
    public static String getScheduleTeacher(){
        return "SELECT * FROM `schedule` WHERE `id_teacher`=?";
    }

    /**
     * Запрос на вывод замены преподавателя по `id_teacher`
     * Истользуется таблица "change"
     *
     * @return
     */
    public static String getChangeTeacher(){
        return "SELECT * FROM `change` WHERE `id_teacher`=?";
    }

    /**
     * Запрос на вывод замены по `id_change`
     * Истользуется таблица "change"
     *
     * @return
     */
    public static String getChange(){
        return "SELECT * FROM `change` WHERE `id_change`=?";
    }

    /**
     * Запрос на вывод расписания замены преподавателя по `id_change`
     * Истользуется таблица "schedule"
     *
     * @return
     */
    public static String getScheduleChange(){
        return "SELECT * FROM `schedule` WHERE `id_change`=?";
    }

    /**
     * Запрос на вывод предмета зарегистрированного в ячейке "дня" по `id_schedule_subject`
     * Истользуется таблица "schedule_subject"
     *
     * @return
     */
    public static String getScheduleSubject(){
        return "SELECT * FROM `schedule_subject` WHERE `id_schedule_subject`=?";
    }

    /**
     * Запрос на вывод конкретного одного преподавателя по `id_teacher`
     * Истользуется таблица "teacher"
     *
     * @return
     */
    public static String getTeacher(){
        return "SELECT * FROM `teacher` WHERE `id_teacher`=?";
    }

    /**
     * Запрос на вывод всех преподавателей
     * Истользуется таблица "teacher"
     *
     * @return
     */
    public static String getTeacherAll(){
        return "SELECT * FROM `teacher`";
    }

    /**
     * Запрос на вывод предмета из списка предметов по `id_list_subject`
     * Истользуется таблица "list_subject"
     *
     * @return
     */
    public static String getListSubject(){
        return "SELECT * FROM `list_subject` WHERE `id_list_subject`=?";
    }

    /**
     * Запрос на вывод списка всех предметов
     * Истользуется таблица "list_subject"
     *
     * @return
     */
    public static String getListSubjectAll(){
        return "SELECT * FROM `list_subject`";
    }

    /**
     * Запрос на вывод куратора группы по `id_group`
     * Истользуется таблица "search"
     *
     * @return
     */
//    public static String getSearchTeacher(){
//        return "SELECT * FROM `search` WHERE `id_group`=?";
//    }

    /**
     * Запрос на вывод групп куратора
     * Истользуется таблица "search"
     *
     * @return
     */
//    public static String getSearchGroup(){
//        return "SELECT * FROM `search` WHERE `id_teacher`=?";
//    }

    /**
     * Запрос на создание ячейки данных в таблице `faculty`
     *
     * @return
     */
    public static String postFaculty(){
        return "INSERT INTO `faculty` " +
                "(`name`) " +
                "VALUE (?)";
    }

    /**
     * Запрос на создание ячейки данных в таблице `specialty`
     *
     * @return
     */
    public static String postSpecialty(){
        return "INSERT INTO `specialty` " +
                "(`name`, `id_faculty`) " +
                "VALUE (?, ?)";
    }

    /**
     * Запрос на создание ячейки данных в таблице `group`
     *
     * @return
     */
    public static String postGroup(){
        return "INSERT INTO `group` " +
                "(`name`, `id_specialty`) " +
                "VALUE (?, ?)";
    }

    /**
     * Запрос на создание ячейки данных в таблице `teacher`
     *
     * @return
     */
    public static String postTeacher(){
        return "INSERT INTO `teacher` " +
                "(`name`, `s_name`, `l_name`, `phone`, `email`, `id_room`) " +
                "VALUE (?, ?, ?, ?, ?, ?)";
    }

    /**
     * Запрос на создание ячейки данных в таблице `schedule_subject`
     *
     * @return
     */
    public static String postSubject(){
        return "INSERT INTO `schedule_subject` " +
                "(`id_list_subject`, `type`, `room`, `id_change`) " +
                "VALUE (?, ?, ?, ?)";
    }

    /**
     * Запрос на создание ячейки данных в таблице `schedule`
     *
     * @return
     */
    public static String postSchedule(){
        return "INSERT INTO `schedule` " +
                "(`d`, `num`, `id_schedule_subject`, `id_group`, `id_teacher`) " +
                "VALUE (?, ?, ?, ?, ?)";
    }

    /**
     * Запрос на создание ячейки данных в таблице `change`
     *
     * @return
     */
    public static String postChange(){
        return "INSERT INTO `change` " +
                "(`id_list_subject`, `type`, `id_teacher`, `room`) " +
                "VALUE (?, ?, ?, ?)";
    }

    /**
     * Запрос на создание ячейки данных в таблице `list_subject`
     *
     * @return
     */
    public static String postItemSubject(){
        return "INSERT INTO `list_subject` " +
                "(`name`) " +
                "VALUE (?)";
    }
}
