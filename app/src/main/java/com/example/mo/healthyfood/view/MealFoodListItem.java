package com.example.mo.healthyfood.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.mo.healthyfood.R;

/**
 * Created by Mo on 4/21/2016.
 */
public class MealFoodListItem extends BaseCustomViewGroup  {

    TextView tvFood,tvAmount,tvUnit,tvKcal;
    public MealFoodListItem(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public MealFoodListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs, 0, 0);
    }

    public MealFoodListItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public MealFoodListItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, defStyleRes);
    }

    private void initInflate() {
        inflate(getContext(), R.layout.fragment_list_mealfood, this);
    }

    private void initInstances() {
        // findViewById here
        //tvFood,tvAmount,tvUnit,tvKcal;
        tvFood =(TextView)findViewById(R.id.tvFood);
        tvAmount =(TextView)findViewById(R.id.tvAmount);
        tvUnit =(TextView)findViewById(R.id.tvUnit);
        tvKcal =(TextView)findViewById(R.id.tvKcal);

    }

    private void initWithAttrs(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        /*
        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.StyleableName,
                defStyleAttr, defStyleRes);

        try {

        } finally {
            a.recycle();
        }
        */
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();

        BundleSavedState savedState = new BundleSavedState(superState);
        // Save Instance State(s) here to the 'savedState.getBundle()'
        // for example,
        // savedState.getBundle().putString("key", value);

        return savedState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        BundleSavedState ss = (BundleSavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());

        Bundle bundle = ss.getBundle();
        // Restore State from bundle here
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    //tvFood,tvAmount,tvUnit,tvKcal

    public void setNameFood(String text){
        tvFood.setText(text);
    }

    public void setAmount(String text){
        tvAmount.setText(text);
    }

    public void setUnit(String text){
        tvUnit.setText(text);
    }

    public void setKcal2(String text){
        tvKcal.setText(text);
    }

}
