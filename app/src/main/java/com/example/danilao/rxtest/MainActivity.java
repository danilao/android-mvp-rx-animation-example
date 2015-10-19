package com.example.danilao.rxtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.github.lzyzsd.circleprogress.DonutProgress;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainView {

    @Bind(R.id.donut_progress) DonutProgress donut;
    @Bind(R.id.button) Button button;

    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        updateProgress(0);

        presenter = new MainPresenterImpl(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.button)
    public void start() {
        presenter.calculateProgress(donut.getProgress());
    }

    @Override
    public void updateProgress(int newProgress) {
        donut.setProgress(newProgress);
    }

    @Override
    public void enableButton() {
        button.setEnabled(true);
    }

    @Override
    public void disableButton() {
        button.setEnabled(false);
    }
}
