package sandbox;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		
		BeanFactory spring = new ClassPathXmlApplicationContext("/spring/spring.xml");
		
		System.out.println("*********** démarrage appli  **************");
		Client client = spring.getBean(Client.class);
		Fournisseur f = (Fournisseur)spring.getBean("autreFournisseur");
		client.faireQQChose();
		System.out.println(f == client.getF());

	}

}
