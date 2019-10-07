package com.framework.utilities;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SMTPUtility {
	
	public  static boolean sendMail(String userName,
      		String passWord,
      		String host,
      		String port,
      		String[] to,
      		String[] cc,
      		String[] bcc,
      		String subject,
      		String text,
      		String attachmentPath,
      		String attachmentName){


              Properties props = new Properties();

              //Properties props=System.getProperties();
		
		      props.put("mail.smtp.user", userName);
		
		      props.put("mail.smtp.host", host);
		
		              if(!"".equals(port))
		
		      props.put("mail.smtp.port", port);
		
		              if(!"".equals("true"))
		
		      props.put("mail.smtp.starttls.enable","true");
		
		      props.put("mail.smtp.auth", "true");
		     // props.put("mail.smtps.auth", "true");

		      boolean debug = true;
              if(debug){

              props.put("mail.smtp.debug", "true");

              }else{

              props.put("mail.smtp.debug", "false");         

              }

              if(!"".equals(port))
		
		      props.put("mail.smtp.socketFactory.port", port);
		
		              if(!"".equals("javax.net.ssl.SSLSocketFactory"))
		
		      props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		
		              if(!"".equals("true"))
		
		      props.put("mail.smtp.socketFactory.fallback", "false");


		      try
		
		      {
		
		          Session session = Session.getDefaultInstance(props, null);
		
		          session.setDebug(debug);
		
		          //attachment start
		          // create the message part 
		         
		          Multipart multipart = new MimeMultipart();
		          
		          MimeBodyPart messageBodyPart = new MimeBodyPart();
		          DataSource source = 
		            new FileDataSource(attachmentPath);
		          messageBodyPart.setDataHandler(
		            new DataHandler(source));
		          messageBodyPart.setFileName(attachmentName);
		           /* messageBodyPart.addHeaderLine("PFA");*/
		          multipart.addBodyPart(messageBodyPart);
		      
		          // attachment ends
		          // Put parts in message
		           MimeMessage msg = new MimeMessage(session);
			          msg.setSubject(subject);
			          msg.setContent(multipart);
		          //msg.setText(text);
		          System.out.println("text================>"+text);
		
		          msg.setFrom(new InternetAddress(userName));
		
		                      for(int i=0;i<to.length;i++){
		
		          msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to[i]));
		
		                      }
		
		                      for(int i=0;i<cc.length;i++){
		
		          msg.addRecipient(Message.RecipientType.CC, new InternetAddress(cc[i]));
		
		                      }
		
		                      for(int i=0;i<bcc.length;i++){
		
		          msg.addRecipient(Message.RecipientType.BCC, new InternetAddress(bcc[i]));
		
		                      }
		
		          msg.saveChanges();
		
		                      Transport transport = session.getTransport("smtp");
		
		                      transport.connect(host, userName, passWord);
		
		                      transport.sendMessage(msg, msg.getAllRecipients());
		
		                      transport.close();
		
		                      return true;
		
		      }
		
		      catch (Exception mex)
		
		      {
		          mex.printStackTrace();
		           return false;
		
		      }
		
		      }
		


    
    
    
    
    
    
    
    
    
    
    
    


}
