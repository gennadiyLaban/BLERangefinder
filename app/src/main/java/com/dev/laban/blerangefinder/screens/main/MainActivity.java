package com.dev.laban.blerangefinder.screens.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dev.laban.blerangefinder.R;
import com.dev.laban.blerangefinder.screens.BaseActivity;
import com.dev.laban.blerangefinder.screens.Screen;

public class MainActivity extends BaseActivity<MainViewModel, MainView> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected Screen getScreen() {
        return Screen.MAIN;
    }
}
