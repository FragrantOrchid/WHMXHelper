package cn.org.orchid.whmx.Selector;

import java.io.File;

import cn.org.orchid.whmx.R;

public class DrawableSelector {
    public static File getThumbnail(String code) {
        return new File("res/drawable/thumbnail/"+code+".png");
    }
}
