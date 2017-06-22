package com.maikefengchao.daixu.UI.Fragment;

import android.os.Bundle;
import android.view.View;

import com.lhh.ptrrv.library.PullToRefreshRecyclerView;
import com.maikefengchao.daixu.R;

/**
 * Created by ALUEX on 2016/11/25.
 */

public class HomepageChildGsxFragment extends BaseRecyclerFragment{
    public static final String FRAGMENT_TAG = "HomepageChildGsxFragment";

    @Override
    protected void initCommonView(Bundle savedInstanceState) {
        setContentView(R.layout.childfragment_homepage_gsx);
    }

    @Override
    protected PullToRefreshRecyclerView initRecyclerView() {
        return null;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void processRecyclerLogic() {

    }

    @Override
    protected void firstRecyclerLoadData() {

    }

    @Override
    protected void refreshData() {

    }

    @Override
    protected void loadMoreData() {

    }


    @Override
    public void emptyViewReloadData() {

    }
}
