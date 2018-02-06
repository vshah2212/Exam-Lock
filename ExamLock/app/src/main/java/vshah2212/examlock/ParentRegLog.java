package vshah2212.examlock;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class ParentRegLog extends AppCompatActivity {

    private AdView mAdView,mAdView2;
    EditText em,pass;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_reg_log);


        MobileAds.initialize(this, "ca-app-pub-6682930157478661~5830362053");

        mAdView = findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mAdView2 = findViewById(R.id.adView3);
        AdRequest adRequest2 = new AdRequest.Builder().build();
        mAdView2.loadAd(adRequest2);

        em = findViewById(R.id.emailid);
        pass = findViewById(R.id.passw);

        b = findViewById(R.id.regLog);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = em.getText().toString().trim();
                final String pw = pass.getText().toString().trim();

                if(email.equals("") || pw.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Cannot be left blank!",Toast.LENGTH_SHORT).show();
                }
                else {
                    AlertDialog alertDialog = new AlertDialog.Builder(ParentRegLog.this).create();
                    alertDialog.setTitle("Alert");
                    alertDialog.setMessage("Do not forget this email ID and password!");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent i = new Intent(ParentRegLog.this, StudentMain.class);
                                    i.putExtra("username", email);
                                    i.putExtra("password", pw);
                                    startActivity(i);
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();

                }

            }
        });


    }
}
