package com.chinark.apppickimagev3.Dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.chinark.apppickimagev3.R;

/**
 * Created by PC on 2016/6/23.
 */
public class ChoosePictureFragment extends DialogFragment {
    public static final String TAG_NAME = "ChoosePicture";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        ChoosePictureDialog dialog = new ChoosePictureDialog(getActivity(), R.style.showDialog);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.getDecorView().setPadding(0, 0, 0, 0); //消除边距
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; //设置宽度充满屏幕
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        return dialog;
    }
}
