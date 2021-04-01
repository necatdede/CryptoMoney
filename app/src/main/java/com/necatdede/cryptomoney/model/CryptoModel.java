package com.necatdede.cryptomoney.model;
import com.google.gson.annotations.SerializedName;
public class CryptoModel {


    @SerializedName("logo_url")
    public String logo_url;

    @SerializedName("name")
    public String name;

    @SerializedName("currency")
    public String currency;

    @SerializedName("price")
    public String price;


}
