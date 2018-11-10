package kz.osmium.translit.statement;

public class PUTStatement {

    /**
     * Изменяет слово с таблицы `word`
     *
     * @return
     */
    public static String putWord(){
        return "UPDATE `word` SET `word`.`latn`=? WHERE `word`.`сyrl`=?";
    }
}
