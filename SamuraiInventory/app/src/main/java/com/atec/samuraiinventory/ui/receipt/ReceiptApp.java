package com.atec.samuraiinventory.ui.receipt;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.atec.samuraiinventory.R;
import com.atec.samuraiinventory.barcode.BarCodeDecoder;
import com.atec.samuraiinventory.jira.Jira;

import java.util.Objects;

public class ReceiptApp extends Fragment {
    private BarCodeDecoder barCodeDecoder;
    private TextView barCodeResult;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_receipt, container, false);
        barCodeDecoder = view.findViewById(R.id.barCode);
        barCodeResult = view.findViewById(R.id.barCodeResult);
        barCodeDecoder.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                String barCode = Objects.requireNonNull(barCodeDecoder.getText()).toString();
                barCodeResult.setText(barCode);
                //TODO: Реализовать логику
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Objects.requireNonNull(barCodeDecoder.getText()).clear();
            }
        });

        Thread thread = new Thread(() -> {
            Jira.login("xxx", "xxx");
            Jira.getJiraObjects();
        });

        thread.start();
        return view;
    }
}
