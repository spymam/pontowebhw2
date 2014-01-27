package br.rainformatica.pontoweb.session;

import br.rainformatica.pontoweb.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("tbClientesList")
public class TbClientesList extends EntityQuery<TbClientes> {

	private static final String EJBQL = "select tbClientes from TbClientes tbClientes";

	private static final String[] RESTRICTIONS = { "lower(tbClientes.nome) like lower(concat(#{tbClientesList.tbClientes.nome},'%'))", };

	private TbClientes tbClientes = new TbClientes();

	public TbClientesList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public TbClientes getTbClientes() {
		return tbClientes;
	}
}
