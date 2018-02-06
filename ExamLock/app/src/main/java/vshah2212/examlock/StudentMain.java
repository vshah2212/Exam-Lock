package vshah2212.examlock;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class StudentMain extends AppCompatActivity {

    private AdView mAdView,mAdView2;
    int flag=0;
    private Toolbar mTopToolbar;
  //  Button blocker,unblocker;
    String masteruser,masterpass;
    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main);


        MobileAds.initialize(this, "ca-app-pub-6682930157478661~5830362053");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mAdView2 = findViewById(R.id.adView4);
        AdRequest adRequest2 = new AdRequest.Builder().build();
        mAdView2.loadAd(adRequest2);

        Intent i = getIntent();
        masteruser = i.getStringExtra("username");
        masterpass = i.getStringExtra("password");


      //  blocker = findViewById(R.id.buttonBlock);
      //  unblocker = findViewById(R.id.buttonUnBlock);

        mTopToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mTopToolbar);


        final int delay = 1999; //milliseconds
        final Runnable r = new Runnable(){
            public void run(){
                Toast.makeText(getApplication(),"Actions blocked!",Toast.LENGTH_SHORT).show();
                    handler.postDelayed(this, delay);

            }
        };

      //  blocker.setOnClickListener(new View.OnClickListener() {
      //      @Override
      //      public void onClick(View view) {
                View decorView = getWindow().getDecorView();
                int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
                decorView.setSystemUiVisibility(uiOptions);
                flag = 1;
                startLockTask();
                handler.postDelayed(r,delay);

      //      }
      //  });
      //  unblocker.setOnClickListener(new View.OnClickListener() {
      //      @Override
      //      public void onClick(View view) {
      //          flag=0;
      //          handler.removeCallbacksAndMessages(null);
      //          stopLockTask();
      //      }
      //  });

    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(uiOptions);
            flag = 1;
            startLockTask();
            return true;
        }
        return super.onKeyLongPress(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        if(flag==0) {
            super.onBackPressed();
        }
        else if(flag==1){ }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.student_logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_favorite) {
            Toast.makeText(StudentMain.this, "Verify Parent data to LOGOUT", Toast.LENGTH_LONG).show();

            // get prompts.xml view
            LayoutInflater li = LayoutInflater.from(StudentMain.this);
            View promptsView = li.inflate(R.layout.alert_login, null);

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    StudentMain.this);

            // set prompts.xml to alertdialog builder
            alertDialogBuilder.setView(promptsView);

            final EditText email = (EditText) promptsView
                    .findViewById(R.id.emailout);
            final EditText passw = (EditText) promptsView
                    .findViewById(R.id.passwout);

            // set dialog message
            alertDialogBuilder
                    .setCancelable(true)
                    .setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    // get user input and set it to result
                                    // edit text
                                    String emailET = email.getText().toString().trim();
                                    String passwET = passw.getText().toString().trim();
                                    if(emailET.equals(masteruser) && passwET.equals(masterpass))
                                    {
                                        flag=0;
                                        handler.removeCallbacksAndMessages(null);
                                        stopLockTask();
                                        Intent x = new Intent(StudentMain.this,ParentRegLog.class);
                                        startActivity(x);
                                    }
                                    else {
                                        Toast.makeText(StudentMain.this, "Wrong email ID or password!" , Toast.LENGTH_SHORT).show();
                                    }
                                }
                            })
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    dialog.cancel();
                                }
                            });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();



            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
