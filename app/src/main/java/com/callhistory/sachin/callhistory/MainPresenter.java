package com.callhistory.sachin.callhistory;

import android.content.Context;

import com.callhistory.sachin.callhistory.GetterSetter.CallData;
import com.callhistory.sachin.callhistory.Model.MainModel;

import java.util.ArrayList;

/**
 * Created by sachin on 9/4/17.
 */

public class MainPresenter implements MainContract.RequiredPresenterOperations {


    private Context context;
    private MainModel mainModel;

    public MainPresenter(Context context) {
        this.context = context;
        this.mainModel = new MainModel(context);
    }


    @Override
    public ArrayList<CallData> fetchArrayList() {
        return mainModel.getArrayList();
    }
}
