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

public class StatusResponse {

    /* Статус JSON успешной операции */
    public static final String success = "{\"status\":\"SUCCESS\"}";

    /* Статус JSON ошибочной операции */
    public static final String error = "{\"status\":\"ERROR\"}";

    /* Статус JSON конфликтной операции */
    public static final String conflict = "{\"status\":\"CONFLICT\"}";

    /* Статус JSON когда нет содержимого */
    public static final String no_content = "{\"status\":\"NO CONTENT\"}";
}
