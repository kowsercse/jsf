package test;


public class Hello {

	public String name = "hello";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Hello [name=" + name + "]";
	}
	
}
