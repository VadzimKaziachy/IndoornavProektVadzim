package by.grsu.ftf.indoornav.storage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Picture;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.larvalabs.svgandroid.SVG;
import com.larvalabs.svgandroid.SVGParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import by.grsu.ftf.indoornav.db.classesAssistant.BeaconFireBase;

/**
 * Created by Vadzim on 17.01.2018.
 */

public class DataBaseFireBase {


    public interface Callback {
        void mCallingBack(List<BeaconFireBase> mBeacon);
    }

    private Callback callback;
    private CallbackPicture callbackPicture;

    public DataBaseFireBase(Callback callback) {
        this.callback = callback;
    }

    public void dataBaseFireBase(final Context context) {

        final List<BeaconFireBase> mBeacon = new ArrayList<>();

        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
        myRef.child("Vadim").child("Beacon").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSh : dataSnapshot.getChildren()) {
                    BeaconFireBase beacon = new BeaconFireBase();
                    beacon.setName(dataSh.child("id").getValue(String.class));
                    beacon.setX(dataSh.child("X").getValue(Float.class));
                    beacon.setY(dataSh.child("Y").getValue(Float.class));
                    if (dataSh.child("base").exists()) {
                        beacon.setMaxDist(dataSh.child("base").getValue(Float.class));
                    }
                    mBeacon.add(beacon);
                }
                callback.mCallingBack(mBeacon);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(context, "нет покдлючения к интернету", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void setCallbackPicture(CallbackPicture callbackPicture) {
        this.callbackPicture = callbackPicture;
    }

    public void fireBasePicture() {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReferenceFromUrl("gs://beacon-ftf.appspot.com");

        try {
            final File localFile = File.createTempFile("images", "jpg");
            storageRef.child("Maps/method-draw-image.svg").getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            InputStream a = null;
                            try {
                                a = new FileInputStream(localFile.getAbsoluteFile());
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            SVG svg = SVGParser.getSVGFromInputStream(a);
                            Picture p = svg.getPicture();
                            callbackPicture.CallbackPict(p);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            Log.d("Log", "данные не пришли");
                        }
                    });
        } catch (IOException e) {
        }
    }
}
