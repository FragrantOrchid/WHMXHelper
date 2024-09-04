package cn.org.orchid.whmx;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import cn.org.orchid.whmx.Adapter.PrimaryMenuAdapter;

public class PrimaryActivity extends AppCompatActivity {
    @Override
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.primary_layout);
        String[] values = new String[] {"茶歇","信息来源","相关信息"};

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

                break;
            case "信息来源":
                //TODO

                break;
            case "相关信息":
                //TODO

                break;
            default:
                break;
        }
    }

}
