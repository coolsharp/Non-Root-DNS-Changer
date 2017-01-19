
/*
 *                                             `''~``"
 *                                           &( ^ a ^ )&
 * ---------------------------------------.oooO-------Oooo.----------------------------------------
 *  Description :
 *  Date        : 17. 1. 20 오전 1:30
 *  Author      : coolsharp
 *  History     : 17. 1. 20 오전 1:30
 *                                         .oooO
 *                                         (   )   Oooo.
 * -----------------------------------------\ (----(   )-----------------------------coolsharp 2017
 *                                           \_)    ) /
 *                                                 (_/
 *
 */

package com.coolsharp;

import lombok.Data;

/**
 * Created by coolsharp on 2017. 1. 18..
 */

public @Data
class EventVpn {
    // [final/static_property]====================[START]===================[final/static_property]
    // [final/static_property]=====================[END]====================[final/static_property]
    // [private/protected/public_property]========[START]=======[private/protected/public_property]

    /** Dns 상태 */
    private DnsStatus dnsStatus;
    /** Dns 정보 */
    private String dns;

    // [private/protected/public_property]=========[END]========[private/protected/public_property]
    // [interface/enum/inner_class]===============[START]==============[interface/enum/inner_class]

    /** Dns 상태 */
    public enum DnsStatus {DS_STOP, DS_RUNNING}

    // [interface/enum/inner_class]================[END]===============[interface/enum/inner_class]
    // [inherited/listener_method]================[START]===============[inherited/listener_method]
    // [inherited/listener_method]=================[END]================[inherited/listener_method]
    // [life_cycle_method]========================[START]=======================[life_cycle_method]

    /**
     * 생성자
     * @param dnsStatus dns 상태
     * @param dns dns 값
     */
    public EventVpn(DnsStatus dnsStatus, String dns) {
        this.dnsStatus = dnsStatus;
        this.dns = dns;
    }

    /**
     * 생성자
     * @param dnsStatus dns 상태
     */
    public EventVpn(DnsStatus dnsStatus) {
        this.dnsStatus = dnsStatus;
    }

    // [life_cycle_method]=========================[END]========================[life_cycle_method]
    // [private_method]===========================[START]==========================[private_method]
    // [private_method]============================[END]===========================[private_method]
    // [public_method]============================[START]===========================[public_method]
    // [public_method]=============================[END]============================[public_method]
    // [get/set]==================================[START]=================================[get/set]
    // [get/set]===================================[END]==================================[get/set]

}
