package com.c2c.learnopedia.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.c2c.learnopedia.model.Subject;
import com.c2c.learnopedia.other.AppConstance;
import com.c2c.learnopedia.other.GridViewAdapter;
import com.c2c.learnopedia.other.UrlUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import info.androidhive.navigationdrawer.R;


public class SubjectsFragment extends Fragment  {

    private GridView subject_gridView;
    private GridViewAdapter gridAdapter;
    Button entroll;
    ArrayList<Subject> mSubjectData =new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View subjectFragment = inflater.inflate(R.layout.fragment_home, container, false);
        initView(subjectFragment);
        return subjectFragment;
    }

    private void initView(View view) {
        subject_gridView = (GridView) view.findViewById(R.id.subject_gridView);

        gridAdapter = new GridViewAdapter(getActivity(),mSubjectData);
        gridAdapter.notifyDataSetChanged();
        subject_gridView.setAdapter(gridAdapter);
//        subject_gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
//              //  Subject item = (Subject) parent.getItemAtPosition(position);
//                Intent intent = new Intent();
//                intent.setClass(getActivity(), ListOfCoursesActivity.class);
//                getActivity().startActivity(intent);
////                Intent i=new Intent(getActivity(),ListOfCoursesActivity.class);
////                startActivity(i);
//
//                Toast.makeText(getActivity(),"selected", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//

        getAllSubjects();

        gridAdapter.notifyDataSetChanged();
        subject_gridView.setAdapter(gridAdapter);

    }

    private void getAllSubjects() {
        final ProgressDialog loading = ProgressDialog.show(getActivity(), "", "Please wait..", false, false);
        HashMap<String, String> params = new HashMap<String, String>();
        JSONObject jsonObject = new JSONObject(params);
        Log.v(AppConstance.TAG, "Request String:" + jsonObject.toString());
        Log.v(AppConstance.TAG, "Requesting String in SignUp :" + UrlUtils.Get_All_Subjects);
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, UrlUtils.Get_All_Subjects, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        loading.dismiss();
                        Log.v("TAG", "register user " + response);
                        try {
                            Log.v(AppConstance.TAG, "Response:" + response.toString());
                            if (response != null) {

                                JSONArray jsonArray=response.getJSONArray("ChapterRecordView");
                                for (int i = 0 ;i<jsonArray.length();i++)
                                {
                                    JSONObject jsonObjectsub=jsonArray.getJSONObject(i);
                                    Subject subject=new Subject();
                                    subject.setSubjectID(jsonObjectsub.getString("SubjectID"));
                                    subject.setSubjectCode(jsonObjectsub.getString("SubjectCode"));
                                    subject.setSubjectName(jsonObjectsub.getString("SubjectName"));
                                    subject.setImage(jsonObjectsub.getString("Image"));
                                    mSubjectData.add(subject);

                                    Log.v("minnusubject","allsubjects"+mSubjectData);
                                    Log.v("minnusubject","allsubjects"+jsonObjectsub.getString("SubjectID"));
                                    Log.v("minnusubject","allsubjects"+jsonObjectsub.getString("Image"));
                                }
                              /*  if (response.getString("responseId").equalsIgnoreCase("0")) {
                                    Toast.makeText(getActivity(), "" + response.getString("responseText"), Toast.LENGTH_LONG).show();

                                }*/

                                gridAdapter.notifyDataSetChanged();
                                subject_gridView.setAdapter(gridAdapter);

                            } else {
                                Toast.makeText(getActivity(), "doesn't get the subjects ", Toast.LENGTH_LONG).show();

                                if (loading != null)
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
        RetryPolicy policy = new DefaultRetryPolicy(AppConstance.SPLASH_DISPLAY_TIME, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        req.setRetryPolicy(policy);
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(req);
    }

}
