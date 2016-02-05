package controllers;

import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.register;

import javax.persistence.PersistenceException;

public class Register extends Controller {
    Form<User> loginForm = Form.form(User.class);

    public Result index() {
        return ok(register.render(loginForm));
    }

    public Result register() {
        loginForm = loginForm.bindFromRequest(request());
        User user = loginForm.get();
        try {
            if (!user.register()) {
                loginForm.reject("Provided email id already present");
            }
        } catch (PersistenceException e) {
            loginForm.reject(e.getLocalizedMessage());
        }
        if(loginForm.hasErrors()) {
            return badRequest(register.render(loginForm));
        } else {
            session("email", loginForm.get().getEmail());
            return redirect("/");
        }
    }
}
