package br.com.teste.poc.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.eti.nexus.kernel.exception.NexusException;
import br.eti.nexus.kernel.messages.ErrorMessage;
import br.eti.nexus.kernel.messages.domain.ErrorMessageStack;

@Service
public class TesteErroAppService {
	
	@Autowired
	private ErrorMessageStack errorStack;
	
	public void testar() throws NexusException { 
		
		errorStack.addMessage("usuario_invalido");
		errorStack.addMessage("senha_invalida");
		
		errorStack.addMessage("fazenda_invalida", new Object[] { "São João" });
		
		
		
		
		ErrorMessage error = ErrorMessage.builder()
											.title("atencao")
											.error("falha_processo")
											.details(errorStack.getErrors())
											.build();
		
		throw NexusException.of(error);
		
	}

}
