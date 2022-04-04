package com.example.prac6;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.Calendar;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    CharSequence[] items = {"Android", "Security", "Cloud"};
    boolean[] itemsChecked = new boolean[items.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickDialog(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("This is a dialog with a simple text");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener()
        { @Override public void onClick(DialogInterface dialog, int id)
        { Toast.makeText(getBaseContext(), "OK CLicked", Toast.LENGTH_SHORT).show();
        }});

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(getBaseContext(), "Cancel CLicked", Toast.LENGTH_SHORT).show();
        }});

        builder.setMultiChoiceItems(items, itemsChecked, new DialogInterface.OnMultiChoiceClickListener() {
            @Override public void onClick(DialogInterface dialog, int id, boolean isChecked) {
            Toast.makeText(getBaseContext(), items[id] + (isChecked ? "checked!" : "unchecked!"), Toast.LENGTH_SHORT).show();
        }});
        AlertDialog dialog = builder.create(); builder.show();
    }
    public void onClickProgressDialog(View v)
    {
        final ProgressDialog pDialog=new ProgressDialog(this);
        pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pDialog.setMessage("Loading...............");
        pDialog.incrementProgressBy(20);
        pDialog.setButton(Dialog.BUTTON_POSITIVE, "Stop Progress", new
                DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    { pDialog.dismiss();
                    }
                });
        pDialog.show();;
    }

    public void onClickDateDialog(View v)
    {
        final int myear,mmonth,mday;
        final TextView DateDisplay=(TextView)findViewById(R.id.textview_date);
        DatePickerDialog dateDialog;
        Calendar c=Calendar.getInstance();
        myear=c.get(Calendar.YEAR);
        mmonth=c.get(Calendar.MONTH);
        mday=c.get(Calendar.DAY_OF_MONTH);
        final DatePickerDialog.OnDateSetListener mDateSetListener=new
                DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth){ DateDisplay.setText(new StringBuilder().append("Date:").append(month+1).append("-").append(dayOfMonth).append(("-")).append((year)));
                    }
                };
        dateDialog=new DatePickerDialog(this,mDateSetListener,myear,mmonth,mday);dateDialog.show();
    }

    public void onClickTimeDialog(View v)
    {
        final int mhour,mminute;
        final TextView TimeDisplay=(TextView)findViewById(R.id.textview_time);
        TimePickerDialog timeDialog;
        Calendar c=Calendar.getInstance();
        mhour=c.get(Calendar.HOUR_OF_DAY);
        mminute=c.get(Calendar.MINUTE);
        TimePickerDialog.OnTimeSetListener mTimeSetListener=new
                TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute){ TimeDisplay.setText(new
                            StringBuilder().append("Time:").append(hourOfDay).append(":").append((minute)));}
                };
        timeDialog = new TimePickerDialog(this,mTimeSetListener,mhour,mminute,true);timeDialog.show();
    }
}
