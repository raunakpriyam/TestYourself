package com.example.phoneauth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Login extends AppCompatActivity {
    private Button btnLogin;
    private EditText etPhoneNumber,name,usn,sec,sem;
    String name_text,usn_text,sec_text,sem_text,phoneNumber;
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnLogin);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        name=(EditText)findViewById(R.id.name_edit);
        usn=(EditText)findViewById(R.id.usn_edit);
        sec=(EditText)findViewById(R.id.sec_edit);
        sem=(EditText)findViewById(R.id.sem_edit);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //verify phone number
                phoneNumber = etPhoneNumber.getText().toString();
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91"+phoneNumber, 60, TimeUnit.SECONDS, Login.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                                signInUser(phoneAuthCredential);
                            }

                            @Override
                            public void onVerificationFailed(FirebaseException e) {

                                Toast.makeText(Login.this,"Wrong Details",Toast.LENGTH_LONG);
                            }

                            @Override
                            public void onCodeSent(final String verificationId, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(verificationId, forceResendingToken);
                                //
                                Dialog dialog = new Dialog(Login.this);
                                dialog.setContentView(R.layout.verify_popup);

                                final EditText etVerifyCode = dialog.findViewById(R.id.etVerifyCode);
                                Button btnVerifyCode = dialog.findViewById(R.id.btnVerifyOTP);
                                btnVerifyCode.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        String verificationCode = etVerifyCode.getText().toString();
                                        if(verificationId.isEmpty())
                                            return;
                                        //create a credential
                                        PhoneAuthCredential credential=PhoneAuthProvider.getCredential(verificationId,verificationCode);
                                        signInUser(credential);
                                    }
                                });

                                dialog.show();
                                Window window = dialog.getWindow();
                                window.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
                            }
                        });
            }
        });

    }

    private void signInUser(PhoneAuthCredential credential) {
        FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent intent=new Intent(Login.this,HomePage.class);
                            intent.putExtra("PHONE",phoneNumber);
                            intent.putExtra("NAME",name.getText().toString());
                            intent.putExtra("USN",usn.getText().toString());
                            intent.putExtra("SEM",sem.getText().toString());
                            intent.putExtra("SEC",sec.getText().toString());
                            startActivity(intent);
                            finish();
                        }else {
                            Log.d(TAG, "onComplete:"+task.getException().getLocalizedMessage());
                        }
                    }
                });
    }

}