package net.simplifiedlearning.retrofitexample;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.cert.CertPathValidatorException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity implements View.OnClickListener {

    EditText edid,edname;
    Button Loginbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edid = (EditText) findViewById(R.id.editemail);
        edname = (EditText) findViewById(R.id.editpassword);
        Loginbtn = (Button) findViewById(R.id.loginbtn);

        //Hero hero = SharedPrefManager.getmInstance(this).getUser();

        Loginbtn.setOnClickListener(this);
    }

    public void signIn(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Sign In...");
        progressDialog.show();

        String id = edid.getText().toString().trim();
        String name = edname.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api services = retrofit.create(Api.class);

        Call<Message> call  = services.userLogin(id,name);

        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {

                progressDialog.dismiss();

                if (!response.body().getError()){

                    finish();
                    //SharedPrefManager.getmInstance(getApplicationContext()).userLogin(response.body().getHero());

                    Toast.makeText(Login.this, "sucess", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }

                else {

                    Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {

                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

//    public void updateUser(){
//
//        final ProgressDialog progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("Updating...");
//        progressDialog.show();
//
//        String id = edid.getText().toString().trim();
//        String name = edname.getText().toString().trim();
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(Api.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        Api service = retrofit.create(Api.class);
//
//        Hero hero = SharedPrefManager.getmInstance();
//
//        Call<Message> call = service.updateUser(
//
//                hero.getId(),
//                hero.getName(),
//                hero.getUpdated_at(),
//                hero.getCreated_at()
//
//        )



//    }

    public void onClick(View view){

        if (view == Loginbtn ){

            signIn();
        }
    }
}
