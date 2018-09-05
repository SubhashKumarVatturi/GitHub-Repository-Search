package com.blue.githhubsearch.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RepoData implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("full_name")
    @Expose
    private String fullName;

    @SerializedName("html_url")
    @Expose
    private String htmlUrl;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("watchers_count")
    @Expose
    private Integer watchersCount;

    @SerializedName("contributors_url")
    @Expose
    private String contributorsUrl;


    public String getContributorsUrl() {
        return contributorsUrl;
    }

    public void setContributorsUrl(String contributorsUrl) {
        this.contributorsUrl = contributorsUrl;
    }

    public RepoData(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        name = in.readString();
        fullName = in.readString();
        htmlUrl = in.readString();
        description = in.readString();
        url = in.readString();
        updatedAt = in.readString();
        contributorsUrl = in.readString();
        if (in.readByte() == 0) {
            watchersCount = null;
        } else {
            watchersCount = in.readInt();
        }
    }

    public static final Creator<RepoData> CREATOR = new Creator<RepoData>() {
        @Override
        public RepoData createFromParcel(Parcel in) {
            return new RepoData(in);
        }

        @Override
        public RepoData[] newArray(int size) {
            return new RepoData[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getWatchersCount() {
        return watchersCount;
    }

    public void setWatchersCount(Integer watchersCount) {
        this.watchersCount = watchersCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(name);
        dest.writeString(fullName);
        dest.writeString(htmlUrl);
        dest.writeString(description);
        dest.writeString(url);
        dest.writeString(updatedAt);
        dest.writeString(contributorsUrl);
        if (watchersCount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(watchersCount);
        }
    }

    class Woner {

    }

}

