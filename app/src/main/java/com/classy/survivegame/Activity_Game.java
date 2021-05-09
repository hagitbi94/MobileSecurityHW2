package com.classy.survivegame;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Activity_Game extends AppCompatActivity {
    public static final String EXTRA_ID = "EXTRA_ID";
    public static final String EXTRA_STATE = "EXTRA_STATE";
    private ImageButton[] arrows;
    int currentLevel = 0;
    private boolean goodToGo = true;
    int[] steps = {1, 1, 1, 2, 2, 2, 3, 3, 3};


    /* access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        String id = getIntent().getStringExtra(EXTRA_ID);
        if (id.length() == this.steps.length) {
            int i = 0;
            while (true) {
                int[] iArr = this.steps;
                if (i >= iArr.length) {
                    break;
                }
                iArr[i] = Integer.parseInt(String.valueOf(id.charAt(i))) % 4;
                Toast.makeText(getApplicationContext(), "iArr[i] = " + iArr[i], Toast.LENGTH_SHORT).show();
                i++;
            }
        }
        findViews();
        initViews();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void arrowClicked(int direction) {
        if (this.goodToGo & (direction != this.steps[this.currentLevel])) {

            Toast.makeText(getApplicationContext(), "taken the condition now " + direction, Toast.LENGTH_SHORT).show();
            this.goodToGo = false;
        }
        int i = this.currentLevel + 1;
        this.currentLevel = i;
        if (i >= this.steps.length) {
            finishGame();
        }
    }

    private void finishGame() {
        String state = getIntent().getStringExtra(EXTRA_STATE);
        if (this.goodToGo) {
            Toast.makeText(this, "Survived in " + state, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "You Failed ", Toast.LENGTH_LONG).show();
        }
        finish();
    }

    private void initViews() {
        int index = 0;
        while (true) {

            ImageButton[] imageButtonArr = this.arrows;
            if (index < imageButtonArr.length) {
                int finalIndex = index;
                imageButtonArr[index].setOnClickListener(new View.OnClickListener() {
                    /* class com.classy.survivegame.Activity_Game.AnonymousClass1 */

                    public void onClick(View v) {
                        Activity_Game.this.arrowClicked(finalIndex);
                        Toast.makeText(getApplicationContext(), "index: " + finalIndex, Toast.LENGTH_SHORT).show();

                    }

                });

                index++;

            } else {
                return;
            }
        }
    }


    private void findViews() {
        this.arrows = new ImageButton[]{
                (ImageButton) findViewById(R.id.game_BTN_left),
                (ImageButton) findViewById(R.id.game_BTN_right),
                (ImageButton) findViewById(R.id.game_BTN_up),
                (ImageButton) findViewById(R.id.game_BTN_down)};
    }
}
