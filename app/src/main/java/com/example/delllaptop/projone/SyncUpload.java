package com.example.delllaptop.projone;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.delllaptop.projone.DTO.Trip;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Dell Laptop on 3/13/2018.
 */

public class SyncUpload extends AsyncTask<Trip,Void,Integer> {


    Trip trip;
    private FirebaseAuth mAuth;
    private DatabaseReference db;
    private FirebaseUser fuser;
    SQLAdapter adapter;
    Context context;

    public SyncUpload(Context context, FirebaseAuth mAuth, DatabaseReference db, FirebaseUser fuser, Trip trip) {
        this.mAuth = mAuth;
        this.db = db;
        this.fuser = fuser;
        this.context = context;
        this.trip = trip;
    }

    @Override
    protected Integer doInBackground(Trip... trips) {
        final SQLAdapter adapter = new SQLAdapter(context);
        String key = db.child(fuser.getUid()).push().getKey();
        trip.setEd(key);
        long i = adapter.updateTrip(trip.getId(), trip.getName(), trip.getSp(), trip.getSlong(),
                trip.getSlat(), trip.getEp(), trip.getElong(), trip.getElat(), trip.getStatus(),
                trip.getSd(), trip.getEd(), trip.getSt(), trip.getSt(), trip.getRep(), trip.getUser(), null);
        db.child(fuser.getUid()).child(key).setValue(trip);
        Log.i("a5eryom","awel mara " + i+trip.getEd());

        return null;
    }
}
