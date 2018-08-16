//package net.simplifiedlearning.retrofitexample;
//
//import android.content.Context;
//import android.content.SharedPreferences;
//
//import net.simplifiedlearning.retrofitexample.Hero;
//
///**
// * Created by admin on 01-08-2018.
// */
//
//public class SharedPrefManager {
//
//    private static SharedPrefManager mInstance;
//    private static Context mCTx;
//
//    private static final String SHARED_PRE_NAME = "dreamotechretrofit";
//
//    private static final String KEY_ID = "keyid";
//    private static final String KEY_NAME = "name";
//    private static final String UPDATE_AT = "updated_at";
//    private static final String CREATED_AT = "created_at";
//
//    private SharedPrefManager(Context context){mCTx = context;}
//
//    public static synchronized SharedPrefManager getmInstance(Context context){
//
//        if (mInstance == null){
//
//            mInstance = new SharedPrefManager(context);
//        }
//
//        return mInstance;
//    }
//
//    public boolean userLogin(Hero hero){
//
//        SharedPreferences sharedPrefManager =  mCTx.getSharedPreferences(SHARED_PRE_NAME,Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPrefManager.edit();
//        editor.putString(KEY_ID,hero.getId());
//        editor.putString(KEY_NAME,hero.getName());
//        editor.putString(UPDATE_AT,hero.getUpdated_at());
//        editor.putString(CREATED_AT,hero.getCreated_at());
//        editor.apply();
//        return true;
//
//    }
//
//    public Hero getUser() {
//        SharedPreferences sharedPreferences = mCTx.getSharedPreferences(SHARED_PRE_NAME, Context.MODE_PRIVATE);
//        return new Hero(
//                sharedPreferences.getString(KEY_ID, null),
//                sharedPreferences.getString(KEY_NAME, null),
//                sharedPreferences.getString(UPDATE_AT, null),
//                sharedPreferences.getString(CREATED_AT, null)
//        );
//    }
//
//}
