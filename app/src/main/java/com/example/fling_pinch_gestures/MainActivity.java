package com.example.fling_pinch_gestures;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.icu.number.Scale;
import android.opengl.Matrix;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener

{
    // fling gesture steps
    /*

    1. GDC -gesture Detect compact
    2. onTouchEvent
    3. override all methods of gestureDetector
    4. use any method (in NEED)

     */

    ImageView img;
    float gestureFactor=1.0f;

    GestureDetectorCompat gestureDetectorCompat;
    ScaleGestureDetector objScaleGestureDetector;
    int images[]=
            {
                    R.drawable.one, R.drawable.two
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img=(ImageView)findViewById(R.id.imageView);

        gestureDetectorCompat=new GestureDetectorCompat(this,this);

        // for pinch/scale - zoom in/out effects
        objScaleGestureDetector= new ScaleGestureDetector(this, new PinchZoomListener());

        gestureDetectorCompat.setIsLongpressEnabled(true);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetectorCompat.onTouchEvent(event);
        objScaleGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent downEvent, MotionEvent moveEvent, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    //needed
    @Override
    public boolean onFling(MotionEvent downEvent, MotionEvent moveEvent, float velocityX, float velocityY) {
        boolean result=false;
        float diffY=moveEvent.getY()- downEvent.getY();
        float diffX=moveEvent.getX()- downEvent.getX();

        //100=threshold - max

        if(Math.abs(diffX)> 100 && Math.abs(velocityX)> 100 )
        {

            //right or left
            if(diffX>0)
            {
                swipeRight();
            }
            else
            {
                swipeLeft();
            }

        }

        return false;
    }

    private void swipeLeft() {
        img.setImageResource(R.drawable.two);
    }

    private void swipeRight() {
        img.setImageResource(R.drawable.one);
    }

    //for on scale/pinch-zoom in/out of images

    public class PinchZoomListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            gestureFactor *= detector.getScaleFactor();
            gestureFactor =Math.max(0.1f, Math.min (gestureFactor, 10.f));
            img.setScaleX(gestureFactor);
            img.setScaleY(gestureFactor);

            return true;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            return true;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {
            super.onScaleEnd(detector);
        }
    }


}