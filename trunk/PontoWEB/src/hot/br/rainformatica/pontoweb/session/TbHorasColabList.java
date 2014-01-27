package br.rainformatica.pontoweb.session;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;

import br.rainformatica.pontoweb.entity.TbDiaSemana;
import br.rainformatica.pontoweb.entity.TbHorasColab;

@Name("tbHorasColabList")
public class TbHorasColabList extends EntityQuery<TbHorasColab> {

	private static final String EJBQL = "select tbHorasColab from TbHorasColab tbHorasColab";
	static final String FIND_ALL = "select tbHorasColab from TbHorasColab tbHorasColab";

	private static final String[] RESTRICTIONS = {
			"lower(tbHorasColab.descAtividade) like lower(concat(#{tbHorasColabList.tbHorasColab.descAtividade},'%'))",
			"lower(tbHorasColab.diaSemana) like lower(concat(#{tbHorasColabList.tbHorasColab.diaSemana},'%'))", };

	private TbHorasColab tbHorasColab = new TbHorasColab();
	private List<TbDiaSemana> listaTbDiaSemana = new ArrayList<TbDiaSemana>();

	public TbHorasColabList() {
		setEjbql(FIND_ALL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}
	@SuppressWarnings("unchecked")
	protected void init() throws InterruptedException {
		super.wait();
		listaTbDiaSemana = getEntityManager().createQuery(
				TbDiaSemanaList.FIND_ALL).getResultList();

	}

	public TbHorasColab getTbHorasColab() {
		return tbHorasColab;
	}
	public List<TbDiaSemana> getListaTbDiaSemana() {
		return listaTbDiaSemana;
	}
}
