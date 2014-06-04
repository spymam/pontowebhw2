package br.rainformatica.pontoweb.session;

import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

import br.rainformatica.pontoweb.entity.TbClientes;
import br.rainformatica.pontoweb.entity.TbHorasColab;
import br.rainformatica.pontoweb.entity.TbProjeto;

@Name("tbClientesHome")
public class TbClientesHome extends EntityHome<TbClientes> {

	public void setTbClientesIdTbClientes(Integer id) {
		setId(id);
	}

	public Integer getTbClientesIdTbClientes() {
		return (Integer) getId();
	}

	@Override
	protected TbClientes createInstance() {
		TbClientes tbClientes = new TbClientes();
		return tbClientes;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public TbClientes getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<TbProjeto> getTbProjetos() {
		return getInstance() == null ? null : new ArrayList<TbProjeto>(
				getInstance().getTbProjetos());
	}

	public List<TbHorasColab> getTbHorasColabs() {
		return getInstance() == null ? null : new ArrayList<TbHorasColab>(
				getInstance().getTbHorasColabs());
	}

}
