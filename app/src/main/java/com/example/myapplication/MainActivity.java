package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.text_view_result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://fetch-hiring.s3.amazonaws.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HiringAPI hiringAPI = retrofit.create(HiringAPI.class);

        Call<List<Hiring>> call = hiringAPI.getHiring();

        call.enqueue(new Callback<List<Hiring>>() {
            @Override
            public void onResponse(Call<List<Hiring>> call, Response<List<Hiring>> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                List<Hiring> hiring = response.body();
                List<Hiring> secondList = hiring;
                //the proble might be that some of the items in the json are null?
                //maybe i should remove those out of the list first, and then try to sort.

                Iterator<Hiring> iter = hiring.iterator();
                while(iter.hasNext())
                {
                    if(iter.next().getName() == null)
                        iter.remove();
                }

                Collections.sort(hiring, new Comparator<Hiring>() {
                    @Override
                    public int compare(Hiring h1, Hiring h2) {
                        int a = h1.getListId() - h2.getListId();
                        if(a == 0)
                        {
                            int b = h1.getName().compareTo(h2.getName());
                            return b;
                        }
                        else
                            return a;
                    }
                });

                for(Hiring h: hiring)
                {
                    if(h.getName().length() == 0)
                        continue;
                    String content = "";
                    content += "ID: " + h.getId() + "\n";
                    content += "listId: " + h.getListId() + "\n";
                    content += "name: " + h.getName() + "\n";
                    content += "\n";

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Hiring>> call, Throwable t)
            {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}