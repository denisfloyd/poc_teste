package br.com.teste.poc.setup;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

/**
 * Classe responsável pelo carregamento das propriedades de ambiente.
 * 
 * @author Patrick Francis Gomes Rocha - patrick.gomes@nexus.eti.br
 *
 */
@Configuration
@Profile("prod")
@PropertySource("file:./config/application.properties")
public class ProdConfiguration  {

}
