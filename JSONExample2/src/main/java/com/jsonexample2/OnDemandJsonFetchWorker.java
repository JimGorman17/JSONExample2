package com.jsonexample2;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jgorman on 12/19/13.
 */
public class OnDemandJsonFetchWorker extends IntentService {
    Handler mHandler;

    public OnDemandJsonFetchWorker(){
        super("OnDemandJsonFetchWorker");
        mHandler = new Handler();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        LogHelper.ProcessAndThreadId("OnDemandJsonFetchWorker.onHandleIntent");

        // Creating JSON Parser instance
        JSONParser jParser = new JSONParser();
        // contacts JSONArray
        JSONArray contacts = null;

        // getting JSON string from URL
        JSONObject json = jParser.getJSONFromUrl(Constants.url);

        try {
            // Getting Array of Contacts
            contacts = json.getJSONArray(Constants.TAG_CONTACTS);

            // looping through All Contacts
            for(int i = 0; i < contacts.length(); i++){
                JSONObject c = contacts.getJSONObject(i);

                // Storing each json item in variable
                String id = c.getString(Constants.TAG_ID);
                String name = c.getString(Constants.TAG_NAME);
                String email = c.getString(Constants.TAG_EMAIL);
                String address = c.getString(Constants.TAG_ADDRESS);
                String gender = c.getString(Constants.TAG_GENDER);

                // Phone number is agin JSON Object
                JSONObject phone = c.getJSONObject(Constants.TAG_PHONE);
                String mobile = phone.getString(Constants.TAG_PHONE_MOBILE);
                String home = phone.getString(Constants.TAG_PHONE_HOME);
                String office = phone.getString(Constants.TAG_PHONE_OFFICE);
                LogHelper.ProcessAndThreadId("Current object: " + Integer.toString(i));

                mHandler.post(new DisplayToast(this, name));
                // Toast toast = Toast.makeText(this, name, Toast.LENGTH_LONG);
                // toast.show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
