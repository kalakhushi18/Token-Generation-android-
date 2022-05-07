package Functions;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class Functions {

    public static String createURl(){
        String url = "https://e153-2409-4043-4e98-e216-1cee-36f8-82e9-6df1.in.ngrok.io";
        return url ;
    }


 //   for register
    public static JSONObject createJsonObject(String name , String email , String mobile)
    {
        JSONObject parameters = new JSONObject();
        try {
            parameters.put("userName",name);
            parameters.put("email",email);
            parameters.put("contactNo",mobile);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("Credentials", "JSON: " + parameters);
        return parameters ;
    }

    //for login
    public static JSONObject createJsonObjectLogin(String email , String otp)
    {
        JSONObject parameters = new JSONObject();
        try {
            parameters.put("email",email);
            parameters.put("otp",otp);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("Credentials", "JSON: " + parameters);
        return parameters ;
    }

    public static JSONObject createJsonObjectEmail(String email)
    {
        JSONObject parameters = new JSONObject();
        try {
            parameters.put("email",email);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("Credentials", "JSON: " + parameters);
        return parameters ;
    }
}
