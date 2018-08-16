package net.simplifiedlearning.retrofitexample;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.app.PendingIntent.getActivity;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    TextView txt;
    EditText ed1, ed2, ed3, ed4;
    Button btn1, btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        txt = (TextView) findViewById(R.id.txt);
        ed1 = (EditText) findViewById(R.id.update_id);
        ed2 = (EditText) findViewById(R.id.update_name);
        ed3 = (EditText) findViewById(R.id.update_createddate);
        ed4 = (EditText) findViewById(R.id.Update_upddate);
        btn1 = (Button) findViewById(R.id.api_update);
        btn2 = (Button) findViewById(R.id.api_delete);

        Hero hero = SharedPrefManager.getmInstance(Main2Activity.this).getUser();

        final String tempholder = getIntent().getStringExtra("list");
        String tempholder1 =  getIntent().getStringExtra("list1");
        String tempholder2 = getIntent().getStringExtra("list2");
        String tempholder3 = getIntent().getStringExtra("list3");
        //String tempholder6 = extras.getString("user_name");
        ed1.setText(tempholder);
        ed2.setText(tempholder1);
        ed3.setText(tempholder2);
        ed4.setText(tempholder3);

        //txt.setText(tempholder);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);

//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                deletestate(Integer.parseInt(String.valueOf(ed1)));
//
//            }
//        });


    }


    private void updateUser(){

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating...");
        progressDialog.show();

        int id = Integer.parseInt(ed1.getText().toString().trim());
        String name = ed2.getText().toString().trim();
        String updated_at = ed3.getText().toString().trim();
        String created_at = ed4.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api service = retrofit.create(Api.class);

        //Hero user = new Hero(SharedPrefManager.getmInstance(getApplicationContext()).getUser().getId(), name,  updated_at);
        Hero usre = new Hero(id,name,updated_at,created_at);


    Call<Message> call = service.updateUser(
            usre.getId(),
            usre.getName(),
            usre.getCreated_at(),
            usre.getUpdated_at()
    );
        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Sucess",Toast.LENGTH_LONG).show();
//               if (!response.body().geterror()) {
//                   SharedPrefManager.getmInstance(getApplicationContext()).userLogin(response.body().getHero());
//
//               }
//               else  {
//
//                   Toast.makeText(Main2Activity.this, "Failed", Toast.LENGTH_SHORT).show();
//               }

            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"fail", Toast.LENGTH_SHORT).show();

            }
        });



    }


    public void deletestate(){

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Deleting...");
        progressDialog.show();

       int id = Integer.parseInt(ed1.getText().toString().trim());
//        String name = ed2.getText().toString();
//        String updateUser = ed3.getText().toString();
//        String createdat = ed4.getText().toString();

        Hero hero = new Hero(id);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        Api service = retrofit.create(Api.class);

        Call<Message> call = service.deletestate(
                hero.getId()
        );

        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    Toast.makeText(Main2Activity.this, "Deletd", Toast.LENGTH_SHORT).show();
                }
//                if (response.isSuccessful()){
//
//                    Toast.makeText(Main2Activity.this,"Delete Sucessfully",Toast.LENGTH_LONG).show();
//                }
//                else {
//
//                    Toast.makeText(Main2Activity.this, "failed", Toast.LENGTH_SHORT).show();
//                }
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(Main2Activity.this, "Failed", Toast.LENGTH_SHORT).show();

                Log.e("erro",t.getMessage());

            }
        });
    }

    @Override
    public void onClick(View view) {

        if (view == btn1){

            updateUser();
        }
        else if (view == btn2) {

            deletestate();

        }


    }
}
