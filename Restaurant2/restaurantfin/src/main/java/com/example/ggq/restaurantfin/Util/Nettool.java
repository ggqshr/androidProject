package com.example.ggq.restaurantfin.Util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.example.ggq.restaurantfin.entity.Combo;
import com.example.ggq.restaurantfin.entity.Customer;
import com.example.ggq.restaurantfin.entity.Food;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import es.dmoral.toasty.Toasty;

/**
 * Created by ggq on 2017/5/20.
 */

public class Nettool {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static String baseurl = "http://192.168.191.1:8080/Hotpotserver";
    private OkHttpClient client;
    private Gson gson;

    public void log_req(String username, String password, final Context context, final Handler handler) {
        gson = new Gson();
        final Message message = new Message();
        client = new OkHttpClient();
        client.setConnectTimeout(10, TimeUnit.SECONDS);
        client.setReadTimeout(20, TimeUnit.SECONDS);
        Request.Builder builder = new Request.Builder();
        //构造requestbody，填写表单内容
        FormEncodingBuilder formEncodingBuilder = new FormEncodingBuilder();
        final RequestBody requestBody = formEncodingBuilder.add("username", username).add("password", password).build();

        Request build = builder.url(baseurl + "/Login").post(requestBody).build();

        final Call call = client.newCall(build);
        //在当前线程中执行
//        try {
//            call.execute();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //异步请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                message.arg1 = 4;
                handler.sendMessage(message);
                Looper.prepare();
                Toasty.error(context, "服务器出现问题了", 2500).show();
                Looper.loop();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String result = response.body().string();

                if (result.equals("error")) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    message.arg1 = 2;
                    handler.sendMessage(message);
                } else if (result.equals("null")) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    message.arg1 = 3;
                    handler.sendMessage(message);
                } else {
                    message.arg1 = 1;
                    Customer customer = gson.fromJson(result, Customer.class);
                    message.obj = customer;
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.sendMessage(message);
                }

            }
        });
    }

    public void Sign_req(final Customer customer, final Context context, final Handler handler) throws UnsupportedEncodingException {
        client = new OkHttpClient();
        client.setConnectTimeout(10, TimeUnit.SECONDS);
        client.setReadTimeout(20, TimeUnit.SECONDS);
        Gson gson = new Gson();
        final Message message = new Message();
        Request.Builder builder = new Request.Builder();
        String json = gson.toJson(customer);
        RequestBody requestBody = RequestBody.create(JSON, json);
        Request request = builder.url(baseurl + "/SignServlet").post(requestBody).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                message.arg1 = 3;
                handler.sendMessage(message);
                Looper.prepare();
                Toasty.error(context, "服务器出现问题了，请稍候再试", 2500).show();
                Looper.loop();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                String result = response.body().string();
//                Looper.prepare();
//                Toasty.info(context,result,2500).show();
//                Looper.loop();

                if (result.equals("ok")) {
                    message.arg1 = 1;
                    message.obj = customer;
                    handler.sendMessage(message);
                } else if (result.equals("cz")) {
                    message.arg1 = 2;
                    handler.sendMessage(message);
                }
            }
        });
    }

    public void getHotFood(final Context context, final Handler handler) {
        client = new OkHttpClient();
        client.setConnectTimeout(10, TimeUnit.SECONDS);
        client.setReadTimeout(20, TimeUnit.SECONDS);
        gson = new Gson();
        final Message message = new Message();
        Request.Builder builder = new Request.Builder();
        FormEncodingBuilder formEncodingBuilder = new FormEncodingBuilder();
        RequestBody requestBody = formEncodingBuilder.add("1", "1").build();
        Request request = builder.url(baseurl + "/PushFood").post(requestBody).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                message.arg1 = 2;
                handler.sendMessage(message);
                Looper.prepare();
                Toasty.error(context, "服务器出现问题了，请稍候再试", 2500).show();
                Looper.loop();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                message.arg1 = 1;
                String result = response.body().string();
                message.obj = gson.fromJson(result, new TypeToken<ArrayList<Food>>() {
                }.getType());
                handler.sendMessage(message);
            }
        });

    }

    public void getFoodAsType(final Context context, final Handler handler, String foodType) {
        client = new OkHttpClient();
        client.setConnectTimeout(10, TimeUnit.SECONDS);
        client.setReadTimeout(20, TimeUnit.SECONDS);
        gson = new Gson();
        final Message message = new Message();
        Request.Builder builder = new Request.Builder();
        FormEncodingBuilder formEncodingBuilder = new FormEncodingBuilder();
        RequestBody requestBody = formEncodingBuilder.add("foodType", foodType).build();
        Request request = builder.url(baseurl + "/GetFoodAsType").post(requestBody).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                message.arg1 = 2;
                handler.sendMessage(message);
                Looper.prepare();
                Toasty.error(context, "服务器出现问题了，请稍候再试", 2500).show();
                Looper.loop();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                message.arg1 = 1;
                String result = response.body().string();
                message.obj = gson.fromJson(result, new TypeToken<ArrayList<Food>>() {
                }.getType());
                handler.sendMessage(message);
            }
        });
    }

    public void getAllCombo(final Context context, final Handler handler) {
        client = new OkHttpClient();
        client.setConnectTimeout(10, TimeUnit.SECONDS);
        client.setReadTimeout(20, TimeUnit.SECONDS);
        gson = new Gson();
        final Message message = new Message();
        Request.Builder builder = new Request.Builder();
        FormEncodingBuilder formEncodingBuilder = new FormEncodingBuilder();
        RequestBody requestBody = formEncodingBuilder.add("1", "1").build();
        Request request = builder.url(baseurl + "/GetAllCombo").post(requestBody).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                message.arg1 = 2;
                handler.sendMessage(message);
                Looper.prepare();
                Toasty.error(context, "服务器出现问题了，请稍候再试", 2500).show();
                Looper.loop();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                message.arg1 = 2;
                String result = response.body().string();
                message.obj = gson.fromJson(result, new TypeToken<ArrayList<Combo>>() {
                }.getType());
                handler.sendMessage(message);
            }
        });
    }

    public void submitAllThing(final Context context, final Handler handler, HashMap<String, Object> map) {
        client = new OkHttpClient();
        client.setConnectTimeout(10, TimeUnit.SECONDS);
        client.setReadTimeout(20, TimeUnit.SECONDS);
        gson = new Gson();
        final Message message = new Message();
        Request.Builder builder = new Request.Builder();
        String json = gson.toJson(map);
        RequestBody requestBody = RequestBody.create(JSON, json);
        Request request = builder.url(baseurl + "/AddToBusiness").post(requestBody).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                message.arg1 = 3;
                handler.sendMessage(message);
                Looper.prepare();
                Toasty.error(context, "服务器出现问题了，请稍候再试", 2500).show();
                Looper.loop();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                String result = response.body().string();

                if (result.equals("ok")) {
                    message.arg1 = 1;
                    handler.sendMessage(message);
                } else if (result.equals("cz")) {
                    message.arg1 = 2;
                    handler.sendMessage(message);
                }
            }
        });
    }

    public void getBusinessAsCustomerNumber(final Context context, final Handler handler, String custNumber) {
        client = new OkHttpClient();
        client.setConnectTimeout(10, TimeUnit.SECONDS);
        client.setReadTimeout(20, TimeUnit.SECONDS);
        gson = new Gson();
        final Message message = new Message();
        Request.Builder builder = new Request.Builder();
        FormEncodingBuilder formEncodingBuilder = new FormEncodingBuilder();
        RequestBody requestBody = formEncodingBuilder.add("customerNumber", custNumber).build();
        Request request = builder.url(baseurl + "/GetBusiness").post(requestBody).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                message.arg1 = 2;
                handler.sendMessage(message);
//                Looper.prepare();
//                Toasty.error(context, "服务器出现问题了，请稍候再试", 2500).show();
//                Looper.loop();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                message.arg1 = 1;
                String result = response.body().string();
                HashMap<String, Object> map = gson.fromJson(result, new TypeToken<HashMap<String, Object>>() {
                }.getType());
                String businessJson = gson.toJson(map.get("business"));
                String foodCheckJson = gson.toJson(map.get("foodcheck"));
                ArrayList<String> strings = new ArrayList<String>();
                Log.d("net",businessJson);
                Log.d("net",foodCheckJson);
                strings.add(businessJson);
                strings.add(foodCheckJson);
                message.obj = strings;
                handler.sendMessage(message);
            }
        });
    }

    public void changPwd(final Context context, final Handler handler, String username, String userpwd) {
        client = new OkHttpClient();
        client.setConnectTimeout(10, TimeUnit.SECONDS);
        client.setReadTimeout(20, TimeUnit.SECONDS);
        gson = new Gson();
        final Message message = new Message();
        Request.Builder builder = new Request.Builder();
        FormEncodingBuilder formEncodingBuilder = new FormEncodingBuilder();
        RequestBody requestBody = formEncodingBuilder.add("name", username).add("pwd", userpwd).build();
        final Request request = builder.url(baseurl + "/ChangePwd").post(requestBody).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Looper.prepare();
                Toasty.error(context, "服务器出现问题了，请稍候再试", 2500).show();
                Looper.loop();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String result = response.body().string();
                if (result.equals("ok")) {
                    message.arg1 = 1;
                    handler.sendMessage(message);
                }
            }
        });
    }
}
