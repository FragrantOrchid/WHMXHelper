package cn.org.orchid.whmx.Dao;

import android.os.Looper;
import android.os.Message;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import android.os.Handler;
import android.util.Log;

import cn.org.orchid.whmx.Fragment.TeaBreakFragment;

public class QiZheDao {
    private final Handler handler;
    public QiZheDao(Handler handler) {
        this.handler = handler;
    }
    public void getAll() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.v("test_skip","run dao");
                String API_URL = "https://whmx.orchid.org.cn/?s=App.Qizhe.Getall";
                JSONArray jsonArray = null;
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
                    jsonArray = jsonResponse.getJSONArray("data");
                    //得到信息，what=1
                    message.what = 1;
                    Log.v("test_skip","try to send" + jsonArray);
                    System.out.println("test");
                    System.out.println(jsonArray);
                    message.obj = jsonArray.toString();
                } catch (Exception e) {
                    message.what = 2;
                    e.printStackTrace();
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
    public void getTeaBreak(String code) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String API_URL = "https://whmx.orchid.org.cn/?s=App.Qizhe.GetTeaBreak&code="+code;
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
                    JSONObject jsonData = jsonResponse.getJSONObject("data");
                    String teaBreak = jsonData.getString("tea_break");
                    message.what = 3;
                    message.obj = teaBreak;
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
