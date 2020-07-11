package com.example.videodemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentAccount extends Fragment {
    View view;
    Button btnLogout;
    public FragmentManager fragmentManager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_acount, container, false);

        btnLogout = view.findViewById(R.id.buttonLogout);
//        btnLogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
//                fragmentTransaction1.replace(R.id., new LoginUser());
//                fragmentTransaction1.addToBackStack(null);
//                fragmentTransaction1.commit();
//            }
//        });
        return  view;
    }
}
