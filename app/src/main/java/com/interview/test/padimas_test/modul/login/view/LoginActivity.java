package com.interview.test.padimas_test.modul.login.view;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.interview.test.padimas_test.R;
import com.interview.test.padimas_test.modul.dashboard.view.DashboardActivity;
import com.interview.test.padimas_test.modul.login.controller.LoginController;
import com.interview.test.padimas_test.modul.login.model.LoginModel;
import com.interview.test.padimas_test.utils.BaseObject;
import com.interview.test.padimas_test.utils.Constant;
import com.interview.test.padimas_test.utils.UIUtils;
import com.interview.test.padimas_test.utils.base.BaseActivity;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.emailTextInput)
    TextInputLayout emailTextInput;
    @BindView(R.id.passwordTextInput)
    TextInputLayout passwordTextInput;
    LoginController loginController;
    @Override
    public void initContentView() {
        setContentView(R.layout.activity_login);
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        loginController = new LoginController(getContext());
    }

    @OnClick(R.id.loginBT)
    public void onViewClicked() {
        if (UIUtils.validation(getContext(), emailTextInput, passwordTextInput)) {
            showProgressDialog(getString(R.string.login_loading));
            loginController.login(emailTextInput.getEditText().getText().toString(),
                    passwordTextInput.getEditText().getText().toString());
        }
    }

    @Subscribe
    public void onEvent(BaseObject object) {
        hideProgressDialog();
        if(object.getCode()== Constant.LOGIN){
            startActivity(new Intent(this, DashboardActivity.class));
            finish();
        } else {
            LoginModel loginModel = (LoginModel) object.getData();
            showToast(loginModel.getMessage());
        }
    }

    @Subscribe
    public void onErrorEvent(Throwable t) {
        hideProgressDialog();
        showToast(t.getMessage());
        Log.e("onErrorEvent: ", t.getMessage());
    }


}
