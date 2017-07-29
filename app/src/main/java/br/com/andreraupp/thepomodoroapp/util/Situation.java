package br.com.andreraupp.thepomodoroapp.util;

import org.greenrobot.greendao.converter.PropertyConverter;

import br.com.andreraupp.thepomodoroapp.R;
import br.com.andreraupp.thepomodoroapp.ThePomodoro;

/**
 * Created by andre on 27/07/2017.
 */

public enum Situation {
    FINISHED(ThePomodoro.getContext().getString(R.string.finished)),
    STOPPED(ThePomodoro.getContext().getString(R.string.stopped)),
    RUNNING(ThePomodoro.getContext().getString(R.string.running));

    private String situation;

    Situation(String situation) {
        this.situation = situation;
    }

    public String situation() {
        return situation;
    }

    public static class SituationConverter implements PropertyConverter<Situation, String> {
        @Override
        public Situation convertToEntityProperty(String databaseValue) {
            if (databaseValue == null) {
                return null;
            }
            for (Situation situation : Situation.values()) {
                if (situation.situation.equals(databaseValue)) {
                    return situation;
                }
            }
            return Situation.RUNNING;
        }

        @Override
        public String convertToDatabaseValue(Situation entityProperty) {
            return entityProperty == null ? null : entityProperty.situation;
        }
    }
}
