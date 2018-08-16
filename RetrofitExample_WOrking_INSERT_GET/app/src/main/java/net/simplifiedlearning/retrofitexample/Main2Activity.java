package net.simplifiedlearning.retrofitexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class Main2Activity extends AppCompatActivity {
    TextView txt;
    EditText ed1, ed2, ed3, ed4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        txt = (TextView) findViewById(R.id.txt);
        ed1 = (EditText) findViewById(R.id.update_id);
        ed2 = (EditText) findViewById(R.id.update_name);
        ed3 = (EditText) findViewById(R.id.update_createddate);
        ed4 = (EditText) findViewById(R.id.Update_upddate);

        String tempholder = getIntent().getStringExtra("list") ;
        String tempholder1 =  getIntent().getStringExtra("list1");
        String tempholder2 = getIntent().getStringExtra("list2");
        String tempholder3 = getIntent().getStringExtra("list3");
        ed1.setText(tempholder);
        ed2.setText(tempholder1);
        ed3.setText(tempholder2);
        ed4.setText(tempholder3);

        //txt.setText(tempholder);


    }
}
