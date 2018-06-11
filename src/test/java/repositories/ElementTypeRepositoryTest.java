package repositories;


import com.astontech.hr.configuration.RepositoryConfiguration;
import com.astontech.hr.domain.ElementType;
import com.astontech.hr.repositories.ElementTypeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RepositoryConfiguration.class)
public class ElementTypeRepositoryTest {

    @Autowired
    private ElementTypeRepository elementTypeRepository;

    @Test
    public void testFindByElementTypeName() {
        ElementType elementType = new ElementType("FindTestSingle");
        elementTypeRepository.save(elementType);

        ElementType foundByElementType = elementTypeRepository.findByElementTypeName("FindTestSingle");

        assertEquals(elementType.getId(), foundByElementType.getId());
    }

    @Test
    public void testFindAllByElementTypeName() {
        ElementType elementType1 = new ElementType("FindTest");
        elementTypeRepository.save(elementType1);
        ElementType elementType2 = new ElementType("FindTest");
        elementTypeRepository.save(elementType2);
        ElementType elementType3 = new ElementType("FindTest");
        elementTypeRepository.save(elementType3);

        List<ElementType> foundAllByElementType = elementTypeRepository.findAllByElementTypeName("FindTest");

        assertEquals(3, foundAllByElementType.size());
    }

    @Test
    public void testCountAllByElementTypeName() {
        List<ElementType> elementTypeList = new ArrayList<>();
        elementTypeList.add(new ElementType("cellPhone"));
        elementTypeList.add(new ElementType("personalEmail"));
        elementTypeList.add(new ElementType("workEmail"));

        elementTypeRepository.save(elementTypeList);


//        ElementType elementType1 = new ElementType("cellPhone");
//        elementTypeRepository.save(elementType1);
//        ElementType elementType2 = new ElementType("personalEmail");
//        elementTypeRepository.save(elementType2);
//        ElementType elementType3 = new ElementType("workEmail");
//        elementTypeRepository.save(elementType3);

        elementTypeRepository.countAllByElementTypeName("cellPhone");

        assertNotNull(elementTypeList);
    }

    @Test
    public void testFindByElementTypeNameIgnoreCase() {
        ElementType elementType = new ElementType("FindTestSingle");
        elementTypeRepository.save(elementType);

        ElementType foundByElementType = elementTypeRepository.findByElementTypeNameIgnoreCase("finDtesTsinglE");

        assertEquals(elementType.getId(), foundByElementType.getId());
    }

}
