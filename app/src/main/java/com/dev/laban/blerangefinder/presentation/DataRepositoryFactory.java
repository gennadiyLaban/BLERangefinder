package com.dev.laban.blerangefinder.presentation;

import com.dev.laban.blerangefinder.screens.DataRepository;
import com.dev.laban.blerangefinder.screens.Screen;

public interface DataRepositoryFactory {

    <T extends DataRepository> T createRepository(Screen screen);

}
