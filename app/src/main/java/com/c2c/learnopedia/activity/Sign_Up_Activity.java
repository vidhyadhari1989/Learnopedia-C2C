package com.c2c.learnopedia.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
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
import com.c2c.learnopedia.other.SpinnerAdapter;
import com.c2c.learnopedia.other.UrlUtils;

import org.json.JSONObject;

import java.util.HashMap;

import info.androidhive.navigationdrawer.R;

/**
 * Created by acer on 05-11-2016.
 */

public class Sign_Up_Activity extends AppCompatActivity {

    Button register_signup_btn,existing_user_btn;
    EditText register_firstname_et;
    EditText register_useremail_et;
    EditText register_usermobile_et;
    EditText register_lastname_et;
    RadioGroup radioGroup;
    String Gender="Male";
    Spinner isdcodes;
    String selectIsdCode;
    private String firstName,lastName,email,mobileNo,image="image";
    String MobilePattern = "[0-9]{10}";
    String EMAIL_STRING = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    String codes[] ={"91","44","01"};
    int[] images = {R.drawable.indiaflagicon,R.drawable.unitedkingdom,R.drawable.unitedstatesflagicon};
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_activity);
        intiUi();
       }

    private void intiUi() {
        register_firstname_et =(EditText)findViewById(R.id.first_name_et);
        register_lastname_et =(EditText)findViewById(R.id.last_name_et);
        register_useremail_et =(EditText)findViewById(R.id.email_et);
        register_usermobile_et =(EditText)findViewById(R.id.mobile_number_et);
        register_signup_btn =(Button)findViewById(R.id.signup_btn);
        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
        firstName=register_firstname_et.getText().toString();
        lastName=register_lastname_et.getText().toString();
        email=register_useremail_et.getText().toString();
        mobileNo=register_usermobile_et.getText().toString();
        existing_user_btn=(Button)findViewById(R.id.existing_user_btn);
        existing_user_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent login = new Intent(Sign_Up_Activity.this, LoginActivity.class);
                login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(login);
            }
        });

        register_signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(register_firstname_et.getText().toString().length()==0){
                    register_firstname_et.setError("First name not entered");
                    register_firstname_et.requestFocus();
                }if(register_lastname_et.getText().toString().length()==0){
                    register_lastname_et.setError("Last name not entered");
                    register_lastname_et.requestFocus();
                }if(register_useremail_et.getText().toString().length()==0){
                    register_useremail_et.setError("Email not entered");
                    register_useremail_et.requestFocus();
                }else if (!register_useremail_et.getText().toString().matches(EMAIL_STRING)) {
                    register_useremail_et.setError("Enter Your  Valid Email");
                    register_useremail_et.requestFocus();
                }
                if(register_usermobile_et.getText().toString().length()==0){
                    register_usermobile_et.setError("Mobileno is not entered");
                    register_usermobile_et.requestFocus();
                }else if(register_usermobile_et.getText().toString().length()<=0 && register_usermobile_et.getText().toString().length()<=9) {
                    register_usermobile_et.setError("Enter valid mobile number");
                    register_usermobile_et.requestFocus();
                }else{
//                    Intent i =new Intent(Sign_Up_Activity.this,Otp_Alctivity.class);
//                 startActivity(i);
                    registerUserDetails();

                }
            }
        });

        isdcodes = (Spinner) findViewById(R.id.countryCode);
        SpinnerAdapter adapter = new SpinnerAdapter(this,codes,images);
        isdcodes.setAdapter(adapter);
        isdcodes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selectIsdCode = isdcodes.getSelectedItem().toString();
                Log.v("kalyani", "selected code = " + selectIsdCode);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.male_radioButton:
                           Gender = "Male";

                        break;
                    case R.id.female_radioButton:
                        Gender = "Female";
                        break;

                }
            }
        });

    }

    private boolean validate() {
        if(register_firstname_et.getText().toString().length()==0){
            register_firstname_et.setError("First name not entered");
            register_firstname_et.requestFocus();
        }if(register_lastname_et.getText().toString().length()==0){
            register_lastname_et.setError("Last name not entered");
            register_lastname_et.requestFocus();
        }if(register_useremail_et.getText().toString().length()==0){
            register_useremail_et.setError("Email not entered");
            register_useremail_et.requestFocus();
        }else if (!register_useremail_et.getText().toString().matches(EMAIL_STRING)) {
            register_useremail_et.setError("Enter Your  Valid Email");
            register_useremail_et.requestFocus();
        }
        if(register_usermobile_et.getText().toString().length()==0){
            register_usermobile_et.setError("Mobileno is not entered");
            register_usermobile_et.requestFocus();
        }else if(register_usermobile_et.getText().toString().length()<=0 && register_usermobile_et.getText().toString().length()<=9) {
            register_usermobile_et.setError("Enter valid mobile number");
            register_usermobile_et.requestFocus();
        }


        return false;
    }


    private void registerUserDetails() {
        final ProgressDialog progressDialog1 = ProgressDialog.show(Sign_Up_Activity.this,"","Please wait..",false,false);
        HashMap<String, String> params = new HashMap<String, String>();

        params.put("FirstName",register_firstname_et.getText().toString());
        params.put("LastName",register_lastname_et.getText().toString());
        params.put("Email",register_useremail_et.getText().toString());
        params.put("MobileNo",selectIsdCode+register_usermobile_et.getText().toString());
        params.put("Gender",Gender);
        params.put("Image",image);
        Log.v("kal1",firstName);


       // params.put("SubjectCode",courseCode);

        JSONObject jsonObject = new JSONObject(params);
        Log.v("Learnopedia", "Request parames : " + jsonObject.toString());
        Log.v("Learnopedia", "Requesting url :" +  UrlUtils.User_registration);
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, UrlUtils.User_registration, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog1.dismiss();
                        Log.v("TAG", "register patient " + response);
                        try {
                            if ( response!=null) {
                                if(response.getInt("Status")==1) {

                                    Toast.makeText(Sign_Up_Activity.this, "User register  successfully", Toast.LENGTH_LONG).show();
                                    finish();
                                }


                            }
                            else {
                                Toast.makeText(Sign_Up_Activity.this, "user  is not register  successfully", Toast.LENGTH_LONG).show();

                                if (progressDialog1!=null)
                                    progressDialog1.dismiss();

                            }



                        } catch (Exception e) {
                            e.printStackTrace();
                            progressDialog1.dismiss();
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
                            progressDialog1.dismiss();
                        }

                    }
                }
        );
        RetryPolicy policy = new DefaultRetryPolicy(AppConstance.SPLASH_DISPLAY_TIME,DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        req.setRetryPolicy(policy);
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(req);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent login = new Intent(Sign_Up_Activity.this, LoginActivity.class);
        login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(login);

    }
}




