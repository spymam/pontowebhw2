package br.rainformatica.pontoweb.session;

import java.util.Arrays;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;

import br.rainformatica.pontoweb.entity.TbPermissao;

@Name("tbPermissaoList")
public class TbPermissaoList extends EntityQuery<TbPermissao> {

	private static final String EJBQL = "select tbPermissao from TbPermissao tbPermissao";

	private static final String[] RESTRICTIONS = { "lower(tbPermissao.descricao) like lower(concat(#{tbPermissaoList.tbPermissao.descricao},'%'))", };

	private TbPermissao tbPermissao = new TbPermissao();

	public TbPermissaoList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public TbPermissao getTbPermissao() {
		return tbPermissao;
	}
}
