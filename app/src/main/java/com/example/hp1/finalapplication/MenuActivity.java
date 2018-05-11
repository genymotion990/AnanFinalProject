package com.example.hp1.finalapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView lvtepulem;
    ArrayList<lvtepul> tepulim=new ArrayList<lvtepul>();
    Button btwebsite,btprogress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        lvtepulem=(ListView)findViewById(R.id.lvItems);
        btwebsite=(Button)findViewById(R.id.btwebsite);
        btprogress=(Button)findViewById(R.id.btprogress);

        tepulim.add(new lvtepul("More About us",R.drawable.logoo));
        tepulim.add(new lvtepul("Your Account Details",R.drawable.lvpic));
        tepulim.add(new lvtepul("Your Therapy Classes",R.drawable.lvpic));
        tepulim.add(new lvtepul("Contact Us",R.drawable.lvpic));

        customadapter custom=new customadapter(this,R.layout.custom_row,tepulim);
        lvtepulem.setAdapter(custom);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(position==0){

        }}
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.firstitem:
                Toast.makeText(getBaseContext(), "logged out", Toast.LENGTH_LONG).show();
                Intent i=new Intent(this,MainActivity.class);
                startActivity(i);
                break;

            case R.id.seconditem:
                Intent i2=new Intent(this,Camera.class);
                startActivity(i2);
                break;

            case R.id.aboutUs:
                Intent i3=new Intent(this,MoreAboutUs.class);
                startActivity(i3);
                break;
        }
        return true;
    }
    public void getnotification(View view){
        NotificationManager notificationmgr = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        Notification notif = new Notification.Builder(this)
                .setSmallIcon(R.drawable.logoo)
                .setContentTitle("The Website Is Not Ready yet")
                .setContentText("Please Be Patient Until the Website is Released")
                .build();
        notificationmgr.notify(0,notif);
    }
}

