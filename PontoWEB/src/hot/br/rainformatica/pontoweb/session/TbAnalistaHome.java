package br.rainformatica.pontoweb.session;

import br.rainformatica.pontoweb.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("tbAnalistaHome")
public class TbAnalistaHome extends EntityHome<TbAnalista> {

	public void setTbAnalistaIdTbAnalista(Integer id) {
		setId(id);
	}

	public Integer getTbAnalistaIdTbAnalista() {
		return (Integer) getId();
	}

	@Override
	protected TbAnalista createInstance() {
		TbAnalista tbAnalista = new TbAnalista();
		return tbAnalista;
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

	public TbAnalista getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<TbUsuarios> getTbUsuarioses() {
		return getInstance() == null ? null : new ArrayList<TbUsuarios>(
				getInstance().getTbUsuarioses());
	}

	public List<TbHorasColab> getTbHorasColabs() {
		return getInstance() == null ? null : new ArrayList<TbHorasColab>(
				getInstance().getTbHorasColabs());
	}

}
