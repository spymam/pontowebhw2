package br.rainformatica.pontoweb.session;

import java.util.Arrays;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;

import br.rainformatica.pontoweb.entity.TbProjeto;

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
