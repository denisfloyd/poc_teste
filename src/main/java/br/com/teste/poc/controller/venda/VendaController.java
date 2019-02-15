package br.com.teste.poc.controller.venda;

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

import br.com.teste.poc.app.venda.VendaAppService;
import br.com.teste.poc.app.venda.dto.VendaDTO;
import br.com.teste.poc.app.venda.dto.VendaRequestDTO;
import br.com.teste.poc.domain.venda.Venda;
import br.eti.nexus.kernel.application.dto.response.CollectionResponseDTO;
import br.eti.nexus.kernel.infrastructure.dynamic.jpa.util.RequisitionUtil;
import lombok.extern.log4j.Log4j2;

@Log4j2
@CrossOrigin
@RestController
@RequestMapping("/v1/venda")
public class VendaController {

	@Autowired
	private VendaAppService appService;

	@Autowired
	private RequisitionUtil<Venda> requisition;

	@PostMapping
	public ResponseEntity<?> inserir(@RequestBody VendaDTO dto) {

		log.debug(" ");
		log.debug(" VendaController.inserir ");
		log.debug(" Parâmetro VendaDTO: " + dto);
		log.debug(" ");

		VendaDTO result = appService.salvar(dto);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable String id, @RequestBody VendaDTO dto) {

		log.debug(" ");
		log.debug(" VendaController.alterar ");
		log.debug(" Parâmetro ID: " + id);
		log.debug(" Parâmetro VendaDTO: " + dto);
		log.debug(" ");

		VendaDTO result = appService.alterar(id, dto);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<CollectionResponseDTO<VendaDTO>> selecionarTodos(VendaRequestDTO request) {

		log.debug(" ");
		log.debug(" VendaController.selecionarTodos ");
		log.debug(" Request: " + request);
		log.debug(" ");

		// SETANDO ATRIBUTOS DA REQUISIÇÃO.
		requisition.setAttributes(request);

		CollectionResponseDTO<VendaDTO> result = appService.selecionarTodos();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluir(@PathVariable String id) {

		log.debug(" ");
		log.debug(" VendaController.excluir ");
		log.debug(" ID: " + id);
		log.debug(" ");

		VendaDTO result = appService.excluir(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
