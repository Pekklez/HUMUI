package com.uaq.HUMUI.Activities.categorias;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.os.Handler;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.widget.LinearLayout.LayoutParams;
import android.widget.VideoView;


import com.uaq.HUMUI.R;

import java.util.ArrayList;

public class sustentabilidadActivity extends AppCompatActivity {

    LayoutParams params;
    int viewWidth;
    GestureDetector gestureDetector = null;
    HorizontalScrollView horizontalScrollView;
    ImageView imageView3,imageView4,imageView5,imageView6;
    ArrayList<LinearLayout> layouts;
    int parentLeft, parentRight;
    int mWidth;
    int currPosition, prevPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sustentabilidad);


        //-------------REDONDEAR FOTOS
        imageView3 = (ImageView)findViewById(R.id.imageView3);
        imageView4 = (ImageView)findViewById(R.id.imageView4);
        imageView5 = (ImageView)findViewById(R.id.imageView5);
        imageView6 = (ImageView)findViewById(R.id.imageView6);
        imageView3.setImageDrawable(getRounded(getDrawable(R.mipmap.slider1)));
        imageView4.setImageDrawable(getRounded(getDrawable(R.mipmap.slider2)));
        imageView5.setImageDrawable(getRounded(getDrawable(R.mipmap.slider3)));
        imageView6.setImageDrawable(getRounded(getDrawable(R.mipmap.slider4)));


        LinearLayout prev = (LinearLayout)findViewById(R.id.prev);
        LinearLayout next = (LinearLayout)findViewById(R.id.next);
        horizontalScrollView =(HorizontalScrollView)findViewById(R.id.horizontalScrollView);
        gestureDetector = new GestureDetector(new MyGestureDetector());
        LinearLayout linearLayout1 = (LinearLayout)findViewById(R.id.linearChild1);
        LinearLayout linearLayout2 = (LinearLayout)findViewById(R.id.linearChild2);
        LinearLayout linearLayout3 = (LinearLayout)findViewById(R.id.linearChild3);
        LinearLayout linearLayout4 = (LinearLayout)findViewById(R.id.linearChild4);


        Display display = getWindowManager().getDefaultDisplay();
        mWidth = display.getWidth();
        viewWidth = mWidth/3;
        layouts = new ArrayList<LinearLayout>();
        params = new LayoutParams(viewWidth, LayoutParams.WRAP_CONTENT);

        linearLayout1.setLayoutParams(params);
        linearLayout2.setLayoutParams(params);
        linearLayout3.setLayoutParams(params);
        linearLayout4.setLayoutParams(params);

        layouts.add(linearLayout1);
        layouts.add(linearLayout2);
        layouts.add(linearLayout3);
        layouts.add(linearLayout4);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        horizontalScrollView.smoothScrollTo((int)horizontalScrollView.getScrollX()+viewWidth,
                                (int)horizontalScrollView.getScrollY());
                    }
                },100L);
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        horizontalScrollView.smoothScrollTo((int)horizontalScrollView.getScrollX()-viewWidth,
                                (int)horizontalScrollView.getScrollY());
                    }
                },100L);
            }
        });

        horizontalScrollView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(gestureDetector.onTouchEvent(event)){
                    return true;
                }
                return false;
            }
        });
    }

    public RoundedBitmapDrawable getRounded(Drawable drawable){
        Bitmap image =((BitmapDrawable)drawable).getBitmap();
        RoundedBitmapDrawable roundedDrawable = RoundedBitmapDrawableFactory.create(Resources.getSystem() , image);
        roundedDrawable.setCornerRadius(50);
        return roundedDrawable;
    }

    class MyGestureDetector extends SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                               float velocityY) {
            if (e1.getX() < e2.getX()) {
                currPosition = getVisibleViews("left");
            } else {
                currPosition = getVisibleViews("right");
            }

            horizontalScrollView.smoothScrollTo(layouts.get(currPosition)
                    .getLeft(), 0);
            return true;
        }
    }
    public int getVisibleViews(String direction) {
        Rect hitRect = new Rect();
        int position = 0;
        int rightCounter = 0;
        for (int i = 0; i < layouts.size(); i++) {
            if (layouts.get(i).getLocalVisibleRect(hitRect)) {
                if (direction.equals("left")) {
                    position = i;
                    break;
                } else if (direction.equals("right")) {
                    rightCounter++;
                    position = i;
                    if (rightCounter == 2)
                        break;
                }
            }
        }
        return position;
    }
}
