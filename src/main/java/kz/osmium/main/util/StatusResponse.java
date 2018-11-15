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

public class StatusResponse {

    /* Статус JSON успешной операции. CODE 200 */
    public static final String SUCCESS = "{\"status\":\"SUCCESS\"}";

    /* Статус JSON ошибочной операции. CODE 404 */
    public static final String ERROR = "{\"status\":\"ERROR\"}";

    /* Статус JSON конфликтной операции. CODE 409 */
    public static final String CONFLICT = "{\"status\":\"CONFLICT\"}";

    /* Статус JSON серверной ошибки операции. CODE 500 */
    public static final String INTERNAL_SERVER_ERROR = "{\"status\":\"INTERNAL SERVER ERROR\"}";

    /* Статус JSON когда нет содержимого. CODE 204 */
    public static final String NO_CONTENT = "{\"status\":\"NO CONTENT\"}";
}
