package com.example.shortmessage.smsController;

import com.aliyun.auth.credentials.Credential;
import com.aliyun.auth.credentials.provider.StaticCredentialProvider;
import com.aliyun.sdk.service.dysmsapi20170525.AsyncClient;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsResponse;
import com.google.gson.Gson;
import darabonba.core.client.ClientOverrideConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author wang qiang
 * @date 2022/10/20 0020 16:22
 * @Description: 腾讯云发送短信息接口
 */
@Controller
@RequestMapping("tencent")
public class AlibabaSendMassages {
    private final static String ACCESS_KEY_ID="1";
    private final static String ACCESS_KEY_SECRET="1";
    private final static String PHONE_NUMBER="1";
    private final static String SIGN_NAME="1";
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        StaticCredentialProvider provider = StaticCredentialProvider.create(Credential.builder()
                .accessKeyId(ACCESS_KEY_ID)
                .accessKeySecret(ACCESS_KEY_SECRET)
                .build());

        AsyncClient client = AsyncClient.builder()
                .region("cn-hangzhou") // Region ID
                .credentialsProvider(provider)
                .overrideConfiguration(
                        ClientOverrideConfiguration.create()
                                .setEndpointOverride("dysmsapi.aliyuncs.com")
                )
                .build();
        SendSmsRequest sendSmsRequest = SendSmsRequest.builder()
                .resourceOwnerAccount("your_value")
                .resourceOwnerId(1L)
                .phoneNumbers(PHONE_NUMBER)
                .signName(SIGN_NAME)
                .build();
        CompletableFuture<SendSmsResponse> response = client.sendSms(sendSmsRequest);
        SendSmsResponse resp = response.get();
        System.out.println(new Gson().toJson(resp));
        client.close();
    }
}
