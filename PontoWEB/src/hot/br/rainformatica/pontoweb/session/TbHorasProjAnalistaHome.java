package br.rainformatica.pontoweb.session;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

import br.rainformatica.pontoweb.entity.TbHorasProjAnalista;

@Name("tbHorasProjAnalistaHome")
public class TbHorasProjAnalistaHome extends EntityHome<TbHorasProjAnalista> {

	public void setTbHorasProjAnalistaIdHoraProjAnalista(Integer id) {
		setId(id);
	}

	public Integer getTbHorasProjAnalistaIdHoraProjAnalista() {
		return (Integer) getId();
	}

	@Override
	protected TbHorasProjAnalista createInstance() {
		TbHorasProjAnalista tbHorasProjAnalista = new TbHorasProjAnalista();
		return tbHorasProjAnalista;
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

	public TbHorasProjAnalista getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
