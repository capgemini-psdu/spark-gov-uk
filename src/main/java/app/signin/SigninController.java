package app.signin;

import app.user.*;
import app.util.*;
import spark.*;
import java.util.*;
import static app.util.RequestUtil.*;

public class SigninController {

    public static Route serveSigninPage = (Request request, Response response) -> {
        Map<String, Object> model = new HashMap<>();
        model.put("loggedOut", removeSessionAttrLoggedOut(request));
        model.put("signinRedirect", removeSessionAttrSigninRedirect(request));
        return ViewUtil.render(request, model, Path.Template.SIGNIN);
    };

    public static Route handleSigninPost = (Request request, Response response) -> {
        Map<String, Object> model = new HashMap<>();
        if (!UserController.authenticate(getQueryUsername(request), getQueryPassword(request))) {
            model.put("authenticationFailed", true);
            return ViewUtil.render(request, model, Path.Template.SIGNIN);
        }
        model.put("authenticationSucceeded", true);
        request.session().attribute("currentUser", getQueryUsername(request));
        if (getQuerySigninRedirect(request) != null) {
            response.redirect(getQuerySigninRedirect(request));
        }
        response.redirect(Path.Web.INDEX);
        return null;
    };

    public static Route handleSignout = (Request request, Response response) -> {
        request.session().removeAttribute("currentUser");
        request.session().attribute("loggedOut", true);
        response.redirect(Path.Web.SIGNIN);
        return null;
    };

    // The origin of the request (request.pathInfo()) is saved in the session so
    // the user can be redirected back after signin
    public static void ensureUserIsLoggedIn(Request request, Response response) {
        if (request.session().attribute("currentUser") == null) {
            request.session().attribute("signinRedirect", request.pathInfo());
            response.redirect(Path.Web.SIGNIN);
        }
    };

}
