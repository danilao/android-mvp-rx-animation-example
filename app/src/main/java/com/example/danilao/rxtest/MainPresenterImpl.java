package com.example.danilao.rxtest;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by danilao on 3/9/15.
 */
public class MainPresenterImpl implements MainPresenter {

    private MainView mainView;

    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void calculateProgress(int currentProgress) {

        mainView.disableButton();

        int finalValue = new Random().nextInt(100);
        int difference = finalValue - currentProgress;
        int direction = difference > 0 ? 1 : -1;

        Integer numbers[] = new Integer[Math.abs(difference)];

        for(int i = 0; i < Math.abs(difference); i++) {
            numbers[i] = currentProgress + direction * i;
        }

        Observable<Integer> values = Observable.from(numbers);
        Observable<Long> interval = Observable.interval(30, TimeUnit.MILLISECONDS);

        Observable.zip(values, interval, (animationValue, aLong) -> animationValue)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnCompleted(() -> mainView.enableButton())
                .subscribe(animationValue -> mainView.updateProgress((int)animationValue));


    }
}
