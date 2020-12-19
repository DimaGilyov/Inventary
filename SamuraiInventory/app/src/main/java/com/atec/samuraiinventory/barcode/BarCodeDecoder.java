package com.atec.samuraiinventory.barcode;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;

public class BarCodeDecoder extends androidx.appcompat.widget.AppCompatEditText {
    private static final int KEYCODE_UNKNOWN = 0;

    public BarCodeDecoder(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (keyCode == KEYCODE_UNKNOWN) {
            InputMethodManager mgr = (InputMethodManager)
                    getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            mgr.hideSoftInputFromWindow(this.getWindowToken(), 0);
        }

        return false;
    }
}