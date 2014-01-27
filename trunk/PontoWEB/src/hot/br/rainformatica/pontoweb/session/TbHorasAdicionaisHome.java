package br.rainformatica.pontoweb.session;

import br.rainformatica.pontoweb.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("tbHorasAdicionaisHome")
public class TbHorasAdicionaisHome extends EntityHome<TbHorasAdicionais> {

	public void setTbHorasAdicionaisIdTbHorasAdicionais(Integer id) {
		setId(id);
	}

	public Integer getTbHorasAdicionaisIdTbHorasAdicionais() {
		return (Integer) getId();
	}

	@Override
	protected TbHorasAdicionais createInstance() {
		TbHorasAdicionais tbHorasAdicionais = new TbHorasAdicionais();
		return tbHorasAdicionais;
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

	public TbHorasAdicionais getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
