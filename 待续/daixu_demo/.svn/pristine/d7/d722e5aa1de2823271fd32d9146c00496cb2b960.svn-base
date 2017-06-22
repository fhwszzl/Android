package com.maikefengchao.daixu.UI.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.maikefengchao.daixu.R;
import com.maikefengchao.relybox.OnOperationListener;
import com.maikefengchao.relybox.bean.Emojicon;
import com.maikefengchao.relybox.bean.Faceicon;
import com.maikefengchao.relybox.widget.WriteArticleKeyboard;
import com.rockerhieu.emojicon.EmojiconEditText;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 2016/6/25.
 */
public class WriteArticleActivity extends BaseActivity {
    private EditText mEtTitle;
    private EmojiconEditText mEtContent;
    private WriteArticleKeyboard mKeyBoard;
    @Override
    protected void initView(Bundle saveInstanceState) {
        setContentView(R.layout.activity_write_article);
        mEtTitle = getViewById(R.id.writeArticle_et_title);
        mEtContent = getViewById(R.id.writeArticle_et_content);
        mKeyBoard = getViewById(R.id.writeArticle_box_tool);
        initMessageInputToolBox();
    }

    private void initMessageInputToolBox(){
        List<String> faceCagegory = new ArrayList<>();
        mKeyBoard.setOnOperationListener(new OnOperationListener() {
            @Override
            public void send(String content) {

            }

            @Override
            public void selectedFace(Faceicon content) {

            }

            @Override
            public void selectedEmoji(Emojicon content) {
                mEtContent.append(content.getValue());
            }

            @Override
            public void selectedBackSpace(Emojicon back) {
                //没有
            }

            @Override
            public void selectedFunction(int index) {
                //没有
            }
        });
        //如果有外部的表情包，创建并初始化
        File faceList = new File("");
        if (faceList.isDirectory()) {
            File[] faceFolderArray = faceList.listFiles();
            for (File folder : faceFolderArray) {
                if (!folder.isHidden()) {
                    faceCagegory.add(folder.getAbsolutePath());
                }
            }
        }
        mKeyBoard.setFaceData(faceCagegory);
    }

    @Override
    protected void setListener() {
        mEtContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mKeyBoard.hideLayout();
            }
        });
        mEtTitle.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    if (mKeyBoard.isShown()){
                        mKeyBoard.forbidUse();
                    }
                }
                else {
                    mKeyBoard.doUse();
                }
            }
        });
        mEtTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mKeyBoard.hideLayout();
            }
        });
    }

    @Override
    protected void processLogic(Bundle saveInstanceState) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mKeyBoard.isShow()) {
            mKeyBoard.hideLayout();
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int code = intent.getIntExtra("code", -1);
        if (code != 100){
            return;
        }
        ArrayList<String> paths = intent.getStringArrayListExtra("paths");
        mKeyBoard.setImagePicture(paths.get(0));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mKeyBoard = null;
    }
}
