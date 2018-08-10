package com.dev.laban.blerangefinder.presentation;

import com.dev.laban.blerangefinder.screens.DataRepository;
import com.dev.laban.blerangefinder.screens.Screen;

public class DataRepositoryFactoryImpl implements DataRepositoryFactory {

    public DataRepositoryFactoryImpl() {

    }

    @Override
    public <T extends DataRepository> T createRepository(Screen screen) {
        DataRepository dataRepository = new DataRepository() {};
        switch (screen) {

        }
        return (T) dataRepository;
    }
}
