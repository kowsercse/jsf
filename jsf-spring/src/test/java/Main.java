import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import com.kowsercse.entity.Section;

public class Main {

	@Test
	public void testEM() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("school");
		EntityManager em = emf.createEntityManager();
		
		List<Section> resultList = em.createQuery("from Section", Section.class).getResultList();

		System.out.println(resultList);
	}
	
}
