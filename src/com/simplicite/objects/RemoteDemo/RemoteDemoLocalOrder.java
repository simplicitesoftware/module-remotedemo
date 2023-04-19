package com.simplicite.objects.RemoteDemo;

import java.util.ArrayList;
import java.util.List;

import com.simplicite.util.AppLog;
import com.simplicite.util.ObjectDB;

/**
 * Local order
 */
public class RemoteDemoLocalOrder extends ObjectDB {
	private static final long serialVersionUID = 1L;
	
	@Override
	public List<String> preValidate() {
		List<String> msgs = new ArrayList<>();
		
		AppLog.info("Product: " + getFieldValue("remoteRloProduct"), getGrant());
		AppLog.info("Customer: " + getFieldValue("remoteRloClient"), getGrant());
		
		// TODO: retreive product price to calculate the total
		
		return msgs;
	}
}
