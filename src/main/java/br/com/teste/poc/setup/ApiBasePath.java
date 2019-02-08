package br.com.teste.poc.setup;

import org.springframework.context.annotation.Configuration;

import br.eti.nexus.kernel.infrastructure.api.ConfigBasePath;
import br.eti.nexus.kernel.infrastructure.swagger.NexusApplicationApiBasePathConfig;

@Configuration
@ConfigBasePath(basePackages = { "br.com.teste.poc.controller" }, basePath = "nexus/api")
public class ApiBasePath extends NexusApplicationApiBasePathConfig {
}