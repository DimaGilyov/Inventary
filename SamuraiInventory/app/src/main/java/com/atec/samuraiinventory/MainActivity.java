package com.atec.samuraiinventory;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.TextView;

import com.atec.samuraiinventory.barcode.BarCodeDecoder;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    BarCodeDecoder barCodeDecoder;
    TextView barCodeResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        barCodeDecoder = findViewById(R.id.barCode);
        barCodeResult = findViewById(R.id.barCodeResult);
        barCodeDecoder.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                String barCode = barCodeDecoder.getText().toString();
                barCodeResult.setText(barCode);
                Log.d("MY", barCode);
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
    }
}