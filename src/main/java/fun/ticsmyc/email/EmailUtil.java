package fun.ticsmyc.email;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Properties;

/**
 * 简易邮件工具
 */
public class EmailUtil {

    /**
     * 发送邮件【当前设置为QQ，其它邮箱修改一些设置】
     * @param authCode 16位授权码
     * @param fromEmail 发件人
     * @param toUserEmail 收件人
     * @param subject 主题
     * @param comment 文本正文
     */
    public static void sendEmail(String authCode,String fromEmail,String toUserEmail,String subject,String comment) throws GeneralSecurityException, MessagingException {
        //创建一个配置文件并保存
        Properties properties = new Properties();
        properties.setProperty("mail.host","smtp.qq.com");//QQ邮件服务器
        properties.setProperty("mail.transport.protocol","smtp");//协议
        properties.setProperty("mail.smtp.auth","true");

        //QQ存在一个特性设置SSL加密
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", sf);

        //创建一个session对象
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("qq邮箱","QQ邮箱16位授权码");
            }
        });
        //开启debug模式
        //session.setDebug(true);
        //获取连接对象
        Transport transport = session.getTransport();
        //连接服务器
        transport.connect("smtp.qq.com",fromEmail,authCode);
        //创建邮件对象
        MimeMessage mimeMessage = new MimeMessage(session);
        //邮件发送人
        mimeMessage.setFrom(new InternetAddress(fromEmail));
        //邮件接收人
        mimeMessage.setRecipient(Message.RecipientType.TO,new InternetAddress(toUserEmail));
        //邮件标题
        mimeMessage.setSubject(subject);
        //邮件内容
        mimeMessage.setContent(comment,"text/html;charset=UTF-8");
        //发送邮件
        transport.sendMessage(mimeMessage,mimeMessage.getAllRecipients());
        //关闭连接
        transport.close();
    }
}
