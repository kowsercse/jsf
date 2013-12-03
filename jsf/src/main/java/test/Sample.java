package test;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.validation.constraints.Max;

@ManagedBean(name="sample")
@RequestScoped
public class Sample {

	private String name = "sample";

	public String getName() {
		System.out.println(name);
		return name;
	}

	@Max(value=5)
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Sample [name=" + name + "]";
	}
	
}
