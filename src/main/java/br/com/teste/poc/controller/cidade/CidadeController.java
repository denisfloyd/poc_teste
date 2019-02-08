package br.com.teste.poc.controller.cidade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.teste.poc.app.cidade.CidadeAppService;
import br.com.teste.poc.app.cidade.dto.CidadeDTO;
import br.com.teste.poc.app.pessoa.dto.PessoaDTO;
import br.com.teste.poc.app.pessoa.dto.PessoaRequestDTO;
import br.com.teste.poc.domain.cidade.Cidade;
import br.eti.nexus.kernel.application.dto.response.CollectionResponseDTO;
import br.eti.nexus.kernel.infrastructure.dynamic.jpa.util.RequisitionUtil;
import lombok.extern.log4j.Log4j2;

@Log4j2
@CrossOrigin
@RestController
@RequestMapping("/v1/cidade")
public class CidadeController {

	@Autowired
	private CidadeAppService appService;

	@Autowired
	private RequisitionUtil<Cidade> requisition;

	@PostMapping
	public ResponseEntity<?> inserir(@RequestBody CidadeDTO dto) {

		log.debug(" ");
		log.debug(" CidadeController.inserir ");
		log.debug(" Parâmetro CidadeDTO: " + dto);
		log.debug(" ");

		CidadeDTO result = appService.salvar(dto);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable String id, @RequestBody CidadeDTO dto) {

		log.debug(" ");
		log.debug(" CidadeController.alterar ");
		log.debug(" Parâmetro ID: " + id);
		log.debug(" Parâmetro PessoaDTO: " + dto);
		log.debug(" ");

		CidadeDTO result = appService.alterar(id, dto);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<CollectionResponseDTO<CidadeDTO>> selecionarTodos(PessoaRequestDTO request) {

		log.debug(" ");
		log.debug(" CidadeController.selecionarTodos ");
		log.debug(" Request: " + request);
		log.debug(" ");

		// SETANDO ATRIBUTOS DA REQUISIÇÃO.
		requisition.setAttributes(request);

		CollectionResponseDTO<CidadeDTO> result = appService.selecionarTodos();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluir(@PathVariable String id) {

		log.debug(" ");
		log.debug(" CidadeController.excluir ");
		log.debug(" ID: " + id);
		log.debug(" ");

		PessoaDTO result = appService.excluir(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
