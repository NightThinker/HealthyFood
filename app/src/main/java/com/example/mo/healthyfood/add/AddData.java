package com.example.mo.healthyfood.add;

/**
 * Created by Mo on 4/13/2016.
 */
public class AddData {
    String Description,Amount,Unit,Kcal;
    String title,NameFood,kcal,Meal,lm,me,fm,lfm;
    int imgResId;
    public String getMeal() {
        return Meal;
    }

    public void setMeal(String meal) {
        Meal = meal;
    }


    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNameFood() {
        return NameFood;
    }

    public void setNameFood(String title) {
        this.NameFood = title;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        this.Amount = amount;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        this.Unit = unit;
    }

    public String getKcal2() {
        return kcal;
    }

    public void setKcal2(String kcal) {
        this.kcal = kcal;
    }

    public String getKcal1() {
        return Kcal;
    }

    public void setKcal1(String kcal) {
        this.Kcal = kcal;
    }

    public int getImgResId() {
        return imgResId;
    }

    public void setImgResId(int imgResId) {
        this.imgResId = imgResId;
    }



    public String getLM() {
        return lm;
    }

    public void setLM(String lm) {
        this.lm = lm;
    }

    public String getME() {
        return me;
    }

    public void setME(String me) {
        this.me = me;
    }

    public String getFM() {
        return fm;
    }

    public void setFM(String fm) {
        this.fm = fm;
    }

    public String getLFM() {
        return lfm;
    }

    public void setLFM(String lfm) {
        this.lfm = lfm;
    }

}
