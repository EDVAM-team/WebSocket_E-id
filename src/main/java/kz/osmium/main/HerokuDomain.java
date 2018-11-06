package kz.osmium.main;

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
