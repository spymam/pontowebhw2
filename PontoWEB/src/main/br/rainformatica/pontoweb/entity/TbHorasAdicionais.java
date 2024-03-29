package br.rainformatica.pontoweb.entity;

// Generated 27/01/2014 10:16:12 by Hibernate Tools 3.4.0.CR1

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TbHorasAdicionais generated by hbm2java
 */
@Entity
@Table(name = "tb_horas_adicionais", catalog = "db_ponto")
public class TbHorasAdicionais implements java.io.Serializable {

	private Integer idTbHorasAdicionais;
	private TbProjeto tbProjeto;
	private TbAnalista tbAnalista;
	private Date totalHoras;
	private Set<TbHorasColab> tbHorasColab = new HashSet<TbHorasColab>(0);

	public TbHorasAdicionais() {
	}

	public TbHorasAdicionais(TbProjeto tbProjeto, TbAnalista tbAnalista,
			Date totalHoras, Set<TbHorasColab> tbHorasColab) {
		this.tbProjeto = tbProjeto;
		this.tbAnalista = tbAnalista;
		this.totalHoras = totalHoras;
		this.tbHorasColab = tbHorasColab;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_tb_horas_adicionais", unique = true, nullable = false)
	public Integer getIdTbHorasAdicionais() {
		return this.idTbHorasAdicionais;
	}

	public void setIdTbHorasAdicionais(Integer idTbHorasAdicionais) {
		this.idTbHorasAdicionais = idTbHorasAdicionais;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tb_projeto")
	public TbProjeto getTbProjeto() {
		return this.tbProjeto;
	}

	public void setTbProjeto(TbProjeto tbProjeto) {
		this.tbProjeto = tbProjeto;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tb_analista")
	public TbAnalista getTbAnalista() {
		return this.tbAnalista;
	}

	public void setTbAnalista(TbAnalista tbAnalista) {
		this.tbAnalista = tbAnalista;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "total_horas", length = 8)
	
	public Date getTotalHoras() {
		return this.totalHoras;
	}

	public void setTotalHoras(Date totalHoras) {
		this.totalHoras = totalHoras;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbHorasAdicionais")
	public Set<TbHorasColab> getTbHorasColab() {
		return tbHorasColab;
	}

	public void setTbHorasColab(Set<TbHorasColab> tbHorasColab) {
		this.tbHorasColab = tbHorasColab;
	}

}
