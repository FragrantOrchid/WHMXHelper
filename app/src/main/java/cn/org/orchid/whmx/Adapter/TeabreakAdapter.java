package cn.org.orchid.whmx.Adapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.List;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import cn.org.orchid.whmx.R;
import cn.org.orchid.whmx.Selector.DrawableSelector;

public class TeabreakAdapter extends RecyclerView.Adapter<TeabreakAdapter.ImageViewHolder> {

    private final List<String> imageNames;
    private final OnImageClickListener listener;
    private final Context context;

    public TeabreakAdapter(List<String> imageNames, OnImageClickListener listener,Context context) {
        this.imageNames = imageNames;
        this.listener = listener;
        this.context = context;
        Log.v("test_skip", "adapter get");
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_image, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        String imageName = imageNames.get(position);
        //获取缩略图文件路径并加入列表
        try {
            Glide.with(holder.itemView.getContext())
                    .load(DrawableSelector.getThumbnail(context,imageName))
                    //.fitCenter()
                    .apply(new RequestOptions().fitCenter())
                    .into(holder.imageView);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        holder.imageView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onImageClick(imageName);
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageNames.size();
    }

    public interface OnImageClickListener {
        void onImageClick(String imageName);
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageView;
        ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);

            //TODO
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateData(List<String> newImageNames) {
        this.imageNames.clear();
        this.imageNames.addAll(newImageNames);
        notifyDataSetChanged();
    }
}
