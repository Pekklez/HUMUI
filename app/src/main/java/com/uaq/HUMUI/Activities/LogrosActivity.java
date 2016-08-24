package com.uaq.HUMUI.Activities;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;

import com.uaq.HUMUI.R;

import java.util.ArrayList;

public class LogrosActivity extends AppCompatActivity {

    LayoutParams params;
    int viewWidth;
    GestureDetector gestureDetector = null;
    HorizontalScrollView horizontalScrollView;
    ImageView imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9,imageView10
            ,imageView11,imageView12,imageView13,imageView14,imageView15;
    ArrayList<LinearLayout> layouts;
    int parentLeft, parentRight;
    int mWidth;
    int currPosition, prevPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logros);

        //-------------REDONDEAR FOTOS
        imageView3 = (ImageView)findViewById(com.uaq.HUMUI.R.id.imageView3);
        imageView4 = (ImageView)findViewById(com.uaq.HUMUI.R.id.imageView4);
        imageView5 = (ImageView)findViewById(com.uaq.HUMUI.R.id.imageView5);
        imageView6 = (ImageView)findViewById(com.uaq.HUMUI.R.id.imageView6);
        imageView7 = (ImageView)findViewById(com.uaq.HUMUI.R.id.imageView7);
        imageView8 = (ImageView)findViewById(com.uaq.HUMUI.R.id.imageView8);
        imageView9 = (ImageView)findViewById(com.uaq.HUMUI.R.id.imageView9);
        imageView10 = (ImageView)findViewById(com.uaq.HUMUI.R.id.imageView10);
        imageView11 = (ImageView)findViewById(com.uaq.HUMUI.R.id.imageView11);
        imageView12 = (ImageView)findViewById(com.uaq.HUMUI.R.id.imageView12);
        imageView13 = (ImageView)findViewById(com.uaq.HUMUI.R.id.imageView13);
        imageView14 = (ImageView)findViewById(com.uaq.HUMUI.R.id.imageView14);
        imageView15 = (ImageView)findViewById(com.uaq.HUMUI.R.id.imageView15);

        imageView3.setImageDrawable(getRounded(getDrawable(com.uaq.HUMUI.R.mipmap.mpadis_barometro)));
        imageView4.setImageDrawable(getRounded(getDrawable(com.uaq.HUMUI.R.mipmap.mpadis_bluedio)));
        imageView5.setImageDrawable(getRounded(getDrawable(com.uaq.HUMUI.R.mipmap.mpadis_calculadora)));
        imageView6.setImageDrawable(getRounded(getDrawable(com.uaq.HUMUI.R.mipmap.mpadis_derwent)));
        imageView7.setImageDrawable(getRounded(getDrawable(com.uaq.HUMUI.R.mipmap.mpadis_discoduro)));
        imageView8.setImageDrawable(getRounded(getDrawable(com.uaq.HUMUI.R.mipmap.mpadis_herramienta)));
        imageView9.setImageDrawable(getRounded(getDrawable(com.uaq.HUMUI.R.mipmap.mpadis_kingston)));
        imageView10.setImageDrawable(getRounded(getDrawable(com.uaq.HUMUI.R.mipmap.mpadis_lapices_de_color)));
        imageView11.setImageDrawable(getRounded(getDrawable(com.uaq.HUMUI.R.mipmap.mpadis_laptop)));
        imageView12.setImageDrawable(getRounded(getDrawable(com.uaq.HUMUI.R.mipmap.mpadis_mochila)));
        imageView13.setImageDrawable(getRounded(getDrawable(com.uaq.HUMUI.R.mipmap.mpadis_mouse)));
        imageView14.setImageDrawable(getRounded(getDrawable(com.uaq.HUMUI.R.mipmap.mpadis_multimetro)));
        imageView15.setImageDrawable(getRounded(getDrawable(com.uaq.HUMUI.R.mipmap.mpadis_tablet)));

        //-------------GALERIA

        LinearLayout prev = (LinearLayout)findViewById(com.uaq.HUMUI.R.id.prev);
        LinearLayout next = (LinearLayout)findViewById(com.uaq.HUMUI.R.id.next);
        horizontalScrollView =(HorizontalScrollView)findViewById(com.uaq.HUMUI.R.id.horizontalScrollView);
        gestureDetector = new GestureDetector(new MyGestureDetector());
        LinearLayout linearLayout1 = (LinearLayout)findViewById(com.uaq.HUMUI.R.id.linearChild1);
        LinearLayout linearLayout2 = (LinearLayout)findViewById(com.uaq.HUMUI.R.id.linearChild2);
        LinearLayout linearLayout3 = (LinearLayout)findViewById(com.uaq.HUMUI.R.id.linearChild3);
        LinearLayout linearLayout4 = (LinearLayout)findViewById(com.uaq.HUMUI.R.id.linearChild4);
        LinearLayout linearLayout5 = (LinearLayout)findViewById(com.uaq.HUMUI.R.id.linearChild5);
        LinearLayout linearLayout6 = (LinearLayout)findViewById(com.uaq.HUMUI.R.id.linearChild6);
        LinearLayout linearLayout7 = (LinearLayout)findViewById(com.uaq.HUMUI.R.id.linearChild7);
        LinearLayout linearLayout8 = (LinearLayout)findViewById(com.uaq.HUMUI.R.id.linearChild8);
        LinearLayout linearLayout9 = (LinearLayout)findViewById(com.uaq.HUMUI.R.id.linearChild9);
        LinearLayout linearLayout10 = (LinearLayout)findViewById(com.uaq.HUMUI.R.id.linearChild10);
        LinearLayout linearLayout11 = (LinearLayout)findViewById(com.uaq.HUMUI.R.id.linearChild11);
        LinearLayout linearLayout12 = (LinearLayout)findViewById(com.uaq.HUMUI.R.id.linearChild12);
        LinearLayout linearLayout13 = (LinearLayout)findViewById(com.uaq.HUMUI.R.id.linearChild13);


        Display display = getWindowManager().getDefaultDisplay();
        mWidth = display.getWidth();
        viewWidth = mWidth/3;
        layouts = new ArrayList<LinearLayout>();
        params = new LayoutParams(viewWidth, LayoutParams.WRAP_CONTENT);

        linearLayout1.setLayoutParams(params);
        linearLayout2.setLayoutParams(params);
        linearLayout3.setLayoutParams(params);
        linearLayout4.setLayoutParams(params);
        linearLayout5.setLayoutParams(params);
        linearLayout6.setLayoutParams(params);
        linearLayout7.setLayoutParams(params);
        linearLayout8.setLayoutParams(params);
        linearLayout9.setLayoutParams(params);
        linearLayout10.setLayoutParams(params);
        linearLayout11.setLayoutParams(params);
        linearLayout12.setLayoutParams(params);
        linearLayout13.setLayoutParams(params);

        layouts.add(linearLayout1);
        layouts.add(linearLayout2);
        layouts.add(linearLayout3);
        layouts.add(linearLayout4);
        layouts.add(linearLayout5);
        layouts.add(linearLayout6);
        layouts.add(linearLayout7);
        layouts.add(linearLayout8);
        layouts.add(linearLayout9);
        layouts.add(linearLayout10);
        layouts.add(linearLayout11);
        layouts.add(linearLayout12);
        layouts.add(linearLayout13);

        //------------POP-UP
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImage(imageView3.getDrawable());
            }
        });
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImage(imageView4.getDrawable());
            }
        });
        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImage(imageView5.getDrawable());
            }
        });
        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImage(imageView6.getDrawable());
            }
        });
        imageView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImage(imageView7.getDrawable());
            }
        });
        imageView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImage(imageView8.getDrawable());
            }
        });
        imageView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImage(imageView9.getDrawable());
            }
        });
        imageView10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImage(imageView10.getDrawable());
            }
        });
        imageView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImage(imageView11.getDrawable());
            }
        });
        imageView12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImage(imageView12.getDrawable());
            }
        });
        imageView13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImage(imageView13.getDrawable());
            }
        });
        imageView14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImage(imageView14.getDrawable());
            }
        });
        imageView15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImage(imageView15.getDrawable());
            }
        });



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

        /**
        horizontalScrollView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(gestureDetector.onTouchEvent(event)){
                    return true;
                }
                return false;
            }
        });*/
    }

    public void showImage(Drawable imagen) {
        Dialog builder = new Dialog(this);
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                //nothing;
            }
        });

        ImageView imageView = new ImageView(this);
        imageView.setImageDrawable(imagen);

        builder.addContentView(imageView, new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        builder.show();
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
