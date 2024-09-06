package cn.org.orchid.whmx.Fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import java.util.List;

import java.util.logging.LogRecord;

import cn.org.orchid.whmx.Adapter.TeabreakAdapter;
import cn.org.orchid.whmx.Dao.QiZheDao;
import cn.org.orchid.whmx.R;
import androidx.recyclerview.widget.GridLayoutManager;


public class TeaBreakFragment extends Fragment implements TeabreakAdapter.OnImageClickListener{
    RecyclerView recyclerView = null;
    private Handler handler;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        Log.v("test_skip","onCreate");
        return inflater.inflate(R.layout.teabreak_layout,container,false);
    }
    @Override
    public void onViewCreated(View view,Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        this.recyclerView = view.findViewById(R.id.teabreak_list);
        this.handler = new Handler(Looper.getMainLooper(), message -> {
            switch (message.what) {
                case 1:
                    Log.v("test_skip","get message 1");
                    JSONArray qizhe;
                    Log.v("test_skip","message type" + message.toString());
                    Log.v("test_skip","get message" + message.obj.toString());
                    try {
                        qizhe = new JSONArray(message.obj.toString());
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    Log.v("test_skip","get num = "+qizhe.length());
                    updateQizheList(qizhe);

                    break;
                case 2:

                    break;
                default:

                    break;
            }
            return true;
        });
        QiZheDao qiZheDao = new QiZheDao(handler);
        qiZheDao.getAll();
    }


    public void updateQizheList(JSONArray qizhe) {
        List<String> imageNames = new ArrayList<>();
        for (int i = 0; i < qizhe.length(); i++) {
            try {
                imageNames.add(qizhe.getJSONObject(i).getString("code"));
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        // 设置 GridLayoutManager，每行4个列
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 4);
        Log.v("test_skip", "imageNames"+imageNames);
        TeabreakAdapter adapter = new TeabreakAdapter(imageNames, this);
        Log.v("test_skip", String.valueOf((recyclerView != null)));
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onImageClick(String imageName) {
        //TODO 点击时的相应
    }
}
