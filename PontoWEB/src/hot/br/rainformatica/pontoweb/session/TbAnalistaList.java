package br.rainformatica.pontoweb.session;

import java.util.Arrays;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;

import br.rainformatica.pontoweb.entity.TbAnalista;

@Name("tbAnalistaList")
public class TbAnalistaList extends EntityQuery<TbAnalista> {

	private static final String EJBQL = "select tbAnalista from TbAnalista tbAnalista";

	private static final String[] RESTRICTIONS = {
			"lower(tbAnalista.cliente) like lower(concat(#{tbAnalistaList.tbAnalista.cliente},'%'))",
			"lower(tbAnalista.email) like lower(concat(#{tbAnalistaList.tbAnalista.email},'%'))",
			"lower(tbAnalista.funcao) like lower(concat(#{tbAnalistaList.tbAnalista.funcao},'%'))",
			"lower(tbAnalista.nome) like lower(concat(#{tbAnalistaList.tbAnalista.nome},'%'))",
			"lower(tbAnalista.telefone) like lower(concat(#{tbAnalistaList.tbAnalista.telefone},'%'))", };

	private TbAnalista tbAnalista = new TbAnalista();

	public TbAnalistaList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public TbAnalista getTbAnalista() {
		return tbAnalista;
	}
}
