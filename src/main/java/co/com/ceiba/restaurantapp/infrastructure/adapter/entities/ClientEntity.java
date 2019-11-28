package co.com.ceiba.restaurantapp.infrastructure.adapter.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

@Entity
@Table(name = "client")
public class ClientEntity {

	@Id
	@Column(name = "idclient")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer clientId;

	@Column(name = "clientfirstname")
	private String firstName;

	@Column(name = "clientlastname")
	private String lastName;

	@Column(name = "clientemail")
	private String email;

	@Column(name = "clientphonenumber")
	private String phoneNumber;

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
