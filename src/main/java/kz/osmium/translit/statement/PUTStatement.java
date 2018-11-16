package kz.osmium.translit.statement;

public class PUTStatement {

    /**
     * Изменяет слово в таблице `word`
     *
     * @return
     */
    public static String putWord() {
        return "UPDATE `word` " +
                "SET `word`.`cyrl`= CASE WHEN ? IS NULL THEN `word`.`cyrl` ELSE ? END, " +
                "`word`.`latn`= CASE WHEN ? IS NULL THEN `word`.`latn` ELSE ? END " +
                "WHERE `word`.`id_word`=?";
    }

    /**
     * Изменяет символ в таблице `symbol`
     *
     * @return
     */
    public static String putSymbol() {
        return "UPDATE `symbol` " +
                "SET `symbol`.`cyrl`= CASE WHEN ? IS NULL THEN `symbol`.`cyrl` ELSE ? END, " +
                "`symbol`.`latn`= CASE WHEN ? IS NULL THEN `symbol`.`latn` ELSE ? END " +
                "WHERE `symbol`.`id_symbol`=?";
    }
}
