package br.com.zynger.simpleabsample;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import br.com.zynger.simpleab.ABTestVariant;
import br.com.zynger.simpleab.SimpleAB;
import br.com.zynger.simpleabsample.ab.button.ButtonBackgroundTest;
import br.com.zynger.simpleabsample.ab.button.ButtonTestVariant;
import br.com.zynger.simpleabsample.ab.button.WeightedTestDrawer;
import br.com.zynger.simpleabsample.ab.textview.TextViewBackgroundTest;


public class MainActivity extends ActionBarActivity {

    private TextView mTextView;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.main_text);
        mButton = (Button) findViewById(R.id.main_button);

        SimpleAB.with(this).perform(getTextViewTest()).now();
        SimpleAB.with(this).perform(getButtonTest()).withDrawer(new WeightedTestDrawer()).now();
    }

    private ButtonBackgroundTest getButtonTest() {
        return new ButtonBackgroundTest(
                new ButtonTestVariant() {

                    @Override
                    public float getWeight() {
                        return 20;
                    }

                    @Override
                    public void perform() {
                        mButton.setText("This is the less weighted variant");
                    }

                    @Override
                    public String getId() {
                        return "Less-weight";
                    }
                },
                new ButtonTestVariant() {
                    @Override
                    public float getWeight() {
                        return 80;
                    }

                    @Override
                    public void perform() {
                        mButton.setText("This is the MOST weighted variant");
                    }

                    @Override
                    public String getId() {
                        return "More weight";
                    }
                });
    }

    private TextViewBackgroundTest getTextViewTest() {
        return new TextViewBackgroundTest(new ABTestVariant() {
            @Override
            public void perform() {
                int color = getResources().getColor(android.R.color.holo_red_dark);
                mTextView.setBackgroundColor(color);
            }

            @Override
            public String getId() {
                return "A";
            }
        }, new ABTestVariant() {
            @Override
            public void perform() {
                int color = getResources().getColor(android.R.color.holo_green_dark);
                mTextView.setBackgroundColor(color);
            }

            @Override
            public String getId() {
                return "B";
            }
        }, new ABTestVariant() {
            @Override
            public void perform() {
                int color = getResources().getColor(android.R.color.holo_blue_bright);
                mTextView.setBackgroundColor(color);
            }

            @Override
            public String getId() {
                return "C";
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
