package br.rainformatica.pontoweb.session;

import java.util.Arrays;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;

import br.rainformatica.pontoweb.entity.TbDiaSemana;

@Name("tbDiaSemanaList")
public class TbDiaSemanaList extends EntityQuery<TbDiaSemana> {

	private static final String EJBQL = "select tbDiaSemana from TbDiaSemana tbDiaSemana";
	static final String FIND_ALL = "select tbDiaSemana from TbDiaSemana tbSemana";

	private static final String[] RESTRICTIONS = { "lower(tbDiaSemana.descricao) like lower(concat(#{tbDiaSemanaList.tbDiaSemana.descricao},'%'))", };

	private TbDiaSemana tbDiaSemana = new TbDiaSemana();
	

	public TbDiaSemanaList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public TbDiaSemana getTbDiaSemana() {
		return tbDiaSemana;
	}
}
