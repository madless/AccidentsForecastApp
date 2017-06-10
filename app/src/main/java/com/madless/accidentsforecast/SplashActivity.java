package com.madless.accidentsforecast;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.madless.accidentsforecast.utils.IntentHelper;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 6/2/2017.
 */

public class SplashActivity extends BaseActivity {
    private ProgressBar progressBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        RepositoryManager.get().preload(this)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer() {
                    @Override
                    public void accept(Object o) throws Exception {}
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(SplashActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                        loggr.e("error");
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        Toast.makeText(SplashActivity.this, "Fetched!", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        loggr.d("ok");
                        IntentHelper.startMainActivity(SplashActivity.this);
                    }
                });
    }
}
