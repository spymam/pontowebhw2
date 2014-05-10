package br.rainformatica.pontoweb.session;

import br.rainformatica.pontoweb.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("tbHorasProjetosList")
public class TbHorasProjetosList extends EntityQuery<TbHorasProjetos> {

	private static final String EJBQL = "select tbHorasProjetos from TbHorasProjetos tbHorasProjetos";

	private static final String[] RESTRICTIONS = {
			"lower(tbHorasProjetos.projeto1) like lower(concat(#{tbHorasProjetosList.tbHorasProjetos.projeto1},'%'))",
			"lower(tbHorasProjetos.projeto2) like lower(concat(#{tbHorasProjetosList.tbHorasProjetos.projeto2},'%'))",
			"lower(tbHorasProjetos.projeto3) like lower(concat(#{tbHorasProjetosList.tbHorasProjetos.projeto3},'%'))",
			"lower(tbHorasProjetos.projeto4) like lower(concat(#{tbHorasProjetosList.tbHorasProjetos.projeto4},'%'))",
			"lower(tbHorasProjetos.projeto5) like lower(concat(#{tbHorasProjetosList.tbHorasProjetos.projeto5},'%'))",
			"lower(tbHorasProjetos.projeto6) like lower(concat(#{tbHorasProjetosList.tbHorasProjetos.projeto6},'%'))",
			"lower(tbHorasProjetos.projeto7) like lower(concat(#{tbHorasProjetosList.tbHorasProjetos.projeto7},'%'))",
			"lower(tbHorasProjetos.projeto8) like lower(concat(#{tbHorasProjetosList.tbHorasProjetos.projeto8},'%'))",
			"lower(tbHorasProjetos.projeto9) like lower(concat(#{tbHorasProjetosList.tbHorasProjetos.projeto9},'%'))",
			"lower(tbHorasProjetos.projeto10) like lower(concat(#{tbHorasProjetosList.tbHorasProjetos.projeto10},'%'))", };

	private TbHorasProjetos tbHorasProjetos = new TbHorasProjetos();

	public TbHorasProjetosList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public TbHorasProjetos getTbHorasProjetos() {
		return tbHorasProjetos;
	}
}
