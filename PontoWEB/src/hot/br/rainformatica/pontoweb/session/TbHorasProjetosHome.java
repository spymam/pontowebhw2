package br.rainformatica.pontoweb.session;

import br.rainformatica.pontoweb.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("tbHorasProjetosHome")
public class TbHorasProjetosHome extends EntityHome<TbHorasProjetos> {

	public void setTbHorasProjetosIdHorasProjeto(Integer id) {
		setId(id);
	}

	public Integer getTbHorasProjetosIdHorasProjeto() {
		return (Integer) getId();
	}

	@Override
	protected TbHorasProjetos createInstance() {
		TbHorasProjetos tbHorasProjetos = new TbHorasProjetos();
		return tbHorasProjetos;
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

	public TbHorasProjetos getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
