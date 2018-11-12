package com.example.thmotaun.find_taxi;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * Created by thmotaun on 2018/01/09.
 */

public class database_conn {

    private DocumentReference mDocRefCityCol = FirebaseFirestore.getInstance().document("City/Johannesburg");
    private DocumentReference mDocRefRankDoc = FirebaseFirestore.getInstance().document("City/Johannesburg/Taxi_Ranks/Noord");
    private DocumentReference mDocRefDestDoc = FirebaseFirestore.getInstance().document("City/Johannesburg/Taxi_Ranks/Noord/Taxi_to_Where/Standerton");

}
