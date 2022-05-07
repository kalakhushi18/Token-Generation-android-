package com.example.asus.tokengeneration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

//    private TextView counter_details ;
//    private TextView token_generate ;
//    private ArrayList<String> infoList ;
//    RecyclerView historyRecycler ;
//    RecyclerView.Adapter historyAdapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
//        counter_details = findViewById(R.id.counterDetailsHistory);
//        token_generate = findViewById(R.id.tokengeneratehistory);
//
//        infoList = new ArrayList<String>();
//        historyRecycler = (RecyclerView) findViewById(R.id.recyler_history);
//
//        historyRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
//        historyAdapter= new History_Adapter(infoList);
//        historyRecycler.setAdapter(historyAdapter);
//
//        loadInfo();
    }

    private void loadInfo() {
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

//                            infoList.add(val_counter);

                        }

//                        historyAdapter.notifyDataSetChanged();

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