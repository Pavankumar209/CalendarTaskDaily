package com.example.calendartask;

import android.database.Cursor;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list2);


        Spinner sp=findViewById(R.id.sp);
        String[] months= new String[]{"JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"};




        ArrayAdapter<String> aa2;
        aa2=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,months);
        aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(aa2);
        sp.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                ListView lv=findViewById(R.id.lv);
                ArrayAdapter<String> aa;
                DBHelper db = new DBHelper(getApplicationContext());
                String mon="";
                pos++;
                if(pos<=9){
                    mon="0";
                }
                mon=mon+pos;
                Cursor c=db.getLike(mon);
                String[] s=new String[c.getCount()];
                int i=0;
                if(c.moveToFirst()){
                    do {
                        s[i]=c.getString(c.getColumnIndex("date_of_entry"))+"    "+c.getString(c.getColumnIndex("time_of_entry"))+"\n"+c.getFloat(c.getColumnIndex("quantity"))
                                +"\n"+c.getString(c.getColumnIndex("remarks"));
                        i++;
                    }while (c.moveToNext());

                }

                aa=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,s);
                lv.setAdapter(aa);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });


    }

}