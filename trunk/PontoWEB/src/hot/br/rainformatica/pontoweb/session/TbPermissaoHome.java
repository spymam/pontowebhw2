package br.rainformatica.pontoweb.session;

import br.rainformatica.pontoweb.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("tbPermissaoHome")
public class TbPermissaoHome extends EntityHome<TbPermissao> {

	public void setTbPermissaoId(Integer id) {
		setId(id);
	}

	public Integer getTbPermissaoId() {
		return (Integer) getId();
	}

	@Override
	protected TbPermissao createInstance() {
		TbPermissao tbPermissao = new TbPermissao();
		return tbPermissao;
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

	public TbPermissao getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
