package hyerim.my.videoproject;

import android.app.Application;
import hyerim.my.videoproject.dbmanager.DBManager;

public class MyApplication extends Application {
    private static MyApplication instance;
    public DBManager dbManager;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        dbManager = new DBManager(this, "database.db", null, 1);
    }

    public static MyApplication getInstance(){return instance;}
}
