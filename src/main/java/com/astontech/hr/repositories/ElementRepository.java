package com.astontech.hr.repositories;

import com.astontech.hr.domain.Element;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ElementRepository extends CrudRepository<Element, Integer> {

    //will auto generate the required code from the structure of the method name
    Element findByElementName(String elementName);

    List<Element> findAllByElementName(String elementName);

    List<Element> findAllByElementNameIgnoreCase(String elementName);

    Integer countAllByElementName(String elementName);

    @Query("SELECT e FROM Element e WHERE e.elementName LIKE (CONCAT('%', :st, '%'))")
    List<Element> findBySearchTerm(@Param("st") String st);


}
