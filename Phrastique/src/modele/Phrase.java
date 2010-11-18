package modele;

public class Phrase {
	private String id;
	private String contenu;
	
	public Phrase(String id, String contenu){
		this.setId(id);
		this.setContenu(contenu);
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public String getContenu() {
		return contenu;
	}
}
