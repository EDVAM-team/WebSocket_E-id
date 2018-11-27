package kz.osmium.translit.statement;

public class DELETEStatement {

    /**
     * Удаляет слово с таблицы `words`
     *
     * @return
     */
    public static String deleteWord() {
        return "DELETE FROM `words` WHERE `words`.`id`=?";
    }

    /**
     * Удаляет символ с таблицы `symbols`
     *
     * @return
     */
    public static String deleteSymbol() {
        return "DELETE FROM `symbols` WHERE `symbol`.`id`=?";
    }
}
