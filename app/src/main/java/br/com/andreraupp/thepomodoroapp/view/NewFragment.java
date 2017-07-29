package br.com.andreraupp.thepomodoroapp.view;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import br.com.andreraupp.thepomodoroapp.R;
import br.com.andreraupp.thepomodoroapp.controller.PomodoroController;
import br.com.andreraupp.thepomodoroapp.model.Pomodoro;
import br.com.andreraupp.thepomodoroapp.util.Situation;

/**
 * Created by andre on 26/07/2017.
 */

public class NewFragment extends Fragment {
    private static final long ONE_SECOND_TO_MILLESECOND = 1000;
    private static final long TIME_STANDARD = 1500000;
    private FloatingActionButton startStopButton;
    private TextView timerTextView;
    private CountDownTimer countDownTimer;
    private boolean clicked = false;
    private PomodoroController pomodoroController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_new, container, false);
        startStopButton = (FloatingActionButton) rootView.findViewById(R.id.startStopFab);
        timerTextView = (TextView)  rootView.findViewById(R.id.timerText);

        pomodoroController = new PomodoroController();

        countDownTimer = new CountDownTimer(TIME_STANDARD, ONE_SECOND_TO_MILLESECOND) {
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(setFormatedTimer(millisUntilFinished));
            }

            public void onFinish() {
                pomodoroController.insertPomodoro(new Pomodoro(setFormatedTimer(TIME_STANDARD), new Date(), Situation.FINISHED));
                timerTextView.setText(R.string.zero_time);
                startStopButton.setImageResource(android.R.drawable.ic_media_play);
                clicked = false;
            }
        };

        startStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!clicked) {
                    startStopButton.setImageResource(R.drawable.ic_stop_white);
                    countDownTimer.start();
                    clicked = true;
                } else {
                    startStopButton.setImageResource(R.drawable.ic_play_arrow_white);
                    countDownTimer.cancel();
                    clicked = false;
                    pomodoroController.insertPomodoro(new Pomodoro(timerTextView.getText().toString(), new Date(), Situation.STOPPED));
                    timerTextView.setText(R.string.zero_time);
                }
            }
        });
        return rootView;
    }

    private String setFormatedTimer(Long millesecounds) {
        return String.format(Locale.getDefault(), "%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millesecounds),
                TimeUnit.MILLISECONDS.toSeconds(millesecounds) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millesecounds)));
    }
}
