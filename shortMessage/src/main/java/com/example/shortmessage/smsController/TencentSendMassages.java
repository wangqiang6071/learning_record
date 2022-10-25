package com.example.shortmessage.smsController;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;

//导入可选配置类
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;

import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wang qiang
 * @date 2022/10/20 0020 16:22
 * @Description: 腾讯云发送短信息接口
 */
@Controller
@RequestMapping("tencent")
public class TencentSendMassages {
    private static final String SECRET_ID="";
    private static final String SECRET_KEY="";
    private static final String SDK_APPID="";
    private static final String SIGN_NAME="";
    private static final String TEMPLATE_ID="";

    public static void main(String[] args) {
        try {

            Credential cred = new Credential(SECRET_ID, SECRET_KEY);

            // 实例化一个http选项，可选，没有特殊需求可以跳过
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("sms.tencentcloudapi.com");
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            SmsClient client = new SmsClient(cred, "ap-guangzhou",clientProfile);

            SendSmsRequest req = new SendSmsRequest();
            //手机号码
            String[] phoneNumberSet = {"+8621212313123", "+8612345678902", "+8612345678903"};
            req.setPhoneNumberSet(phoneNumberSet);
            //短信参数
            String[] templateParamSet = {"1234"};
            req.setTemplateParamSet(templateParamSet);

            req.setSmsSdkAppid(SDK_APPID);
            req.setSign(SIGN_NAME);
            req.setTemplateID(TEMPLATE_ID);



            SendSmsResponse res = client.SendSms(req);
            // 输出json格式的字符串回包
            System.out.println(SendSmsResponse.toJsonString(res));
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }
    }

}
