package com.c2c.learnopedia.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
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

public class LoginActivity extends AppCompatActivity  {


    private EditText mobile_number_ed;
    private View mProgressView;
    private Button login_button,signup_button;
    Spinner isdcodes;
    String selectIsdCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mobile_number_ed = (EditText)findViewById(R.id.mobile_number_ed);
        login_button= (Button)findViewById(R.id.login_in_button);
        signup_button= (Button)findViewById(R.id.sign_up_button);
        String codes[] ={"91","44","01"};
        int[] images = {R.drawable.indiaflagicon,R.drawable.unitedkingdom,R.drawable.unitedstatesflagicon};
        login_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mobile_number_ed.getText().toString().length()==0){
                    mobile_number_ed.setError("mobile number is required");
                    mobile_number_ed.requestFocus();
                }else if(mobile_number_ed.getText().toString().length()<=0&&mobile_number_ed.getText().toString().length()<=9){
                    mobile_number_ed.setError("mobile number is invalid");
                    mobile_number_ed.requestFocus();
                }else{
                    userRegisterFromServer(selectIsdCode+mobile_number_ed.getText().toString());
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


        signup_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, Sign_Up_Activity.class);
                startActivity(intent);
            }
        });
    }

    private void userRegisterFromServer(final String mobile) {
        final ProgressDialog loading = ProgressDialog.show(this,"Login...","Please wait...",false,false);
        HashMap<String, String> params = new HashMap<String, String>();
        JSONObject jsonObject = new JSONObject(params);
        Log.v(AppConstance.TAG, "Request String:" + jsonObject.toString());
        Log.v(AppConstance.TAG, "Requesting String in SignUp :" + UrlUtils.Verify_user_mobile+mobile);
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, UrlUtils.Verify_user_mobile+mobile,jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        loading.dismiss();
                        Log.v("TAG", "register user " + response);
                        try {
                            Log.v(AppConstance.TAG, "Response:" + response.toString());
                            if ( response!=null) {
                                if(response.getInt("Status")==1) {

                                    Intent verifyOTP = new Intent(LoginActivity.this, Otp_Activity.class);
                                    Log.v(AppConstance.TAG,"MOBILE NO in SIGNIN ACTIVITY"+mobile);
                                    verifyOTP.putExtra("MOBILE_NUMBER",mobile);
                                    startActivity(verifyOTP);
                                    finish();
                                }else if (response.getInt("Status")==0){

                                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(LoginActivity.this);

                                    // Setting Dialog Title
                                    alertDialog.setTitle("Please Register!!");

                                    // Setting Dialog Message
                                    alertDialog.setMessage("Mobile Number not registered, Please Register!!");

                                    // Setting Icon to Dialog
//                                    alertDialog.setIcon(R.drawable.save);

                                    // Setting Positive "Yes" Button
                                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            // User pressed YES button. Write Logic Here
                                            /*Toast.makeText(getApplicationContext(), "You clicked on YES",
                                                    Toast.LENGTH_SHORT).show();*/
                                        }
                                    });

                                 // Showing Alert Message
                                    alertDialog.show();

                                }
                                if(response.optString("responseId").equalsIgnoreCase("0")){
                                    Toast.makeText(LoginActivity.this,""+response.getString("responseText"),Toast.LENGTH_LONG).show();

                                }


                            }
                            else {
                                Toast.makeText(LoginActivity.this, "user  is not register ", Toast.LENGTH_LONG).show();

                                if (loading!=null)
                                    loading.dismiss();

                            }



                        } catch (Exception e) {
                            e.printStackTrace();
                            loading.dismiss();
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
                            loading.dismiss();
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

////        mLoginFormView = findViewById(R.id.login_form);
//        mProgressView = findViewById(R.id.login_progress);
//    }
//
//    private void populateAutoComplete() {
//        if (!mayRequestContacts()) {
//            return;
//        }
//
//        getLoaderManager().initLoader(0, null, this);
//    }
//
//    private boolean mayRequestContacts() {
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
//            return true;
//        }
//        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
//            return true;
//        }
//        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
//            Snackbar.make(mobile_number_ed, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
//                    .setAction(android.R.string.ok, new View.OnClickListener() {
//                        @Override
//                        @TargetApi(Build.VERSION_CODES.M)
//                        public void onClick(View v) {
//                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
//                        }
//                    });
//        } else {
//            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
//        }
//        return false;
//    }
//
//    /**
//     * Callback received when a permissions request has been completed.
//     */
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
//                                           @NonNull int[] grantResults) {
//        if (requestCode == REQUEST_READ_CONTACTS) {
//            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                populateAutoComplete();
//            }
//        }
//    }
//
//
//    /**
//     * Attempts to sign in or register the account specified by the login form.
//     * If there are form errors (invalid email, missing fields, etc.), the
//     * errors are presented and no actual login attempt is made.
//     */
//    private void attemptLogin() {
//        if (mAuthTask != null) {
//            return;
//        }
//
//        // Reset errors.
//        mobile_number_ed.setError(null);
//
//        // Store values at the time of the login attempt.
//        String email = mobile_number_ed.getText().toString();
//
//        boolean cancel = false;
//        View focusView = null;
//
//
//        // Check for a valid email address.
//        if (TextUtils.isEmpty(email)) {
//            mobile_number_ed.setError(getString(R.string.error_field_required));
//            focusView = mobile_number_ed;
//            cancel = true;
//        } else if (!isEmailValid(email)) {
//            mobile_number_ed.setError(getString(R.string.error_invalid_email));
//            focusView = mobile_number_ed;
//            cancel = true;
//        }
//
//        if (cancel) {
//            // There was an error; don't attempt login and focus the first
//            // form field with an error.
//            focusView.requestFocus();
//        } else {
//            // Show a progress spinner, and kick off a background task to
//            // perform the user login attempt.
//            showProgress(true);
//            String password = "password";
//            mAuthTask = new UserLoginTask(email, password);
//            mAuthTask.execute((Void) null);
//        }
//    }
//
//    private boolean isEmailValid(String email) {
//        //TODO: Replace this with your own logic
//        return email.contains("@");
//    }
//
//    private boolean isPasswordValid(String password) {
//        //TODO: Replace this with your own logic
//        return password.length() > 4;
//    }
//
//    /**
//     * Shows the progress UI and hides the login form.
//     */
//    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
//    private void showProgress(final boolean show) {
//        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
//        // for very easy animations. If available, use these APIs to fade-in
//        // the progress spinner.
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
//            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);
//
//           /* mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
//            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
//                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
//                @Override
//                public void onAnimationEnd(Animator animation) {
//                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
//                }
//            });
//*/
//            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
//            mProgressView.animate().setDuration(shortAnimTime).alpha(
//                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
//                @Override
//                public void onAnimationEnd(Animator animation) {
//                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
//                }
//            });
//        } else {
//            // The ViewPropertyAnimator APIs are not available, so simply show
//            // and hide the relevant UI components.
//            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
////            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
//        }
//    }
//
//    @Override
//    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
//        return new CursorLoader(this,
//                // Retrieve data rows for the device user's 'profile' contact.
//                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
//                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,
//
//                // Select only email addresses.
//                ContactsContract.Contacts.Data.MIMETYPE +
//                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
//                .CONTENT_ITEM_TYPE},
//
//                // Show primary email addresses first. Note that there won't be
//                // a primary email address if the user hasn't specified one.
//                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
//    }
//
//    @Override
//    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
//        List<String> emails = new ArrayList<>();
//        cursor.moveToFirst();
//        while (!cursor.isAfterLast()) {
//            emails.add(cursor.getString(ProfileQuery.ADDRESS));
//            cursor.moveToNext();
//        }
//
//        addEmailsToAutoComplete(emails);
//    }
//
//    @Override
//    public void onLoaderReset(Loader<Cursor> cursorLoader) {
//
//    }
//
//    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
//        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
//        ArrayAdapter<String> adapter =
//                new ArrayAdapter<>(LoginActivity.this,
//                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);
//
//        mobile_number_ed.setAdapter(adapter);
//    }
//
//
//    private interface ProfileQuery {
//        String[] PROJECTION = {
//                ContactsContract.CommonDataKinds.Email.ADDRESS,
//                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
//        };
//
//        int ADDRESS = 0;
//        int IS_PRIMARY = 1;
//    }
//
//    /**
//     * Represents an asynchronous login/registration task used to authenticate
//     * the user.
//     */
//    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {
//
//        private final String mEmail;
//        private final String mPassword;
//
//        UserLoginTask(String email, String password) {
//            mEmail = email;
//            mPassword = password;
//        }
//
//        @Override
//        protected Boolean doInBackground(Void... params) {
//            // TODO: attempt authentication against a network service.
//
//            try {
//                // Simulate network access.
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                return false;
//            }
//
//            for (String credential : DUMMY_CREDENTIALS) {
//                String[] pieces = credential.split(":");
//                if (pieces[0].equals(mEmail)) {
//                    // Account exists, return true if the password matches.
//                    return pieces[1].equals(mPassword);
//                }
//            }
//
//            // TODO: register the new account here.
//            return true;
//        }
//
//        @Override
//        protected void onPostExecute(final Boolean success) {
//            mAuthTask = null;
//            showProgress(false);
//
//            if (success) {
//                finish();
//            } else {
//            }
//        }
//
//        @Override
//        protected void onCancelled() {
//            mAuthTask = null;
//            showProgress(false);
//        }
//    }
//}

