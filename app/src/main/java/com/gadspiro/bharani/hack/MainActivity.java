package com.gadspiro.bharani.hack;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.*;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alertdialogpro.AlertDialogPro;
import com.gadspiro.bharani.hack.Async.Retrieve;
import com.gadspiro.bharani.hack.Async.Send;
import com.sandrios.sandriosCamera.internal.SandriosCamera;
import com.sandrios.sandriosCamera.internal.configuration.CameraConfiguration;
import com.sandrios.sandriosCamera.internal.controller.CameraController;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final int CAPTURE_MEDIA = 368;

    static Uri obj;
    public static File image1;
    public static String doctype;
    AlertDialog levelDialog;

    final CharSequence[] Docs = {" Driving License "," PAN Card "};

    // showImagePicker is boolean value: Default is true
    // setAutoRecord() to start recording the video automatically if media action is set to video.
    private void launchCamera() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView gridView=(GridView) findViewById(R.id.grid);

         final Activity activit=this;
        gridView.setAdapter(new ImageAdapter(this));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Log.d("tes","asdas");
                switch (position){
                    case 0:
                        Toast.makeText(view.getContext(),"Hola",Toast.LENGTH_SHORT).show();



                        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                        builder.setTitle("Select the type of document");
                        builder.setSingleChoiceItems(Docs, -1, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {


                                switch(item)
                                {
                                    case 0:
                                        doctype="_DriverLicense";
                                        break;
                                    case 1:

                                        doctype="_PanCard";
                                        break;


                                }

                                new  SandriosCamera(activit,CAPTURE_MEDIA)
                                        .setShowPicker(true)
                                        .setVideoFileSize(0)
                                        .setMediaAction(CameraConfiguration.MEDIA_ACTION_PHOTO)
                                        .enableImageCropping(true)
                                        .launchCamera();


                                levelDialog.dismiss();
                            }
                        });
                        levelDialog = builder.create();
                        levelDialog.show();






                        break;



                    case 1:

                        Retrieve retrieve=new Retrieve(view.getContext());
                        retrieve.execute((Void) null);
                        break;

                    case 2:
                        startActivity(new Intent(MainActivity.this,QR.class));
                        break;

                }
            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAPTURE_MEDIA && resultCode == RESULT_OK) {

            String path=data.getStringExtra(CameraConfiguration.Arguments.FILE_PATH);
            SharedPreferences pref = getSharedPreferences("MyPref", 0); // 0 - for private mode


            String cid=String.valueOf(pref.getInt("c_id",0));



            Log.e("File", "" +             path.substring(path.lastIndexOf("/"))   );
            Toast.makeText(this, "Media captured. ", Toast.LENGTH_SHORT).show();

            image1=new File(path);
            String name=path.substring(0,path.lastIndexOf("/")+1);
            String rename=name+cid+doctype+".jpg";
            File nn=new File(rename);
            image1.renameTo(new File(rename));


            Send send=new Send(this,nn);
            send.execute((Void) null);

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        SharedPreferences preferences =getSharedPreferences("MyPref",0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
        finish();
    }
}
