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

package kz.osmium.main;

import java.net.URI;
import java.net.URISyntaxException;

public class HerokuAPI {

    /* Ссылка на базу данных. */
    private static URI dbUri;

    /* Пароль для авторизации. */
    public static String pass;

    /* Ключ для POST запросов. */
    public static String key;

    static {

        try {
            dbUri = new URI(System.getenv("JAWSDB_URL"));
            pass = System.getenv("PASSWORD_AUTH");
            key = System.getenv("KEY_AUTH");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * Ссылка для подключения к базе данных через JDBC.
     * {@link java.sql.DriverManager#getConnection}
     * в {@link Main#main(String[])}
     */
    public static final String url = "jdbc:mysql://" + dbUri.getHost() + dbUri.getPath();

    /* Логин для подключения к базе данных. */
    public static final String login = dbUri.getUserInfo().split(":")[0];

    /* Пароль для подключения к базе данных. */
    public static final String password = dbUri.getUserInfo().split(":")[1];
}
