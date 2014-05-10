package br.rainformatica.pontoweb.session;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.contexts.Contexts;
import org.jboss.seam.framework.EntityHome;

import br.rainformatica.pontoweb.entity.TbAnalista;
import br.rainformatica.pontoweb.entity.TbClientes;
import br.rainformatica.pontoweb.entity.TbDiaSemana;
import br.rainformatica.pontoweb.entity.TbHorasColab;
import br.rainformatica.pontoweb.entity.TbHorasProjetos;
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
	TbUsuarios tbUser = new TbUsuarios();
	public List<TbHorasColab> horasColabs = new ArrayList<TbHorasColab>();
	TbHorasProjetos tbHorasProjetos = new TbHorasProjetos();
	
	
	

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
		tbHorasColab.setTbHorasProjetos(tbHorasProjetos);
		tbHorasColab.getTbHorasProjetos().setTbAnalista(tbHorasColab.getTbAnalista());
		tbHorasColab.getTbHorasProjetos().setTbClientes(tbHorasColab.getTbClientes());
		tbHorasColab.getTbHorasProjetos().setProjeto1(tbHorasColab.getTbProjeto().getNome());
		
		
		//tbHorasColab.setTbClientes(tbHorasColab.getTbProjeto().getTbClientes());		

		setInstance(tbHorasColab);

		super.persist();
		clear();
		
		

	}
	public void cancelar(){
		clear();
	}
	public void clear() {
		setInstance(null);		
		setId(null);
		createInstance();	
		tbHorasColab = new TbHorasColab();
	}

	
	/**/


	public void calculaTotalHoras() {
				
		int horaDaEntrada = tbHorasColab.getEntrada().getHours();
		int minutoDaEntrada = tbHorasColab.getEntrada().getMinutes();
		int horaDaSaida = tbHorasColab.getSaida().getHours();
		int minutoDaSaida = tbHorasColab.getSaida().getMinutes();
		int totalHoras = horaDaSaida - horaDaEntrada;
		int saidaDoAlmoco = 0;
		int minutoSaidaAlmoco = 0;
		int retornoDoAlmoco = 0;
		int minutoDoRetAlmoco = 0;
		if(tbHorasColab.getSaidaAlmoco() == null){
			saidaDoAlmoco = 00;
			minutoSaidaAlmoco = 00;
		}	else{
			saidaDoAlmoco = tbHorasColab.getSaidaAlmoco().getHours();
			minutoSaidaAlmoco = tbHorasColab.getSaidaAlmoco().getMinutes();
		}
		
		if(tbHorasColab.getRetornoAlmoco() == null){
		retornoDoAlmoco = 00;
		minutoDoRetAlmoco = 00;
		}else{
			retornoDoAlmoco = tbHorasColab.getRetornoAlmoco().getHours();
			minutoDoRetAlmoco = tbHorasColab.getRetornoAlmoco().getMinutes();
		}
		
		if(saidaDoAlmoco == 0 || retornoDoAlmoco == 0){
			
			int somaEntrada = horaDaEntrada * 60 + minutoDaEntrada;
			int somaSaida = horaDaSaida * 60 + minutoDaSaida;
			int somaTotal = somaSaida - somaEntrada;
			int divideMinutos = somaTotal / 60;
			int restoDivisao = somaTotal % 60;
			
			Date totalDeHoras = new Date();
			totalDeHoras.setHours(divideMinutos);
			totalDeHoras.setMinutes(restoDivisao);
			totalDeHoras.setSeconds(00);
			
			tbHorasColab.setTotalHoras(totalDeHoras);
			
			}else {
				
				int somaEntrada = horaDaEntrada * 60 + minutoDaEntrada;
				int somaSaidaAlmoco = saidaDoAlmoco * 60 + minutoSaidaAlmoco;
				int somaTotal1 = somaSaidaAlmoco - somaEntrada;
				
				int somaRetAlmoco = retornoDoAlmoco * 60 + minutoDoRetAlmoco;
				int somaSaida = horaDaSaida * 60 + minutoDaSaida;
				int somaTotal2 = somaSaida - somaRetAlmoco;
				
				int somaTotal = somaTotal1 + somaTotal2;
				int divideMinutos = somaTotal / 60;
				int restoDivisao = somaTotal % 60;
				
				Date totalDeHoras = new Date();
				totalDeHoras.setHours(divideMinutos);
				totalDeHoras.setMinutes(restoDivisao);
				totalDeHoras.setSeconds(00);
				
				tbHorasColab.setTotalHoras(totalDeHoras);
				
				
			}
		
		
		/*10:30 + 20:15 

		10:30 = 10 * 60 + 30 = 630 
		20:15 = 20 * 60 + 15 = 1215 
		630 + 1215 = 1845 
		1845 / 60 = 30 
		1845 % 60 = 45*/
		
		/*int totalMinutos = 0;
		
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
			
			if (condition) {
				
			}
			Date totalGeraldeHoras = new Date();
			
			totalGeraldeHoras.setHours(totalHoras);
			totalGeraldeHoras.setMinutes(somaMinutos);
			totalGeraldeHoras.setSeconds(00);
			tbHorasColab.setTotalHoras(totalGeraldeHoras);
		}*/
		
		
	}

	@Override
	protected TbHorasColab createInstance() {
		TbHorasColab tbHorasColab = new TbHorasColab();
		
		tbUser = (TbUsuarios) Contexts.getSessionContext().get("usuario");
		
		
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
	public List<TbProjeto> getListaProjetoPorCliente() {
		int cliente = tbHorasColab.getTbClientes().getIdTbClientes();
		
		List resultList = getEntityManager()
				.createQuery("SELECT e FROM TbProjeto e WHERE e.tbClientes.idTbClientes=:idCliente")
				.setParameter("idCliente", cliente).getResultList();
		
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
	public List<TbHorasColab> getListaTbHorasPorAnalista(String nome) {
		
		return getEntityManager()
				.createQuery("SELECT e FROM TbHorasColab e WHERE e.tbAnalista.nome=:nome")
				.setParameter("nome", nome).getResultList();
		
		
		
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

	public TbUsuarios getTbUser() {
		return tbUser;
	}

	public void setTbUser(TbUsuarios tbUser) {
		this.tbUser = tbUser;
	}

	public List<TbHorasColab> getHorasColabs() {
		
		if (horasColabs.size() == 0) {
			horasColabs.add(getInstance());
			
			horasColabs.get(0).setData(new Date());
			
			
			
			
		}
		return horasColabs;
	}

	public Date getDataAtual(){
		return new Date();
		
	}
	public void setHorasColabs(List<TbHorasColab> horasColabs) {
		this.horasColabs = horasColabs;
	}

	public TbHorasProjetos getTbHorasProjetos() {
		return tbHorasProjetos;
	}

	public void setTbHorasProjetos(TbHorasProjetos tbHorasProjetos) {
		this.tbHorasProjetos = tbHorasProjetos;
	}

	
	

}
