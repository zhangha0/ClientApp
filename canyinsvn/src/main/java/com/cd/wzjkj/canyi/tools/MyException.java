package com.cd.wzjkj.canyi.tools;


import android.content.Context;

import com.cd.wzjkj.canyi.R;

public class MyException {
    public static void sendmail(Exception e) {
        /*StackTraceElement[] c = e.getStackTrace();
		StringBuffer sb = new StringBuffer();
		sb.append("ID=="+Httptools.USERID);
		sb.append("\n\r");
		sb.append("����=="+e.getMessage());
		sb.append("\n\r");
		for(int i=0;i<c.length;i++){
			sb.append("Cause by\n\r");
			sb.append("getClassName=="+c[i].getClassName());
			sb.append("\n\r");
			sb.append("getFileName=="+c[i].getFileName());
			sb.append("\n\r");
			sb.append("getLineNumber=="+c[i].getLineNumber());
			sb.append("\n\r");
			sb.append("getMethodName=="+c[i].getMethodName());
			sb.append("\n\r");
		}
		// TODO Auto-generated method stub
		final SimpleMailSender sms = new SimpleMailSender();
		final MailSenderInfo msi = new MailSenderInfo();
		String str = new String(sb);
		msi.setContent(str);
		new Thread() {
			public void run() {
				sms.sendTextMail(msi);
			};
		}.start();*/
    }

    public static void sendmail2(String email, Context context) {


        final MailSenderInfo mailInfo = new MailSenderInfo();
        String[] to = {email};
        mailInfo.setToAddress(to);

        mailInfo.setSubject(context.getResources().getString(R.string.email_title));
        mailInfo.setContent(context.getResources().getString(R.string.email_tip)+getString(6));
//        mailInfo.setAttachFileNames(new String[]{"ss"});

        //这个类主要来发送邮件
        final SimpleMailSender sms = new SimpleMailSender();
        new Thread() {
            public void run() {
                sms.sendMail(mailInfo);
            }
        }.start();
    }
    public static void sendmail2(String email, Context context,String content) {


        final MailSenderInfo mailInfo = new MailSenderInfo();
        String[] to = {email};
        mailInfo.setToAddress(to);

        mailInfo.setSubject(context.getResources().getString(R.string.email_title));
        mailInfo.setContent(content);
//        mailInfo.setAttachFileNames(new String[]{"ss"});

        //这个类主要来发送邮件
        final SimpleMailSender sms = new SimpleMailSender();
        new Thread() {
            public void run() {
                sms.sendMail(mailInfo);
            }
        }.start();
    }

    private static String getString(int num) {
        int numcode = (int) ((Math.random() * 9 + 1) * Math.pow(10,num-1));
        return ""+numcode;
    }
}
