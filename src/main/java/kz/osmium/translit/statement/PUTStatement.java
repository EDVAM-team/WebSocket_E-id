package kz.osmium.translit.statement;

public class PUTStatement {

    /**
     * Изменяет слово в таблице `word`
     *
     * @return
     */
    public static String putWord() {
        return "UPDATE `word` SET `word`.`latn`=? WHERE `word`.`cyrl`=?";
    }

    /**
     * Изменяет символ в таблице `symbol`
     *
     * @return
     */
    public static String putSymbol() {
        return "UPDATE `symbol` SET `symbol`.`latn`=? WHERE `symbol`.`cyrl`=?";
    }
}
