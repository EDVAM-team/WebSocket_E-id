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

public class HerokuDomain {

    /**
     * Проверка запроса на доменное имя www.osmium.kz
     *
     * @param host - Название домена на который был произведен запрос
     * @return - Если доменное имя www.osmium.kz, то вернется TRUE
     */
    public static boolean getDomainMain(String host){
        return host.equals("www.osmium.kz");
    }

    /**
     * Проверка запроса на доменное имя account.osmium.kz
     *
     * @param host - Название домена на который был произведен запрос
     * @return - Если доменное имя account.osmium.kz, то вернется TRUE
     */
    public static boolean getDomainAccount(String host){
        return host.equals("account.osmium.kz");
    }

    /**
     * Проверка запроса на доменное имя oqu.osmium.kz
     *
     * @param host - Название домена на который был произведен запрос
     * @return - Если доменное имя oqu.osmium.kz, то вернется TRUE
     */
    public static boolean getDomainOqu(String host){
        return host.equals("oqu.osmium.kz");
    }

    /**
     * Проверка запроса на доменное имя translit.osmium.kz
     *
     * @param host - Название домена на который был произведен запрос
     * @return - Если доменное имя translit.osmium.kz, то вернется TRUE
     */
    public static boolean getDomainTranslit(String host){
        return host.equals("translit.osmium.kz");
    }
}
