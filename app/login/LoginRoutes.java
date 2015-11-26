package login;

import controllers.Application;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Result;
import user.UserActivity;

/**
 * Created by patrick on 11/26/15.
 */
public class LoginRoutes extends Application {

    public Result loginFailed(){
        return index(true);
    }

    public Result login(){
        DynamicForm dynamicForm = Form.form().bindFromRequest();
        int id = login.login(dynamicForm.get("login"), dynamicForm.get("password"));
        if(id > 0){
            session("connected",Integer.toString(id));

            return redirect("/game");
        }
        return redirect("/user/loginFailed");
    }

    public Result logout(){
        if(login.checkSession(session("connected"))) {
            new UserActivity(login.parseSession(session("connected"))).deleteUserActivity();
            session().remove("connected");
        }
        return redirect("/");
    }
}
