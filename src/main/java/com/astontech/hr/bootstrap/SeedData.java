package com.astontech.hr.bootstrap;

import com.astontech.hr.domain.Element;
import com.astontech.hr.domain.ElementType;
import com.astontech.hr.services.ElementService;
import com.astontech.hr.services.ElementTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SeedData implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ElementService elementService;

    @Autowired
    private ElementTypeService elementTypeService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        generateElementAndElementTypes();
    }

    private void generateElementAndElementTypes() {

        ElementType laptopType = new ElementType("Laptop");
        ElementType phoneType = new ElementType("Phone");
        ElementType emailType = new ElementType("Email");

        List<Element> laptopList = new ArrayList<>();
        laptopList.add(new Element("Acer"));
        laptopList.add(new Element("Dell"));
        laptopList.add(new Element("Samsung"));
        laptopList.add(new Element("Apple"));
        laptopList.add(new Element("Asus"));

        laptopType.setElementList(laptopList);
        elementTypeService.saveElementType(laptopType);

        List<Element> phoneList = new ArrayList<>();
        phoneList.add(new Element("Samsung Galaxy"));
        phoneList.add(new Element("iPhone"));
        phoneList.add(new Element("Google Pixel"));

        phoneType.setElementList(phoneList);
        elementTypeService.saveElementType(phoneType);

        List<Element> emailList = new ArrayList<>();
        emailList.add(new Element("Work"));
        emailList.add(new Element("Personal"));

        emailType.setElementList(emailList);
        elementTypeService.saveElementType(emailType);


    }

}
