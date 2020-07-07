package com.king.lib.frame.mvvm;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * Desc:
 *
 * @author：Jing Yang
 * @date: 2020/7/3 10:22
 */
public class BaseViewModel extends AndroidViewModel {

    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    public MutableLiveData<Boolean> loadingObserver = new MutableLiveData<>();
    public MutableLiveData<String> messageObserver = new MutableLiveData<>();

    public BaseViewModel(Application application) {
        super(application);
    }

    protected void addDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    public void onDestroy() {
        compositeDisposable.clear();
    }

    public void showMessage(String msg) {
        messageObserver.setValue(msg);
    }

    public void postMessage(String msg) {
        messageObserver.postValue(msg);
    }

    public void showLoading(boolean show) {
        loadingObserver.setValue(show);
    }

    public void postLoading(boolean show) {
        loadingObserver.postValue(show);
    }

}
