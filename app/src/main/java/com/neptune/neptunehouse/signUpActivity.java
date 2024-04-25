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

public class signUpActivity extends AppCompatActivity {
  EditText name,emailId,password,address;
  Button btn_signUp1;
  TextView signIn;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        name=findViewById(R.id.Name);
        emailId=findViewById(R.id.emailId);
        password=findViewById(R.id.signUp_password);
        address=findViewById(R.id.address);
        btn_signUp1=findViewById(R.id.btn_signUp1);
        signIn=findViewById(R.id.signIn);
        fAuth=FirebaseAuth.getInstance();
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(signUpActivity.this,loginActivity.class);
                startActivity(intent);
            }
        });
        btn_signUp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sEmail=emailId.getText().toString().trim();
                String sPassword=password.getText().toString().trim();
                String sAddress =address.getText().toString().trim();
                String sName=name.getText().toString().trim();
                if(TextUtils.isEmpty(sName))
                {
                    name.setError("Please enter your name");
                    return;
                }
                if(TextUtils.isEmpty(sAddress))
                {
                    address.setError("Please enter your address");
                    return;
                }
                if(TextUtils.isEmpty(sEmail))
                {
                    emailId.setError("PLEASE ENTER YOUR EMAIL");
                    return;
                }

                if(TextUtils.isEmpty(sPassword))
                {
                    password.setError("Please enter your password ");
                    return;
                }
                if(sPassword.length()<6)
                {
                    password.setError("Password must be of 6 letters ");
                }
                fAuth.createUserWithEmailAndPassword(sEmail,sPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(signUpActivity.this, "User created", Toast.LENGTH_SHORT).show();
                            Intent intent =new Intent(signUpActivity.this,loginActivity.class);
                            startActivity(intent);


                        }
                        else
                        {
                            Toast.makeText(signUpActivity.this, "ERROR!!"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });






    }
}