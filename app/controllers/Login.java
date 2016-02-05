package controllers;

import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.login;

/**
 * Created by kavi on 2/4/16.
 */
public class Login extends Controller {

    Form<User> loginForm = Form.form(User.class);

    public Result index() {
        return ok(login.render(loginForm));
    }

    public Result authenticate() {
        loginForm = loginForm.bindFromRequest(request());
        String loginError = authenticate(loginForm.get());
        if(loginError != null) {
            loginForm.reject(loginError);
        }
        if(loginForm.hasErrors()) {
            return badRequest(login.render(loginForm));
        } else {
            session("email", loginForm.get().getEmail());
            return redirect("/");
        }
    }

    private String authenticate(User user) {
        if (!user.authenticate()) {
            return "Invalid Email/Password";
        }
        return null;
    }
}


    // -- Authentication

/*
val loginForm = Form(
        tuple(
                "email" -> email,
        "password" -> text
        ) verifying ("Invalid email or password", result => result match {
        case (email, password) => User.authenticate(email, password)
        })
        )

        */
/**
         * Login page.
         *//*

        def index = Action { implicit request =>
        Ok(html.login(loginForm))
        }

        */
/**
         * Handle login form submission.
         *//*

        def authenticate = Action { implicit request =>
        val result = loginForm.bindFromRequest.fold(
        formWithErrors => { BadRequest(html.login(formWithErrors)) },
        success => { Redirect(routes.Application.index).withSession("email" -> success._1) }
        )
        result.asInstanceOf[Result]
        }*/
