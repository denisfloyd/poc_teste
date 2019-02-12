package br.com.teste.poc.controller.produto;

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

import br.com.teste.poc.app.produto.ProdutoAppService;
import br.com.teste.poc.app.produto.dto.ProdutoDTO;
import br.com.teste.poc.app.produto.dto.ProdutoRequestDTO;
import br.com.teste.poc.domain.produto.Produto;
import br.eti.nexus.kernel.application.dto.response.CollectionResponseDTO;
import br.eti.nexus.kernel.infrastructure.dynamic.jpa.util.RequisitionUtil;
import lombok.extern.log4j.Log4j2;

/**
 * 
 * @author Denis M. Ladeira
 *
 */
@Log4j2
@CrossOrigin
@RestController
@RequestMapping("/v1/produto")
public class ProdutoController {

	@Autowired
	private ProdutoAppService appService;

	@Autowired
	private RequisitionUtil<Produto> requisition;

	@PostMapping
	public ResponseEntity<?> inserir(@RequestBody ProdutoDTO dto) {

		log.debug(" ");
		log.debug(" ProdutoController.inserir ");
		log.debug(" Parâmetro ProdutoDTO: " + dto);
		log.debug(" ");

		ProdutoDTO result = appService.salvar(dto);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable String id, @RequestBody ProdutoDTO dto) {

		log.debug(" ");
		log.debug(" ProdutoController.alterar ");
		log.debug(" Parâmetro ID: " + id);
		log.debug(" Parâmetro ProdutoDTO: " + dto);
		log.debug(" ");

		ProdutoDTO result = appService.alterar(id, dto);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<CollectionResponseDTO<ProdutoDTO>> selecionarTodos(ProdutoRequestDTO request) {

		log.debug(" ");
		log.debug(" ProdutoController.selecionarTodos ");
		log.debug(" Request: " + request);
		log.debug(" ");

		// SETANDO ATRIBUTOS DA REQUISIÇÃO.
		requisition.setAttributes(request);

		CollectionResponseDTO<ProdutoDTO> result = appService.selecionarTodos();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluir(@PathVariable String id) {

		log.debug(" ");
		log.debug(" PessoaController.excluir ");
		log.debug(" ID: " + id);
		log.debug(" ");

		ProdutoDTO result = appService.excluir(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
