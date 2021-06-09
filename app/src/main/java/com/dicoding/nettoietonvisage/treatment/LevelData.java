package com.dicoding.nettoietonvisage.treatment;

import java.util.ArrayList;

public class LevelData {
    private static String[] Levl = {
            "Level Low/Mild (1-5)",
            "Level Moderate (6-20)",
            "Level Severe (21-50)",
            "Very Severe (>50)"
    };
    private static String[] Descr = {
            "Description : " +
                    "Hardly visible from 2.5 meters away; a few scattered comedones and a few small papules; and very few pustules, comedones, and papules",
            "Description :  " +
                    "Easily recognizable; less than half of the affected area is involved; many comedones, papules, and pustules",
            "Description : " +
                    "Entire area is involved; covered with comedones, numerous pustules and papules, a few nodules and cysts.",
            "Description : " +
                    "Highly inflammatory acne covering the affected area, nodules and cysts present"
    };
    private static String[] Recom = {
            "Recommendation : " +
                    "Over-the-counter medicine helps! Benzoyl peroxide is a bactericidal agent that comes in the form of creams and gels in a variety of manners and is proven to be the most effective for treatment of mild to moderate mixed acne when used in combination with topical retinoids!. A diet plan may also help in complementing this treatment regime as there have been numerous studies that show that there is a correlation between chocolate and milk consumption with acne/lesions breakout. Therapy through skin care clinics should also be considered. For Asian patients, there have been studies that show Chemical peels for acne and acne scars have been shown to be safe and effective",
            "Recommendation : " +
                    "Over-the-counter medicine helps! Benzoyl peroxide is a bactericidal agent that comes in the form of creams and gels in a variety of manners and is proven to be the most effective for treatment of mild to moderate mixed acne when used in combination with topical retinoids!. You may also consider getting oral antibiotics. Based on expert consensus on relative effectiveness, they recommend using doxycycline and minocycline (minocin). Taking a dosage of 50 to 100 mg once or twice per day. It should be noted however that once the severity of the acne has lowered, that you should stop taking oral antibiotics and instead switch over to over-the-counter medicine instead. Therapy through skin care clinics should also be considered. For Asian patients, there have been studies that show Chemical peels for acne and acne scars have been shown to be safe and effective. Photodynamic Therapy have been shown to be effective in moderate-to-severe acne.",
            "Recommendation : " +
                    "Consult with a dermatologist immediately. Oral antibiotics helps a lot but needs a doctor's prescription. Based on expert consensus on relative effectiveness, they recommend using doxycycline and minocycline (minocin). Taking a dosage of 50 to 100 mg once or twice per day. A minimum duration of six weeks is commonly required to see clinical improvement but it is not recommended to continue using these oral options for more than 3-4 months. However, these should be taken with other topical agents such as topical retinoids which has the full recommendation of experts!. It should be noted however that once the severity of the acne has lowered to mild levels, you should stop taking oral antibiotics and instead switch over to over-the-counter medicine such as topical retinoids that come in the form of cream and gels.\n. Therapy through skin care clinics should also be considered. Photodynamic Therapy have been shown to be effective in moderate-to-severe acne.",
            "Recommendation : " +
                    "Consult with a dermatologist immediately, there are many studies that show that oral isotretinoin is effective for severe recalcitrant acne, however, it requires a doctor’s discretion or done in accordance with the region’s medical regulations. There are many variables which come into play with these levels of acne, and it should be noted that therapic measures from expert dermatologists will help the most."
    };
    public static ArrayList<com.dicoding.nettoietonvisage.treatment.Level> getListData(){
        ArrayList<com.dicoding.nettoietonvisage.treatment.Level> list = new ArrayList<>();
        for (int position = 0; position <Levl.length; position++){
            com.dicoding.nettoietonvisage.treatment.Level level = new com.dicoding.nettoietonvisage.treatment.Level();
            level.setLevell(Levl[position]);
            level.setDescription(Descr[position]);
            level.setRecommendation(Recom[position]);
            list.add(level);
        }
        return list;
    }
}
