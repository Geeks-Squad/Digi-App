package com.gadspiro.bharani.hack.Async;

/**
 * Created by Bharani on 12/2/17.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.gadspiro.bharani.hack.MainActivity.doctype;
import static com.gadspiro.bharani.hack.MainActivity.image1;
import static com.gadspiro.bharani.hack.R.id.img;


public class Send extends AsyncTask<Void, Void, Boolean> {
    OkHttpClient client = new OkHttpClient();

    String cid;

    private static String email,phone,mPassword,aadhaar;


    HashMap<String, String> param=new HashMap<>();

    Context x;
    String mess="";
    File ima;

  /*  c_id_PanCard

            c_id_DriverLicense*/

     public Send( Context cont,File img) {

         ima=img;
        x=cont;
    }

    public void upload(String url) throws IOException {
        RequestBody formBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                //.addPart(RequestBody.create(MediaType.parse("text/plain"),doctype.substring(1)))
                .addFormDataPart(cid, doctype+".jpg", RequestBody.create(MediaType.parse("image/jpeg"), ima))
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .addHeader("doctype", doctype.substring(1))
                .build();
        Response response = this.client.newCall(request).execute();
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

            param.put("phone",phone);
            param.put("password",mPassword);

            //param.put("ORDER_BY_CLAUSE","");
            //param.put("WHERE_CLAUSE","");
            //param.put("DATA_SOURCE_NAME","HJOREBS02");

            Log.d("register",""+param.toString());

            SharedPreferences pref = x.getSharedPreferences("MyPref", 0); // 0 - for private mode

            cid=String.valueOf(pref.getInt("c_id",0));
            String url="http://192.168.43.46/user/"+cid+"/upload";

            upload(url);


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
    protected void onPostExecute(final Boolean success) {



    }

    @Override
    protected void onCancelled() {}
}
