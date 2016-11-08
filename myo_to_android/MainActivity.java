package com.example.nvlad.myo_to_android;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.thalmic.myo.AbstractDeviceListener;
import com.thalmic.myo.DeviceListener;
import com.thalmic.myo.Hub;
import com.thalmic.myo.Myo;
import com.thalmic.myo.Pose;
import com.thalmic.myo.scanner.ScanActivity;

public class MainActivity extends AppCompatActivity
{
    private static Context context;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivity.context = getApplicationContext();

        Hub hub = Hub.getInstance();
        if (!hub.init(this))
        {
            // Log.e("Could not initialize the Hub.");
            finish();
            return;
        }

       // Hub.getInstance().addListener(mListener);

        Intent intent = new Intent(context, ScanActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

        hub.setLockingPolicy(Hub.LockingPolicy.NONE);
        hub.addListener(mListener);
        hub.attachToAdjacentMyo();


    }

    private DeviceListener mListener = new AbstractDeviceListener()
    {
        @Override
        public void onConnect(Myo myo, long timestamp)
        {
            Toast.makeText(context, "Myo Connected!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onDisconnect(Myo myo, long timestamp)
        {
            Toast.makeText(context, "Myo Disconnected!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPose(Myo myo, long timestamp, Pose pose)
        {
            //Toast.makeText(context, "Pose: " + pose, Toast.LENGTH_SHORT).show();

            TextView mTextView = (TextView)findViewById(R.id.mTextView);

            // Handle the cases of the Pose enumeration, and change the text of the text view
            // based on the pose we receive.
            switch (pose) {
                case UNKNOWN:
                    mTextView.setText("Hello");
                    break;
                case REST:
//                case DOUBLE_TAP:
//                    //int restTextId = R.string.hello_world;
//                    switch (myo.getArm()) {
//                        case LEFT:
//                            //restTextId = R.string.arm_left;
//                            break;
//                        case RIGHT:
//                            //restTextId = R.string.arm_right;
//                            break;
//
//                    }
//                    //mTextView.setText(getString(restTextId));
//                    break;

                case FIST:
                    //mTextView.setText("Start");
                    Toast.makeText(context, "Fist", Toast.LENGTH_SHORT).show();
                    Forward("http://192.168.240.1/arduino/digital/13/11");
                    break;

                case WAVE_IN:
                    //mTextView.setText("Left");
                    Toast.makeText(context, "Left", Toast.LENGTH_SHORT).show();
                    Left("http://192.168.240.1/arduino/digital/13/71");
                    break;

                case WAVE_OUT:
                  //  mTextView.setText("Right");
                    Toast.makeText(context, "Right", Toast.LENGTH_SHORT).show();
                    Right("http://192.168.240.1/arduino/digital/13/31");
                    break;

                case FINGERS_SPREAD:
                   // mTextView.setText("Forward");
                    Toast.makeText(context, "Forward", Toast.LENGTH_SHORT).show();
                    Stop("http://192.168.240.1/arduino/digital/13/0");
                    break;

                case DOUBLE_TAP:
                    // mTextView.setText("Reverse");
                    Toast.makeText(context, "Reverse", Toast.LENGTH_SHORT).show();
                    Stop("http://192.168.240.1/arduino/digital/13/51");
                    break;
            }
            if (pose != Pose.UNKNOWN && pose != Pose.REST) {
                // Tell the Myo to stay unlocked until told otherwise. We do that here so you can
                // hold the poses without the Myo becoming locked.
                myo.unlock(Myo.UnlockType.HOLD);
                // Notify the Myo that the pose has resulted in an action, in this case changing
                // the text on the screen. The Myo will vibrate.
                myo.notifyUserAction();
            } else {
                // Tell the Myo to stay unlocked only for a short period. This allows the Myo to
                // stay unlocked while poses are being performed, but lock after inactivity.
                myo.unlock(Myo.UnlockType.TIMED);
            }

        }


    };






    //Commands

    public void Forward(String url)
    {


// Request a string response
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // Result handling
                        //System.out.println(response.substring(0,10000));

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                // Error handling
                System.out.println("Something went wrong!");
                error.printStackTrace();

            }
        });

// Add the request to the queue
        Volley.newRequestQueue(this).add(stringRequest);
        //Volley.newRequestQueue(this).cancelAll(stringRequest);
    }
    public void BackLeft(String url)
    {


// Request a string response
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // Result handling
                        //System.out.println(response.substring(0,10000));

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                // Error handling
                System.out.println("Something went wrong!");
                error.printStackTrace();

            }
        });

// Add the request to the queue
        Volley.newRequestQueue(this).add(stringRequest);
        //Volley.newRequestQueue(this).cancelAll(stringRequest);
    }
    public void BackRight(String url)
    {


// Request a string response
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // Result handling
                        //System.out.println(response.substring(0,10000));

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                // Error handling
                System.out.println("Something went wrong!");
                error.printStackTrace();

            }
        });

// Add the request to the queue
        Volley.newRequestQueue(this).add(stringRequest);
        //Volley.newRequestQueue(this).cancelAll(stringRequest);
    }
    public void ForwardLeft(String url)
    {


// Request a string response
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // Result handling
                        //System.out.println(response.substring(0,10000));

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                // Error handling
                System.out.println("Something went wrong!");
                error.printStackTrace();

            }
        });

// Add the request to the queue
        Volley.newRequestQueue(this).add(stringRequest);
        //Volley.newRequestQueue(this).cancelAll(stringRequest);
    }
    public void ForwardRight(String url)
    {


// Request a string response
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // Result handling
                        //System.out.println(response.substring(0,10000));

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                // Error handling
                System.out.println("Something went wrong!");
                error.printStackTrace();

            }
        });

// Add the request to the queue
        Volley.newRequestQueue(this).add(stringRequest);
        //Volley.newRequestQueue(this).cancelAll(stringRequest);
    }
    public void Right(String url)
    {
//        String url = null;
//        if (js.getDistance() <= 80)
//        {
//            url = "http://192.168.240.1/arduino/digital/13/11";
//            System.out.println("First Speed");
//        }else if (js.getDistance() > 80 && js.getDistance() <= 160)
//        {
//            url = "http://192.168.240.1/arduino/digital/13/12";
//            System.out.println("Second Speed");
//        }else if (js.getDistance() > 160)
//        {
//            url = "http://192.168.240.1/arduino/digital/13/13";
//            System.out.println("Third Speed");
//        }
        //String url = "http://bing.com";

// Request a string response
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // Result handling
                        // System.out.println(response.substring(0,10000));

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                // Error handling
                System.out.println("Something went wrong!");
                error.printStackTrace();

            }
        });

// Add the request to the queue
        Volley.newRequestQueue(this).add(stringRequest);
        //Volley.newRequestQueue(this).cancelAll(stringRequest);
    }
    public void Left(String url)
    {
//        String url = null;
//        if (js.getDistance() <= 80)
//        {
//            url = "http://192.168.240.1/arduino/digital/13/21";
//            System.out.println("First Speed");
//        }else if (js.getDistance() > 80 && js.getDistance() <= 160)
//        {
//            url = "http://192.168.240.1/arduino/digital/13/22";
//            System.out.println("Second Speed");
//        }else if (js.getDistance() > 160)
//        {
//            url = "http://192.168.240.1/arduino/digital/13/23";
//            System.out.println("Third Speed");
//        }

// Request a string response
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // Result handling
                        //System.out.println(response.substring(0,100));

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                // Error handling
                System.out.println("Something went wrong!");
                error.printStackTrace();

            }
        });

// Add the request to the queue
        Volley.newRequestQueue(this).add(stringRequest);
        //Volley.newRequestQueue(this).cancelAll(stringRequest);
    }
    public void Back(String url)
    {
//        String url = null;
//        if (js.getDistance() <= 80)
//        {
//            url = "http://192.168.240.1/arduino/digital/13/1500";
//            System.out.println("First Speed");
//        }else if (js.getDistance() > 80 && js.getDistance() <= 160)
//        {
//            url = "http://192.168.240.1/arduino/digital/13/1530";
//            System.out.println("Second Speed");
//        }else if (js.getDistance() > 160)
//        {
//            url = "http://192.168.240.1/arduino/digital/13/1560";
//            System.out.println("Third Speed");
//        }

// Request a string response
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // Result handling
                        //System.out.println(response.substring(0,100));

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                // Error handling
                System.out.println("Something went wrong!");
                error.printStackTrace();

            }
        });

// Add the request to the queue
        Volley.newRequestQueue(this).add(stringRequest);
        // Volley.newRequestQueue(this).cancelAll(stringRequest);
    }
    public void Stop(String url)
    {


// Request a string response
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // Result handling
                        //System.out.println(response.substring(0,10000));

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                // Error handling
                System.out.println("Something went wrong!");
                error.printStackTrace();

            }
        });

// Add the request to the queue
        Volley.newRequestQueue(this).add(stringRequest);
        //Volley.newRequestQueue(this).cancelAll(stringRequest);
    }


}
