package com.example.hp1.finalapplication;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;

public class Camera extends AppCompatActivity implements View.OnClickListener {

    private Bitmap bitmap;
    private ImageView imageView;
    Button btCamera,btGalery;
    static final int SELECT_IMAGE=1;
    static final int TAKE_IMAGE=0;
    TextView tvemail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        Intent k = getIntent();
        String name = k.getStringExtra("email");


        imageView = (ImageView) findViewById(R.id.imgPhoto);

        btCamera =(Button) findViewById(R.id.btcap);
        btGalery = (Button) findViewById(R.id.btgalery);
        tvemail=(TextView)findViewById(R.id.tvemail);
        btCamera.setOnClickListener(this);
        btGalery.setOnClickListener(this);
        tvemail.setText("your username is: "+name);


        if(!hasCamera()){
            btCamera.setEnabled(false);
        }
    }
    private boolean hasCamera(){
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }
    @Override
    public void onClick(View v) {
        if(v==btCamera){
            //start another activity and receive a result back in case the activity exists return 0
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(i,TAKE_IMAGE );
        }else{
            Intent i = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i, SELECT_IMAGE);
        }
    }
    /**
     *  The resultCode will be RESULT_CANCELED if the activity explicitly returned that, didn't return any result, or crashed during its operation.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == TAKE_IMAGE && resultCode == RESULT_OK){
            Bundle extra = data.getExtras();
            bitmap  = (Bitmap) extra.get("data");
            imageView.setImageBitmap(bitmap);
        }
        else if(requestCode == SELECT_IMAGE && resultCode == RESULT_OK)
        {
            Uri targetUri = data.getData();
            Toast.makeText(getApplicationContext(), targetUri.toString(), Toast.LENGTH_SHORT).show();
            //textTargetUri.setText(targetUri.toString());
            Bitmap bitmap;
            try {

                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }
}