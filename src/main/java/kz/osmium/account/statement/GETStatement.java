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

public class GETStatement {

    /**
     * Запрос на авторизацию приложения.
     * Истользуется таблица "accounts"
     *
     * @return
     */
    public static String getAccount() {
        return "SELECT * FROM `accounts` WHERE `accounts`.login=? AND `accounts`.pass=?";
    }

    /**
     * Запрос на создание токена.
     * Истользуется таблица "auths"
     *
     * @return
     */
    public static String addToken() {
        return "INSERT INTO `auths` " +
                "(`token`, `account_id`) " +
                "VALUE (?, ?)";
    }


    /**
     * Запрос на вывод конкретного аккаунта по `id`
     * Истользуется таблица "accounts"
     *
     * @return
     */
    public static String getAccountID() {
        return "SELECT * FROM `accounts` WHERE `id`=?";
    }

    /**
     * Запрос на вывод всех преподавателей
     * Истользуется таблица "accounts"
     *
     * @return
     */
    public static String getTeacherAll() {
        return "SELECT * FROM `accounts` WHERE `accounts`.type=2";
    }
}
