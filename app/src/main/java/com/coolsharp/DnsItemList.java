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
}
