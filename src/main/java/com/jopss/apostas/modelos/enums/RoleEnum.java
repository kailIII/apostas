package com.jopss.apostas.modelos.enums;

/**
 * Roles especificas para o Spring Security.
 */
public enum RoleEnum {

	ROLE_LOGADO("Logado", "Usuário logado, mas sem seleção com grupos."),
	ROLE_ADMIN("Administrador", "Acesso administrativo a um grupo, como gerenciar processos ou adicionar pessoas."),
	ROLE_VISITANTE("Visitante", "Somente pode visualizar dados de um grupo. Sem acesso a cadastros.");

	private final String description;

	private final String title;

	private RoleEnum(String title, String description) {
		this.title = title;
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}
	
	public String getName() {
		return this.name();
	}

	public boolean isAdmin() {
		return this.equals(ROLE_ADMIN);
	}

	public boolean isVisitante() {
		return this.equals(ROLE_VISITANTE);
	}
}
