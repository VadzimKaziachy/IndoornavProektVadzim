package by.grsu.ftf.indoornav.navigation.map.fragment_1_map;

import android.content.Context;
import android.telecom.Call;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import by.grsu.ftf.indoornav.db.classesAssistant.BeaconFireBase;
import by.grsu.ftf.indoornav.db.listZal.Zal;

/**
 * Created by Vadzim on 19.03.2018.
 */

public class DataBaseFireBaseFragmentMap {

    private Callback callback;
//    private Zal zal;

    public interface Callback {
        void mCallback(List<String> mList_Zal);
    }

    public DataBaseFireBaseFragmentMap(Callback callback) {
        this.callback = callback;
    }

    public void dataBaseFireBase(final Context context) {

        final List<String> list_Zal = new ArrayList<>();
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
        myRef.child("Vadim").child("zal1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSh : dataSnapshot.getChildren()) {
//                    zal = new Zal();
//                    zal.setZal(dataSh.child("zal").getValue(String.class));
//                    zal.setUrl(dataSh.child("url").getValue(String.class));
                    list_Zal.add(dataSh.getValue(String.class));
                }
                callback.mCallback(list_Zal);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(context, "нет покдлючения к интернету", Toast.LENGTH_LONG).show();
            }
        });
    }
}
