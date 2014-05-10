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
import javax.persistence.OneToMany;
import javax.persistence.Table;

*//**
 * TbAnalista generated by hbm2java
 *//*
@Entity
@Table(name = "tb_analista", catalog = "db_ponto")
public class TbAnalista implements java.io.Serializable {

	private Integer idTbAnalista;
	private String cliente;
	private String email;
	private String funcao;
	private String nome;
	private String telefone;
	private Set<TbUsuarios> tbUsuarioses = new HashSet<TbUsuarios>(0);
	private Set<TbHorasColab> tbHorasColabs = new HashSet<TbHorasColab>(0);

	public TbAnalista() {
	}

	public TbAnalista(String cliente, String email, String funcao, String nome,
			String telefone, Set<TbUsuarios> tbUsuarioses,
			Set<TbHorasColab> tbHorasColabs) {
		this.cliente = cliente;
		this.email = email;
		this.funcao = funcao;
		this.nome = nome;
		this.telefone = telefone;
		this.tbUsuarioses = tbUsuarioses;
		this.tbHorasColabs = tbHorasColabs;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_Tb_Analista", unique = true, nullable = false)
	public Integer getIdTbAnalista() {
		return this.idTbAnalista;
	}

	public void setIdTbAnalista(Integer idTbAnalista) {
		this.idTbAnalista = idTbAnalista;
	}

	@Column(name = "cliente")
	public String getCliente() {
		return this.cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	@Column(name = "email")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "funcao")
	public String getFuncao() {
		return this.funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	@Column(name = "nome")
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "telefone")
	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbAnalista")
	public Set<TbUsuarios> getTbUsuarioses() {
		return this.tbUsuarioses;
	}

	public void setTbUsuarioses(Set<TbUsuarios> tbUsuarioses) {
		this.tbUsuarioses = tbUsuarioses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbAnalista")
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

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TbAnalista generated by hbm2java
 */
@Entity
@Table(name = "tb_analista", catalog = "db_ponto")
public class TbAnalista implements java.io.Serializable {

	private Integer idTbAnalista;
	private String cliente;
	private String email;
	private String funcao;
	private String nome;
	private String telefone;
	private Set<TbUsuarios> tbUsuarioses = new HashSet<TbUsuarios>(0);
	private Set<TbHorasColab> tbHorasColabs = new HashSet<TbHorasColab>(0);
	private Set<TbHorasAdicionais> tbHorasAdicionaises = new HashSet<TbHorasAdicionais>(0);
	private Set<TbHorasProjetos> tbHorasProjetos = new HashSet<TbHorasProjetos>(0);

	public TbAnalista() {
	}

	public TbAnalista(String cliente, String email, String funcao, String nome,
			String telefone, Set<TbUsuarios> tbUsuarioses, Set<TbHorasAdicionais> tbHorasAdicionaises,
			Set<TbHorasColab> tbHorasColabs, Set<TbHorasProjetos> tbHorasProjetos) {
		this.cliente = cliente;
		this.email = email;
		this.funcao = funcao;
		this.nome = nome;
		this.telefone = telefone;
		this.tbUsuarioses = tbUsuarioses;
		this.tbHorasColabs = tbHorasColabs;
		this.tbHorasAdicionaises = tbHorasAdicionaises;
		this.tbHorasProjetos = tbHorasProjetos;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_Tb_Analista", unique = true, nullable = false)
	public Integer getIdTbAnalista() {
		return this.idTbAnalista;
	}

	public void setIdTbAnalista(Integer idTbAnalista) {
		this.idTbAnalista = idTbAnalista;
	}

	@Column(name = "cliente")
	public String getCliente() {
		return this.cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	@Column(name = "email")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "funcao")
	public String getFuncao() {
		return this.funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	@Column(name = "nome")
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "telefone")
	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbAnalista")
	public Set<TbUsuarios> getTbUsuarioses() {
		return this.tbUsuarioses;
	}

	public void setTbUsuarioses(Set<TbUsuarios> tbUsuarioses) {
		this.tbUsuarioses = tbUsuarioses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbAnalista")
	public Set<TbHorasColab> getTbHorasColabs() {
		return this.tbHorasColabs;
	}

	public void setTbHorasColabs(Set<TbHorasColab> tbHorasColabs) {
		this.tbHorasColabs = tbHorasColabs;
	}
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbAnalista")
	public Set<TbHorasAdicionais> getTbHorasAdicionais() {
		return this.tbHorasAdicionaises;
	}

	public void setTbHorasAdicionais(Set<TbHorasAdicionais> tbHorasAdicionaises) {
		this.tbHorasAdicionaises = tbHorasAdicionaises;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbAnalista")
	public Set<TbHorasProjetos> getTbHorasProjetos() {
		return this.tbHorasProjetos;
	}

	public void setTbHorasProjetos(Set<TbHorasProjetos> tbHorasProjetos) {
		this.tbHorasProjetos = tbHorasProjetos;
	}
}
