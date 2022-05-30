package br.com.fiap.bean;


import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;

import org.primefaces.model.file.UploadedFile;

import br.com.fiap.dao.VisitanteDao;
import br.com.fiap.model.Visitante;

@Named
@RequestScoped
public class VisitanteBean {

	private Visitante user = new Visitante();
	
	private VisitanteDao userDao = new VisitanteDao();
	private UploadedFile image;
	
	public void save() throws IOException  {

		System.out.println(this.user);
		
		System.out.println(image.getFileName()); 
		
		ServletContext servletContext = (ServletContext) FacesContext
															.getCurrentInstance()
															.getExternalContext()
															.getContext();
		String servletPath = servletContext.getRealPath("/");
		
		System.err.println(servletPath);
		
		FileOutputStream out = 
				new FileOutputStream(servletPath + "\\images\\" + image.getFileName());
		out.write(image.getContent());
		out.close();
		
		user.setImagePath("\\images\\" + image.getFileName());
		
		userDao.create(user);
		
		FacesContext
			.getCurrentInstance()
			.addMessage(null, new FacesMessage("Visitante cadastrado com sucesso"));
		
	}
	
	public List<Visitante> listAll() {
		Visitante user = 
		(Visitante) FacesContext
		.getCurrentInstance()
		.getExternalContext()
		.getSessionMap()
		.get("user");
		return userDao.listAll(user);
	}
	
	public String login(){
		Visitante userx = userDao.exist(user);
		if (userx != null) {
			//salvar o usuario logado na secao
			FacesContext
				.getCurrentInstance()
				.getExternalContext()
				.getSessionMap()
				.put("user", userx);
			
			return "setups";
		}
		
		FacesContext
			.getCurrentInstance()
			.getExternalContext()
			.getFlash()
			.setKeepMessages(true);
		
		FacesContext
			.getCurrentInstance()
			.addMessage(null, new FacesMessage("Login inválido"));

		return "login?faces-redirect=true";
	}
	
	public String logout() {
		FacesContext
			.getCurrentInstance()
			.getExternalContext()
			.getSessionMap()
			.remove("user");
		
		return "login";
	}
	
	public Visitante getUser() {
		return user;
	}

	public void setUser(Visitante user) {
		this.user = user;
	}
	
	public UploadedFile getImage() {
		return image;
	}

	public void setImage(UploadedFile image) {
		this.image = image;
	}



}
