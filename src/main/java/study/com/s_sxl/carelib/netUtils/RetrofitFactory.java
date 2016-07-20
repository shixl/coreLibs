package study.com.s_sxl.carelib.netUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {

    private static Retrofit mRetrofit;
    private static String mBaseUrl;
    private static final int DEFAULT_TIMEOUT = 5;

    public static void setBaseUrl(String url){
        mBaseUrl = url;
    }

    public static Retrofit getRetrofit(){

        if(mRetrofit == null){
            if(mBaseUrl == null ){
                throw new IllegalStateException("请在调用getFactory之前先调用setBaseUrl");
            }

            OkHttpClient.Builder okHttpClient = new OkHttpClient().newBuilder();
            okHttpClient.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

            Retrofit.Builder builder = new Retrofit.Builder();

            builder.baseUrl(mBaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(okHttpClient.build());

            mRetrofit = builder.build();
        }

        return mRetrofit;
    }
}
