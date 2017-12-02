package com.gadspiro.bharani.hack.Async;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.gadspiro.bharani.hack.Doc_view;
import com.gadspiro.bharani.hack.MainActivity;

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


public class Retrieve extends AsyncTask<Void, Void, Boolean> {


    String cid;

    private static String email,phone,mPassword,aadhaar;
    OkHttpClient client = new OkHttpClient();

    public static HashMap<String, String> user=new HashMap<>();

    Context x;
    JSONArray jsonArray;
    String mess="";


    public Retrieve(Context cont) {
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


            SharedPreferences pref = x.getSharedPreferences("MyPref", 0); // 0 - for private mode


            cid=String.valueOf(pref.getInt("c_id",0));

            String url="http://192.168.43.46/user/"+cid+"/docs";

            //param.put("ORDER_BY_CLAUSE","");
            //param.put("WHERE_CLAUSE","");
            //param.put("DATA_SOURCE_NAME","HJOREBS02");

            Request request = new Request.Builder()
                    .url(url)
                    .build();


            Response response = client.newCall(request).execute();


            jsonArray=new JSONArray(response.body().string());


            Log.d("Docs",""+jsonArray.toString());




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
    protected void onProgressUpdate(Void... values) {

    }

    @Override
    protected void onPostExecute(final Boolean success){


        x.startActivity(new Intent(x,Doc_view.class));


        SharedPreferences pref = x.getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();

                   editor.remove("driv");
        editor.remove("pan");
        try {

            for(int i=0;i<jsonArray.length();i++){
                JSONObject ret=jsonArray.getJSONObject(i);
                if(ret.getString("type").equals("DriverLicense"))
                    editor.putString("driv",ret.getString("status"));
                else
                    editor.putString("pan",ret.getString("status"));


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        editor.commit();

    }

    @Override
    protected void onCancelled() {}
}
