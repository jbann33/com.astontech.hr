package repositories;

import com.astontech.hr.configuration.RepositoryConfiguration;
import com.astontech.hr.domain.Element;
import com.astontech.hr.repositories.ElementRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RepositoryConfiguration.class)
public class ElementRepositoryTest {

    @Autowired
    private ElementRepository elementRepository;

    @Test
    public void testSaveElement() {
        //setup element
        Element element = new Element();
        element.setElementName("Phone");

        //save element, verify it has an ID after the save
        assertNull(element.getId());
        elementRepository.save(element);
        assertNotNull(element.getId());

        //fetch
        Element fetchedElement = elementRepository.findOne(element.getId());
        assertNotNull(fetchedElement);
        assertEquals(element.getId(), fetchedElement.getId());

        //update
        fetchedElement.setElementName("Email");
        elementRepository.save(fetchedElement);

        Element updatedElement = elementRepository.findOne(fetchedElement.getId());
        assertEquals(updatedElement.getElementName(), "Email");
    }
    @Test
    public void testSaveElementList() {
        List<Element> elementList = new ArrayList<>();
        elementList.add(new Element("Phone"));
        elementList.add(new Element("Email"));
        elementList.add(new Element("Laptop"));
        elementList.add(new Element("PayRate"));

        elementRepository.save(elementList);

        Iterable<Element> fetchedElementList = elementRepository.findAll();

        int count = 0;
        for (Element element : fetchedElementList) {
            count++;
            System.out.println("count" + " " + element.getElementName());
        }

        assertEquals(4, count);
    }

    @Test
    public void testFindByName() {
        Element element = new Element("FindTestSingle");
        elementRepository.save(element);

        Element foundByNameElement = elementRepository.findByElementName("FindTestSingle");

        assertEquals(element.getId(), foundByNameElement.getId());
    }

    @Test
    public void testFindAllByName() {
        Element element1 = new Element("FindTest");
        elementRepository.save(element1);
        Element element2 = new Element("FindTest");
        elementRepository.save(element2);
        Element element3 = new Element("FindTest");
        elementRepository.save(element3);

        List<Element> foundAllByNameElement = elementRepository.findAllByElementName("FindTest");

        assertEquals(3, foundAllByNameElement.size());
    }

    @Test
    public void testCountAllByElementName() {
        List<Element> elementList = new ArrayList<>();
        elementList.add(new Element("Phone"));
        elementList.add(new Element("Email"));
        elementList.add(new Element("Laptop"));
        elementList.add(new Element("PayRate"));

        elementRepository.save(elementList);

        Integer size = elementRepository.countAllByElementName("Phone");

        assertNotNull(elementList);
    }

    @Test
    public void testFindBySearchTerm() {
        List<Element> elementList = new ArrayList<>();
        elementList.add(new Element("Phone"));
        elementList.add(new Element("Email"));
        elementList.add(new Element("Laptop"));
        elementList.add(new Element("PayRate"));

        elementRepository.save(elementList);

        List<Element> returnedElements = elementRepository.findBySearchTerm("e");
    }
}
