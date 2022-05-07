package com.example.asus.tokengeneration;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class API {

    public static void Call_Api(final Context context, final String url, final JSONObject jsonobject, final Callback callback) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonobject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (callback != null) {
                    callback.response(response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(context,"", Toast.LENGTH_SHORT).show();
                if (error instanceof NetworkError){
                    Log.i("on response", "netowork");
                }
                else if(error instanceof ServerError){
                    Log.i("on response", "server");
                }
                else if(error instanceof TimeoutError){
                    Log.i("on response", "timeout");
                }
                Log.i("on response", "onResponse: " + "Called" + error);
            }
        }) {
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
////                Map<String, String> params = new HashMap<String, String>();
////                params.put("Authkey", "j%1s@#8@%$2h#a");
//               // return params;
//            }
        };
        Volley.newRequestQueue(context).add(jsonObjectRequest);
    }

}