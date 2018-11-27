package kz.osmium.translit.statement;

public class PUTStatement {

    /**
     * Изменяет слово в таблице `words`
     *
     * @return
     */
    public static String putWord() {
        return "UPDATE `words` " +
                "SET `words`.`cyrl`= CASE WHEN ? IS NULL THEN `words`.`cyrl` ELSE ? END, " +
                "`words`.`latn`= CASE WHEN ? IS NULL THEN `words`.`latn` ELSE ? END " +
                "WHERE `words`.`id`=?";
    }

    /**
     * Изменяет символ в таблице `symbols`
     *
     * @return
     */
    public static String putSymbol() {
        return "UPDATE `symbols` " +
                "SET `symbols`.`cyrl`= CASE WHEN ? IS NULL THEN `symbols`.`cyrl` ELSE ? END, " +
                "`symbols`.`latn`= CASE WHEN ? IS NULL THEN `symbols`.`latn` ELSE ? END " +
                "WHERE `symbols`.`id`=?";
    }
}
