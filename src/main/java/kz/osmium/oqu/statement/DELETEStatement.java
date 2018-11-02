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

public class DELETEStatement {

    /**
     * Запрос на удаление факультета.
     * Истользуется таблица "faculty"
     *
     * @return
     */
    public static String deleteFaculty() {
        return "DELETE FROM `faculty` WHERE `id_faculty`=?";
    }

    /**
     * Запрос на удаление специальности.
     * Истользуется таблица "specialty"
     *
     * @return
     */
    public static String deleteSpecialty() {
        return "DELETE FROM `specialty` WHERE `id_specialty`=?";
    }

    /**
     * Запрос на удаление группы.
     * Истользуется таблица "group"
     *
     * @return
     */
    public static String deleteGroup() {
        return "DELETE FROM `group` WHERE `id_group`=?";
    }

    /**
     * Запрос на удаление предмета.
     * Истользуется таблица "list_subject"
     *
     * @return
     */
    public static String deleteSubject() {
        return "DELETE FROM `list_subject` WHERE `id_list_subject`=?";
    }

    /**
     * Запрос на удаление аккаунт.
     * Истользуется таблица "account"
     *
     * @return
     */
    public static String deleteAccount() {
        return "DELETE FROM `account` WHERE `id_account`=?";
    }

    /**
     * Запрос на удаление замены.
     * Истользуется таблица "change"
     *
     * @return
     */
    public static String deleteChange() {
        return "DELETE FROM `change` WHERE `id_change`=?";
    }

    /**
     * Запрос на удаление кабинета.
     * Истользуется таблица "room"
     *
     * @return
     */
    public static String deleteRoom() {
        return "DELETE FROM `room` WHERE `id_room`=?";
    }

    /**
     * Запрос на удаление куратора.
     * Истользуется таблица "curator"
     *
     * @return
     */
    public static String deleteCurator() {
        return "DELETE FROM `curator` WHERE `id_group`=?";
    }
}
