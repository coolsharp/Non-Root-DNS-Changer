/*
** Copyright 2015, Mohamed Naufal
**
** Licensed under the Apache License, Version 2.0 (the "License");
** you may not use this file except in compliance with the License.
** You may obtain a copy of the License at
**
**     http://www.apache.org/licenses/LICENSE-2.0
**
** Unless required by applicable law or agreed to in writing, software
** distributed under the License is distributed on an "AS IS" BASIS,
** WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
** See the License for the specific language governing permissions and
** limitations under the License.
*/

package com.coolsharp;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.VpnService;
import android.os.ParcelFileDescriptor;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class LocalVPNService extends VpnService {
    private static final String VPN_ADDRESS = "10.0.0.2"; // Only IPv4 support for now
    private static final String DNS2 = "10.102.4.1"; // Intercept everything

    public static final String BROADCAST_VPN_STATE = "com.coolsharp.VPN_STATE";

    private static boolean isRunning = false;

    private ParcelFileDescriptor vpnInterface = null;

    private PendingIntent pendingIntent;

    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventVpn event) {/* Do something */};

    private void setupVPN(String dns1) {
        if (vpnInterface == null) {
            isRunning = true;
            Builder builder = new Builder();
            builder.addAddress(VPN_ADDRESS, 32);
            builder.addDnsServer(dns1); // "10.102.1.7"
            builder.addDnsServer(DNS2);
            vpnInterface = builder.setSession(getString(R.string.app_name)).setConfigureIntent(pendingIntent).establish();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY; // 서비스가 죽으면 다시 실행 금지
    }

    public static boolean isRunning() {
        return isRunning;
    }

    @Override
    public void onDestroy() {
        isRunning = false;
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
