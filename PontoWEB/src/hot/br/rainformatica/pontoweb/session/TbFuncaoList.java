package br.rainformatica.pontoweb.session;

import br.rainformatica.pontoweb.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("tbFuncaoList")
public class TbFuncaoList extends EntityQuery<TbFuncao> {

	private static final String EJBQL = "select tbFuncao from TbFuncao tbFuncao";

	private static final String[] RESTRICTIONS = { "lower(tbFuncao.descricao) like lower(concat(#{tbFuncaoList.tbFuncao.descricao},'%'))", };

	private TbFuncao tbFuncao = new TbFuncao();

	public TbFuncaoList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public TbFuncao getTbFuncao() {
		return tbFuncao;
	}
}
