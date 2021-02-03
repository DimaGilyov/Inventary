package com.atec.samuraiinventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.atec.samuraiinventory.dao.HardwareDAO;
import com.atec.samuraiinventory.jira.JiraInsightService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {
    Button inventoryButton;
    Button movedButton;
    Button receiptButton;
    Button writeOffButton;
    TextView errorText;
    JSONObject settings;
    HardwareDAO hardwareDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setTitle(R.string.menu);

        inventoryButton = findViewById(R.id.inventoryButton);
        movedButton = findViewById(R.id.movedButton);
        receiptButton = findViewById(R.id.receiptButton);
        writeOffButton = findViewById(R.id.writeOffButton);
        errorText = findViewById(R.id.errorText);
        errorText.setVisibility(View.INVISIBLE);

        inventoryButton.setOnClickListener(this);
        movedButton.setOnClickListener(this);
        receiptButton.setOnClickListener(this);
        writeOffButton.setOnClickListener(this);


        if (settings == null) {
            InputStream settingsInputStream = getResources().openRawResource(R.raw.settings);
            BufferedReader settingsBufferedReader = new BufferedReader(new InputStreamReader(settingsInputStream));
            String line = "";
            StringBuilder settingsJson = new StringBuilder();
            while (true) {
                try {
                    if ((line = settingsBufferedReader.readLine()) == null) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                settingsJson.append(line);
            }

            try {
                settings = new JSONObject(settingsJson.toString());
                hardwareDAO = new HardwareDAO(settings);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                hardwareDAO.update();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    @Override
    public void onClick(View v) {
        int buttonId = v.getId();
        this.onClickMenuButton(this, buttonId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int buttonId = menuItem.getItemId();
        this.onClickMenuButton(this, buttonId);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.menuButton).setEnabled(false);
        return true;
    }

    private void onClickMenuButton(Context context, int buttonId) {
        if (buttonId == R.id.inventoryButton) {
            Intent intent = new Intent(context, InventoryActivity.class);
            startActivity(intent);
        } else if (buttonId == R.id.movedButton) {
            Intent intent = new Intent(context, MovedActivity.class);
            startActivity(intent);
        } else if (buttonId == R.id.receiptButton) {
            Intent intent = new Intent(context, ReceiptActivity.class);
            startActivity(intent);
        } else if (buttonId == R.id.writeOffButton) {
            Intent intent = new Intent(context, WriteOffActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(context, Login.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }
}