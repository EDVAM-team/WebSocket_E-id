package kz.keu;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {

        port(getHerokuAssignedPort());

        get("/", (req, res) -> "Status: Online (200 OK)\n\n" +
                "Здесь могла быть ваша реклама :) #Vulcan #azino #ПроблемыСДжо...");
    }

    private static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();

        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }

        return 4567;
    }
}

