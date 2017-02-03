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
