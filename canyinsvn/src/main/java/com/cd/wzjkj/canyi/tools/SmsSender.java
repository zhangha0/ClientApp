package com.cd.wzjkj.canyi.tools;

import com.qcloud.sms.SmsSingleSender;
import com.qcloud.sms.SmsSingleSenderResult;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * Created by liuzheng on 2016/12/21.
 */

public class SmsSender {
    Random random = new Random();
    int sdkappid;
    String appkey;
    // 请根据我们的说明文档适时调整 url
    final String url = "https://yun.tim.qq.com/v3/tlssmssvr/sendsms";

    public SmsSender(int sdkappid, String appkey) {
        this.sdkappid = sdkappid;
        this.appkey = appkey;
    }

    public void sendMsg(String nationCode, String phoneNumber, String content) {

        // 初始化单发
        SmsSingleSender singleSender = new SmsSingleSender(sdkappid, appkey);
        // 普通单发
        try {
            SmsSingleSenderResult singleSenderResult = singleSender.send(0, nationCode, phoneNumber, content+"为您的登录验证码，请于1分钟内填写。如非本人操作，请忽略本短信。", "", "");
        } catch (Exception e) {
            e.printStackTrace();
        }



//        long rnd = random.nextInt(999999)%(999999-100000+1)+100000;
//        String wholeUrl = String.format("%s?sdkappid=%d&random=%d", url, sdkappid, rnd);
//        try {
//            Log.e("TAG","url---"+wholeUrl);
//            URL object = new URL(wholeUrl);
//            HttpURLConnection con = (HttpURLConnection) object.openConnection();
//            con.setDoOutput(true);
//            con.setDoInput(true);
//            con.setRequestProperty("Content-Type", "application/json");
//            con.setRequestProperty("Accept", "application/json");
//            con.setRequestMethod("POST");
//            JSONObject data = new JSONObject();
//            JSONObject tel = new JSONObject();
//            tel.put("nationcode", nationCode);
//            String phone = phoneNumber;
//            tel.put("phone", phone);
//            data.put("tel", tel);
//            data.put("type", 0);
//            data.put("msg", content);
//            String sig = stringMD5(appkey.concat(phone));
//            data.put("sig", sig);
//            data.put("extend", "");
//            data.put("ext", "");
//            Log.e("TAG","data==="+data.toString());
//            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream(), "utf-8");
//            wr.write(data.toString());
//            wr.flush();
//            Log.e("TAG","data2==="+wr.toString());
//
//            // 显示 POST 请求返回的内容
//            StringBuilder sb = new StringBuilder();
//            int HttpResult = con.getResponseCode();
//            if (HttpResult == HttpURLConnection.HTTP_OK) {
//                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
//                String line = null;
//                while ((line = br.readLine()) != null) {
//                    sb.append(line + "\n");
//                }
//                br.close();
//                Log.e("TAG","123---"+sb.toString());
//            } else {
//                System.out.println(con.getResponseMessage());
//                Log.e("TAG","abc----"+con.getResponseMessage());
//            }
//        } catch (Exception e) {
//            Log.e("TAG","ABC"+e.toString());
//            e.printStackTrace();
//        }
    }

    private static String stringMD5(String input) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] inputByteArray = input.getBytes();
        messageDigest.update(inputByteArray);
        byte[] resultByteArray = messageDigest.digest();
        return byteArrayToHex(resultByteArray);
    }

    private static String byteArrayToHex(byte[] byteArray) {
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] resultCharArray = new char[byteArray.length * 2];
        int index = 0;
        for (byte b : byteArray) {
            resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
            resultCharArray[index++] = hexDigits[b & 0xf];
        }
        return new String(resultCharArray);
    }
}


