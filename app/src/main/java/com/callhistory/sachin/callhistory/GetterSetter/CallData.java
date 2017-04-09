package com.callhistory.sachin.callhistory.GetterSetter;

import com.callhistory.sachin.callhistory.Model.MainModel;

import java.util.ArrayList;

/**
 * Created by sachin on 9/4/17.
 */

public class CallData {

    private String phNumber = "";
    private String callDayTime = "";
    private String callDuration = "";
    private String dir = "";

    public String getCallDayTime() {
        return callDayTime;
    }

    public void setCallDayTime(String callDayTime) {
        this.callDayTime = callDayTime;
    }

    public String getCallDuration() {
        return callDuration;
    }

    public void setCallDuration(String callDuration) {
        this.callDuration = callDuration;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getPhNumber() {
        return phNumber;
    }

    public void setPhNumber(String phNumber) {
        this.phNumber = phNumber;
    }
}
