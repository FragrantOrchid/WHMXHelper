package cn.org.orchid.whmx.Activity;

import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import cn.org.orchid.whmx.Adapter.PrimaryMenuAdapter;
import cn.org.orchid.whmx.Fragment.TeaBreakFragment;
import cn.org.orchid.whmx.R;

public class PrimaryActivity extends AppCompatActivity {
    @Override
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.primary_layout);
        String[] values = new String[] {"茶歇","相关链接"};

        PrimaryMenuAdapter adapter = new PrimaryMenuAdapter(this,values);
        ListView listView = findViewById(R.id.primary_menu);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                handleItemClick(selectedItem);

            }
        });
    }

    private void handleItemClick(String selectedItem) {
        switch (selectedItem) {
            case "茶歇":
                //TODO
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                TeaBreakFragment fragment = new TeaBreakFragment();
                fragmentTransaction.replace(R.id.primary_container,fragment).commit();
                Log.v("test_skip","activity end");
                break;
            case "相关链接":
                //TODO

                break;

            default:
                //TODO
                break;
        }
    }




}
