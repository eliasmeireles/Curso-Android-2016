package com.cursoandroid.aula03;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements MyTextWhatcherListener {

    private DotView dotView;
    private Dots dotModel = new Dots();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText tb1 = (EditText) findViewById(R.id.text1);
        final EditText tb2 = (EditText) findViewById(R.id.text2);

        dotModel.setDotsChangeListener(new Dots.DotsChangeListener() {
            public void onDotsChange(Dots dots) {
                Dot d = dots.getLastDot();
                tb1.setText((null == d) ? "" : String.valueOf(d.getX()));
                tb2.setText((null == d) ? "" : String.valueOf(d.getY()));
                dotView.invalidate();
            }
        });

        dotView = (DotView) findViewById(R.id.dotView);
        dotView.setDots(dotModel);

        dotView.setOnTouchListener(new TrackingTouchListener(dotModel));

        dotView.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (KeyEvent.ACTION_UP != event.getAction()) {
                    int color = Color.BLUE;
                    switch (keyCode) {
                        case KeyEvent.KEYCODE_SPACE:
                            color = Color.MAGENTA;
                            break;
                        case KeyEvent.KEYCODE_ENTER:
                            color = Color.YELLOW;
                            break;
                        default:
                            ;
                    }
                    makeDot(dotModel, dotView, color);
                }
                return false;
            }
        });

        ((Button) findViewById(R.id.button1)).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                makeDot(dotModel, dotView, Color.RED);
            }
        });

        ((Button) findViewById(R.id.button2)).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                makeDot(dotModel, dotView, Color.GREEN);
            }
        });

        final EditText text3 = (EditText) findViewById(R.id.text3);
        text3.addTextChangedListener(new MyTextWhatcher(this));


        dotView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    makeDot(dotModel, dotView, Color.rgb(0, 128, 255));
                } else {
                    makeDot(dotModel, dotView, Color.rgb(255, 128, 0));
                }
            }
        });
    }

    private final Random rand = new Random();
    public static final int DOT_DIAMETER = 6;

    void makeDot(Dots dots, DotView view, int color) {
        int pad = (DOT_DIAMETER + 2) * 2;
        dots.addDot(DOT_DIAMETER + (rand.nextFloat() * (view.getWidth() - pad)),
                DOT_DIAMETER + (rand.nextFloat() * (view.getHeight() - pad)),
                color, DOT_DIAMETER);
    }

    private final int CLEAR_MENU_ID = 1;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, CLEAR_MENU_ID, Menu.NONE, getString(R.string.limpar));

        menu.clear();
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case CLEAR_MENU_ID:
                dotModel.clearDots();
                return true;
            default:
                ;
        }
        return false;
    }

    @Override
    public void desenharPontoCinza() {
        makeDot(dotModel, dotView, Color.GRAY);
    }
}
