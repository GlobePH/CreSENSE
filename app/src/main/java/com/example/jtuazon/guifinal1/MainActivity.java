package com.example.jtuazon.guifinal1;

import java.lang.*;
import java.io.*;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.EditText;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.widget.PopupWindow;
import android.app.Activity;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends Activity {

    private static MainActivity inst;
    ArrayList<String> smsMessagesList = new ArrayList<String>();
    ListView smsListView;
    ArrayAdapter arrayAdapter;

    String message;
    String firstname;
    String midname;
    String lastname;

    public static MainActivity instance() {
        return inst;
    }

    @Override
    public void onStart() {
        super.onStart();
        inst = this;
    }

    //  @Override
//   protected void onCreate(Bundle savedInstanceState) {
//    super.onCreate(savedInstanceState);
    //    setContentView(R.layout.activity_main);

    // }
    static final int READ_BLOCK_SIZE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //   SmsBroadcastReceiver asf = new SmsBroadcastReceiver();//make sure that you pass the appropriate arguments if you have an args constructor
        //  asf.startChronometer();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //SmsManager smsManager = SmsManager.getDefault();
        //smsManager.sendTextMessage("09254502324", null, "sjdfsdj", null, null);
    //    SmsBroadcastReceiver mActivity = new SmsBroadcastReceiver();//make sure that you pass the appropriate arguments if you have an args constructor
     //   mActivity.startChronometer();


        try {
            FileInputStream fileIn = openFileInput("field1.txt");
            InputStreamReader InputRead = new InputStreamReader(fileIn);

            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            String s = "";
            int charRead;

            while ((charRead = InputRead.read(inputBuffer)) > 0) {
                // char to string conversion
                String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                s += readstring;
            }
            InputRead.close();
            EditText fname = (EditText) findViewById(R.id.fname);
            fname.setText(s, TextView.BufferType.EDITABLE);

            firstname = s;

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FileInputStream fileIn = openFileInput("field2.txt");
            InputStreamReader InputRead = new InputStreamReader(fileIn);

            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            String t = "";
            int charRead;

            while ((charRead = InputRead.read(inputBuffer)) > 0) {
                // char to string conversion
                String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                t += readstring;
            }
            InputRead.close();
            EditText mname = (EditText) findViewById(R.id.mname);
            mname.setText(t, TextView.BufferType.EDITABLE);

            midname = t;

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FileInputStream fileIn = openFileInput("field3.txt");
            InputStreamReader InputRead = new InputStreamReader(fileIn);

            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            String u = "";
            int charRead;

            while ((charRead = InputRead.read(inputBuffer)) > 0) {
                // char to string conversion
                String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                u += readstring;
            }
            InputRead.close();
            EditText lname = (EditText) findViewById(R.id.lname);
            lname.setText(u, TextView.BufferType.EDITABLE);

            lastname = u;

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void sndbtnOnClick(View v1) {
        //Button sndbtn = (Button) v1;
        //((Button) v1).setText("clicked");
        int option_value = 0;
        final RadioGroup radgrp = (RadioGroup) findViewById(R.id.radgrp);
        int radioID = radgrp.getCheckedRadioButtonId();
        RadioButton singleButton = (RadioButton) findViewById(radioID);
        /* RadioButton singleButton = (RadioButton) findViewById(radioID);

        Toast.makeText(getBaseContext(), singleButton.getText(),
                Toast.LENGTH_SHORT).show();
        */
        if (singleButton.getText().equals("safe") == true) {
            option_value = 1;
            message = String.format("MAN\n%s %s %s\nSAFE", firstname, midname, lastname);
        }
        else if(singleButton.getText().equals("NOT SAFE") == true){
            option_value = 0;
            message = String.format("MAN\n%s %s %s\nNOTSAFE", firstname, midname, lastname);
        }
        else {
            option_value = -1;
        }

        Toast.makeText(getBaseContext(), message,
                Toast.LENGTH_SHORT).show();

        try
        {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage("21583695", null, message, null, null);
            Toast.makeText(getApplicationContext(),"wow",Toast.LENGTH_LONG).show();
        }
        catch(Exception e)
        {
            Toast.makeText(getApplicationContext(), e.getMessage(),Toast.LENGTH_LONG).show();
        }

    }

    public void updtbtnOnclick(View v2) {
        Button updtbtn = (Button) v2;

        //Get First Name:
        EditText fname = (EditText) findViewById(R.id.fname);
        String fname_val = fname.getText().toString();

        //Get Middle Name:
        EditText mname = (EditText) findViewById(R.id.mname);
        String mname_val = mname.getText().toString();

        //Get Last Name:
        EditText lname = (EditText) findViewById(R.id.lname);
        String lname_val = lname.getText().toString();

        String fullname = fname_val + " " + mname_val + " " + lname_val; //To be sent to server

       // Intent intent = new Intent(MainActivity .this, SmsBroadcastReceiver.class);

        //intent.putExtra("string_name", fullname);
        //startActivity(intent);

        String str1 = fname_val;
        String str2 = mname_val;
        String str3 = lname_val;
        try {
            FileOutputStream fileout = openFileOutput("field1.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
            outputWriter.write(str1);
            outputWriter.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FileOutputStream fileout = openFileOutput("field2.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
            outputWriter.write(str2);
            outputWriter.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FileOutputStream fileout = openFileOutput("field3.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
            outputWriter.write(str3);
            outputWriter.close();

            //display file saved message
            Toast.makeText(getBaseContext(), "Name saved successfully!",
                    Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }

        firstname = fname_val;
        midname = mname_val;
        lastname = lname_val;

        //((Button)v2).setText(fullname);
   }
    public int getSelectedRadioButton (View view) {
        final RadioGroup radgrp = (RadioGroup) findViewById(R.id.radgrp);
        int radioID = radgrp.getCheckedRadioButtonId();
        RadioButton singleButton = (RadioButton) findViewById(radioID);
        /* RadioButton singleButton = (RadioButton) findViewById(radioID);



        Toast.makeText(getBaseContext(), singleButton.getText(),
                Toast.LENGTH_SHORT).show();
        */
        if (singleButton.getText().equals("safe") == true) {
            return 1;
        }
        else if(singleButton.getText().equals("NOT SAFE") == true){
            return 0;
        }
        else{
            return -1;
        }
    }

    private void sendmsg3() {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("21583695", null, message, null, null);
    }


}

