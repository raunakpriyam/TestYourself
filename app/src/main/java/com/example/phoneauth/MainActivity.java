package com.example.phoneauth;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin;
    private EditText etPhoneNumber,name,usn,sec,sem;
    String name_text,usn_text,sec_text,sem_text,phoneNumber;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                        "+91"+phoneNumber, 60, TimeUnit.SECONDS, MainActivity.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                                signInUser(phoneAuthCredential);
                            }

                            @Override
                            public void onVerificationFailed(FirebaseException e) {
                                Log.d(TAG, "onVerificationFailed:"+e.getLocalizedMessage());
                                Toast.makeText(MainActivity.this,"Wrong Details",Toast.LENGTH_LONG);
                            }

                            @Override
                            public void onCodeSent(final String verificationId, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(verificationId, forceResendingToken);
                                    //
                                Dialog dialog = new Dialog(MainActivity.this);
                                dialog.setContentView(R.layout.verify_popup);

                                final EditText etVerifyCode = dialog.findViewById(R.id.etVerifyCode);
                                Button btnVerifyCode = dialog.findViewById(R.id.btnVerifyOTP);
                                btnVerifyCode.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        String verificationCode = etVerifyCode.getText().toString();
                                        if(verificationId.isEmpty()) return;
                                            //create a credential
                                        PhoneAuthCredential credential=PhoneAuthProvider.getCredential(verificationId,verificationCode);
                                        signInUser(credential);
                                    }
                                });

                                dialog.show();
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
                            Intent intent=new Intent(MainActivity.this,HomePage.class);
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