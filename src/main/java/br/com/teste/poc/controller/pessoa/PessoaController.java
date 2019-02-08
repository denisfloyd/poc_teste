package br.com.teste.poc.controller.pessoa;

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

import br.com.teste.poc.app.pessoa.PessoaAppService;
import br.com.teste.poc.app.pessoa.dto.PessoaDTO;
import br.com.teste.poc.app.pessoa.dto.PessoaRequestDTO;
import br.com.teste.poc.domain.pessoa.Pessoa;
import br.eti.nexus.kernel.application.dto.response.CollectionResponseDTO;
import br.eti.nexus.kernel.infrastructure.dynamic.jpa.util.RequisitionUtil;
import lombok.extern.log4j.Log4j2;

@Log4j2
@CrossOrigin
@RestController
@RequestMapping("/v1/pessoa")
public class PessoaController {

	@Autowired
	private PessoaAppService appService;

	@Autowired
	private RequisitionUtil<Pessoa> requisition;

	@PostMapping
	public ResponseEntity<?> inserir(@RequestBody PessoaDTO dto) {

		log.debug(" ");
		log.debug(" PessoaController.inserir ");
		log.debug(" Parâmetro PessoaDTO: " + dto);
		log.debug(" ");

		PessoaDTO result = appService.salvar(dto);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable String id, @RequestBody PessoaDTO dto) {

		log.debug(" ");
		log.debug(" PessoaController.alterar ");
		log.debug(" Parâmetro ID: " + id);
		log.debug(" Parâmetro PessoaDTO: " + dto);
		log.debug(" ");

		PessoaDTO result = appService.alterar(id, dto);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<CollectionResponseDTO<PessoaDTO>> selecionarTodos(PessoaRequestDTO request) {

		log.debug(" ");
		log.debug(" PessoaController.selecionarTodos ");
		log.debug(" Request: " + request);
		log.debug(" ");

		// SETANDO ATRIBUTOS DA REQUISIÇÃO.
		requisition.setAttributes(request);

		CollectionResponseDTO<PessoaDTO> result = appService.selecionarTodos();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluir(@PathVariable String id) {

		log.debug(" ");
		log.debug(" PessoaController.excluir ");
		log.debug(" ID: " + id);
		log.debug(" ");

		PessoaDTO result = appService.excluir(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
