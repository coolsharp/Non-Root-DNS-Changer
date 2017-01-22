/*
 *                                             `''~``"
 *                                           &( ^ a ^ )&
 * ---------------------------------------.oooO-------Oooo.----------------------------------------
 *  Description :
 *  Date        : 17. 1. 20 오전 4:14
 *  Author      : coolsharp
 *  History     : 17. 1. 20 오전 4:14
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

/**
 * Created by cools on 2017-01-20.
 */

public class DnsItem {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("dns")
    @Expose
    private String dns;
    @SerializedName("project")
    @Expose
    private String project;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDns() {
        return dns;
    }

    public void setDns(String dns) {
        this.dns = dns;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }
}
