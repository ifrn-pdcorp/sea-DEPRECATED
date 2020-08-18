package br.edu.ifrn.laj.pdcorp.apisea.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "config.upload")
public class UploadConfig {
	
	private String source;
	
	private String rootVirtualAddress;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getRootVirtualAddress() {
		return rootVirtualAddress;
	}

	public void setRootVirtualAddress(String rootVirtualAddress) {
		this.rootVirtualAddress = rootVirtualAddress;
	}

	
	
}
