
/*
 *                                             `''~``"
 *                                           &( ^ a ^ )&
 * ---------------------------------------.oooO-------Oooo.----------------------------------------
 *  Description :
 *  Date        : 17. 1. 18 오후 9:50
 *  Author      : coolsharp
 *  History     : 17. 1. 18 오후 9:50
 *                                           .oooO
 *                                          (   )   Oooo.
 * -----------------------------------------\ (----(   )-----------------------------coolsharp 2017
 *                                          \_)    ) /
 *                                               (_/
 *
 */

package com.coolsharp;

import lombok.Data;

/**
 * Created by coolsharp on 2017. 2. 3..
 */

public @Data
class EventVpnOnClick {
    private String ip;

    public EventVpnOnClick(String ip) {
        this.ip = ip;
    }
}
