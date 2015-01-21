package com.example.student7.kucharzmiszcz;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.rest.RestService;

import com.example.student7.kucharzmiszcz.data.EmailAndPassword;
import com.example.student7.kucharzmiszcz.data.User;

@EBean
public class RestLoginBackgroundTask {

    @RootContext
    LoginActivity activity;

    @RestService
    KucharzMiszczRestClient restClient;

    @Background
    void login(EmailAndPassword emailAndPassword) {
        try {
            restClient.setHeader("X-Dreamfactory-Application-Name", "cookbook");
            User user = restClient.login(emailAndPassword);
            publishResult(user);
        } catch (Exception e) {
            publishError(e);
        }
    }

    @UiThread
    void publishResult(User user) {
        activity.loginSuccess(user);
    }

    @UiThread
    void publishError(Exception e) {
        activity.showError(e);
    }

}
