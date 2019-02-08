package br.com.teste.poc.controller;

import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.teste.poc.app.TesteErroAppService;
import br.eti.nexus.kernel.i18n.provider.I18nProvider;
import br.eti.nexus.kernel.infrastructure.dynamic.sql.infra.DataProviderDynamicSQL;
import br.eti.nexus.kernel.infrastructure.email.html.domain.HTMLMail;
import br.eti.nexus.kernel.infrastructure.email.html.model.HTMLMailModel;
import br.eti.nexus.kernel.infrastructure.email.simple.domain.SimpleMail;
import br.eti.nexus.kernel.infrastructure.email.simple.model.SimpleMailModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@CrossOrigin
@RequestMapping("testar")
@RestController
@Api(tags = { "Teste Controller" })
@SwaggerDefinition(tags = { @Tag(name = "Teste Controller", description = "API de testes - POC Nexus") })
public class TesteController {

	@Autowired
	private DataProviderDynamicSQL dynamicSQL;

	@Autowired
	private I18nProvider provider;

	@Autowired
	private TesteErroAppService testeErro;

	@Autowired
	private SimpleMail mail;

	@Autowired
	private HTMLMail mailHTML;

	@GetMapping("/i18n/{locale}")
	@ApiOperation(value = "Retorna lista de termos.", response = Map.class, responseContainer = "Map")
	public ResponseEntity<?> recuperarConfiguracoes(@PathVariable String locale) {

		String _locale[] = locale.split("_");

		Map<String, String> termos = provider.loadMap(new Locale(_locale[0], _locale[1]));

		return new ResponseEntity<>(termos.toString(), HttpStatus.OK);
	}

	@GetMapping("/erros")
	@ApiOperation(value = "Exexuta requisição de erro")
	public ResponseEntity<?> testarError() {

		testeErro.testar();

		System.out.println("Passou por aqui...");

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/testarSQL")
	@ApiOperation(value = "Executa teste de SQL")
	public ResponseEntity<?> testarSQL(@RequestBody String sql) {

		Object json = null;

		try {
			json = dynamicSQL.executeSQL(sql);
		} catch (Exception e) {

		}

		return new ResponseEntity<>(json, HttpStatus.OK);
	}

	@GetMapping("/sendMail")
	@ApiOperation(value = "Teste de envio de e-mail simples")
	public ResponseEntity<?> sendMail() {

		SimpleMailModel model = SimpleMailModel.builder().from("Patrick Francis Teste <pfgrocha@gmail.com>")
				.to(new String[] { "patrick.gomes@nexus.eti.br" }).message("E-mail Teste").subject("E-mail teste")
				.build();

		String message = "";

		try {
			mail.send(model);
			message = "OK";
		} catch (Exception e) {
			message = e.getMessage();
		}

		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	@GetMapping("/sendMailHTML")
	@ApiOperation(value = "Tesde de envio de e-mail em HTML")
	public ResponseEntity<?> sendMailHTML() {

		HTMLMailModel model = HTMLMailModel.builder().from("Patrick Francis Teste <pfgrocha@gmail.com>")
				.to(new String[] { "pfrocha@outlook.com.br" })
				.messageHTML(
						"<h1 style=\"text-align: center;\"><span style=\"color: #800000;\"><strong>Ola, este &eacute; um texto em formato HTML</strong></span></h1>")
				.subject("E-mail em HTML").build();

		String message = "";

		try {
			mailHTML.send(model);
			message = "OK";
		} catch (Exception e) {
			message = e.getMessage();
		}

		return new ResponseEntity<>(message, HttpStatus.OK);
	}

}
