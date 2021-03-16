package com.utopia.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.utopia.demo.view.EmptyStateView;
import com.utopia.demo.view.Http50xStateView;
import com.utopia.demo.view.LoadingStateView;
import com.utopia.state.StateBox;
import com.utopia.state.BoxManager;

public class MainActivity extends AppCompatActivity {
    private BoxManager stateManager;
    private final Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stateManager = StateBox.getDefault().inject(this, R.id.ll_root_view, v -> {
            loadData();
        });

        loadData();
    }


    private void loadData(){
        stateManager.show(LoadingStateView.class);
        //模拟耗时操作
        mHandler.postDelayed(()-> stateManager.hidden(),1500);
    }

}