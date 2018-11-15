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

package kz.osmium.account.statement;

public class PUTStatement {

    /**
     * Вносит изменения в аккаунте.
     * Используется таблица "account"
     *
     * @return
     */
    public static String putAccount(){
        return "UPDATE `account`\n" +
                "SET `account`.`type` = CASE WHEN ? IS NULL THEN `account`.`type` ELSE ? END,\n" +
                "\t`account`.`id_group` = ?,\n" +
                "\t`account`.`f_name` = CASE WHEN ? IS NULL THEN `account`.`f_name` ELSE ? END,\n" +
                "\t`account`.`l_name` = CASE WHEN ? IS NULL THEN `account`.`l_name` ELSE ? END,\n" +
                "\t`account`.`patronymic` = CASE WHEN ? IS NULL THEN `account`.`patronymic` ELSE ? END,\n" +
                "\t`account`.`phone` = ?,\n" +
                "\t`account`.`email` = ?,\n" +
                "\t`account`.`id_room` = ?,\n" +
                "\t`account`.`login` = CASE WHEN ? IS NULL THEN `account`.`login` ELSE ? END,\n" +
                "\t`account`.`pass` = CASE WHEN ? IS NULL THEN `account`.`pass` ELSE ? END\n" +
                "WHERE `account`.`id_account`=?";
    }
}
