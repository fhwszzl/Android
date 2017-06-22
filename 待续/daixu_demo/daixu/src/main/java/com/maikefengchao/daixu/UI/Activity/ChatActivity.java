package com.maikefengchao.daixu.UI.Activity;

import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

import com.maikefengchao.daixu.R;
import com.maikefengchao.relybox.OnOperationListener;
import com.maikefengchao.relybox.bean.Emojicon;
import com.maikefengchao.relybox.bean.Faceicon;
import com.maikefengchao.relybox.widget.KJChatKeyboard;

import org.kymjs.kjframe.KJActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 2016/6/19.
 */
public class ChatActivity extends KJActivity {
    private KJChatKeyboard box;
    private ListView mRealListView;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_article);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        box = (KJChatKeyboard) findViewById(R.id.reply_msg_input_box);
        mRealListView = (ListView) findViewById(R.id.reply_listview);
        initMessageInputToolBox();
    }

    private void initMessageInputToolBox(){
        List<String> faceCagegory = new ArrayList<>();
        box.setOnOperationListener(new OnOperationListener() {
            @Override
            public void send(String content) {

            }

            @Override
            public void selectedFace(Faceicon content) {

            }

            @Override
            public void selectedEmoji(Emojicon content) {
                box.getEditTextBox().append(content.getValue());
            }

            @Override
            public void selectedBackSpace(Emojicon back) {

            }

            @Override
            public void selectedFunction(int index) {

            }
        });
        File faceList = new File("");
        if (faceList.isDirectory()) {
            File[] faceFolderArray = faceList.listFiles();
            for (File folder : faceFolderArray) {
                if (!folder.isHidden()) {
                    faceCagegory.add(folder.getAbsolutePath());
                }
            }
        }
        box.setFaceData(faceCagegory);
        mRealListView.setOnTouchListener(getOnTouchListener());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && box.isShow()) {
            box.hideLayout();
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    /**
     * 若软键盘或表情键盘弹起，点击上端空白处应该隐藏输入法键盘
     *
     * @return 会隐藏输入法键盘的触摸事件监听器
     */
    private View.OnTouchListener getOnTouchListener() {
        return new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                box.hideLayout();
                box.hideKeyboard(aty);
                return false;
            }
        };
    }

    /**
     * @return 聊天列表内存点击事件监听器
     */
    private OnChatItemClickListener getOnChatItemClickListener() {
        return new OnChatItemClickListener() {
            @Override
            public void onPhotoClick(int position) {
            }

            @Override
            public void onTextClick(int position) {
            }

            @Override
            public void onFaceClick(int position) {
            }
        };
    }

    /**
     * 聊天列表中对内容的点击事件监听
     */
    public interface OnChatItemClickListener {
        void onPhotoClick(int position);

        void onTextClick(int position);

        void onFaceClick(int position);
    }
}
