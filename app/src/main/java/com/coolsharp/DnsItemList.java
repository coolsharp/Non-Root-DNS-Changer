/*
 *                                             `''~``"
 *                                           &( ^ a ^ )&
 * ---------------------------------------.oooO-------Oooo.----------------------------------------
 *  Description :
 *  Date        : 17. 1. 20 오전 4:16
 *  Author      : coolsharp
 *  History     : 17. 1. 20 오전 4:16
 *                                         .oooO
 *                                         (   )   Oooo.
 * -----------------------------------------\ (----(   )-----------------------------coolsharp 2017
 *                                           \_)    ) /
 *                                                 (_/
 *
 */

package com.coolsharp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by cools on 2017-01-20.
 */

public class DnsItemList {
    @SerializedName("dnsList")
    @Expose
    private List<DnsItem> dnsList = null;

    public List<DnsItem> getDnsList() {
        return dnsList;
    }

    public void setDnsList(List<DnsItem> dnsList) {
        this.dnsList = dnsList;
    }

    public interface DnsItemListApiInterface {
        @Headers({"Accept: application/json"})
        @GET("coolsharp/Non-Root-DNS-Changer/master/dns.data")
//        Call<DnsItemList> get_dns_item_list_retrofit(@Query("version") int version, @Query("lat") String lat, @Query("lon") String lon);
        Call<DnsItemList> get_dns_item_list_retrofit();
    }
}
