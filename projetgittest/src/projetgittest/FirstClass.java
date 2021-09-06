package projetgittest;

public class FirstClass {

	private String name;
	private String ensuite;
	
	public FirstClass(){
		name="je suis la premier class qui sera commité";
		ensuite = "je suis modifié";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEnsuite() {
		return ensuite;
	}

	public void setEnsuite(String ensuite) {
		this.ensuite = ensuite;
	}
	
	
}
