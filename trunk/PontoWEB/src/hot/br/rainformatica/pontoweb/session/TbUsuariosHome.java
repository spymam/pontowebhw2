package br.rainformatica.pontoweb.session;

import java.util.List;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;

import br.rainformatica.pontoweb.entity.TbAnalista;
import br.rainformatica.pontoweb.entity.TbUsuarios;

@Name("tbUsuariosHome")
public class TbUsuariosHome extends EntityHome<TbUsuarios> {

	@Logger private Log log;

    @In Identity identity;
    @In Credentials credentials;
	@In(create = true)
	TbAnalistaHome tbAnalistaHome;
	Credentials credential = new Credentials();
	TbUsuarios tbUsuarios = new TbUsuarios();
	String nomeUsuario;

	public void setTbUsuariosIdTbUsuario(Integer id) {
		setId(id);
	}

	public Integer getTbUsuariosIdTbUsuario() {
		return (Integer) getId();
	}
	
	@SuppressWarnings("unchecked")
	public List<TbUsuarios> getListaTbUsuarios() {
		List resultList = getEntityManager()
				.createQuery(
						"SELECT e FROM TbUsuarios e WHERE e.login=:login")
				.setParameter("login",
						credential.getUsername()).getResultList();
						//getInstance().getLogin()).getResultList();
		TbUsuarios tbUser = new TbUsuarios();
		
		tbUser = (TbUsuarios) resultList.get(0);
		
		setTbUsuarios(tbUser);
		return resultList;
		
						
	}

	@Override
	protected TbUsuarios createInstance() {
		TbUsuarios tbUsuarios = new TbUsuarios();
		return tbUsuarios;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
		TbAnalista tbAnalista = tbAnalistaHome.getDefinedInstance();
		if (tbAnalista != null) {
			getInstance().setTbAnalista(tbAnalista);
		}
	}

	public boolean isWired() {
		if (getInstance().getTbAnalista() == null)
			return false;
		return true;
	}

	public TbUsuarios getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public Credentials getCredential() {
		return credential;
	}

	public void setCredential(Credentials credential) {
		this.credential = credential;
	}

	public TbUsuarios getTbUsuarios() {
		return tbUsuarios;
	}

	public void setTbUsuarios(TbUsuarios tbUsuarios) {
		this.tbUsuarios = tbUsuarios;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	

}
