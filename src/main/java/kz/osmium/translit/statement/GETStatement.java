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

package kz.osmium.translit.statement;

public class GETStatement {

    /**
     * Выводит все слова с таблицы `word`
     *
     * @return
     */
    public static String getWordAll() {
        return "SELECT * FROM `word`";
    }

    /**
     * Выводит все символы с таблицы `symbol`
     *
     * @return
     */
    public static String getSymbolAll() {
        return "SELECT * FROM `symbol`";
    }

    /**
     * Выводит слово с таблицы `word`
     *
     * @return
     */
    public static String getWord() {
        return "SELECT * FROM `word` WHERE `word`.`cyrl`=? OR `word`.`latn`=?";
    }

    /**
     * Выводит символ с таблицы `symbol`
     *
     * @return
     */
    public static String getSymbol() {
        return "SELECT * FROM `symbol` WHERE `symbol`.`cyrl`=? OR `symbol`.`latn`=?";
    }
}
