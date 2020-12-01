package com.example.phoneauth;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class HomePage extends AppCompatActivity {
    private static final String TAG ="Main Activty" ;
    ArrayList<eachquestion> questions;
    private RequestQueue mQueue;
    public TextView ques_num1;
    public TextView ques_num2;
    public TextView ques_num3;
    public TextView ques_num4;
    public TextView ques_num5;
    public TextView ques_num6;
    public TextView ques_num7;
    public TextView ques_num8;
    public TextView ques_num9;
    public TextView ques_num10;

    public TextView ques1;
    public TextView ques2;
    public TextView ques3;
    public TextView ques4;
    public TextView ques5;
    public TextView ques6;
    public TextView ques7;
    public TextView ques8;
    public TextView ques9;
    public TextView ques10;

    public RadioButton a1;
    public RadioButton a2;
    public RadioButton a3;
    public RadioButton a4;
    public RadioButton a5;
    public RadioButton a6;
    public RadioButton a7;
    public RadioButton a8;
    public RadioButton a9;
    public RadioButton a10;

    public RadioButton b1;
    public RadioButton b2;
    public RadioButton b3;
    public RadioButton b4;
    public RadioButton b5;
    public RadioButton b6;
    public RadioButton b7;
    public RadioButton b8;
    public RadioButton b9;
    public RadioButton b10;

    public RadioButton c1;
    public RadioButton c2;
    public RadioButton c3;
    public RadioButton c4;
    public RadioButton c5;
    public RadioButton c6;
    public RadioButton c7;
    public RadioButton c8;
    public RadioButton c9;
    public RadioButton c10;

    public RadioButton d1;
    public RadioButton d2;
    public RadioButton d3;
    public RadioButton d4;
    public RadioButton d5;
    public RadioButton d6;
    public RadioButton d7;
    public RadioButton d8;
    public RadioButton d9;
    public RadioButton d10;

    public Button save1;
    public Button save2;
    public Button save3;
    public Button save4;
    public Button save5;
    public Button save6;
    public Button save7;
    public Button save8;
    public Button save9;
    public Button save10;

    public RadioGroup rg1;
    public RadioGroup rg2;
    public RadioGroup rg3;
    public RadioGroup rg4;
    public RadioGroup rg5;
    public RadioGroup rg6;
    public RadioGroup rg7;
    public RadioGroup rg8;
    public RadioGroup rg9;
    public RadioGroup rg10;

    public RadioButton r1;
    int totalscore=0;
    public Button submit;
    public String phone;
    public String name;
    public String usn;
    public String sem;
    public String sec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        questions= new ArrayList<eachquestion>();
        mQueue= Volley.newRequestQueue(this);
        jsonParse();
        submit=findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone=getIntent().getStringExtra("PHONE").trim();
                name = getIntent().getStringExtra("NAME").trim();
                usn=getIntent().getStringExtra("USN").trim();
                sem=getIntent().getStringExtra("SEM").trim();
                sec=getIntent().getStringExtra("SEC").trim();
                addItemtosheet();
                FirebaseAuth.getInstance().signOut();
                finish();


            }
        });
    }
    @Override
    public void onBackPressed() {
        Toast.makeText(this,"You Can't Go Back",Toast.LENGTH_LONG).show();
    }

    private void jsonParse(){
        String url="https://script.googleusercontent.com/macros/echo?user_content_key=1FE11Jlr4EZGov07k1cJIjCqdYqhPA2xVVRHs74KRwwpJevg7W_UKw5fJafwdfKsR3FR4RVAJNaFLn1-4w-2qE3mjzsNuQ-2OJmA1Yb3SEsKFZqtv3DaNYcMrmhZHmUMWojr9NvTBuBLhyHCd5hHa1GhPSVukpSQTydEwAEXFXgt_wltjJcH3XHUaaPC1fv5o9XyvOto09QuWI89K6KjOu0SP2F-BdwUzx1qJeNhDPC0ot0ZmnGR2QeK2ePn8F22rU_KuR8TVlnjdjzMAAwvTDHIgkvOLUTN5y7FLqOV0Tk27B8Rh4QJTQ&lib=MnrE7b2I2PjfH799VodkCPiQjIVyBAxva";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray=response.getJSONArray("Sheet1");

                            Random rand = new Random();

                            for(int k=0;k<10;k++) {
                                int n = rand.nextInt(26);
                                JSONObject q = jsonArray.getJSONObject(n);
                                int ques_num = q.getInt("Question_No.");
                                String ques = q.getString("Question");
                                String Option_A = q.getString("Option_A");
                                String Option_B = q.getString("Option_B");
                                String Option_C = q.getString("Option_C");
                                String Option_D = q.getString("Option_D");
                                int answer = q.getInt("Answer");
                                String c = null;
                                if (answer==1){
                                    c=Option_A;
                                }else if(answer==2){
                                    c=Option_B;
                                }else if (answer==3){
                                    c=Option_C;
                                }else if(answer==4){
                                    c=Option_D;
                                }
                                questions.add(new eachquestion(String.valueOf(ques_num),ques,Option_A,Option_B,Option_C,Option_D,c));

                            }

                            ques_num1 = findViewById(R.id.ques_num1);
                            ques1 = findViewById(R.id.ques1);
                            a1 = findViewById(R.id.optionA1);

                            b1 = findViewById(R.id.optionB1);

                            c1 = findViewById(R.id.optionC1);

                            d1 = findViewById(R.id.optionD1);

                            ques_num1.setText(questions.get(0).getMquestion_num());
                            ques1.setText(questions.get(0).getMquestion());
                            a1.setText(questions.get(0).getMoptionA());

                            b1.setText(questions.get(0).getMoptionB());

                            c1.setText(questions.get(0).getMoptionC());

                            d1.setText(questions.get(0).getMoptionD());


                            ques_num2 = findViewById(R.id.ques_num2);
                            ques2 = findViewById(R.id.ques2);
                            a2 = findViewById(R.id.optionA2);
                            //a2.setTag("1");
                            b2 = findViewById(R.id.optionB2);
                            //b2.setTag("2");
                            c2 = findViewById(R.id.optionC2);
                            //c2.setTag("3");
                            d2 = findViewById(R.id.optionD2);
                            //d2.setTag("4");
                            ques_num2.setText(questions.get(0).getMquestion_num());
                            ques2.setText(questions.get(1).getMquestion());
                            a2.setText(questions.get(1).getMoptionA());
                            b2.setText(questions.get(1).getMoptionB());
                            c2.setText(questions.get(1).getMoptionC());
                            d2.setText(questions.get(1).getMoptionD());

                            ques_num3 = findViewById(R.id.ques_num3);
                            ques3 = findViewById(R.id.ques3);
                            a3 = findViewById(R.id.optionA3);
                            //a3.setTag("1");
                            b3 = findViewById(R.id.optionB3);
                            //b3.setTag("2");
                            c3 = findViewById(R.id.optionC3);
                            //c3.setTag("3");
                            d3 = findViewById(R.id.optionD3);
                            //d3.setTag("4");
                            ques_num3.setText(questions.get(2).getMquestion_num());
                            ques3.setText(questions.get(2).getMquestion());
                            a3.setText(questions.get(2).getMoptionA());
                            b3.setText(questions.get(2).getMoptionB());
                            c3.setText(questions.get(2).getMoptionC());
                            d3.setText(questions.get(2).getMoptionD());

                            ques_num4 = findViewById(R.id.ques_num4);
                            ques4 = findViewById(R.id.ques4);
                            a4 = findViewById(R.id.optionA4);
                            //a4.setTag("1");
                            b4 = findViewById(R.id.optionB4);
                            //b4.setTag("2");
                            c4 = findViewById(R.id.optionC4);
                            //c4.setTag("3");
                            d4 = findViewById(R.id.optionD4);
                            //d4.setTag("4");
                            ques_num4.setText(questions.get(3).getMquestion_num());
                            ques4.setText(questions.get(3).getMquestion());
                            a4.setText(questions.get(3).getMoptionA());
                            b4.setText(questions.get(3).getMoptionB());
                            c4.setText(questions.get(3).getMoptionC());
                            d4.setText(questions.get(3).getMoptionD());

                            ques_num5 = findViewById(R.id.ques_num5);
                            ques5 = findViewById(R.id.ques5);
                            a5 = findViewById(R.id.optionA5);
                            //a5.setTag("1");
                            b5 = findViewById(R.id.optionB5);
                            //b5.setTag("2");
                            c5 = findViewById(R.id.optionC5);
                            //c5.setTag("3");
                            d5 = findViewById(R.id.optionD5);
                            //d5.setTag("4");
                            ques_num5.setText(questions.get(4).getMquestion_num());
                            ques5.setText(questions.get(4).getMquestion());
                            a5.setText(questions.get(4).getMoptionA());
                            b5.setText(questions.get(4).getMoptionB());
                            c5.setText(questions.get(4).getMoptionC());
                            d5.setText(questions.get(4).getMoptionD());

                            ques_num6 = findViewById(R.id.ques_num6);
                            ques6 = findViewById(R.id.ques6);
                            a6 = findViewById(R.id.optionA6);
                            //a6.setTag("1");
                            b6 = findViewById(R.id.optionB6);
                            //b6.setTag("2");
                            c6 = findViewById(R.id.optionC6);
                            //c6.setTag("3");
                            d6  = findViewById(R.id.optionD6);
                            //d6.setTag("4");
                            ques_num6.setText(questions.get(5).getMquestion_num());
                            ques6.setText(questions.get(5).getMquestion());
                            a6.setText(questions.get(5).getMoptionA());
                            b6.setText(questions.get(5).getMoptionB());
                            c6.setText(questions.get(5).getMoptionC());
                            d6.setText(questions.get(5).getMoptionD());

                            ques_num7 = findViewById(R.id.ques_num7);
                            ques7 = findViewById(R.id.ques7);
                            a7 =findViewById(R.id.optionA7);
                            //a7.setTag("1");
                            b7 = findViewById(R.id.optionB7);
                            //b7.setTag("2");
                            c7 = findViewById(R.id.optionC7);
                            //c7.setTag("3");
                            d7 = findViewById(R.id.optionD7);
                            //d7.setTag("4");
                            ques_num7.setText(questions.get(6).getMquestion_num());
                            ques7.setText(questions.get(6).getMquestion());
                            a7.setText(questions.get(6).getMoptionA());
                            b7.setText(questions.get(6).getMoptionB());
                            c7.setText(questions.get(6).getMoptionC());
                            d7.setText(questions.get(6).getMoptionD());

                            ques_num8 = findViewById(R.id.ques_num8);
                            ques8 = findViewById(R.id.ques8);
                            a8 = findViewById(R.id.optionA8);
                            //a8.setTag("1");
                            b8 = findViewById(R.id.optionB8);
                            //b8.setTag("2");
                            c8 = findViewById(R.id.optionC8);
                            //c8.setTag("3");
                            d8 = findViewById(R.id.optionD8);
                            //d8.setTag("4");
                            ques_num8.setText(questions.get(7).getMquestion_num());
                            ques8.setText(questions.get(7).getMquestion());
                            a8.setText(questions.get(7).getMoptionA());
                            b8.setText(questions.get(7).getMoptionB());
                            c8.setText(questions.get(7).getMoptionC());
                            d8.setText(questions.get(7).getMoptionD());

                            ques_num9 = findViewById(R.id.ques_num9);
                            ques9 = findViewById(R.id.ques9);
                            a9 = findViewById(R.id.optionA9);
                            //a9.setTag("1");
                            b9 = findViewById(R.id.optionB9);
                            //b9.setTag("2");
                            c9 = findViewById(R.id.optionC9);
                            //c9.setTag("3");
                            d9 = findViewById(R.id.optionD9);
                            //d9.setTag("4");
                            ques_num9.setText(questions.get(8).getMquestion_num());
                            ques9.setText(questions.get(8).getMquestion());
                            a9.setText(questions.get(8).getMoptionA());
                            b9.setText(questions.get(8).getMoptionB());
                            c9.setText(questions.get(8).getMoptionC());
                            d9.setText(questions.get(8).getMoptionD());

                            ques_num10 = findViewById(R.id.ques_num10);
                            ques10 = findViewById(R.id.ques10);
                            a10 = findViewById(R.id.optionA10);
                            //a10.setTag("1");
                            b10 = findViewById(R.id.optionB10);
                            //b10.setTag("2");
                            c10  = findViewById(R.id.optionC10);
                            //c10.setTag("3");
                            d10 = findViewById(R.id.optionD10);
                            //d10.setTag("4");
                            ques_num10.setText(questions.get(9).getMquestion_num());
                            ques10.setText(questions.get(9).getMquestion());
                            a10.setText(questions.get(9).getMoptionA());
                            b10.setText(questions.get(9).getMoptionB());
                            c10.setText(questions.get(9).getMoptionC());
                            d10.setText(questions.get(9).getMoptionD());



                            rg1 = (RadioGroup) findViewById(R.id.radioUser1);
                            save1 = (Button) findViewById(R.id.save1);
                            save1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    int selectedId = rg1.getCheckedRadioButtonId();
                                    String ca=questions.get(0).getMcorrectansr();
                                    RadioButton radioButton1 =(RadioButton)findViewById(selectedId);
                                    String marked= (String) radioButton1.getText().toString();
                                    a1.setEnabled(false);
                                    b1.setEnabled(false);
                                    c1.setEnabled(false);
                                    d1.setEnabled(false);
                                    if ((ca.equals(marked))){
                                        totalscore+=1;
                                    }

                                }
                            });

                            rg2 = (RadioGroup) findViewById(R.id.radioUser2);
                            save2 = (Button) findViewById(R.id.save2);
                            save2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    int selectedId = rg2.getCheckedRadioButtonId();
                                    String ca=questions.get(1).getMcorrectansr();
                                    RadioButton radioButton2 =(RadioButton)findViewById(selectedId);
                                    String marked= (String) radioButton2.getText().toString();
                                    a2.setEnabled(false);
                                    b2.setEnabled(false);
                                    c2.setEnabled(false);
                                    d2.setEnabled(false);
                                    if ((ca.equals(marked))){
                                        totalscore+=1;
                                    }

                                }
                            });
                            rg3 = (RadioGroup) findViewById(R.id.radioUser3);
                            save3 = (Button) findViewById(R.id.save3);
                            save3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    int selectedId = rg3.getCheckedRadioButtonId();
                                    String ca=questions.get(3).getMcorrectansr();
                                    RadioButton radioButton3 =(RadioButton)findViewById(selectedId);
                                    String marked= (String) radioButton3.getText().toString();
                                    a3.setEnabled(false);
                                    b3.setEnabled(false);
                                    c3.setEnabled(false);
                                    d3.setEnabled(false);
                                    if ((ca.equals(marked))){
                                        totalscore+=1;
                                    }

                                }
                            });
                            rg4 = (RadioGroup) findViewById(R.id.radioUser4);
                            save4 = (Button) findViewById(R.id.save4);
                            save4.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    int selectedId = rg4.getCheckedRadioButtonId();
                                    String ca=questions.get(3).getMcorrectansr();
                                    RadioButton radioButton4 =(RadioButton)findViewById(selectedId);
                                    String marked= (String) radioButton4.getText().toString();
                                    a4.setEnabled(false);
                                    b4.setEnabled(false);
                                    c4.setEnabled(false);
                                    d4.setEnabled(false);
                                    if ((ca.equals(marked))){
                                        totalscore+=1;
                                    }

                                }
                            });
                            rg5 = (RadioGroup) findViewById(R.id.radioUser5);
                            save5 = (Button) findViewById(R.id.save5);

                            save5.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    int selectedId = rg5.getCheckedRadioButtonId();
                                    String ca=questions.get(4).getMcorrectansr();
                                    RadioButton radioButton5 =(RadioButton)findViewById(selectedId);
                                    String marked= (String) radioButton5.getText().toString();
                                    a5.setEnabled(false);
                                    b5.setEnabled(false);
                                    c5.setEnabled(false);
                                    d5.setEnabled(false);
                                    if ((ca.equals(marked))){
                                        totalscore+=1;
                                    }

                                }
                            });
                            rg6 = (RadioGroup) findViewById(R.id.radioUser6);
                            save6 = (Button) findViewById(R.id.save6);
                            save6.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    int selectedId = rg6.getCheckedRadioButtonId();
                                    String ca=questions.get(5).getMcorrectansr();
                                    RadioButton radioButton6 =(RadioButton)findViewById(selectedId);
                                    String marked= (String) radioButton6.getText().toString();
                                    a6.setEnabled(false);
                                    b6.setEnabled(false);
                                    c6.setEnabled(false);
                                    d6.setEnabled(false);
                                    if ((ca.equals(marked))){
                                        totalscore+=1;
                                    }

                                }
                            });
                            rg7 = (RadioGroup) findViewById(R.id.radioUser7);
                            save7 = (Button) findViewById(R.id.save7);
                            save7.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    int selectedId = rg7.getCheckedRadioButtonId();
                                    String ca=questions.get(6).getMcorrectansr();
                                    RadioButton radioButton7 =(RadioButton)findViewById(selectedId);
                                    String marked= (String) radioButton7.getText().toString();
                                    a7.setEnabled(false);
                                    b7.setEnabled(false);
                                    c7.setEnabled(false);
                                    d7.setEnabled(false);
                                    if ((ca.equals(marked))){
                                        totalscore+=1;
                                    }

                                }
                            });
                            rg8 = (RadioGroup) findViewById(R.id.radioUser8);
                            save8 = (Button) findViewById(R.id.save8);
                            save8.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    int selectedId = rg8.getCheckedRadioButtonId();
                                    String ca=questions.get(7).getMcorrectansr();
                                    RadioButton radioButton8 =(RadioButton)findViewById(selectedId);
                                    String marked= (String) radioButton8.getText().toString();
                                    a8.setEnabled(false);
                                    b8.setEnabled(false);
                                    c8.setEnabled(false);
                                    d8.setEnabled(false);
                                    if ((ca.equals(marked))){
                                        totalscore+=1;
                                    }

                                }
                            });
                            rg9 = (RadioGroup) findViewById(R.id.radioUser9);
                            save9 = (Button) findViewById(R.id.save9);
                            save9.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    int selectedId = rg9.getCheckedRadioButtonId();
                                    String ca=questions.get(8).getMcorrectansr();
                                    RadioButton radioButton9 =(RadioButton)findViewById(selectedId);
                                    String marked= (String) radioButton9.getText().toString();
                                    a9.setEnabled(false);
                                    b9.setEnabled(false);
                                    c9.setEnabled(false);
                                    d9.setEnabled(false);
                                    if ((ca.equals(marked))){
                                        totalscore+=1;
                                    }

                                }
                            });
                            rg10 = (RadioGroup) findViewById(R.id.radioUser10);
                            save10 = (Button) findViewById(R.id.save10);
                            save10.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    int selectedId = rg10.getCheckedRadioButtonId();
                                    String ca=questions.get(9).getMcorrectansr();
                                    RadioButton radioButton10 =(RadioButton)findViewById(selectedId);
                                    String marked= (String) radioButton10.getText().toString();
                                    a10.setEnabled(false);
                                    b10.setEnabled(false);
                                    c10.setEnabled(false);
                                    d10.setEnabled(false);
                                    if ((ca.equals(marked))){
                                        totalscore+=1;
                                    }

                                }
                            });





                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        });
        mQueue.add(request);


    }
    private void addItemtosheet() {
        final ProgressDialog loading = ProgressDialog.show(this,"Adding Item","Please wait");


        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbw2IzTb8Wfu5rCKhc3y-A6Z37UHZ4BlyPE2Uh_-K8L4QymlaFo/exec",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        loading.dismiss();
                        //Toast.makeText(quesActivity.this,response,Toast.LENGTH_LONG).show();
//                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
//                        startActivity(intent);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parmas = new HashMap<>();

                //here we pass params
                parmas.put("action","addItem");
                parmas.put("NAME",name);
                parmas.put("USN",usn);
                parmas.put("SEM",sem);
                parmas.put("SEC",sec);
                parmas.put("MOBILE",phone);
                parmas.put("TOTALSCORE",String.valueOf(totalscore));

                return parmas;
            }
        };

        int socketTimeOut = 50000;// u can change this .. here it is 50 seconds

        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);

        RequestQueue queue = Volley.newRequestQueue(this);

        queue.add(stringRequest);



    }



}