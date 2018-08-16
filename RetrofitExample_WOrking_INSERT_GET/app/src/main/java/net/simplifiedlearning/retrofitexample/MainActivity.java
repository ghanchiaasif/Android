package net.simplifiedlearning.retrofitexample;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.provider.SyncStateContract;
import android.renderscript.RenderScript;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.multidex.MultiDexApplication;

import net.simplifiedlearning.retrofitexample.Api;
import net.simplifiedlearning.retrofitexample.Hero;
import net.simplifiedlearning.retrofitexample.R;
import net.simplifiedlearning.retrofitexample.SignUp;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends Activity {

    ListView listView;
    Button btnNext;
    //TextView txtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.ls);
        //listView.setOnClickListener((View.OnClickListener) this);
        btnNext = (Button) findViewById(R.id.btnNext);
        //txtView = findViewById(R.id.txt);

        //calling the method to display the heroes
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent next = new Intent(MainActivity.this,SignUp.class);
                startActivity(next);
            }
        });

        getHeroes();
    }

    public void getHeroes() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<Hero>> call = api.getHeroes();


        call.enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {
                final List<Hero> heroList = response.body();

                final String[] heros = new String[heroList.size()];
                final String[] heros1 = new String [heroList.size()];

                for (int i = 0; i < heroList.size(); i++) {



                        heros[i] = "Id :  " + heroList.get(i).getId() + "\n" + "Name :  " + heroList.get(i).getName() + "\n" + "Created_Date :  " + heroList.get(i).getCreated_at() + "\n" +  "Updated_date :  " + heroList.get(i).getUpdated_at() ;

                    }


                listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, heros));
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long l) {



                            //long listid =

                            String templist = heroList.get(position).getId().toString();
                            String templist1 = heroList.get(position).getName().toString();
                            String templist2 = heroList.get(position).getCreated_at().toString();
                            String templist3 = heroList.get(position).getUpdated_at().toString();

                            Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                            intent.putExtra("list",templist);
                            intent.putExtra("list1",templist1);
                            intent.putExtra("list2",templist2);
                            intent.putExtra("list3",templist3);
                            startActivity(intent);

                        }
                    });

            }

            @Override
            public void onFailure(Call<List<Hero>> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();

            }
        });


    }

//        call.enqueue(new Callback<List<Hero>>() {
//            @Override
//            public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {
//                List<Hero> heroList = response.body();
//
//                //Creating an String array for the ListView
//                final String[] heroes = new String[heroList.size()];
//
//                //looping through all the heroes and inserting the names inside the string array
//                for (int i = 0; i < heroList.size(); i++) {
//                    heroes[i] = "ID :"+heroList.get(i).getId() + "\n" +"name :"+heroList.get(i).getName()+"\n" +"Created date" + heroList.get(i).getCreated_at()+"\n" +"update date :" +heroList.get(i).getUpdated_at();
//                    //heroes[i] = heroList.get(i).getTeam();
//                    //heroes[i]= heroList.get(i).getBio();
//
//                }
//
//                Toast.makeText(MainActivity.this, "sucess", Toast.LENGTH_SHORT).show();
//
//                listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, heroes));
//
//
//
////
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Hero>> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
//            }
//
//        });




//    public void onItemClick(AdapterView<?> 1, View v, int position, long id){
//
//        Intent intent = new Intent();
//        intent.setClass(this,Button.class);
//        intent.putExtra("id",id);
//        startActivity(intent);
//    }


}
