package cn.org.orchid.whmx.Dao;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class QiZheDao {
    public JSONArray getAll() {
        String API_URL = "http://whmx.orchid.org.cn/?s=App.Qizhe.Getall";
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
            JSONArray jsonArray = jsonResponse.getJSONArray("data");
            return jsonArray;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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
