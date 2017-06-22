package com.maikefengchao.relybox.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.chinark.apppickimagev3.ui.PhotoWallActivity;
import com.chinark.apppickimagev3.utils.SDCardImageLoader;
import com.chinark.apppickimagev3.utils.ScreenUtils;
import com.maikefengchao.relybox.Adapter.FaceCategroyAdapter;
import com.maikefengchao.relybox.OnOperationListener;
import com.maikefengchao.relybox.R;
import com.maikefengchao.relybox.SoftKeyboardStateHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 2016/7/9.
 */
public class FreeWriteKeyboard extends RelativeLayout implements
        SoftKeyboardStateHelper.SoftKeyboardStateListener {

    public interface OnToolBoxListener {
        void onShowFace();
    }

    public static final int LAYOUT_TYPE_HIDE = 0;
    public static final int LAYOUT_TYPE_FACE = 1;
    public static final int LAYOUT_TYPE_PICTURE = 2;

    /**
     * 最上层输入框
     */
    private EditText mEtMsg;
    private CheckBox mBtnFace;
    private CheckBox mBtnPicture;
    private Button mBtnSend;
    private RelativeLayout mRlTools;
    private HorizontalScrollView mHorizontalScroll;
    private LinearLayout mLinearShowPicture;
    private Button mBtnChoosePicture;
    /**
     * 表情
     */
    private ViewPager mPagerFaceCagetory;
    private RelativeLayout mRlFace;
    //当含有多个folder的时候
    private PagerSlidingTabStrip mFaceTabs;

    private int layoutType = LAYOUT_TYPE_HIDE;
    private FaceCategroyAdapter adapter;  //点击表情按钮时的适配器

    private List<String> mFaceData;

    private Context context;
    private OnOperationListener listener;
    private OnToolBoxListener mFaceListener;
    private SoftKeyboardStateHelper mKeyboardHelper;
    private SDCardImageLoader mImageLoader;

    public FreeWriteKeyboard(Context context) {
        super(context);
        init(context);
    }

    public FreeWriteKeyboard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FreeWriteKeyboard(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if (hasWindowFocus){
            mImageLoader = SDCardImageLoader.getImageLoader();
        }
    }

    private void init(Context context) {
        this.context = context;
        View root = View.inflate(context, R.layout.free_article_tool_box, null);
        this.addView(root);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initData();
        this.initWidget();
    }

    private void initData() {
        mKeyboardHelper = new SoftKeyboardStateHelper(((Activity) getContext())
                .getWindow().getDecorView());
        mKeyboardHelper.addSoftKeyboardStateListener(this);
    }

    private void initWidget() {
        mEtMsg = (EditText) findViewById(R.id.toolbox_et_message);
        mBtnSend = (Button) findViewById(R.id.toolbox_btn_send);
        mBtnFace = (CheckBox) findViewById(R.id.toolbox_btn_face);
        mBtnPicture = (CheckBox) findViewById(R.id.toolbox_btn_more);
        mBtnChoosePicture = (Button)findViewById(R.id.toolBox_btn_selectPicture);
        mRlFace = (RelativeLayout) findViewById(R.id.toolbox_layout_face);
        mRlTools = (RelativeLayout)findViewById(R.id.toolbox_rl_tools);
        mHorizontalScroll = (HorizontalScrollView)findViewById(R.id.toolBox_horizontalScroll);
        mLinearShowPicture = (LinearLayout) findViewById(R.id.toolBox_linear_showPicture);
        mPagerFaceCagetory = (ViewPager) findViewById(R.id.toolbox_pagers_face);
        mFaceTabs = (PagerSlidingTabStrip) findViewById(R.id.toolbox_tabs);
        adapter = new FaceCategroyAdapter(((FragmentActivity) getContext())
                .getSupportFragmentManager(), LAYOUT_TYPE_FACE);
        mBtnSend.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    String content = mEtMsg.getText().toString();
                    listener.send(content);
                    mEtMsg.setText(null);
                }
            }
        });
        // 点击表情按钮
        mBtnFace.setOnClickListener(getFunctionBtnListener(LAYOUT_TYPE_FACE));
        // 点击表情按钮旁边的加号
        mBtnPicture.setOnClickListener(getFunctionBtnListener(LAYOUT_TYPE_PICTURE));
        // 点击消息输入框
        mEtMsg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                hideLayout();
            }
        });
        mBtnChoosePicture.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PhotoWallActivity.class);
                intent.putExtra(PhotoWallActivity.EXTRA_LAUNCH_CLASS,getContext().getClass().getName());
                intent.putExtra(PhotoWallActivity.EXTRA_TYPE_CHOOSE_PICTURE,true);
                getContext().startActivity(intent);
            }
        });
    }

    /*************************
     * 内部方法 start
     ************************/

    private OnClickListener getFunctionBtnListener(final int which) {
        return new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShow() && which == layoutType) {
                    hideLayout();
                    showKeyboard(context);
                } else {
                    if (which == LAYOUT_TYPE_FACE){
                        mRlTools.setVisibility(GONE);
                        showLayout(which);
                    }
                    else if (which == LAYOUT_TYPE_PICTURE){
                        mRlFace.setVisibility(GONE);
                        showLayout(which);
                    }
                }
                mBtnFace.setChecked(layoutType == LAYOUT_TYPE_FACE);
                mBtnPicture.setChecked(layoutType == LAYOUT_TYPE_PICTURE);
            }
        };
    }

    @Override
    public void onSoftKeyboardOpened(int keyboardHeightInPx) {
        hideLayout();
    }

    @Override
    public void onSoftKeyboardClosed() {
    }

    /***************************** 内部方法 end ******************************/

    /**************************
     * 可选调用的方法 start
     **************************/

    public void setFaceData(List<String> faceData) {
        mFaceData = faceData;
        adapter.refresh(faceData);
        mPagerFaceCagetory.setAdapter(adapter);
        mFaceTabs.setViewPager(mPagerFaceCagetory);
        if (layoutType == LAYOUT_TYPE_PICTURE) {
            mFaceTabs.setVisibility(GONE);
        } else {

            //加1是表示第一个分类为默认的emoji表情分类，这个分类是固定不可更改的
            if (faceData.size() + 1 < 2) {
                mFaceTabs.setVisibility(GONE);
            } else {
                mFaceTabs.setVisibility(VISIBLE);
            }
        }
    }

    public EditText getEditTextBox() {
        return mEtMsg;
    }

    public void showLayout(int mode) {
        layoutType = mode;
        hideKeyboard(this.context);
        // 延迟一会，让键盘先隐藏再显示表情键盘，否则会有一瞬间表情键盘和软键盘同时显示
        postDelayed(new Runnable() {
            @Override
            public void run() {
                if (layoutType == LAYOUT_TYPE_FACE){
                    mRlFace.setVisibility(View.VISIBLE);
                    if (mFaceListener != null) {
                        mFaceListener.onShowFace();
                    }
                }
                else if (layoutType == LAYOUT_TYPE_PICTURE){
                    mRlTools.setVisibility(VISIBLE);
                }
            }
        }, 100);
    }


    public boolean isShow() {
        return mRlFace.getVisibility() == VISIBLE || mRlTools.getVisibility() == VISIBLE;
    }

    public void hideLayout() {
        mRlFace.setVisibility(View.GONE);
        mRlTools.setVisibility(View.GONE);
        if (mBtnFace.isChecked()) {
            mBtnFace.setChecked(false);
        }
        if (mBtnPicture.isChecked()) {
            mBtnPicture.setChecked(false);
        }
    }

    /**
     * 隐藏软键盘
     */
    public void hideKeyboard(Context context) {
        Activity activity = (Activity) context;
        if (activity != null) {
            InputMethodManager imm = (InputMethodManager) activity
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm.isActive() && activity.getCurrentFocus() != null) {
                imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
            }
            else {
                imm.hideSoftInputFromWindow(((Activity) context).getWindow().getDecorView().getWindowToken(), 0);
            }
        }
    }

    /**
     * 显示软键盘
     */
    public static void showKeyboard(Context context) {
        Activity activity = (Activity) context;
        if (activity != null) {
            InputMethodManager imm = (InputMethodManager) activity
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm.isActive() && activity.getCurrentFocus() != null) {
                imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
            }
            else {
                imm.hideSoftInputFromWindow(((Activity) context).getWindow().getDecorView().getWindowToken(), 0);
            }
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    public OnOperationListener getOnOperationListener() {
        return listener;
    }

    public void setOnOperationListener(OnOperationListener onOperationListener) {
        this.listener = onOperationListener;
        adapter.setOnOperationListener(onOperationListener);
    }

    public void setOnToolBoxListener(OnToolBoxListener mFaceListener) {
        this.mFaceListener = mFaceListener;
    }

    public void addUpdateImage(ArrayList<String> filePaths){
        for(String path:filePaths){
            ImageView imageView = new ImageView(getContext());
            imageView.setTag(path);
            mImageLoader.loadImage(3,path,imageView);
            MarginLayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            params.setMargins(0,0, ScreenUtils.dp2px(5),0);
            mLinearShowPicture.addView(imageView,params);
        }
        mBtnChoosePicture.setVisibility(GONE);
        mHorizontalScroll.setVisibility(VISIBLE);
    }
    /*********************** 可选调用的方法 end ******************************/
}