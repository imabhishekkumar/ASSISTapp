package com.theworkingbros.ak.assist.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.theworkingbros.ak.assist.R;

public class changePassword extends AppCompatActivity {
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseDatabase mDB;
    DatabaseReference mRef;
    String email,oldpas,newpas,confirmnewpass;
    EditText oldpass, newpass,confirmpass;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        mDB = FirebaseDatabase.getInstance();
        oldpass = findViewById(R.id.oldpass);
        newpass = findViewById(R.id.newpass1);
        confirmpass = findViewById(R.id.newpass2);
        submit = findViewById(R.id.submitpass);
        mRef = mDB.getReference().child("AssistUsers").child(user.getUid());



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog progressDialog = new ProgressDialog(changePassword.this);
                progressDialog.setMessage("Changing Password");
                progressDialog.show();
                email = user.getEmail();
                oldpas = oldpass.getText().toString();
                newpas = newpass.getText().toString();
                confirmnewpass = confirmpass.getText().toString();

                    if (!TextUtils.isEmpty(oldpas) && !TextUtils.isEmpty(newpas) && !TextUtils.isEmpty(confirmnewpass)) {
                        if (newpas.equals(confirmnewpass)) {
                            AuthCredential credential = EmailAuthProvider
                                    .getCredential(email, oldpas);


                            user.reauthenticate(credential)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                user.updatePassword(newpas).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            progressDialog.dismiss();
                                                            Toast.makeText(changePassword.this, "Passwords changed", Toast.LENGTH_LONG).show();
                                                            Intent intent = new Intent(changePassword.this, MainActivity.class);
                                                            startActivity(intent);
                                                            finish();
                                                        } else {
                                                            Toast.makeText(changePassword.this, "Sorry,could not change password", Toast.LENGTH_LONG).show();
                                                            Intent intent = new Intent(changePassword.this, MainActivity.class);
                                                            startActivity(intent);
                                                            finish();
                                                        }
                                                    }
                                                });
                                            } else {
                                                Toast.makeText(changePassword.this, "Authetication failed", Toast.LENGTH_LONG).show();
                                                Intent intent = new Intent(changePassword.this, MainActivity.class);
                                                startActivity(intent);
                                                finish();
                                            }
                                        }
                                    });
                        }
                else
                { Toast.makeText(changePassword.this,"Passwords do not match",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(changePassword.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                        }

                    }


        });

    }
}
