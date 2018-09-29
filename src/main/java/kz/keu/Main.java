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

package kz.keu;

import static spark.Spark.*;

public class Main {

    /**
     * Запускает WebSocket
     * Port и запросы настроенны именно в этом методе.
     */
    public static void main(String[] args) {

        /* Изменяет port. */
        port(getHerokuAssignedPort());

        /* GET запрос на получение статуса WebSocket'а */
        get("/", (req, res) -> "Status: Online");
    }

    /**
     * Настройка port'а
     * С heroku.com приходит ответ зарег. порта для WebSocket'а
     *
     * @return если сервер запущен на стороне heroku.com, то будет использован port от heroku.com.
     */
    private static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();

        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }

        return 4567;
    }
}

