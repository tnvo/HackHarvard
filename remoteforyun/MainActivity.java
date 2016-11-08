package com.example.nvlad.remoteforyun;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity
{

    RelativeLayout layout_joystick;
    ImageView image_joystick, image_border;
    TextView textView1, textView2, textView3, textView4, textView5;

    JoyStickClass js;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = (TextView)findViewById(R.id.textView1);
        textView2 = (TextView)findViewById(R.id.textView2);
        textView3 = (TextView)findViewById(R.id.textView3);
        textView4 = (TextView)findViewById(R.id.textView4);
        textView5 = (TextView)findViewById(R.id.textView5);

        layout_joystick = (RelativeLayout)findViewById(R.id.layout_joystick);

        js = new JoyStickClass(getApplicationContext(), layout_joystick, R.drawable.tank12);
        js.setStickSize(150, 150);
        js.setLayoutSize(500, 500);
        js.setLayoutAlpha(150);
        js.setStickAlpha(100);
        js.setOffset(90);
        js.setMinimumDistance(50);

        layout_joystick.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View arg0, MotionEvent arg1) {
                js.drawStick(arg1);
                if(arg1.getAction() == MotionEvent.ACTION_DOWN
                        || arg1.getAction() == MotionEvent.ACTION_MOVE) {
                    textView1.setText("X : " + String.valueOf(js.getX()));
                    textView2.setText("Y : " + String.valueOf(js.getY()));
                    textView3.setText("Angle : " + String.valueOf(js.getAngle()));
                    textView4.setText("Distance : " + String.valueOf(js.getDistance()));

                    int direction = js.get8Direction();
                    if(direction == JoyStickClass.STICK_UP)
                    {
                        new Forward().execute("http://192.168.240.1/arduino/digital/13/500");
                        textView5.setText("Direction : Up");
                    } else if(direction == JoyStickClass.STICK_UPRIGHT)
                    {
                        new Forward().execute("http://192.168.240.1/arduino/digital/13/500");
                        textView5.setText("Direction : Up Right");
                    } else if(direction == JoyStickClass.STICK_RIGHT)
                    {
                        new Right().execute("http://192.168.240.1/arduino/digital/13/1");
                        textView5.setText("Direction : Right");
                    } else if(direction == JoyStickClass.STICK_DOWNRIGHT)
                    {
                        //new Forward().execute("http://192.168.240.1/arduino/digital/13/500");
                        textView5.setText("Direction : Down Right");
                    } else if(direction == JoyStickClass.STICK_DOWN)
                    {
                        new Back().execute("http://192.168.240.1/arduino/digital/13/1500");
                        textView5.setText("Direction : Down");
                    } else if(direction == JoyStickClass.STICK_DOWNLEFT)
                    {
                        //new Forward().execute("http://192.168.240.1/arduino/digital/13/500");
                        textView5.setText("Direction : Down Left");
                    } else if(direction == JoyStickClass.STICK_LEFT)
                    {
                        new Left().execute("http://192.168.240.1/arduino/digital/13/2");
                        textView5.setText("Direction : Left");
                    } else if(direction == JoyStickClass.STICK_UPLEFT)
                    {
                        //new Forward().execute("http://192.168.240.1/arduino/digital/13/500");
                        textView5.setText("Direction : Up Left");
                    } else if(direction == JoyStickClass.STICK_NONE)
                    {
                        new Stop().execute("http://192.168.240.1/arduino/digital/13/0");
                        textView5.setText("Direction : Center");
                    }
                } else if(arg1.getAction() == MotionEvent.ACTION_UP)
                {
                    textView1.setText("X :");
                    textView2.setText("Y :");
                    textView3.setText("Angle :");
                    textView4.setText("Distance :");
                    textView5.setText("Direction :");
                }
                return true;
            }
        });
    }





//    public void buttonForward(View v)
//    {
//        findViewById(R.id.buttonTop).setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                new Forward().execute("http://192.168.240.1/arduino/digital/13/500");
//            }
//        });
//    }
//    public void buttonBack(View v)
//    {
//        findViewById(R.id.buttonBack).setOnClickListener(new View.OnClickListener()
//        {
//            public void onClick(View v)
//            {
//                new Back().execute("http://192.168.240.1/arduino/digital/13/1500");
//            }
//        });
//    }
//    public void buttonStop(View v)
//    {
//        findViewById(R.id.buttonStop).setOnClickListener(new View.OnClickListener()
//        {
//            public void onClick(View v)
//            {
//                new Stop().execute("http://192.168.240.1/arduino/digital/13/0");
//            }
//        });
//    }
//    public void buttonLeft(View v)
//    {
//        findViewById(R.id.buttonLeft).setOnClickListener(new View.OnClickListener()
//        {
//            public void onClick(View v)
//            {
//                new Left().execute("http://192.168.240.1/arduino/digital/13/2");
//            }
//        });
//    }
//    public void buttonRight(View v)
//    {
//        findViewById(R.id.buttonRight).setOnClickListener(new View.OnClickListener()
//        {
//            public void onClick(View v)
//            {
//                new Right().execute("http://192.168.240.1/arduino/digital/13/1");
//            }
//        });
//    }


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

















