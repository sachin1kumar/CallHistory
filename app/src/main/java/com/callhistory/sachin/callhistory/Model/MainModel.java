package com.callhistory.sachin.callhistory.Model;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.callhistory.sachin.callhistory.GetterSetter.CallData;
import com.callhistory.sachin.callhistory.MainActivity;
import com.callhistory.sachin.callhistory.MainContract;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by sachin on 9/4/17.
 */

public class MainModel extends AppCompatActivity implements MainContract.RequiredModelOperations {

    private Context context;
    private String phNumber = "", callDayTime = "", callDuration = "", dir = "";
    private ArrayList<CallData> arrayList;

    public MainModel(Context context) {
        this.context = context;
        arrayList = new ArrayList<>();
        setData();

    }

    public void setData() {


        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions((MainActivity) context, new String[]{Manifest.permission.READ_CALL_LOG},
                    0);
        } else {
            getCallDetails();
        }
    }


    private void getCallDetails() {
        StringBuffer sb = new StringBuffer();
        CallData callData = new CallData();
        Uri contacts = CallLog.Calls.CONTENT_URI;

        if (ActivityCompat.checkSelfPermission(context,
                Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        Cursor managedCursor = context.getContentResolver().query(contacts, null, null, null,
                android.provider.CallLog.Calls.DATE + " DESC limit 10;");
        int number = managedCursor.getColumnIndex(CallLog.Calls.NUMBER);
        int type = managedCursor.getColumnIndex(CallLog.Calls.TYPE);
        int date = managedCursor.getColumnIndex(CallLog.Calls.DATE);
        int duration = managedCursor.getColumnIndex(CallLog.Calls.DURATION);
        sb.append("Call Details :");
        while (managedCursor.moveToNext()) {

            HashMap<String, String> rowDataCall = new HashMap<String, String>();

            phNumber = managedCursor.getString(number);
            String callType = managedCursor.getString(type);
            String callDate = managedCursor.getString(date);
            callDayTime = new Date(Long.valueOf(callDate)).toString();
            // long timestamp = convertDateToTimestamp(callDayTime);
            callDuration = managedCursor.getString(duration);
            dir = null;
            int dircode = Integer.parseInt(callType);
            switch (dircode) {
                case CallLog.Calls.OUTGOING_TYPE:
                    dir = "OUTGOING";
                    break;

                case CallLog.Calls.INCOMING_TYPE:
                    dir = "INCOMING";
                    break;

                case CallLog.Calls.MISSED_TYPE:
                    dir = "MISSED";
                    break;
            }
            sb.append("\nPhone Number:--- " + phNumber + " \nCall Type:--- " + dir + " \nCall Date:--- " + callDayTime + " \nCall duration in sec :--- " + callDuration);
            sb.append("\n----------------------------------");

            callData.setPhNumber(phNumber);
            callData.setCallDayTime(callDayTime);
            callData.setCallDuration(callDuration);
            callData.setDir(dir);
            arrayList.add(callData);
        }

        setArrayList(arrayList);
        managedCursor.close();
        System.out.println(sb);
        Log.e("MA:", "getCallDetails:" + sb);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getCallDetails();
        }
    }



    @Override
    public ArrayList<CallData> getArrayList() {
        return arrayList;
    }

    @Override
    public void setArrayList(ArrayList<CallData> arrayList) {
        this.arrayList = arrayList;
    }
}
