package cn.org.orchid.whmx.Fragment;




import static java.lang.Thread.sleep;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import java.util.List;

import java.util.logging.LogRecord;

import cn.org.orchid.whmx.Adapter.TeabreakAdapter;
import cn.org.orchid.whmx.Dao.QiZheDao;
import cn.org.orchid.whmx.R;
import androidx.recyclerview.widget.GridLayoutManager;


public class TeaBreakFragment extends Fragment implements TeabreakAdapter.OnImageClickListener{
    RecyclerView recyclerView = null;
    GridLayout gridLayout = null;
    View view = null;
    private Handler handler;
    private List<String> imageNames;
    private TeabreakAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {

        return inflater.inflate(R.layout.teabreak_layout,container,false);
    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        this.view = view;
        //初始化适配器
        recyclerView = view.findViewById(R.id.teabreak_list);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        imageNames = new ArrayList<>();
        adapter = new TeabreakAdapter(imageNames,this,getContext());
        recyclerView.setAdapter(adapter);
        //获取右侧布局
        gridLayout = view.findViewById(R.id.teabreak_content);

        handler = new Handler(Looper.getMainLooper(), message -> {
            switch (message.what) {
                case 1:
                    JSONArray qizhe;
                    try {
                        qizhe = new JSONArray(message.obj.toString());
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    updateQizheList(qizhe);
                    break;
                case 2:
                    Log.v("Dao","get Qizhe list failed");
                    break;
                case 3:
                    JSONObject jsonObject;
                    try {jsonObject = new JSONObject(message.obj.toString());
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        updateTeaBreak(jsonObject);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    break;
            }
            return true;
        });
        QiZheDao qiZheDao = new QiZheDao(handler);
        qiZheDao.getAll();
    }


    public void updateQizheList(JSONArray qizhe) {

        imageNames = new ArrayList<>();
        for (int i = 0; i < qizhe.length(); i++) {
            try {
                imageNames.add(qizhe.getJSONObject(i).getString("code"));
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        Log.v("test_skip", "imageNames"+imageNames);

        if(recyclerView != null) {
            if(adapter != null) {
                adapter.updateData(imageNames);
            } else {
                adapter = new TeabreakAdapter(imageNames,this,getContext());
                recyclerView.setAdapter(adapter);
            }
        } else {
            Log.v("test_skip", "RecyclerView is null");
        }
    }

    @Override
    public void onImageClick(String imageName) {
        System.out.println("click"+imageName);
        QiZheDao qiZheDao = new QiZheDao(handler);
        qiZheDao.getTeaBreak(imageName);
    }

    public void updateTeaBreak(JSONObject data) throws JSONException {
        System.out.println(data.toString(4));
        setTextForTextView(
                view,
                R.id.topic1,
                data.getJSONObject("topic1").getString("topic")
        );
        setTextForTextView(
                view,
                R.id.pos5,
                data.getJSONObject("topic1").getString("positive")
        );
        setTextForTextView(
                view,
                R.id.neg5,
                data.getJSONObject("topic1").getString("negative")
        );
        setTextForTextView(
                view,
                R.id.topic2,
                data.getJSONObject("topic2").getString("topic")
        );
        setTextForTextView(
                view,
                R.id.pos6,
                data.getJSONObject("topic2").getString("positive")
        );
        setTextForTextView(
                view,
                R.id.neg6,
                data.getJSONObject("topic2").getString("negative")
        );
        setTextForTextView(
                view,
                R.id.tea1,
                data.getJSONObject("invitation").getJSONArray("tea").getString(0)
        );
        setTextForTextView(
                view,
                R.id.tea2,
                data.getJSONObject("invitation").getJSONArray("tea").getString(1)
        );
        setTextForTextView(
                view,
                R.id.pos1,
                data.getJSONObject("invitation").getJSONArray("positive").getString(0)
        );
        setTextForTextView(
                view,
                R.id.pos2,
                data.getJSONObject("invitation").getJSONArray("positive").getString(1)
        );
        setTextForTextView(
                view,
                R.id.pos3,
                data.getJSONObject("invitation").getJSONArray("positive").getString(2)
        );
        setTextForTextView(
                view,
                R.id.pos4,
                data.getJSONObject("invitation").getJSONArray("positive").getString(3)
        );
        setTextForTextView(
                view,
                R.id.neg1,
                data.getJSONObject("invitation").getJSONArray("negative").getString(0)
        );
        setTextForTextView(
                view,
                R.id.neg2,
                data.getJSONObject("invitation").getJSONArray("negative").getString(1)
        );
        setTextForTextView(
                view,
                R.id.neg3,
                data.getJSONObject("invitation").getJSONArray("negative").getString(2)
        );
        setTextForTextView(
                view,
                R.id.neg4,
                data.getJSONObject("invitation").getJSONArray("negative").getString(3)
        );
    }
    // 设置 TextView 文本的方法
    private void setTextForTextView(View view, int textViewId, String text) {
        TextView textView = view.findViewById(textViewId);
        if (textView != null) {
            textView.setText(text);
        }
    }

}
