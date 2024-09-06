package cn.org.orchid.whmx.Dao;

import android.os.Message;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import android.os.Handler;

import cn.org.orchid.whmx.Fragment.TeaBreakFragment;

public class QiZheDao {
    private Handler handler;
    public QiZheDao(Handler handler) {
        this.handler = handler;
    }
    public void getAll() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String API_URL = "http://whmx.orchid.org.cn/?s=App.Qizhe.Getall";
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
                    message.obj = jsonArray.toString();
                } catch (Exception e) {
                    message.what = 2;
                    e.printStackTrace();
                }


            }
        }).start();
    }
    public JSONObject getTeaBreak(int id) {
        String API_URL = "http://whmx.orchid.org.cn/?s=App.Qizhe.GetTeaBreak&id="+id;
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
            JSONObject teaBreakJson = new JSONObject(teaBreak);
            return teaBreakJson;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
