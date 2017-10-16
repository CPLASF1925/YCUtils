package com.ns.yc.ycutils;

import android.content.Context;
import android.os.Handler;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ns.yc.ycutilslib.blurView.RealTimeBlurView;
import com.ns.yc.ycutilslib.loadingDialog.ViewLoading;

/**
 * ================================================
 * 作    者：杨充
 * 版    本：1.0
 * 创建日期：2017/5/22
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class KnowledgeCustomFragment extends BaseFragment implements View.OnClickListener {

    private MainActivity activity;
    private TextView tv1;
    private ViewLoading mLoading;
    private TextView tv2;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }


    @Override
    public int getContentView() {
        return R.layout.fragment_custom;
    }

    @Override
    public void initView() {
        tv1 = (TextView) activity.findViewById(R.id.tv_1);
        tv2 = (TextView) activity.findViewById(R.id.tv_2);
    }

    @Override
    public void initListener() {
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_1:
                startShowLoading();
                break;
            case R.id.tv_2:
                startShowDialog();
                break;
        }
    }

    private void startShowLoading() {
        // 添加Loading
        mLoading = new ViewLoading(activity , R.style.Loading) {
            @Override
            public void loadCancel() {

            }
        };
        if (!mLoading.isShowing()) {
            mLoading.show();
        }

        // 2秒后关闭
        new Handler().postDelayed(new Runnable() {
            public void run() {
                if (mLoading != null && mLoading.isShowing()) {
                    mLoading.dismiss();
                }
            }
        }, 2000);
    }


    private void startShowDialog() {
        View popMenuView = activity.getLayoutInflater().inflate(R.layout.dialog, null);
        RealTimeBlurView blur_view = (RealTimeBlurView) popMenuView.findViewById(R.id.blur_view);
        float v = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15, activity.getResources().getDisplayMetrics());
        blur_view.setBlurRadius(v);
        final PopupWindow popMenu = new PopupWindow(popMenuView, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT, true);
        popMenu.setClippingEnabled(false);
        popMenu.setFocusable(true);         //点击其他地方关闭
        popMenu.setAnimationStyle(R.style.main_menu_animstyle);
        popMenu.showAtLocation(popMenuView, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }


}
