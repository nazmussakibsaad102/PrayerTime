package com.ArtpaperLab.ui.gallery;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ArtpaperLab.R;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;


public class GalleryFragment extends Fragment{
    String KEY_FAZR="fazr";
    String Key_ZOHR = "zohr";
    String KEY_ASR = "asr";
    String KEY_MAGRIB = "magrib";
    String KEY_ESHA = "esha";
    String KEY_FAZRTOMORROW = "fazrTomorrow";
    public TextView textViewFazr;
    public TextView textViewZohr;
    private TextView textViewAsr;
    private TextView textViewMagrib;
    private TextView textViewEsha;
    private TextView textViewFazrTomorrow;
    private String textFazr;
    private String textZohr;
    private String textAsr;
    private String textMagrib;
    private String textEsha;
    private String textFazrTomorrow;
    private int mInterval = 5000; // 5 seconds by default, can be changed later
    private Handler mHandler;

    public class A {

        String documentID = "2";










    }



    private GalleryViewModel ajkerOnucchedViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ajkerOnucchedViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
       View root = inflater.inflate(R.layout.fragment_gallery, container, false);


        textViewFazr = root.findViewById(R.id.fazr);
       textViewZohr = root.findViewById(R.id.zohr);
       textViewAsr = root.findViewById(R.id.asr);
        textViewMagrib = root.findViewById(R.id.magrib);
        textViewEsha = root.findViewById(R.id.esha);

        A myobj = new A();
        String DOC_ID = String.valueOf(myobj.documentID);

        FirebaseFirestore db= FirebaseFirestore.getInstance();
        DocumentReference prayerTimeLoad = db.collection("PrayerTime").document(DOC_ID);

        prayerTimeLoad.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (e!=null){
                    Toast.makeText(getActivity(), "error while loading", Toast.LENGTH_LONG).show();
                    return;
                }
                if (documentSnapshot.exists()){
                    textFazr  = documentSnapshot.getString(KEY_FAZR);
                   textZohr = documentSnapshot.getString(Key_ZOHR);
                    textAsr = documentSnapshot.getString(KEY_ASR);
                    textMagrib = documentSnapshot.getString(KEY_MAGRIB);
                    textEsha = documentSnapshot.getString(KEY_ESHA);
                    textViewFazr.setText(textFazr);
                    textViewZohr.setText(textZohr);
                    textViewAsr.setText(textAsr);
                    textViewMagrib.setText(textMagrib);
                    textViewEsha.setText(textEsha);



                }

            }
        });





        return root;
    }


}
