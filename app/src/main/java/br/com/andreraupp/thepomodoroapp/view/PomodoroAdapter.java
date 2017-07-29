package br.com.andreraupp.thepomodoroapp.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import br.com.andreraupp.thepomodoroapp.R;
import br.com.andreraupp.thepomodoroapp.ThePomodoro;
import br.com.andreraupp.thepomodoroapp.model.Pomodoro;

/**
 * Created by andre on 28/07/2017.
 */

public class PomodoroAdapter extends BaseAdapter {

    private List<Pomodoro> pomodoroList;
    private Context context;

    public PomodoroAdapter(List<Pomodoro> pomodoroList, Context context) {
        this.pomodoroList = pomodoroList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.pomodoroList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.pomodoroList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        PomodoroHolder viewHolder = new PomodoroHolder();
        Pomodoro pomodoro = this.pomodoroList.get(position);

        if (view == null) {
            view = LayoutInflater.from(this.context).inflate(R.layout.list_pomodoro, null);
            viewHolder.time = (TextView)view.findViewById(R.id.time);
            viewHolder.sitation = (TextView) view.findViewById(R.id.situation);
            viewHolder.afterDate = (TextView) view.findViewById(R.id.after_date);
            viewHolder.afterTime = (TextView) view.findViewById(R.id.after_time);
            view.setTag(viewHolder);
        } else {
            viewHolder = (PomodoroHolder) view.getTag();
        }

        viewHolder.time.setText(pomodoro.getTimer());
        viewHolder.sitation.setText(pomodoro.getSituation().situation());
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        viewHolder.afterTime.setText(formatter.format(pomodoro.getDate()));
        formatter = new SimpleDateFormat(ThePomodoro.getContext().getString(R.string.date_format));
        viewHolder.afterDate.setText(formatter.format(pomodoro.getDate()));

        return view;
    }

    static class PomodoroHolder {
        TextView time;
        TextView sitation;
        TextView afterTime;
        TextView afterDate;
    }
}
