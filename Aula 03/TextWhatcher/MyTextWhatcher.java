package com.cursoandroid.aula03;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by Agripino on 09/04/2016.
 */
public class MyTextWhatcher implements TextWatcher {

    private MyTextWhatcherListener callback;
    private int textSize;

    public MyTextWhatcher(MyTextWhatcherListener callback){
        this.callback = callback;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (textSize < s.length()) {
            if (s.length() > 0) {
                if (Character.isDigit(s.charAt(s.length() - 1))) {
                    callback.desenharPontoCinza();
                }
            }
        }
        textSize = s.length();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}

interface MyTextWhatcherListener{
    void desenharPontoCinza();
}
