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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TokenCheck {

    /**
     * Права на уровне админа
     *
     * @param connection
     * @param token
     * @return
     */
    public static boolean checkAdmin(Connection connection, String token) {

        if (token != null) {

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(SQLStatement.admin());

                preparedStatement.setString(1, token);

                ResultSet resultSet = preparedStatement.executeQuery();

                return resultSet.next();
            } catch (SQLException e) {

                return false;
            }
        } else {

            return false;
        }
    }

    /**
     * Права на уровне преподавателя
     *
     * @param connection
     * @param token
     * @return
     */
    public static boolean checkTeacher(Connection connection, String token) {

        if (token != null) {

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(SQLStatement.teacher());

                preparedStatement.setString(1, token);

                ResultSet resultSet = preparedStatement.executeQuery();

                return resultSet.next();
            } catch (SQLException e) {

                return false;
            }
        } else {

            return false;
        }
    }

    private static class SQLStatement {

        /**
         * Проверка токена на статус админа
         *
         * @return
         */
        public static String admin() {
            return "SELECT * FROM `account` WHERE `account`.`t`=3 AND `account`.`token`=?";
        }

        /**
         * Проверка токена на статус преподавателя или админа
         *
         * @return
         */
        public static String teacher() {
            return "SELECT * FROM `account` WHERE (`account`.`t`=2 OR `account`.`t`=3) AND `account`.`token`=?";
        }
    }
}
