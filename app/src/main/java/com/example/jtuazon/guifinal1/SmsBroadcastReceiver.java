package com.example.jtuazon.guifinal1;

/**
 * Created by jtuazon on 23/7/2017.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.telephony.SmsManager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class SmsBroadcastReceiver extends BroadcastReceiver {
    String display1;
    String display2;
    String message;
    String firstname;
    String midname;
    String lastname;

    public static final String SMS_BUNDLE = "pdus";
    static final int READ_BLOCK_SIZE = 100;
    //String value = getIntent().getStringExtra("string_name");

    public void onReceive(Context context, Intent intent) {
        Bundle intentExtras = intent.getExtras();
        if (intentExtras != null) {
            Object[] sms = (Object[]) intentExtras.get(SMS_BUNDLE);
            String smsMessageStr = "";
            for (int i = 0; i < sms.length; ++i) {
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) sms[i]);

                String smsBody = smsMessage.getMessageBody().toString();
                String address = smsMessage.getOriginatingAddress();

                smsMessageStr += "SMS From: " + address + "\n";
                smsMessageStr += smsBody + "\n";
            }
            Toast.makeText(context, smsMessageStr, Toast.LENGTH_SHORT).show();
            String CurrentString = smsMessageStr;
            String[] separated = CurrentString.split("\n");



            display1 = separated[1];
            display2 = separated[0];

            //sendmsg();

            if (display1.equals("ADVISORY") == true)
            {
                if (display2.contains("21583695") == true)
                {

                    try {
                        FileInputStream fileIn = context.openFileInput("field1.txt");
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

                        firstname = s;

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    try {
                        FileInputStream fileIn = context.openFileInput("field2.txt");
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

                        midname = t;

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    try {
                        FileInputStream fileIn = context.openFileInput("field3.txt");
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

                        lastname = u;

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    message = String.format("%s\n%s %s %s\n%s", "AUTO", firstname, midname, lastname, separated[3]);
                    sendmsg();

                    final Intent notificationIntent = new Intent(context, MainActivity.class);
                    notificationIntent.setAction(Intent.ACTION_MAIN);
                    notificationIntent.addCategory(Intent.CATEGORY_LAUNCHER);
                    notificationIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(notificationIntent);
                }
            }


            //getInfo();

            //sendmsg();
        }

    }

    public void getInfo() {

    }
    public void SendSafe(){
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("21583695", null, "safe", null, null);
    }
    public void sendmsg() {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("21583695", null, message, null, null);

    }
}