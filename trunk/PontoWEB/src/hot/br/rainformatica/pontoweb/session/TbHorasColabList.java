package br.rainformatica.pontoweb.session;

import br.rainformatica.pontoweb.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("tbHorasColabList")
public class TbHorasColabList extends EntityQuery<TbHorasColab> {

	private static final String EJBQL = "select tbHorasColab from TbHorasColab tbHorasColab";

	private static final String[] RESTRICTIONS = {
			"lower(tbHorasColab.descAtividade) like lower(concat(#{tbHorasColabList.tbHorasColab.descAtividade},'%'))",
			"lower(tbHorasColab.diaSemana) like lower(concat(#{tbHorasColabList.tbHorasColab.diaSemana},'%'))", };

	private TbHorasColab tbHorasColab = new TbHorasColab();

	public TbHorasColabList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public TbHorasColab getTbHorasColab() {
		return tbHorasColab;
	}
}
