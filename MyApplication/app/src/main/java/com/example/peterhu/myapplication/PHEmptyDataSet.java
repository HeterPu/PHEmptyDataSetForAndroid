package com.example.peterhu.myapplication;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;



/**
 * Created by peterhu on 2017/3/30.
 */

public class PHEmptyDataSet extends RelativeLayout{
    
    /**
     * 没有网络的时候PHEmptyNoDataNoNetwork;
     * 没有数据的时候PHEmptyNoData;
     * */
    public enum TapNoDataType {
        PHEmptyNoDataNoNetwork,
        PHEmptyNoData,
    }
    
    /**
     * 代理接口
     * */
    public interface PHEmptyDataSetDelegate {
        /**
         * 点击图片实现加载，通过返回bool值决定视图是否消失,不设置返回null，使用默认配置。
         * @param layout 布局
         * @param type 无数据类型
         * @return 返回值
         */
        Boolean  didTapEmptyDataView(RelativeLayout layout,TapNoDataType type);

        /**
         * 获取自定义的视图，不设置返回null，使用默认配置。
         * @param layout 布局
         * @param type 无数据类型
         * @return 返回的按钮
         */
        Button  viewForEmptyDataSet(RelativeLayout layout,TapNoDataType type);

        /**
         * 设置背景颜色
         * @param layout  布局
         * @param type 无数据类型
         * @return 返回的颜色值
         */
        Integer backgroundColorForEmptyDataSet(RelativeLayout layout,TapNoDataType type);

        /**
         * 设置垂直偏移量
         * @param layout 布局
         * @param type 无数据类型
         * @return 返回垂直偏移量
         */
        Integer verticalOffsetForEmptyDataSet(RelativeLayout layout,TapNoDataType type);

        /**
         * 设置水平偏移
         * @param layout 布局
         * @param type 无数据类型
         * @return 返回水平偏移量
         */
        Integer horizonOffsetForEmptyDataSet(RelativeLayout layout, TapNoDataType type);
    }
    
    public  Context mcontext;
    public  PHEmptyDataSetDelegate delegate;
    private ViewGroup msuperGroup;
    private View mfatherView;
    private ImageView imageBTN;
    private TextView  titleV;
    private boolean isFirstCreated;
    
    public PHEmptyDataSet(View fatherView){
        super(fatherView.getContext());
        mcontext = fatherView.getContext();
        this.isFirstCreated = false;
        this.msuperGroup = (ViewGroup) fatherView.getParent();
        this.mfatherView = fatherView;
        ViewGroup.LayoutParams mparam = mfatherView.getLayoutParams();
        this.setLayoutParams(mparam);
        this.imageBTN = new ImageView(mcontext);
        this.imageBTN.setId(9527);
        titleV = new TextView(mcontext);
    }


    /**
     * 有数据的时候调用，数据大于一条。
     */
    public void hasData(){
        switchView(false);
    }

    /**
     * 无数据时调用
     */
    public  void  noData(){
        setPHEmptyDataSetWithType(TapNoDataType.PHEmptyNoData);
    }

    /**
     * 无网络时调用
     */
    public  void noNetWork(){
        setPHEmptyDataSetWithType(TapNoDataType.PHEmptyNoDataNoNetwork);
    }


    private void setPHEmptyDataSetWithType(TapNoDataType type){
        
        imageBTN.setClickable(true);
        Integer verticalOff  = delegate == null ? null: delegate.verticalOffsetForEmptyDataSet(this,type);
        if (verticalOff == null) {
            verticalOff = (type == TapNoDataType.PHEmptyNoData) ? PHEmptyDataSetConstants.DataSet_offset_v_noData : PHEmptyDataSetConstants.DataSet_offset_v_noNetWork;
        }
        
        Integer horizonOff = delegate == null ? null: delegate.horizonOffsetForEmptyDataSet(this,type);
        if (horizonOff == null) {
            horizonOff = (type == TapNoDataType.PHEmptyNoData) ? PHEmptyDataSetConstants.DataSet_offset_h_noData : PHEmptyDataSetConstants.DataSet_offset_h_noNetWork;
        }
        
        Integer color = delegate == null ? null: delegate.backgroundColorForEmptyDataSet(this,type);
        if (color == null){
            color = (type == TapNoDataType.PHEmptyNoData) ? PHEmptyDataSetConstants.DataSet_backGround_noData : PHEmptyDataSetConstants.DataSet_backGround_noNetWork;
        }
        setBackgroundColor(color);
        
        String title ;
        Drawable draw;
        Integer titleColor;
        Float titleSize;
        Integer mwidth;
        Integer mheight;
        Button button = delegate == null ? null: delegate.viewForEmptyDataSet(this,type);
        if (button == null){
            title = (type == TapNoDataType.PHEmptyNoData) ? PHEmptyDataSetConstants.DataSet_title_noData : PHEmptyDataSetConstants.DataSet_title_noNetWork;
            draw = (type == TapNoDataType.PHEmptyNoData) ?  new BitmapDrawable(getResources(),BitmapFactory.decodeResource(getResources(),
                                                                                                                           PHEmptyDataSetConstants.DataSet_pic_noData)) : new BitmapDrawable(getResources(),BitmapFactory.decodeResource(getResources(),
                                                                                                                                                                                                                                         PHEmptyDataSetConstants.DataSet_pic_noNetWork));
            titleColor =  (type == TapNoDataType.PHEmptyNoData) ? PHEmptyDataSetConstants.DataSet_titleColor_noData : PHEmptyDataSetConstants.DataSet_titleColor_noNetWork;
            titleSize =  (type == TapNoDataType.PHEmptyNoData) ? PHEmptyDataSetConstants.DataSet_titleSize_noData : PHEmptyDataSetConstants.DataSet_titleSize_noNetWork;
            mwidth =  (type == TapNoDataType.PHEmptyNoData) ? PHEmptyDataSetConstants.DataSet_width_noData : PHEmptyDataSetConstants.DataSet_width_noNetWork;
            mheight =  (type == TapNoDataType.PHEmptyNoData) ? PHEmptyDataSetConstants.DataSet_height_noData : PHEmptyDataSetConstants.DataSet_height_noNetWork;
        }
        else
        {
            title =  (String)button.getText();
            draw =   button.getBackground();
            titleColor = button.getCurrentTextColor();
            titleSize = button.getTextSize();
            mwidth = button.getLayoutParams().width;
            mheight = button.getLayoutParams().height;
        }
        
        Integer superHeight =  getLayoutParams().height == -1 ? msuperGroup.getHeight():getLayoutParams().height;
        Integer superWidth =  getLayoutParams().width == -1 ? msuperGroup.getWidth():getLayoutParams().width;
        
        imageBTN.setImageDrawable(draw);
        imageBTN.setScaleType(ImageView.ScaleType.FIT_XY);
        titleV.setText(title);
        titleV.setTextColor(titleColor);
        titleV.setTextSize(titleSize);
        titleV.setTextAlignment(TEXT_ALIGNMENT_CENTER);
        RelativeLayout.LayoutParams param1 = new RelativeLayout.LayoutParams(mwidth,mheight);
        RelativeLayout.LayoutParams param2 = new RelativeLayout.LayoutParams(superWidth * 2 / 3, ViewGroup.LayoutParams.WRAP_CONTENT);
        param1.addRule(RelativeLayout.ALIGN_PARENT_START);
        param1.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        param2.addRule(RelativeLayout.BELOW,9527);
        
        param1.topMargin = (superHeight - param1.height) / 2 + verticalOff;
        param1.leftMargin = (superWidth - param1.width) / 2 + horizonOff;
        param2.topMargin = PHEmptyDataSetConstants.DataSet_Image_title_padding;
        param2.leftMargin = (superWidth - param2.width) / 2 + horizonOff;
        
        imageBTN.setLayoutParams(param1);
        titleV.setLayoutParams(param2);
        
        if (!isFirstCreated){
            addView(titleV);
            addView(imageBTN);
            isFirstCreated = true;
        }
        
        final TapNoDataType mtype = type;
        imageBTN.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                imageBTN.setClickable(false);
                Boolean isAllowedClick = delegate == null ? null: delegate.didTapEmptyDataView(PHEmptyDataSet.this,mtype);
                if (isAllowedClick == null){isAllowedClick = false;}
                if (isAllowedClick) {PHEmptyDataSet.this.hasData();}
            }
        });
        switchView(true);
    }
    
    private void switchView(boolean isChange) {
        if (isChange) {
            AlphaAnimation alphaAnimation = (AlphaAnimation) AnimationUtils.loadAnimation(mcontext, R.anim.alphato1);
            
            int index = msuperGroup.indexOfChild(mfatherView);
            if (index != -1){
                msuperGroup.removeView(mfatherView);
            }
            if((this.getParent() == null)&&(index != -1)){
                msuperGroup.addView(this,index);
                this.startAnimation(alphaAnimation);}
        }
        else
        {
            AlphaAnimation alphaAnimation = (AlphaAnimation) AnimationUtils.loadAnimation(mcontext,R.anim.alphato1);
            int index = msuperGroup.indexOfChild(this);
            if (index != -1){
                msuperGroup.removeView(this);
            }
            if ((mfatherView.getParent() == null)&&(index != -1)){
                msuperGroup.addView(mfatherView,index);
                mfatherView.startAnimation(alphaAnimation);}
        }
    }
}
