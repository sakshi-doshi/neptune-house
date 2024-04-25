package com.neptune.neptunehouse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginActivity extends AppCompatActivity {

    EditText emailId1,password1;
    TextView textView;
    Button logIn2;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailId1=findViewById(R.id.emailId2);
        password1=findViewById(R.id.password2);
        textView=findViewById(R.id.click);
        logIn2=findViewById(R.id.login2);
        fAuth= FirebaseAuth.getInstance();
        logIn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sEmail1=emailId1.getText().toString().trim();
                String sPassword1=password1.getText().toString().trim();
                if(TextUtils.isEmpty(sEmail1))
                {
                    emailId1.setError("PLEASE ENTER YOUR EMAIL");
                    return;
                }
                if(TextUtils.isEmpty(sPassword1))
                {
                    password1.setError("Please enter your password ");
                    return;
                }
                fAuth.signInWithEmailAndPassword(sEmail1,sPassword1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(loginActivity.this, "Logged in successfully!!", Toast.LENGTH_SHORT).show();
                            Intent intent =new Intent(loginActivity.this,homeActivity.class);
                            startActivity(intent);

                        }
                        else
                        {
                            Toast.makeText(loginActivity.this, "ERROR!!"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(loginActivity.this,signUpActivity.class);
                startActivity(intent);
            }
        });



    }
}