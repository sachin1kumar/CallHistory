package com.callhistory.sachin.callhistory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.callhistory.sachin.callhistory.GetterSetter.CallData;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CallsAdapter callsAdapter;
    MainPresenter mainPresenter;
    ArrayList<CallData> displayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mainPresenter = new MainPresenter(this);
        displayList = new ArrayList<>();
        displayList.addAll(mainPresenter.fetchArrayList());


        Log.e("MA:","onCreate:displayList:"+displayList);

        callsAdapter = new CallsAdapter();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(callsAdapter);

    }



    public class CallsAdapter extends RecyclerView.Adapter<CallsAdapter.MyViewHolder> {




        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView phone, callTime, callType;


            public MyViewHolder(View view) {
                super(view);
                phone = (TextView) view.findViewById(R.id.phone);
                callTime = (TextView) view.findViewById(R.id.calltime);
                callType = (TextView) view.findViewById(R.id.type);

            }
        }


        public CallsAdapter() {

          //  displayList = new ArrayList<>();
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_content, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            //MainPresenter movie = moviesList.get(position);
            Object objdata = displayList.get(position);;
            holder.phone.setText(((CallData)objdata).getPhNumber());
            holder.callTime.setText(((CallData)objdata).getCallDayTime());
            holder.callType.setText(((CallData)objdata).getDir());
        }

        @Override
        public int getItemCount() {
            return displayList.size();
        }
    }
}
