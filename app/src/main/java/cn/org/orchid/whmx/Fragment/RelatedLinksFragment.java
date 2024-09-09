package cn.org.orchid.whmx.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import cn.org.orchid.whmx.R;

public class RelatedLinksFragment extends Fragment {
    View view = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {

        return inflater.inflate(R.layout.relatedlinks_layout,container,false);

    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        this.view = view;
        //官网链接
        view.findViewById(R.id.official_website).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUrl("https://wh.cipaishe.com/");
            }
        });
        //wiki链接
        view.findViewById(R.id.wiki_website).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUrl("https://wiki.biligame.com/whmx/%E9%A6%96%E9%A1%B5");
            }
        });
        //github链接
        view.findViewById(R.id.github_website).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUrl("https://github.com/FragrantOrchid/WHMXHelper");
            }
        });
        //api链接
        view.findViewById(R.id.api_website).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUrl("http://whmx.orchid.org.cn/docs.php");
            }
        });
        //爱发电链接
        view.findViewById(R.id.ifdian_website).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUrl("https://ifdian.net/a/cuteorchid");
            }
        });
    }
    private void openUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

}
