
package avscience.server;

//import javax.mail.*;
import java.util.*;
import java.io.*;
//import javax.mail.internet.*;
//import com.sun.mail.smtp.*;
//import avscience.server.*;
public class Mailer
{
	public void mail()
	{
		/*Session session = getSession();
		SMTPMessage message = new SMTPMessage(session);
		Properties props= getMessageProps();
		String subject = props.getProperty("subject");
		String text = props.getProperty("text");
		
		//String addr = props.getProperty("recipient");
	//	String from = props.getProperty("from");
		String from = "mkahrl@adelphia.net";
		InternetAddress[] addresses = getRecipients();
		
		for ( int i=0; i<addresses.length; i++ )
		{
			try
			{
				// = new InternetAddress(addr);
				message = new SMTPMessage(session);
				message.setSubject(subject);
				message.setText(text);
				message.setRecipient(Message.RecipientType.TO, addresses[i]);
				message.setSentDate(new java.util.Date());
				message.setFrom(new InternetAddress(from));
				Provider[] provs = session.getProviders();
				for ( int ii=0; ii<provs.length; ii++ )
				{
					System.out.println("Prov: "+provs[ii].toString());
				}
			//	Provider prov = session.getProvider("SMTP");
				try
				{
					Transport trans = session.getTransport(provs[1]);
					trans.send(message);
				}
				catch(Exception e){System.out.println("Error sending: "+e.toString());}
			}
			catch(Exception e){System.out.println(e.toString());}
		}*/
	}
	
	//public InternetAddress[] getRecipients()
	//{
		/*DAO dao = new DAO();
		String[] adds = dao.getUserAddreses();
	//	String[] adds = {"mark@kahrlconsulting.com"};
		Vector ads = new Vector();
		
		for ( int i = 0; i<adds.length; i++ )
		{
			try
			{
				InternetAddress ia =new InternetAddress(adds[i]);
				ads.add(ia);
			}
			catch(Exception e){System.out.println("Bad address: "+adds[i]+e.toString());}
		}
		InternetAddress[] iads = new InternetAddress[ads.size()];
		Enumeration e = ads.elements();
		int i = 0;
		while ( e.hasMoreElements())
		{
			iads[i] = (InternetAddress) e.nextElement();
			System.out.println("Address: "+iads[i].toString());
			i++;
		}
		return iads;*///return null;
	//}
	
	//Session getSession()
	//{
		/*Properties props = new Properties();
		try
		{
			File file = new File("mail.properties");
			FileInputStream fin = new FileInputStream(file);
			props.load(fin);
			
		}
		catch(Exception e){System.out.println(e.toString());}
		Session session = Session.getDefaultInstance(props);
		return session;*/
	//}
	
	//Properties getMessageProps()
	//{
		/*Properties props = new Properties();
		try
		{
			File file = new File("message.properties");
			FileInputStream fin = new FileInputStream(file);
			props.load(fin);
			
		}
		catch(Exception e){System.out.println(e.toString());}
		return props;*/
	//}
	
	public static void main(String[] args)
	{
		//new Mailer().mail();
		//new Mailer().getRecipients();
	}
}