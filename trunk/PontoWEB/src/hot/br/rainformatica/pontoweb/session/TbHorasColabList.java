package br.rainformatica.pontoweb.session;

import br.rainformatica.pontoweb.entity.*;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;
import java.util.List;

@Name("tbHorasColabList")
public class TbHorasColabList extends EntityQuery<TbHorasColab> {

	private static final String EJBQL = "select tbHorasColab from TbHorasColab tbHorasColab";
	private static final String EJBQL2 = "select tbHorasColab from TbHorasColab tbHorasColab where tbHorasColab.tbAnalista.nome";

	String nomeAnalista = null;
	private static final String[] RESTRICTIONS = {
			"lower(tbHorasColab.descAtividade) like lower(concat(#{tbHorasColabList.tbHorasColab.descAtividade},'%'))",
			"lower(tbHorasColab.diaSemana) like lower(concat(#{tbHorasColabList.tbHorasColab.diaSemana},'%'))", };

	private TbHorasColab tbHorasColab = new TbHorasColab();

	public TbHorasColabList() {
		
		String nome = userLogado();
		
		/*setEjbql("select tbHorasColab from TbHorasColab tbHorasColab where tbHorasColab.descAtividade = " +getIdentity().getUsername());
		*/setEjbql("select tbHorasColab from TbHorasColab tbHorasColab where tbHorasColab.tbAnalista.nome = " +"'"+nome+"'"+ "order by idTbHorasColab DESC");
		
		
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(10);
	}
	
	
	/*public TbHorasColabList(String nome) {
		setEjbql("select tbHorasColab from TbHorasColab tbHorasColab where tbHorasColab.tbAnalista.nome" + nome);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
		
	}
	*/
public List<TbHorasColab> getListaTbHorasPorAnalista(String nome) {
		
		return getEntityManager()
				.createQuery("SELECT e FROM TbHorasColab e WHERE e.tbAnalista.nome=:nome")
				.setParameter("nome", nome).getResultList();
		
		
		
		
	}


public String userLogado (){
	List<TbUsuarios> result = getEntityManager()
	.createQuery(
			"SELECT e FROM TbUsuarios e WHERE e.login=:login")
	.setParameter("login", getIdentity().getUsername()).getResultList();
	
	
	return result.get(0).getNome();
}

	public TbHorasColab getTbHorasColab() {
		return tbHorasColab;
	}
}
