/*
package com.example.project6;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

class DateAdapter extends BaseAdapter {

    private Context context;
    private ArrayList arrData;
    private LayoutInflater inflater;
    //private ArrayList<String> list;

    // java.util.Calendar cal = java.util.Calendar.getInstance();
    // int year = cal.get(cal.YEAR);
    // int month = cal.get(cal.MONTH) +1 ;
    // int day = cal.get(cal.DATE);

    public DateAdapter(Context c, ArrayList<CalData> arr) {
        this.context = c;
        this.arrData = arr;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return arrData.size();
    }

    @Override
    public Object getItem(int position) {
        return arrData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //ViewHolder holder = null;
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.viewitem, parent, false);
            // holder = new ViewHolder();
        }

        TextView ViewText = convertView.findViewById(R.id.ViewText);
        ViewText.setText(arrData.get(position)+ "");

        //if (arrData.get(position) == null) {
            //ViewText.setText("");
          //  ViewText.setClickable(false);
          // ViewText.setOnClickListener(null);
        //} else
        //    {
        //   ViewText.setText(arrData.get(position).getDay() + "");
        //    // 오늘 날짜 표시
        //    if (MainActivity.year == year
        //            && MainActivity.month == month
        //            && arrData.get(position).getDay() == day) {
        //        ViewText.setBackgroundColor(Color.BLACK);
        //    }
        //
            if (position % 7 == 0) {
                ViewText.setTextColor(Color.RED);
            } else if (position % 7 == 6) {
               ViewText.setTextColor(Color.BLUE);
            } else {
                ViewText.setTextColor(Color.BLACK);
            }
        //}

        return convertView;
    }
}
*/