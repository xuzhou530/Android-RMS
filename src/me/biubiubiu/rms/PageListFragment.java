package me.biubiubiu.rms;

import android.util.*;
import android.widget.*;
import android.view.*;
import android.content.*;
import android.app.*;
import android.os.*;
import android.database.*;
import android.net.*;
import android.opengl.*;
import android.graphics.*;
import android.view.animation.*;

import java.util.*;
import org.json.*;

import android.support.v4.app.Fragment;
import me.biubiubiu.rms.ui.*;
import me.biubiubiu.rms.util.*;
import me.biubiubiu.rms.util.ReceiverManager.OnReceiveListener;

public class PageListFragment extends BaseFragment {

    private ListView mListView;
    public PageListAdapter mAdapter;
    protected PageList mPageList;
    public String mEndPoint;
    private int mItemLayout;
    private String mWhere;
    private Class mCustomDetail;

    public PageListFragment() {
    }

    public PageListFragment(String endPoint, int itemLayout) {
        mEndPoint = endPoint;
        mItemLayout = itemLayout;
    }

    public PageListFragment(String endPoint, int itemLayout, Class detailAct) {
        this(endPoint, itemLayout, null, detailAct);
    }

    public PageListFragment(String endPoint, int itemLayout, String where) {
        mEndPoint = endPoint;
        mItemLayout = itemLayout;
        mWhere = where;
    }

    public PageListFragment(String endPoint, int itemLayout, String where, Class detailAct) {
        mEndPoint = endPoint;
        mItemLayout = itemLayout;
        mWhere = where;
        mCustomDetail = detailAct;
    }

    public PageListAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mPageList = new PageList(getActivity(), mEndPoint);
        PageListAdapter adapter = new PageListAdapter(getActivity(), mItemLayout);
        mPageList.condition(mWhere, mCustomDetail);
        if (mAdapter == null) {
            mAdapter = new PageListAdapter(getActivity(), mItemLayout);
            mPageList.setAdapter(mAdapter);
        } else {
            mPageList.setAdapter(mAdapter);
        }
        mListView = mPageList.getListView();
        mPageList.load();

        mReceiverManager.registerReceiver(mEndPoint);
        mReceiverManager.setOnReceiveListener(new OnReceiveListener() {
            @Override
            public void onReceive(Intent intent) {
                mPageList.load();
            }
        });

        return mPageList;
    }

    public ListView getListView() {
        return mListView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.refresh, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.refresh) {
            if (mPageList != null) {
                mPageList.load();
            }
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}

