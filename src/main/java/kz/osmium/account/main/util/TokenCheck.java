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

package kz.osmium.account.main.util;

import kz.osmium.account.statement.RoleStatement;
import kz.osmium.main.util.HerokuAPI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class TokenCheck {

    /**
     * Права на уровне админа
     *
     * @param token
     * @return
     */
    public static boolean checkAdmin(HashMap<String, Connection> connection1, String token) {

        if (token != null) {

            try (Connection connection = HerokuAPI.Account.getDB()) {
                PreparedStatement preparedStatement = connection.prepareStatement(RoleStatement.admin());

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
     * Права на уровне преподавателя и админа
     *
     * @param token
     * @return
     */
    public static boolean checkTeacher(HashMap<String, Connection> connection1, String token) {

        if (token != null) {

            try (Connection connection = HerokuAPI.Account.getDB()) {
                PreparedStatement preparedStatement = connection.prepareStatement(RoleStatement.teacher());

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
     * Права на уровне обычного пользователя
     *
     * @param token
     * @return
     */
    public static boolean checkAccount(HashMap<String, Connection> connection1, String token, int idAccount) {

        if (token != null) {

            try (Connection connection = HerokuAPI.Account.getDB()) {
                PreparedStatement preparedStatement = connection.prepareStatement(RoleStatement.account());

                preparedStatement.setInt(1, idAccount);
                preparedStatement.setString(2, token);

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
     * Права на уровне обычного пользователя и админа
     *
     * @param token
     * @return
     */
    public static boolean checkAccountAdmin(HashMap<String, Connection> connection1, String token, int idAccount) {

        if (token != null) {

            try (Connection connection = HerokuAPI.Account.getDB()) {
                PreparedStatement preparedStatement = connection.prepareStatement(RoleStatement.accountAdmin());

                preparedStatement.setInt(1, idAccount);
                preparedStatement.setString(2, token);

                ResultSet resultSet = preparedStatement.executeQuery();

                return resultSet.next();
            } catch (SQLException e) {

                return false;
            }
        } else {

            return false;
        }
    }
}
