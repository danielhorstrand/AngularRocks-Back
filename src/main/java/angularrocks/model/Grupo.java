package angularrocks.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Grupo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "descripcion", length = 10000)
	private String descripcion;
	
	@Column(name = "video")
	private String video;
	
	@Column(name = "published")
	private boolean published;

	public Grupo() {
		super();
		
	}

	public Grupo(String name, String descripcion, String video, boolean published) {
		super();
		this.name = name;
		this.descripcion = descripcion;
		this.video = video;
		this.published = published;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	@Override
	public String toString() {
		return "Grupo [id=" + id + ", name=" + name + ", descripcion=" + descripcion + ", video=" + video
				+ ", published=" + published + "]";
	}

	
	
	
	
}
