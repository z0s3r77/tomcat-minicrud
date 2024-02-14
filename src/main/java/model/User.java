package model;

public class User {

	private Integer id;
	private String user;
	private String password;
	private String rol;
	private String description;

	public User() {
	}

	public User(Integer id, String user, String password, String rol, String description) {
		this.setId(id);
		this.setUser(user);
		this.setPassword(password);
		this.setRol(rol);
		this.setDescription(description);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "User{" + "id=" + id + ", user='" + user + '\'' + ", password='" + password + '\'' + ", rol='" + rol
				+ '\'' + ", description='" + description + '\'' + '}';
	}

}
