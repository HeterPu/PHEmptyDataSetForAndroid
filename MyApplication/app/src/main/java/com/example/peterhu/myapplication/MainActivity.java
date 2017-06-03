package com.example.peterhu.myapplication;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity  implements PHEmptyDataSet.PHEmptyDataSetDelegate{

    private  PHEmptyDataSet dataS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Button btn = new Button(this);
        btn.setBackgroundColor(Color.GREEN);

//        testV.getHeight();
//        testV.setBackgroundColor(0x125995);
//        testV2.setText("sdsaddfsdfsdfsd");

        RelativeLayout.LayoutParams param1 = new  RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,400);
        RelativeLayout.LayoutParams param2 = new  RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,800);
        param1.addRule(RelativeLayout.CENTER_IN_PARENT);
        param2.addRule(RelativeLayout.CENTER_IN_PARENT);



        btn.setLayoutParams(param2);
      final   LinearLayout mainL = new LinearLayout(this);
        ViewGroup.LayoutParams param = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,800);


       int  ja = param1.width;
//        testV2.setLayoutParams(param1);

//        mainL.addView(testV);
        mainL.addView(btn);
       btn.setText("hello!");

        final PHEmptyDataSet testV2 = new PHEmptyDataSet(btn);
        testV2.delegate = this;
        testV2.setBackgroundColor(Color.CYAN);

       btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               testV2.noNetWork();

           }
       });

        mainL.setLayoutParams(param);
        setContentView(mainL);
        mainL.setBackgroundColor(Color.RED);
    }

//   PHEmptyDataSetDelegate

    @Override
    public Button viewForEmptyDataSet(RelativeLayout layout,PHEmptyDataSet.TapNoDataType type) {
        Button btn = new Button(this);

        btn.setLayoutParams(new ViewGroup.LayoutParams(200,200));
//        Bitmap bitmap = BitmapFactory.decodeResource(res,R.drawable.m123);
        Drawable draw = new BitmapDrawable(getResources(),BitmapFactory.decodeResource(getResources(),R.drawable.m123));
        btn.setBackground(draw);
        btn.setText("goog");
        btn.setTextColor(Color.RED);
        btn.setTextSize((float)8.0);
        return btn;
    }

    @Override
    public Integer verticalOffsetForEmptyDataSet(RelativeLayout layout, PHEmptyDataSet.TapNoDataType type) {
        return  null;
    }

    @Override
    public Integer horizonOffsetForEmptyDataSet(RelativeLayout layout, PHEmptyDataSet.TapNoDataType type) {
        return null;
    }

    @Override
    public Integer backgroundColorForEmptyDataSet(RelativeLayout layout, PHEmptyDataSet.TapNoDataType type) {
        return null;
    }

    @Override
    public Boolean didTapEmptyDataView(RelativeLayout layout, PHEmptyDataSet.TapNoDataType type) {
        return null;
    }
}
