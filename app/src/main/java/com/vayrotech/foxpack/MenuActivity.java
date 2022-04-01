package com.vayrotech.foxpack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


public class MenuActivity extends AppCompatActivity {

    ImageView imageViewProfile;
    TextView textViewName,textViewId;
    Button signOutBtn;
    GoogleSignInClient mGoogleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);



        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        imageViewProfile=findViewById(R.id.imageViewProfile);
        textViewName=findViewById(R.id.textViewName);
        textViewId=findViewById(R.id.textViewId);
        signOutBtn=findViewById(R.id.buttonSignOut);

        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    // ...
                    case R.id.buttonSignOut:
                        signOut();
                        break;
                    // ...
                }

            }
        });




        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();

            textViewName.setText(personName);
            textViewId.setText(personId);
            Glide.with(this).load(String.valueOf(personPhoto)).into(imageViewProfile);
        }
    }





    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(MenuActivity.this, "Signed Out Succesfully!", Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
    }



    public void toScheduleWorkout(View view)
    {

        Intent intent = new Intent(MenuActivity.this, FrameActivity.class);
        intent.putExtra("personGivenName", textViewName.getText().toString());
        intent.putExtra("firstFrag", "toAddFrag");
        startActivity(intent);

    }

    public void toScheduleList(View view)
    {

        Intent intent = new Intent(MenuActivity.this, FrameActivity.class);
        intent.putExtra("personGivenName", textViewName.getText().toString());
        intent.putExtra("firstFrag", "toScheduleFrag");
        startActivity(intent);

    }






}