package com.example.student7.kucharzmiszcz;

import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.RequiresHeader;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.RestClientHeaders;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.example.student7.kucharzmiszcz.data.EmailAndPassword;
import com.example.student7.kucharzmiszcz.data.Recipe;
import com.example.student7.kucharzmiszcz.data.KucharzMiszcz;
import com.example.student7.kucharzmiszcz.data.User;

@Rest(rootUrl = "http://pegaz.wzr.ug.edu.pl:8080/rest",
        converters = { MappingJackson2HttpMessageConverter.class })
@RequiresHeader({"X-Dreamfactory-Application-Name"})

public interface KucharzMiszczRestClient extends RestClientHeaders {

    @Get("/db/person")
    KucharzMiszcz getKucharzMiszcz();

    @Post("/db/person")
    void addKucharzMiszczEntry(Recipe recipe);

    @Post("/user/session")
    User login(EmailAndPassword emailAndPassword);


}