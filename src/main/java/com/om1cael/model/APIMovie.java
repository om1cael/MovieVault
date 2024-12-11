package com.om1cael.model;

import com.google.gson.annotations.SerializedName;

public record APIMovie(@SerializedName("Title") String title,
                       @SerializedName("Year") String year,
                       @SerializedName("Released") String released,
                       @SerializedName("Genre") String genre,
                       @SerializedName("Plot") String plot,
                       String imdbRating) {}
