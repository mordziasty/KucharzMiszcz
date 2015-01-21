package com.example.student7.kucharzmiszcz;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.NonConfigurationInstance;
import org.androidannotations.annotations.ViewById;

import com.example.student7.kucharzmiszcz.data.EmailAndPassword;
import com.example.student7.kucharzmiszcz.data.User;

@EActivity(R.layout.activity_my)
public class LoginActivity extends ActionBarActivity {

    @Bean
    @NonConfigurationInstance
    RestLoginBackgroundTask restLoginBackgroundTask;

    @ViewById
    EditText email;

    @ViewById
    EditText password;

    ProgressDialog ringProgressDialog;

    @AfterViews
    void init() {
        ringProgressDialog = new ProgressDialog(this);
        ringProgressDialog.setMessage("Loading...");
        ringProgressDialog.setIndeterminate(true);
    }

    @Click
    void dodajClicked()    {
        ringProgressDialog.show();
        EmailAndPassword emailAndPassword = new EmailAndPassword();
        emailAndPassword.email = email.getText().toString(); //"example@example.com";
        emailAndPassword.password = password.getText().toString(); //"test00";
        restLoginBackgroundTask.login(emailAndPassword);
    }

    public void loginSuccess(User user) {
        ringProgressDialog.dismiss();
        FirstActivity_.intent(this).user(user).start();
    }

    public void showError(Exception e) {
        ringProgressDialog.dismiss();
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        e.printStackTrace();
    }

}