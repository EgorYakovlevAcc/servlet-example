package org.example;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class Main {
    public static void main(String[] args) throws LifecycleException {
        Tomcat app = getApp(getPort());
        app.start();
        app.getServer().await();
    }

    private static int getPort() {
        String port = System.getenv("PORT");
        if (port != null) {
            return Integer.valueOf(port);
        }
        return 8091;
    }

    public static Tomcat getApp(int port) {

        Tomcat app = new Tomcat();
        app.setPort(port);

        Context ctx = app.addWebapp("", new File("src/main/webapp").getAbsolutePath());

        app.addServlet(ctx, WelcomeServlet.class.getSimpleName(), new WelcomeServlet());
        ctx.addServletMappingDecoded("", WelcomeServlet.class.getSimpleName());

        app.addServlet(ctx, AccountServlet.class.getSimpleName(), new AccountServlet());
        ctx.addServletMappingDecoded("/accounts/*", AccountServlet.class.getSimpleName());

        app.addServlet(ctx, GoodsServlet.class.getSimpleName(), new GoodsServlet());
        ctx.addServletMappingDecoded("/goods/*", GoodsServlet.class.getSimpleName());

        return app;
    }
}
