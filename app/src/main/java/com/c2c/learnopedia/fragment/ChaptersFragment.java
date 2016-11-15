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
import com.c2c.learnopedia.model.Chapters;
import com.c2c.learnopedia.other.AppConstance;
import com.c2c.learnopedia.other.GridViewAdapter;
import com.c2c.learnopedia.other.UrlUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import info.androidhive.navigationdrawer.R;


public class ChaptersFragment extends Fragment  {

    private GridView subject_gridView;
    private GridViewAdapter gridAdapter;
    Button entroll;
    ArrayList<Chapters> mChaptersData =new ArrayList<>();
    String map1 = "map1";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View subjectFragment = inflater.inflate(R.layout.fragment_chapters, container, false);
        initView(subjectFragment);
        return subjectFragment;
    }

    private void initView(View view) {

        getAllChapters();

       /* gridAdapter.notifyDataSetChanged();
        subject_gridView.setAdapter(gridAdapter);
*/
    }

    private void getAllChapters() {
        final ProgressDialog loading = ProgressDialog.show(getActivity(), "", "Please wait..", false, false);
        HashMap<String, String> params = new HashMap<String, String>();
        JSONObject jsonObject = new JSONObject(params);
        Log.v(AppConstance.TAG, "Request String:" + jsonObject.toString());
        Log.v(AppConstance.TAG, "Requesting String in SignUp :" + UrlUtils.Get_All_Subjects);
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, UrlUtils.Get_Chapters+map1, jsonObject,
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

                                /*{
                                    "ChapterName": null,
                                        "ChapterCode": "BACTERIAL ENDOCARDITIS",
                                        "ChapterContent": "•\tBacterial endocarditis\r\n•\tis a localized infection of the endocardium\r\n•\tcharacterized by vegetations involving the valve leaflets or walls. \r\n•\tIt can also be classified as acute (ABE) or subacute (SBE).\r\no\tABE\r\n?\tInfection of healthy valves by high-virulence organisms\r\n?\tProduces metastatic foci\r\n?\tUsually fatal if not treated within 6 weeks\r\n?\tMost common organism is S. aureus (MCQ)\r\no\tSBE\r\n?\tSeeding of previously damaged valves (rheumatic heart disease, con-\r\n?\tgenital valve defects: mitral valve prolapse) \r\n?\tcausedby low-virulence organisms\r\n?\tDoes not produce metastatic foci\r\n?\tMost common organism is Streptococcus viridans(MCQ)\r\n?\tMitral valve is most often affected(MCQ)\r\n•\tEtiology\r\no\tAcute: S. aureus, gram negative(MCQ)\r\no\tSubacute:\r\n?\tStreptococcusviridans,otheroralflora,groupAbeta-hemolyticstrep, enterococci, Staphylococcus epidermidis\r\no\tIV drug users: S. aureus, streptococci, enterococci, Candida\r\no\tProsthetic valves (10 to 20% of cases):\r\n?\tS. aureus, Streptococcus viridans,gram negative bacilli, fungi(MCQ)\r\no\tNosocomial infections: \r\n?\tIndwelling venous catheters, hemodialysis, CTsurgery\r\n•\tSigns and symptoms\r\no\tABE\r\n?\tAcute onset of fever, chills, rigors\r\n?\tNew cardiac murmur\r\n?\tMetastatic infections—meningitis, pneumonia\r\no\tSBE\r\n?\tGradual onset of fever, sweats, weakness, arthralgia, anorexia, weight loss, and cutaneous lesions\r\n?\tNew cardiac murmur\r\n?\tSplenomegaly\r\n?\tPetechiae:\r\n•\tMultiple nonblanching red macules on upper chest and mu-cous membranes\r\n?\tOsler’s nodes:(MCQ)\r\n•\tTender violaceous subcutaneous nodules on fingers andtoes \r\n?\tSplinter hemorrhages:(MCQ)\r\n•\tFine linear hemorrhages in middle of nailbed\r\n?\tJaneway lesions: (MCQ)\r\n•\tMultiple hemorrhagic nontender macules or noduleson palms \r\n?\tRoth’s spots:(MCQ)\r\n•\tRetinal hemorrhages seen on funduscopy\r\n?\tConjunctival hemorrhages\r\n•\tDiagnosis\r\no\tDuke’s criteria(A very High yield MCQ in MD Entrance )\r\n?\tPatient must have 2 major, 1 major + 3 minor, or 5 minor criteria for diagnosis):\r\n?\tMajor Criteria\r\n•\tTwo positive blood cultures taken at least 12 hours apart, or 3??positive cultures taken at least 1 hour apart\r\n•\tEchocardiography—vegetations are pathognomonic but their absence does not rule out endocarditis\r\n•\ttransesophageal echo is more sensitive.\r\n?\tMinor Criteria\r\n•\tPredisposing lesion on valve or intravenous drug use\r\n•\tFever > 38 °C\r\n•\tArterial emboli (Janeway lesions)\r\n•\tOsler’s nodes, Roth’s spots\r\n•\tPositive blood cultures not meeting major criteria\r\n•\tEchocardiogram suspicious for endocarditis but not meeting major criteria\r\n•\tTreatment\r\no\tStreptococci: Penicillin G or ceftriaxone ??4 weeks(MCQ)\r\no\tStaphylococci:Nafcillin or oxacillin??4 weeks(MCQ)\r\no\tMRSA: Vancomycin??4 weeks(MCQ)\r\n•\tClinical Pearls :\r\no\tEndocarditis prophylaxis is given to patients with ValvularHD and those with previous history of endocarditis 30 minutes prior to:(MCQ)\r\n?\tDentalprocedures \r\n?\tGIprocedures?\r\n?\tUrologicprocedures\r\no\tThere is a strong association between Streptococcus bovis endocarditis and colonic neoplasms.(MCQ)\r\no\tIV drug users: (MCQ)\r\n?\tRight-sided ABE most often affects the tricuspid valve\r\n?\tseptic pulmonary emboli are common.",
                                        "ChapterVideolink": "https://player.vimeo.com/video/72130489",
                                        "Status": null,
                                        "Message": null,
                                        "ChapterRecordView": null
                                },*/
                                {
                                    JSONObject jsonObjectsub=jsonArray.getJSONObject(i);
                                    Chapters chapters=new Chapters();
                                    chapters.setChapterName(jsonObjectsub.getString("ChapterName"));
                                    chapters.setChapterContent(jsonObjectsub.getString("ChapterContent"));
                                    chapters.setChapterVideolink(jsonObjectsub.getString("ChapterVideolink"));
                                    chapters.setChapterRecordView(jsonObjectsub.getString("ChapterRecordView"));
                                    mChaptersData.add(chapters);

                                    Log.v("minnusubject","allsubjects"+mChaptersData);
                                    Log.v("minnusubject","allsubjects"+jsonObjectsub.getString("ChapterName"));
                                    Log.v("minnusubject","allsubjects"+jsonObjectsub.getString("ChapterContent"));
                                }
                              /*  if (response.getString("responseId").equalsIgnoreCase("0")) {
                                    Toast.makeText(getActivity(), "" + response.getString("responseText"), Toast.LENGTH_LONG).show();

                                }*/


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
