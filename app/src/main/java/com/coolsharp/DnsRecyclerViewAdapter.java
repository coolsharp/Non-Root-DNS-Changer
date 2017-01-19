/*
 *                                             `''~``"
 *                                           &( ^ a ^ )&
 * ---------------------------------------.oooO-------Oooo.----------------------------------------
 *  Description :
 *  Date        : 17. 1. 20 오전 4:00
 *  Author      : coolsharp
 *  History     : 17. 1. 20 오전 4:00
 *                                         .oooO
 *                                         (   )   Oooo.
 * -----------------------------------------\ (----(   )-----------------------------coolsharp 2017
 *                                           \_)    ) /
 *                                                 (_/
 *
 */

package com.coolsharp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by cools on 2017-01-20.
 */

public class DnsRecyclerViewAdapter extends RecyclerView.Adapter {
    // [final/static_property]====================[START]===================[final/static_property]
    // [final/static_property]=====================[END]====================[final/static_property]
    // [private/protected/public_property]========[START]=======[private/protected/public_property]

    private DnsItemList dnsItemList;

    // [private/protected/public_property]=========[END]========[private/protected/public_property]
    // [interface/enum/inner_class]===============[START]==============[interface/enum/inner_class]

    public final static class DnsItemViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvDns;

        public DnsItemViewHolder(View itemView) {
            super(itemView);

            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvDns = (TextView) itemView.findViewById(R.id.tvDns);
        }
    }

    // [interface/enum/inner_class]================[END]===============[interface/enum/inner_class]
    // [inherited/listener_method]================[START]===============[inherited/listener_method]

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);

        return new DnsItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DnsItem dnsItem = dnsItemList.getDnsList().get(position);
        DnsItemViewHolder dnsItemViewHolder = (DnsItemViewHolder) holder;
        dnsItemViewHolder.tvName.setText(dnsItem.getName());
        dnsItemViewHolder.tvDns.setText(dnsItem.getDns());
    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (null != dnsItemList && null != dnsItemList.getDnsList()) {
            count = dnsItemList.getDnsList().size();
        }

        return count;
    }

    // [inherited/listener_method]=================[END]================[inherited/listener_method]
    // [life_cycle_method]========================[START]=======================[life_cycle_method]
    // [life_cycle_method]=========================[END]========================[life_cycle_method]
    // [private_method]===========================[START]==========================[private_method]
    // [private_method]============================[END]===========================[private_method]
    // [public_method]============================[START]===========================[public_method]
    // [public_method]=============================[END]============================[public_method]
    // [get/set]==================================[START]=================================[get/set]

    public void setDnsItemList(DnsItemList dnsItemList) {
        this.dnsItemList = dnsItemList;
    }

    // [get/set]===================================[END]==================================[get/set]

}
