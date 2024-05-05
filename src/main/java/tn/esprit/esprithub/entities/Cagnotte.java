package tn.esprit.esprithub.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cagnotte implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idCagnotte;
	String labelle;
	int totalsomme;
	int etat;
	@Temporal(TemporalType.DATE)
	Date dateCreation;
	

	@JsonIgnore
	@OneToOne(mappedBy="cagnotte")

	private Event event;
	@JsonIgnore
	@ManyToOne
	 User user;

}
