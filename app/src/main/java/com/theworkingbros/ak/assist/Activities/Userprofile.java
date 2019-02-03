package com.theworkingbros.ak.assist.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import com.theworkingbros.ak.assist.R;

import java.util.Objects;

public class Userprofile extends AppCompatActivity {
    String uid,imgurl;
    FirebaseAuth mAuth;
    ImageView verified;
    FirebaseDatabase mDatabase;
    DatabaseReference mDref;
    private StorageReference mFirebaseStorage;
    FirebaseUser mUser;
    private Uri resultUri = null;
    TextView username,reg;
    de.hdodenhof.circleimageview.CircleImageView profilepic;

    EditText about;
    Button save;
    private final static int gallerycode = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);
        mAuth=FirebaseAuth.getInstance();
        mUser= mAuth.getCurrentUser();
        username=findViewById(R.id.usernameTV);
        mFirebaseStorage=FirebaseStorage.getInstance().getReference().child("AssistUserProfilePics");
        about=findViewById(R.id.AboutET);
        reg=findViewById(R.id.RegisterTV);
        verified=findViewById(R.id.verifiedev);
        save=findViewById(R.id.SaveBTN);
        profilepic=findViewById(R.id.profilepic);
        mDatabase=FirebaseDatabase.getInstance();
        mDref= mDatabase.getReference().child("AssistUsers");
        uid= mUser.getUid();
        mDref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                username.setText(dataSnapshot.child(uid).child("name").getValue(String.class));
                reg.setText(dataSnapshot.child(uid).child("register").getValue(String.class));
                imgurl=dataSnapshot.child(uid).child("image").getValue(String.class);
                if(Objects.equals(dataSnapshot.child(uid).child("verified").getValue(String.class), "true"))
                {
                    verified.setVisibility(View.VISIBLE);
                }
                Picasso
                        .get()
                        .load(imgurl)
                        .into(profilepic);
                if(uid!=mAuth.getUid())
                {
                    about.setFocusable(false);
                    about.setEnabled(false);
                }
                else{
                    if(dataSnapshot.child(uid).child("about").getValue(String.class).equals(""))
                    {
                        about.setText("");
                    }
                    else{
                        about.setText(dataSnapshot.child(uid).child("about").getValue(String.class));
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        profilepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery=new Intent();
                gallery.setAction(Intent.ACTION_GET_CONTENT);
                gallery.setType("image/*");
                startActivityForResult(gallery,gallerycode);


            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDref.child(uid).child("about").setValue(about.getText().toString());

                ///////////////
try {


                StorageReference imagepath = mFirebaseStorage.child("AssistUserProfilePics")
                        .child(resultUri.getLastPathSegment());

                imagepath.putFile(resultUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                                                      @Override
                                                                      public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                                                          Uri downloadurl = taskSnapshot.getDownloadUrl();
                                                                          mDref.child(uid).child("image").setValue(downloadurl.toString());


                                                                      }
                                                                  });

                //////////
}
catch (Exception e){}
                Intent main= new Intent(Userprofile.this,MainActivity.class);
                startActivity(main);
            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && requestCode==gallerycode){
            Uri mImageUri= data.getData();
            CropImage.activity(mImageUri)
                    .setAspectRatio(1,1)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .start(Userprofile.this);

        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                resultUri = result.getUri();

                profilepic.setImageURI(resultUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();

            }
        }
    }
    @Override
    public void onBackPressed()
    {
        startActivity(new Intent(Userprofile.this,MainActivity.class));
        super.onBackPressed();
    }
}

