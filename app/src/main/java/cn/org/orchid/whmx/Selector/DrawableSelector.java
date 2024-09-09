package cn.org.orchid.whmx.Selector;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import cn.org.orchid.whmx.R;

public class DrawableSelector {
    public static Bitmap getThumbnail(Context context, String code) throws IOException {
        AssetManager assetManager = context.getAssets();
        Bitmap bitmap = null;
        try {
            InputStream inputStream = assetManager.open("thumbnail/"+code+".png");
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  bitmap;
    }


}
