package no.ntnu.cardsnap.rest;

import io.javalin.Javalin;

public final class Application {
    /**
     * System port the application should start on.
     */
    public static final int APP_PORT = 8080;

    private Application() {
    }

    /**
     * Start the REST API Server and connect all the route handlers.
     *
     * @param args Java program arguments, unused
     */
    public static void main(final String[] args) {
        Javalin app = Javalin.create();
        app.get("/", ctx -> ctx.result("Hello world"));
        app.start(APP_PORT);
    }
}
