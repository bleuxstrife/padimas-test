package com.interview.test.padimas_test.utils.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import com.interview.test.padimas_test.R;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;


public abstract class BaseActivity extends AppCompatActivity implements IBaseView {
    private ProgressDialog mProgressDialog;

    public abstract void initContentView();

    public static final String TAG = BaseActivity.class.getSimpleName();

    public abstract void initView(Bundle savedInstanceState);

    BaseApplication baseApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        initContentView();
        ButterKnife.bind(this);
        initView(savedInstanceState);
        baseApplication = new BaseApplication();

    }

    Toolbar mToolbar;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        mToolbar = findViewById(R.id.toolbar_main);
        if (null != mToolbar) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setTitle("");
        }
    }

    public void showToolbarBack() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void finish() {
        super.finish();
    }


    public void showProgressDialog(boolean flag, String message) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setCancelable(flag);
            mProgressDialog.setCanceledOnTouchOutside(false);
            if (message.isEmpty()) {
                mProgressDialog.setMessage("Loading...");
            } else {
                mProgressDialog.setMessage(message);
            }
        }

        mProgressDialog.show();
    }

    @Override
    public void showProgressDialog(String message) {
        showProgressDialog(true, message);
    }

    @Override
    public void showProgressDialog() {
        showProgressDialog(true);
    }

    @Override
    public void showProgressDialog(boolean flag) {
        showProgressDialog(flag, "");
    }

    @Override
    public void hideProgressDialog() {
        if (mProgressDialog == null)
            return;

        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void showToast(int resId) {
        showToast(getString(resId));
    }

    @Override
    public void showToast(String msg) {
        if (!isFinishing()) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void close() {
        finish();
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
        baseApplication.removeActivity(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        baseApplication.removeActivity(this);
    }


}
