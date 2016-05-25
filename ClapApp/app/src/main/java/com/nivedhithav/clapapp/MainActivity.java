package com.nivedhithav.clapapp;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    private SensorManager mSM;
    private Sensor ms;
    ImageView iv;
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSM=(SensorManager) getSystemService(SENSOR_SERVICE);
        ms=mSM.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        iv=(ImageView) findViewById(R.id.image);
        player= MediaPlayer.create(MainActivity.this,R.raw.applause8);
    }

    protected void onResume(){
        super.onResume();
        mSM.registerListener(this,ms,SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause(){
        super.onPause();
        mSM.unregisterListener(this);
    }

    public void onAccuracyChanged(Sensor sensor,int accuracy){

    }

    public void onSensorChanged(SensorEvent event){
        if(event.values[0]==0){
            iv.setImageResource(R.drawable.smiley_clap);
            player= MediaPlayer.create(MainActivity.this,R.raw.applause8);
            player.start();
        }
        else {
            iv.setImageResource(R.drawable.smiley);
            player.pause();
        }
    }

}
