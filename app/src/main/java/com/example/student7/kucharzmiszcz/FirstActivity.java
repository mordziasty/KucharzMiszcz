package com.example.student7.kucharzmiszcz;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.NonConfigurationInstance;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import com.example.student7.kucharzmiszcz.adapter.RecipeListAdapter;
import com.example.student7.kucharzmiszcz.data.Recipe;
import com.example.student7.kucharzmiszcz.data.KucharzMiszcz;
import com.example.student7.kucharzmiszcz.data.User;

@EActivity(R.layout.activity_list)
@OptionsMenu(R.menu.my)
public class FirstActivity extends ActionBarActivity {

    @Extra
    User user;

    @ViewById
    ListView list;

    @Bean
    RecipeListAdapter adapter;

    @Bean
    @NonConfigurationInstance
    RestBackgroundTask restBackgroundTask;

    ProgressDialog ringProgressDialog;

    @AfterViews
    void init() {
        list.setAdapter(adapter);
        ringProgressDialog = new ProgressDialog(this);
        ringProgressDialog.setMessage("Loading...");
        ringProgressDialog.setIndeterminate(true);
        Toast.makeText(this, user.sessionId, Toast.LENGTH_LONG).show();
    }

    @ItemClick
    void listItemClicked(Recipe item) {
        Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show();
    }


    @Click
    void refreshClicked()    {
        ringProgressDialog.show();
        restBackgroundTask.getKucharzMiszcz();
    }

    public void updateKucharzMiszcz(KucharzMiszcz kucharzMiszcz) {
        ringProgressDialog.dismiss();
        adapter.update(kucharzMiszcz);
    }

    public void showError(Exception e) {
        ringProgressDialog.dismiss();
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        e.printStackTrace();
    }

//    @OptionsItem
//    void settingsSelected() {
//    }

}