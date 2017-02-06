
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

import android.app.PendingIntent;
import android.content.Intent;
import android.net.VpnService;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;

public class LocalVpnService extends VpnService {
    // [final/static_property]====================[START]===================[final/static_property]

    /** VPN 주소 */
    final static private String VPN_ADDRESS = "10.0.0.2"; // Only IPv4 support for now
    /** 브로드캐스트 명칭 */
    final static public String BROADCAST_VPN_STATE = "com.coolsharp.VPN_STATE";

    /** 상태 */
    static private boolean isRunning = false;

    // [final/static_property]=====================[END]====================[final/static_property]
    // [private/protected/public_property]========[START]=======[private/protected/public_property]

    /** Vpn 인터페이스 */
    private ParcelFileDescriptor vpnInterface = null;
    /** pendingIntent */
    private PendingIntent pendingIntent = null;
    /** dns */
    private String dns;

    // [private/protected/public_property]=========[END]========[private/protected/public_property]
    // [interface/enum/inner_class]===============[START]==============[interface/enum/inner_class]
    // [interface/enum/inner_class]================[END]===============[interface/enum/inner_class]
    // [inherited/listener_method]================[START]===============[inherited/listener_method]

    /**
     *
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle extra = intent.getExtras();

        if (null != extra) {
            dns = (String) extra.get("dns");
            setDns();
        }

        return START_NOT_STICKY; // 서비스가 죽으면 다시 실행 금지
    }

    // [inherited/listener_method]=================[END]================[inherited/listener_method]
    // [life_cycle_method]========================[START]=======================[life_cycle_method]

    /**
     *
     */
    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.getDefault().register(this);
    }

    /**
     *
     */
    @Override
    public void onDestroy() {
        isRunning = false;
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    // [life_cycle_method]=========================[END]========================[life_cycle_method]
    // [private_method]===========================[START]==========================[private_method]

    /**
     *
     */
    private void setDns() {
        if (vpnInterface == null) {
            isRunning = true;
            Builder builder = new Builder();
            builder.addAddress(VPN_ADDRESS, 32);
            builder.addDnsServer(dns); // "10.102.1.7"
            builder.addDnsServer(DnsManager.DNS2);
            vpnInterface = builder.setSession(getString(R.string.app_name)).setConfigureIntent(pendingIntent).establish();
        }
    }

    /**
     *
     */
    private void stopVpn() {
        try {
            if (null != vpnInterface) {
                vpnInterface.close();
                vpnInterface = null;
                isRunning = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // [private_method]============================[END]===========================[private_method]
    // [public_method]============================[START]===========================[public_method]

    /**
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventVpn event) {
        switch (event.getDnsStatus()) {
            case DS_STOP:
                stopVpn();
                stopSelf();
                break;
            case DS_RUNNING:
                break;
        }
    }

    // [public_method]=============================[END]============================[public_method]
    // [get/set]==================================[START]=================================[get/set]

    /**
     *
     * @return
     */
    public static boolean isRunning() {
        return isRunning;
    }

    // [get/set]===================================[END]==================================[get/set]
}
