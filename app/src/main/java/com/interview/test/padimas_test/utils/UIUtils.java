package com.interview.test.padimas_test.utils;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.text.Html;
import android.text.Spanned;
import android.widget.EditText;

import com.interview.test.padimas_test.R;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.regex.Pattern;

public class UIUtils {
    public static boolean validation(Context context, TextInputLayout emailWrapper, TextInputLayout passwordWrapper) {
        boolean isEmail = false;
        boolean isPassword = false;
        EditText email = emailWrapper.getEditText();
        EditText password = passwordWrapper.getEditText();

        if (email.getText().toString().equalsIgnoreCase("")) {
            emailWrapper.setErrorEnabled(true);
            emailWrapper.setError(context.getString(R.string.error_field_required));
        } else if (!UIUtils.isValidEmail(email.getText().toString())) {
            emailWrapper.setErrorEnabled(true);
            emailWrapper.setError(context.getString(R.string.error_invalid_email));
        } else {
            emailWrapper.setErrorEnabled(false);
            isEmail = true;
        }

        if (password.getText().toString().equalsIgnoreCase("")) {
            passwordWrapper.setErrorEnabled(true);
            passwordWrapper.setError(context.getString(R.string.error_field_required));
        } else if (password.getText().toString().length() < 6) {
            passwordWrapper.setErrorEnabled(true);
            passwordWrapper.setError(context.getString(R.string.pass6Char));
        } else {
            passwordWrapper.setErrorEnabled(false);
            isPassword = true;
        }


        return isEmail && isPassword;
    }

    public static boolean isValidEmail(String email) {

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }

    public static String setCurrency(int price){
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        return ""+formatRupiah.format(price);
    }

}
