package com.lgh.wine.ui.collect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.lgh.wine.R;
import com.lgh.wine.base.BaseActivity;
import com.lgh.wine.beans.CollectBean;
import com.lgh.wine.beans.SpoorBean;
import com.lgh.wine.contract.CollectContract;
import com.lgh.wine.model.CollectModel;
import com.lgh.wine.model.ProductModel;
import com.lgh.wine.presenter.CollectPresenter;
import com.lgh.wine.presenter.ProductPresenter;
import com.lgh.wine.ui.collect.adapter.CollectAdapter;
import com.lgh.wine.ui.product.adapter.SpoorAdapter;
import com.lgh.wine.utils.AccountUtil;
import com.lgh.wine.utils.BaseRecyclerAdapter;
import com.lgh.wine.utils.Constant;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static com.lgh.wine.utils.Constant.START_NUM;
import static com.lgh.wine.utils.Constant.USER_ID;

public class CollectListActivity extends BaseActivity implements CollectContract.View, OnRefreshLoadMoreListener {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ll_no_data)
    FrameLayout llNoData;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private Map<String, Object> params;
    private int pageNum = 0;
    private final static int size = 10;
    private int loadType;
    private CollectAdapter collectAdapter;

    private CollectPresenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_collect_list;
    }


    @Override
    protected void initUI() {
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshLoadMoreListener(this);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        collectAdapter = new CollectAdapter(mContext);
        recyclerView.setAdapter(collectAdapter);
        collectAdapter.setOnItemViewClickListener(new BaseRecyclerAdapter.OnItemViewClickListener() {
            @Override
            public void onViewClick(View view, int position) {
                CollectBean info = collectAdapter.getItem(position);
            }
        });
        collectAdapter.setListener(new CollectAdapter.OnOtherListener() {
            @Override
            public void onCartClick(CollectBean bean) {
                showError(bean.getGoods_price());
            }
        });
    }

    @Override
    protected void initData() {
        params = new HashMap<>();
        params.put(USER_ID, AccountUtil.getUserId());
        params.put(START_NUM, pageNum * size);

        presenter = new CollectPresenter(this, CollectModel.newInstance());
        addPresenter(presenter);
        refreshLayout.autoRefresh();
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        TextView tv_title = toolbar.findViewById(R.id.toolbar_title);
        tv_title.setText("我的收藏");

        TextView tv_save = toolbar.findViewById(R.id.toolbar_other);
        tv_save.setText("编辑");
        tv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public void dealAddCollectResult() {

    }

    @Override
    public void showCollectList(List<CollectBean> beans) {
        if (loadType == Constant.LOAD_TYPE.REFRESH) {
            collectAdapter.loadData(beans);
            pageNum = 1;
            if (beans != null && beans.size() > 0) {
                refreshLayout.setEnableLoadMore(true);
            }
        } else {
            collectAdapter.insertData(collectAdapter.getItemCount(), beans);
            ++pageNum;
        }
    }

    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {
        loadType = Constant.LOAD_TYPE.LOAD_MORE;
        pageNum += 1;
        getData();
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        loadType = Constant.LOAD_TYPE.REFRESH;
        pageNum = 0;
        getData();
    }

    private void getData() {
        params.put(START_NUM, pageNum * size);
        presenter.getCollect(params);
    }

    @Override
    public void hideProgress() {
        super.hideProgress();
        if (loadType == Constant.LOAD_TYPE.REFRESH) {
            refreshLayout.finishRefresh();
        } else {
            refreshLayout.finishLoadMore();
        }
        int itemCount = collectAdapter.getItemCount();
        llNoData.setVisibility(itemCount > 0 ? View.GONE : View.VISIBLE);
    }

    @Override
    public void dealDeleteCollect() {

    }
}
