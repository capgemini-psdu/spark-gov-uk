package app.util;

import spark.*;

public class RequestUtil {

    public static String getQueryLocale(Request request) {
        return request.queryParams("locale");
    }

    public static String getParamIsbn(Request request) {
        return request.params("isbn");
    }

    public static String getQueryUsername(Request request) {
        return request.queryParams("username");
    }

    public static String getQueryPassword(Request request) {
        return request.queryParams("password");
    }

    public static String getQuerySigninRedirect(Request request) {
        return request.queryParams("signinRedirect");
    }

    public static String getSessionLocale(Request request) {
        return request.session().attribute("locale");
    }

    public static String getSessionCurrentUser(Request request) {
        return request.session().attribute("currentUser");
    }

    public static boolean removeSessionAttrLoggedOut(Request request) {
        Object loggedOut = request.session().attribute("loggedOut");
        request.session().removeAttribute("loggedOut");
        return loggedOut != null;
    }

    public static String removeSessionAttrSigninRedirect(Request request) {
        String signinRedirect = request.session().attribute("signinRedirect");
        request.session().removeAttribute("signinRedirect");
        return signinRedirect;
    }

    public static boolean clientAcceptsHtml(Request request) {
        String accept = request.headers("Accept");
        return accept != null && accept.contains("text/html");
    }

    public static boolean clientAcceptsJson(Request request) {
        String accept = request.headers("Accept");
        return accept != null && accept.contains("application/json");
    }

}
