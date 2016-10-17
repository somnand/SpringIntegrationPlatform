package code;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

public class IntegrationApplicationStartup {

	@SuppressWarnings({"resource","unused"})
	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext siContext=new ClassPathXmlApplicationContext("/META-INF/si-components.xml");
		
		MessageChannel channel=siContext.getBean("messageChannel",MessageChannel.class);
		Message<String> message1 = MessageBuilder.withPayload("Hello world - one!").build();
		Message<String> message2 = MessageBuilder.withPayload("Hello world - two!").build();
		Message<String> message3 = MessageBuilder.withPayload("Hello world - three!").build();
		
		/*Message<String> messages[]=new Message<String>[10];
		Message<String> message=null;
		
		for(int i=0;i<10;i++)
		{
			message=MessageBuilder.withPayload("Hello world - "+i).build();
			messages[i]=message;
		}*/
		
		
		try
		{
			System.out.println("Sending message ....");
			channel.send(message1);
			System.out.println("Sending message ....");
			channel.send(message2);
			System.out.println("Sending message ....");
			channel.send(message3);
		}
		catch(RuntimeException re)
		{
			System.out.println("Some error");
			re.printStackTrace();
		}
		finally
		{
			System.out.println("Done! and closing");
			siContext.close();
		}
		
		
		

	}

}
