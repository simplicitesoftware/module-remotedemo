package com.simplicite.objects.RemoteDemo;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.simplicite.util.AppLog;
import com.simplicite.util.ObjectDB;
import com.simplicite.util.ObjectField;
import com.simplicite.util.Message;
import com.simplicite.util.tools.JSONTool;

/**
 * Local order using remote objects
 */
public class RemoteDemoLocalOrder extends ObjectDB {
	private static final long serialVersionUID = 1L;

	public static final String UNIT_PRICE = "demoPrdUnitPrice";

	@Override
	public List<String> preValidate() {
		List<String> msgs = new ArrayList<>();

		ObjectField cli = getField("remoteRloClient");
		ObjectField cliLabel = getField("remoteRloClientLabel");
		AppLog.info("Customer ref: " + cli.getValue(), getGrant());
		try {
			JSONObject data = new JSONObject(JSONTool.jsonMetaObject(getGrant(), cli.getValue(), null));
			AppLog.info("Customer data: " + data.toString(2), getGrant());
			cliLabel.setValue(data.getString("userkeylabel"));
		} catch (Exception e) {
			msgs.add(Message.formatError("Error on customer: " + e.getMessage(), null, cli.getName()));
			cliLabel.setValue("?");
		}

		ObjectField prd = getField("remoteRloProduct");
		ObjectField prdLabel = getField("remoteRloProductLabel");
		AppLog.info("Product ref: " + prd.getValue(), getGrant());
		try {
			JSONObject data = new JSONObject(JSONTool.jsonMetaObject(getGrant(), prd.getValue(), Arrays.asList(new String[] { UNIT_PRICE }), null));
			AppLog.info("Product data: " + data.toString(2), getGrant());
			prdLabel.setValue(data.getString("userkeylabel"));

			// Order total calculation
			JSONObject item = data.getJSONObject("item");
			setFieldValue("remoteRloTotal", getField("remoteRloQuantity").getInt(0) * item.getDouble(UNIT_PRICE));
		} catch (Exception e) {
			msgs.add(Message.formatError("Error on product: " + e.getMessage(), null, prd.getName()));
			prdLabel.setValue("?");
		}

		return msgs;
	}
}