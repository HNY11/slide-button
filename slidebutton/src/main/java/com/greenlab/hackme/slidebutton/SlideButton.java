package com.greenlab.hackme.slidebutton;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SlideButton extends LinearLayout implements View.OnClickListener
{
    LinearLayout linearLayout;
    TextView centerText;
    ImageButton slidingButton;
    View view;

    TypedArray typedArray;
    //shapes
    GradientDrawable buttonCollapse,buttonExpand,backgroundButton;
    String mAttrText;
    Drawable disabledDrawable,enabledDrawable;
    int mAttrId,mAttrButtonPadding,mAttrTextPadding,
            mAttrTextSize,mAttrTextColor,mAttrRadius,mAttrCollapse,
            mAttrStrokeWidth,mAttrStrokeColor,mAttrExpand,mAttrBackColor,
            mAttrBackStrokeColor,mAttrBackStrokeWidth;

    //it is the variable that says that the button is expand or not.
    private boolean active;
    //it is the initial width of the button. we need to save it so we can back to the initial position.
    private int initialButtonWidth;
    SlideListener listener;

    public SlideButton(Context context) {
        super(context);
        init(context,null,-1,-1);
    }

    public SlideButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, -1, -1);
    }

    public SlideButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, -1);
    }

    @TargetApi(21)
    public SlideButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    public void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {

        attrValue(context,attrs);
        btnCollapse();
        btnExpand();
        backButton();
        dynamicLayout(context);
        dynamicButton(context);
        dynamicTextView(context);
        new ShineEffect(centerText);

        try {
            listener = (SlideListener) context;
        }catch (ClassCastException e){
            e.printStackTrace();
        }
    }

    /*
  -----------------------------------------getting attr values---------------------------------------------------------
     */
    private void attrValue(Context context,AttributeSet attrs){

        typedArray=context.getTheme().obtainStyledAttributes(attrs,R.styleable.SlideButton,0,0);
        try {
            //attribute for id
            mAttrId=typedArray.getResourceId(R.styleable.SlideButton_id,0);

            //attribute for text
            mAttrText = typedArray.getString(R.styleable.SlideButton_text);
            mAttrTextPadding=typedArray.getDimensionPixelSize(R.styleable.SlideButton_textPadding,0);
            mAttrTextSize=typedArray.getDimensionPixelSize(R.styleable.SlideButton_textSize,0);
            mAttrTextColor=typedArray.getColor(R.styleable.SlideButton_textColor, Color.GRAY);

            //attribute for  button
            mAttrButtonPadding =typedArray.getDimensionPixelSize(R.styleable.SlideButton_buttonPadding,0);
            mAttrRadius=typedArray.getDimensionPixelSize(R.styleable.SlideButton_cornerRadius,45);
            mAttrCollapse=typedArray.getColor(R.styleable.SlideButton_collapseColor,Color.WHITE);
            mAttrExpand=typedArray.getColor(R.styleable.SlideButton_expandColor,Color.WHITE);
            disabledDrawable=typedArray.getDrawable(R.styleable.SlideButton_collapseIcon);
            enabledDrawable=typedArray.getDrawable(R.styleable.SlideButton_expandIcon);
            mAttrStrokeColor=typedArray.getColor(R.styleable.SlideButton_strokeColor,Color.parseColor("#ee071a32"));
            mAttrStrokeWidth=typedArray.getDimensionPixelSize(R.styleable.SlideButton_strokeWidth,3);

            //attribute for background
            mAttrBackColor=typedArray.getColor(R.styleable.SlideButton_backColor,Color.parseColor("#ee071a32"));
            mAttrBackStrokeColor=typedArray.getColor(R.styleable.SlideButton_backStrokeColor,Color.WHITE);
            mAttrBackStrokeWidth=typedArray.getDimensionPixelSize(R.styleable.SlideButton_backStrokeWidth,3);
        }
        finally {
            typedArray.recycle();
        }
    }

     /*
  -----------------------------------------btn collapse shape---------------------------------------------------------
     */

    private void btnCollapse(){
        buttonCollapse = new GradientDrawable();
        buttonCollapse.setShape(GradientDrawable.RECTANGLE);
        buttonCollapse.setCornerRadius(mAttrRadius);
        buttonCollapse.setColor(mAttrCollapse);
        buttonCollapse.setStroke(mAttrStrokeWidth,mAttrStrokeColor);
    }

     /*
  -----------------------------------------btn expand shape---------------------------------------------------------
     */

    private void btnExpand(){
        buttonExpand = new GradientDrawable();
        buttonExpand.setShape(GradientDrawable.RECTANGLE);
        buttonExpand.setCornerRadius(mAttrRadius);
        buttonExpand.setColor(mAttrExpand);
        buttonExpand.setStroke(mAttrStrokeWidth,mAttrStrokeColor);
    }

     /*
  -----------------------------------------btn background shape---------------------------------------------------------
     */

    private void backButton(){
        backgroundButton = new GradientDrawable();
        backgroundButton.setShape(GradientDrawable.RECTANGLE);
        backgroundButton.setCornerRadius(mAttrRadius);
        backgroundButton.setColor(mAttrBackColor);
        backgroundButton.setStroke(mAttrBackStrokeWidth,mAttrBackStrokeColor);
    }

     /*
  -----------------------------------------dynamic linear layout---------------------------------------------------------
     */

    private void dynamicLayout(Context context) {
        linearLayout=new LinearLayout(context);
        //LayoutParams are used by views to tell their parents how big the view wants to be for both in width and height
        linearLayout.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setBackground(backgroundButton);
        linearLayout.setGravity(Gravity.START);
        //add the view to parent layout
        addView(linearLayout);
    }

     /*
  -----------------------------------------dynamic text view---------------------------------------------------------
     */

    private void dynamicTextView(Context context) {
        centerText=new TextView(context);
        centerText.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        centerText.setGravity(Gravity.CENTER);
        centerText.setText(mAttrText);
        centerText.setPadding(mAttrTextPadding,0,0,0);
        centerText.setTextSize(mAttrTextSize);
        centerText.setTextColor(mAttrTextColor);
        linearLayout.addView(centerText);
    }

     /*
  -----------------------------------------dynamic image button--------------------------------------------------------
     */

    private void dynamicButton(Context context) {
       slidingButton=new ImageButton(context);
       LayoutParams params=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
       params.setMargins(3,3,3,3);
       slidingButton.setLayoutParams(params);
       slidingButton.setImageDrawable(disabledDrawable);
        slidingButton.setId(mAttrId);
       slidingButton.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
       slidingButton.setPadding(mAttrButtonPadding,mAttrButtonPadding,mAttrButtonPadding,mAttrButtonPadding);
       slidingButton.setBackground(buttonCollapse);
       linearLayout.addView(slidingButton);
       slidingButton.setOnClickListener(this);
    }

     /*
  -----------------------------------------listener---------------------------------------------------------
     */

    @Override
    public void onClick(View view) {

        this.view=view;
        if(active){
            collapseButton();
        }
        else {
            initialButtonWidth=slidingButton.getWidth();
            expandButton();
        }
    }

    public interface SlideListener{
        void onClick(boolean active,View v);
    }

    public void setOnSlideListener(SlideListener listener){
        this.listener=listener;
    }

     /*
  -----------------------------------------expand animation---------------------------------------------------------
     */

    private void expandButton()
    {
        final ValueAnimator widthAnimator = ValueAnimator.ofInt(slidingButton.getWidth(),getWidth()-6);
        widthAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                ViewGroup.LayoutParams params = slidingButton.getLayoutParams();
                params.width = (Integer) widthAnimator.getAnimatedValue();
                slidingButton.setLayoutParams(params);
                slidingButton.setEnabled(false);
            }
        });

        widthAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                active = true;

                if (listener!=null) {
                    listener.onClick(true,view);
                }
                slidingButton.setEnabled(true);
                slidingButton.setImageDrawable(enabledDrawable);
                slidingButton.setBackground(buttonExpand);
            }
        });
        widthAnimator.start();
    }

     /*
  -----------------------------------------collapse animation---------------------------------------------------------
     */

    private void collapseButton() {

        final ValueAnimator widthAnimator = ValueAnimator.ofInt(slidingButton.getWidth(), initialButtonWidth);
        widthAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                ViewGroup.LayoutParams params =  slidingButton.getLayoutParams();
                params.width = (Integer) widthAnimator.getAnimatedValue();
                slidingButton.setLayoutParams(params);
                slidingButton.setEnabled(false);
            }
        });

        widthAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                active = false;

                if(listener!=null){
                    listener.onClick(false,view);
                }
                slidingButton.setEnabled(true);
                if(disabledDrawable!=null){
                    slidingButton.setImageDrawable(disabledDrawable);
                }
                slidingButton.setBackground(buttonCollapse);
            }
        });
        widthAnimator.start();
    }
}
