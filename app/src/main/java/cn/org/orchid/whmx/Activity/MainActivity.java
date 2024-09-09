package cn.org.orchid.whmx.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;

import cn.org.orchid.whmx.Dao.PackageDao;
import cn.org.orchid.whmx.R;

public class MainActivity extends AppCompatActivity {
    Handler handler = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v("test_skip","main");
        handler = new Handler(Looper.getMainLooper(), message -> {
            switch (message.what) {
                case 1:
                    break;
                //获取最新版本
                case 2:
                    break;
            }
            return true;
        });
    }

    public void checkVersion(View view) throws PackageManager.NameNotFoundException {
        PackageDao packageDao = new PackageDao(this,handler);
        packageDao.getLastedVersion();
    }

    public void beginHelper(View view) {
        Intent intent = new Intent(MainActivity.this,PrimaryActivity.class);
        startActivity(intent);
    }
}