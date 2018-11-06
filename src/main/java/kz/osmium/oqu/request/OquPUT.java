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

package kz.osmium.oqu.request;

import java.sql.Connection;

public class OquPUT {

    /**
     * Вносит изменения в расписании группы.
     * Используется таблица "schedule"
     *
     * @param connection
     * @return
     */
    public static String putSchedule(Connection connection){
        return "OquPUT getSchedule";
    }

    /**
     * Вносит изменения в замене группы.
     * Используется таблица "change"
     *
     * @param connection
     * @return
     */
    public static String putChange(Connection connection){
        return "OquPUT getChange";
    }
}
