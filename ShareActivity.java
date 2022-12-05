package com.dcm.miniproject7;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class ShareActivity extends AppCompatActivity {

    public static final int SOLICIT_CALL_PHONE = 1;
    public static final int SOLICIT_MSG_PHONE = 2;

    private EditText getPhoneNum_editText;
    private String teamWinning;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharing);

        getPhoneNum_editText = findViewById(R.id.getPhoneNum_editText);
        Button makeCall_btn = findViewById(R.id.makeCall_btn);
        Button sendMsg_btn = findViewById(R.id.sendMsg_btn);
        Button search_btn = findViewById(R.id.search_btn);

        Intent intentShare = getIntent();
        score = intentShare.getIntExtra(WinningActivity.EXTRA_SCORE, 0);
        teamWinning = intentShare.getStringExtra(WinningActivity.EXTRA_WINNER);

        makeCall_btn.setOnClickListener(v -> callFriend());

        sendMsg_btn.setOnClickListener(v -> msgFriend());

        search_btn.setOnClickListener(v -> searchFriend());

    }

    private void callFriend() {
        String numCall = "tel:" + getPhoneNum_editText.getText().toString();
        Intent intentCallFriend = new Intent(Intent.ACTION_CALL);
        intentCallFriend.setData(Uri.parse(numCall));

        intentCallFriend.resolveActivity(getPackageManager());
        {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.CALL_PHONE}, SOLICIT_CALL_PHONE);
            } else {
                startActivity(intentCallFriend);
            }
        }
    }

    private void msgFriend() {

        String numSend = "sms:" + getPhoneNum_editText.getText().toString();

        String msg = teamWinning + " succeeded at ScoreCount by: " + score;

        Intent intentMsgFriend = new Intent(Intent.ACTION_SENDTO, Uri.parse(numSend));
        intentMsgFriend.putExtra("sms_body", msg);
        startActivity(intentMsgFriend);

        intentMsgFriend.resolveActivity(getPackageManager());
        {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.CALL_PHONE}, SOLICIT_MSG_PHONE);
            } else {
                startActivity(intentMsgFriend);
            }
        }
    }

    private void searchFriend() {

        Uri searchFriend = Uri.parse("geo:0,0?q=basketball near me");

        Intent intentSearchFriend = new Intent(Intent.ACTION_VIEW, searchFriend);
        startActivity(intentSearchFriend);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(ShareActivity.this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}