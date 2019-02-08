package br.com.teste.poc.setup;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import br.eti.nexus.kernel.i18n.provider.II18nDataProviderRepository;
import br.eti.nexus.kernel.i18n.provider.file.I18nProviderFileRepository;
import br.eti.nexus.kernel.infrastructure.util.NexusPropertiesUtil;

@SpringBootApplication(scanBasePackages = { "br.eti.nexus.kernel.*", "br.com.teste.poc" })
@EnableJpaRepositories({ "br.eti.nexus.kernel.*", "br.com.teste.poc.infrastructure.*" })
@EntityScan({ "br.eti.nexus.kernel.*", "*br.com.teste.poc.domain." })
public class TestePocApplication {

	@Autowired
	private NexusPropertiesUtil config;

	public static void main(String[] args) {
		SpringApplication.run(TestePocApplication.class, args);
	}

	@Bean
	public II18nDataProviderRepository createI18nDataProvider() {
		return new I18nProviderFileRepository();
	}

	@Bean
	public JavaMailSender getJavaMailSender() {
//		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//		mailSender.setHost("smtp.gmail.com");
//		mailSender.setPort(587);
//
//		mailSender.setUsername("pfgrocha@gmail.com");
//		mailSender.setPassword("P@trick#123");
//
//		Properties props = mailSender.getJavaMailProperties();
//		props.put("mail.transport.protocol", "smtp");
//		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
//		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.starttls.enable", "true");
//		props.put("mail.debug", "false");
		
		
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(config.getConfiguration("mail.host"));
		mailSender.setPort(Integer.parseInt(config.getConfiguration("mail.port") != null ? config.getConfiguration("mail.port") : "0"));

		mailSender.setUsername(config.getConfiguration("mail.user"));
		mailSender.setPassword(config.getConfiguration("mail.password"));

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", config.getConfiguration("mail.transport.protocol"));
		props.put("mail.smtp.ssl.trust", config.getConfiguration("mail.smtp.ssl.trust"));
		props.put("mail.smtp.auth", config.getConfiguration("mail.smtp.auth"));
		props.put("mail.smtp.starttls.enable", config.getConfiguration("mail.smtp.starttls.enable"));
		props.put("mail.debug", config.getConfiguration("mail.debug"));

		return mailSender;
	}

}
