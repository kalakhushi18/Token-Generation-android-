package com.example.asus.tokengeneration;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class Counter_Details extends AppCompatActivity {
    private ArrayList<String> counterList ;
    private ArrayList<String> tokenList ;
    RecyclerView counterRecycler ;
    RecyclerView.Adapter counterAdapter ;
    private TextView history ;
    private TextView tokengenerate ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.counter_entry);
        counterList = new ArrayList<String>();
        tokenList = new ArrayList<String>();
        counterRecycler = (RecyclerView) findViewById(R.id.recyler_counter);
        history = findViewById(R.id.historyCounter);
        tokengenerate = findViewById(R.id.tokengenerateCounter);
        counterRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        counterAdapter= new Counter_details_Adapter(counterList,tokenList);
        counterRecycler.setAdapter(counterAdapter);

        loadValues();

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),HistoryActivity.class);
                startActivity(intent);
            }
        });

        tokengenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Registration.class);
                startActivity(intent);
            }
        });
    }




    //not sure
    private void loadValues() {
        Log.i("inside method", "come");
        JSONObject object1 = new JSONObject();
        try {
            object1.put("action", "CounterDetails");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        API.Call_Api(getApplicationContext(), "https://7449-2409-4043-194-3e12-fd30-3b63-c1d0-36ab.in.ngrok.io/files/majorBackend/MajorPralipta/api/index.php?parameter=allCounterDetails", object1, new Callback() {


            @Override
            public void response(JSONObject resp) {
                String status = resp.optString("status");
                String message = resp.optString("message");

                Log.i("Response", "Status: " + resp.optString("status"));
                Log.i("Response", "Message: " + resp.optString("message"));

                if (status.equals("200")) {
                            try {
                                JSONArray array = resp.getJSONArray("data");

                                for (int i = 0; i < array.length(); i++) {

                                    Log.i("value", array.optString(i));
                                    String val_counter = String.valueOf( array.getJSONObject(i).get("counterNumber"));
                                   String val_token = String.valueOf(array.getJSONObject(i).get("currentToken"));

                                    counterList.add(val_counter);
                                    tokenList.add(val_token);
                                }

                                counterAdapter.notifyDataSetChanged();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }else{
                            Log.i("status",status);
                            Toast.makeText(getApplicationContext(),"Some error"+message,Toast.LENGTH_LONG);
                        }
            }
        });


    }
}