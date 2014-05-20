/*package br.rainformatica.pontoweb.entity;

// Generated 23/01/2014 17:41:24 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.validator.NotNull;

*//**
 * TbProjeto generated by hbm2java
 *//*
@Entity
@Table(name = "tb_projeto", catalog = "db_ponto")
public class TbProjeto implements java.io.Serializable {

	private Integer idTbProjeto;
	private TbClientes tbClientes;
	private String nome;
	private Set<TbHorasColab> tbHorasColabs = new HashSet<TbHorasColab>(0);

	public TbProjeto() {
	}

	public TbProjeto(TbClientes tbClientes) {
		this.tbClientes = tbClientes;
	}

	public TbProjeto(TbClientes tbClientes, String nome,
			Set<TbHorasColab> tbHorasColabs) {
		this.tbClientes = tbClientes;
		this.nome = nome;
		this.tbHorasColabs = tbHorasColabs;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_Tb_Projeto", unique = true, nullable = false)
	public Integer getIdTbProjeto() {
		return this.idTbProjeto;
	}

	public void setIdTbProjeto(Integer idTbProjeto) {
		this.idTbProjeto = idTbProjeto;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cliente", nullable = false)
	@NotNull
	public TbClientes getTbClientes() {
		return this.tbClientes;
	}

	public void setTbClientes(TbClientes tbClientes) {
		this.tbClientes = tbClientes;
	}

	@Column(name = "nome")
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbProjeto")
	public Set<TbHorasColab> getTbHorasColabs() {
		return this.tbHorasColabs;
	}

	public void setTbHorasColabs(Set<TbHorasColab> tbHorasColabs) {
		this.tbHorasColabs = tbHorasColabs;
	}

}
*/

package br.rainformatica.pontoweb.entity;

// Generated 23/01/2014 17:41:24 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.validator.NotNull;

/**
 * TbProjeto generated by hbm2java
 */
@Entity
@Table(name = "tb_projeto", catalog = "db_ponto")
public class TbProjeto implements java.io.Serializable {

	private Integer idTbProjeto;
	private TbClientes tbClientes;
	private String nome;
	private Set<TbHorasColab> tbHorasColabs = new HashSet<TbHorasColab>(0);
	private Set<TbHorasAdicionais> tbHorasAdicionais = new HashSet<TbHorasAdicionais>(0);
	private Set<TbHorasProjAnalista> tbHorasProjAnalistas = new HashSet<TbHorasProjAnalista>(0);

	public TbProjeto() {
	}

	public TbProjeto(TbClientes tbClientes) {
		this.tbClientes = tbClientes;
	}

	public TbProjeto(TbClientes tbClientes, String nome,
			Set<TbHorasColab> tbHorasColabs, Set<TbHorasAdicionais> tbHorasAdicionais, Set<TbHorasProjAnalista> tbHorasProjAnalistas) {
		this.tbClientes = tbClientes;
		this.nome = nome;
		this.tbHorasColabs = tbHorasColabs;
		this.tbHorasAdicionais = tbHorasAdicionais;
		this.tbHorasProjAnalistas = tbHorasProjAnalistas;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_Tb_Projeto", unique = true, nullable = false)
	public Integer getIdTbProjeto() {
		return this.idTbProjeto;
	}

	public void setIdTbProjeto(Integer idTbProjeto) {
		this.idTbProjeto = idTbProjeto;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cliente", nullable = false)
	@NotNull
	public TbClientes getTbClientes() {
		return this.tbClientes;
	}

	public void setTbClientes(TbClientes tbClientes) {
		this.tbClientes = tbClientes;
	}

	@Column(name = "nome")
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbProjeto")
	public Set<TbHorasColab> getTbHorasColabs() {
		return this.tbHorasColabs;
	}

	public void setTbHorasColabs(Set<TbHorasColab> tbHorasColabs) {
		this.tbHorasColabs = tbHorasColabs;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbProjeto")
	public Set<TbHorasAdicionais> getTbHorasAdicionais() {
		return tbHorasAdicionais;
	}

	public void setTbHorasAdicionais(Set<TbHorasAdicionais> tbHorasAdicionais) {
		this.tbHorasAdicionais = tbHorasAdicionais;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbProjeto")
	public Set<TbHorasProjAnalista> getTbHorasProjAnalistas() {
		return tbHorasProjAnalistas;
	}

	public void setTbHorasProjAnalistas(
			Set<TbHorasProjAnalista> tbHorasProjAnalistas) {
		this.tbHorasProjAnalistas = tbHorasProjAnalistas;
	}
	
	
	

}