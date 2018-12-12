package sharukh.piggy.mfcomparision.controller;

import android.content.Context;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;
import sharukh.piggy.mfcomparision.BuildConfig;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Developed by Sharukh Mohammed on 24 July 2018 at 17:00. Copyright © 2018 Wheelstreet All rights reserved.
 */
public class NetworkController {

    private static final int SIZE_OF_CACHE = 10485760;

    private final static String TAG = "NetworkController";

    private static HashMap<String, Object> mClients;

    private static Retrofit mRetrofit;

    private static NetworkController mRetrofitClient;

    static {
        mClients = new HashMap<>();
    }

    private NetworkController() {
    }

    public static NetworkController getInstance() {


        if (mRetrofitClient == null) {
            mRetrofitClient = new NetworkController();
        }

        return mRetrofitClient;
    }

    public <T> T getClient(Class<T> tClass, final Context context) {
        return getClient(tClass, context, true);
    }

    public <T> T getClient(Class<T> tClass, final Context context, boolean handleErrorsInternally) {

        final String TAG = "NetworkController";

        //Warns if context passed is not an activity context
        /*if (!(context instanceof BaseActivity)) {
            Log.w(TAG, "Not Using Base Activity context");
        }*/

        if (mRetrofit == null) {

            final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(Level.BODY);

            final Interceptor authorization = chain -> {

                Request request = chain.request();
                /*RequestBody body = request.body();

                if (request.method().equalsIgnoreCase("POST")) {

                   *//* Buffer buffer = new Buffer();
                    if (body != null) {
                        body.writeTo(buffer);
                        String postBody = buffer.readUtf8();
                        postBody += postBody.length() > 0 ? "&" : "";

                        if (body.contentType() != null && body.contentType().toString().contains("multipart/form-data")) {
                            request = request.newBuilder()
                                    .addHeader("authorization", "Token a41d2b39e3b47412504509bb5a1b66498fb1f43a")
                                    .build();
                        } else {

                            request = request.newBuilder()
                                    .post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8"), postBody))
                                    .addHeader("authorization", "Token a41d2b39e3b47412504509bb5a1b66498fb1f43a")
                                    .build();
                        }
                    }
*//*

                    request = request.newBuilder()
                            .addHeader("authorization", "Token a41d2b39e3b47412504509bb5a1b66498fb1f43a")
                            .addHeader("cache-control", "no-cache")
                            .build();

                } else if (request.method().equalsIgnoreCase("GET")) {

                    HttpUrl httpUrl = request.url()
                            .newBuilder()
                            .build();

                    request = request
                            .newBuilder()
                            .url(httpUrl)
                            .addHeader("authorization", "Token a41d2b39e3b47412504509bb5a1b66498fb1f43a")
                            .addHeader("cache-control", "no-cache")
                            .build();
                }*/

                HttpUrl httpUrl = request.url()
                        .newBuilder()
                        .build();


                request = request
                        .newBuilder()
                        .url(httpUrl)
                        .addHeader("authorization", "Token a41d2b39e3b47412504509bb5a1b66498fb1f43a")
                        .addHeader("content-type","application/json")
                        .addHeader("cache-control", "no-cache")
                        .build();

                return chain.proceed(request);
            };


            Builder rBuilder = new Builder();
            rBuilder.baseUrl("https://api.piggy.co.in/");
            rBuilder.addConverterFactory(GsonConverterFactory.create());

            {
                //Setting up OkHttpInterceptors
                OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
                okBuilder.addInterceptor(authorization);

                okBuilder.connectTimeout(60, TimeUnit.SECONDS)
                        .readTimeout(60, TimeUnit.SECONDS)
                        .writeTimeout(60, TimeUnit.SECONDS);

                //Add interceptors for Debug config
                if (BuildConfig.DEBUG) {
                    okBuilder.addInterceptor(loggingInterceptor);
                    //okBuilder.addInterceptor(DeltaFile.getInterceptor(context.getApplicationContext()));
                }

                rBuilder.client(okBuilder.build());
            }

            mRetrofit = rBuilder.build();
        }

        T client = (T) mClients.get(tClass.getCanonicalName());
        if (client != null) {
            return client;
        }

        client = mRetrofit.create(tClass);
        mClients.put(tClass.getCanonicalName(), client);
        return client;
    }


    //This is a worker thread. To use Toast or SnackBar, we must run from Main thread.
/*    private void runOnUiThread(Context context, Runnable run) {

        if (context instanceof BaseActivity) {
            ((BaseActivity) context).runOnUiThread(run);
        } else {
            Log.w(TAG, "Given context is not instance of an activity");
        }

    }*/
}
