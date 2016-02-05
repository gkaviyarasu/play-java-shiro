import models.User;
import play.Application;
import play.GlobalSettings;

/**
 * Created by kavi on 2/5/16.
 */
public class Global extends GlobalSettings {
    @Override
    public void onStart(Application application) {
        super.onStart(application);
        insert();
    }

    /**
     * Initial set of data to be imported
     * in the sample application.
     */
    private void insert() {

        if (User.getAllUsers().isEmpty()) {
            new User("admin@example.com", "admin").register();
        }
    }
}
