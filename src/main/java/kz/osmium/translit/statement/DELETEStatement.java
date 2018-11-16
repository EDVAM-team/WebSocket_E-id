package kz.osmium.translit.statement;

public class DELETEStatement {

    /**
     * Удаляет слово с таблицы `word`
     *
     * @return
     */
    public static String deleteWord() {
        return "DELETE FROM `word` WHERE `word`.`id_word`=?";
    }

    /**
     * Удаляет символ с таблицы `symbol`
     *
     * @return
     */
    public static String deleteSymbol() {
        return "DELETE FROM `symbol` WHERE `symbol`.`id_symbol`=?";
    }
}
