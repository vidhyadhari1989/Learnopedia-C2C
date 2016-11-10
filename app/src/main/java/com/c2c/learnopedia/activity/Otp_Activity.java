package com.c2c.learnopedia.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.c2c.learnopedia.other.AppConstance;
import com.c2c.learnopedia.other.UrlUtils;

import org.json.JSONObject;

import java.util.HashMap;

import info.androidhive.navigationdrawer.R;


public class Otp_Activity extends Activity {
    Button resend_otp_btn,verify_otp_btn;
    EditText verifty_opt_et;
    TextView mobileNo_tv;
    String mobileNo,mobileNo_hint;
    private String deviceregid="-1";

    ProgressDialog progressDialog;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp_activity);
        resend_otp_btn=(Button)findViewById(R.id.resend_otp_btn);
        verify_otp_btn=(Button)findViewById(R.id.verify_otp_btn);
        verifty_opt_et=(EditText)findViewById(R.id.verifty_opt_et);
        mobileNo_tv=(TextView)findViewById(R.id.mobile_number_hint);

        Bundle extras = getIntent().getExtras();
         mobileNo = extras.getString("MOBILE_NUMBER");
        mobileNo_hint =" OTP sent to "+mobileNo+"\n" +" If you do not receive your OTP within 30seconds, you may request for another OTP by clicking on the 'Resend OTP' button.";
        mobileNo_tv.setText(mobileNo_hint);
        verify_otp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (verifty_opt_et.getText().toString().length() == 0) {
                    verifty_opt_et.setError("enter the otp");
                    verifty_opt_et.requestFocus();
                } else if (verifty_opt_et.getText().toString().length() <=0 && verifty_opt_et.getText().toString().length() <= 3) {
                    verifty_opt_et.setError("enter valid otp");
                    verifty_opt_et.requestFocus();

                } else {
                        VerifyMobileNumber(mobileNo,verifty_opt_et.getText().toString(),deviceregid);
                }
            }
        });

    }

    private void VerifyMobileNumber(final String mobileNo, String otp,String deviceregid) {
        progressDialog=new ProgressDialog(Otp_Activity.this);
        progressDialog.setMessage("Please wait..");
        progressDialog.show();

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("strMobile", mobileNo);
        params.put("strOTP",otp );
        params.put("deviceregid",deviceregid);

        JSONObject jsonObject = new JSONObject(params);
        Log.v(AppConstance.TAG, "Request String:" + jsonObject.toString());
        Log.v(AppConstance.TAG, "Requesting String in SignUp :" + UrlUtils.GetVerify_mobile+"/"+mobileNo+"/"+otp+"/"+deviceregid);
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, UrlUtils.GetVerify_mobile+"/"+mobileNo+"/"+otp+"/"+deviceregid, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();
                        Log.v("TAG", "register User" + response);
                        try {
                            if ( response!=null) {
                                if(response.getInt("Status")==1) {
                                    Log.v("kalyaniResponse","kal"+response);
                                    Intent i=new Intent(Otp_Activity.this,MainActivity.class);
                                    startActivity(i);

                                    Toast.makeText(Otp_Activity.this, "User register  successfully", Toast.LENGTH_LONG).show();
                                    finish();

                                }

                            }
                            else {
                                Toast.makeText(Otp_Activity.this, "user  is not register  successfully", Toast.LENGTH_LONG).show();

                                if (progressDialog!=null)
                                    progressDialog.dismiss();

                            }



                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(Otp_Activity.this, "user  is not register  successfully", Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        try {
                            Log.d("Error.Response", error.getMessage());
                        } catch (Exception e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                        }

                    }
                }
        );
        RetryPolicy policy = new DefaultRetryPolicy(AppConstance.SPLASH_DISPLAY_TIME,DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        req.setRetryPolicy(policy);
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(req);
    }


}

