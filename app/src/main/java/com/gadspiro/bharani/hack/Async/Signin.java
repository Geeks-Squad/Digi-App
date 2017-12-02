package com.gadspiro.bharani.hack.Async;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Bharani on 12/2/17.
 */


public class Signin extends AsyncTask<Void, Void, Boolean> {



    private static String email,phone,mPassword,aadhaar;
    OkHttpClient client = new OkHttpClient();

    public static HashMap<String, String> user=new HashMap<>();

    Context x;
    JSONArray jsonArray;
    String mess="";


    public Signin( String phon, String password , Context cont) {
        mPassword = password;
        phone=phon;
        x=cont;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        // TODO: attempt authentication against a network service.




        try {

            if (Looper.myLooper() == null)
            {
                Looper.prepare();
            }

            //param = lightClient.getServiceParameters("ADMIN", "KPIT_ADMIN", "user_data");


            String url="http://192.168.43.46/user/"+phone+"/get";

            //param.put("ORDER_BY_CLAUSE","");
            //param.put("WHERE_CLAUSE","");
            //param.put("DATA_SOURCE_NAME","HJOREBS02");

            Request request = new Request.Builder()
                    .url(url)
                    .build();


        Response response = client.newCall(request).execute();


             jsonArray=new JSONArray(response.body().string());


            Log.d("signin",""+jsonArray.toString());




            //WSResponse wsResponse= a.genericservice("ADMIN", "KPIT_ADMIN", "register_user", new Gson().toJson(param));

            Log.v("registered","sdasdasd");

            //mess=wsResponse.getData().toString();
            Toast.makeText(x,""+mess,Toast.LENGTH_SHORT).show();


            // Simulate network access.
            Thread.sleep(2000);
        } catch (Exception e) {

            Toast.makeText(x,e.getLocalizedMessage()+mess,Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }




        // TODO: register the new account here.
        return true;
    }

    @Override
    protected void onPostExecute(final Boolean success){

        SharedPreferences pref = x.getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        try {
            editor.putInt("c_id",jsonArray.getJSONObject(0).getInt("c_id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        editor.commit();

    }

    @Override
    protected void onCancelled() {}
}
