package com.dev.laban.blerangefinder.presentation;

import com.dev.laban.blerangefinder.screens.BaseViewModel;
import com.dev.laban.blerangefinder.screens.Screen;

public interface ViewModelFactory {

    <T extends BaseViewModel> T onCreateScreen(Screen screen);

    void onDestroyScreen(Screen screen, boolean rotation);

}
