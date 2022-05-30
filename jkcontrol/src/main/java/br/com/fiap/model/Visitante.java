package br.com.fiap.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Visitante{

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private LocalDate birthday;
	private String email;
	private String password;
	private String imagePath;
	
	public String getImagePath() {
		return imagePath;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setImagePath(String imagePath) {
		// TODO Auto-generated method stub
		this.imagePath = imagePath;
		
	}

	@Override
	public String toString() {
		return "Visitante [id=" + id + ", name=" + name + ", birthday=" + birthday + ", email=" + email + ", password="
				+ password + ", imagePath=" + imagePath + "]";
	}
	
}
