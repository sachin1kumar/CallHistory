package com.callhistory.sachin.callhistory;

import com.callhistory.sachin.callhistory.GetterSetter.CallData;

import java.util.ArrayList;

/**
 * Created by sachin on 9/4/17.
 */

public interface MainContract {

    /**
     * View mandatory methods.
     */
    interface RequiredViewOperations {
        void showData();
    }

    /**
     * Presenter mandatory methods.
     */
    interface RequiredPresenterOperations {
        ArrayList<CallData> fetchArrayList();

    }

    /**
     * Model mandatory methods.
     */
    interface RequiredModelOperations {
        ArrayList<CallData> getArrayList();
        void setArrayList(ArrayList<CallData> callData);
    }

}
