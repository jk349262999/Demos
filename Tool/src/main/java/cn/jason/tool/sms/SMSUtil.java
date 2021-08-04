package cn.jason.tool.sms;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.*;
import com.aliyun.teaopenapi.models.Config;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class SMSUtil {

    /**
     * 使用AK&SK初始化账号Client
     *
     * @param accessKeyId
     * @param accessKeySecret
     * @return Client
     * @throws Exception
     */
    public static Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new Client(config);
    }

//    public static


    public static void main(String[] args) throws Exception {
        String startTime = DateUtil.now();
        Console.log("启动时间:{}", startTime);
        TimeInterval timer = DateUtil.timer();
        List<String> keyWordErrorNumber = Arrays.asList("18195269135");
        while (true) {
            timer.intervalRestart();
            keyWordErrorNumber = sendzx(keyWordErrorNumber);
            long costTime = timer.intervalRestart();
            if (keyWordErrorNumber.size() == 0) {
                break;
            }
            Console.log("已运行时间：{}；当次循环花费时间: {}，内容关键字需要重发条数：{}"
                    , DateUtil.between(DateUtil.parse(startTime), new Date(), DateUnit.MINUTE), costTime, keyWordErrorNumber.size());
            Console.log("关键字错误号码，开始...");
            Console.log(keyWordErrorNumber);
            Console.log("关键字错误号码，结束...");
        }
        String endTime = DateUtil.now();
        Console.log("结束时间: {},总体耗费时间", endTime, DateUtil.between(DateUtil.parse(startTime), DateUtil.parse(endTime), DateUnit.MINUTE));
        Console.log("done");
//        java.util.List<String> args = java.util.Arrays.asList(args_);
//        Client client = createClient("accessKeyId", "accessKeySecret");
//        SendSmsRequest sendSmsRequest = new SendSmsRequest();
        // 复制代码运行请自行打印 API 的返回值
//        client.sendSms(sendSmsRequest);
    }

    public static List<String> sendzx(List<String> errorPhoneArr) throws Exception {

        List<Entity> result = Db.use().query("\tSELECT distinct\n" +
                "\tr.phonenumber, \n" +
                "\t r.smspar \n" +
                "FROM\n" +
                "\ttb_student_zx s\n" +
                "\tINNER JOIN tb_smsrecord r on r.student_id=s.id\n" +
                "WHERE\n" +
                "\ts.CountyId = 640105 \n" +
                "\tAND s.JiazhangSubmit =1\n" +
                "\tand r.SmsTemplateCode='SMS_210996360'");


        List<String> keyWordErrorNumber = new ArrayList<>(100);
        for (Entity entity : result) {
            String param = entity.get("smspar").toString().replaceFirst("2月21日", "8月6日至8月11日");
            String phoneNumber = entity.get("phonenumber").toString();

            if (!errorPhoneArr.contains(phoneNumber)) {
                continue;
            }
//            System.out.println(entity.get("phonenumber"));
//            System.out.println(param);
//            phoneNumber = "18595149383";

            try {
                QuerySendDetailsResponseBody.QuerySendDetailsResponseBodySmsSendDetailDTOsSmsSendDetailDTO res =
                        sendMsgGetDetail("入转学服务平台", "SMS_196657898", phoneNumber, param);
                if ("CONTENT_KEYWORD".equals(res.getErrCode())) {
                    keyWordErrorNumber.add(res.getPhoneNum());
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("错误");
            }
        }
        return keyWordErrorNumber;

    }

    @SneakyThrows
    public static SendSmsResponse sendMsg(String signName, String templateCode, String phoneNumber, String param) {
        com.aliyun.dysmsapi20170525.Client client = createClient("Orqduwimyh6dXBwZ", "IzAGFLvONTg22utEsbmDP6qkYhFNrm");
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setSignName(signName)
                .setTemplateCode(templateCode)
                .setPhoneNumbers(phoneNumber)
                .setTemplateParam(param);
        return client.sendSms(sendSmsRequest);
    }


    public static QuerySendDetailsResponseBody.QuerySendDetailsResponseBodySmsSendDetailDTOsSmsSendDetailDTO sendMsgGetDetail(String signName, String templateCode, String phoneNumber, String param) throws Exception {
        com.aliyun.dysmsapi20170525.Client client = createClient("Orqduwimyh6dXBwZ", "IzAGFLvONTg22utEsbmDP6qkYhFNrm");
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setSignName(signName)
                .setTemplateCode(templateCode)
                .setPhoneNumbers(phoneNumber)
                .setTemplateParam(param);
        SendSmsResponse sendResp = client.sendSms(sendSmsRequest);
        String code = sendResp.body.code;
        if (!com.aliyun.teautil.Common.equalString(code, "OK")) {
            com.aliyun.teaconsole.Client.log("错误信息: " + sendResp.body.message + "");
            return null;
        }
        System.out.println(StrUtil.format("发送手机号【{}】完成", phoneNumber));
        String bizId = sendResp.body.bizId;
        // 2. 等待 10 秒后查询结果
        com.aliyun.teautil.Common.sleep(10000);
        QuerySendDetailsRequest queryReq = new QuerySendDetailsRequest()
                .setPhoneNumber(com.aliyun.teautil.Common.assertAsString(phoneNumber))
                .setBizId(bizId)
                .setSendDate(DateUtil.format(new Date(), "yyyyMMdd"))
                .setPageSize(10L)
                .setCurrentPage(1L);
        QuerySendDetailsResponse queryResp = client.querySendDetails(queryReq);

        QuerySendDetailsResponseBody body = queryResp.body;

        java.util.List<QuerySendDetailsResponseBody.QuerySendDetailsResponseBodySmsSendDetailDTOsSmsSendDetailDTO> dtos = queryResp.body.smsSendDetailDTOs.smsSendDetailDTO;
        // 打印结果queryResp.body.smsSendDetailDTOs.smsSendDetailDTO
        QuerySendDetailsResponseBody.QuerySendDetailsResponseBodySmsSendDetailDTOsSmsSendDetailDTO dto = queryResp.body.smsSendDetailDTOs.smsSendDetailDTO.get(0);
        if (com.aliyun.teautil.Common.equalString("" + dto.sendStatus + "", "3")) {
            com.aliyun.teaconsole.Client.log("" + dto.phoneNum + " 发送成功，接收时间: " + dto.receiveDate + "");
        } else if (com.aliyun.teautil.Common.equalString("" + dto.sendStatus + "", "2")) {
            com.aliyun.teaconsole.Client.log("" + dto.phoneNum + " 发送失败");
//            com.aliyun.teaconsole.Client.log(dto.getErrCode());
//            com.aliyun.teaconsole.Client.log(dto.getContent());

        } else {
            com.aliyun.teaconsole.Client.log("" + dto.phoneNum + " 正在发送中...");
        }
        return dto;
    }


}
