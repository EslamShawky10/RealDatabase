package com.eslamshawky.hp.realdatabase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    private EditText email,password;
    private Button signUp;
    private TextView alreadyAccount;
    private String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.signUp_Email);
        password = findViewById(R.id.signUp_password);
        signUp = findViewById(R.id.signUp);
        alreadyAccount = findViewById(R.id.creat_account);
        alreadyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this,LogInActivity.class);
                startActivity(intent);
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUpUser(email.getText().toString(),password.getText().toString());
            }
        });
    }

    private void signUpUser(String Email,String PassWord){
        mAuth.createUserWithEmailAndPassword(Email,PassWord).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(SignUpActivity.this, " Successful....", Toast.LENGTH_SHORT).show();
                }
                else {
                    Log.e(TAG,task.getException().getMessage());
                    Toast.makeText(SignUpActivity.this," Error in Register...",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
