package br.com.andreraupp.thepomodoroapp;

import android.app.Application;
import android.content.Context;

import org.greenrobot.greendao.database.Database;

import br.com.andreraupp.thepomodoroapp.model.DaoMaster;
import br.com.andreraupp.thepomodoroapp.model.DaoSession;

/**
 * Created by andre on 28/07/2017.
 */

public class ThePomodoro extends Application {
    private static Context context;
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        ThePomodoro.context = getApplicationContext();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "pomodoro-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public static ThePomodoro getContext() {
        return (ThePomodoro) ThePomodoro.context;
    }
}
