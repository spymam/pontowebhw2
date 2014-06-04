package br.rainformatica.pontoweb.session;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

import br.rainformatica.pontoweb.entity.TbDiaSemana;

@Name("tbDiaSemanaHome")
public class TbDiaSemanaHome extends EntityHome<TbDiaSemana> {

	public void setTbDiaSemanaId(Integer id) {
		setId(id);
	}

	public Integer getTbDiaSemanaId() {
		return (Integer) getId();
	}

	@Override
	protected TbDiaSemana createInstance() {
		TbDiaSemana tbDiaSemana = new TbDiaSemana();
		return tbDiaSemana;
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

	public TbDiaSemana getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
