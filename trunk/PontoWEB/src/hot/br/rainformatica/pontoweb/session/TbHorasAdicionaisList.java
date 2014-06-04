package br.rainformatica.pontoweb.session;

import java.util.Arrays;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;

import br.rainformatica.pontoweb.entity.TbHorasAdicionais;

@Name("tbHorasAdicionaisList")
public class TbHorasAdicionaisList extends EntityQuery<TbHorasAdicionais> {

	private static final String EJBQL = "select tbHorasAdicionais from TbHorasAdicionais tbHorasAdicionais";

	private static final String[] RESTRICTIONS = {};

	private TbHorasAdicionais tbHorasAdicionais = new TbHorasAdicionais();

	public TbHorasAdicionaisList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public TbHorasAdicionais getTbHorasAdicionais() {
		return tbHorasAdicionais;
	}
}
