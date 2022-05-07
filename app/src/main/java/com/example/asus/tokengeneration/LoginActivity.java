package com.example.asus.tokengeneration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Functions.Functions;

public class LoginActivity extends AppCompatActivity {

    private Button verifyOtp ;
    private EditText emailEditText ;
    private EditText otpEditText ;
    private Button sendEmail ;
    JSONObject jsonObject ;
    String emailLogin ="";
    String otp ="";
    String finalotp = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        verifyOtp = findViewById(R.id.verify);
        emailEditText = findViewById(R.id.emailLogin);
        otpEditText = findViewById(R.id.otp);
        sendEmail = findViewById(R.id.send_email);





        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailLogin = emailEditText.getText().toString().trim();
                String otp = otpEditText.getText().toString().trim();
                if(emailLogin.isEmpty()){
                    Log.i("here","here");
                    Log.i("email",emailLogin);
                    Toast.makeText(getApplicationContext(),"Please enter your email",Toast.LENGTH_LONG).show();
                    emailEditText.requestFocus();
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(emailLogin).matches()){
                    Toast.makeText(getApplicationContext(),"Please enter valid email id ",Toast.LENGTH_LONG).show();
                    emailEditText.requestFocus();
                }
                else
                {
                  //  api
                    jsonObject = Functions.createJsonObjectLogin(emailLogin,null);
                    Log.i("JSON", "register " + jsonObject);
                    API.Call_Api(getApplicationContext(), "https://e153-2409-4043-4e98-e216-1cee-36f8-82e9-6df1.in.ngrok.io/files/majorBackend/MajorPralipta/api/index.php?parameter=sendOTP", jsonObject, new Callback() {


                        @Override
                        public void response(JSONObject resp) {

                            String status = resp.optString("status");
                            String message = resp.optString("message");

                            Log.i("Response", "Status: " + resp.optString("status"));
                            Log.i("Response", "Message: " + resp.optString("message"));


                            if(status.equals("200")&& message.equalsIgnoreCase("success")){

                                String otp = resp.optString("otp");
                                Log.i("token", "t: " + otp);
                                finalotp = otp ;
                                Toast.makeText(getApplicationContext(),"Otp  has been sent to your Email Id ",Toast.LENGTH_LONG).show();
                            }
                            else if(status.equals("201")){
                                Toast.makeText(getApplicationContext(),"Some error"+message,Toast.LENGTH_LONG).show();
                            }

                        }
                    });

                }
            }
        });

       verifyOtp.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if(otp.equals("")){
                   Toast.makeText(getApplicationContext(),"Please enter otp sent to your email id ",Toast.LENGTH_LONG).show();
                   otpEditText.requestFocus();
               }
               else if(otp.length()>6 || otp.length()<6){
                   Toast.makeText(getApplicationContext(),"Please check your otp once again ",Toast.LENGTH_LONG).show();
                   emailEditText.requestFocus();
               }
               else if(finalotp != otp) {
                   Toast.makeText(getApplicationContext(),"Invalid otp ",Toast.LENGTH_LONG).show();
                   emailEditText.requestFocus();
               }
               else{
                   jsonObject = Functions.createJsonObjectLogin(emailLogin,otp);
                   Log.i("JSON", "register " + jsonObject);
                   API.Call_Api(getApplicationContext(), Functions.createURl() +"files/majorBackend/MajorPralipta/api/index.php?parameter=userlogin", jsonObject, new Callback() {


                       @Override
                       public void response(JSONObject resp) {
                           String status = resp.optString("status");
                           String message = resp.optString("message");

                           Log.i("Response", "Status: " + resp.optString("status"));
                           Log.i("Response", "Message: " + resp.optString("message"));


                           if(status.equals("200")&& message.equalsIgnoreCase("success")){

                               Toast.makeText(getApplicationContext(),"You are logged in  ",Toast.LENGTH_LONG).show();
                           }
                           else if(status.equals("201")){
                               Toast.makeText(getApplicationContext(),"Some error"+message,Toast.LENGTH_LONG).show();
                           }

                       }
                   });

               }
           }
       });



//        else{
//            jsonObject = Functions.createJsonObjectLogin(emailLogin,otp);
//            Log.i("JSON", "login " + jsonObject);
//
//            API.Call_Api(getApplicationContext(), "url", jsonObject, new Callback() {
//                JSONArray jsonArray;
//                JSONObject jObj;
//
//                @Override
//                public void response(JSONObject resp) {
//                    String status = resp.optString("status");
//                    String message = resp.optString("message");
//                    Log.i("Response", "Status: " + resp.optString("status"));
//                    Log.i("Response", "Message: " + resp.optString("message"));
//
//                    if(status.equals("200")&& message.equalsIgnoreCase("done")){
//                        try {
//                            jsonArray = resp.getJSONArray("data");
//                            jObj = jsonArray.getJSONObject(0);
//                            Log.i("Response", "Data: " + jObj);
//                            String otpNumber = jObj.getString("otp");
//
//                            verifyOtp.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View view) {
//
////                                    if(otpNumber.equals(otp)) {
//                                        Intent intent = new Intent(LoginActivity.this, Registration.class);
//                                        startActivity(intent);
//                                  //  }
////                                    else{
////                                        Toast.makeText(getApplicationContext(),"Invalid otp",Toast.LENGTH_LONG).show();
////
////                                    }
//                                }
//                            });
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                      //  Toast.makeText(getApplicationContext(),"Registration has been done",Toast.LENGTH_LONG).show();
//                    }
//                    else if(status.equals("201")){
//                        Toast.makeText(getApplicationContext(),"Some error",Toast.LENGTH_LONG).show();
//                    }
//
//                }
//            });
//        }



    }
}