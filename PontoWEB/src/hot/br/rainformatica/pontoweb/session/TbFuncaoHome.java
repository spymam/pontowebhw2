package br.rainformatica.pontoweb.session;

import br.rainformatica.pontoweb.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("tbFuncaoHome")
public class TbFuncaoHome extends EntityHome<TbFuncao> {

	public void setTbFuncaoIdTbFuncao(Integer id) {
		setId(id);
	}

	public Integer getTbFuncaoIdTbFuncao() {
		return (Integer) getId();
	}

	@Override
	protected TbFuncao createInstance() {
		TbFuncao tbFuncao = new TbFuncao();
		return tbFuncao;
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

	public TbFuncao getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
