package br.rainformatica.pontoweb.session;

import br.rainformatica.pontoweb.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("tbProjetoList")
public class TbProjetoList extends EntityQuery<TbProjeto> {

	private static final String EJBQL = "select tbProjeto from TbProjeto tbProjeto";

	private static final String[] RESTRICTIONS = { "lower(tbProjeto.nome) like lower(concat(#{tbProjetoList.tbProjeto.nome},'%'))", };

	private TbProjeto tbProjeto = new TbProjeto();

	public TbProjetoList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public TbProjeto getTbProjeto() {
		return tbProjeto;
	}
}
