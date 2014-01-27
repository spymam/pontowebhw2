package br.rainformatica.pontoweb.session;

import br.rainformatica.pontoweb.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("tbProjetoHome")
public class TbProjetoHome extends EntityHome<TbProjeto> {

	@In(create = true)
	TbClientesHome tbClientesHome;

	public void setTbProjetoIdTbProjeto(Integer id) {
		setId(id);
	}

	public Integer getTbProjetoIdTbProjeto() {
		return (Integer) getId();
	}

	@Override
	protected TbProjeto createInstance() {
		TbProjeto tbProjeto = new TbProjeto();
		return tbProjeto;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
		TbClientes tbClientes = tbClientesHome.getDefinedInstance();
		if (tbClientes != null) {
			getInstance().setTbClientes(tbClientes);
		}
	}

	public boolean isWired() {
		if (getInstance().getTbClientes() == null)
			return false;
		return true;
	}

	public TbProjeto getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<TbHorasColab> getTbHorasColabs() {
		return getInstance() == null ? null : new ArrayList<TbHorasColab>(
				getInstance().getTbHorasColabs());
	}

}
