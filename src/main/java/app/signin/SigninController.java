package app.signin;

import app.user.*;
import app.util.*;
import spark.*;
import java.util.*;
import static app.util.RequestUtil.*;

public class SigninController {

    public static Route serveSigninPage = (Request request, Response response) -> {
        Map<String, Object> model = new HashMap<>();
        model.put("signedOut", getQuerySignedOut(request));
        return ViewUtil.render(request, model, Path.Template.SIGNIN);
    };

    public static Route handleSigninPost = (Request request, Response response) -> {
        Map<String, Object> model = new HashMap<>();
        if (!UserController.authenticate(getQueryUsername(request), getQueryPassword(request))) {
            model.put("authenticationFailed", true);
            return ViewUtil.render(request, model, Path.Template.SIGNIN);
        }
        request.session().attribute("currentUser", getQueryUsername(request));
        if (getQuerySigninRedirect(request) != null) {
            response.redirect(getQuerySigninRedirect(request));
        }
        response.redirect(Path.Web.INDEX);
        return null;
    };

    public static Route handleSignout = (Request request, Response response) -> {
        request.session().removeAttribute("currentUser");
        response.redirect(Path.Web.SIGNIN + "?signedOut=true");
        return null;
    };

    // The origin of the request is saved so the user can be redirected back after signin
    public static void ensureUserIsLoggedIn(Request request, Response response) {
        if (request.session().attribute("currentUser") == null) {
            response.redirect(Path.Web.SIGNIN + "?signinRedirect=" + request.pathInfo());
        }
    };

}
