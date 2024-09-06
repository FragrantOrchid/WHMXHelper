package cn.org.orchid.whmx.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import cn.org.orchid.whmx.Dao.PackageDao;
import cn.org.orchid.whmx.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void checkVersion(View view) throws PackageManager.NameNotFoundException {
        PackageDao packageDao = new PackageDao(this);
        if(packageDao.getLocalVersion() < packageDao.getLastedVersion()){
            packageDao.getUpdate();
        }
    }

    public void beginHelper(View view) {
        Intent intent = new Intent(MainActivity.this,PrimaryActivity.class);
        startActivity(intent);
    }
}