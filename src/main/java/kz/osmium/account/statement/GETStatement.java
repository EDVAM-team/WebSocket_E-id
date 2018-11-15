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
     * Истользуется таблица "account"
     *
     * @return
     */
    public static String getAccount() {
        return "SELECT * FROM `account` WHERE `account`.login=? AND `account`.pass=?";
    }

    /**
     * Запрос на создание токена.
     * Истользуется таблица "auth"
     *
     * @return
     */
    public static String addToken() {
        return "INSERT INTO `auth` " +
                "(`token`, `id_account`) " +
                "VALUE (?, ?)";
    }
}
