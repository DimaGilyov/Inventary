package com.atec.samuraiinventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {
    Button inventoryButton;
    Button movedButton;
    Button receiptButton;
    Button writeOffButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setTitle(R.string.menu);

        inventoryButton = findViewById(R.id.inventoryButton);
        movedButton = findViewById(R.id.movedButton);
        receiptButton = findViewById(R.id.receiptButton);
        writeOffButton = findViewById(R.id.writeOffButton);

        inventoryButton.setOnClickListener(this);
        movedButton.setOnClickListener(this);
        receiptButton.setOnClickListener(this);
        writeOffButton.setOnClickListener(this);
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