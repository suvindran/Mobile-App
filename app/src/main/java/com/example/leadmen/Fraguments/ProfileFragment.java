package com.example.leadmen.Fraguments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.leadmen.Activities.EditProfileActivity;
import com.example.leadmen.R;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import static android.content.Context.MODE_PRIVATE;

public class ProfileFragment extends Fragment {

    public static final String GOOGLE_ACCOUNT = "google_account";

    TextView username,email;

    SharedPreferences sp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ImageButton edit = (ImageButton) view.findViewById(R.id.edit);

        sp = getActivity().getApplicationContext().getSharedPreferences("mypref", MODE_PRIVATE);

        username = (TextView)view.findViewById(R.id.username);
        email = (TextView)view.findViewById(R.id.email_id);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), EditProfileActivity.class);
                startActivity(i);
            }
        });

        setDataOnView();

        return view;
    }

    private void setDataOnView() {
        if (sp.getString("token","") != null){
            username.setText(sp.getString("name",""));
            email.setText(sp.getString("email",""));
        }else {
            GoogleSignInAccount googleSignInAccount = getActivity().getIntent().getParcelableExtra(GOOGLE_ACCOUNT);
            username.setText(googleSignInAccount.getDisplayName());
            email.setText(googleSignInAccount.getEmail());
        }
    }
}
