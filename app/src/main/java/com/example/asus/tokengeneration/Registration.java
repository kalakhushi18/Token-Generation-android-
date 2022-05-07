package com.example.asus.tokengeneration;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Functions.Functions;

public class Registration extends AppCompatActivity {

    private EditText name ;
    private EditText mobile ;
    private EditText email ;
    private TextView token ;
    private Button getToken ;
    private TextView counterDetails ;
    private TextView getHistory ;
    private TextView tokenGenerate ;
    JSONObject jsonObject = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        mobile = findViewById(R.id.mobile);
        token = findViewById(R.id.token);
        getToken = findViewById(R.id.getToken);
        counterDetails = findViewById(R.id.details);
        getHistory = findViewById(R.id.history);
        tokenGenerate = findViewById(R.id.tokengenerate);


       String  $contactPattern = "^[+]?[0-9]{10,12}$";
       String  $userNamePattern = "^[a-zA-Z]([._-](?![._-])|[a-zA-Z]){3,50}[a-zA-Z]$";



        getToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = name.getText().toString().trim();
                String mobileNumber = mobile.getText().toString().trim();
                String emailId = email.getText().toString().trim();
                Log.i("username",name.getText().toString());
                Log.i("mobile",mobileNumber);
                Log.i("email",emailId);
                if(username.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Please enter user name",Toast.LENGTH_LONG).show();
                    name.requestFocus();

                }
                else if(mobileNumber.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Please enter mobile number",Toast.LENGTH_LONG).show();

                    mobile.requestFocus();
                }
                else if(email.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Please enter email address",Toast.LENGTH_LONG).show();
                    email.requestFocus();
                }
                else if(!username.matches($userNamePattern))
                {
                    Toast.makeText(getApplicationContext(),"Please enter valid user name",Toast.LENGTH_LONG).show();
                    name.requestFocus();
                }

                else if(!Patterns.EMAIL_ADDRESS.matcher(emailId).matches())
                {
                    Toast.makeText(getApplicationContext(),"Please enter valid email address",Toast.LENGTH_LONG).show();
                    email.requestFocus();
                }
                else if(!mobileNumber.matches($contactPattern))
                {
                    Toast.makeText(getApplicationContext(),"Please enter valid mobile number ",Toast.LENGTH_LONG).show();
                    mobile.requestFocus();
                }
                else
                {
                    jsonObject = Functions.createJsonObject(username,emailId,mobileNumber);
                    Log.i("JSON", "register " + jsonObject);
                    API.Call_Api(getApplicationContext(), Functions.createURl() +"/files/majorBackend/MajorPralipta/api/index.php?parameter=generateToken", jsonObject, new Callback() {


                        @Override
                        public void response(JSONObject resp) {
                            String status = resp.optString("status");
                            String message = resp.optString("message");

                            Log.i("Response", "Status: " + resp.optString("status"));
                            Log.i("Response", "Message: " + resp.optString("message"));


                            if(status.equals("200")&& message.equalsIgnoreCase("success")){

                                    String tokenNo = resp.optString("token");
                                    Log.i("token", "t: " + tokenNo);
                                    token.setText(tokenNo);
                                    Toast.makeText(getApplicationContext(),"Registration has been done",Toast.LENGTH_LONG).show();
                            }
                            else if(status.equals("201")){
                                Toast.makeText(getApplicationContext(),"Some error"+message,Toast.LENGTH_LONG).show();
                            }

                        }
                    });
                }
            }
        });


        //move to counter details screen
        counterDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Counter_Details.class);
                startActivity(intent);
            }
        });

        //move to history screen
        getHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HistoryActivity.class);
                startActivity(intent);
            }
        });


    }

}
