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
     * Используется таблица "accounts"
     *
     * @return
     */
    public static String putAccount(){
        return "UPDATE `accounts`\n" +
                "SET `accounts`.`type` = CASE WHEN ? IS NULL THEN `accounts`.`type` ELSE ? END,\n" +
                "\t`accounts`.`group_id` = ?,\n" +
                "\t`accounts`.`name_f` = CASE WHEN ? IS NULL THEN `accounts`.`name_f` ELSE ? END,\n" +
                "\t`accounts`.`name_l` = CASE WHEN ? IS NULL THEN `accounts`.`name_l` ELSE ? END,\n" +
                "\t`accounts`.`patronymic` = CASE WHEN ? IS NULL THEN `accounts`.`patronymic` ELSE ? END,\n" +
                "\t`accounts`.`phone` = ?,\n" +
                "\t`accounts`.`email` = ?,\n" +
                "\t`accounts`.`room_id` = ?,\n" +
                "\t`accounts`.`login` = CASE WHEN ? IS NULL THEN `accounts`.`login` ELSE ? END,\n" +
                "\t`accounts`.`pass` = CASE WHEN ? IS NULL THEN `accounts`.`pass` ELSE ? END\n" +
                "WHERE `accounts`.`id`=?";
    }
}
