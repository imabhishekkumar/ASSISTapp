package com.theworkingbros.ak.assist.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import com.theworkingbros.ak.assist.Model.Blog;
import com.theworkingbros.ak.assist.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Addpost extends AppCompatActivity {
    private ImageButton postimg;
    public String user,uid,parent;

    TextView username;
    private EditText posttitle, postdesc;
    private Button submitbtn,addimg;
    private DatabaseReference mPostDatabase,userref;
    private FirebaseUser mUser;
    private FirebaseAuth mAuth;
    private StorageReference mStorage;
    private ProgressDialog mProgress;
    private static final int Gallery_code = 1;
    private final static int gallerycode = 1;

    private Uri ImageUri=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpost);
        mAuth = FirebaseAuth.getInstance();
        mProgress = new ProgressDialog(this);
        mUser = mAuth.getCurrentUser();
        addimg=findViewById(R.id.addimg);
        mStorage = FirebaseStorage.getInstance().getReference();
        mPostDatabase = FirebaseDatabase.getInstance().getReference().child("AssistBlog");
        userref= FirebaseDatabase.getInstance().getReference().child("AssistUsers");
        userref.keepSynced(true);
        postimg = findViewById(R.id.post_image);
        posttitle = findViewById(R.id.postview_titlelistt);
        postdesc = findViewById(R.id.postview_desclistt);
        submitbtn = findViewById(R.id.submitt);
        username=findViewById(R.id.usernameid);
        uid= mUser.getUid();
        final Blog blog=new  Blog();
        blog.setUserid(uid);

        addimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postimg.setVisibility(View.VISIBLE);
                addimg.setVisibility(View.GONE);
            }
        });
        userref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                user= dataSnapshot.child(uid).child("name").getValue(String.class);
                username.setText(user);
                blog.setUsername(user);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        postimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent(Intent.ACTION_GET_CONTENT);
                gallery.setType("image/*");
                startActivityForResult(gallery, Gallery_code);
            }
        });

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPosting();
            }
        });
    }


 /*   @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Gallery_code && resultCode == RESULT_OK) {
            ImageUri = data.getData();
            postimg.setImageURI(ImageUri);

        }
    }*/
    /////////////////////////////////////////////////////////////





    //////////////////////////////////////////////////////

    private void startPosting() {
        mProgress.setMessage("Posting..");
        mProgress.show();
        final String titleVal = posttitle.getText().toString().trim();
        final String desVal = postdesc.getText().toString().trim();


        if (!TextUtils.isEmpty(titleVal) && !TextUtils.isEmpty(desVal)) {


            if (ImageUri == null) {
                if (!TextUtils.isEmpty(titleVal) && !TextUtils.isEmpty(desVal)) {

               /* StorageReference filepath= mStorage.child("Assist_Images").child(ImageUri.getLastPathSegment());
                filepath.putFile(ImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Uri downloadurl= taskSnapshot.getDownloadUrl();
                 */
                    DatabaseReference newPost = mPostDatabase.push();
                    Map<String, String> dataToSave = new HashMap<>();
                    //  java.text.DateFormat dateFormat= java.text.DateFormat.getDateInstance();
                    long yourmilliseconds = System.currentTimeMillis();
                    SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");
                    Date resultdate = new Date(yourmilliseconds);
                    //System.out.println();
                    String formatteddate = sdf.format(resultdate);
                    final String uniqueid = (mUser.getUid() + formatteddate);
                    dataToSave.put("title", titleVal);
                    dataToSave.put("desc", desVal);
                    dataToSave.put("timestamp", formatteddate);//String.valueOf(java.lang.System.currentTimeMillis()));
                    dataToSave.put("userid", mUser.getUid());
                    dataToSave.put("username", user);
                    dataToSave.put(uniqueid, uniqueid);
                    newPost.setValue(dataToSave);
                    mProgress.dismiss();
                    startActivity(new Intent(Addpost.this, MainActivity.class));
                    finish();


                }
            }
                else {
                if (!TextUtils.isEmpty(titleVal) && !TextUtils.isEmpty(desVal)) {
                    StorageReference filepath = mStorage.child("Assist_Images").child(ImageUri.getLastPathSegment());
                    filepath.putFile(ImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Uri downloadurl = taskSnapshot.getDownloadUrl();
                            DatabaseReference newPost = mPostDatabase.push();
                            java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance();
                            String formatteddate = dateFormat.format(new Date(Long.valueOf(String.valueOf(java.lang.System.currentTimeMillis()))));
                            final String uniqueid = (mUser.getUid() + formatteddate);
                            Map<String, String> dataToSave = new HashMap<>();
                            dataToSave.put("title", titleVal);
                            dataToSave.put("desc", desVal);
                            dataToSave.put("image", downloadurl.toString());
                            dataToSave.put("timestamp", formatteddate);//String.valueOf(java.lang.System.currentTimeMillis()));
                            dataToSave.put("userid", mUser.getUid());
                            dataToSave.put("username", user);
                            dataToSave.put(uniqueid, uniqueid);
                            newPost.setValue(dataToSave);
                         /* Blog blog=new  Blog();
                            blog.setUniqueid(uniqueid);*/

                       /*   newPost.child("title").setValue(titleVal);
                            newPost.child("desc").setValue(desVal);
                            newPost.child("image").setValue(downloadurl.toString());
                            newPost.child("timestamp").setValue(String.valueOf(java.lang.System.currentTimeMillis()));
                            newPost.child("userid").setValue(mUser.getUid());
                            newPost.child("username").setValue(user);
                            mProgress.dismiss();*/
                            startActivity(new Intent(Addpost.this, MainActivity.class));
                            finish();

                        }
                    });
                }
            }


        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && requestCode==gallerycode){
            Uri mImageUri= data.getData();
            CropImage.activity(mImageUri)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .start(Addpost.this);

        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                ImageUri = result.getUri();

                postimg.setImageURI(ImageUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                //Exception error = result.getError();
                Toast.makeText(Addpost.this,"Error",Toast.LENGTH_SHORT).show();

            }
        }
    }
}
