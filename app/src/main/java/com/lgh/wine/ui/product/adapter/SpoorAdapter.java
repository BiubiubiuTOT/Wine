package com.lgh.wine.ui.product.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lgh.wine.R;
import com.lgh.wine.beans.ProductBean;
import com.lgh.wine.beans.SpoorBean;
import com.lgh.wine.utils.BaseRecyclerAdapter;
import com.lgh.wine.utils.Constant;
import com.lgh.wine.utils.GlideHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by niujingtong on 2018/7/16.
 * 模块：
 */
public class SpoorAdapter extends BaseRecyclerAdapter<SpoorBean, SpoorAdapter.ViewHolder> {

    public SpoorAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_spoor, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        holder.tvName.setText(getItem(position).getGoods_name());
        holder.tvPrice.setText("￥" + getItem(position).getGoods_price());
        GlideHelper.loadImage(mContext, holder.ivPic, Constant.IMG_IP + getItem(position).getGoods_icon());
    }

    @Override
    protected boolean useItemAnimation() {
        return true;
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_name)
        TextView tvName;

        @BindView(R.id.tv_price)
        TextView tvPrice;

        @BindView(R.id.iv_pic)
        ImageView ivPic;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
