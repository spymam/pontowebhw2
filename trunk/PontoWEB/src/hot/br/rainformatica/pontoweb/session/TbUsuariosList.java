package br.rainformatica.pontoweb.session;

import java.util.Arrays;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;

import br.rainformatica.pontoweb.entity.TbUsuarios;

@Name("tbUsuariosList")
public class TbUsuariosList extends EntityQuery<TbUsuarios> {

	private static final String EJBQL = "select tbUsuarios from TbUsuarios tbUsuarios";

	private static final String[] RESTRICTIONS = {
			"lower(tbUsuarios.login) like lower(concat(#{tbUsuariosList.tbUsuarios.login},'%'))",
			"lower(tbUsuarios.nome) like lower(concat(#{tbUsuariosList.tbUsuarios.nome},'%'))",
			"lower(tbUsuarios.senha) like lower(concat(#{tbUsuariosList.tbUsuarios.senha},'%'))", };

	private TbUsuarios tbUsuarios = new TbUsuarios();

	public TbUsuariosList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}
	
	public TbUsuarios login(String login, String senha) {
		
		
	     TbUsuarios usuario = (TbUsuarios) this.getEntityManager().createQuery("select u from TbUsuarios as u where u.login = :login").setParameter("login", login).getSingleResult();
	     
	     if (usuario.getSenha().equals(senha))
	       return usuario;
	     else
	       return null;
	   }

	public TbUsuarios getTbUsuarios() {
		return tbUsuarios;
	}
}
