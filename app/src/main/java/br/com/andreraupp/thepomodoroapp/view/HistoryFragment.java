package br.com.andreraupp.thepomodoroapp.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import br.com.andreraupp.thepomodoroapp.R;
import br.com.andreraupp.thepomodoroapp.ThePomodoro;
import br.com.andreraupp.thepomodoroapp.controller.PomodoroController;
import br.com.andreraupp.thepomodoroapp.model.Pomodoro;

/**
 * Created by andre on 26/07/2017.
 */

public class HistoryFragment extends Fragment {
    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_history, container, false);
        listView = (ListView) rootView.findViewById(R.id.list_pomodoro);
        startAdapter();
        return rootView;
    }

    public void startAdapter() {
        PomodoroAdapter pomodoroListAdapter;
        PomodoroController pomodoroController = new PomodoroController();
        List<Pomodoro> pomodoroList = pomodoroController.getPomodoroList();

        pomodoroListAdapter = new PomodoroAdapter(pomodoroList, ThePomodoro.getContext());
        listView.setAdapter(pomodoroListAdapter);
    }
}
