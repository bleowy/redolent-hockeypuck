package expeditions;

import controllers.Application;
import play.mvc.Result;
import user.UserActivity;

/**
 * Created by patrick on 11/26/15.
 */
public class ExpeditionsRoutes  extends Application {

    public ExpeditionsRoutes(){

    }

    public Result expedition(){
        if(login.checkSession(session("connected"))){
            new UserActivity(login.parseSession(session("connected"))).checkActivity();
            html = views.html.expedition.render(user,login.parseSession(session("connected")));
            return ok(html);
        }
        return redirect("/");
    }

    public Result testExpedition(){
        user.startExp(1,1);
        return redirect("/game/expedition");
    }
}
