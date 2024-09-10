package cn.org.orchid.whmx.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
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
                //获取最新版本
                case 1:
                    Log.v("checkversion","get last version");
                    //TODO 这段逻辑梳理一下，包括PackageDao一起
                    try {
                        if(new PackageDao(this,handler).getLocalVersion() < Integer.parseInt(message.obj.toString())){
                            new PackageDao(this,handler).getUpdate();
                        }
                    } catch (PackageManager.NameNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 3:
                    Log.v("checkversion","get laast url");
                    openUrl(message.obj.toString());
                    break;
            }
            return true;
        });
    }

    public void checkVersion(View view) throws PackageManager.NameNotFoundException {
        Log.v("checkversion","begin check");
        PackageDao packageDao = new PackageDao(this,handler);
        packageDao.getLastedVersion();
    }

    public void beginHelper(View view) {
        Intent intent = new Intent(MainActivity.this,PrimaryActivity.class);
        startActivity(intent);
    }
    private void openUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}