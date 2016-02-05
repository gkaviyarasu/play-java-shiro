package controllers;

import models.User;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import scala.Option;
import views.html.index;

/**
 * Created by kavi on 2/4/16.
 */
public class Application extends Controller {

    public Result index() {
        return ok(index.render(User.getAllUsers(), Option.apply(currentUser(session()))));
        //ok(views.html.index(User.getAllUsers(), currentUser(session())));
    }

    public Result logout() {
        User.logout();
        session().clear();
        flash("success", "You've been logged out");
        return redirect("/");
        /*Redirect("/").withNewSession.flashing(
                "success" -> "You've been logged out"
        )*/
    }

        /**
         * Retrieve the connected user email.
         */
        private String authToken(Http.Session session) {
            return session.get("email");
        }

        private User currentUser(Http.Session session) {
            return User.getUserByEmail(authToken(session));

    }
}
