package cn.org.orchid.whmx.Dao;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PackageDao {
    private final Context context;
    private final Handler handler;
    public PackageDao(Context context,Handler handler) {
        this.context = context;
        this.handler = handler;
    }
    public String getPackageName() {
        return context.getPackageName();
    }
    public void getLastedVersion() {
        Log.v("checkversion","begin lastversion");
        new Thread(new Runnable() {
            @Override
            public void run() {
                String API_URL = "https://whmx.orchid.org.cn/?s=App.Package.GetLastedVersion";
                Message message = Message.obtain();
                try {
                    // 创建 URL 对象
                    URL url = new URL(API_URL);
                    // 打开连接
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    // 读取响应
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();
                    // 关闭连接
                    connection.disconnect();
                    // 解析 JSON 数据
                    JSONObject jsonResponse = new JSONObject(response.toString());
                    message.what = 1;
                    message.obj = jsonResponse.getString("data");
                } catch (Exception e) {
                    e.printStackTrace();
                    message.what = 2;
                }
                //准备Looper
                Looper.prepare();
                //传入消息
                new Handler().post(() -> {
                    handler.sendMessage(message);
                });
                //进入消息循环
                Looper.loop();
            }
        }).start();
    }
    public int getLocalVersion() throws PackageManager.NameNotFoundException {
        PackageInfo packageInfo = context.getPackageManager().getPackageInfo(getPackageName(),0);
        return packageInfo.versionCode;
    }
    public void getUpdate() {
        Log.v("checkversion","begin uodate");
        new Thread(new Runnable() {
            @Override
            public void run() {
                String API_URL = "https://whmx.orchid.org.cn/?s=App.Package.GetLastedUrl";
                Message message = Message.obtain();
                try {
                    // 创建 URL 对象
                    URL url = new URL(API_URL);
                    // 打开连接
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    // 读取响应
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();
                    // 关闭连接
                    connection.disconnect();
                    // 解析 JSON 数据
                    JSONObject jsonResponse = new JSONObject(response.toString());
                    message.what = 3;
                    message.obj = jsonResponse.getString("data");
                } catch (Exception e) {
                    e.printStackTrace();
                    message.what = 4;
                }
                //准备Looper
                Looper.prepare();
                //传入消息
                new Handler().post(() -> {
                    handler.sendMessage(message);
                });
                //进入消息循环
                Looper.loop();
            }
        }).start();
    }
}
