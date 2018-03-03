package by.grsu.ftf.indoornav.storage;

import android.content.Context;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

import by.grsu.ftf.indoornav.db.BeaconDatabase;
import by.grsu.ftf.indoornav.db.beacon.Beacon;
import by.grsu.ftf.indoornav.db.classesAssistant.BeaconFireBase;

/**
 * Created by Vadzim on 17.01.2018.
 */

public class DataBaseFireBase {

    public List<BeaconFireBase> dataBaseFireBase(final Context context) {

        final List<BeaconFireBase> mBeacon = new ArrayList<>();

        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
        myRef.child("Vadim").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSh : dataSnapshot.getChildren()) {
                    BeaconFireBase beacon = new BeaconFireBase();
                    beacon.setName(dataSh.child("id").getValue(String.class));
                    beacon.setX(dataSh.child("X").getValue(Float.class));
                    beacon.setY(dataSh.child("Y").getValue(Float.class));
                    if(dataSh.child("base").exists()){
                        beacon.setMaxDist(dataSh.child("base").getValue(Float.class));
                    }
                    mBeacon.add(beacon);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(context, "нет покдлючения к интернету", Toast.LENGTH_LONG).show();
            }
        });
        return mBeacon;
    }
}
