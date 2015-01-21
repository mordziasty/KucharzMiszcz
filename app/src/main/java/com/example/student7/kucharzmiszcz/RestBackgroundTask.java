package com.example.student7.kucharzmiszcz;


import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.rest.RestService;

import com.example.student7.kucharzmiszcz.data.Recipe;
import com.example.student7.kucharzmiszcz.data.KucharzMiszcz;
//import com.example.student7.kucharzmiszcz.data.Picture;

@EBean
public class RestBackgroundTask {

    @RootContext
    FirstActivity activity;

    @RestService
    KucharzMiszczRestClient restClient;

    @Background
    void getKucharzMiszcz() {
        try {
            restClient.setHeader("X-Dreamfactory-Application-Name", "cookbook");
            KucharzMiszcz kucharzMiszcz = restClient.getKucharzMiszcz();
//            for (Recipe recipe: kucharzMiszcz.records) {
//                if (recipe.pictureId != null) {
//                    Picture picture = restClient.getPictureById(person.pictureId);
//                    person.pictureBytes = picture.base64bytes;
//                }
//
//            }
            publishResult(kucharzMiszcz);
        } catch (Exception e) {
            publishError(e);
        }
    }

    @Background
    void addPhoneBookEntry(Recipe recipe) {
        try {
            restClient.setHeader("X-Dreamfactory-Application-Name", "phonebook");
            restClient.addKucharzMiszczEntry(recipe);
            KucharzMiszcz kucharzMiszcz = restClient.getKucharzMiszcz();
            publishResult(kucharzMiszcz);
        } catch (Exception e) {
            publishError(e);
        }
    }

    @UiThread
    void publishResult(KucharzMiszcz kucharzMiszcz) {
        activity.updateKucharzMiszcz(kucharzMiszcz);
    }

    @UiThread
    void publishError(Exception e) {
        activity.showError(e);
    }

}
