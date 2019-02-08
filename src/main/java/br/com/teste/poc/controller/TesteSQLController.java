package br.com.teste.poc.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.eti.nexus.kernel.infrastructure.dynamic.sql.api.AbstractDynamicSQLController;

@CrossOrigin
@RestController
@RequestMapping("/v1/testeSQLDynamic")
public class TesteSQLController extends AbstractDynamicSQLController {

}
