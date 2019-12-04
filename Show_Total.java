package com.example.calendartask;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Show_Total extends AppCompatActivity {

    float pay= (float) 60.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__total);

        ListView lv=findViewById(R.id.lv2);
        DBHelper db = new DBHelper(this);

        ArrayAdapter<String> aa;

        /*String[] s=new String[c.getCount()];
        int i=0;
        if(c.moveToFirst()){
            do {
                s[i]=c.getString(c.getColumnIndex("date_of_entry"))+"    "+c.getString(c.getColumnIndex("time_of_entry"))+"\n"+c.getFloat(c.getColumnIndex("quantity"))
                        +"\n"+c.getString(c.getColumnIndex("remarks"));
                i++;
            }while (c.moveToNext());

        }*/

        String[] month=new String[13];
        int k=1;
        month[0]="";
        for(int i = 1; i<13; i++){
            String s="";
            if(i<10){
                s=s+"0";
            }
            s=s+""+i+"";
            float cost=0;
            Cursor c=db.getLike(s);

            if(c.moveToFirst()){
                do{
                    float q=c.getFloat(c.getColumnIndex("quantity"));
                    cost = cost + (pay * q);
                }while(c.moveToNext());
            }
            if(cost!=0)
                month[k]="Cost for month "+s+" is  ===>>>  "+cost;
            else
                month[k]="Not added anything";
            k++;
        }

        aa=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,month);
        lv.setAdapter(aa);
    }
}
