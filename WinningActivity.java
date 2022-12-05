package com.dcm.miniproject7;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import java.text.MessageFormat;

public class WinningActivity extends AppCompatActivity {

    private String TeamWinning;
    private int spread_Score;
    private Intent activityShare;
    private RelativeLayout relativeLayout_WinningActivity;

    public static final String EXTRA_WINNER = "com.dcm.miniproject7.EXTRA_WINNER";
    public static final String EXTRA_SCORE = "com.dcm.miniproject7.EXTRA_SCORE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winning);

        TextView txt_winner = findViewById(R.id.txt_winner);
        Button startAct_btn = findViewById(R.id.shareAct_btn);
        relativeLayout_WinningActivity = findViewById(R.id.relativeLayout_WinningActivity);
        activityShare = new Intent(this, ShareActivity.class);

        startAct_btn.setOnClickListener(v -> {
            activityShare.putExtra(EXTRA_WINNER, TeamWinning);
            activityShare.putExtra(EXTRA_SCORE, spread_Score);

            startActivity(activityShare);
        });

        Intent intent = getIntent();
        int int_teamOne = intent.getIntExtra(MainActivity.EXTRA_INT1, 0);
        int int_teamTwo = intent.getIntExtra(MainActivity.EXTRA_INT2, 0);
        TeamWinning = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        spread_Score = (Math.max(int_teamOne, int_teamTwo) - Math.min(int_teamOne, int_teamTwo));

        txt_winner.setText(MessageFormat.format("{0} succeeded by: {1}", TeamWinning, spread_Score));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.winning_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Resources resources = getResources();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(WinningActivity.this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        else if (id == R.id.gold_medal) {
            Drawable drawable = ResourcesCompat.getDrawable(resources, R.drawable.goldmedal, null);
            relativeLayout_WinningActivity.setBackground(drawable);

        }

        else if (id == R.id.trophy) {
            Drawable drawable = ResourcesCompat.getDrawable(resources, R.drawable.trophy, null);
            relativeLayout_WinningActivity.setBackground(drawable);

        }

        else if (id == R.id.thumbs_up) {
            Drawable drawable = ResourcesCompat.getDrawable(resources, R.drawable.up, null);
            relativeLayout_WinningActivity.setBackground(drawable);

        }
        return super.onOptionsItemSelected(item);
    }
}