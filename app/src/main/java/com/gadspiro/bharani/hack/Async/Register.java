package com.gadspiro.bharani.hack.Async;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.gadspiro.bharani.hack.MainActivity.doctype;
import static com.gadspiro.bharani.hack.MainActivity.image1;

/**
 * Created by Bharani on 12/2/17.
 */


public class Register extends AsyncTask<Void, Void, Boolean> {



    private final String name,email,phone,mPassword,aadhaar;



    JSONObject param=new JSONObject();
    Context x;
    String mess="";


   public Register(String nam,String phon,String aadh,String em, String password , Context cont) {
        mPassword = password;
        name=nam;
        email=em;
        phone=phon;
        aadhaar=aadh;
        x=cont;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        // TODO: attempt authentication against a network service.



        OkHttpClient client = new OkHttpClient();

        try {

            if (Looper.myLooper() == null)
            {
                Looper.prepare();
            }

            //param = lightClient.getServiceParameters("ADMIN", "KPIT_ADMIN", "user_data");

            param.put("name",name);
            param.put("mobile",phone);
            param.put("aadhaar",aadhaar);
            param.put("email",email);
            param.put("pass",mPassword);

            //param.put("ORDER_BY_CLAUSE","");
            //param.put("WHERE_CLAUSE","");
            //param.put("DATA_SOURCE_NAME","HJOREBS02");



            SharedPreferences pref = x.getSharedPreferences("MyPref", 0); // 0 - for private mode

            param.put("loc",""+pref.getString("loc",null));

            Log.d("shared"," "+pref.getInt("c_id",0));
            Log.d("register",""+param.toString());







            MediaType mediaType = MediaType.parse("application/json");
            String json = "\"{\\\"name\\\": \\\""+name+"\\\",\\\"email\\\": \\\""+email+"\\\",\\\"aadhaar\\\": \\\""+aadhaar+"\\\",\\\"pass\\\": \\\""+mPassword+"\\\",\\\"loc\\\": \\\""+""+pref.getString("loc",null)+"\\\",\\\"mobile\\\":\\\""+phone+"\\\"}\"";
            Log.d("JSOn", json);
            RequestBody body = RequestBody.create(mediaType, json);
            Request request = new Request.Builder()
                    .url("http://192.168.43.46:80/user/signup")
                    .post(body)
                    .addHeader("content-type", "application/json")
                    .addHeader("cache-control", "no-cache")
                    .build();

            Response response = client.newCall(request).execute();








            //WSResponse wsResponse= a.genericservice("ADMIN", "KPIT_ADMIN", "register_user", new Gson().toJson(param));

            Log.v("registered",""+ response.body().toString());

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
    protected void onPostExecute(final Boolean success) {


        Log.d("REgister","Success");

    }

    @Override
    protected void onCancelled() {}
}
