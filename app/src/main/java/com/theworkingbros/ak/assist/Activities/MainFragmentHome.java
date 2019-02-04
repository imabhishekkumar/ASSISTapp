package com.theworkingbros.ak.assist.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.theworkingbros.ak.assist.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainFragmentHome extends Fragment {
    String name, uid, imgurl;
    private CircleImageView avatar;
    private FirebaseUser mUser;
    private FirebaseAuth mAuth;

    CardView gpaCv, mapCv, clubCv,resultsCv;
    ConstraintLayout constraintLayout;
    public MainFragmentHome(){}
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_home, container, false);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();

        DatabaseReference mdbref = mDatabase.getReference().child("AssistUsers");
        mdbref.keepSynced(true);
        avatar = view.findViewById(R.id.avatarpic);

        uid = mUser.getUid();
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading from database");
        progressDialog.show();
       gpaCv= view.findViewById(R.id.gpaCalCV);
        mapCv = view.findViewById(R.id.mapsCV);
        clubCv = view.findViewById(R.id.clubsCV);
        resultsCv= view.findViewById(R.id.resultsCV);
        constraintLayout= view.findViewById(R.id.sublayout);
        mdbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                name = dataSnapshot.child(uid).child("name").getValue(String.class);
                imgurl = dataSnapshot.child(uid).child("image").getValue(String.class);
                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                        .setPhotoUri(Uri.parse(imgurl))
                        .build();
                mUser.updateProfile(profileUpdates);
                Picasso.get()
                        .load(imgurl)
                        .into(avatar);
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        gpaCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(getContext(), CGPA.class);
                startActivity(main);
            }
        });
        mapCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(getContext(), universitymap.class);
                startActivity(main);

            }
        });
        clubCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(getContext(), club.class);
                startActivity(main);

            }
        });
        resultsCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(getContext(), results.class);
                startActivity(main);

            }
        });

        return view;
    }

}
