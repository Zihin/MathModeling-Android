package com.neu.coder.mathmodeling.MOOC;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.neu.coder.mathmodeling.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by jianghejie on 15/11/26.
 */
public class MOOCAdapter extends RecyclerView.Adapter<MOOCAdapter.ViewHolder> {
    public ArrayList<MoocItemData> datas = null;
    public MoocItemClickListener mItemClickListener;
    protected ImageLoader imageLoader = ImageLoader.getInstance();

    public MOOCAdapter(ArrayList<MoocItemData> datas) {
        this.datas = datas;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_mooc_item,viewGroup,false);
        ViewHolder vh = new ViewHolder(view, mItemClickListener);
        return vh;
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.mTextView.setText((CharSequence) datas.get(position).getTitle());
        imageLoader.displayImage(datas.get(position).getImageUrl(), viewHolder.mImageView);
    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mTextView;
        public ImageView mImageView;
        private MoocItemClickListener mListener;

        public ViewHolder(View view, MoocItemClickListener listener) {
            super(view);
            view.setOnClickListener(this);

            mListener = listener;
            mImageView = (ImageView) view.findViewById(R.id.item_image);
            mTextView = (TextView) view.findViewById(R.id.text);
        }

        @Override
        public void onClick(View v) {
            if(mListener != null){
                mListener.onItemClick(v,getPosition());
            }
        }
    }

    public void setOnItemClickListener(MoocItemClickListener listener){
        this.mItemClickListener = listener;
    }

    public interface MoocItemClickListener {
        public void onItemClick(View view, int postion);
    }
}
