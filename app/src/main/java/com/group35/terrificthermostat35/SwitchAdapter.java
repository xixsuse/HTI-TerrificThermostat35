package com.group35.terrificthermostat35;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.projectapi.thermometerapi.Switch;
import com.projectapi.thermometerapi.SwitchType;
import com.projectapi.thermometerapi.WeekProgram;

import java.util.ArrayList;

import static java.security.AccessController.getContext;

/**
 * Created by s163390 on 25-6-2017.
 */

public class SwitchAdapter extends ArrayAdapter<Switch> {

    private ArrayList<Switch> switches = new ArrayList<>();

    private Runnable afterDelete;

    public SwitchAdapter(Context context, int textViewResourceId, ArrayList<Switch> switches, Runnable afterDelete) {
        super(context, textViewResourceId, switches);
        this.switches = switches;
        this.afterDelete = afterDelete;
    }

    @Override
    public int getCount() {
        int count = 0;
        for (Switch s : switches) {
            if (s.getState())
                count++;
        }
        return count;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.switch_list_item, null);
        TextView textView = (TextView) v.findViewById(R.id.textView);
        ImageView imageView = (ImageView) v.findViewById(R.id.imageView);
        imageView.setImageResource(switches.get(position).getSwitchType() == SwitchType.DAY ? R.drawable.ic_sun : R.drawable.ic_moon);


        ImageView imageView2 = (ImageView) v.findViewById(R.id.imageView2);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switches.get(position).time = "24:00";
                afterDelete.run();
            }
        });
        textView.setText(switches.get(position).getTime());
        return v;
    }
}
