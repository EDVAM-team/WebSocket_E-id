/*
 * Copyright 2018 KEUGuide
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

package kz.keu.jdbc;

import kz.keu.utils.HerokuKey;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCPUT {

    /* Подключается к базе данных. */
    private Connection connection;

    /* Служит для выполнения запросов к базе данных. */
    private PreparedStatement preparedStatement;

    public JDBCPUT() {

        try {
            connection = DriverManager.getConnection(HerokuKey.url, HerokuKey.login, HerokuKey.password);
        } catch (SQLException e) {

            System.out.println("Error SQL Connecting");
        }
    }
}
