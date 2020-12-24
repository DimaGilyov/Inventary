package com.atec.samuraiinventory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {
    public static final String NICK_NAME = "NICKNAME";
    private static final String IS_SAVE_NICK = "IS_SAVE_NICK";
    EditText inputLogin;
    EditText pinEditText;
    TextView errorMessage;
    CheckBox saveLoginCheckBox;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inputLogin = findViewById(R.id.inputLogin);
        pinEditText = findViewById(R.id.pinEditText);
        errorMessage = findViewById(R.id.errorText);
        saveLoginCheckBox = findViewById(R.id.saveLoginCheckBox);

        sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        String nickName = sharedPreferences.getString(NICK_NAME, "");
        inputLogin.setText(nickName);

        boolean isSave = sharedPreferences.getBoolean(IS_SAVE_NICK, false);
        saveLoginCheckBox.setChecked(isSave);

        Intent intent = new Intent(this, MainActivity.class);
        pinEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String login = inputLogin.getText().toString();
                String pin = pinEditText.getText().toString();
                sharedPreferences = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                if (saveLoginCheckBox.isChecked()) {
                    editor.putString(NICK_NAME, login);
                    editor.putBoolean(IS_SAVE_NICK, true);
                } else {
                    editor.putString(NICK_NAME, "");
                    editor.putBoolean(IS_SAVE_NICK, false);
                }
                editor.apply();
                if (!login.isEmpty() && pin.length() == 4) {
                    if (login.equals("Bro") && pin.equals("1234")) {
                        intent.putExtra(NICK_NAME, login);
                        startActivity(intent);
                    } else {
                        inputLogin.setBackground(ContextCompat.getDrawable(inputLogin.getContext(), R.drawable.edit_text_style_error));
                        pinEditText.setBackground(ContextCompat.getDrawable(pinEditText.getContext(), R.drawable.edit_text_style_error));
                        errorMessage.setVisibility(View.VISIBLE);
                    }
                } else {
                    inputLogin.setBackground(ContextCompat.getDrawable(inputLogin.getContext(), R.drawable.edit_text_style));
                    pinEditText.setBackground(ContextCompat.getDrawable(pinEditText.getContext(), R.drawable.edit_text_style));
                    errorMessage.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
}