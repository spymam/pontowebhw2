package br.rainformatica.pontoweb.session;

import br.rainformatica.pontoweb.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("tbHorasProjAnalistaList")
public class TbHorasProjAnalistaList extends EntityQuery<TbHorasProjAnalista> {

	private static final String EJBQL = "select tbHorasProjAnalista from TbHorasProjAnalista tbHorasProjAnalista";

	private static final String[] RESTRICTIONS = {};

	private TbHorasProjAnalista tbHorasProjAnalista = new TbHorasProjAnalista();

	public TbHorasProjAnalistaList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public TbHorasProjAnalista getTbHorasProjAnalista() {
		return tbHorasProjAnalista;
	}
}
