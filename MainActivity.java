package com.dcm.miniproject7;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import java.text.MessageFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button teamOne_btn, teamTwo_btn;
    private TextView teamOne_textView;
    private TextView teamTwo_textView;
    private int teamOne_int = 0, teamTwo_int = 0;
    private RelativeLayout relativeLayout;

    public static final String EXTRA_MESSAGE = "com.dcm.miniproject7.EXTRA_MESSAGE";
    public static final String EXTRA_INT1 = "com.dcm.miniproject7.EXTRA_INT1";
    public static final String EXTRA_INT2 = "com.dcm.miniproject7.EXTRA_INT2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        teamOne_btn = findViewById(R.id.one_btn);
        teamTwo_btn = findViewById(R.id.two_btn);
        teamOne_textView = findViewById(R.id.textView_teamOne);
        teamTwo_textView = findViewById(R.id.textView_teamTwo);

        teamOne_textView.setText(MessageFormat.format("Score: {0}", teamOne_int));
        teamTwo_textView.setText(MessageFormat.format("Score: {0}", teamTwo_int));

        teamOne_btn.setOnClickListener(this);
        teamTwo_btn.setOnClickListener(this);

        relativeLayout = findViewById(R.id.relativeLayout);// mini project 7

    }

    @Override
    public void onClick(View v) {
        if(teamOne_btn.equals(v)) {
            teamOne_int += 1;
            teamOne_textView.setText(MessageFormat.format("Score: {0}", teamOne_int));
            if(teamOne_int == 4) {
                gameOver();
            }
        }
        else if(teamTwo_btn.equals(v)) {
            teamTwo_int += 1;
            teamTwo_textView.setText(MessageFormat.format("Score {0}", teamTwo_int));
            if(teamTwo_int == 4) {
                gameOver();
            }
        }

    }

    private void gameOver() {
        String team_winning;
        if(teamOne_int == Math.max(teamOne_int,teamTwo_int)) {
            team_winning = "TEAM A";
        }
        else {
            team_winning = "TEAM B";
        }

        Intent intent = new Intent(this, WinningActivity.class);
        intent.putExtra(EXTRA_MESSAGE, team_winning);
        intent.putExtra(EXTRA_INT1, teamOne_int);
        intent.putExtra(EXTRA_INT2, teamTwo_int);
        startActivity(intent);

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
        Resources resources = getResources();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        else if (id == R.id.basketball) {
            Drawable drawable = ResourcesCompat.getDrawable(resources, R.drawable.basketball, null);
            relativeLayout.setBackground(drawable);

        }

        else if (id == R.id.baseball) {
            Drawable drawable = ResourcesCompat.getDrawable(resources, R.drawable.baseball, null);
            relativeLayout.setBackground(drawable);

        }

        else if (id == R.id.soccer) {
            Drawable drawable = ResourcesCompat.getDrawable(resources, R.drawable.soccer, null);
            relativeLayout.setBackground(drawable);

        }

        else if (id == R.id.football) {
            Drawable drawable = ResourcesCompat.getDrawable(resources, R.drawable.football, null);
            relativeLayout.setBackground(drawable);

        }

        else if (id == R.id.golf) {
            Drawable drawable = ResourcesCompat.getDrawable(resources, R.drawable.golf, null);
            relativeLayout.setBackground(drawable);

        }
        return super.onOptionsItemSelected(item);
    }
}