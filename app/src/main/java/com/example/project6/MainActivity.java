package com.example.project6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<CalData> arrData;
   // ArrayList<String> list;
    Calendar mCalToday;
    Calendar mCal;
    GridView mGridView;
    DateAdapter adapter;

    Date date = new Date();
    int thisYear = date.getYear();
    int thisMonth = date.getMonth();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCalToday = Calendar.getInstance();
        mCal = Calendar.getInstance();

        //thisYear = mCal.get(Calendar.YEAR);
        //thisMonth = mCal.get(Calendar.MONTH) + 1;
        setCalendarDate(thisYear, thisMonth);  //처음에 년/달 출력

        ImageButton pre = findViewById(R.id.pre); //이전 달
        ImageButton next = findViewById(R.id.next); //다음 달

        pre.setOnClickListener(this);
        next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) { //버튼 누를시 달 이동
        switch (v.getId()){
            case R.id.pre:
                thisMonth --;
                if (thisMonth == -1) // 1월은 0이니까 0에서 또 전으로 돌아가서 -1이 되면
                {
                    thisMonth = 11; // 12월로 세팅
                    thisYear--;
                }
                setCalendarDate(thisYear,thisMonth);
                break;

            case R.id.next:
                thisMonth ++;
                if (thisMonth == 12) // 12월이 11인데 또 올라가서 13이 되면
                {
                    thisMonth = 0; // 1월로 세팅
                    thisYear++;
                }
                setCalendarDate(thisYear,thisMonth);
                break;

            default:
                break;
        }
    }


    // 양회윤 바보... 안드로이드 너무 힘들다...
    private void setCalendarDate(int year, int month) {

        arrData = new ArrayList<CalData>();

        Date date = new Date();
        date.setYear(year);
        date.setMonth(month);
        date.setDate(1);

        TextView today = findViewById(R.id.today); //현재텍스트
        today.setText((year + 1900) + "." + (month + 1 )); // 년/월 출력


        mCalToday.set((year + 1900), month, 1); //1일에 맞는 요일 세팅

        int startDay = mCalToday.get(Calendar.DAY_OF_WEEK);
        if (startDay != 1) {                    //시작 요일이 일요일이 아니면 그만큼 공백
            for (int i =0; i < startDay-1; i++) {
                arrData.add(null);
            }
        }

        mCal.set(Calendar.MONTH, month); //요일은 +1해야되서 달력에 요일을 세팅할 때 -1을 해줌
        for (int i = 0; i < mCal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            mCalToday.set(mCal.get(Calendar.YEAR), month, (i + 1));
            arrData.add(new CalData((i+1), mCalToday.get(Calendar.DAY_OF_WEEK)));
        }

        adapter = new DateAdapter(this,arrData);
        mGridView = findViewById(R.id.gridview);
        mGridView.setAdapter(adapter);

    }
}



class DateAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<CalData> arrData;
    private LayoutInflater inflater;


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
        /*
        if (arrData.get(position) == null)
        {
            return null;
        }
        else
        {
            return arrData.get(position).getDay();
        }
        */

        // 위에꺼랑 똑같은 소스코드
        // 조건 ? true 일때 : false 일때
        return arrData.get(position) == null ? "" : arrData.get(position).getDay();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.viewitem, parent, false);
            holder = new ViewHolder();
            holder.Viewitem = (TextView)convertView.findViewById(R.id.viewitem);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.Viewitem.setText("" + getItem(position));


        TextView ViewText = convertView.findViewById(R.id.viewitem);

        if (position % 7 == 0) {
            ViewText.setTextColor(Color.RED);
        } else if (position % 7 == 6) {
            ViewText.setTextColor(Color.BLUE);
        } else {
            ViewText.setTextColor(Color.BLACK);
        }

        return convertView;
    }

    private class ViewHolder {
        TextView Viewitem;
    }
}

