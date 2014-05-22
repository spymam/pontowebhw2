package br.rainformatica.pontoweb.session;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.contexts.Contexts;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.framework.EntityHome;

import br.rainformatica.pontoweb.entity.TbAnalista;
import br.rainformatica.pontoweb.entity.TbClientes;
import br.rainformatica.pontoweb.entity.TbDiaSemana;
import br.rainformatica.pontoweb.entity.TbHorasColab;
import br.rainformatica.pontoweb.entity.TbHorasProjAnalista;
import br.rainformatica.pontoweb.entity.TbHorasProjetos;
import br.rainformatica.pontoweb.entity.TbProjeto;
import br.rainformatica.pontoweb.entity.TbUsuarios;

@Name("tbHorasColabHome")
public class TbHorasColabHome extends EntityHome<TbHorasColab> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@In(create = true)
	TbAnalistaHome tbAnalistaHome;
	@In(create = true)
	TbClientesHome tbClientesHome;
	@In(create = true)
	TbProjetoHome tbProjetoHome;
	@In(create = true)
	TbHorasColabList tbHorasColabList;

	TbAnalista tbAnalista = new TbAnalista();
	TbClientes tbClientes = new TbClientes();
	TbProjeto tbProjeto = new TbProjeto();
	TbHorasColab tbHorasColab = new TbHorasColab();
	TbUsuarios tbUser = new TbUsuarios();
	public List<TbHorasColab> horasColabs = new ArrayList<TbHorasColab>();
	TbHorasProjetos tbHorasProjetos = new TbHorasProjetos();
	private List<TbHorasProjAnalista> tbHorasProjAnalista = new ArrayList<TbHorasProjAnalista>();
	private List<TbProjeto> listaDeProjetos = new ArrayList<TbProjeto>();
	
	String diaSemana;
	String totalHorasFormatado;
	Date horaTemporaria = new Date();
	Boolean desativaBotao = false;
	Integer posicaoLista = null;
	public List<TbProjeto> tbProjetos = new ArrayList<TbProjeto>();
	List<Date> tbHoras = new ArrayList<Date>();
	private Date hora = new Date();
	

	public void setTbHorasColabIdTbHorasColab(Integer id) {
		setId(id);
		tbUser = (TbUsuarios) Contexts.getSessionContext().get("usuario");
		setTbHorasColab(getInstance());
		setDiaSemana(tbHorasColab.getDiaSemana());
		setTbProjeto(getInstance().getTbProjeto());
		
		if (getInstance().getTbHorasProjAnalista().size() > 0) {
			
			setTbHorasProjAnalista(new ArrayList<TbHorasProjAnalista>());
		tbHorasProjAnalista.addAll(getInstance().getTbHorasProjAnalista());
		} 
		
	}
	
	

	public Integer getTbHorasColabIdTbHorasColab() {
		return (Integer) getId();
	}

	public void addProjetoNaLista(){
		if (tbHorasProjAnalista.size() >= 6) {
			
			FacesMessage fm = new FacesMessage("Senha inválida!"); 
			FacesContext.getCurrentInstance().addMessage("senha", fm);
			
			
			
		}else{
		
			TbUsuarios tbUserr = new TbUsuarios();
			tbUserr = (TbUsuarios) Contexts.getSessionContext().get("usuario");

			List<TbAnalista> result = getEntityManager()
					.createQuery(
							"SELECT e FROM TbAnalista e WHERE e.nome=:idAnalista")
					.setParameter("idAnalista", tbUser.getNome()).getResultList();
			
		List <TbHorasProjAnalista> listaProjHoras = new ArrayList<TbHorasProjAnalista>();
		
		TbHorasProjAnalista tbProj = new TbHorasProjAnalista();
		tbProj.setDataLancamento(tbHorasColab.getData());
		tbProj.setTbAnalista(result.get(0));
		
		tbHorasProjAnalista.add(tbProj);
		
	}
	}
	
	public void removerProjetoDaLista(int posicao){
		tbHorasProjAnalista.remove(posicao);
	}
	
	public void addPosicaoProjetoLista(){
		
		
	}
	public int geraNomeProjeto(){
		
		int numero = 0;
		
		for (int i = 0; i < tbHorasProjAnalista.size(); i++) {
			
			numero++;
		}
		
		
		
		return numero;
		
		
	}
	@Override
	public String update(){
		
		if (tbHorasProjAnalista.size() > 0) {
			
			
				for (int i = 0; i < tbHorasProjAnalista.size(); i++) {
				
				tbHorasProjAnalista.get(i).setTbHorasColab(tbHorasColab);
				
			}
			getInstance().getTbHorasProjAnalista().addAll(tbHorasProjAnalista);
			
			
		}super.update();
		return "updated";
		
	}
	
	
	
	public void gravarDados() {

		TbUsuarios tbUser = new TbUsuarios();
		TbProjeto tbProj = new TbProjeto();
		tbProj = getTbProjeto();
		tbUser = (TbUsuarios) Contexts.getSessionContext().get("usuario");

		List<TbAnalista> result = getEntityManager()
				.createQuery(
						"SELECT e FROM TbAnalista e WHERE e.nome=:idAnalista")
				.setParameter("idAnalista", tbUser.getNome()).getResultList();

		tbHorasColab.setDiaSemana(diaSemana);
		tbHorasColab.setTbAnalista(result.get(0));
		/*tbHorasColab.setTbHorasProjetos(tbHorasProjetos);
		tbHorasColab.getTbHorasProjetos().setTbAnalista(tbHorasColab.getTbAnalista());
		tbHorasColab.getTbHorasProjetos().setTbClientes(tbHorasColab.getTbClientes());
		tbHorasColab.setTbProjeto(tbProj);
		tbHorasColab.getTbHorasProjetos().setProjeto1(tbProj.getNome());
		
		tbHorasColab.getTbHorasProjetos().setDataLancamento(tbHorasColab.getData());	*/	
		//tbHorasColab.setTbClientes(tbHorasColab.getTbProjeto().getTbClientes());
		tbHorasColab.setTbProjeto(getTbProjeto());
		tbHorasColab.getTbHorasProjAnalista().addAll(tbHorasProjAnalista);
		
		for (int i = 0; i < tbHorasProjAnalista.size(); i++) {
			
			tbHorasProjAnalista.get(i).setTbHorasColab(tbHorasColab);
			
		}
		setInstance(tbHorasColab);

		
		super.persist();
		
		clear();
		tbHorasColabList.refresh();
		
		

	}
	public void cancelar(){
		clear();
	}
	public void clear() {
		setInstance(null);		
		setId(null);
		createInstance();	
		tbHorasColab = new TbHorasColab();
		this.setDiaSemana(null);
		tbHorasColab.setDiaSemana(null);
		tbUser = new TbUsuarios();
		
	}

	
	/**/
	 

	public void calcHorasProjeto2(Date horaProjeto, int posicaoCampo) throws Throwable {		
		
		Calendar horasProjetoPrincipal = Calendar.getInstance();
		Calendar horasProjetoAdicional = Calendar.getInstance();
		Calendar tst = Calendar.getInstance();
		// formata e exibe a data e hora atual
		Format formato = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
		horasProjetoPrincipal.setTime(tbHorasColab.getTotalHoras());
		tst.setTime(tbHorasColab.getTotalHoras());
		
		String hora1 = String.valueOf(horaProjeto.getHours()+ String.valueOf(horaProjeto.getMinutes()));
		String hora2 = String.valueOf(tbHorasColab.getTotalHoras().getHours() + String.valueOf(tbHorasColab.getTotalHoras().getMinutes()));
		int horaProj = Integer.valueOf(hora1);
		int totalHor = Integer.valueOf(hora2);
		if (horaProjeto.getTime() > tbHorasColab.getTotalHoras().getTime()) {
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");  
		    String hora = "00:00";  
		    Date horaFormatada = sdf.parse(hora);  
		    System.out.println(formato.format(horaFormatada));  
		    
			horaProjeto = new Date();
			horaProjeto.setHours(0);
			horaProjeto.setMinutes(0);
			horaProjeto.setSeconds(0);
			/*List<Date> tbHoras = new ArrayList<Date>();*/
			tbHorasProjAnalista.get(posicaoCampo).setHoras(horaFormatada);
				for (int i = 0; i < tbHorasProjAnalista.size(); i++) {
				
				
				tst.add(Calendar.MINUTE, -tbHorasProjAnalista.get(i).getHoras().getMinutes());
				tst.add(Calendar.HOUR, -tbHorasProjAnalista.get(i).getHoras().getHours());
				tbHorasColab.setHorasProjetoPrincipal(tst.getTime());
				System.out.println("HORA PROJETO PRINCIPAL : " + formato.format(tst.getTime()));
				System.out.println("HORA PROJETO PRINCIPAL2 : " + formato.format(tbHorasColab.getHorasProjetoPrincipal()));
			
			}
			/*tbHoras.add(posicaoCampo, tbHorasProjAnalista.get(posicaoCampo).getHoras());
			tbHoras.set(posicaoCampo, d);*/
					horasProjetoAdicional.setTime(getHora());
					System.out.println("Horas Projeto Principal: " + formato.format(horasProjetoPrincipal.getTime()));				
					// Subtrae hora e minuto(projeto adicional) do projeto principal
					horasProjetoPrincipal.add(Calendar.MINUTE,  -horasProjetoAdicional.getTime().getMinutes());
					horasProjetoPrincipal.add(Calendar.HOUR, -horasProjetoAdicional.getTime().getHours());
					System.out.println("Total de HOras: " + formato.format(horasProjetoPrincipal.getTime()));
					// formata e exibe o resultado
					formato = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
					System.out.println("Daqui a 60 minutos será: " + formato.format(horasProjetoPrincipal.getTime()));
					tbHorasProjetos.setHorasProjeto1(horasProjetoPrincipal.getTime());
					System.out.println("horas : " + formato.format(tbHorasColab.getHorasProjetoPrincipal().getTime()) );
					System.out.println("hora recebida :" + formato.format(horaProjeto));
					setDesativaBotao(true);
					/*setTbHoras(new ArrayList<Date>());*/
			 
			
		}else{
		
		//Se a data digitada for em branco, este metodo seta um formato de hora "00:00" para prosseguir o calculo.			
		if (horaProjeto == null) {
			
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");  
		    String hora = "00:00";  
		    Date horaFormatada = sdf.parse(hora);  
		    System.out.println(formato.format(horaFormatada));  
		    
			horaProjeto = new Date();
			horaProjeto.setHours(0);
			horaProjeto.setMinutes(0);
			horaProjeto.setSeconds(0);
			/*List<Date> tbHoras = new ArrayList<Date>();*/
			tbHorasProjAnalista.get(posicaoCampo).setHoras(horaFormatada);
				for (int i = 0; i < tbHorasProjAnalista.size(); i++) {
				
				
				tst.add(Calendar.MINUTE, -tbHorasProjAnalista.get(i).getHoras().getMinutes());
				tst.add(Calendar.HOUR, -tbHorasProjAnalista.get(i).getHoras().getHours());
				tbHorasColab.setHorasProjetoPrincipal(tst.getTime());
				System.out.println("HORA PROJETO PRINCIPAL : " + formato.format(tst.getTime()));
				System.out.println("HORA PROJETO PRINCIPAL2 : " + formato.format(tbHorasColab.getHorasProjetoPrincipal()));
			
			}
			/*tbHoras.add(posicaoCampo, tbHorasProjAnalista.get(posicaoCampo).getHoras());
			tbHoras.set(posicaoCampo, d);*/
					horasProjetoAdicional.setTime(getHora());
					System.out.println("Horas Projeto Principal: " + formato.format(horasProjetoPrincipal.getTime()));				
					// Subtrae hora e minuto(projeto adicional) do projeto principal
					horasProjetoPrincipal.add(Calendar.MINUTE,  -horasProjetoAdicional.getTime().getMinutes());
					horasProjetoPrincipal.add(Calendar.HOUR, -horasProjetoAdicional.getTime().getHours());
					System.out.println("Total de HOras: " + formato.format(horasProjetoPrincipal.getTime()));
					// formata e exibe o resultado
					formato = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
					System.out.println("Daqui a 60 minutos será: " + formato.format(horasProjetoPrincipal.getTime()));
					tbHorasProjetos.setHorasProjeto1(horasProjetoPrincipal.getTime());
					System.out.println("horas : " + formato.format(tbHorasColab.getHorasProjetoPrincipal().getTime()) );
					System.out.println("hora recebida :" + formato.format(horaProjeto));
					setDesativaBotao(true);
					/*setTbHoras(new ArrayList<Date>());*/
			
		}else{			
			 
			Date dta = getDataAtual(); //aqui
			dta.setTime(horaProjeto.getTime());
			
			for (int i = 0; i < tbHorasProjAnalista.size(); i++) {
				
				tst.add(Calendar.MINUTE, -tbHorasProjAnalista.get(i).getHoras().getMinutes());
				tst.add(Calendar.HOUR, -tbHorasProjAnalista.get(i).getHoras().getHours());
				
				
				tbHorasColab.setHorasProjetoPrincipal(tst.getTime());
				System.out.println("HORA PROJETO PRINCIPAL : " + formato.format(tst.getTime()));
				System.out.println("HORA PROJETO PRINCIPAL2 : " + formato.format(tbHorasColab.getHorasProjetoPrincipal()));
			}
			
			if (dta.getHours() > tbHorasColab.getTotalHoras().getHours()) {  
				
				tbHorasColab.setHorasProjetoPrincipal(null);
				
				
			}
			/*List<Date> tbHoras = new ArrayList<Date>();*/
			//tbHoras.add(tbHorasProjetos.getHorasProjeto1());
			
			
			/*tbHoras.add(posicaoCampo, tbHorasProjAnalista.get(posicaoCampo).getHoras());
			setHora(tbHorasProjAnalista.get(posicaoCampo).getHoras());
			
				horasProjetoAdicional.setTime(getHora());
					 
					System.out.println("Horas Projeto Principal: " + formato.format(horasProjetoPrincipal.getTime()));				
					
					// Subtrae hora e minuto(projeto adicional) do projeto principal
					horasProjetoPrincipal.add(Calendar.MINUTE,  -horasProjetoAdicional.getTime().getMinutes());
					
					horasProjetoPrincipal.add(Calendar.HOUR, -horasProjetoAdicional.getTime().getHours());
					
					System.out.println("Total de HOras: " + formato.format(horasProjetoPrincipal.getTime()));
					// formata e exibe o resultado
					formato = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
					System.out.println("Daqui a 60 minutos será: " + formato.format(horasProjetoPrincipal.getTime()));
					tbHorasProjetos.setHorasProjeto1(horasProjetoPrincipal.getTime());
					tbHorasColab.setHorasProjetoPrincipal(horasProjetoPrincipal.getTime());
					*/
					System.out.println("horas : " + formato.format(tbHorasColab.getHorasProjetoPrincipal().getTime()) );
					setDesativaBotao(true);
			/*setTbHoras(new ArrayList<Date>());*/
					
				}
				
		}
			}
				
			
			
			
	
	
/*public void calcHorasProjeto(Date horaProjeto, int posicaoCampo) throws Throwable {		
		
		Calendar horasProjetoPrincipal = Calendar.getInstance();
		Calendar horasProjetoAdicional = Calendar.getInstance();
		// formata e exibe a data e hora atual
		Format formato = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
		horasProjetoPrincipal.setTime(tbHorasColab.getTotalHoras());
		horasProjetoPrincipal.setTime(tbHorasProjetos.getHorasProjeto1());
					
		if (horaProjeto == null) {
			
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");  
		    String hora = "00:00";  
		    Date d = sdf.parse(hora);  
		    System.out.println(formato.format(d));  
		    
			horaProjeto = new Date();
			horaProjeto.setHours(0);
			horaProjeto.setMinutes(0);
			horaProjeto.setSeconds(0);
									
			tbHorasProjetos.setHorasProjeto1(tbHorasColab.getTotalHoras());
			List<Date> tbHoras = new ArrayList<Date>();
			
			
			//tbHoras.add(tbHorasProjetos.getHorasProjeto1());
			tbHoras.add(0, tbHorasProjetos.getHorasProjeto2());
			tbHoras.add(1, tbHorasProjetos.getHorasProjeto3());
			tbHoras.add(2, tbHorasProjetos.getHorasProjeto4());
			tbHoras.add(3, tbHorasProjetos.getHorasProjeto5());
			tbHoras.add(4, tbHorasProjetos.getHorasProjeto6());
								
			tbHoras.set(posicaoCampo, d);

			
			for (int i = 0; i < tbHoras.size(); i++) {
				
				if (tbHoras.get(i) == null) {
					finalize();
				}else{
					horasProjetoAdicional.setTime(tbHoras.get(i));
					
					System.out.println("Horas Projeto Principal: " + formato.format(horasProjetoPrincipal.getTime()));				
					
					// Subtrae hora e minuto(projeto adicional) do projeto principal
					horasProjetoPrincipal.add(Calendar.MINUTE,  -horasProjetoAdicional.getTime().getMinutes());
					
					horasProjetoPrincipal.add(Calendar.HOUR, -horasProjetoAdicional.getTime().getHours());
					
					System.out.println("Total de HOras: " + formato.format(horasProjetoPrincipal.getTime()));
					// formata e exibe o resultado
					formato = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
					System.out.println("Daqui a 60 minutos será: " + formato.format(horasProjetoPrincipal.getTime()));
					tbHorasProjetos.setHorasProjeto1(horasProjetoPrincipal.getTime());
					System.out.println("horas : " + formato.format(tbHorasProjetos.getHorasProjeto1().getTime()) );
					System.out.println("hora recebida :" + formato.format(horaProjeto));
					setDesativaBotao(true);
					
					
					

				}
			
			}
		}else{			
			 
			Date dta = getDataAtual(); //aqui
			dta.setTime(horaProjeto.getTime());
			if (dta.getHours() > tbHorasColab.getTotalHoras().getHours()) {  
				
				tbHorasProjetos.setHorasProjeto1(null);
				
			}
			List<Date> tbHoras = new ArrayList<Date>();
			//tbHoras.add(tbHorasProjetos.getHorasProjeto1());
			tbHoras.add(0, tbHorasProjetos.getHorasProjeto2());
			tbHoras.add(1, tbHorasProjetos.getHorasProjeto3());
			tbHoras.add(2, tbHorasProjetos.getHorasProjeto4());
			tbHoras.add(3, tbHorasProjetos.getHorasProjeto5());
			tbHoras.add(4, tbHorasProjetos.getHorasProjeto6());
			
			for (int i = 0; i < tbHoras.size(); i++) {
				
				if (tbHoras.get(i) == null) {
					finalize();
				}else{
					horasProjetoAdicional.setTime(tbHoras.get(i));
					 
					System.out.println("Horas Projeto Principal: " + formato.format(horasProjetoPrincipal.getTime()));				
					
					// Subtrae hora e minuto(projeto adicional) do projeto principal
					horasProjetoPrincipal.add(Calendar.MINUTE,  -horasProjetoAdicional.getTime().getMinutes());
					
					horasProjetoPrincipal.add(Calendar.HOUR, -horasProjetoAdicional.getTime().getHours());
					
					System.out.println("Total de HOras: " + formato.format(horasProjetoPrincipal.getTime()));
					// formata e exibe o resultado
					formato = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
					System.out.println("Daqui a 60 minutos será: " + formato.format(horasProjetoPrincipal.getTime()));
					tbHorasProjetos.setHorasProjeto1(horasProjetoPrincipal.getTime());
					System.out.println("horas : " + formato.format(tbHorasProjetos.getHorasProjeto1().getTime()) );
					setDesativaBotao(true);
					
				}
				
				
			}
				
			}
			
			
	}*/
	
	public void addProjetoLista(int posicao){
		
		tbProjetos.add(posicao, tbProjeto);
	}
	
	public void limpaCamposProjetos(){
		setDesativaBotao(false);
	}
	public void calcHorasProjeto2(Date horaProjeto) throws ParseException {
		
		
		
		//=SE(SOMA(K14:O14)>H14;0;H14-SOMA(K14:O14))
		
		// se a soma de todos os projetos for maior que o total de horas, total de horas - a soma de todos os projetos
		
		if (horaProjeto == null) {
			
			tbHorasProjetos.setHorasProjeto1(tbHorasColab.getTotalHoras());
				
		}else {
			Calendar horasProjetoPrincipal = Calendar.getInstance();
			Calendar horasProjetoAdicional = Calendar.getInstance();
			// formata e exibe a data e hora atual
			Format formato = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
			/*horasProjetoPrincipal.setTime(tbHorasColab.getTotalHoras());*/
			horasProjetoPrincipal.setTime(tbHorasProjetos.getHorasProjeto1());
			
			horasProjetoAdicional.setTime(horaProjeto);
			
			System.out.println("Horas Projeto Principal: " + formato.format(horasProjetoPrincipal.getTime()));
			
			
			// Subtrae hora e minuto(projeto adicional) do projeto principal
			horasProjetoPrincipal.add(Calendar.MINUTE,  -horasProjetoAdicional.getTime().getMinutes());
			
			horasProjetoPrincipal.add(Calendar.HOUR, -horasProjetoAdicional.getTime().getHours());
			
			System.out.println("Total de HOras: " + formato.format(horasProjetoPrincipal.getTime()));
			// formata e exibe o resultado
			formato = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
			System.out.println("Daqui a 60 minutos será: " + formato.format(horasProjetoPrincipal.getTime()));
			tbHorasProjetos.setHorasProjeto1(horasProjetoPrincipal.getTime());
			setHoraTemporaria(horasProjetoPrincipal.getTime());
			System.out.println("horas : " + formato.format(tbHorasProjetos.getHorasProjeto1().getTime()) );
		}
	}
	
	

	@SuppressWarnings("deprecation")
	public void calculaTotalHoras() {
				
		int horaDaEntrada = tbHorasColab.getEntrada().getHours();
		int minutoDaEntrada = tbHorasColab.getEntrada().getMinutes();
		int horaDaSaida = tbHorasColab.getSaida().getHours();
		int minutoDaSaida = tbHorasColab.getSaida().getMinutes();
		/*int totalHoras = horaDaSaida - horaDaEntrada;*/
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
			tbHorasColab.setHorasProjetoPrincipal(totalDeHoras);
			
			Date horasAdicionais = new Date();
			if (totalDeHoras.getHours() >= 8) {
				int hora = totalDeHoras.getHours();
				int minuto = totalDeHoras.getMinutes();
				
				int subtraeHora = hora - 8; // recebe o total de horas e retira 8(horas diarias)
				int horaSubtraida = hora - subtraeHora; //variavel que força a ter 8 horas.
				
				
				if (horaSubtraida <= 8) {
					horasAdicionais.setHours(0);
					horasAdicionais.setMinutes(minuto);
				}
					horasAdicionais.setHours(subtraeHora);
					horasAdicionais.setMinutes(minuto);
					tbHorasColab.setHorasAdicionais(horasAdicionais);
								
			}
			
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
				Date horasAdicionais = new Date();
				if (totalDeHoras.getHours() >= 8) {
					int hora = totalDeHoras.getHours();
					int minuto = totalDeHoras.getMinutes();
					
					int subtraeHora = hora - 8;
					int horaSubtraida = hora - subtraeHora;
					
					if (horaSubtraida <= 8) {
						horasAdicionais.setHours(0);
						horasAdicionais.setMinutes(minuto);
					}
						horasAdicionais.setHours(subtraeHora);
						horasAdicionais.setMinutes(minuto);					
						tbHorasColab.setHorasAdicionais(horasAdicionais);
									
				}
				
				tbHorasColab.setTotalHoras(totalDeHoras);
				tbHorasColab.setHorasProjetoPrincipal(totalDeHoras);
						
				
			}
		
		
		
		
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
	
	public List getListaProjAnalista(){
		
		List toReturn = new ArrayList<List>();
		
		List<TbProjeto>listaTbProjeto = new ArrayList<TbProjeto>();
		
		return horasColabs;
		
	}

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

	public Date getHoraTemporaria() {
		return horaTemporaria;
	}

	public void setHoraTemporaria(Date horaTemporaria) {
		this.horaTemporaria = horaTemporaria;
	}

	public Boolean getDesativaBotao() {
		return desativaBotao;
	}

	public void setDesativaBotao(Boolean desativaBotao) {
		this.desativaBotao = desativaBotao;
	}

	public List<TbProjeto> getTbProjetos() {
		return tbProjetos;
	}

	public void setTbProjetos(List<TbProjeto> tbProjetos) {
		this.tbProjetos = tbProjetos;
	}

	public List<TbHorasProjAnalista> getTbHorasProjAnalista() {
		return tbHorasProjAnalista;
	}

	public void setTbHorasProjAnalista(List<TbHorasProjAnalista> tbHorasProjAnalista) {
		this.tbHorasProjAnalista = tbHorasProjAnalista;
	}



	public Integer getPosicaoLista() {
		return posicaoLista;
	}



	public void setPosicaoLista(Integer posicaoLista) {
		this.posicaoLista = posicaoLista;
	}



	public List<Date> getTbHoras() {
		return tbHoras;
	}



	public void setTbHoras(List<Date> tbHoras) {
		this.tbHoras = tbHoras;
	}



	public Date getHora() {
		return hora;
	}



	public void setHora(Date hora) {
		this.hora = hora;
	}

	
	
	

}
