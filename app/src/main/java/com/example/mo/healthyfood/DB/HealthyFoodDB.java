package com.example.mo.healthyfood.DB;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mo.healthyfood.activityMuscle.ShowFoodMuscleActivity;
import com.example.mo.healthyfood.add.AddData;
import com.example.mo.healthyfood.add.TotalKcal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Mo on 3/21/2016.
 */
public class HealthyFoodDB extends SQLiteOpenHelper {
    private static final String TAG = HealthyFoodDB.class.getSimpleName();

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "Healthy";

    // Table Name

    //constant user
    public static final String FoodAllergy_TABLE = "FoodAllergy";
    public static final String Vegetarian_TABLE = "Vegetarian";
    public static final String Activities_TABLE = "Activities";
    public static final String DataTypeUser_TABLE = "DataTypeUser";
    public static final String TypeMuscle_TABLE = "TypeMuscle";
    public static final String Plan_TABLE = "Plan";
    public static final String TypeGoal_Table = "TypeGoal";

    //sum
    public static final String Weight_TABLE = "Weight";

    // muscle
    public static final String BodyFat_Table = "BodyFat";
    public static final String FFMI_Table = "FFMI";

    //sum
    public static final String User_TABLE = "User";

    //muscle
    public static final String UserMuscle_TABLE = "UserMuscle";

    //sum
    public static final String GoalNormal_Table = "GoalNormal";
    public static final String GoalMuscle_Table = "GoalMuscle";

    //question normal
    public static final String Health_TABLE = "Health";

    //question muscle
    public static final String ExerciseToday_TABLE = "ExerciseToday";

    //normal
    public static final String NormalToday_TABLE = "NormalToday";

    //muscle
    public static final String MuscleToday_TABLE = "MuscleToday";

    //constant food
    public static final String MealAndExercise_TABLE = "MealAndExercise";
    public static final String FoodUnit_TABLE = "FoodUnit";
    public static final String TypeFood_TABLE = "TypeFood";

    //food normal
    public static final String FoodNormal_TABLE = "FoodNormal";
    public static final String DetailFoodNormal_TABLE = "DetailFoodNormal";
    public static final String IngredientsNoraml_TABLE = "IngredientsNoraml";

    //food muscle
    public static final String FoodMuscle_TABLE = "FoodMuscle";
    public static final String IngredientsMuscle_TABLE = "IngredientsMuscle";
    public static final String DetailFoodMuscle_TABLE = "DetailFoodMuscle";

    //exercise
    public static final String Exert_TABLE = "Exert";

    //exercise
    public static final String TypeUser_TABLE = "TypeUser";


    //Log

    //Log food normal
    public static final String LogNormal_TABLE = "LogNormal";
    public static final String LogFoodNormal_TABLE = "LogFoodNormal";

    //Log food muscle
    public static final String LogMuscle_TABLE = "LogMuscle";
    public static final String LogFoodMuscle_TABLE = "LogFoodMuscle";

    //log exercise normal
    public static final String LogExerciseNormal_TABLE = "LogExerciseNormal";

    //log exercise muscle
    public static final String LogExerciseMuscle_TABLE = "LogExerciseMuscle";

    //total
    public static final String TotalCaloriesNormal_TABLE = "TotalCaloriesNormal";
    public static final String TotalCaloriesMuscle_TABLE = "TotalCaloriesMuscle";
//    public static final String DetailTotalCaloriesMuscle_TABLE = "DetailTotalCaloriesMuscle";



    // Labels Table Columns names

    //FoodAllergy
    public static final String FA_ID = "FA_ID";
    public static final String FA_Name = "FA_Name";
    public static final String FA_Status = "FA_Status";


    //Vegetarian
    public static final String VT_ID = "VT_ID";
    public static final String VT_Name = "VT_Name";
    public static final String VT_Status = "VT_Status";




    //Activities
    public static final String AC_ID = "AC_ID";
    public static final String AC_Date = "AC_Date";
    public static final String AC_Name = "AC_Name";
    public static final String AC_Values = "AC_Values";


    //DataTypeUser
    public static final String DTU_ID = "DTU_ID";
    public static final String DTU_Name = "DTU_Name";

    //TypeUser
    public static final String TU_ID = "TU_ID";
    public static final String TU_Date = "TU_Date";

    //TypeMuscle
    public static final String TM_ID = "TM_ID";
    public static final String TM_Name = "TM_Name";

    //Plan
    public static final String P_ID = "P_ID";
    public static final String P_Name = "P_Name";
    public static final String P_Values = "P_Values";

    //TypeGoal
    public static final String TG_ID = "TG_ID";
    public static final String TG_Name = "TG_Name";

    //weight
    public static final String WE_ID = "WE_ID";
    public static final String WE_Date = "WE_Date";
    public static final String WE_Weight = "WE_Weight";

    //BodyFat
    public static final String BF_ID = "BF_ID";
    public static final String BF_Date = "BF_Date";
    public static final String BF_BodyFat = "BF_BodyFat";

    //FFMI
    public static final String FI_ID = "FI_ID";
    public static final String FI_Date = "FI_Date";
    public static final String FI_FFMI = "FI_FFMI";

    //User
    public static final String U_ID = "U_ID";
    public static final String U_Date = "U_Date";
    public static final String U_Gender = "U_Gender";
    public static final String U_Height = "U_Height";
    public static final String U_Age = "U_Age";

    //UserMuscle
    public static final String UM_ID = "UM_ID";

    //GoalNormal
    public static final String GN_ID = "GN_ID";
    public static final String GN_Date = "GN_Date";
    public static final String GN_Weight = "GN_Weight";

    //GoalMuscle
    public static final String GM_ID = "GM_ID";
    public static final String GM_Date = "GM_Date";
    public static final String GM_Weight = "GM_Weight";
    public static final String GM_Bodyfat = "GM_Bodyfat";

    //Health
    public static final String HT_ID = "HT_ID";
    public static final String HT_Name = "HT_Name";

    //ExerciseToday
    public static final String ET_ID = "ET_ID";
    public static final String ET_Name = "ET_Name";

    //NormalToday
    public static final String NT_ID = "NT_ID";

    //MuscleToday
    public static final String MT_ID = "MT_ID";

    //meal
    public static final String ME_ID = "ME_ID";
    public static final String ME_Name = "ME_Name";

    //FoodUnit
    public static final String FU_ID = "FU_ID";
    public static final String FU_Name = "FU_Name";

    //TypeFood
    public static final String TF_ID = "TF_ID";
    public static final String TF_Name = "TF_Name";

    //Food normal
    public static final String FN_ID = "FN_ID";
    public static final String FN_Name = "FN_Name";

    //IngredientsNormal
    public static final String IN_ID = "IN_ID";
    public static final String IN_Name = "IN_Name";

    //DetailFood normal
    public static final String DFN_ID = "DFN_ID";
    public static final String FN_Kcal = "FN_Kcal";

    //Food muscle
    public static final String FM_ID = "FM_ID";
    public static final String FM_Name = "FM_Name";

    //IngredientsMuscle
    public static final String IM_ID = "IM_ID";
    public static final String IM_Name = "IM_Name";

    //DetailFood muscle
    public static final String DFM_ID = "DFM_ID";
    public static final String FM_Kcal = "FM_Kcal";
    public static final String FM_Fat = "FM_Fat";
    public static final String FM_Protein = "FM_Protein";
    public static final String FM_Carb = "FM_Carb";

    //Exert
    public static final String EX_ID = "EX_ID";
    public static final String EX_Name = "EX_Name";
    public static final String EX_Time = "EX_Time";
    public static final String EX_Kcal = "EX_Kcal";

    //Log normal
    public static final String LN_ID = "LN_ID";
    public static final String TDN_TotalKcal = "TDN_TotalKcal";
    public static final String TDN_Kcal = "TDN_Kcal";

    //LogFood normal
    public static final String LFN_ID = "LFN_ID";
    public static final String LFN_Amount = "LFN_Amount";
    public static final String LFN_Kcal = "LFN_Kcal";

    //Log muscle
    public static final String LM_ID = "LM_ID";
    public static final String TD_Date = "TD_Date";
    public static final String TDM_TotalKcal = "TDM_TotalKcal";
    public static final String TDM_TotalFat = "TDM_TotalFat";
    public static final String TDM_TotalProtein = "TDM_TotalProtein";
    public static final String TDM_TotalCarb = "TDM_TotalCarb";
    public static final String TDM_Kcal = "TDM_Kcal";
    public static final String TDM_Fat = "TDM_Fat";
    public static final String TDM_Protein = "TDM_Protein";
    public static final String TDM_Carb = "TDM_Carb";

    //LogFood muscle
    public static final String LFM_ID = "LFM_ID";
    public static final String LFM_Amount = "LFM_Amount";
    public static final String LFM_Kcal = "LFM_Kcal";
    public static final String LFM_Fat = "LFM_Fat";
    public static final String LFM_Protein = "LFM_Protein";
    public static final String LFM_Carb = "LFM_Carb";

    //LogExcise normal
    public static final String LEN_ID = "LEN_ID";
    public static final String LEN_Time = "LEN_Time";
    public static final String LEN_Kcal = "LEN_Kcal";

    //LogExcise muscle
    public static final String LEM_ID = "LEM_ID";
    public static final String LEM_Time = "LEM_Time";
    public static final String LEM_Kcal = "LEM_Kcal";

    //TotalCaloriesNormal
    public static final String TLN_ID = "TLN_ID";
    public static final String TLN_Kcal = "TLN_Kcal";

    //TotalCaloriesMuscle
    public static final String TLM_ID = "TLM_ID";
    public static final String TLM_Kcal = "TLM_Kcal";
    public static final String TLM_Fat = "TLM_Fat";
    public static final String TLM_Protein = "TLM_Protein";
    public static final String TLM_Carb = "TLM_Carb";

//    //DetailTotalCaloriesMuscle
//    public static final String DTM_ID = "DTM_ID";
//    public static final String DTM_Fat = "DTM_Fat";
//    public static final String DTM_Protein = "DTM_Protein";
//    public static final String DTM_Carb = "DTM_Carb";



    public HealthyFoodDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + DataTypeUser_TABLE +
                "("+ DTU_ID   + " TEXT PRIMARY KEY NOT NULL," +
                     DTU_Name + " TEXT)");
        Log.d("CREATE TABLE","Create Table  DataTypeUser Successfully.");


        db.execSQL("CREATE TABLE " + TypeMuscle_TABLE +
                "("+ TM_ID   + " TEXT PRIMARY KEY NOT NULL," +
                     TM_Name + " TEXT)");
        Log.d("CREATE TABLE","Create Table TypeMuscle Successfully.");

        db.execSQL("CREATE TABLE " + Plan_TABLE +
                "("+ P_ID     +" TEXT PRIMARY KEY NOT NULL," +
                     P_Name   +" TEXT," +
                     P_Values +" REAL)");
        Log.d("CREATE TABLE","Create Table Plan Successfully.");

        db.execSQL("CREATE TABLE " + TypeGoal_Table +
                "("+ TG_ID   +" TEXT PRIMARY KEY NOT NULL," +
                     TG_Name +" TEXT)");
        Log.d("CREATE TABLE","Create Table TypeGoal Successfully.");


        db.execSQL("CREATE TABLE " + Activities_TABLE +
                "("+ AC_ID     +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                AC_Date   +" NUMERIC unique," +
                AC_Values +" REAL)");
        Log.d("CREATE TABLE","Create Table Activities Successfully.");


        db.execSQL("CREATE TABLE " + Weight_TABLE +
                "(" + WE_ID     +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                      WE_Date   +" NUMERIC unique," +
                      WE_Weight +" REAL)");
        Log.d("CREATE TABLE","Create Table Weight Successfully.");

        db.execSQL("CREATE TABLE " + TypeUser_TABLE +
                "("+ TU_ID   + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                TU_Date   +" NUMERIC unique," +
                DTU_ID + " TEXT,"+
        "FOREIGN KEY(" + DTU_ID + ") REFERENCES " + DataTypeUser_TABLE    + "(" + DTU_ID + "))");
        Log.d("CREATE TABLE","Create Table  TypeUser Successfully.");


        db.execSQL("CREATE TABLE " + BodyFat_Table +
                "(" + BF_ID     +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                BF_Date   +" NUMERIC unique," +
                BF_BodyFat +" REAL)");
        Log.d("CREATE TABLE","Create Table BodyFat Successfully.");



        db.execSQL("CREATE TABLE " + FFMI_Table +
                "(" + FI_ID     +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                FI_Date   +" NUMERIC unique," +
                FI_FFMI +" REAL)");
        Log.d("CREATE TABLE","Create Table FFMI Successfully.");

        db.execSQL("CREATE TABLE " + Vegetarian_TABLE +
                "("+ VT_ID      + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                VT_Name    +" TEXT UNIQUE," +
                VT_Status  + " NUMERIC)");
        Log.d("CREATE TABLE","Create Table  Vegetarian Successfully.");

        db.execSQL("CREATE TABLE " + User_TABLE +
                "(" + U_ID     +" INTEGER PRIMARY KEY  NOT NULL," +
                      U_Date   +" NUMERIC UNIQUE," +
                      U_Gender +" INTEGER," +
                      WE_ID    +" INTEGER," +
                      U_Height +" INTEGER," +
                      U_Age    +" INTEGER," +
                      AC_ID    +" INTEGER," +
                        VT_ID    +" INTEGER," +
                      TU_ID    +" INTEGER," +
                "FOREIGN KEY(" + WE_ID + ") REFERENCES " + Weight_TABLE      + "(" + WE_ID + ")," +
                "FOREIGN KEY(" + AC_ID + ") REFERENCES " + Activities_TABLE  + "(" + AC_ID + ")," +
                "FOREIGN KEY(" + VT_ID + ") REFERENCES " + Vegetarian_TABLE  + "(" + VT_ID + ")," +
                "FOREIGN KEY(" + TU_ID + ") REFERENCES " + TypeUser_TABLE    + "(" + TU_ID + "))");
        Log.d("CREATE TABLE","Create Table User Successfully.");

        db.execSQL("CREATE TABLE " + FoodAllergy_TABLE +
                "("+ FA_ID      + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                U_ID    +" INTEGER," +
                FA_Name    +" TEXT UNIQUE," +
                FA_Status + " NUMERIC,"+
                "FOREIGN KEY(" + U_ID + ") REFERENCES " + User_TABLE    + "(" + U_ID + "))");
        Log.d("CREATE TABLE","Create Table  FoodAllergy Successfully.");


        db.execSQL("CREATE TABLE " + UserMuscle_TABLE +
                "(" + UM_ID    +" INTEGER PRIMARY KEY  NOT NULL," +
                      U_ID     +" INTEGER," +
                      BF_ID    +" INTEGER," +
                      FI_ID    +" INTEGER," +
                      TM_ID    +" TEXT," +
                "FOREIGN KEY(" + U_ID  + ") REFERENCES " + User_TABLE       + "(" + U_ID  + ")," +
                "FOREIGN KEY(" + BF_ID + ") REFERENCES " + BodyFat_Table    + "(" + BF_ID + ")," +
                "FOREIGN KEY(" + FI_ID + ") REFERENCES " + FFMI_Table       + "(" + FI_ID + ")," +
                "FOREIGN KEY(" + TM_ID + ") REFERENCES " + UserMuscle_TABLE + "(" + TM_ID + "))");
        Log.d("CREATE TABLE","Create Table UserMuscle Successfully.");


        db.execSQL("CREATE TABLE " + GoalNormal_Table +
                "("+ GN_ID   +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                     U_ID   +" INTEGER," +
                     TG_ID  +" TEXT," +
                     P_ID   +" TEXT," +
                     GN_Date +" NUMERIC UNIQUE," +
                     GN_Weight +" REAL," +
                "FOREIGN KEY("+ U_ID  +") REFERENCES " + User_TABLE     + "(" + U_ID  + ")," +
                "FOREIGN KEY("+ TG_ID +") REFERENCES " + TypeGoal_Table + "(" + TG_ID + ")," +
                "FOREIGN KEY("+ P_ID  +") REFERENCES " + Plan_TABLE     + "(" + P_ID  + "))");
        Log.d("CREATE TABLE","Create Table GoalNormal Successfully.");



        db.execSQL("CREATE TABLE " + GoalMuscle_Table +
                "("+ GM_ID   +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                U_ID   +" INTEGER," +
                UM_ID  +" INTEGER," +
                P_ID   +" TEXT," +
                GM_Date +" NUMERIC UNIQUE," +
                GM_Weight +" REAL," +
                GM_Bodyfat +" INTEGER," +
                "FOREIGN KEY("+ U_ID  +") REFERENCES " + User_TABLE     + "(" + U_ID  + ")," +
                "FOREIGN KEY("+ UM_ID +") REFERENCES " + UserMuscle_TABLE + "(" + UM_ID + ")," +
                "FOREIGN KEY("+ P_ID  +") REFERENCES " + Plan_TABLE     + "(" + P_ID  + "))");
        Log.d("CREATE TABLE","Create Table GoalMuscle Successfully.");


        db.execSQL("CREATE TABLE " + Health_TABLE +
                "( "+ HT_ID   +" TEXT PRIMARY KEY NOT NULL," +
                      HT_Name +" TEXT)");
        Log.d("CREATE TABLE","Create Table Health Successfully.");


        db.execSQL("CREATE TABLE " + ExerciseToday_TABLE +
                "(" + ET_ID   +" TEXT PRIMARY KEY NOT NULL," +
                      ET_Name +" TEXT)");
        Log.d("CREATE TABLE","Create Table ExerciseToday Successfully.");


        db.execSQL("CREATE TABLE " + NormalToday_TABLE +
                "(" + NT_ID   +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                      U_ID    +" INTEGER," +
                      HT_ID   +" TEXT," +
                      TD_Date +" NUMERIC UNIQUE ," +
                "FOREIGN KEY("+ U_ID  +") REFERENCES " + User_TABLE   + "(" + U_ID  + ")," +
                "FOREIGN KEY("+ HT_ID +") REFERENCES " + Health_TABLE + "(" + HT_ID + "))");
        Log.d("CREATE TABLE","Create Table NormalToday Successfully.");


        db.execSQL("CREATE TABLE " + MuscleToday_TABLE +
                "("+ MT_ID   +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                     U_ID    +" INTEGER," +
                     UM_ID   +" INTEGER," +
                     ET_ID   +" TEXT," +
                     TD_Date +" NUMERIC UNIQUE," +
                "FOREIGN KEY("+ U_ID  +") REFERENCES " + User_TABLE          + "(" + U_ID  + ")," +
                "FOREIGN KEY("+ UM_ID  +") REFERENCES " + UserMuscle_TABLE   + "(" + UM_ID + ")," +
                "FOREIGN KEY("+ ET_ID +") REFERENCES " + ExerciseToday_TABLE + "(" + ET_ID + "))");
        Log.d("CREATE TABLE","Create Table MuscleToday Successfully.");


        db.execSQL("CREATE TABLE " + MealAndExercise_TABLE +
                "("+ ME_ID   +" TEXT PRIMARY KEY NOT NULL," +
                     ME_Name +" TEXT)");
        Log.d("CREATE TABLE","Create Table MealAndExercise Successfully.");


        db.execSQL("CREATE TABLE " + FoodUnit_TABLE +
                "("+ FU_ID   +" TEXT PRIMARY KEY NOT NULL," +
                     FU_Name +" TEXT)");
        Log.d("CREATE TABLE","Create Table FoodUnit Successfully.");


        db.execSQL("CREATE TABLE " + TypeFood_TABLE +
                "("+ TF_ID   +" TEXT PRIMARY KEY NOT NULL," +
                     TF_Name +" TEXT)");
        Log.d("CREATE TABLE","Create Table TypeFood Successfully.");


        db.execSQL("CREATE TABLE " + FoodNormal_TABLE +
                "("+ FN_ID   +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                     ME_ID   +" TEXT," +
                     HT_ID   +" TEXT," +
                     TF_ID   +" TEXT," +
                     FN_Name +" TEXT," +
                "FOREIGN KEY("+ ME_ID +") REFERENCES " + MealAndExercise_TABLE + "(" + ME_ID  + ")," +
                "FOREIGN KEY("+ HT_ID +") REFERENCES " + Health_TABLE          + "(" + HT_ID + ")," +
                "FOREIGN KEY("+ TF_ID +") REFERENCES " + TypeFood_TABLE        + "(" + TF_ID + "))");
        Log.d("CREATE TABLE","Create Table FoodNormal Successfully.");

        db.execSQL("CREATE TABLE " + IngredientsNoraml_TABLE +
                "(" + IN_ID   +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                FN_ID    +" INTEGER," +
                IN_Name   +" TEXT," +
                "FOREIGN KEY("+ FN_ID +") REFERENCES " + FoodNormal_TABLE + "(" + FN_ID + "))");
        Log.d("CREATE TABLE","Create Table IngredientsNoraml Successfully.");


        db.execSQL("CREATE TABLE " + DetailFoodNormal_TABLE +
                "("+ DFN_ID  +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                     FN_ID   +" INTEGER," +
                     FU_ID   +" TEXT," +
                     FN_Kcal +" INTEGER," +
                "FOREIGN KEY("+ FN_ID +") REFERENCES " + FoodNormal_TABLE + "(" + FN_ID  + ")," +
                "FOREIGN KEY("+ FU_ID +") REFERENCES " + FoodUnit_TABLE   + "(" + FU_ID  + "))");
        Log.d("CREATE TABLE","Create Table DetailFoodNormal Successfully.");


        db.execSQL("CREATE TABLE " + FoodMuscle_TABLE +
                "("+ FM_ID   +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                     ME_ID   +" TEXT," +
                     TF_ID   +" TEXT," +
                     FM_Name +" TEXT," +
                "FOREIGN KEY("+ ME_ID +") REFERENCES " + MealAndExercise_TABLE + "(" + ME_ID  + ")," +
                "FOREIGN KEY("+ TF_ID +") REFERENCES " + TypeFood_TABLE        + "(" + TF_ID + "))");
        Log.d("CREATE TABLE","Create Table FoodMuscle Successfully.");

        db.execSQL("CREATE TABLE " + IngredientsMuscle_TABLE +
                "(" + IM_ID   +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                FM_ID    +" INTEGER," +
                IM_Name   +" TEXT," +
                "FOREIGN KEY("+ FM_ID +") REFERENCES " + FoodMuscle_TABLE + "(" + FM_ID + "))");
        Log.d("CREATE TABLE","Create Table IngredientsMuscle Successfully.");


        db.execSQL("CREATE TABLE " + DetailFoodMuscle_TABLE +
                "("+ DFM_ID     +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                     FM_ID      +" INTEGER," +
                     FU_ID      +" TEXT," +
                     FM_Kcal    +" INTEGER," +
                     FM_Fat     +" REAL," +
                     FM_Protein +" REAL," +
                     FM_Carb    +" REAL," +
                "FOREIGN KEY("+ FM_ID +") REFERENCES " + FoodMuscle_TABLE + "(" + FM_ID  + ")," +
                "FOREIGN KEY("+ FU_ID +") REFERENCES " + FoodUnit_TABLE   + "(" + FU_ID + "))");
        Log.d("CREATE TABLE","Create Table DetailFoodMuscle Successfully.");


        db.execSQL("CREATE TABLE " + Exert_TABLE +
                "("+ EX_ID   +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                ME_ID +" TEXT," +
                     EX_Name +" TEXT," +
                     EX_Time +" INTEGER," +
                     EX_Kcal +" REAL," +
                "FOREIGN KEY("+ ME_ID +") REFERENCES " + MealAndExercise_TABLE + "(" + ME_ID + "))");
        Log.d("CREATE TABLE","Create Table Exert Successfully.");


        db.execSQL("CREATE TABLE " + LogNormal_TABLE +
                "("+ LN_ID    +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                     U_ID     +" INTEGER," +
                     TD_Date  +" NUMERIC UNIQUE," +
                TDN_Kcal +" INTEGER," +
                     TDN_TotalKcal +" INTEGER," +
                "FOREIGN KEY("+ U_ID +") REFERENCES " + User_TABLE + "(" + U_ID + "))");
        Log.d("CREATE TABLE","Create Table LogNormal Successfully.");


        db.execSQL("CREATE TABLE " + LogFoodNormal_TABLE +
                "("+ LFN_ID     +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                     LN_ID      +" INTEGER," +
                     ME_ID      +" TEXT," +
                     FN_ID      +" INTEGER," +
                     FU_ID      +" TEXT," +
                     LFN_Amount +" INTEGER," +
                     LFN_Kcal   +" INTEGER," +
                "FOREIGN KEY("+ LN_ID +") REFERENCES " + LogNormal_TABLE       + "(" + LN_ID  + ")," +
                "FOREIGN KEY("+ ME_ID +") REFERENCES " + MealAndExercise_TABLE + "(" + ME_ID  + ")," +
                "FOREIGN KEY("+ FN_ID +") REFERENCES " + FoodNormal_TABLE      + "(" + FN_ID  + ")," +
                "FOREIGN KEY("+ FU_ID +") REFERENCES " + FoodUnit_TABLE        + "(" + FU_ID + "))");
        Log.d("CREATE TABLE","Create Table LogFoodNormal Successfully.");


        db.execSQL("CREATE TABLE " + LogMuscle_TABLE +
                "("+ LM_ID       +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                     U_ID        +" INTEGER," +
                     UM_ID       +" INTEGER," +
                     TD_Date     +" NUMERIC UNIQUE," +
                     TDM_Kcal    +" INTEGER," +
                     TDM_Fat     +" REAL," +
                     TDM_Protein +" REAL," +
                     TDM_Carb    +" REAL," +
                TDM_TotalKcal    +" INTEGER," +
                TDM_TotalFat     +" REAL," +
                TDM_TotalProtein +" REAL," +
                TDM_TotalCarb    +" REAL," +
                "FOREIGN KEY("+ UM_ID +") REFERENCES " + UserMuscle_TABLE + "(" + UM_ID  + ")," +
                "FOREIGN KEY("+ U_ID  +") REFERENCES " + User_TABLE       + "(" + U_ID   + "))");
        Log.d("CREATE TABLE","Create Table LogMuscle Successfully.");


        db.execSQL("CREATE TABLE " + LogFoodMuscle_TABLE +
                "("+ LFM_ID      +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                     LM_ID       +" INTEGER," +
                     ME_ID       +" TEXT," +
                     FM_ID       +" INTEGER," +
                     FU_ID       +" TEXT," +
                     LFM_Amount  +" INTEGER," +
                     LFM_Kcal    +" INTEGER," +
                     LFM_Fat     +" REAL," +
                     LFM_Protein +" REAL," +
                     LFM_Carb    +" REAL," +
                "FOREIGN KEY("+ LM_ID  +") REFERENCES " + LogMuscle_TABLE      + "(" + LM_ID  + ")," +
                "FOREIGN KEY("+ ME_ID  +") REFERENCES " + MealAndExercise_TABLE     + "(" + ME_ID  + ")," +
                "FOREIGN KEY("+ FM_ID  +") REFERENCES " + FoodMuscle_TABLE     + "(" + FM_ID  + ")," +
                "FOREIGN KEY("+ FU_ID +") REFERENCES " + FoodUnit_TABLE + "(" + FU_ID + "))");
        Log.d("CREATE TABLE","Create Table LogFoodMuscle Successfully.");


        db.execSQL("CREATE TABLE " + LogExerciseNormal_TABLE +
                "("+ LEN_ID   +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                     LN_ID    +" INTEGER," +
                     ME_ID    +" TEXT," +
                     EX_ID    +" INTEGER," +
                     LEN_Time +" INTEGER," +
                     LEN_Kcal +" INTEGER," +
                "FOREIGN KEY("+ LN_ID  +") REFERENCES " + LogNormal_TABLE        + "(" + LN_ID  + ")," +
                "FOREIGN KEY("+ ME_ID  +") REFERENCES " + MealAndExercise_TABLE  + "(" + ME_ID  + ")," +
                "FOREIGN KEY("+ EX_ID +") REFERENCES " + Exert_TABLE             + "(" + EX_ID  + "))");
        Log.d("CREATE TABLE","Create Table LogExerciseNormal Successfully.");


        db.execSQL("CREATE TABLE " + LogExerciseMuscle_TABLE +
                "("+ LEM_ID   +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                     LM_ID    +" INTEGER," +
                     ME_ID    +" TEXT," +
                     EX_ID    +" INTEGER," +
                     LEM_Time +" INTEGER," +
                     LEM_Kcal +" INTEGER," +
                "FOREIGN KEY("+ LM_ID  +") REFERENCES " + LogMuscle_TABLE        + "(" + LM_ID  + ")," +
                "FOREIGN KEY("+ ME_ID  +") REFERENCES " + MealAndExercise_TABLE  + "(" + ME_ID  + ")," +
                "FOREIGN KEY("+ EX_ID  +") REFERENCES " + Exert_TABLE            + "(" + EX_ID + "))");
        Log.d("CREATE TABLE","Create Table LogExerciseMuscle Successfully.");


        db.execSQL("CREATE TABLE " + TotalCaloriesNormal_TABLE +
                "("+ TLN_ID   +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                     LN_ID    +" INTEGER," +
                     ME_ID    +" TEXT," +
                     TLN_Kcal +" INTEGER," +
                "FOREIGN KEY("+ LN_ID +") REFERENCES " + LogNormal_TABLE       + "(" + LN_ID + ")," +
                "FOREIGN KEY("+ ME_ID +") REFERENCES " + MealAndExercise_TABLE + "(" + ME_ID + "))");
        Log.d("CREATE TABLE","Create Table TotalCaloriesNormal Successfully.");


        db.execSQL("CREATE TABLE " + TotalCaloriesMuscle_TABLE +
                "("+ TLM_ID   +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                     LM_ID    +" INTEGER," +
                     ME_ID    +" TEXT," +
                     TLM_Kcal +" INTEGER," +
                     TLM_Fat     +" REAL," +
                     TLM_Protein +" REAL," +
                     TLM_Carb    +" REAL," +
                "FOREIGN KEY("+ LM_ID +") REFERENCES " + LogMuscle_TABLE       + "(" + LM_ID + ")," +
                "FOREIGN KEY("+ ME_ID +") REFERENCES " + MealAndExercise_TABLE + "(" + ME_ID + "))");
        Log.d("CREATE TABLE","Create Table TotalCaloriesMuscle Successfully.");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed, all data will be gone!!!

        // Create tables again
//        onCreate(db);

    }





    public boolean InsertActivities( String ac_values) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues ac = new ContentValues();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ac.put(HealthyFoodDB.AC_Date, dateFormat.format(new Date()));
        ac.put(HealthyFoodDB.AC_Values, ac_values);
     //   ac.put(HealthyFoodDB.AC_Flag, ac_flag);
        long result = db.insert(Activities_TABLE, null, ac);
        if (result == -1)
            return false;
        else
            return true;

    }

    public boolean InsertDataTypeUser(String tu_id, String tu_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues m = new ContentValues();
        m.put(HealthyFoodDB.DTU_ID, tu_id);
        m.put(HealthyFoodDB.DTU_Name, tu_name);
        long result = db.insert(DataTypeUser_TABLE, null, m);
        if (result == -1)
            return false;
        else
            return true;

    }

    public boolean InsertWeightData( String date, String weight) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(HealthyFoodDB.WE_Date, date);
        cv.put(HealthyFoodDB.WE_Weight,weight);
        long result = db.insert(Weight_TABLE, null, cv);
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean InsertBodyFatData( String date, String bodyfat) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(HealthyFoodDB.BF_Date, date);
        cv.put(HealthyFoodDB.BF_BodyFat,bodyfat);
        long result = db.insert(BodyFat_Table, null, cv);
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean InsertFfmiData( String date, String ffmi) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(HealthyFoodDB.FI_Date, date);
        cv.put(HealthyFoodDB.FI_FFMI,ffmi);
        long result = db.insert(FFMI_Table, null, cv);
        if (result == -1)
            return false;
        else
            return true;
    }


    public boolean InsertTypeMuscle(String tm_id, String tm_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues m = new ContentValues();
        m.put(HealthyFoodDB.TM_ID, tm_id);
        m.put(HealthyFoodDB.TM_Name, tm_name);
        long result = db.insert(TypeMuscle_TABLE, null, m);
        if (result == -1)
            return false;
        else
            return true;

    }

    public boolean InsertPlan(String p_id, String p_name, String p_values) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(HealthyFoodDB.P_ID, p_id);
        cv.put(HealthyFoodDB.P_Name, p_name);
        cv.put(HealthyFoodDB.P_Values, p_values);
        long result = db.insert(Plan_TABLE, null, cv);
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean InsertAllergy( String fa_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(HealthyFoodDB.U_ID, "1");
        cv.put(HealthyFoodDB.FA_Name, fa_name);
        cv.put(HealthyFoodDB.FA_Status,false);
        long result = db.insert(FoodAllergy_TABLE, null, cv);
        if (result == -1)
            return false;
        else
            return true;
    }


    public boolean InsertTypeGoal(String tg_id, String tg_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(HealthyFoodDB.TG_ID, tg_id);
        cv.put(HealthyFoodDB.TG_Name, tg_name);
        long result = db.insert(TypeGoal_Table, null, cv);
        if (result == -1)
            return false;
        else
            return true;
    }


    public boolean InsertHealthToday(String ht_id, String ht_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues fe = new ContentValues();
        fe.put(HealthyFoodDB.HT_ID, ht_id);
        fe.put(HealthyFoodDB.HT_Name, ht_name);
        long result = db.insert(Health_TABLE, null, fe);
        if (result == -1)
            return false;
        else
            return true;

    }


    public boolean InsertVegetarian(String vt_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues fe = new ContentValues();
        fe.put(HealthyFoodDB.VT_Name, vt_name);
        long result = db.insert(Vegetarian_TABLE, null, fe);
        if (result == -1)
            return false;
        else
            return true;

    }


    public boolean InsertExerciseToday(String et_id, String et_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(HealthyFoodDB.ET_ID, et_id);
        cv.put(HealthyFoodDB.ET_Name, et_name);
        long result = db.insert(ExerciseToday_TABLE, null, cv);
        if (result == -1)
            return false;
        else
            return true;
    }



    public boolean InsertMeal(String me_id, String me_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues m = new ContentValues();
        m.put(HealthyFoodDB.ME_ID, me_id);
        m.put(HealthyFoodDB.ME_Name, me_name);
        long result = db.insert(MealAndExercise_TABLE, null, m);
        if (result == -1)
            return false;
        else
            return true;

    }



    public boolean InsertFoodUnit(String fu_id, String fu_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues fu = new ContentValues();
        fu.put(HealthyFoodDB.FU_ID, fu_id);
        fu.put(HealthyFoodDB.FU_Name, fu_name);
        long result = db.insert(FoodUnit_TABLE, null, fu);
        if (result == -1)
            return false;
        else
            return true;

    }

    public boolean InsertTypeFood(String tf_id, String tf_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(HealthyFoodDB.TF_ID, tf_id);
        cv.put(HealthyFoodDB.TF_Name, tf_name);
        long result = db.insert(TypeFood_TABLE, null, cv);
        if (result == -1)
            return false;
        else
            return true;
    }
    public boolean InsertFoodNormal(String me_id,  String ht_id, String tf_id, String fn_name ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues fo = new ContentValues();
        fo.put(HealthyFoodDB.ME_ID, me_id);
        fo.put(HealthyFoodDB.HT_ID, ht_id);
        fo.put(HealthyFoodDB.TF_ID, tf_id);
        fo.put(HealthyFoodDB.FN_Name, fn_name);
        long result = db.insert(FoodNormal_TABLE, null, fo);
        if (result == -1)
            return false;
        else
            return true;

    }

    public boolean InsertFoodMus(String me_id,   String tf_id,   String fm_name ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues fo = new ContentValues();
        fo.put(HealthyFoodDB.ME_ID, me_id);
        fo.put(HealthyFoodDB.TF_ID, tf_id);
        fo.put(HealthyFoodDB.FM_Name, fm_name);
        long result = db.insert(FoodMuscle_TABLE, null, fo);
        if (result == -1)
            return false;
        else
            return true;

    }

    public boolean InsertIngredientNormal( String fn_id,   String in_name ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues fo = new ContentValues();
        fo.put(HealthyFoodDB.FN_ID, fn_id);
        fo.put(HealthyFoodDB.IN_Name, in_name);
        long result = db.insert(IngredientsNoraml_TABLE, null, fo);
        if (result == -1)
            return false;
        else
            return true;

    }


    public boolean InsertIngredientMuscle( String fm_id,   String im_name ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues fo = new ContentValues();
        fo.put(HealthyFoodDB.FM_ID, fm_id);
        fo.put(HealthyFoodDB.IM_Name, im_name);
        long result = db.insert(IngredientsMuscle_TABLE, null, fo);
        if (result == -1)
            return false;
        else
            return true;

    }


    public boolean InsertDetailFoodNormal(String fn_id,String fu_id, String fn_kcal ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues df = new ContentValues();
        df.put(HealthyFoodDB.FN_ID, fn_id);
        df.put(HealthyFoodDB.FU_ID, fu_id);
        df.put(HealthyFoodDB.FN_Kcal, fn_kcal);
        long result = db.insert(DetailFoodNormal_TABLE, null, df);
        if (result == -1)
            return false;
        else
            return true;

    }


    public boolean InsertDetailFoodMus(String fm_id,String fu_id, String fm_kcal ,  String fm_fat, String fm_Protein ,String fm_Carb) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues df = new ContentValues();
        df.put(HealthyFoodDB.FM_ID, fm_id);
        df.put(HealthyFoodDB.FU_ID, fu_id);
        df.put(HealthyFoodDB.FM_Kcal, fm_kcal);
        df.put(HealthyFoodDB.FM_Fat, fm_fat);
        df.put(HealthyFoodDB.FM_Protein, fm_Protein);
        df.put(HealthyFoodDB.FM_Carb, fm_Carb);

        long result = db.insert(DetailFoodMuscle_TABLE, null, df);
        if (result == -1)
            return false;
        else
            return true;

    }


    public boolean InsertExert( String ex_name, String ex_kcal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues ex = new ContentValues();
        ex.put(HealthyFoodDB.ME_ID, "ME07");
        ex.put(HealthyFoodDB.EX_Name, ex_name);
        ex.put(HealthyFoodDB.EX_Time, "1");
        ex.put(HealthyFoodDB.EX_Kcal, ex_kcal);
        long result = db.insert(Exert_TABLE, null, ex);
        if (result == -1)
            return false;
        else
            return true;

    }


    public boolean InsertLogDetailFoodNormal( String ln_id, String me_id,String fn_id, String fu_id,String amount,String kcal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues ex = new ContentValues();
        ex.put(HealthyFoodDB.LN_ID, ln_id);
        ex.put(HealthyFoodDB.ME_ID, me_id);
        ex.put(HealthyFoodDB.FN_ID, fn_id);
        ex.put(HealthyFoodDB.FU_ID, fu_id);
        ex.put(HealthyFoodDB.LFN_Amount, amount);
        ex.put(HealthyFoodDB.LFN_Kcal, kcal);
        long result = db.insert(LogFoodNormal_TABLE, null, ex);
        if (result == -1)
            return false;
        else
            return true;

    }



    //Data demo

    public  void  WeightDataEx(){
        InsertWeightData("2016-12-08","89");
        InsertWeightData("2016-12-07","88");
        InsertWeightData("2016-12-05","89");
        InsertWeightData("2016-12-04","89");
        InsertWeightData("2016-12-02","75");
    }

    public  void BodyFatDataEx(){
        InsertBodyFatData("2016-12-08","10");
        InsertBodyFatData("2016-12-07","11");
        InsertBodyFatData("2016-12-05","12");
        InsertBodyFatData("2016-12-04","13");
        InsertBodyFatData("2016-12-02","13");
    }

    public void FFMIDataEx(){
        InsertFfmiData("2016-12-08","19");
        InsertFfmiData("2016-12-07","19");
        InsertFfmiData("2016-12-05","19");
        InsertFfmiData("2016-12-04","19");
        InsertFfmiData("2016-12-02","15");
    }













    //Data


    public  void  TypeUserData(){
        InsertDataTypeUser("TU01", "ปกติ");
        InsertDataTypeUser("TU02", "กล้ามเนื้อิ");
        Log.d("INSERT DATA","Insert Data  DataTypeUser Successfully.");
    }

    public  void  TypeMuscleData(){
        InsertTypeMuscle("TM01", "ลดไขมัน");
        InsertTypeMuscle("TM02", "เพิ่มกล้ามเนื้อ");
        InsertTypeMuscle("TM03", "ควบคุมน้ำหนัก");
        Log.d("INSERT DATA","Insert Data  TypeMuscle Successfully.");
    }

    public void PlanData() {
        // InsertPlan(,,);
        InsertPlan("P01", "0.2 กิโลต่อสัปดาห์", "220");
        InsertPlan("P02", "0.3 กิโลต่อสัปดาห", "330");
        InsertPlan("P03", "0.4 กิโลต่อสัปดาห", "440");
        InsertPlan("P04", "0.5 กิโลต่อสัปดาห", "550");
        InsertPlan("P05", "ควบคุมน้ำหนัก", "0");
        Log.d("INSERT DATA","Insert Data  Plan Successfully.");
    }


    public void TypeGoalData() {
        //InsertTypeGoal(,);
        InsertTypeGoal("TG01", "ลดน้ำหนัก");
        InsertTypeGoal("TG02", "ควบคุมน้ำหนัก");
        InsertTypeGoal("TG03", "เพิ่มน้ำหนัก");
        Log.d("INSERT DATA","Insert Data  TypeGoal Successfully.");
    }

    public void HealthTodayData() {
        InsertHealthToday("HT01", "สบายดี");
        InsertHealthToday("HT02", "ป่วย");
        Log.d("INSERT DATA","Insert Data  HealthToday Successfully.");
    }

    public void ExerciseTodayData() {
        InsertExerciseToday("ET01", "ไม่ออกกำลังกาย");
        InsertExerciseToday("ET02", "ออกกำลังกาย");
        Log.d("INSERT DATA","Insert Data  ExerciseToday Successfully.");
    }


    public void MaelData() {
        InsertMeal("ME01", "มื้อเช้า");
        InsertMeal("ME02", "มื้อกลางวัน");
        InsertMeal("ME03", "มื้อเย็น");
        InsertMeal("ME04", "มื้อก่อนนอน");
        InsertMeal("ME05", "มื้อว่าง");
        InsertMeal("ME06", "มื้อก่อนและหลังเล่นเวท");
        InsertMeal("ME07", "ออกกำลังกาย");
        InsertMeal("ME08", "อาหารเช้า เที่ยง เย็น");
        InsertMeal("ME09", "กำหนดเอง");
        InsertMeal("ME10", "อาหาร");
        Log.d("INSERT DATA","Insert Data  Mael Successfully.");
    }

    public void FoodUnitData() {
        //InsertFoodUnit(, );
        InsertFoodUnit("FU001", "ถ้วย");
        InsertFoodUnit("FU002", "จาน");
        InsertFoodUnit("FU003", "น่อง");
        InsertFoodUnit("FU004", "ฟอง");
        InsertFoodUnit("FU005", "ผล");
        InsertFoodUnit("FU006", "ชิ้น");
        InsertFoodUnit("FU007", "แก้ว");
        InsertFoodUnit("FU008", "กล่อง");
        InsertFoodUnit("FU009", "กระป๋อง");
        InsertFoodUnit("FU010", "ห่อ");
        InsertFoodUnit("FU011", "ลูก");
        InsertFoodUnit("FU012", "คู่");
        InsertFoodUnit("FU013", "แผ่น");
        InsertFoodUnit("FU014", "อัน");
        InsertFoodUnit("FU015", "เม็ด");
        InsertFoodUnit("FU016", "โคน");
        InsertFoodUnit("FU017", "แพ");
        InsertFoodUnit("FU018", "ดอก");
        InsertFoodUnit("FU019", "ใบ");
        InsertFoodUnit("FU020", "ก้อน");
        InsertFoodUnit("FU021", "ชาม");
        InsertFoodUnit("FU022", "ตัวกลาง");
        InsertFoodUnit("FU023", "กรัม");
        Log.d("INSERT DATA","Insert Data  FoodUnit Successfully.");
    }

    public void TypeFoodData() {
        //InsertTypeFood(,);
        InsertTypeFood("TF01", "อาหารจานเดียว");
        InsertTypeFood("TF02", "ของหวาน");
        InsertTypeFood("TF03", "ผลไม้");
        InsertTypeFood("TF04", "เครื่องดื่ม");
        InsertTypeFood("TF05", "ซุปเปอร์มาร์เก็ต");
        InsertTypeFood("TF06", "อื่นๆ");
        Log.d("INSERT DATA","Insert Data  TypeFood Successfully.");
    }

    public  void  AllergyData(){
        InsertAllergy("ไม่มี");
        InsertAllergy("นม");
        InsertAllergy("ไข่");
        InsertAllergy("กุ้ง");
        InsertAllergy("ปู");

        InsertAllergy("ปลาหมึก");
        InsertAllergy("ลอบเตอร์");
        InsertAllergy("ปลา");
        InsertAllergy("ไก่");
        InsertAllergy("หมู");
        InsertAllergy("เนื้อ");
        InsertAllergy("ถั่วลิสง");
        InsertAllergy("ถั่วเหลือง");

        InsertAllergy("ข้าวสาลี");
        InsertAllergy("เม็ดมะม่วงหิมพานต์");
        InsertAllergy("อัลมอนด์");
     //   InsertAllergy("");
      //  InsertAllergy("");
    }

    public void VegetData(){
        InsertVegetarian("ไม่ทานมังสวิรัติ");
        InsertVegetarian("มังสวิรัติที่กินนมและไข่");
        InsertVegetarian("มังสวิรัติทดื่มนม");
        InsertVegetarian("มังสวิรัติที่กินไข่");
        InsertVegetarian("มังสวิรัติบริสุทธิ์");
    }


    public void FoodNormalData() {
        //InsertFood("","" ,"" ,"" );
        InsertFoodNormal("ME01", "HT01", "TF02", "กระทงทอง ไส้ไก่ ซอสขาว");
        InsertFoodNormal("ME01", "HT01", "TF02", "กระทงทองไส้ข้าวโพด");
        InsertFoodNormal("ME01", "HT01", "TF03", "กระท้อนทรงเครื่อง");
        InsertFoodNormal("ME01", "HT01", "TF01", "กระเพาะปลา");
        InsertFoodNormal("ME01", "HT01", "TF01", "กระเพาะปลาตุ๋นน้ำแดง");

        InsertFoodNormal("ME02", "HT01", "TF02", "กล้วยคลุกมะพร้าว");
        InsertFoodNormal("ME02", "HT01", "TF02", "กล้วยฉาบ");
        InsertFoodNormal("ME02", "HT01", "TF02", "กล้วยตาก");
        InsertFoodNormal("ME02", "HT01", "TF02", "กล้วยต้มจิ้มมะพร้าว");
        InsertFoodNormal("ME02", "HT02", "TF03", "กล้วยน้ำว้า");

        InsertFoodNormal("ME03", "HT01", "TF02", "กล้วยน้ำว้าปิ้ง");
        InsertFoodNormal("ME03", "HT01", "TF02", "กล้วยบวชชี");
        InsertFoodNormal("ME03", "HT01", "TF02", "กล้วยปิ้งชุบกะทิ");
        InsertFoodNormal("ME03", "HT02", "TF03", "กล้วยหอม");
        InsertFoodNormal("ME03", "HT01", "TF03", "กล้วยเล็บมือนาง");

        InsertFoodNormal("ME05", "HT02", "TF03", "กล้วยแขก");
        InsertFoodNormal("ME05", "HT02", "TF03", "กล้วยไข่");
        InsertFoodNormal("ME05", "HT01", "TF02", "กล้วยไข่เชื่อม");
        InsertFoodNormal("ME05", "HT01", "TF02", "กะละแม");
        InsertFoodNormal("ME05", "HT01", "TF05", "กะหรี่พัฟ");

        InsertFoodNormal("ME03", "HT01", "TF04", "กาแฟร้อน");
        InsertFoodNormal("ME03", "HT01", "TF04", "กาแฟเย็น");
        InsertFoodNormal("ME03", "HT01", "TF01", "กุนเชียงทอด");
        InsertFoodNormal("ME03", "HT01", "TF01", "กุ่ยช่าย(นึ่ง)");
        InsertFoodNormal("ME03", "HT01", "TF01", "กุ้งทอดกระเทียมพริกไทย");

        InsertFoodNormal("ME03", "HT01", "TF01", "กุ้งนางนึ่งนมสด");
        InsertFoodNormal("ME05", "HT02", "TF01", "กุ้งผัดพริกอ่อน");
        InsertFoodNormal("ME05", "HT01", "TF01", "กุ้งอบวุ้นเส้น");
        InsertFoodNormal("ME03", "HT01", "TF01", "ก๋วยจั๊บ");
        InsertFoodNormal("ME03", "HT01", "TF01", "ก๋วยจั๊บญวณ");



        InsertFoodNormal("ME01", "HT02", "TF06", "a");

        InsertFoodNormal("ME01", "HT02", "TF06", "ข้าวต้ม (ข้าวกล้อง)");
        InsertFoodNormal("ME02", "HT02", "TF06", "ข้าวต้ม (ข้าวขาว)");
        Log.d("INSERT DATA","Insert Data  FoodNormal Successfully.");

    }

    public  void IngredientsNormal(){
        InsertIngredientNormal("1"," แป้งสาลี");
        InsertIngredientNormal("1","ไข่");
        InsertIngredientNormal("1","กะทิ");
        InsertIngredientNormal("1","เกลือ");

        InsertIngredientNormal("2","แป้งสาลี");
        InsertIngredientNormal("2","เกลือ");
        InsertIngredientNormal("2","น้ำตาลทราย");
        InsertIngredientNormal("2","ไข่");
        InsertIngredientNormal("2","หมูสับ");
        InsertIngredientNormal("2","ข้าวโพด");
        InsertIngredientNormal("2","สับปะรด");
        InsertIngredientNormal("2","ผักชี");

        InsertIngredientNormal("3","น้ำตาลปี๊บ");
        InsertIngredientNormal("3","กะปิ");
        InsertIngredientNormal("3","ถั่วลิสง");
        InsertIngredientNormal("3","กุ้งแห้ง");
        InsertIngredientNormal("3","งาขาว");
        InsertIngredientNormal("3","มะนาว");

        InsertIngredientNormal("4","หน่อไม้");
        InsertIngredientNormal("4","กระเพาะปลา");
        InsertIngredientNormal("4","ไก่");
        InsertIngredientNormal("4","เห็ดหอม");
        InsertIngredientNormal("4","เกลือ");
        InsertIngredientNormal("4","น้ำตาลทราย");
        InsertIngredientNormal("4","แป้งข้าวโพด");
        InsertIngredientNormal("4","ซอสหอยนางรม");

        InsertIngredientNormal("5","กระเพาะปลา");
        InsertIngredientNormal("5","ขิง");
        InsertIngredientNormal("5","ไก่");
        InsertIngredientNormal("5","พริกไทย");
        InsertIngredientNormal("5","ซอสหอยนางรม");

        InsertIngredientNormal("6","กล้วย");
        InsertIngredientNormal("6","น้ำตาลทราย");
        InsertIngredientNormal("6","งาขาว");
        InsertIngredientNormal("6","เกลือ");

        InsertIngredientNormal("7","กล้วย");
        InsertIngredientNormal("7","น้ำตาลทราย");
        InsertIngredientNormal("7","เกลือ");

        InsertIngredientNormal("8","กล้วย");
        InsertIngredientNormal("8","เกลือ");

        InsertIngredientNormal("9","กล้วย");
        InsertIngredientNormal("9","น้ำตาลทราย");
        InsertIngredientNormal("9","งาขาว");
        InsertIngredientNormal("9","เกลือ");

        InsertIngredientNormal("10","กล้วย");
        InsertIngredientNormal("11","กล้วย");

        InsertIngredientNormal("12","กล้วย");
        InsertIngredientNormal("12","กะทิ");
        InsertIngredientNormal("12","น้ำตาลปี๊บ");
        InsertIngredientNormal("12","น้ำตาลทราย");
        InsertIngredientNormal("12","เกลือ");

        InsertIngredientNormal("13","กล้วย");
        InsertIngredientNormal("13","กะทิ");
        InsertIngredientNormal("13","น้ำตาลปี๊บ");
        InsertIngredientNormal("13","น้ำตาลทราย");
        InsertIngredientNormal("13","เกลือ");
        InsertIngredientNormal("13","เนย");

        InsertIngredientNormal("14","กล้วย");
        InsertIngredientNormal("15","กล้วย");
        InsertIngredientNormal("16","กล้วย");
        InsertIngredientNormal("17","กล้วย");

        InsertIngredientNormal("18","กล้วย");
        InsertIngredientNormal("18","น้ำตาลทราย");
        InsertIngredientNormal("18","กะทิ");
        InsertIngredientNormal("18","เกลือ");

        InsertIngredientNormal("19","แป้งเท้า");
        InsertIngredientNormal("19","แป้งข้าวเหนียว");
        InsertIngredientNormal("19","น้ำตาลปี๊บ");

        InsertIngredientNormal("20","น้ำตาลทราย");
        InsertIngredientNormal("20","เกลือ");
        InsertIngredientNormal("20","พริกไทย");
        InsertIngredientNormal("20","ผงกะหรี่");
        InsertIngredientNormal("20","เนย");
        InsertIngredientNormal("20","ไก่");
        InsertIngredientNormal("20","แป้งสาลี");

        InsertIngredientNormal("21","กาแฟ");
        InsertIngredientNormal("22","กาแฟ");

        InsertIngredientNormal("23","หมู");

        InsertIngredientNormal("24","แป้งมัน");
        InsertIngredientNormal("24","แป้งท้าว");
        InsertIngredientNormal("24","แป้งข้าวเหนียว");
        InsertIngredientNormal("24","เกลือ");
        InsertIngredientNormal("24","เบคกิ้งโซดา");
        InsertIngredientNormal("24","พริกไทย");

        InsertIngredientNormal("25","กุ้ง");

        InsertIngredientNormal("26","กุ้ง");
        InsertIngredientNormal("26","ไข่");
        InsertIngredientNormal("26","นม");
        InsertIngredientNormal("26","เกลือ");
        InsertIngredientNormal("26","พริกไทย");
        InsertIngredientNormal("26","ผักชี");

        InsertIngredientNormal("27","กุ้ง");
        InsertIngredientNormal("27","พริกหยวก");
        InsertIngredientNormal("27","ต้นหอม");
        InsertIngredientNormal("27","น้ำตาลทราย");
        InsertIngredientNormal("27","น้ำปลา");
        InsertIngredientNormal("27","กระเทียม");

        InsertIngredientNormal("28","กุ้ง");
        InsertIngredientNormal("28","กระเทียม");
        InsertIngredientNormal("28","พริกไทย");
        InsertIngredientNormal("28","น้ำตาล");

        InsertIngredientNormal("29","กระเทียม");
        InsertIngredientNormal("29","หมู");
        InsertIngredientNormal("29","เต้าหู้");
        InsertIngredientNormal("29","ไก่");
        InsertIngredientNormal("29","แป้งข้าวโพด");

        InsertIngredientNormal("30","หมู");
        InsertIngredientNormal("30","พริกไทย");
        InsertIngredientNormal("30","ผักชี");

        InsertIngredientNormal("31","กุ้ง");
        InsertIngredientNormal("31","ปลาหมึก");
        InsertIngredientNormal("31","นม");

//        InsertIngredientNormal("","");
        Log.d("INSERT DATA","Insert Data  InsertIngredientNormal Successfully.");
    }


    public void DetailFoodNormalData() {
        //(String fu_id ,String f_kcal)
        //InsertDetailFood("", "");
        InsertDetailFoodNormal("1","FU006", "93");
        InsertDetailFoodNormal("2","FU006", "72");
        InsertDetailFoodNormal("3","FU005", "205");
        InsertDetailFoodNormal("4","FU021", "150");
        InsertDetailFoodNormal("5","FU001", "225");

        InsertDetailFoodNormal("6","FU001", "100");
        InsertDetailFoodNormal("7","FU006", "22");
        InsertDetailFoodNormal("8","FU005", "120");
        InsertDetailFoodNormal("9","FU005", "90");
        InsertDetailFoodNormal("10","FU005", "60");

        InsertDetailFoodNormal("11","FU005", "90");
        InsertDetailFoodNormal("12","FU001", "230");
        InsertDetailFoodNormal("13","FU005", "145");
        InsertDetailFoodNormal("14","FU005", "120");
        InsertDetailFoodNormal("15","FU005", "30");

        InsertDetailFoodNormal("16","FU006", "85");
        InsertDetailFoodNormal("17","FU005", "60");
        InsertDetailFoodNormal("18","FU005", "89");
        InsertDetailFoodNormal("19","FU010", "65");
        InsertDetailFoodNormal("2","FU006", "190");

        InsertDetailFoodNormal("21","FU007", "55");
        InsertDetailFoodNormal("22","FU007", "115");
        InsertDetailFoodNormal("23","FU012", "240");
        InsertDetailFoodNormal("24","FU014", "140");
        InsertDetailFoodNormal("25","FU022", "259");

        InsertDetailFoodNormal("26","FU001", "185");
        InsertDetailFoodNormal("27","FU002", "235");
        InsertDetailFoodNormal("28","FU002", "300");
        InsertDetailFoodNormal("29","FU021", "240");
        InsertDetailFoodNormal("30","FU001", "235");

        InsertDetailFoodNormal("31","FU001", "235");

        InsertDetailFoodNormal("32","FU001", "120");
        InsertDetailFoodNormal("33","FU001", "120");

        Log.d("INSERT DATA","Insert Data  DetailFoodNormal Successfully.");
    }

    public void FoodMuscleData() {
        //InsertFood(, , , );
        InsertFoodMus("ME03",  "TF01", "กระเพาะปลา");
        InsertFoodMus("ME06",  "TF01", "ก๋วยเตี๋ยวเนื้อสับ");
        InsertFoodMus("ME06",  "TF01", "แกงเขียวหวานหมู");
        InsertFoodMus("ME02",  "TF01", "ข้าวขาหมู");
        InsertFoodMus("ME01",  "TF01", "ข้าวคลุกกะปิ");

        InsertFoodMus("ME03",  "TF01", "ข้าวหมูแดง");
        InsertFoodMus("ME04",  "TF01", "น้ำพริกอ่อง");
        InsertFoodMus("ME04",  "TF01", "ลาบเลือด");
        InsertFoodMus("ME02",  "TF02", "ขนมลูกชุบ");
        InsertFoodMus("ME02",  "TF02", "ทอดหยอด");


        InsertFoodMus("ME03",  "TF03", "กล้วยไข่");
        InsertFoodMus("ME02",  "TF03", "แตงโม");
        InsertFoodMus("ME04",  "TF03", "ฝรั่ง");
        InsertFoodMus("ME02",  "TF03", "ระกำ");
        InsertFoodMus("ME01",  "TF06", "ทุเรียนกวน");

        InsertFoodMus("ME01",  "TF03", "ไข่ไก่");
        InsertFoodMus("ME01",  "TF03", "ไข่เป็ด");
        InsertFoodMus("ME01",  "TF03", "ไข่นกกระทา");
        InsertFoodMus("ME01",  "TF03", "ยาคูลท์");
        InsertFoodMus("ME01",  "TF06", "นมสด ยูเอชที");


        InsertFoodMus("ME03",  "TF01", "a");
        Log.d("INSERT DATA","Insert Data  FoodMuscle Successfully.");

    }

    public void IngredientsMuscle(){
        InsertIngredientMuscle("1","หน่อไม้");
        InsertIngredientMuscle("1","กระเพาะปลา");
        InsertIngredientMuscle("1","ไก่");
        InsertIngredientMuscle("1","เห็ดหอม");
        InsertIngredientMuscle("1","เกลือ");
        InsertIngredientMuscle("1","น้ำตาลทราย");
        InsertIngredientMuscle("1","แป้งข้าวโพด");
        InsertIngredientMuscle("1","ซอสหอยนางรม");

        InsertIngredientMuscle("2","เนื้อ");

        InsertIngredientMuscle("3","หมู");

        InsertIngredientMuscle("4","หมู");

        InsertIngredientMuscle("5","หมู");
        InsertIngredientMuscle("5","แป้งสาลี");
        InsertIngredientMuscle("6","หมู");
        InsertIngredientMuscle("7","หมู");

        InsertIngredientMuscle("10","ไข่");

        InsertIngredientMuscle("16","ไข่");
        InsertIngredientMuscle("17","ไข่");
        InsertIngredientMuscle("18","ไข่");

        InsertIngredientMuscle("19","นม");
        InsertIngredientMuscle("20","นม");

        InsertIngredientMuscle("21","กุ้ง");
        InsertIngredientMuscle("21","นม");
        InsertIngredientMuscle("21","ไข่");

//        InsertIngredientMuscle("","");

        Log.d("INSERT DATA","Insert Data   InsertIngredientMuscle Successfully.");
    }

    public void DetailFoodMuscleData() {
        //(String fu_id  ,String f_kcal ,String f_fat ,String f_Protein,String f_Carb )
        InsertDetailFoodMus("1","FU023", " 83", "5.2", "7.3", "1.8");
        InsertDetailFoodMus("2", "FU023", " 112", "4.1", "3.5", "15.2");
        InsertDetailFoodMus("3", "FU023", " 93", "6.2", "6.3", "2.9");
        InsertDetailFoodMus("4", "FU023", " 112", "4.1", "3.5", "15.2");
        InsertDetailFoodMus("5", "FU023", " 152", "5.7", "6.7", "18.5");

        InsertDetailFoodMus("6","FU023", " 169", "5.6", "6.2", "23.5");
        InsertDetailFoodMus("7", "FU023", " 212", "19.6", "6.9", "2.1");
        InsertDetailFoodMus("8", "FU023", " 99", "1.2", "15.7", "6.4");
        InsertDetailFoodMus("9", "FU023", " 284", "6.6", "6.6", "49.5");
        InsertDetailFoodMus("10", "FU023", " 340", "13.4", "4.3", "50.5");

        InsertDetailFoodMus("11","FU023", " 147", "0.2", "1.5", "34.8");
        InsertDetailFoodMus("12", "FU023", " 8", "0", "0.3", "1.6");
        InsertDetailFoodMus("13", "FU023", " 43", "0.1", "0.6", "9.8");
        InsertDetailFoodMus("14", "FU023", " 50", "0.2", "0.6", "11.5");
        InsertDetailFoodMus("15", "FU023", " 340", "7.5", "3.2", "64.9");

        InsertDetailFoodMus("16","FU023", " 160", "11.7", "12.3", "1.4");
        InsertDetailFoodMus("17", "FU023", " 186", "14.3", "12.3", "2.1");
        InsertDetailFoodMus("18", "FU023", " 171", "12.0", "13.3", "2.4");
        InsertDetailFoodMus("19", "FU023", " 54", "0.1", "1.5", "11.8");
        InsertDetailFoodMus("20", "FU023", " 65", "3.9", "2.3", "4.1");

        InsertDetailFoodMus("20", "FU023", " 100", "4.1", "3.5", "13");
//        InsertDetailFood(, , , , );
        Log.d("INSERT DATA","Insert Data  DetailFoodMuscle Successfully.");
    }

    public void ExertData() {
        //InsertExert("","" );

        InsertExert("ยิงธนู","3.5" );
        InsertExert("แบดมินตัน (แข่งขัน)","7.0" );
        InsertExert("แบดมินตันเดี่ยวหรือคู่ผสม (ทั่วไป)","4.5" );
        InsertExert("บาสเกตบอล (เกมส์)","8.0" );
        InsertExert("บาสเกตบอล (ทั่วไป)","6.0" );

        InsertExert("บาสเกตบอล (รถเข็นคนพิการ)","6.5" );
        InsertExert("สนุกเกอร์","2.5" );
        InsertExert("โบว์ลิ่ง","3.0" );
        InsertExert("มวย (ทั่วไป)","12.0" );
        InsertExert("มวย (กระสอบ)","6.0" );

        InsertExert("มวย (ซ้อม)","9.0" );
        InsertExert("ปาเป้า","2.5" );
        InsertExert("ฟันดาบ","6.0" );
        InsertExert("ฟุตบอล (แข่งขัน)","9.0" );
        InsertExert("ฟุตบอล (ทั่วไป)","8.0" );

        InsertExert("ฟุตบอล (ฝึก)","4.0" );
        InsertExert("กอล์ฟ (ทั่วไป)","4.5" );
        InsertExert("กอล์ฟ (ขับรถ)","3.0" );
        InsertExert("กอล์ฟ (เดิน)","4.3" );
        InsertExert("กอล์ฟ (รถเข็น)","3.5" );

        InsertExert("ยิมนาสติก (ทั่วไป)","4.0" );
        InsertExert("แฮนด์บอล (ทั่วไป)","12.0" );
        InsertExert("แฮนด์บอล (ทีม)","8.0" );
        InsertExert("ขี่ม้า (ทั่วไป)","4.0" );
        InsertExert("ขี่ม้า (วิ่งเหยาะ)","6.5" );

        InsertExert("ขี่ม้า (เดิน)","2.5" );
        InsertExert("ยูโด","10.0" );
        InsertExert("คาราเต้","10.0" );
        InsertExert("เทควันโด","10.0" );
        InsertExert("มอเตอร์ครอส","4.0" );

        InsertExert("ปีนเขา","11.0" );
        InsertExert("ปีนผา (โรยตัว)","8.0" );
        InsertExert("กระโดดเชือก (เร็ว)","12.0" );
        InsertExert("กระโดดเชือก (ทั่วไป)","10.0" );
        InsertExert("กระโดดเชือก (ช้า)","8.0" );

        InsertExert("สเก็ตบอร์ด","5.0" );
        InsertExert("สเก็ตลูกกลิ้ง","7.0" );
        InsertExert("โรลเลอร์เบลด","12.5" );
        InsertExert("กระโดดร่ม","3.5" );
        InsertExert("ปิงปอง","4.0" );

        InsertExert("ไทเก็ก","4.0" );
        InsertExert("เทนนิส (ทั่วไป)","7.0" );
        InsertExert("เทนนิส (คู่)","5.0" );
        InsertExert("เทนนิส (เดี่ยว)","8.0" );
        InsertExert("วอลเลย์บอล","4.0" );

        InsertExert("วอลเลย์บอล (แข่งขัน)","8.0" );
        InsertExert("วอลเลย์บอล (ทีม)","3.0" );
        InsertExert("วอลเลย์บอล (ชายหาด)","8.0" );
        InsertExert("มวยปล้ำ","6.0" );
        InsertExert("วิ่งเหยาะๆ","7.0" );

        InsertExert("เดิน","2.0" );
        InsertExert("ปั่นจักรยาน","8.0" );
        InsertExert("วิ่งในน้ำ","8.0" );
        InsertExert("ว่ายน้ำ (ฝึก)","4.0" );
        InsertExert("ว่ายน้ำ ฟรีสไตล์ (เร็ว)","10.0" );

        InsertExert("ว่ายน้ำ ฟรีสไตล์ (ช้า)","7.0" );
        InsertExert("ว่ายน้ำ กรรเชียง","7.0" );
        InsertExert("ว่ายน้ำ ผีเสื้อ","11.0" );




        Log.d("INSERT DATA","Insert Data  Exert Successfully.");
    }



    public boolean InsertWeight(String weight) {
        SQLiteDatabase db = this.getWritableDatabase();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ContentValues we = new ContentValues();
        we.put(WE_Date, dateFormat.format(new Date()));
        we.put(WE_Weight, weight);
        long result = db.insert(Weight_TABLE, null, we);
        if (result == -1)
            return false;
        else
            return true;

    }

    public boolean InsertTypeUser(String tu_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ContentValues we = new ContentValues();
        we.put(TU_Date, dateFormat.format(new Date()));
        we.put(DTU_ID, tu_id);
        long result = db.insert(TypeUser_TABLE, null, we);
        if (result == -1)
            return false;
        else
            return true;

    }

    public boolean InsertBodyFat(String bodyfat) {
        SQLiteDatabase db = this.getWritableDatabase();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ContentValues we = new ContentValues();
        we.put(BF_Date, dateFormat.format(new Date()));
        we.put(BF_BodyFat, bodyfat);
        long result = db.insert(BodyFat_Table, null, we);
        if (result == -1)
            return false;
        else
            return true;

    }

    public boolean InsertFFMI(String ffmi) {
        SQLiteDatabase db = this.getWritableDatabase();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ContentValues we = new ContentValues();
        we.put(FI_Date, dateFormat.format(new Date()));
        we.put(FI_FFMI, ffmi);
        long result = db.insert(FFMI_Table, null, we);
        if (result == -1)
            return false;
        else
            return true;

    }

    public boolean InsertUser(String gender, String weight_id, String height, String age) {
        SQLiteDatabase db = this.getWritableDatabase();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ContentValues user = new ContentValues();
        user.put(U_ID,1);
        user.put(U_Date, dateFormat.format(new Date()));
        user.put(U_Gender, gender);
        user.put(WE_ID, weight_id);
        user.put(U_Height, height);
        user.put(U_Age, age);
        long result = db.insert(User_TABLE, null, user);
        if (result == -1)
            return false;
        else
            return true;

    }

    public boolean InsertUserMuscle( String u_id, String bf_id, String fi_id, String tm_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues user = new ContentValues();
        user.put(U_ID, u_id);
        user.put(BF_ID, bf_id);
        user.put(FI_ID,fi_id);
        user.put(TM_ID, tm_id);
        long result = db.insert(UserMuscle_TABLE, null, user);
        if (result == -1)
            return false;
        else
            return true;

    }

    public boolean InsertLogNormal(String u_id,String tdn_Totalkcal,String tdn_kcal) {
        SQLiteDatabase db = this.getWritableDatabase();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ContentValues we = new ContentValues();
        we.put(U_ID,u_id);
        we.put(TD_Date, dateFormat.format(new Date()));
        we.put(TDN_TotalKcal, tdn_Totalkcal);
        we.put(TDN_Kcal, tdn_kcal);
        long result = db.insert(LogNormal_TABLE, null, we);
        if (result == -1)
            return false;
        else
            return true;

    }


    public boolean InsertLogMuscle(String u_id,String um_id,
                                   String tdm_Totalkcal,String tdm_Totalfat,String tdm_Totalprotein, String tdm_Totalcarb,
                                   String tdm_kcal,String tdm_fat,String tdm_protein, String tdm_carb) {
        SQLiteDatabase db = this.getWritableDatabase();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ContentValues we = new ContentValues();
        we.put(U_ID, u_id);
        we.put(UM_ID, um_id);
        we.put(TD_Date, dateFormat.format(new Date()));
        we.put(TDM_TotalKcal, tdm_Totalkcal);
        we.put(TDM_TotalFat, tdm_Totalfat);
        we.put(TDM_TotalProtein, tdm_Totalprotein);
        we.put(TDM_TotalCarb, tdm_Totalcarb);
        we.put(TDM_Kcal, tdm_kcal);
        we.put(TDM_Fat, tdm_fat);
        we.put(TDM_Protein, tdm_protein);
        we.put(TDM_Carb, tdm_carb);
        long result = db.insert(LogMuscle_TABLE, null, we);
        if (result == -1)
            return false;
        else
            return true;

    }

    public boolean InsertLogFoodNormal(String ln_id, String me_id, String fn_id, String fu_id, String amount, String kcal) {
        SQLiteDatabase db = this.getWritableDatabase();
        // SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ContentValues we = new ContentValues();
        we.put(LN_ID, ln_id);
        we.put(ME_ID, me_id);
        we.put(FN_ID, fn_id);
        we.put(FU_ID, fu_id);
        we.put(LFN_Amount, amount);
        we.put(LFN_Kcal, kcal);
        long result = db.insert(LogFoodNormal_TABLE, null, we);
        if (result == -1)
            return false;
        else
            return true;

    }

    public boolean InsertLogFoodMuscle(String lm_id,String me_id, String fm_id, String fu_id,
                                        String amount, String kcal, String fat, String protein, String carb) {
        SQLiteDatabase db = this.getWritableDatabase();
        // SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ContentValues we = new ContentValues();
        we.put(LM_ID, lm_id);
        we.put(ME_ID, me_id);
        we.put(FM_ID, fm_id);
        we.put(FU_ID, fu_id);
        we.put(LFM_Amount, amount);
        we.put(LFM_Kcal, kcal);
        we.put(LFM_Fat, fat);
        we.put(LFM_Protein, protein);
        we.put(LFM_Carb, carb);
        long result = db.insert(LogFoodMuscle_TABLE, null, we);
        if (result == -1)
            return false;
        else
            return true;

    }


    public boolean InsertLogExerciseNormal(String ln_id,String me_id, String ex_id, String len_time, String len_kcal) {
        SQLiteDatabase db = this.getWritableDatabase();
        // SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ContentValues we = new ContentValues();
        we.put(LN_ID, ln_id);
        we.put(ME_ID, me_id);
        we.put(EX_ID, ex_id);
        we.put(LEN_Time, len_time);
        we.put(LEN_Kcal, len_kcal);
        long result = db.insert(LogExerciseNormal_TABLE, null, we);
        if (result == -1)
            return false;
        else
            return true;

    }


    public boolean InsertLogExerciseMuscle(String lm_id,String me_id, String ex_id, String lem_time, String lem_kcal) {
        SQLiteDatabase db = this.getWritableDatabase();
        // SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ContentValues we = new ContentValues();
        we.put(LM_ID, lm_id);
        we.put(ME_ID, me_id);
        we.put(EX_ID, ex_id);
        we.put(LEM_Time, lem_time);
        we.put(LEM_Kcal, lem_kcal);
        long result = db.insert(LogExerciseMuscle_TABLE, null, we);
        if (result == -1)
            return false;
        else
            return true;

    }

    public boolean InsertTotalCalNormal(String ln_id,String me_id, String tln_kcal) {
        SQLiteDatabase db = this.getWritableDatabase();
        // SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ContentValues we = new ContentValues();
        we.put(LN_ID, ln_id);
        we.put(ME_ID, me_id);
        we.put(TLN_Kcal, tln_kcal);
        long result = db.insert(TotalCaloriesNormal_TABLE, null, we);
        if (result == -1)
            return false;
        else
            return true;

    }

    public boolean InsertTotalCalMuscle(String lm_id,String me_id, String tlm_kcal, String tlm_fat, String tlm_protein , String tlm_carb) {
        SQLiteDatabase db = this.getWritableDatabase();
        // SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ContentValues we = new ContentValues();
        we.put(LM_ID, lm_id);
        we.put(ME_ID, me_id);
        we.put(TLM_Kcal, tlm_kcal);
        we.put(TLM_Fat, tlm_fat);
        we.put(TLM_Protein, tlm_protein);
        we.put(TLM_Carb, tlm_carb);
        long result = db.insert(TotalCaloriesMuscle_TABLE, null, we);
        if (result == -1)
            return false;
        else
            return true;

    }

//    public boolean InsertLogExercise(String m_id, String ex_id, String time, String kcal) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        //  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        ContentValues we = new ContentValues();
//        we.put(ME_ID, m_id);
//        we.put(EX_ID, ex_id);
//        we.put(DE_Time, time);
//        we.put(DE_Kcal, kcal);
//        long result = db.insert(DetailLogExercise_TABLE, null, we);
//        if (result == -1)
//            return false;
//        else
//            return true;
//
//    }

//    public boolean InsertTotalCalories(String m_id, String kcal) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        ContentValues we = new ContentValues();
//        we.put(TD_Date, dateFormat.format(new Date()));
//        we.put(M_ID, m_id);
//        we.put(TL_Kcal, kcal);
//        long result = db.insert(TotalCalories_TABLE, null, we);
//        if (result == -1)
//            return false;
//        else
//            return true;
//
//    }

    public boolean InsertNormalToday(String u_id,String ht_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ContentValues we = new ContentValues();
        we.put(U_ID, u_id);
        we.put(TD_Date, dateFormat.format(new Date()));
        we.put(HT_ID, ht_id);
        long result = db.insert(NormalToday_TABLE, null, we);
        if (result == -1)
            return false;
        else
            return true;

    }

    public boolean InsertMuscleToday(String u_id,String um_id ,String et_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ContentValues we = new ContentValues();
        we.put(U_ID, u_id);
        we.put(UM_ID, um_id);
        we.put(TD_Date, dateFormat.format(new Date()));
        we.put(ET_ID, et_id);
        long result = db.insert(MuscleToday_TABLE, null, we);
        if (result == -1)
            return false;
        else
            return true;

    }



    public Boolean InsertCustomFoodNormal(String fn_name,  String tf_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(FN_Name, fn_name);
        cv.put(TF_ID,tf_id);
        cv.put(HT_ID, "HT01");
        cv.put(ME_ID, "ME09");
        long result = db.insert(FoodNormal_TABLE, null, cv);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Boolean InsertCustomFoodMuscle(String fm_name,  String tf_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(FM_Name, fm_name);
        cv.put(TF_ID,tf_id);
        cv.put(ME_ID, "ME09");
        long result = db.insert(FoodMuscle_TABLE, null, cv);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Boolean InsertCustomDetailFoodNormal(String fn_id ,String fu_id,  String fn_kcal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues df = new ContentValues();
        df.put(HealthyFoodDB.FN_ID, fn_id);
        df.put(HealthyFoodDB.FU_ID, fu_id);
        df.put(HealthyFoodDB.FN_Kcal, fn_kcal);
        long result = db.insert(DetailFoodNormal_TABLE, null, df);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Boolean InsertCustomDetailFoodMuscle(String fm_id ,String fu_id, String fm_kcal, String fm_fat, String fm_Protein, String fm_Carb) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues df = new ContentValues();
        df.put(HealthyFoodDB.FM_ID, fm_id);
        df.put(HealthyFoodDB.FU_ID, fu_id);
        df.put(HealthyFoodDB.FM_Kcal, fm_kcal);
        df.put(HealthyFoodDB.FM_Fat, fm_fat);
        df.put(HealthyFoodDB.FM_Protein, fm_Protein);
        df.put(HealthyFoodDB.FM_Carb, fm_Carb);
        long result = db.insert(DetailFoodMuscle_TABLE, null, df);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Boolean InsertCustomExert( String ex_name, String ex_time , String ex_kcal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ME_ID, "ME09");
        cv.put(EX_Name, ex_name);
        cv.put(EX_Time,ex_time);
        cv.put(EX_Kcal, ex_kcal);
        long result = db.insert(Exert_TABLE, null, cv);
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean InsertGoalNormal(String u_id,String tg_id, String p_id , String gn_weight) {
        SQLiteDatabase db = this.getWritableDatabase();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ContentValues cv = new ContentValues();
        cv.put(GN_Date, dateFormat.format(new Date()));
        cv.put(U_ID,u_id);
        cv.put(TG_ID, tg_id);
        cv.put(P_ID, p_id);
        cv.put(GN_Weight, gn_weight);
        long result = db.insert(GoalNormal_Table, null, cv);
        if (result == -1)
            return false;
        else
            return true;

    }

    public boolean InsertGoalMuscle(String u_id, String um_id, String p_id, String gm_weight, String gm_bodyfat) {
        SQLiteDatabase db = this.getWritableDatabase();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ContentValues cv = new ContentValues();
        cv.put(GM_Date, dateFormat.format(new Date()));
        cv.put(U_ID, u_id);
        cv.put(UM_ID, um_id);
        cv.put(P_ID, p_id);
        cv.put(GM_Weight, gm_weight);
        cv.put(GM_Bodyfat, gm_bodyfat);
        long result = db.insert(GoalMuscle_Table, null, cv);
        if (result == -1)
            return false;
        else
            return true;

    }



    //get



    public Cursor getMaxWeight() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT  MAX(" + WE_ID + ") , " + WE_ID + " , " + WE_Date + ","+ WE_Weight +" FROM " + Weight_TABLE, null);
        if (res != null) {
            res.moveToFirst();
        }
        return res;
    }



    public Cursor getMaxActivities() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT  MAX(" + AC_ID + ") , " + AC_ID +", " + AC_Date + ","+ AC_Values + " FROM " + Activities_TABLE, null);
        if (res != null) {
            res.moveToFirst();
        }
        return res;
    }

    public Cursor getMaxTypeUser() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT  MAX(" + TU_ID + ") , " + TU_ID +", " + TU_Date + ","+ DTU_ID + " FROM " + TypeUser_TABLE, null);
        if (res != null) {
            res.moveToFirst();
        }
        return res;
    }
    public Cursor getMaxGoalNormal() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT  MAX(" + GN_ID + ") ," + GN_ID + "," + U_ID + ", "+ TG_ID +","+ P_ID + " , " + GN_Date + "," + GN_Weight + " FROM " + GoalNormal_Table, null);
        if (res != null) {
            res.moveToFirst();
        }
        return res;
    }

    public Cursor getMaxGoalMuscle() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT  MAX(" + GM_ID + ") , " + GM_ID + "," + U_ID + ","+ UM_ID + ","+ P_ID +" , " + GM_Date + ", " + GM_Weight + ","+ GM_Bodyfat + " FROM " + GoalMuscle_Table, null);
        if (res != null) {
            res.moveToFirst();
        }
        return res;
    }

    public Cursor getMaxBodyfat() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT  MAX(" + BF_ID + ") , " + BF_ID + " , " + BF_Date + ","+ BF_BodyFat +" FROM " + BodyFat_Table, null);
        if (res != null) {
            res.moveToFirst();
        }
        return res;
    }

    public Cursor getMaxFFMI() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT  MAX(" + FI_ID + ") , " + FI_ID + ", " + FI_Date + ","+ FI_FFMI +" FROM " + FFMI_Table, null);
        if (res != null) {
            res.moveToFirst();
        }
        return res;
    }
    public Cursor getMaxUser() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT  MAX(" + U_ID + ")" + U_Date + " FROM " + User_TABLE, null);
        if (res != null) {
            res.moveToFirst();
        }
        return res;
    }

    public Cursor getMaxMuscleToday() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor tg = db.rawQuery("SELECT MAX(" + MT_ID + ") , " + ET_ID + " FROM " + MuscleToday_TABLE, null);
        if (tg != null) {
            tg.moveToFirst();
        }
        return tg;
    }

    public Cursor getMaxNormalToday() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor tg = db.rawQuery("SELECT MAX(" + NT_ID + ") , " + HT_ID + " FROM " + NormalToday_TABLE, null);
        if (tg != null) {
            tg.moveToFirst();
        }
        return tg;
    }

    public Cursor getMaxUserMuscle() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT  MAX(" + UM_ID + ") , "+ UM_ID + " , " + U_ID + ","+ BF_ID +" , " + FI_ID + "  , " + TM_ID + " FROM " + UserMuscle_TABLE, null);
        if (res != null) {
            res.moveToFirst();
        }
        return res;
    }

    public Cursor getMaxLogNormal() {
        SQLiteDatabase db = this.getReadableDatabase();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Log.d("date","dateToo");
        String m = dateFormat.format(new Date());
        Cursor res = db.rawQuery("SELECT  MAX(" + LN_ID + ")," + LN_ID + "," + U_ID + "," + TD_Date + ","
                + TDN_TotalKcal + " , " + TDN_Kcal + " FROM " + LogNormal_TABLE  + " WHERE " + TD_Date + "= ?",new String[] {m});
        if (res != null) {
            res.moveToFirst();
        }
        return res;
    }

    public Cursor getMaxLogMuscle() {
        SQLiteDatabase db = this.getReadableDatabase();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Log.d("date","dateToo");
        String m = dateFormat.format(new Date());
        Cursor res = db.rawQuery("SELECT  MAX(" + LM_ID + ")," + LM_ID + "," + U_ID +"," + UM_ID +"," + TD_Date + "," + TDM_Kcal + "," + TDM_Fat + "," + TDM_Protein + "," + TDM_Carb + "," + TDM_TotalKcal + "  FROM " + LogMuscle_TABLE  + " WHERE " + TD_Date + "= ?",new String[] {m});
        if (res != null) {
            res.moveToFirst();
        }
        return res;
    }
    public Cursor getMaxLogExertNormal() {
        SQLiteDatabase db = this.getReadableDatabase();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Log.d("date","dateToo");
        String m = dateFormat.format(new Date());
        Cursor res = db.rawQuery("SELECT  MAX(" + LEN_ID + ")," + LEN_ID + "," + LN_ID + "," + ME_ID + "," + EX_ID + "," + LEN_Time + "," + LEN_Kcal + " FROM " + LogExerciseNormal_TABLE  + " WHERE " + TD_Date + "= ?",new String[] {m});
        if (res != null) {
            res.moveToFirst();
        }
        return res;
    }


    public Cursor getMaxLogExertMuscle() {
        SQLiteDatabase db = this.getReadableDatabase();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Log.d("date","dateToo");
        String m = dateFormat.format(new Date());
        Cursor res = db.rawQuery("SELECT  MAX(" + LEM_ID + ")," + LEM_ID + "," + LM_ID +"," + ME_ID +"," + EX_ID + "," + LEM_Time + "," + LEM_Kcal + "  FROM " + LogExerciseMuscle_TABLE  + " WHERE " + TD_Date + "= ?",new String[] {m});
        if (res != null) {
            res.moveToFirst();
        }
        return res;
    }

    public Cursor getMaxExert() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT  MAX(" + EX_ID + ") , " + EX_ID + " FROM " + Exert_TABLE, null);
        if (res != null) {
            res.moveToFirst();
        }
        return res;
    }

    public Cursor getWeight() {
        SQLiteDatabase db = this.getReadableDatabase();
        //String[] id = {date};
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Log.d("date","dateToo");
        String m = dateFormat.format(new Date());
        Cursor res = db.rawQuery("SELECT " + WE_ID + " , " + WE_Weight + " FROM " + Weight_TABLE + " WHERE " + WE_Date + "= ?",new String[] {m});
        Log.d("cursor", "Cursor = " + res.getCount());
        if (res != null) {
            res.moveToFirst();
        }
        return res;
    }

    public Cursor getLogMuscle(String date) {
        SQLiteDatabase db = this.getReadableDatabase();
        //String[] id = {date};
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Log.d("date","dateToo");
        String m = dateFormat.format(new Date());
        Cursor res = db.rawQuery("SELECT * FROM " + LogMuscle_TABLE + " WHERE " + TD_Date + "= ?",new String[] {date});
        Log.d("cursor", "Cursor = " + res.getCount());
        if (res != null) {
            res.moveToFirst();
        }
        return res;
    }

    public Cursor getLogNormal(String date) {
        SQLiteDatabase db = this.getReadableDatabase();
        //String[] id = {date};
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Log.d("date","dateToo");
        String m = dateFormat.format(new Date());
        Cursor res = db.rawQuery("SELECT * FROM " + LogNormal_TABLE + " WHERE " + TD_Date + "= ?",new String[] {date});
        Log.d("cursor", "Cursor = " + res.getCount());
        if (res != null) {
            res.moveToFirst();
        }
        return res;
    }
    public Cursor getLogFoodMuscle(String lfm_id , String lm_id , String me_id ,String fm_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + LogFoodMuscle_TABLE + " WHERE " + LFM_ID + "= ? AND " + LM_ID + "= ? AND "
                + ME_ID + "= ? AND " + FM_ID + "= ? ",new String[] {lfm_id,lm_id,me_id,fm_id});
        if (res != null) {
            res.moveToFirst();
        }
        return res;
    }

    public Cursor getLogFoodNormal(String lfn_id , String ln_id , String me_id ,String fn_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + LogFoodNormal_TABLE + " WHERE " + LFN_ID + "= ? AND " + LN_ID + "= ? AND "
                + ME_ID + "= ? AND " + FN_ID + "= ? ",new String[] {lfn_id,ln_id,me_id,fn_id});
        if (res != null) {
            res.moveToFirst();
        }
        return res;
    }

    public Cursor getLogExertMuscle(String lem_id , String lm_id , String me_id ,String ex_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + LogExerciseMuscle_TABLE + " WHERE " + LEM_ID + "= ? AND " + LM_ID + "= ? AND "
                + ME_ID + "= ? AND " + EX_ID + "= ? ",new String[] {lem_id,lm_id,me_id,ex_id});
        if (res != null) {
            res.moveToFirst();
        }
        return res;
    }

    public Cursor getLogExertNormal(String len_id , String ln_id , String me_id ,String ex_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + LogExerciseNormal_TABLE + " WHERE " + LEN_ID + "= ? AND " + LN_ID + "= ? AND "
                + ME_ID + "= ? AND " + EX_ID + "= ? ",new String[] {len_id,ln_id,me_id,ex_id});
        if (res != null) {
            res.moveToFirst();
        }
        return res;
    }


    public Cursor getTotalCalMuscle(String lm_id , String me_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TotalCaloriesMuscle_TABLE + " WHERE " + LM_ID + "= ? AND " +
                ME_ID + "= ? ",new String[] {lm_id,me_id});
        if (res != null) {
            res.moveToFirst();
        }
        return res;
    }

    public Cursor getTotalCalNormal(String ln_id , String me_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TotalCaloriesNormal_TABLE + " WHERE " + LN_ID + "= ? AND " +
                ME_ID + "= ? ",new String[] {ln_id,me_id});
        if (res != null) {
            res.moveToFirst();
        }
        return res;
    }


    public Cursor getLogMuscleToday() {
        SQLiteDatabase db = this.getReadableDatabase();
        //String[] id = {date};
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Log.d("date","dateToo");
        String m = dateFormat.format(new Date());
        Cursor res = db.rawQuery("SELECT * FROM " + LogMuscle_TABLE + " WHERE " + TD_Date + "= ?",new String[] {m});
        Log.d("cursor", "Cursor = " + res.getCount());
        if (res != null) {
            res.moveToFirst();
        }
        return res;
    }

    public Cursor getLogNormalToday() {
        SQLiteDatabase db = this.getReadableDatabase();
        //String[] id = {date};
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Log.d("date","dateToo");
        String m = dateFormat.format(new Date());
        Cursor res = db.rawQuery("SELECT * FROM " + LogNormal_TABLE + " WHERE " + TD_Date + "= ?",new String[] {m});
        Log.d("cursor", "Cursor = " + res.getCount());
        if (res != null) {
            res.moveToFirst();
        }
        return res;
    }

    public Cursor getNormalToday() {
        SQLiteDatabase db = this.getReadableDatabase();
        //String[] id = {date};
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String m = dateFormat.format(new Date());
        Cursor res = db.rawQuery("SELECT * FROM " + NormalToday_TABLE + " WHERE " + TD_Date + "= ?",new String[] {m});
        Log.d("cursor", "Cursor = " + res.getCount());
        if (res != null) {
            res.moveToFirst();
        }
        return res;
    }

    public Cursor getUserMuscle(String um_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] um = {um_id};
        Cursor unit = db.rawQuery("SELECT * FROM " + UserMuscle_TABLE + " WHERE " + UM_ID + "= ?", um);
        if (unit != null) {
            unit.moveToFirst();
        }
        return unit;
    }

    public Cursor getActivities(String ac_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] ac = {ac_id};
        Cursor unit = db.rawQuery("SELECT * FROM " + Activities_TABLE + " WHERE " + AC_ID + "= ?", ac);
        if (unit != null) {
            unit.moveToFirst();
        }
        return unit;
    }

    public Cursor getDateWeight() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT  " + WE_Date + ","+ WE_Weight +" FROM " + Weight_TABLE, null);
        if (res != null) {
            res.moveToFirst();
        }
        return res;
    }


    public Cursor getUser() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT  * FROM " + User_TABLE, null);
        if (res != null) {
            res.moveToFirst();
        }
        return res;
    }

    public Cursor getFoodAllergy() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT  * FROM " + FoodAllergy_TABLE, null);
        if (res != null) {
            res.moveToFirst();
        }
        return res;
    }




    public Cursor getFoodNormakByName(String inputText) {
        SQLiteDatabase db = this.getReadableDatabase();
        Log.w(TAG, inputText);
        Cursor food;
        if (inputText == null || inputText.length() == 0) {
            food = db.rawQuery("SELECT " + FN_ID + " as _id, " + FN_Name + " FROM " + FoodNormal_TABLE , null);

        } else {
            food = db.rawQuery("SELECT " + FN_ID + " as _id, " + FN_Name + " FROM " + FoodNormal_TABLE +
                    " WHERE  " + FN_Name + " LIKE '%" + inputText + "%'",null);
        }
        if (food != null) {
            food.moveToFirst();
        }
        return food;
    }


    public Cursor getFoodMuscleByName(String inputText) {
        SQLiteDatabase db = this.getReadableDatabase();
        Log.w(TAG, inputText);
        Cursor food;
        if (inputText == null || inputText.length() == 0) {
            food = db.rawQuery("SELECT " + FM_ID + " as _id, " + FM_Name + " FROM " + FoodMuscle_TABLE , null);

        } else {
            food = db.rawQuery("SELECT " + FM_ID + " as _id, " + FM_Name + " FROM " + FoodMuscle_TABLE +
                    " WHERE " + FM_Name + " LIKE '%" + inputText + "%'",null);
        }
        if (food != null) {
            food.moveToFirst();
        }
        return food;
    }

    public Cursor getExerciseByName(String inputText) {
        SQLiteDatabase db = this.getReadableDatabase();
        Log.w(TAG, inputText);
        Cursor food;
        if (inputText == null || inputText.length() == 0) {
            food = db.rawQuery("SELECT " + EX_ID + " as _id, " + EX_Name + " FROM " + Exert_TABLE , null);

        } else {
            food = db.rawQuery("SELECT " + EX_ID + " as _id, " + EX_Name + " FROM " + Exert_TABLE +
                    " WHERE " + EX_Name + " LIKE '%" + inputText + "%'",null);
        }
        if (food != null) {
            food.moveToFirst();
        }
        return food;
    }



    public Cursor getAllFoodNormal(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor food = db.rawQuery("SELECT " + FN_ID + " as _id, " + FN_Name + " FROM " + FoodNormal_TABLE , null);
        if (food != null && !food.isClosed()) {
            food.moveToFirst();
        }
        return food;
    }

    public Cursor getAllAllergy(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor food = db.rawQuery("SELECT " + FA_ID + " as _id, " + FA_Name + "," + FA_Status + " FROM " + FoodAllergy_TABLE , null);
        if (food != null && !food.isClosed()) {
            food.moveToFirst();
        }
        return food;
    }

    public Cursor getAllergyVeget(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor food = db.rawQuery("SELECT " + FA_ID + " as _id, " + FA_Name + "," + FA_Status + " FROM " + FoodAllergy_TABLE + " WHERE " + FA_ID + " BETWEEN 12 AND 16", null);
        if (food != null && !food.isClosed()) {
            food.moveToFirst();
        }
        return food;
    }
    public Cursor getAllFoodAllergy(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor food = db.rawQuery("SELECT " + FA_ID + " as _id, " + FA_Name + "," + FA_Status + " FROM " + FoodAllergy_TABLE + " WHERE " + FA_Status + "= ?" ,new String[] {"1"});
        if (food != null && !food.isClosed()) {
            food.moveToFirst();
        }
        return food;
    }

    public Cursor getAllFoodAllerNormal(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor food = db.rawQuery("SELECT " + IN_ID + " as _id, " + FN_ID + "," + IN_Name + " FROM " + IngredientsNoraml_TABLE + " WHERE " + IN_Name + "= ?" ,new String[] {name});
        if (food != null && !food.isClosed()) {
            food.moveToFirst();
        }
        return food;
    }

    public Cursor getAllFoodNormal(String tf_id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor food = db.rawQuery("SELECT " + FN_ID + " as _id, " + FN_ID + "," + FN_Name + " FROM "
                + FoodNormal_TABLE + " WHERE " + TF_ID + "= ?" ,new String[] {tf_id});
        if (food != null && !food.isClosed()) {
            food.moveToFirst();
        }
        return food;
    }


    public Cursor getAllFoodMuscle(String tf_id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor food = db.rawQuery("SELECT " + FM_ID + " as _id, " + FM_ID + "," + FM_Name + " FROM "
                + FoodNormal_TABLE + " WHERE " + TF_ID + "= ?" ,new String[] {tf_id});
        if (food != null && !food.isClosed()) {
            food.moveToFirst();
        }
        return food;
    }

    public Cursor getAllnameFoodNormal(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor food = db.rawQuery("SELECT * FROM " + FoodNormal_TABLE + " WHERE " + FN_ID + " NOT IN ( SELECT "
                 + FN_ID + " FROM " + IngredientsNoraml_TABLE +
                " WHERE " + IN_Name + "= ? )" ,new String[] {name});
        if (food != null && !food.isClosed()) {
            food.moveToFirst();
        }
        return food;
    }

    public Cursor getAllnameFoodMuscle(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor food = db.rawQuery("SELECT * FROM " + FoodMuscle_TABLE + " WHERE " + FM_ID + " NOT IN ( SELECT "
                + FM_ID + " FROM " + IngredientsMuscle_TABLE +
                " WHERE " + IM_Name + "= ? )" ,new String[] {name});
        if (food != null && !food.isClosed()) {
            food.moveToFirst();
        }
        return food;
    }

    public Cursor getAllWeight(){
        SQLiteDatabase db = this.getReadableDatabase();
        // String[] fa_id = {"1"};
        Cursor food = db.rawQuery("SELECT " + WE_ID + " as _id, " + WE_Date + "," + WE_Weight + " FROM " + Weight_TABLE ,null);
        if (food != null && !food.isClosed()) {
            food.moveToFirst();
        }
        return food;
    }

    public Cursor getAllBodyFat(){
        SQLiteDatabase db = this.getReadableDatabase();
        // String[] fa_id = {"1"};
        Cursor food = db.rawQuery("SELECT " + BF_ID + " as _id, " + BF_Date + "," + BF_BodyFat + " FROM " + BodyFat_Table ,null);
        if (food != null && !food.isClosed()) {
            food.moveToFirst();
        }
        return food;
    }


    public Cursor getAllFFMI(){
        SQLiteDatabase db = this.getReadableDatabase();
        // String[] fa_id = {"1"};
        Cursor food = db.rawQuery("SELECT " + FI_ID + " as _id, " + FI_Date + "," + FI_FFMI + " FROM " + FFMI_Table ,null);
        if (food != null && !food.isClosed()) {
            food.moveToFirst();
        }
        return food;
    }

    public  Cursor getAllFoodMuscleAllergy(String me_id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor food = db.rawQuery("SELECT " + FM_ID + " as _id, " + FM_Name + " FROM " + FoodMuscle_TABLE + " WHERE " + ME_ID + "= ? AND " + FM_ID + " NOT IN ( SELECT "
                + FM_ID + " FROM " + IngredientsMuscle_TABLE +
                " WHERE " + IM_Name + " IN ( SELECT " + FA_Name +" FROM " + FoodAllergy_TABLE + " WHERE "
                + FA_Status + "= ?))" ,new String[] {me_id,"1"});
        if (food != null && !food.isClosed()) {
            food.moveToFirst();
        }
        return food;
    }

    public  Cursor getAllFoodNormalAllergy(String ht_id,String me_id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor food = db.rawQuery("SELECT " + FN_ID + " as _id, " + FN_Name + " FROM " + FoodNormal_TABLE + " WHERE " + HT_ID + "= ? AND " + ME_ID + " = ? AND " + FN_ID +
                " NOT IN ( SELECT " + FN_ID + " FROM " + IngredientsNoraml_TABLE +
                " WHERE " + IN_Name + " IN ( SELECT " + FA_Name +" FROM " + FoodAllergy_TABLE + " WHERE "
                + FA_Status + "= ?))" ,new String[] {ht_id,me_id,"1"});
        if (food != null && !food.isClosed()) {
            food.moveToFirst();
        }
        return food;
    }


    public Cursor getAllFoodMuscle()  {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor food = db.rawQuery("SELECT " + FM_ID + " as _id, " + FM_Name + " FROM " + FoodMuscle_TABLE , null);
        if (food != null) {
            food.moveToFirst();
        }
        return food;
    }

    public Cursor getAllExercise()  {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor food = db.rawQuery("SELECT " + EX_ID + " as _id, " + EX_Name + " FROM " + Exert_TABLE , null);
        if (food != null) {
            food.moveToFirst();
        }
        return food;
    }

    public Cursor getAllFoodAllergyNormal(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor food = db.rawQuery("SELECT " + FN_ID + " as _id, " + FN_Name + " FROM " + FoodNormal_TABLE , null);
        if (food != null && !food.isClosed()) {
            food.moveToFirst();
        }
        return food;
    }

    public Cursor getAllFoodAllergyMuscle(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor food = db.rawQuery("SELECT " + FN_ID + " as _id, " + FN_Name + " FROM " + FoodNormal_TABLE , null);
        if (food != null && !food.isClosed()) {
            food.moveToFirst();
        }
        return food;
    }


    public Cursor getCustomFoodNormal() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] m_id = {"ME09"};
        Cursor food = db.rawQuery("SELECT " + FN_ID + " as _id, " + ME_ID + "," + FN_Name + " FROM " + FoodNormal_TABLE +
                " WHERE " + ME_ID + "= ? ", m_id);
        if (food != null) {
            food.moveToFirst();
        }
        return food;
    }

    public Cursor getCustomFoodMuscle() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] m_id = {"ME09"};
        Cursor food = db.rawQuery("SELECT " + FM_ID + " as _id, " + ME_ID + "," + FM_Name + " FROM " + FoodMuscle_TABLE +
                " WHERE " + ME_ID + "= ? ", m_id);
        if (food != null) {
            food.moveToFirst();
        }
        return food;
    }



    public Cursor getCustomExert() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] m_id = {"ME09"};
        Cursor food = db.rawQuery("SELECT " + EX_ID + " as _id, " + ME_ID + "," + EX_Name + "," + EX_Time + "," +EX_Kcal + " FROM " + Exert_TABLE +
                " WHERE " + ME_ID + "= ? ", m_id);
        if (food != null) {
            food.moveToFirst();
        }
        return food;
    }

    public Cursor getShowFoodNormal(String fn_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] f = {fn_id};
        Cursor food = db.rawQuery("SELECT " + FN_ID + " as _id, " + FN_Name + "," + TF_ID + " FROM " + FoodNormal_TABLE +
                " WHERE " + FN_ID + "= ?", f);
        if (food != null) {
            food.moveToFirst();
        }
        return food;
    }

    public Cursor getShowFoodMuscle(String fm_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] f = {fm_id};
        Cursor food = db.rawQuery("SELECT " + FM_ID + " as _id, " + FM_Name + "," + TF_ID + " FROM " + FoodMuscle_TABLE +
                " WHERE " + FM_ID + "= ?", f);
        if (food != null) {
            food.moveToFirst();
        }
        return food;
    }

    public Cursor getShowExert(String ex_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] ex = {ex_id};
        Cursor food = db.rawQuery("SELECT " + EX_ID + " as _id, " + ME_ID + "," + EX_Name + " , " + EX_Time + ", " + EX_Kcal + " FROM " + Exert_TABLE +
                " WHERE " + EX_ID + "= ?", ex);
        if (food != null) {
            food.moveToFirst();
        }
        return food;
    }


    public Cursor getUnitNormal(String f_ids) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] f_id = {f_ids};
        Cursor food = db.rawQuery("SELECT " + FN_ID + " as _id, " + FU_ID + " FROM " + DetailFoodNormal_TABLE + " WHERE " + FN_ID + "= ?", f_id);
        if (food != null) {
            food.moveToFirst();
        }
        return food;

    }

    public Cursor getUnitMuscle(String f_ids) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] f_id = {f_ids};
        Cursor food = db.rawQuery("SELECT " + FM_ID + " as _id, " + FU_ID + " FROM " + DetailFoodMuscle_TABLE + " WHERE " + FM_ID + "= ?", f_id);
        if (food != null) {
            food.moveToFirst();
        }
        return food;

    }

    public List<String> getAllUnit(String id) {
        List<String> unit = new ArrayList<String>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + FoodUnit_TABLE + " WHERE " + FU_ID + "= ?";

        //String sql = "SELECT * FROM " + FoodUnit_TABLE + " WHERE " + FU_ID + "=" + fu_ids;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{id});

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                unit.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        return unit;
    }



    public Cursor getIdUnit(String fu_name) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] name = {fu_name};
        Cursor unit = db.rawQuery("SELECT * FROM " + FoodUnit_TABLE + " WHERE " + FU_Name + "= ?", name);
        if (unit != null) {
            unit.moveToFirst();
        }
        return unit;
    }



    public ArrayList<String> getAllIngredNormal(String name){
        ArrayList<String> allergy = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor food = db.rawQuery("SELECT " + IN_ID + " as _id, " + FN_ID + "," + IN_Name + " FROM " + IngredientsNoraml_TABLE + " WHERE " + IN_Name + "= ?" ,new String[] {name});
        if(food.moveToFirst()){
            do {
                allergy.add(food.getString(1));
            }while (food.moveToNext());

        }
        return allergy;
    }

    public List<String> getAllUnit2() {
        List<String> unit = new ArrayList<String>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + FoodUnit_TABLE;

        //String sql = "SELECT * FROM " + FoodUnit_TABLE + " WHERE " + FU_ID + "=" + fu_ids;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                unit.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        return unit;
    }


    public Cursor getIdBrand(String tf_name) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] name = {tf_name};
        Cursor unit = db.rawQuery("SELECT * FROM " + TypeFood_TABLE + " WHERE " + TF_Name + "= ?", name);
        if (unit != null) {
            unit.moveToFirst();
        }
        return unit;
    }

    public List<String> getAllBrand() {
        List<String> unit = new ArrayList<String>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TypeFood_TABLE;

        //String sql = "SELECT * FROM " + FoodUnit_TABLE + " WHERE " + FU_ID + "=" + fu_ids;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                unit.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        return unit;
    }

    public Cursor getDetailFoodNormal(String f_ids, String fu_ids) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] fu_id = {f_ids,fu_ids};
        Cursor food = db.rawQuery("SELECT * FROM " + DetailFoodNormal_TABLE +
                " WHERE " + FN_ID + "= ? AND " + FU_ID + "= ?", fu_id);
        if (food != null) {
            food.moveToFirst();
        }
        return food;
    }

    public Cursor getDetailFoodMuscle(String f_id, String fu_ids) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] fu_id = {f_id,fu_ids};
        Cursor food = db.rawQuery("SELECT * FROM " + DetailFoodMuscle_TABLE + " WHERE " + FM_ID + "= ? AND " + FU_ID + "= ? ", fu_id);
        if (food != null) {
            food.moveToFirst();
        }
        return food;
    }


    public Cursor getTypeGoal(String tg_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] tgId = {tg_id};
        Cursor tg = db.rawQuery("SELECT * FROM " + TypeGoal_Table + " WHERE " + TG_ID + "= ?", tgId);
        if (tg != null) {
            tg.moveToFirst();
        }
        return tg;
    }

    public Cursor getTypeUser(String tu_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] tuId = {tu_id};
        Cursor tg = db.rawQuery("SELECT * FROM " + TypeUser_TABLE + " WHERE " + TU_ID + "= ?", tuId);
        if (tg != null) {
            tg.moveToFirst();
        }
        return tg;
    }

    public Cursor getPlan(String p_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] pId = {p_id};
        Cursor tg = db.rawQuery("SELECT * FROM " + Plan_TABLE + " WHERE " + P_ID + "= ?", pId);
        if (tg != null) {
            tg.moveToFirst();
        }
        return tg;
    }




    public Cursor getMaxLogFoodNormal(String ln_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] ln = {ln_id};
        Cursor goal = db.rawQuery("SELECT MAX(" + LFN_ID + "), " + LFN_Kcal + ", " + LN_ID + ", " + LFN_Amount + " FROM " + LogFoodNormal_TABLE +
                " WHERE " + LN_ID + "= ?", ln);
        if (goal != null) {
            goal.moveToFirst();
        }
        return goal;
    }

    public Cursor getMaxLogFoodMuscle(String lm_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] lm = {lm_id};
        Cursor goal = db.rawQuery("SELECT MAX(" + LFM_ID + ") , " + LFM_ID + ", " + LM_ID + "," + ME_ID + "," + FM_ID + "," + FU_ID + "," + LFM_Amount + "," + LFM_Kcal + "," + LFM_Fat + "," + LFM_Protein + "," + LFM_Carb + " FROM " + LogFoodMuscle_TABLE +
                " WHERE " + LM_ID + "= ?", lm);
        Log.e("Log","Log" + goal.getCount());
        if (goal != null) {
            goal.moveToFirst();
        }
        return goal;
    }

    public Cursor getMaxLogExertMuscle(String lm_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] lm = {lm_id};
        Cursor goal = db.rawQuery("SELECT MAX(" + LEM_ID + ") , " + LEM_ID + ", " + LM_ID + "," + ME_ID + "," + EX_ID + "," + LEM_Kcal + "," + LEM_Time + " FROM " + LogExerciseMuscle_TABLE +
                " WHERE " + LM_ID + "= ?", lm);
        if (goal != null) {
            goal.moveToFirst();
        }
        return goal;
    }


    public Cursor getMaxLogExertNormal(String ln_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] ln = {ln_id};
        Cursor goal = db.rawQuery("SELECT MAX(" + LEN_ID + ") , " + LEN_ID + ", " + LN_ID + "," + ME_ID + "," + EX_ID + "," + LEN_Kcal + "," + LEN_Time + " FROM " + LogExerciseNormal_TABLE +
                " WHERE " + LN_ID + "= ?", ln);
        if (goal != null) {
            goal.moveToFirst();
        }
        return goal;
    }



    public Cursor getLogFoodNormal(String ln_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] ln = {ln_id};
        Cursor goal = db.rawQuery("SELECT " + LFN_Kcal + ", " + LN_ID + ", " + LFN_Amount + " FROM " + LogFoodNormal_TABLE +
                " WHERE " + LN_ID + "= ?", ln);
        if (goal != null) {
            goal.moveToFirst();
        }
        return goal;
    }

    public Cursor getLogFoodMuscle(String lm_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] lm = {lm_id};
        Cursor goal = db.rawQuery("SELECT " + LFM_ID + ", " + LM_ID + "," + ME_ID + "," + FM_ID + "," + FU_ID + "," + LFM_Amount + "," + LFM_Kcal + "," + LFM_Fat + "," + LFM_Protein + "," + LFM_Carb + " FROM " + LogFoodMuscle_TABLE +
                " WHERE " + LM_ID + "= ?", lm);
        Log.e("Log","Log" + goal.getCount());
        if (goal != null) {
            goal.moveToFirst();
        }
        return goal;
    }

    public Cursor getLogExertMuscle(String lm_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] lm = {lm_id};
        Cursor goal = db.rawQuery("SELECT " + LEM_ID + ", " + LM_ID + "," + ME_ID + "," + EX_ID + "," + LEM_Kcal + "," + LEM_Time + " FROM " + LogExerciseMuscle_TABLE +
                " WHERE " + LM_ID + "= ?", lm);
        if (goal != null) {
            goal.moveToFirst();
        }
        return goal;
    }


    public Cursor getLogExertNormal(String ln_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] ln = {ln_id};
        Cursor goal = db.rawQuery("SELECT " + LEN_ID + ", " + LN_ID + "," + ME_ID + "," + EX_ID + "," + LEN_Kcal + "," + LEN_Time + " FROM " + LogExerciseNormal_TABLE +
                " WHERE " + LN_ID + "= ?", ln);
        if (goal != null) {
            goal.moveToFirst();
        }
        return goal;
    }


    public Cursor getMaxCustomFoodNormal() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor food = db.rawQuery("SELECT MAX(" + FN_ID + ") , " + FN_ID + " as _id, " + FN_ID + ","  + ME_ID + "," + FN_Name + " FROM " + FoodNormal_TABLE , null);
        if (food != null) {
            food.moveToFirst();
        }
        return food;
    }

    public Cursor getMaxCustomFoodMuscle() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor food = db.rawQuery("SELECT MAX(" + FM_ID + ") , " + FM_ID + " as _id, " + FM_ID + "," + ME_ID + "," + FM_Name + " FROM " + FoodMuscle_TABLE , null);
        if (food != null) {
            food.moveToFirst();
        }
        return food;
    }





        public ArrayList<AddData> getDetailLogBreakfastMuscle(String lm_id) {
            Log.d("yes","yes");
        // String selectQuery = "SELECT  * FROM " + DetailLogFood_TABLE;
        ArrayList<AddData> detail = new ArrayList<AddData>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT  f.FM_Name , u.FU_Name , LFM_Amount , LFM_Kcal , l.LM_ID , l.ME_ID , l.FM_ID , LFM_ID FROM " + LogFoodMuscle_TABLE + " l INNER JOIN " + FoodMuscle_TABLE + " f ON l.FM_ID = f.FM_ID " +
                "INNER JOIN " + FoodUnit_TABLE +" u ON l.FU_ID = u.FU_ID WHERE " + LM_ID + " = ? AND  l.ME_ID  = ?",new String[] {lm_id,"ME01"});
        Log.d("cursor", "Cursor : " + cursor.getCount());
        // looping through all rows and adding to list
            Log.d("yes","yes2");
        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    Log.d("yes","yes3");
                    AddData list = new AddData();
                    list.setNameFood(cursor.getString(0));
                    list.setUnit(cursor.getString(1));
                    list.setAmount(cursor.getString(2));
                    list.setKcal1(cursor.getString(3));
                    list.setLM(cursor.getString(4));
                    list.setME(cursor.getString(5));
                    list.setFM(cursor.getString(6));
                    list.setLFM(cursor.getString(7));
                    detail.add(list);
                    Log.d("testLogFood","food : " + detail);
                } while (cursor.moveToNext());
            }

        }
        db.close();
            Log.d("yes","yes4");
        return detail;
    }

    public ArrayList<AddData> getDetailLogLunchMuscle(String lm_id) {
        // String selectQuery = "SELECT  * FROM " + DetailLogFood_TABLE;
        ArrayList<AddData> detail = new ArrayList<AddData>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT  f.FM_Name , u.FU_Name , LFM_Amount , LFM_Kcal , l.LM_ID , l.ME_ID , l.FM_ID , LFM_ID  FROM " + LogFoodMuscle_TABLE + " l INNER JOIN " + FoodMuscle_TABLE + " f ON l.FM_ID = f.FM_ID " +
                "INNER JOIN " + FoodUnit_TABLE +" u ON l.FU_ID = u.FU_ID WHERE " + LM_ID + " = ? AND  l.ME_ID  = ?",new String[] {lm_id,"ME02"});
        // looping through all rows and adding to list

        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    AddData list = new AddData();
                    list.setNameFood(cursor.getString(0));
                    list.setUnit(cursor.getString(1));
                    list.setAmount(cursor.getString(2));
                    list.setKcal1(cursor.getString(3));
                    list.setLM(cursor.getString(4));
                    list.setME(cursor.getString(5));
                    list.setFM(cursor.getString(6));
                    list.setLFM(cursor.getString(7));
                    detail.add(list);
                } while (cursor.moveToNext());
            }

        }
        db.close();
        return detail;
    }


    public ArrayList<AddData> getDetailLogDinnerMuscle(String lm_id) {
        // String selectQuery = "SELECT  * FROM " + DetailLogFood_TABLE;
        ArrayList<AddData> detail = new ArrayList<AddData>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT  f.FM_Name , u.FU_Name , LFM_Amount , LFM_Kcal , l.LM_ID , l.ME_ID , l.FM_ID , LFM_ID  FROM " + LogFoodMuscle_TABLE + " l INNER JOIN " + FoodMuscle_TABLE + " f ON l.FM_ID = f.FM_ID " +
                "INNER JOIN " + FoodUnit_TABLE +" u ON l.FU_ID = u.FU_ID WHERE " + LM_ID + " = ? AND  l.ME_ID  = ?",new String[] {lm_id,"ME03"});
        Log.d("cursor", "Cursor" + cursor.getCount());
        // looping through all rows and adding to list

        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    AddData list = new AddData();
                    list.setNameFood(cursor.getString(0));
                    list.setUnit(cursor.getString(1));
                    list.setAmount(cursor.getString(2));
                    list.setKcal1(cursor.getString(3));
                    list.setLM(cursor.getString(4));
                    list.setME(cursor.getString(5));
                    list.setFM(cursor.getString(6));
                    list.setLFM(cursor.getString(7));
                    detail.add(list);
                } while (cursor.moveToNext());
            }

        }
        db.close();
        return detail;
    }

    public ArrayList<AddData> getDetailLogSupperMuscle(String lm_id) {
        // String selectQuery = "SELECT  * FROM " + DetailLogFood_TABLE;
        ArrayList<AddData> detail = new ArrayList<AddData>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT  f.FM_Name , u.FU_Name , LFM_Amount , LFM_Kcal , l.LM_ID , l.ME_ID , l.FM_ID , LFM_ID  FROM " + LogFoodMuscle_TABLE + " l INNER JOIN " + FoodMuscle_TABLE + " f ON l.FM_ID = f.FM_ID " +
                "INNER JOIN " + FoodUnit_TABLE +" u ON l.FU_ID = u.FU_ID WHERE " + LM_ID + " = ? AND  l.ME_ID  = ?",new String[] {lm_id,"ME04"});
        Log.d("cursor", "Cursor" + cursor.getCount());
        // looping through all rows and adding to list

        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    AddData list = new AddData();
                    list.setNameFood(cursor.getString(0));
                    list.setUnit(cursor.getString(1));
                    list.setAmount(cursor.getString(2));
                    list.setKcal1(cursor.getString(3));
                    list.setLM(cursor.getString(4));
                    list.setME(cursor.getString(5));
                    list.setFM(cursor.getString(6));
                    list.setLFM(cursor.getString(7));
                    detail.add(list);
                } while (cursor.moveToNext());
            }

        }
        db.close();
        return detail;
    }

    public ArrayList<AddData> getDetailLogSnackMuscle(String lm_id) {
        // String selectQuery = "SELECT  * FROM " + DetailLogFood_TABLE;
        ArrayList<AddData> detail = new ArrayList<AddData>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT  f.FM_Name , u.FU_Name , LFM_Amount , LFM_Kcal , l.LM_ID , l.ME_ID , l.FM_ID , LFM_ID  FROM " + LogFoodMuscle_TABLE + " l INNER JOIN " + FoodMuscle_TABLE + " f ON l.FM_ID = f.FM_ID " +
                "INNER JOIN " + FoodUnit_TABLE +" u ON l.FU_ID = u.FU_ID WHERE " + LM_ID + " = ? AND  l.ME_ID  = ?",new String[] {lm_id,"ME05"});
        Log.d("cursor", "Cursor" + cursor.getCount());
        // looping through all rows and adding to list

        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    AddData list = new AddData();
                    list.setNameFood(cursor.getString(0));
                    list.setUnit(cursor.getString(1));
                    list.setAmount(cursor.getString(2));
                    list.setKcal1(cursor.getString(3));
                    list.setLM(cursor.getString(4));
                    list.setME(cursor.getString(5));
                    list.setFM(cursor.getString(6));
                    list.setLFM(cursor.getString(7));
                    detail.add(list);
                } while (cursor.moveToNext());
            }

        }
        db.close();
        return detail;
    }

    public ArrayList<AddData> getDetailLogTeaMuscle(String lm_id) {
        // String selectQuery = "SELECT  * FROM " + DetailLogFood_TABLE;
        ArrayList<AddData> detail = new ArrayList<AddData>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT  f.FM_Name , u.FU_Name , LFM_Amount , LFM_Kcal , l.LM_ID , l.ME_ID , l.FM_ID , LFM_ID  FROM " + LogFoodMuscle_TABLE + " l INNER JOIN " + FoodMuscle_TABLE + " f ON l.FM_ID = f.FM_ID " +
                "INNER JOIN " + FoodUnit_TABLE +" u ON l.FU_ID = u.FU_ID WHERE " + LM_ID + " = ? AND  l.ME_ID  = ?",new String[] {lm_id,"ME06"});
        Log.d("cursor", "Cursor" + cursor.getCount());
        // looping through all rows and adding to list

        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    AddData list = new AddData();
                    list.setNameFood(cursor.getString(0));
                    list.setUnit(cursor.getString(1));
                    list.setAmount(cursor.getString(2));
                    list.setKcal1(cursor.getString(3));
                    list.setLM(cursor.getString(4));
                    list.setME(cursor.getString(5));
                    list.setFM(cursor.getString(6));
                    list.setLFM(cursor.getString(7));
                    detail.add(list);
                } while (cursor.moveToNext());
            }

        }
        db.close();
        return detail;
    }

    public ArrayList<AddData> getDetailLogExertMuscle(String lm_id) {
        // String selectQuery = "SELECT  * FROM " + DetailLogFood_TABLE;
        ArrayList<AddData> detail = new ArrayList<AddData>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT  f.EX_Name , LEM_Time , LEM_Kcal , l.LM_ID , l.ME_ID , l.EX_ID , LEM_ID  FROM " + LogExerciseMuscle_TABLE + " l INNER JOIN " + Exert_TABLE + " f ON l.EX_ID = f.EX_ID WHERE " + LM_ID + " = ? AND  l.ME_ID  = ?", new String[] {lm_id,"ME07"});
        Log.d("cursor", "Cursor" + cursor.getCount());
        // looping through all rows and adding to list

        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    AddData list = new AddData();
                    list.setNameFood(cursor.getString(0));
                    //   list.setUnit(cursor.getString(4));
                    list.setAmount(cursor.getString(1));
                    list.setKcal1(cursor.getString(2));
                    list.setLM(cursor.getString(3));
                    list.setME(cursor.getString(4));
                    list.setFM(cursor.getString(5));
                    list.setLFM(cursor.getString(6));
                    detail.add(list);
                } while (cursor.moveToNext());
            }

        }
        db.close();
        return detail;
    }


    public ArrayList<AddData> getDetailLogBreakfastNormal(String ln_id) {
        Log.d("yes","yes");
        // String selectQuery = "SELECT  * FROM " + DetailLogFood_TABLE;
        ArrayList<AddData> detail = new ArrayList<AddData>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT  f.FN_Name , u.FU_Name , LFN_Amount , LFN_Kcal , l.LN_ID , l.ME_ID , l.FN_ID , LFN_ID  FROM " + LogFoodNormal_TABLE + " l INNER JOIN " + FoodNormal_TABLE + " f ON l.FN_ID = f.FN_ID " +
                "INNER JOIN " + FoodUnit_TABLE +" u ON l.FU_ID = u.FU_ID WHERE " + LN_ID + " = ? AND  l.ME_ID  = ?",new String[] {ln_id,"ME01"});
        Log.d("cursor", "Cursor : " + cursor.getCount());
        // looping through all rows and adding to list
        Log.d("yes","yes2");
        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    Log.d("yes","yes3");
                    AddData list = new AddData();
                    list.setNameFood(cursor.getString(0));
                    list.setUnit(cursor.getString(1));
                    list.setAmount(cursor.getString(2));
                    list.setKcal1(cursor.getString(3));
                    list.setLM(cursor.getString(4));
                    list.setME(cursor.getString(5));
                    list.setFM(cursor.getString(6));
                    list.setLFM(cursor.getString(7));
                    detail.add(list);
                    Log.d("testLogFood","food : " + detail);
                } while (cursor.moveToNext());
            }

        }
        db.close();
        Log.d("yes","yes4");
        return detail;
    }

    public ArrayList<AddData> getDetailLogLunchNormal(String ln_id) {
        // String selectQuery = "SELECT  * FROM " + DetailLogFood_TABLE;
        ArrayList<AddData> detail = new ArrayList<AddData>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT  f.FN_Name , u.FU_Name , LFN_Amount , LFN_Kcal , l.LN_ID , l.ME_ID , l.FN_ID , LFN_ID  FROM " + LogFoodNormal_TABLE + " l INNER JOIN " + FoodNormal_TABLE + " f ON l.FN_ID = f.FN_ID " +
                "INNER JOIN " + FoodUnit_TABLE +" u ON l.FU_ID = u.FU_ID WHERE " + LN_ID + " = ? AND  l.ME_ID  = ?",new String[] {ln_id,"ME02"});
        // looping through all rows and adding to list

        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    AddData list = new AddData();
                    list.setNameFood(cursor.getString(0));
                    list.setUnit(cursor.getString(1));
                    list.setAmount(cursor.getString(2));
                    list.setKcal1(cursor.getString(3));
                    list.setLM(cursor.getString(4));
                    list.setME(cursor.getString(5));
                    list.setFM(cursor.getString(6));
                    list.setLFM(cursor.getString(7));
                    detail.add(list);
                } while (cursor.moveToNext());
            }

        }
        db.close();
        return detail;
    }


    public ArrayList<AddData> getDetailLogDinnerNormal(String ln_id) {
        // String selectQuery = "SELECT  * FROM " + DetailLogFood_TABLE;
        ArrayList<AddData> detail = new ArrayList<AddData>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT  f.FN_Name , u.FU_Name , LFN_Amount , LFN_Kcal , l.LN_ID , l.ME_ID , l.FN_ID , LFN_ID  FROM " + LogFoodNormal_TABLE + " l INNER JOIN " + FoodNormal_TABLE + " f ON l.FN_ID = f.FN_ID " +
                "INNER JOIN " + FoodUnit_TABLE +" u ON l.FU_ID = u.FU_ID WHERE " + LN_ID + " = ? AND  l.ME_ID  = ?",new String[] {ln_id,"ME03"});
        Log.d("cursor", "Cursor" + cursor.getCount());
        // looping through all rows and adding to list

        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    AddData list = new AddData();
                    list.setNameFood(cursor.getString(0));
                    list.setUnit(cursor.getString(1));
                    list.setAmount(cursor.getString(2));
                    list.setKcal1(cursor.getString(3));
                    list.setLM(cursor.getString(4));
                    list.setME(cursor.getString(5));
                    list.setFM(cursor.getString(6));
                    list.setLFM(cursor.getString(7));
                    detail.add(list);
                } while (cursor.moveToNext());
            }

        }
        db.close();
        return detail;
    }

    public ArrayList<AddData> getDetailLogSnackNormal(String ln_id) {
        // String selectQuery = "SELECT  * FROM " + DetailLogFood_TABLE;
        ArrayList<AddData> detail = new ArrayList<AddData>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT  f.FN_Name , u.FU_Name , LFN_Amount , LFN_Kcal , l.LN_ID , l.ME_ID , l.FN_ID , LFN_ID  FROM " + LogFoodNormal_TABLE + " l INNER JOIN " + FoodNormal_TABLE + " f ON l.FN_ID = f.FN_ID " +
                "INNER JOIN " + FoodUnit_TABLE +" u ON l.FU_ID = u.FU_ID WHERE " + LN_ID + " = ? AND  l.ME_ID  = ?",new String[] {ln_id,"ME05"});
        Log.d("cursor", "Cursor" + cursor.getCount());
        // looping through all rows and adding to list

        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    AddData list = new AddData();
                    list.setNameFood(cursor.getString(0));
                    list.setUnit(cursor.getString(1));
                    list.setAmount(cursor.getString(2));
                    list.setKcal1(cursor.getString(3));
                    list.setLM(cursor.getString(4));
                    list.setME(cursor.getString(5));
                    list.setFM(cursor.getString(6));
                    list.setLFM(cursor.getString(7));
                    detail.add(list);
                } while (cursor.moveToNext());
            }

        }
        db.close();
        return detail;
    }

    public ArrayList<AddData> getDetailLogExertNormal(String ln_id) {
        // String selectQuery = "SELECT  * FROM " + DetailLogFood_TABLE;
        ArrayList<AddData> detail = new ArrayList<AddData>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT  f.EX_Name , LEN_Time , LEN_Kcal , l.LN_ID , l.ME_ID , l.EX_ID ,LEN_ID  FROM " + LogExerciseNormal_TABLE + " l INNER JOIN " + Exert_TABLE + " f ON l.EX_ID = f.EX_ID WHERE " + LN_ID + " = ? AND  l.ME_ID  = ?", new String[] {ln_id,"ME07"});
        Log.d("cursor", "Cursor" + cursor.getCount());
        // looping through all rows and adding to list

        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    AddData list = new AddData();
                    list.setNameFood(cursor.getString(0));
                 //   list.setUnit(cursor.getString(4));
                    list.setAmount(cursor.getString(1));
                    list.setKcal1(cursor.getString(2));
                    list.setLM(cursor.getString(3));
                    list.setME(cursor.getString(4));
                    list.setFM(cursor.getString(5));
                    list.setLFM(cursor.getString(6));
                    detail.add(list);
                } while (cursor.moveToNext());
            }

        }
        db.close();
        return detail;
    }


    public ArrayList<TotalKcal> getTotalKcalFoodMuscle(String lm_id,String me_id) {
        // String selectQuery = "SELECT  * FROM " + DetailLogFood_TABLE;
        ArrayList<TotalKcal> detail = new ArrayList<TotalKcal>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + LFM_Kcal + "," + LFM_Fat + "," + LFM_Protein + "," + LFM_Carb + " FROM " + LogFoodMuscle_TABLE + " WHERE " + LM_ID + " = ? AND  " + ME_ID + "  = ?", new String[] {lm_id,me_id});
        Log.d("cursor", "Cursor" + cursor.getCount());
        // looping through all rows and adding to list

        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    TotalKcal list = new TotalKcal();
                    list.setTotalKcal(cursor.getInt(0));
                    //   list.setUnit(cursor.getString(4));
                    list.setTotalFat(cursor.getDouble(1));
                    list.setTotalProtein(cursor.getDouble(2));
                    list.setTotalCarb(cursor.getDouble(2));
                    detail.add(list);
                } while (cursor.moveToNext());
            }

        }
        db.close();
        return detail;
    }

    public ArrayList<Integer> getKcalFoodNormal(String ln_id , String me_id) {
        ArrayList<Integer> kcal = new ArrayList<Integer>();
        // Select All Query
        String selectQuery = "SELECT " + LFN_Kcal +" FROM " + LogFoodNormal_TABLE + " WHERE " + LN_ID + "= ? AND " + ME_ID + "= ? " ;

        //String sql = "SELECT * FROM " + FoodUnit_TABLE + " WHERE " + FU_ID + "=" + fu_ids;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{ln_id,me_id});

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                kcal.add(cursor.getInt(0));
            } while (cursor.moveToNext());
        }
        return kcal;
    }

    public ArrayList<Integer> getKcalFoodMuscle(String lm_id , String me_id) {
        ArrayList<Integer> kcal = new ArrayList<Integer>();
        // Select All Query
        String selectQuery = "SELECT " + LFM_Kcal +" FROM " + LogFoodMuscle_TABLE + " WHERE " + LM_ID + "= ? AND " + ME_ID + "= ? " ;

        //String sql = "SELECT * FROM " + FoodUnit_TABLE + " WHERE " + FU_ID + "=" + fu_ids;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{lm_id,me_id});

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                kcal.add(cursor.getInt(0));
            } while (cursor.moveToNext());
        }
        return kcal;
    }

    public ArrayList<Double> getFatFoodMuscle(String lm_id , String me_id) {
        ArrayList<Double> kcal = new ArrayList<Double>();
        // Select All Query
        String selectQuery = "SELECT " + LFM_Fat +" FROM " + LogFoodMuscle_TABLE + " WHERE " + LM_ID + "= ? AND " + ME_ID + "= ? " ;

        //String sql = "SELECT * FROM " + FoodUnit_TABLE + " WHERE " + FU_ID + "=" + fu_ids;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{lm_id,me_id});

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                kcal.add(cursor.getDouble(0));
            } while (cursor.moveToNext());
        }
        return kcal;
    }

    public ArrayList<Double> getProteinFoodMuscle(String lm_id , String me_id) {
        ArrayList<Double> kcal = new ArrayList<Double>();
        // Select All Query
        String selectQuery = "SELECT " + LFM_Protein +" FROM " + LogFoodMuscle_TABLE + " WHERE " + LM_ID + "= ? AND " + ME_ID + "= ? " ;

        //String sql = "SELECT * FROM " + FoodUnit_TABLE + " WHERE " + FU_ID + "=" + fu_ids;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{lm_id,me_id});

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                kcal.add(cursor.getDouble(0));
            } while (cursor.moveToNext());
        }
        return kcal;
    }

    public ArrayList<Double> getCarbFoodMuscle(String lm_id , String me_id) {
        ArrayList<Double> kcal = new ArrayList<Double>();
        // Select All Query
        String selectQuery = "SELECT " + LFM_Carb +" FROM " + LogFoodMuscle_TABLE + " WHERE " + LM_ID + "= ? AND " + ME_ID + "= ? " ;

        //String sql = "SELECT * FROM " + FoodUnit_TABLE + " WHERE " + FU_ID + "=" + fu_ids;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{lm_id,me_id});

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                kcal.add(cursor.getDouble(0));
            } while (cursor.moveToNext());
        }
        return kcal;
    }

    public ArrayList<Integer> getKcalExertMuscle(String lm_id , String me_id) {
        ArrayList<Integer> kcal = new ArrayList<Integer>();
        // Select All Query
        String selectQuery = "SELECT " + LEM_Kcal +" FROM " + LogExerciseMuscle_TABLE + " WHERE " + LM_ID + "= ? AND " + ME_ID + "= ? " ;

        //String sql = "SELECT * FROM " + FoodUnit_TABLE + " WHERE " + FU_ID + "=" + fu_ids;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{lm_id,me_id});

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                kcal.add(cursor.getInt(0));
            } while (cursor.moveToNext());
        }
        return kcal;
    }

    public ArrayList<Integer> getKcalExertNormal(String ln_id , String me_id) {
        ArrayList<Integer> kcal = new ArrayList<Integer>();
        // Select All Query
        String selectQuery = "SELECT " + LEN_Kcal +" FROM " + LogExerciseNormal_TABLE + " WHERE " + LN_ID + "= ? AND " + ME_ID + "= ? " ;

        //String sql = "SELECT * FROM " + FoodUnit_TABLE + " WHERE " + FU_ID + "=" + fu_ids;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{ln_id,me_id});

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                kcal.add(cursor.getInt(0));
            } while (cursor.moveToNext());
        }
        return kcal;
    }




    public  boolean updateWeight( String weight){
        SQLiteDatabase db = this.getWritableDatabase();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ContentValues we = new ContentValues();
        Log.d("date","dateToo");
//        we.put(WE_Date, dateFormat.format(new Date()));
//        we.put(WE_Date, date);
        String m = dateFormat.format(new Date());
        we.put(WE_Weight, weight);
        Log.d("date", "weight" + weight);

        long result = db.update(Weight_TABLE,we, WE_Date + " = ? ", new String[] {m});
        Log.d("date", "dateToooooooooo");
        if (result == -1)
            return false;
        else
            return true;
    }

    public  boolean updateMuscleToday(String u_id,String um_id, String et_id){
        SQLiteDatabase db = this.getWritableDatabase();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ContentValues we = new ContentValues();
        Log.d("date","dateToo");
        String m = dateFormat.format(new Date());
        we.put(U_ID, u_id);
        we.put(UM_ID, um_id);
        we.put(ET_ID, et_id);
        long result = db.update(MuscleToday_TABLE,we, TD_Date + " = ? ", new String[] {m});
        Log.d("date","dateToooooooooo");
        if (result == -1)
            return false;
        else
            return true;
    }

    public  boolean updateNormalToday(String u_id, String ht_id){
        SQLiteDatabase db = this.getWritableDatabase();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ContentValues we = new ContentValues();
        Log.d("date","dateToo");
        String m = dateFormat.format(new Date());
        we.put(U_ID, u_id);
        we.put(HT_ID, ht_id);
        long result = db.update(NormalToday_TABLE,we, TD_Date + " = ? ", new String[] {m});
        Log.d("date","dateToooooooooo");
        if (result == -1)
            return false;
        else
            return true;
    }



    public  boolean UpdateGoalNormal( String u_id, String tg_id, String p_id, String u_goal){
        SQLiteDatabase db = this.getWritableDatabase();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ContentValues cv = new ContentValues();
        Log.d("date","dateToo");
        String m = dateFormat.format(new Date());
        cv.put(U_ID, u_id);
        cv.put(TG_ID, tg_id);
        cv.put(P_ID, p_id);
        cv.put(GN_Weight, u_goal);
        long result = db.update(GoalNormal_Table,cv, GN_Date + " = ? ", new String[] {m});
        Log.d("date","dateToooooooooo");
        if (result == -1)
            return false;
        else
            return true;
    }

    public  boolean UpdateGoalMuscle( String u_id, String um_id, String p_id, String gm_weight, String gm_bodyfat){
        SQLiteDatabase db = this.getWritableDatabase();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ContentValues cv = new ContentValues();
        Log.d("date","dateToo");
        String m = dateFormat.format(new Date());
        cv.put(U_ID, u_id);
        cv.put(UM_ID, um_id);
        cv.put(P_ID, p_id);
        cv.put(GM_Weight, gm_weight);
        cv.put(GM_Bodyfat, gm_bodyfat);

        long result = db.update(GoalMuscle_Table,cv, GM_Date + " = ? ", new String[] {m});
        Log.d("date","dateToooooooooo");
        if (result == -1)
            return false;
        else
            return true;
    }

    public  boolean updateUser( String ac_id,String tu_id){
        SQLiteDatabase db = this.getWritableDatabase();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ContentValues we = new ContentValues();
        Log.d("date", "dateToo");
        String m = dateFormat.format(new Date());
        we.put(AC_ID, ac_id);
        we.put(TU_ID, tu_id);
        long result = db.update(User_TABLE,we, U_Date + " = ? ", new String[] {m});
        Log.d("date","dateToooooooooo");
        if (result == -1)
            return false;
        else
            return true;
    }
    public  boolean UpdateLogNormalKcal( String tdn_kcal){
        SQLiteDatabase db = this.getWritableDatabase();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ContentValues we = new ContentValues();
        Log.d("date", "dateToo");
        String m = dateFormat.format(new Date());
        we.put(TDN_Kcal, tdn_kcal);
        long result = db.update(LogNormal_TABLE,we, TD_Date + " = ? ", new String[] {m});
        Log.d("date","dateToooooooooo");
        if (result == -1)
            return false;
        else
            return true;
    }

    public  boolean UpdateLogMuscleKcal( String tdn_kcal,String tdn_fat,String tdn_protein,String tdn_carb){
        SQLiteDatabase db = this.getWritableDatabase();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ContentValues we = new ContentValues();
        Log.d("date", "dateToo");
        String m = dateFormat.format(new Date());
        we.put(TDM_Kcal, tdn_kcal);
        we.put(TDM_Fat, tdn_fat);
        we.put(TDM_Protein, tdn_protein);
        we.put(TDM_Carb, tdn_carb);
        long result = db.update(LogMuscle_TABLE,we, TD_Date + " = ? ", new String[] {m});
        Log.d("date","dateToooooooooo");
        if (result == -1)
            return false;
        else
            return true;
    }

    public  boolean UpdateLogMuscleKcalExert( String tdm_kcal, String tdm_fat ,String tdm_protein ,String tdm_carb){
        SQLiteDatabase db = this.getWritableDatabase();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ContentValues we = new ContentValues();
        String m = dateFormat.format(new Date());
        we.put(TDM_Kcal, tdm_kcal);
        we.put(TDM_Fat, tdm_fat);
        we.put(TDM_Protein, tdm_protein);
        we.put(TDM_Carb, tdm_carb);
        long result = db.update(LogMuscle_TABLE,we, TD_Date + " = ? ", new String[] {m});
        if (result == -1)
            return false;
        else
            return true;
    }


    public  boolean UpdateActivities( String ac_val ){
        SQLiteDatabase db = this.getWritableDatabase();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ContentValues we = new ContentValues();
        Log.d("date", "dateToo");
        String m = dateFormat.format(new Date());
        we.put(AC_Values, ac_val);
        long result = db.update(Activities_TABLE,we, AC_Date + " = ? ", new String[] {m});
        Log.d("date","dateToooooooooo");
        if (result == -1)
            return false;
        else
            return true;
    }

    public  boolean UpdateTypeUser( String tu_id ){
        SQLiteDatabase db = this.getWritableDatabase();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ContentValues we = new ContentValues();
        Log.d("date", "dateToo");
        String m = dateFormat.format(new Date());
        we.put(DTU_ID, tu_id);
        long result = db.update(TypeUser_TABLE,we, TU_Date + " = ? ", new String[] {m});
        Log.d("date","dateToooooooooo");
        if (result == -1)
            return false;
        else
            return true;
    }

    public  boolean UpdateBodyFat( String bf_bodyfat ){
        SQLiteDatabase db = this.getWritableDatabase();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ContentValues we = new ContentValues();
        Log.d("date", "dateToo");
        String m = dateFormat.format(new Date());
        we.put(BF_BodyFat, bf_bodyfat);
        long result = db.update(BodyFat_Table,we, BF_Date + " = ? ", new String[] {m});
        Log.d("date","dateToooooooooo");
        if (result == -1)
            return false;
        else
            return true;
    }

    public  boolean UpdateFFMI( String fi_ffmi ){
        SQLiteDatabase db = this.getWritableDatabase();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ContentValues we = new ContentValues();
        Log.d("date", "dateToo");
        String m = dateFormat.format(new Date());
        we.put(FI_FFMI, fi_ffmi);
        long result = db.update(FFMI_Table,we, FI_Date + " = ? ", new String[] {m});
        Log.d("date","dateToooooooooo");
        if (result == -1)
            return false;
        else
            return true;
    }


    public  boolean UpdateLogNormal(String u_id, String tdn_Totalkcal , String tdn_kcal){
        SQLiteDatabase db = this.getWritableDatabase();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ContentValues we = new ContentValues();
        Log.d("date", "dateToo");
        String m = dateFormat.format(new Date());
        we.put(U_ID,u_id);
        we.put(TDN_TotalKcal, tdn_Totalkcal);
        we.put(TDN_Kcal, tdn_kcal);
        long result = db.update(LogNormal_TABLE,we, TD_Date + " = ? ", new String[] {m});
        Log.d("date","dateToooooooooo");
        if (result == -1)
            return false;
        else
            return true;
    }

    public  boolean UpdateLogMuscle( String u_id,String um_id,
                                     String tdm_Totalkcal,String tdm_Totalfat,String tdm_Totalprotein, String tdm_Totalcarb,
                                     String tdm_kcal,String tdm_fat,String tdm_protein, String tdm_carb){
        SQLiteDatabase db = this.getWritableDatabase();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ContentValues we = new ContentValues();
        Log.d("date", "dateToo");
        String m = dateFormat.format(new Date());
        we.put(U_ID, u_id);
        we.put(UM_ID, um_id);
        we.put(TDM_TotalKcal, tdm_Totalkcal);
        we.put(TDM_TotalFat, tdm_Totalfat);
        we.put(TDM_TotalProtein, tdm_Totalprotein);
        we.put(TDM_TotalCarb, tdm_Totalcarb);
        we.put(TDM_Kcal, tdm_kcal);
        we.put(TDM_Fat, tdm_fat);
        we.put(TDM_Protein, tdm_protein);
        we.put(TDM_Carb, tdm_carb);
        long result = db.update(LogMuscle_TABLE,we, TD_Date + " = ? ", new String[] {m});
        Log.d("date","dateToooooooooo");
        if (result == -1)
            return false;
        else
            return true;
    }

    public  boolean UpdateFoodAllergy(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues al = new ContentValues();
        al.put(FA_Status,true);
        long result = db.update(FoodAllergy_TABLE,al, FA_Name + " = ? ", new String[] {name});
        if (result == -1)
            return false;
        else
            return true;
    }

    public  boolean UpdateFoodAllergyVegetEggMilk(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues al = new ContentValues();
        al.put(FA_Status,true);
        long result = db.update(FoodAllergy_TABLE,al, FA_ID + " BETWEEN 4 AND 11", null);
        if (result == -1)
            return false;
        else
            return true;
    }

    public  boolean UpdateFoodAllergyVegetEgg(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues al = new ContentValues();
        al.put(FA_Status,true);
        long result = db.update(FoodAllergy_TABLE,al,FA_ID + " = ?", new String[] {"2"});
        if (result == -1)
            return false;
        else
            return true;
    }

    public  boolean UpdateFoodAllergyVegetMilk(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues al = new ContentValues();
        al.put(FA_Status,true);
        long result = db.update(FoodAllergy_TABLE,al, FA_ID + " BETWEEN 3 AND 11", null);
        if (result == -1)
            return false;
        else
            return true;
    }
    public  boolean UpdateFoodAllergyVegetVegan(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues al = new ContentValues();
        al.put(FA_Status,true);
        long result = db.update(FoodAllergy_TABLE,al, FA_ID + " BETWEEN 2 AND 11", null);
        if (result == -1)
            return false;
        else
            return true;
    }

    public  boolean UpdateAllergy(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues al = new ContentValues();
        al.put(FA_Status,true);
        long result = db.update(FoodAllergy_TABLE,al, FA_ID + " = ? ", new String[] {"1"});
        if (result == -1)
            return false;
        else
            return true;
    }



    public boolean UpdateUser(String gender, String weight_id, String height, String age) {
        SQLiteDatabase db = this.getWritableDatabase();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ContentValues user = new ContentValues();
        user.put(U_ID,1);
        user.put(U_Date, dateFormat.format(new Date()));
        user.put(U_Gender, gender);
        user.put(WE_ID, weight_id);
        user.put(U_Height, height);
        user.put(U_Age, age);
        long result = db.update(User_TABLE, user, null,null);
        if (result == -1)
            return false;
        else
            return true;

    }

    public boolean UpdateUserVeget(String vt_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ContentValues user = new ContentValues();
        user.put(VT_ID, vt_id);
        long result = db.update(User_TABLE, user, U_ID + " = ? ", new String[] {"1"});
        if (result == -1)
            return false;
        else
            return true;

    }

    public  boolean UpdateTotalCalNormal(String ln_id, String me_id , String tln_kcal){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues we = new ContentValues();
        we.put(TLN_Kcal, tln_kcal);
        long result = db.update(TotalCaloriesNormal_TABLE,we, LN_ID + " = ? AND " + ME_ID + " = ? ", new String[] {ln_id,me_id});

        if (result == -1)
            return false;
        else
            return true;
    }



    public  boolean UpdateTotalCalMuscle(String lm_id, String me_id , String tlm_kcal, String tlm_fat, String tlm_protein, String tlm_carb){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues we = new ContentValues();
        we.put(TLM_Kcal, tlm_kcal);
        we.put(TLM_Fat, tlm_fat);
        we.put(TLM_Protein, tlm_protein);
        we.put(TLM_Carb, tlm_carb);
        long result = db.update(TotalCaloriesMuscle_TABLE,we, LM_ID + " = ? AND " + ME_ID + " = ? ", new String[] {lm_id,me_id});

        if (result == -1)
            return false;
        else
            return true;
    }


    public  boolean UpdateLogFoodMuscle(String lfm_id ,String lm_id,String me_id, String fm_id, String fu_id,
                                        String amount, String kcal, String fat, String protein, String carb){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues we = new ContentValues();
        we.put(FU_ID, fu_id);
        we.put(LFM_Amount, amount);
        we.put(LFM_Kcal, kcal);
        we.put(LFM_Fat, fat);
        we.put(LFM_Protein, protein);
        we.put(LFM_Carb, carb);
        long result = db.update(LogFoodMuscle_TABLE,we,LFM_ID + " = ? AND " + LM_ID + " = ? AND " + ME_ID + " = ?  AND " + FM_ID + " = ? ", new String[] {lfm_id,lm_id,me_id,fm_id});

        if (result == -1)
            return false;
        else
            return true;
    }


    public boolean UpdateLogFoodNormal(String lfn_id, String ln_id, String me_id, String fn_id, String fu_id, String amount, String kcal) {
        SQLiteDatabase db = this.getWritableDatabase();
        // SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ContentValues we = new ContentValues();
        we.put(FU_ID, fu_id);
        we.put(LFN_Amount, amount);
        we.put(LFN_Kcal, kcal);
        long result = db.update(LogFoodNormal_TABLE,we,LFN_ID + " = ? AND " + LN_ID + " = ? AND " + ME_ID + " = ?  AND " + FN_ID + " = ? ", new String[] {lfn_id,ln_id,me_id,fn_id});
        if (result == -1)
            return false;
        else
            return true;

    }


    public boolean UpdateLogExertNormal(String len_id ,String ln_id,String me_id, String ex_id, String len_time, String len_kcal) {
        SQLiteDatabase db = this.getWritableDatabase();
        // SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ContentValues we = new ContentValues();
        we.put(LEN_Time, len_time);
        we.put(LEN_Kcal, len_kcal);
        long result = db.update(LogExerciseNormal_TABLE,we,LEN_ID + " = ? AND " + LN_ID + " = ? AND " + ME_ID + " = ?  AND " + EX_ID + " = ? ", new String[] {len_id,ln_id,me_id,ex_id});
        if (result == -1)
            return false;
        else
            return true;

    }

    public  boolean UpdateLogExertMuscle(String lem_id ,String lm_id,String me_id, String ex_id, String lem_time, String lem_kcal){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues we = new ContentValues();
        we.put(LEM_Time, lem_time);
        we.put(LEM_Kcal, lem_kcal);
        long result = db.update(LogExerciseMuscle_TABLE,we,LEM_ID + " = ? AND " + LM_ID + " = ? AND " + ME_ID + " = ?  AND " + EX_ID + " = ? ", new String[] {lem_id,lm_id,me_id,ex_id});

        if (result == -1)
            return false;
        else
            return true;
    }











    ///// Delete

    public boolean deleteAllUser (){
        int doneDelete = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        doneDelete = db.delete(User_TABLE, null , null);
        Log.w(TAG, Integer.toString(doneDelete));
        return doneDelete > 0;
    }

    public boolean deleteAllUserMuscle (){
        int doneDelete = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        doneDelete = db.delete(UserMuscle_TABLE, null , null);
        Log.w(TAG, Integer.toString(doneDelete));
        return doneDelete > 0;
    }


    public boolean deleteLogFoodMuscle (String lm_id){
        int doneDelete = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        doneDelete = db.delete(LogFoodMuscle_TABLE, LM_ID + " = ? " , new String[] {lm_id});
        Log.w(TAG, Integer.toString(doneDelete));
        return doneDelete > 0;
    }

    public boolean deleteLogFoodNormal (String ln_id){
        int doneDelete = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        doneDelete = db.delete(LogFoodNormal_TABLE, LN_ID + " = ? " , new String[] {ln_id});
        Log.w(TAG, Integer.toString(doneDelete));
        return doneDelete > 0;
    }


    public boolean deleteLogExertMuscle (String lm_id){
        int doneDelete = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        doneDelete = db.delete(LogExerciseMuscle_TABLE, LM_ID + " = ? " , new String[] {lm_id});
        Log.w(TAG, Integer.toString(doneDelete));
        return doneDelete > 0;
    }

    public boolean deleteLogExertNormal (String ln_id){
        int doneDelete = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        doneDelete = db.delete(LogExerciseNormal_TABLE, LN_ID + " = ? " , new String[] {ln_id});
        Log.w(TAG, Integer.toString(doneDelete));
        return doneDelete > 0;
    }


    public boolean deleteTotalcalMuscle (String lm_id){
        int doneDelete = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        doneDelete = db.delete(TotalCaloriesMuscle_TABLE, LM_ID + " = ? " , new String[] {lm_id});
        Log.w(TAG, Integer.toString(doneDelete));
        return doneDelete > 0;
    }

    public boolean deleteTotalcalNormal (String ln_id){
        int doneDelete = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        doneDelete = db.delete(TotalCaloriesNormal_TABLE, LN_ID + " = ? " , new String[] {ln_id});
        Log.w(TAG, Integer.toString(doneDelete));
        return doneDelete > 0;
    }


    public boolean deleteLogFoodEditMuscle (String lfm_id ,String lm_id , String me_id ,String fm_id){
        int doneDelete = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        doneDelete = db.delete(LogFoodMuscle_TABLE,LFM_ID + " = ? AND " + LM_ID + " = ? AND " + ME_ID + " = ? AND " + FM_ID + " = ? " , new String[] {lfm_id,lm_id,me_id,fm_id});
        Log.w(TAG, Integer.toString(doneDelete));
        return doneDelete > 0;
    }

    public boolean deleteLogFoodEditNormal (String lfn_id , String ln_id , String me_id ,String fn_id){
        int doneDelete = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        doneDelete = db.delete(LogFoodNormal_TABLE,LFN_ID + " = ? AND " + LN_ID + " = ? AND " + ME_ID + " = ? AND " + FN_ID + " = ? " , new String[] {lfn_id,ln_id,me_id,fn_id});
        Log.w(TAG, Integer.toString(doneDelete));
        return doneDelete > 0;
    }


    public boolean deleteLogExertEditMuscle (String lem_id , String lm_id , String me_id ,String ex_id){
        int doneDelete = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        doneDelete = db.delete(LogExerciseMuscle_TABLE,LEM_ID + " = ? AND " + LM_ID + " = ? AND " + ME_ID + " = ? AND " + EX_ID + " = ? " , new String[] {lem_id,lm_id,me_id,ex_id});
        Log.w(TAG, Integer.toString(doneDelete));
        return doneDelete > 0;
    }

    public boolean deleteLogExertEditNormal (String len_id , String ln_id , String me_id ,String ex_id){
        int doneDelete = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        doneDelete = db.delete(LogExerciseNormal_TABLE,LEN_ID + " = ? AND " + LN_ID + " = ? AND " + ME_ID + " = ? AND " + EX_ID + " = ? " , new String[] {len_id,ln_id,me_id,ex_id});
        Log.w(TAG, Integer.toString(doneDelete));
        return doneDelete > 0;
    }









}
