package com.maggot99999.packagedetector;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jaredrummler.android.processes.AndroidProcesses;
import com.jaredrummler.android.processes.models.AndroidAppProcess;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button detectBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        detectBtn = findViewById(R.id.btn_detect);
        detectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get a list of running apps
                List<AndroidAppProcess> runningAppProcessInfo = AndroidProcesses.getRunningAppProcesses();

                for (int i = 0; i < runningAppProcessInfo.size(); i++) {
                    Log.i("MainActivity", runningAppProcessInfo.get(i).getPackageName());
                    // Check for AnkuLua
                    if(runningAppProcessInfo.get(i).getPackageName().equals("com.appautomatic.ankulua.pro2") ||
                            runningAppProcessInfo.get(i).getPackageName().equals("com.appautomatic.ankulua.pro") ||
                            runningAppProcessInfo.get(i).getPackageName().equals("com.appautomatic.ankulua.trial")){

                        // AnkuLua package process has been detected! Perma ban account for abusing Terms & Conditions
                        alert();
                        return;
                    }
                }
                Toast.makeText(MainActivity.this,"Not Found",Toast.LENGTH_SHORT).show();
            }
        });
    }

    void alert(){
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("AnkuLua has been detected!");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}
