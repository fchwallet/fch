package com.xyz.fch_sp.app.core.ali;

import org.springframework.stereotype.Service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

@Service("emailSend")
public class EmailSend {

	public static void sample(String fromemail, String subject, String htmlBody) {
	    // 如果是除杭州region外的其它region（如新加坡、澳洲Region），需要将下面的"cn-hangzhou"替换为"ap-southeast-1"、或"ap-southeast-2"。
	    IClientProfile profile = DefaultProfile.getProfile("ap-southeast-1", "LTAITFwych1B44kx", "3GSGpzCx1FZqcPJrdHzAwV2k6rqD92");
	// 如果是除杭州region外的其它region（如新加坡region）， 需要做如下处理
	    try {
	    DefaultProfile.addEndpoint("dm.ap-southeast-1.aliyuncs.com", "ap-southeast-1", "Dm",  "dm.ap-southeast-1.aliyuncs.com");
	    } catch (ClientException e) {
	    e.printStackTrace();
	    }
	    IAcsClient client = new DefaultAcsClient(profile);
	    SingleSendMailRequest request = new SingleSendMailRequest();

	    try {
	        request.setVersion("2017-06-22");// 如果是除杭州region外的其它region（如新加坡region）,必须指定为2017-06-22
	        request.setAccountName("info@gdaex.pro");
	        request.setFromAlias("otc");
	        request.setAddressType(1);
//	        request.setTagName("test");
	        request.setReplyToAddress(true);
	        request.setToAddress(fromemail);
	        request.setSubject(subject);
	        request.setHtmlBody(htmlBody);
	        SingleSendMailResponse httpResponse = client.getAcsResponse(request);

	    } catch (ServerException e) {
	        e.printStackTrace();
	    }
	    catch (ClientException e) {
	        e.printStackTrace();
	    }
	}

}
