package br.rainformatica.pontoweb.session;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.contexts.Contexts;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.framework.EntityHome;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;

import br.rainformatica.pontoweb.entity.TbClientes;
import br.rainformatica.pontoweb.entity.TbUsuarios;

import com.sun.xml.internal.ws.util.UtilException;

@Name("authenticator")
public class Authenticator extends EntityHome<TbUsuarios>
{
    @Logger private Log log;

    @In Identity identity;
    @In Credentials credentials;
    @In FacesMessages facesMessages;
    TbUsuariosHome tbUsuariosHome = new TbUsuariosHome();
    
    
    //    public boolean authenticate()
//    {
//    tbUsuariosHome.setCredential(credentials);
//    	String login = tbUsuariosHome.getListaTbUsuarios().get(0).getLogin();
//    	String senha = tbUsuariosHome.getListaTbUsuarios().get(0).getSenha();
//    	
//        log.info("authenticating {0}", tbUsuariosHome.getListaTbUsuarios().get(0).getNome());
//        //write your authentication logic here,
//        //return true if the authentication was
//        //successful, false otherwise
//        if (login.equals(credentials.getUsername()))
//        {
//           // identity.addRole(login);
//            identity.addRole(tbUsuariosHome.getListaTbUsuarios().get(0).getNome());
//            
//            return true;
//        }
//                    		
//        return false;
//    }
    
    public void verificaDadosExistentes(){
    	
    	TbClientes tbClientes = listarClientes.get(0);
    	
    	if (tbClientes == null) {
    
    		tbClientes.setNome("Projeto Administrador");
    		
    		
			
		}
    	
    }
        
    public List<TbClientes> listarClientes;

	public List<TbClientes> getListaTbClientes() {
		List resultList = getEntityManager().createQuery(
				"SELECT e FROM TbClientes e").getResultList();

		return resultList;

	}
    
    public boolean authenticate() throws NoSuchAlgorithmException, UtilException {
    	 
        TbUsuarios usuario = new TbUsuariosList().login(identity.getUsername(), identity.getPassword());
        setInstance(usuario);
        String nome = getInstance().getTbAnalista().getNome();
        getInstance().setTbAnalista(usuario.getTbAnalista());
        if (usuario != null) {
          Contexts.getSessionContext().set("usuario", usuario);
          identity.addRole("admin");
          setInstance(usuario);
          return true;
        }
        else
          return false;
      }



	public TbUsuariosHome getTbUsuariosHome() {
		return tbUsuariosHome;
	}



	public void setTbUsuariosHome(TbUsuariosHome tbUsuariosHome) {
		this.tbUsuariosHome = tbUsuariosHome;
	}

}
