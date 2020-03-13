package com.example.myapps;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.myapps.session.Config;

public class My_Home extends AppCompatActivity {
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__home);

    }


    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            Intent setIntent = new Intent(Intent.ACTION_MAIN);
            setIntent.addCategory(Intent.CATEGORY_HOME);
            setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(setIntent);
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please tap back again to Exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        },2000);

    }
//    private void logout() {
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(My_Home.this);
//        alertDialogBuilder.setMessage("Are you sure you want to Logout?");
//        alertDialogBuilder.setPositiveButton("OK",
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface arg0, int arg1) {
//
//                        //Getting out sharedpreferences
//                        SharedPreferences preferences = My_Home.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
//                        //Getting editor
//                        SharedPreferences.Editor editor = preferences.edit();
//
//                        //Puting the value false for loggedin
//                        editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, false);
//
//                        if (sFlagRemember.equals("1")) {
//                            //Putting blank value sessions
////                            editor.putString(Config.PWD_SHARED_PREF, "");
////                            editor.putString(Config.FULLNAME_SHARED_PREF, "");
////                            editor.putString(Config.BRANCH_SHARED_PREF, "");
////                            editor.putString(Config.BRANCHNAME_SHARED_PREF, "");
////                            editor.putString(Config.POSITION_SHARED_PREF, "");
////                            editor.putString(Config.LOKASI_SHARED_PREF, "");
////                            editor.putString(Config.DIVISION_SHARED_PREF, "");
////                            editor.putString(Config.FLAGSTATUS_SHARED_PREF, "");
////                            editor.putString(Config.FINGERID_SHARED_PREF, "");
////                            editor.putString(Config.FLAGMULTI_SHARED_PREF, "");
////                            editor.putString(Config.FLAGCARD_SHARED_PREF, "");
////                            editor.putString(Config.FLAGREMEMBER_SHARED_PREF, "");
////                            editor.putString(Config.SESSIONSDATE_SHARED_PREF, "");
////                            editor.putString(Config.IMEI_SHARED_PREF, "");
//
//                        } else {
//                            //Putting blank value sessions
////                            editor.putString(Config.NIP_SHARED_PREF, "");
////                            editor.putString(Config.PWD_SHARED_PREF, "");
////                            editor.putString(Config.FULLNAME_SHARED_PREF, "");
////                            editor.putString(Config.BRANCH_SHARED_PREF, "");
////                            editor.putString(Config.BRANCHNAME_SHARED_PREF, "");
////                            editor.putString(Config.POSITION_SHARED_PREF, "");
////                            editor.putString(Config.LOKASI_SHARED_PREF, "");
////                            editor.putString(Config.DIVISION_SHARED_PREF, "");
////                            editor.putString(Config.FLAGSTATUS_SHARED_PREF, "");
////                            editor.putString(Config.FINGERID_SHARED_PREF, "");
////                            editor.putString(Config.FLAGMULTI_SHARED_PREF, "");
////                            editor.putString(Config.FLAGCARD_SHARED_PREF, "");
////                            editor.putString(Config.FLAGREMEMBER_SHARED_PREF, "");
////                            editor.putString(Config.SESSIONSDATE_SHARED_PREF, "");
////                            editor.putString(Config.IMEI_SHARED_PREF, "");
//                        }
//
//                        //Saving the sharedpreferences
//                        editor.commit();
//                        //Starting login activity
//                        Intent intent = new Intent(My_Home.this, My_Login.class);
//                        startActivity(intent);
//                    }
//                });
//
//        alertDialogBuilder.setNegativeButton("CANCEL",
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface arg0, int arg1) {
//
//                    }
//                });
//
//        //Showing the alert dialog
//        AlertDialog alertDialog = alertDialogBuilder.create();
//        alertDialog.show();
//    }
}
