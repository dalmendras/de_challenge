package cl.dechallenge.apirest.backend.model;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the jobs database table.
 *
 */
@Entity
@Table(name="jobs")
@NamedQuery(name="Jobs.findAll", query="SELECT c FROM Jobs c")
public class Jobs implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;

	@Column(name="job")
	private String job;

	public Jobs() {
	}

	public Jobs(Integer id, String job) {
		this.id = id;
		this.job = job;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getJob() {
		return this.job;
	}

	public void setJob(String job) {
		this.job = job;
	}

}
