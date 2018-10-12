package com.yidao.appstore.service.impl;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.yidao.appstore.service.ISendSmsService;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.MediaType;


@Service("sendSmsService")
public class SmsServiceImpl implements ISendSmsService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private final String LSM_KEY = "key-c53be35079f404ef32a1e8e240eec1cb";
	private final String LSM_URL = "https://sms-api.luosimao.com/v1/send.json";


	@Override
	public void sendSMS(String mobileNo, String msg) {
		// TODO Auto-generated method stub
		// just replace key here
		if (StringUtils.isEmpty(mobileNo) || StringUtils.isEmpty(msg))
			return;

		Client client = Client.create();
		client.addFilter(new HTTPBasicAuthFilter(
				"api", LSM_KEY));
		WebResource webResource = client.resource(LSM_URL);
		MultivaluedMapImpl formData = new MultivaluedMapImpl();
		formData.add("mobile", mobileNo);
		formData.add("message", msg);
		ClientResponse response = webResource.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class, formData);
		String textEntity = response.getEntity(String.class);
		int status = response.getStatus();
		//分析返回结果
		if (status == 200) {
			try {
				JSONObject obj = new JSONObject(textEntity);
				String error = obj.getString("error");
				String str = obj.getString("msg");
				logger.debug(String.format("Result: error=%s, msg=%s", error, str));
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
	}
}