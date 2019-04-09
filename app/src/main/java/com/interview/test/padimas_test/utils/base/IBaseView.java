package com.interview.test.padimas_test.utils.base;

import android.content.Context;

public interface IBaseView {

    void showProgressDialog(String message);

    void showProgressDialog();

    void showProgressDialog(boolean flag);

    void hideProgressDialog();

    void showToast(int resId);

    void showToast(String msg);

    Context getContext();

    void close();
}
