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

package kz.osmium.main.util;

import kz.osmium.main.Main;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HerokuAPI {

    public static class Account {

        /* Ссылка на базу данных. */
        private static URI dbUri;

        static {

            try {
                dbUri = new URI(System.getenv("JAWSDB_BLACK_URL"));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }

        /**
         * Ссылка для подключения к базе данных через JDBC.
         * {@link java.sql.DriverManager#getConnection}
         * в {@link Main#main(String[])}
         */
        private static final String url = "jdbc:mysql://" + dbUri.getHost() + ":" + dbUri.getPort() + dbUri.getPath() + "?" + dbUri.getQuery();

        /* Логин для подключения к базе данных. */
        private static final String login = dbUri.getUserInfo().split(":")[0];

        /* Пароль для подключения к базе данных. */
        private static final String password = dbUri.getUserInfo().split(":")[1];

        /* Подключение к БД. */
        public static Connection getDB() throws SQLException {
            return DriverManager.getConnection(url, login, password);
        }
    }

    public static class Oqu {

        /* Ссылка на базу данных. */
        private static URI dbUri;

        static {

            try {
                dbUri = new URI(System.getenv("JAWSDB_URL"));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }

        /**
         * Ссылка для подключения к базе данных через JDBC.
         * {@link java.sql.DriverManager#getConnection}
         * в {@link Main#main(String[])}
         */
        private static final String url = "jdbc:mysql://" + dbUri.getHost() + ":" + dbUri.getPort() + dbUri.getPath() + "?" + dbUri.getQuery();

        /* Логин для подключения к базе данных. */
        private static final String login = dbUri.getUserInfo().split(":")[0];

        /* Пароль для подключения к базе данных. */
        private static final String password = dbUri.getUserInfo().split(":")[1];

        /* Подключение к БД. */
        public static Connection getDB() throws SQLException {
            return DriverManager.getConnection(url, login, password);
        }
    }

    public static class Translit {

        /* Ссылка на базу данных. */
        private static URI dbUri;

        static {

            try {
                dbUri = new URI(System.getenv("JAWSDB_GOLD_URL"));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }

        /**
         * Ссылка для подключения к базе данных через JDBC.
         * {@link java.sql.DriverManager#getConnection}
         * в {@link kz.osmium.translit.request}
         */
        private static final String url = "jdbc:mysql://" + dbUri.getHost() + ":" + dbUri.getPort() + dbUri.getPath() + "?" + dbUri.getQuery();

        /* Логин для подключения к базе данных. */
        private static final String login = dbUri.getUserInfo().split(":")[0];

        /* Пароль для подключения к базе данных. */
        private static final String password = dbUri.getUserInfo().split(":")[1];

        /* Подключение к БД. */
        public static Connection getDB() throws SQLException {
            return DriverManager.getConnection(url, login, password);
        }
    }
}
