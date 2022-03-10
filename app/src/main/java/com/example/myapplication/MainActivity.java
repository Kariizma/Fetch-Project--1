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

        //linking the textview in xml to our variable
        textViewResult = findViewById(R.id.text_view_result);

        //this handles executing the request, parsing the data through GSON, and building it
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://fetch-hiring.s3.amazonaws.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //we are creating our API and retrofit is implementing our request by itself
        HiringAPI hiringAPI = retrofit.create(HiringAPI.class);
        //retrieving the data from the getHiring method
        //the List will be populated with Hiring Data
        Call<List<Hiring>> call = hiringAPI.getHiring();

        //we are executing the get request Async (on a background thread)
        //retrofit does this automatically aswell
        call.enqueue(new Callback<List<Hiring>>() {
            @Override
            public void onResponse(Call<List<Hiring>> call, Response<List<Hiring>> response) {
                //if the response is not successful, could be a 404 error or a 403
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }
                //response is successful and we have our json data in our list
                List<Hiring> hiring = response.body();
                //using an iterator to remove the null variables (if i try to remove via list i get a concurrent error)
                Iterator<Hiring> iter = hiring.iterator();
                while(iter.hasNext())
                {
                    if(iter.next().getName() == null)
                        iter.remove();
                }
                //sorting by ListId, and if list id is equal then we sort by name
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
                //if the name is empty, don't display it on the app
                //if it is not, display all the data via TextView
                for(Hiring h: hiring)
                {
                    if(h.getName().length() == 0)
                        continue;
                    String content = "";
                    content += "ID: " + h.getId() + "\n";
                    content += "listId: " + h.getListId() + "\n";
                    content += "name: " + h.getName() + "\n";
                    content += "\n";

                    //putting the content into the text view
                    //essentially displaying it
                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Hiring>> call, Throwable t)
            {
                //returns the error in application if the data could not  be received
                textViewResult.setText(t.getMessage());
            }
        });
    }
}