package com.example.videodemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentAccount extends Fragment {
    View view;
    Button buttonLogin;
    TextView textViewName, textViewUserName, textViewEmail, textViewPhone, textViewTwitter, textViewFB;
    ImageView imageViewAccount;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_acount, container, false);

        init();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        return  view;
    }

    private void init() {
        imageViewAccount = view.findViewById(R.id.imgViewAccount);
        buttonLogin = view.findViewById(R.id.btnLogin);
        textViewName = view.findViewById(R.id.textViewName);
        textViewUserName = view.findViewById(R.id.textUserName);
        textViewEmail = view.findViewById(R.id.textViewEmail);
        textViewPhone = view.findViewById(R.id.textViewPhone);
        textViewTwitter = view.findViewById(R.id.textViewTwitter);
        textViewFB = view.findViewById(R.id.textViewFb);
    }
}
