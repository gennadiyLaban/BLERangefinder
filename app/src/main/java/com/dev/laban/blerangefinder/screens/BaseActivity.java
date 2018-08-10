package com.dev.laban.blerangefinder.screens;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.dev.laban.blerangefinder.errorrs.ErrorRepository;
import com.dev.laban.blerangefinder.errorrs.ErrorRepositoryLocator;
import com.dev.laban.blerangefinder.presentation.ViewModelFactory;
import com.dev.laban.blerangefinder.screens.error.ErrorDialogFragment;
import com.dev.laban.blerangefinder.utils.MessageHelper;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import com.dev.laban.blerangefinder.errorrs.exceptions.Error;


public abstract class BaseActivity<T extends BaseViewModel, V extends BaseView> extends AppCompatActivity {
    private T viewModel;
    private boolean rotation = false;
    private Disposable errorDispossible;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rotation = false;
        viewModel = ((ViewModelFactory) getApplication()).onCreateScreen(getScreen());
    }

    @Override
    protected void onStart() {
        super.onStart();
        attachPresenter();
        ErrorRepository repository = ErrorRepositoryLocator.getRepository();
        errorDispossible = repository.getErrorFlow()
                .flatMapSingle(error -> onError(error))
                .subscribe(error -> repository.nextError());
        repository.nextError();
    }

    @Override
    protected void onStop() {
        super.onStop();
        detachPresenter();
        errorDispossible.dispose();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        rotation = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ((ViewModelFactory) getApplication()).onDestroyScreen(getScreen(), rotation);
    }

    protected Single<Error> onError(Error error) {
        error.setMessage(MessageHelper.getErrorMessage(error, this));
        ErrorDialogFragment fragment = ErrorDialogFragment.newInstance(error);
        Single<Error> confirmFlow = fragment.getConfirmErrorFlow();
        fragment.show(getSupportFragmentManager().beginTransaction(), "");
        return confirmFlow;
    }

    protected T getViewModel() {
        return viewModel;
    }

    protected abstract Screen getScreen();

    protected void attachPresenter() {
        viewModel.attachView((V) this);
    }

    protected void detachPresenter() {
        viewModel.detachView();
    }

}
