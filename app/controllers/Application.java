package controllers;

import game.Game;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;
import play.twirl.api.Content;
import user.Login;
import user.UserActivity;

public class Application extends Controller{
 public Game game = new Game();
 Login login = new Login(game.connect);
 User user = new User();
 Content html;
 
    public Result index(Boolean loginFailed){
    if(loginFailed == true){
      html = views.html.index.render(true);
     }else{
      html = views.html.index.render(false);
     }
        return ok(html);
    }

    public Result game(){
     if(login.checkSession(session("connected"))){
         new UserActivity(login.parseSession(session("connected"))).checkActivity();
      html = views.html.game.render(user,login.parseSession(session("connected")));
      return ok(html);
     }
     return redirect("/");
    }
    
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
            session().remove("connected");
        }
        return redirect("/");
    }

}
