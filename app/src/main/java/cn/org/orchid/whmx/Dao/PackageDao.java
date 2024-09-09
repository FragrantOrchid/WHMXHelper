package cn.org.orchid.whmx.Dao;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.HandlerThread;

public class PackageDao {
    private Context context;
    private Handler handler;
    public PackageDao(Context context,Handler handler) {
        this.context = context;
        this.handler = handler;
    }
    public String getPackageName() {
        return context.getPackageName();
    }
    public int getLastedVersion() {
        //TODO
        return 0;
    }
    public int getLocalVersion() throws PackageManager.NameNotFoundException {
        PackageInfo packageInfo = context.getPackageManager().getPackageInfo(getPackageName(),0);
        return packageInfo.versionCode;
    }
    public void getUpdate() {
        //TODO
    }
}
