package com.example.indoornavigation;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;

import java.util.Timer;
import java.util.TimerTask;

public class MapActivity extends AppCompatActivity {
    private final static int REQUEST_ENABLE_BT = 1;
    private static final int PERMISSION_REQUEST_COARSE_LOCATION = 1;
    BluetoothManager btManager;
    BluetoothAdapter btAdapter;
    BluetoothLeScanner btScanner;
    KalmanFilter kalmanFilters1 = new KalmanFilter(0.01f, 3.0f);
    KalmanFilter kalmanFilters2 = new KalmanFilter(0.01f, 3.0f);
    KalmanFilter kalmanFilters3 = new KalmanFilter(0.01f, 3.0f);
    KalmanFilter kalmanFilters4 = new KalmanFilter(0.01f, 3.0f);

    Triang rt = new Triang();
    float beaconXY[][] = new float[4][4];
    String beaconName[]= new String[4];
    int beaconRssi[] = new int[4];
    int beaconRssi1[] = new int[4];
    int beaconId[] = new int[4];
    int beaconIdd[] = new int[4];
    float beaconDl[] = new float[4];
    PointF a = new PointF();
    PointF b = new PointF();
    PointF c = new PointF();
    PointF r = new PointF();
    float qw=0.0f;
    float ty=0.0f;
    TextView mText;
    RectF rectf;
    Intent intent = new Intent();
    DrawView dr;
    MyTimerTask bthh;
    Timer tim;
    float filteredRssi=0.0f;
    int port=0;
    int port1=0;

    @Override
    @TargetApi(25)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        dr = new DrawView(this);
        addContentView(dr, new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT,ActionBar.LayoutParams.WRAP_CONTENT));
        btManager = (BluetoothManager)getSystemService(Context.BLUETOOTH_SERVICE);
        btAdapter = btManager.getAdapter();
        btScanner = btAdapter.getBluetoothLeScanner();
        if (this.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("This app needs location access");
            builder.setMessage("Please grant location access so this app can detect peripherals.");
            builder.setPositiveButton(android.R.string.ok, null);
            builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                @TargetApi(25)
                public void onDismiss(DialogInterface dialog) {
                    requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_REQUEST_COARSE_LOCATION);
                }
            });
            builder.show();
        }
        mText=(TextView)findViewById(R.id.textView6);
        mText.setText("");
        tim = new Timer();
        bthh = new MyTimerTask();
        tim.schedule(bthh,500,500);
        beacon_XY();
        for(int i=0;i<3;i++){
            beaconRssi[i]=0;
        }
    }
    private ScanCallback leScanCallback = new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            beaconRssi[beaconPoisk(result.getDevice().getName())]=result.getRssi();
            beaconIdd[beaconPoisk(result.getDevice().getName())]=beaconPoisk(result.getDevice().getName());
            //mText.append(String.valueOf(result.getRssi())+"\n");

            for(int i=0;i<4;i++){
                if(beaconRssi[i]!=0){
                    beaconDl[port]=rt.dlina(beaconRssi1[i],beaconRssi[i],3.0f);
                    //beaconDl[port]=rt.get_distance(beaconRssi[i],-70);
                    beaconId[port]=beaconIdd[i];
                    port++;
                }
                if(port>2){
                    a.x=beaconXY[beaconId[0]][0];
                    a.y=beaconXY[beaconId[0]][1];
                    b.x=beaconXY[beaconId[1]][0];
                    b.y=beaconXY[beaconId[1]][1];
                    c.x=beaconXY[beaconId[2]][0];
                    c.y=beaconXY[beaconId[2]][1];
                    r=rt.trilaterate(a,b,c,beaconDl[0],beaconDl[1],beaconDl[2]);
                    qw=r.x;
                    ty=r.y;
                    mText.append("! "+"*"+String.valueOf(beaconId[0])+"*"+String.valueOf(beaconDl[0])+"++"+"*"+String.valueOf(beaconId[1])+"*"+String.valueOf(beaconDl[1])+"++"+"*"+String.valueOf(beaconId[2])+"*"+String.valueOf(beaconDl[2])+"\n");
                    mText.append("!!!"+String.valueOf(port)+"++"+String.valueOf(qw)+"++"+String.valueOf(ty)+"\n");
                    //mText.append(String.valueOf(beaconId[0])+"-"+a.x+"-"+beaconDl[0]+"\n");
                    port=0;
                }else{
                }
            }
            if(beaconPoisk(result.getDevice().getName())==0){
                filteredRssi = kalmanFilters1.filter(result.getRssi());
                //mText.append(String.valueOf(result.getRssi())+" -- "+String.valueOf(rt.dlina(beaconRssi1[0],result.getRssi(),3.0f))+"\n");
            }
            //mText.append(result.getDevice().getName()+" -- "+String.valueOf(beaconPoisk(result.getDevice().getName()))+"\n");
        }
    };
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_COARSE_LOCATION: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    System.out.println("coarse location permission granted");
                } else {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Functionality limited");
                    builder.setMessage("Since location access has not been granted, this app will not be able to discover beacons when in the background.");
                    builder.setPositiveButton(android.R.string.ok, null);
                    builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                        }
                    });
                    builder.show();
                }
                return;
            }
        }
    }
    public void startScanning() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                btScanner.startScan(leScanCallback);
            }
        });
    }
    public void stopScanning() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                btScanner.stopScan(leScanCallback);
            }
        });
    }
    public void beacon_XY(){
        beaconXY[0][0]=0.0f;
        beaconXY[0][1]=0.0f;
        beaconName[0]="id1";
        beaconRssi1[0]=-70;

        beaconXY[1][0]=70.0f;
        beaconXY[1][1]=0.0f;
        beaconName[1]="id2";
        beaconRssi1[1]=-70;

        beaconXY[2][0]=70.0f;
        beaconXY[2][1]=120.0f;
        beaconName[2]="id3";
        beaconRssi1[2]=-70;

        beaconXY[3][0]=0.0f;
        beaconXY[3][1]=600.0f;
        beaconName[3]="id4";
        beaconRssi1[3]=-70;
    }
    public int beaconPoisk(String name){
        int n=0;
        for(int i=0;i<3;i++){
            if(beaconName[i].equals(name)){
                n=i;
            }
        }
        return n;
    }
    class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            runOnUiThread( new Runnable(){
                @Override
                public void run() {
                    startScanning();
                    //mText.append("44"+"\n");
                }
            });
        }
    }
    class DrawView extends View{
        Paint p;
        Path d;
        public DrawView(Context context){
            super(context);
            p=new Paint();
            d=new Path();
            p.setStrokeWidth(2);
            p.setStyle(Paint.Style.STROKE);
            rectf = new RectF(0,0,400,600);
            p.setColor(Color.RED);
        }
        @Override
        protected void onDraw(Canvas canvas) {
            invalidate();
            d.reset();
            d.addRect(rectf,Path.Direction.CW);
            d.addCircle(r.x,r.y,10,Path.Direction.CW);
            canvas.drawPath(d,p);
        }
    }
}
