package com.simplicite.objects.RemoteDemo;

import org.json.JSONObject;

import java.util.Arrays;

import com.simplicite.util.AppLog;
import com.simplicite.util.ObjectDB;
import com.simplicite.util.ObjectField;
import com.simplicite.util.tools.JSONTool;

/**
 * Local order using remote objects
 */
public class RemoteDemoLocalOrder extends ObjectDB {
    public static final long serialVersionUID = 1L;

    @Override
    public void postSelect(String rowId, boolean copy) {
        ObjectField cli = getField("remoteRloClient");
        ObjectField cliLabel = getField("remoteRloClientLabel");
        try {
            JSONObject data = new JSONObject(JSONTool.jsonMetaObject(getGrant(), cli.getValue(), null));
            cliLabel.setValue(data.getString("userkeylabel"));
        } catch (Exception e) {
            AppLog.error("Unable to retreive customer data: " + e.getMessage(), null, getGrant());
            cliLabel.setValue("");
        }

        ObjectField prd = getField("remoteRloProduct");
        ObjectField prdLabel = getField("remoteRloProductLabel");
        try {
            JSONObject data = new JSONObject(JSONTool.jsonMetaObject(getGrant(), prd.getValue(), null));
            prdLabel.setValue(data.getString("userkeylabel"));
        } catch (Exception e) {
            AppLog.error("Unable to retreive product data: " + e.getMessage(), null, getGrant());
            prdLabel.setValue("");
        }
    }
    
    @Override
    public String preSave() {
        // Order total calculation
        ObjectField prd = getField("remoteRloProduct");
        ObjectField total = getField("remoteRloTotal");
        try {
            JSONObject data = new JSONObject(JSONTool.jsonMetaObject(getGrant(), prd.getValue(), Arrays.asList("demoPrdUnitPrice"), null));
            JSONObject item = data.getJSONObject("item");
            total.setValue(item.getDouble("demoPrdUnitPrice") * getField("remoteRloQuantity").getInt(0));
        } catch (Exception e) {
            AppLog.error("Unable to retreive product data: " + e.getMessage(), null, getGrant());
            total.setValue(0);
        }
    
        return null;
    }
    
}