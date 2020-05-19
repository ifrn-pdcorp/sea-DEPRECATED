package br.edu.ifrn.laj.pdcorp.apisea.models;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Evento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	@Lob
	private String resumo;
	
	private String thumbPath;
	
	@NotNull
	private Calendar inicioInscricao;
	
	@NotNull
	private Calendar fimInscricao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	public String getThumbPath() {
		return thumbPath;
	}

	public void setThumbPath(String thumbPath) {
		this.thumbPath = thumbPath;
	}

	public Calendar getInicioInscricao() {
		return inicioInscricao;
	}

	public void setInicioInscricao(Calendar inicioInscricao) {
		this.inicioInscricao = inicioInscricao;
	}

	public Calendar getFimInscricao() {
		return fimInscricao;
	}

	public void setFimInscricao(Calendar fimInscricao) {
		this.fimInscricao = fimInscricao;
	}

	@Override
	public String toString() {
		return "Evento [id=" + id + ", nome=" + nome + ", resumo=" + resumo + ", thumbPath=" + thumbPath
				+ ", inicioInscricao=" + inicioInscricao + ", fimInscricao=" + fimInscricao + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evento other = (Evento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
