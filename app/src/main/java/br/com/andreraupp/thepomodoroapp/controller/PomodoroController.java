package br.com.andreraupp.thepomodoroapp.controller;

import android.os.AsyncTask;

import java.util.List;

import br.com.andreraupp.thepomodoroapp.ThePomodoro;
import br.com.andreraupp.thepomodoroapp.model.DaoSession;
import br.com.andreraupp.thepomodoroapp.model.Pomodoro;
import br.com.andreraupp.thepomodoroapp.model.PomodoroDao;

/**
 * Created by andre on 28/07/2017.
 */

public class PomodoroController {

    private DaoSession getAppDaoSession() {
        return ThePomodoro.getContext().getDaoSession();
    }

    public void insertPomodoro(Pomodoro insertPomodoro) {
        new InsertPomodoroTask(insertPomodoro).execute();
    }

    public List<Pomodoro> getPomodoroList() {
        //return getAppDaoSession().getPomodoroDao().loadAll();
        return getAppDaoSession().getPomodoroDao().queryBuilder().orderDesc(PomodoroDao.Properties.Date).list();
    }

    private class InsertPomodoroTask extends AsyncTask<Void, Void, Void> {
        Pomodoro pomodoro;

        public InsertPomodoroTask(Pomodoro pomodoro) {
            this.pomodoro = pomodoro;
        }

        @Override
        protected Void doInBackground(Void... params) {
            getAppDaoSession().getPomodoroDao().insert(this.pomodoro);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

        }
    }
}
