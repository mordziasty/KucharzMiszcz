package com.example.student7.kucharzmiszcz.data;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class KucharzMiszcz {

    @JsonProperty("record")
    public List<Recipe> records = new ArrayList<Recipe>();

}
