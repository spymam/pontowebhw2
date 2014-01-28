package br.rainformatica.pontoweb.session;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.contexts.Contexts;
import org.jboss.seam.framework.EntityHome;

import br.rainformatica.pontoweb.entity.TbAnalista;
import br.rainformatica.pontoweb.entity.TbClientes;
import br.rainformatica.pontoweb.entity.TbDiaSemana;
import br.rainformatica.pontoweb.entity.TbHorasColab;
import br.rainformatica.pontoweb.entity.TbProjeto;
import br.rainformatica.pontoweb.entity.TbUsuarios;

@Name("tbHorasColabHome")
public class TbHorasColabHome extends EntityHome<TbHorasColab> {

	@In(create = true)
	TbAnalistaHome tbAnalistaHome;
	@In(create = true)
	TbClientesHome tbClientesHome;
	@In(create = true)
	TbProjetoHome tbProjetoHome;

	TbAnalista tbAnalista = new TbAnalista();
	TbClientes tbClientes = new TbClientes();
	TbProjeto tbProjeto = new TbProjeto();
	TbHorasColab tbHorasColab = new TbHorasColab();

	String diaSemana;
	String totalHorasFormatado;

	public void setTbHorasColabIdTbHorasColab(Integer id) {
		setId(id);
	}

	public Integer getTbHorasColabIdTbHorasColab() {
		return (Integer) getId();
	}

	public void gravarDados() {

		TbUsuarios tbUser = new TbUsuarios();
		tbUser = (TbUsuarios) Contexts.getSessionContext().get("usuario");

		List<TbAnalista> result = getEntityManager()
				.createQuery(
						"SELECT e FROM TbAnalista e WHERE e.nome=:idAnalista")
				.setParameter("idAnalista", tbUser.getNome()).getResultList();

		tbHorasColab.setDiaSemana(diaSemana);
		tbHorasColab.setTbAnalista(result.get(0));
		//tbHorasColab.setTbClientes(tbHorasColab.getTbProjeto().getTbClientes());		

		setInstance(tbHorasColab);

		super.persist();

	}

	public void calculaTotalHoras() {
		
		/*String entrada = tbHorasColab.getEntrada().replace(":", "");
		String saida = tbHorasColab.getSaida().replace(":", "");
		String saidaAlmoco = tbHorasColab.getSaidaAlmoco().replace(":", "");
		String retornoAlmoco = tbHorasColab.getRetornoAlmoco().replace(":", "");
		
		int totalGeral = Integer.parseInt(saida)-Integer.parseInt(entrada);
		int almoco = Integer.parseInt(retornoAlmoco)-Integer.parseInt(saidaAlmoco);
		
		int calculaTotaldeHoras = totalGeral-almoco;
		
		int total = (calculaTotaldeHoras);
		
		tbHorasColab.setTotalHoras(total);*/
	/*	
		Calendar horaEntrada = Calendar.getInstance();
		Calendar horasaida = Calendar.getInstance();
		
		horaEntrada.setTime(tbHorasColab.getEntrada());
		horasaida.setTime(tbHorasColab.getSaida());
		
		long milisEntrada = horaEntrada.getTimeInMillis();  
		
		long milisSaida = horasaida.getTimeInMillis();  
		long diferenca =  milisSaida - milisEntrada;  
		long diferencaHoras = diferenca / 3600000;*/
		
		int horaDaEntrada = tbHorasColab.getEntrada().getHours();
		int minutoDaEntrada = tbHorasColab.getEntrada().getMinutes();
		int horaDaSaida = tbHorasColab.getSaida().getHours();
		int minutoDaSaida = tbHorasColab.getSaida().getMinutes();
		int totalHoras = horaDaSaida - horaDaEntrada;
		int somaMinutos = 0;
		
		int totalMinutos = 0;
		
		if (minutoDaEntrada >= minutoDaSaida) {
			somaMinutos = minutoDaEntrada - minutoDaSaida;			
		}else{
			somaMinutos = minutoDaSaida - minutoDaEntrada;
		}				
		if (somaMinutos >= 60) {
			
			int sobra = somaMinutos - 60;
			totalHoras = totalHoras + 1;
			totalMinutos = sobra;
			Date totalGeraldeHoras = new Date();
			
			totalGeraldeHoras.setHours(totalHoras);
			totalGeraldeHoras.setMinutes(totalMinutos);
			totalGeraldeHoras.setSeconds(00);
			
			tbHorasColab.setTotalHoras(totalGeraldeHoras);
			
		}else if(somaMinutos <= 59){
			totalHoras = totalHoras - 1;
			
			Date totalGeraldeHoras = new Date();
			
			totalGeraldeHoras.setHours(totalHoras);
			totalGeraldeHoras.setMinutes(somaMinutos);
			totalGeraldeHoras.setSeconds(00);
			tbHorasColab.setTotalHoras(totalGeraldeHoras);
		}
		
	}

	@Override
	protected TbHorasColab createInstance() {
		TbHorasColab tbHorasColab = new TbHorasColab();
		return tbHorasColab;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public List<TbDiaSemana> listarDiaSemana;

	public List<TbDiaSemana> getListaTbDiaSemana() {
		List resultList = getEntityManager().createQuery(
				"SELECT e FROM TbDiaSemana e").getResultList();

		return resultList;

	}

	@SuppressWarnings("unchecked")
	public List<TbAnalista> getListaTbAnalista() {
		List resultList = getEntityManager().createQuery(
				"SELECT e FROM TbAnalista e").getResultList();

		return resultList;

	}

	@SuppressWarnings("unchecked")
	public List<TbProjeto> getListaTbProjeto() {
		List resultList = getEntityManager().createQuery(
				"SELECT e FROM TbProjeto e").getResultList();

		return resultList;

	}

	@SuppressWarnings("unchecked")
	public List<TbClientes> getListaTbCLientes() {
		List resultList = getEntityManager().createQuery(
				"SELECT e FROM TbClientes e").getResultList();

		return resultList;

	}

	@SuppressWarnings("deprecation")
	public void addDiaSemana() {
		int dia;

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getTbHorasColab().getData());
		dia = calendar.getTime().getDay();

		//Condicao para alterar o Domingo (0) para (7) na tabela
		if (dia == 0) {
			dia = 7;
		}

		diaSemana = getListaTbDiaDaSemana(dia).get(0).getDescricao();

	}

	@SuppressWarnings("unchecked")
	public List<TbDiaSemana> getListaTbDiaDaSemana(int dia) {
		return getEntityManager()
				.createQuery("SELECT e FROM TbDiaSemana e WHERE e.id=:idsemana")
				.setParameter("idsemana", dia).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<TbAnalista> getAnalista() {
		return getEntityManager()
				.createQuery(
						"SELECT e FROM TbAnalista e WHERE e.idTbAnalista=:idAnalista")
				.setParameter("idAnalista",
						Contexts.getSessionContext().get("usuario"))
				.getResultList();
	}

	public void wire() {
		getInstance();
		TbAnalista tbAnalista = tbAnalistaHome.getDefinedInstance();
		if (tbAnalista != null) {
			getInstance().setTbAnalista(tbAnalista);
		}
		TbClientes tbClientes = tbClientesHome.getDefinedInstance();
		if (tbClientes != null) {
			getInstance().setTbClientes(tbClientes);
		}
		TbProjeto tbProjeto = tbProjetoHome.getDefinedInstance();
		if (tbProjeto != null) {
			getInstance().setTbProjeto(tbProjeto);
		}
	}

	public boolean isWired() {
		//		if (getInstance().getTbAnalista() == null)
		//			return false;
		if (tbHorasColab.getTbClientes() == null)
			return false;
		if (tbHorasColab.getTbProjeto() == null)
			return false;
		return true;
	}

	public TbHorasColab getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<TbDiaSemana> getListarDiaSemana() {
		return listarDiaSemana;
	}

	public TbAnalista getTbAnalista() {
		return tbAnalista;
	}

	public void setTbAnalista(TbAnalista tbAnalista) {
		this.tbAnalista = tbAnalista;
	}

	public TbClientes getTbClientes() {
		return tbClientes;
	}

	public void setTbClientes(TbClientes tbClientes) {
		this.tbClientes = tbClientes;
	}

	public TbProjeto getTbProjeto() {
		return tbProjeto;
	}

	public void setTbProjeto(TbProjeto tbProjeto) {
		this.tbProjeto = tbProjeto;
	}

	public TbHorasColab getTbHorasColab() {
		return tbHorasColab;
	}

	public void setTbHorasColab(TbHorasColab tbHorasColab) {
		this.tbHorasColab = tbHorasColab;
	}

	public String getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}

	public String getTotalHorasFormatado() {
		return totalHorasFormatado;
	}

	public void setTotalHorasFormatado(String totalHorasFormatado) {
		this.totalHorasFormatado = totalHorasFormatado;
	}

}
