package br.com.andreraupp.thepomodoroapp.model;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Keep;

import java.util.Date;

import br.com.andreraupp.thepomodoroapp.util.Situation;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by andre on 27/07/2017.
 */

@Entity
public class Pomodoro {
    private String timer;
    private Date date;
    @Convert(converter = Situation.SituationConverter.class, columnType = String.class)
    private Situation situation;

    @Keep
    public Pomodoro(String timer, Date date, Situation situation) {
        this.timer = timer;
        this.date = date;
        this.situation = situation;
    }

    @Keep
    public Pomodoro() {
    }

    public String getTimer() {
        return timer;
    }

    public void setTimer(String timer) {
        this.timer = timer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Situation getSituation() {
        return this.situation;
    }

    public void setSituation(Situation situation) {
        this.situation = situation;
    }
}
