package com.example.mo.healthyfood.add;

/**
 * Created by Mo on 10/30/2016.
 */
public class TotalKcal {
    int Kcal = 0;
    double fat = 0.0,protein,carb;


    public int getTotalKcal() {
        return Kcal;
    }

    public void setTotalKcal(int Kcal) {
        this.Kcal = Kcal;
    }

    public double getTotalFat() {
        return fat;
    }

    public void setTotalFat(double fat) {
        this.fat = fat;
    }


    public double getTotalProtein() {
        return protein;
    }

    public void setTotalProtein(double protein) {
        this.protein = protein;
    }

    public double getTotalCarb() {
        return carb;
    }

    public void setTotalCarb(double carb) {
        this.carb = carb;
    }


}
