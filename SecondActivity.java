package com.example.calendartask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    Button b1;
    Button b2;
    TextView t1;
    EditText ed1;
    EditText ed2;
    RadioButton rb1;
    RadioButton rb2;
    RadioGroup rg1;
    RadioGroup rg2;

    DBHelper db = new DBHelper(this);
    String selected_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        t1=findViewById(R.id.tv21);
        b1=findViewById(R.id.b3);
        /*b2=findViewById(R.id.b4);*/
        rg1=findViewById(R.id.rg1);
        rg2=findViewById(R.id.rg2);
        ed1=findViewById(R.id.ed1);
        ed2=findViewById(R.id.ed2);
        Intent i2=getIntent();
        Toast.makeText(getApplicationContext(),"Date selected "+i2.getStringExtra("date_selected"),Toast.LENGTH_LONG).show();
        selected_date = i2.getStringExtra("date_selected");
        Cursor c2 = db.getStatus(selected_date);
        int c=c2.getCount();
        Toast.makeText(getApplicationContext(),"Count of cursor of this "+c,Toast.LENGTH_SHORT).show();

        if(c==0 || c2==null){               ////////////////////////////////         CONDENSIBLE            //////////////for init of activity////////////////////////////
            t1.setText("You have not entered anything today");
        }
        else{

            if(c==2){
                t1.setText(selected_date+" entered as below");
                if(c2.moveToFirst()){
                    do {
                        String me=c2.getString(c2.getColumnIndex("time_of_entry"));
                        float sel=c2.getFloat(c2.getColumnIndex("quantity"));
                        if(me.equals("M")){
                            if(sel==1.00){
                                rg1.check(R.id.rb1);
                            }
                            else if(sel==0.75){
                                rg1.check(R.id.rb2);
                            }
                            else if(sel==0.50){
                                rg1.check(R.id.rb3);
                            }
                            else if(sel==0.25){
                                rg1.check(R.id.rb4);
                            }
                            else{
                                rg1.check(R.id.rb5);
                            }
                        }
                        else if(me.equals("E")) {
                            if(sel==1.00){
                                rg2.check(R.id.rb6);
                            }
                            else if(sel==0.75){
                                rg2.check(R.id.rb7);
                            }
                            else if(sel==0.50){
                                rg2.check(R.id.rb8);
                            }
                            else if(sel==0.25){
                                rg2.check(R.id.rb9);
                            }
                            else{
                                rg2.check(R.id.rb10);
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"ME value is "+me,Toast.LENGTH_SHORT).show();
                        }
                    }while (c2.moveToNext());
                }
            }
            else if(c==1){
                if(c2.moveToFirst()){
                    String me=c2.getString(c2.getColumnIndex("time_of_entry"));
                    float sel=c2.getFloat(c2.getColumnIndex("quantity"));
                    if(me.equals("M")){
                        t1.setText("Morning of "+selected_date+"is entered");
                        if(sel==1.00){
                            rg1.check(R.id.rb1);
                        }
                        else if(sel==0.75){
                            rg1.check(R.id.rb2);
                        }
                        else if(sel==0.50){
                            rg1.check(R.id.rb3);
                        }
                        else if(sel==0.25){
                            rg1.check(R.id.rb4);
                        }
                        else{
                            rg1.check(R.id.rb5);
                        }
                    }
                    else if(me.equals("E")){
                        t1.setText("Evening of "+selected_date+" is entered");
                        if(sel==1.00){
                            rg2.check(R.id.rb6);
                        }
                        else if(sel==0.75){
                            rg2.check(R.id.rb7);
                        }
                        else if(sel==0.50){
                            rg2.check(R.id.rb8);
                        }
                        else if(sel==0.25){
                            rg2.check(R.id.rb9);
                        }
                        else{
                            rg2.check(R.id.rb10);
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"lolopala theda undi",Toast.LENGTH_SHORT).show();
                    }
                }
            }
            else{
                Toast.makeText(getApplicationContext(),"theda undi count "+c,Toast.LENGTH_SHORT).show();
            }





        }
        /*b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(SecondActivity.this,MainActivity.class);
                startActivity(i);
            }
        });*/

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rg1.getCheckedRadioButtonId()!=-1 || rg2.getCheckedRadioButtonId()!=-1){
                    String sed1;
                    float q=0;
                    int done=0;
                    int id_of_sel=0;
                    if (rg1.getCheckedRadioButtonId()!=-1){
                        sed1=ed1.getText().toString();
                        id_of_sel=rg1.getCheckedRadioButtonId();
                        done=1;
                        if(id_of_sel==R.id.rb1){
                            q= (float) 1.00;
                        }
                        else if(id_of_sel==R.id.rb2){
                            q= (float) 0.75;
                        }
                        else if(id_of_sel==R.id.rb3){
                            q= (float) 0.50;
                        }
                        else if(id_of_sel==R.id.rb4){
                            q= (float) 0.25;
                        }
                        db.insert_val(selected_date,done,q,sed1);
                    }
                    if(rg2.getCheckedRadioButtonId()!=-1){
                        q=0;
                        sed1=ed2.getText().toString();
                        id_of_sel=rg2.getCheckedRadioButtonId();
                        done=2;
                        if(id_of_sel==R.id.rb6){
                            q= (float) 1.00;
                        }
                        else if(id_of_sel==R.id.rb7){
                            q= (float) 0.75;
                        }
                        else if(id_of_sel==R.id.rb8){
                            q= (float) 0.50;
                        }
                        else if(id_of_sel==R.id.rb9){
                            q= (float) 0.25;
                        }
                        db.insert_val(selected_date,done,q,sed1);
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"Please select the quantity",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
