package com.coolsharp;

import android.content.Context;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by cools on 2017-01-31.
 */

public class WeatherThread extends Thread {
    final static String TAG = "WeatherThread";
    Context mContext;
    DnsItemList weatherRepo;

    int version = 1;
    String lat;
    String lon;

    public WeatherThread(Context mContext, double lat, double lon) {
        this.mContext = mContext;
        this.lat = String. valueOf(lat);
        this.lon = String .valueOf(lon);
    }

    @Override
    public void run() {
        super.run();
        Retrofit client = new Retrofit.Builder().baseUrl("https://raw.githubusercontent.com/").addConverterFactory(GsonConverterFactory.create()).build();
        DnsItemList.DnsItemListApiInterface service = client.create(DnsItemList.DnsItemListApiInterface.class);
        Call<DnsItemList> call = service.get_dns_item_list_retrofit();
        call.enqueue(new Callback<DnsItemList>() {
            @Override
            public void onResponse(Call<DnsItemList> call, Response<DnsItemList> response) {
                if(response.isSuccessful()){
                    weatherRepo = response.body();
                }
            }

            @Override
            public void onFailure(Call<DnsItemList> call, Throwable t) {
            }
        });
    }
}
