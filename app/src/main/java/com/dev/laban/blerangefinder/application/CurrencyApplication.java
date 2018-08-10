package com.dev.laban.blerangefinder.application;

import android.annotation.SuppressLint;
import android.app.Application;

import com.dev.laban.blerangefinder.errorrs.ErrorRepository;
import com.dev.laban.blerangefinder.errorrs.ErrorRepositoryLocator;
import com.dev.laban.blerangefinder.presentation.DataRepositoryFactoryImpl;
import com.dev.laban.blerangefinder.presentation.VMFactoryImpl;
import com.dev.laban.blerangefinder.presentation.ViewModelFactory;
import com.dev.laban.blerangefinder.screens.BaseViewModel;
import com.dev.laban.blerangefinder.screens.Screen;

public class CurrencyApplication extends Application implements ViewModelFactory{
    private ViewModelFactory viewModelFactory;

    @SuppressLint("CheckResult")
    @Override
    public void onCreate() {
        super.onCreate();
        ErrorRepository errorRepository = new ErrorRepository();
        ErrorRepositoryLocator.setRepository(errorRepository);


        viewModelFactory = new VMFactoryImpl(new DataRepositoryFactoryImpl());
    }

    @Override
    public <T extends BaseViewModel> T onCreateScreen(Screen screen) {
        return viewModelFactory.onCreateScreen(screen);
    }

    @Override
    public void onDestroyScreen(Screen screen, boolean rotation) {
        viewModelFactory.onDestroyScreen(screen, rotation);
    }

}
