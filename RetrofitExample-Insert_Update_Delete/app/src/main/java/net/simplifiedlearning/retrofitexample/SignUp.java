package net.simplifiedlearning.retrofitexample;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private EditText ed1, ed2, ed3, ed4;
    private Button btn1, btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ed1 = (EditText) findViewById(R.id.id);
        ed2 =(EditText) findViewById(R.id.name);
        ed3 = (EditText) findViewById(R.id.created_at);
        ed4 =(EditText) findViewById(R.id.updated_at);
        btn2 = (Button) findViewById(R.id.btnNextLogin);
        btn1 = (Button) findViewById(R.id.singup);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SignUp.this,Login.class);

                startActivity(intent);
            }
        });

        btn1.setOnClickListener(this);


    }

    private void usersignUp(){

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading");
        progressDialog.show();


        int id = Integer.parseInt(ed1.getText().toString().trim());
        String name = ed2.getText().toString().trim();
        String created_at = ed3.getText().toString().trim();
        String updated_at = ed4.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        //Hero hero = new Hero(SharedPrefManager.getmInstance(getApplicationContext()).getUser().getId());

        Hero hero = new Hero(id,name,created_at,updated_at);

        Call<Message> call = api.createUser(

                //hero.getId(),
                hero.getName(),
                hero.getCreated_at(),
                hero.getUpdated_at()
        );


        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                progressDialog.dismiss();

                Toast.makeText(getApplicationContext(),"Sucess",Toast.LENGTH_LONG).show();


//                if (!response.body().geterror()){
//
//                    finish();
//
//                   // SharedPrefManager.getmInstance(getApplicationContext()).userLogin(response.body().getHero());
//                }

            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onClick(View view){
        if (view == btn1){
            usersignUp();
        }
    }
}
