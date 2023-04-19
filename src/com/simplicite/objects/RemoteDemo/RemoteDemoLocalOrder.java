package com.simplicite.objects.RemoteDemo;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.simplicite.util.AppLog;
import com.simplicite.util.ObjectDB;
import com.simplicite.util.ObjectField;
import com.simplicite.util.Message;
import com.simplicite.util.tools.JSONTool;

/**
 * Local order
 */
public class RemoteDemoLocalOrder extends ObjectDB {
	private static final long serialVersionUID = 1L;
	
	@Override
	public List<String> preValidate() {
		List<String> msgs = new ArrayList<>();

		ObjectField prd = getField("remoteRloProduct");
		AppLog.info("Product ref: " + prd.getValue(), getGrant());
		try {
			JSONObject data = new JSONObject(JSONTool.jsonMetaObject(getGrant(), prd.getValue(), null));
			AppLog.info("Product data: " + data.toString(2), getGrant());
			setFieldValue("remoteRloProductData", data.getString("userkeylabel"));
		} catch (Exception e) {
			msgs.add(Message.formatError("Error on product: " + e.getMessage(), null, prd.getName()));
		}
		
		ObjectField cli = getField("remoteRloClient");
		AppLog.info("Customer ref: " + cli.getValue(), getGrant());
		try {
			JSONObject data = new JSONObject(JSONTool.jsonMetaObject(getGrant(), cli.getValue(), null));
			AppLog.info("Customer data: " + data.toString(2), getGrant());
			setFieldValue("remoteRloCustomerData", data.getString("userkeylabel"));
		} catch (Exception e) {
			msgs.add(Message.formatError("Error on customer: " + e.getMessage(), null, cli.getName()));
			setFieldValue("remoteRloCustomerData", "<Error>");
		}
		
		// TODO: retreive product price to calculate the total
		
		return msgs;
	}
}