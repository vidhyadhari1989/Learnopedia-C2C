package com.c2c.learnopedia.model;

/**
 * Created by Vidhyadhari on 14-11-2016.
 *
 */
/*{
        "ChapterName": null,
        "ChapterCode": "BACTERIAL ENDOCARDITIS",
        "ChapterContent": "•\tBacterial endocarditis\r\n•\tis a localized infection of the endocardium\r\n•\tcharacterized by vegetations involving the valve leaflets or walls. \r\n•\tIt can also be classified as acute (ABE) or subacute (SBE).\r\no\tABE\r\n?\tInfection of healthy valves by high-virulence organisms\r\n?\tProduces metastatic foci\r\n?\tUsually fatal if not treated within 6 weeks\r\n?\tMost common organism is S. aureus (MCQ)\r\no\tSBE\r\n?\tSeeding of previously damaged valves (rheumatic heart disease, con-\r\n?\tgenital valve defects: mitral valve prolapse) \r\n?\tcausedby low-virulence organisms\r\n?\tDoes not produce metastatic foci\r\n?\tMost common organism is Streptococcus viridans(MCQ)\r\n?\tMitral valve is most often affected(MCQ)\r\n•\tEtiology\r\no\tAcute: S. aureus, gram negative(MCQ)\r\no\tSubacute:\r\n?\tStreptococcusviridans,otheroralflora,groupAbeta-hemolyticstrep, enterococci, Staphylococcus epidermidis\r\no\tIV drug users: S. aureus, streptococci, enterococci, Candida\r\no\tProsthetic valves (10 to 20% of cases):\r\n?\tS. aureus, Streptococcus viridans,gram negative bacilli, fungi(MCQ)\r\no\tNosocomial infections: \r\n?\tIndwelling venous catheters, hemodialysis, CTsurgery\r\n•\tSigns and symptoms\r\no\tABE\r\n?\tAcute onset of fever, chills, rigors\r\n?\tNew cardiac murmur\r\n?\tMetastatic infections—meningitis, pneumonia\r\no\tSBE\r\n?\tGradual onset of fever, sweats, weakness, arthralgia, anorexia, weight loss, and cutaneous lesions\r\n?\tNew cardiac murmur\r\n?\tSplenomegaly\r\n?\tPetechiae:\r\n•\tMultiple nonblanching red macules on upper chest and mu-cous membranes\r\n?\tOsler’s nodes:(MCQ)\r\n•\tTender violaceous subcutaneous nodules on fingers andtoes \r\n?\tSplinter hemorrhages:(MCQ)\r\n•\tFine linear hemorrhages in middle of nailbed\r\n?\tJaneway lesions: (MCQ)\r\n•\tMultiple hemorrhagic nontender macules or noduleson palms \r\n?\tRoth’s spots:(MCQ)\r\n•\tRetinal hemorrhages seen on funduscopy\r\n?\tConjunctival hemorrhages\r\n•\tDiagnosis\r\no\tDuke’s criteria(A very High yield MCQ in MD Entrance )\r\n?\tPatient must have 2 major, 1 major + 3 minor, or 5 minor criteria for diagnosis):\r\n?\tMajor Criteria\r\n•\tTwo positive blood cultures taken at least 12 hours apart, or 3??positive cultures taken at least 1 hour apart\r\n•\tEchocardiography—vegetations are pathognomonic but their absence does not rule out endocarditis\r\n•\ttransesophageal echo is more sensitive.\r\n?\tMinor Criteria\r\n•\tPredisposing lesion on valve or intravenous drug use\r\n•\tFever > 38 °C\r\n•\tArterial emboli (Janeway lesions)\r\n•\tOsler’s nodes, Roth’s spots\r\n•\tPositive blood cultures not meeting major criteria\r\n•\tEchocardiogram suspicious for endocarditis but not meeting major criteria\r\n•\tTreatment\r\no\tStreptococci: Penicillin G or ceftriaxone ??4 weeks(MCQ)\r\no\tStaphylococci:Nafcillin or oxacillin??4 weeks(MCQ)\r\no\tMRSA: Vancomycin??4 weeks(MCQ)\r\n•\tClinical Pearls :\r\no\tEndocarditis prophylaxis is given to patients with ValvularHD and those with previous history of endocarditis 30 minutes prior to:(MCQ)\r\n?\tDentalprocedures \r\n?\tGIprocedures?\r\n?\tUrologicprocedures\r\no\tThere is a strong association between Streptococcus bovis endocarditis and colonic neoplasms.(MCQ)\r\no\tIV drug users: (MCQ)\r\n?\tRight-sided ABE most often affects the tricuspid valve\r\n?\tseptic pulmonary emboli are common.",
        "ChapterVideolink": "https://player.vimeo.com/video/72130489",
        "Status": null,
        "Message": null,
        "ChapterRecordView": null
        },*/
public class Chapters {

    private String ChapterName;
    private String ChapterCode;
    private String ChapterContent;
    private String ChapterVideolink;
    private String Message;
    private String ChapterRecordView;

    public String getChapterName() {
        return ChapterName;
    }

    public void setChapterName(String chapterName) {
        ChapterName = chapterName;
    }

    public String getChapterCode() {
        return ChapterCode;
    }

    public void setChapterCode(String chapterCode) {
        ChapterCode = chapterCode;
    }

    public String getChapterContent() {
        return ChapterContent;
    }

    public void setChapterContent(String chapterContent) {
        ChapterContent = chapterContent;
    }

    public String getChapterVideolink() {
        return ChapterVideolink;
    }

    public void setChapterVideolink(String chapterVideolink) {
        ChapterVideolink = chapterVideolink;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getChapterRecordView() {
        return ChapterRecordView;
    }

    public void setChapterRecordView(String chapterRecordView) {
        ChapterRecordView = chapterRecordView;
    }

}
