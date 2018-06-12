package com.theworkingbros.ak.assist.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.theworkingbros.ak.assist.R;

public class emailverify extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseUser user;

Button login,verify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emailverify);
        login=findViewById(R.id.login);
        verify=findViewById(R.id.verify);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        if(user.isEmailVerified())
            startActivity(new Intent(emailverify.this,MainActivity.class));
      else{ user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(emailverify.this,"Check email for verification link.",Toast.LENGTH_SHORT).show();

                        }
                        else{
                            Toast.makeText(emailverify.this,"Failed to send verification email.",Toast.LENGTH_SHORT).show();
                        }

                    }
                });
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mAuth.getCurrentUser().reload();
                if(user.isEmailVerified())
                {
                    Toast.makeText(emailverify.this,"Email verified",Toast.LENGTH_SHORT).show();
                    Intent main=new Intent(emailverify.this,MainActivity.class);
                    startActivity(main);
                }
                else{
                    Toast.makeText(emailverify.this,"Email not verified",Toast.LENGTH_SHORT).show();
                }

            }
        });

  /*    user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(emailverify.this,"Check email for verification link.",Toast.LENGTH_SHORT).show();

                        }
                        else{
                            Toast.makeText(emailverify.this,"Failed to send verification email.",Toast.LENGTH_SHORT).show();
                        }

                    }
                });*/
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (user != null && mAuth != null) {
                    mAuth.signOut();
                    Intent post = new Intent(emailverify.this, LoginPage.class);
                    startActivity(post);
                    finish();
                }
            }
        });
    }}@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.emailverify,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_signout:
                if (user != null && mAuth != null) {
                    mAuth.signOut();
                    Intent post = new Intent(emailverify.this, LoginPage.class);
                    startActivity(post);
                    finish();
                }


                break;

        }

        return super.onOptionsItemSelected(item);
    }

}
