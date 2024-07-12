package com.scuec.restaurant.utils;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Random;

@Slf4j
@Component
public class SMSUtil implements InitializingBean {

    /** 腾讯云账户密钥对secretKey（在访问管理中配置） */
    @Value("${tencent.sms.secretId}")
    private String secretID ;
    /** 腾讯云账户密钥对secretKey（在访问管理中配置） */
    @Value("${tencent.sms.secretKey}")
    private String secretKey ;
    @Value("${tencent.sms.smsSdkAppId}")
    private String smsSdkAppID ;
    @Value("${tencent.sms.signName}")
    private String signName ;

    public static String SECRET_ID;
    public static String SECRET_KEY;
    public static String SMSSDKAPP_ID;
    public static String SIGN_NAME;

    @Override
    public void afterPropertiesSet() throws Exception {
        SECRET_ID = secretID;
        SECRET_KEY = secretKey;
        SMSSDKAPP_ID = smsSdkAppID;
        SIGN_NAME = signName;
    }

    /**
     * 发送消息
     * @param phoneNumber 手机号
     * @param templateID 模板Id
     * @param templateParams 模板参数
     * @return
     */
    public static boolean sendMsg(String phoneNumber, String templateID, String[] templateParams) {
        //判断手机号和模板Id
        if (!StringUtils.hasText(phoneNumber) || !StringUtils.hasText(templateID))
            return false;
        try{
            // 实例化一个认证对象，入参需要传入腾讯云账户secretId，secretKey,此处还需注意密钥对的保密
            // 密钥可前往https://console.cloud.tencent.com/cam/capi网站进行获取
            Credential cred = new Credential(SECRET_ID, SECRET_KEY);
            // 实例化一个http选项，可选的，没有特殊需求可以跳过
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("sms.tencentcloudapi.com");
            // 实例化一个client选项，可选的，没有特殊需求可以跳过
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            // 实例化要请求产品的client对象,clientProfile是可选的  第二个参数是地域信息
            SmsClient client = new SmsClient(cred, "ap-nanjing", clientProfile);
            // 实例化一个请求对象,每个接口都会对应一个request对象
            SendSmsRequest req = new SendSmsRequest();
            //设置发送相关的参数
            String[] phoneNumberSet1 = {"86"+phoneNumber};
            req.setPhoneNumberSet(phoneNumberSet1);//发送的手机号
            //设置固定的参数
            req.setSmsSdkAppid(SMSSDKAPP_ID);// 短信应用ID: 短信SdkAppId在 [短信控制台] 添加应用后生成的实际SdkAppId
            req.setSign(SIGN_NAME);//短信签名内容: 使用 UTF-8 编码，必须填写已审核通过的签名
            req.setTemplateID(templateID);//模板 ID: 必须填写已审核通过的模板 ID
            //发送的短信
            req.setTemplateParamSet(templateParams);
            // 发送短信，返回的resp是一个SendSmsResponse的实例，与请求对象对应
            SendSmsResponse resp = client.SendSms(req);
            // 输出json格式的字符串回包
            log.info(SendSmsResponse.toJsonString(resp));
            return true;
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 随机生成验证码
     * @param length 长度为4位或者6位
     * @return
     */
    public static String generateValidateCode(int length){
        Integer code =null;
        if(length == 4){
            code = new Random().nextInt(9999);//生成随机数，最大为9999
            if(code < 1000){
                code = code + 1000;//保证随机数为4位数字
            }
        }else if(length == 6){
            code = new Random().nextInt(999999);//生成随机数，最大为999999
            if(code < 100000){
                code = code + 100000;//保证随机数为6位数字
            }
        }else{
            throw new RuntimeException("只能生成4位或6位数字验证码");
        }
        return code.toString();
    }
}
