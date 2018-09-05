package com.blue.githhubsearch.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Repos {

    @SerializedName("items")
    @Expose
    private List<RepoData> items = new ArrayList<RepoData>();

    public List<RepoData> getItems() {
        return items;
    }

    public void setItems(List<RepoData> items) {
        this.items = items;
    }

}

