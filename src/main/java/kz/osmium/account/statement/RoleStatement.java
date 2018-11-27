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

public class RoleStatement {

    /**
     * Проверка токена на статус админа
     *
     * @return
     */
    public static String admin() {
        return "SELECT `accounts`.`id` \n" +
                "FROM `auths` \n" +
                "INNER JOIN `accounts` ON `auths`.`account_id`=`accounts`.`id` \n" +
                "WHERE `accounts`.`type`=3 AND `auths`.`token`=?";
    }

    /**
     * Проверка токена на статус преподавателя или админа
     *
     * @return
     */
    public static String teacher() {
        return "SELECT `accounts`.`id` \n" +
                "FROM `auths` \n" +
                "INNER JOIN `accounts` ON `auths`.`account_id`=`accounts`.`id` \n" +
                "WHERE (`accounts`.`type`=2 OR `accounts`.`type`=3) AND `auths`.`token`=?";
    }

    /**
     * Проверка токена на статус обычного аккаунта
     *
     * @return
     */
    public static String account() {
        return "SELECT `accounts`.`id` \n" +
                "FROM `auths` \n" +
                "INNER JOIN `accounts` ON `auths`.`account_id`=`accounts`.`id` \n" +
                "WHERE `accounts`.`id`=? AND `auths`.`token`=?";
    }

    /**
     * Проверка токена на статус обычного аккаунта или админа
     *
     * @return
     */
    public static String accountAdmin() {
        return "SELECT `accounts`.`id` \n" +
                "FROM `auths` \n" +
                "INNER JOIN `accounts` ON `auths`.`account_id`=`accounts`.`id` \n" +
                "WHERE (`accounts`.`id`=? OR `accounts`.`type`=3) AND `auths`.`token`=?";
    }
}
