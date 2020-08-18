package br.edu.ifrn.laj.pdcorp.apisea.enums;

public enum TypeImage {
	
	AVATAR_USER("avatar_"), THUMB_EVENT("event_");
	
	private String prefix;
	
	private TypeImage(String prefix) {
		this.prefix = prefix;
	}
	
	public String getPrefix() {
		return prefix;
	}
}
