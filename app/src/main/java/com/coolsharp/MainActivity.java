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

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.VpnService;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.greenrobot.eventbus.EventBus;

/**
 * 메인 화면
 */
public class MainActivity extends ActionBarActivity {
    // [final/static_property]====================[START]===================[final/static_property]

    /** VPN 요청 코드 */
    private static final int VPN_REQUEST_CODE = 0x0F;

    // [final/static_property]=====================[END]====================[final/static_property]
    // [private/protected/public_property]========[START]=======[private/protected/public_property]

    /** 작동중 */
    private boolean isRunningVpn;

    private RecyclerView recyclerView;

    // [private/protected/public_property]=========[END]========[private/protected/public_property]
    // [interface/enum/inner_class]===============[START]==============[interface/enum/inner_class]

    /**
     *
     */
    private BroadcastReceiver vpnStateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (LocalVpnService.BROADCAST_VPN_STATE.equals(intent.getAction())) {
                if (intent.getBooleanExtra("running", false))
                    isRunningVpn = false;
            }
        }
    };

    // [interface/enum/inner_class]================[END]===============[interface/enum/inner_class]
    // [inherited/listener_method]================[START]===============[inherited/listener_method]
    // [inherited/listener_method]=================[END]================[inherited/listener_method]
    // [life_cycle_method]========================[START]=======================[life_cycle_method]

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_vpn);
        final Button vpnButton = (Button) findViewById(R.id.vpn);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        String json = "{\"dnsList\":[{\"name\":\"QA #1\",\"dns\":\"10.102.1.7\"},{\"name\":\"QA #2\",\"dns\":\"10.102.1.7\"},{\"name\":\"QA #3\",\"dns\":\"10.102.1.7\"}]}";
        DnsRecyclerViewAdapter dnsRecyclerViewAdapter = new DnsRecyclerViewAdapter();
        Gson gson = new GsonBuilder().create();
        dnsRecyclerViewAdapter.setDnsItemList(gson.fromJson(json, DnsItemList.class));
        recyclerView.setAdapter(dnsRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        vpnButton.setOnClickListener(v -> {
            if (isRunning()) {
                startVpn();
            } else {
                stopVpn();
            }
        });
        isRunningVpn = false;
        LocalBroadcastManager.getInstance(this).registerReceiver(vpnStateReceiver,
                new IntentFilter(LocalVpnService.BROADCAST_VPN_STATE));
    }

    /**
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == VPN_REQUEST_CODE && resultCode == RESULT_OK) {
            Intent intent = new Intent(this, LocalVpnService.class);
            intent.putExtra("dns", "10.102.1.7");
            isRunningVpn = true;
            startService(intent);
//            enableButton(false);
        }
    }

    /**
     *
     */
    @Override
    protected void onResume() {
        super.onResume();
    }

    // [life_cycle_method]=========================[END]========================[life_cycle_method]
    // [private_method]===========================[START]==========================[private_method]

    /**
     * Vpn 시작
     */
    private void startVpn() {
        Intent vpnIntent = VpnService.prepare(this);
        if (vpnIntent != null)
            startActivityForResult(vpnIntent, VPN_REQUEST_CODE);
        else
            onActivityResult(VPN_REQUEST_CODE, RESULT_OK, null);
    }

    /**
     * Vpn 중지
     */
    private void stopVpn() {
        EventBus.getDefault().post(new EventVpn(EventVpn.DnsStatus.DS_STOP));
        isRunningVpn = false;
    }

    // [private_method]============================[END]===========================[private_method]
    // [public_method]============================[START]===========================[public_method]
    // [public_method]=============================[END]============================[public_method]
    // [get/set]==================================[START]=================================[get/set]

    private boolean isRunning() {
        return !isRunningVpn && !LocalVpnService.isRunning();
    }

    // [get/set]===================================[END]==================================[get/set]
}
