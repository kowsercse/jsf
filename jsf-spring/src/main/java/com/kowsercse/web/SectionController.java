package com.kowsercse.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;

import com.kowsercse.entity.Section;
import com.kowsercse.service.SectionService;

@Controller
@Scope(value = WebApplicationContext.SCOPE_SESSION)
public class SectionController {

	public static final String PATH = "/section";

	@Autowired
	private SectionService sectionService;

	private Section currentSection;

	public String create() {
		currentSection = new Section();
		System.out.println("id:=" + currentSection.getId());
		return PATH + "/create.jsf";
	}

	public String save() {
		System.out.println("name:=" + currentSection.getName());
		sectionService.addSection(currentSection);
		return PATH + "/list.jsf";
	}

	public String delete(Section section) {
		sectionService.deleteSection(section);
		return PATH + "/list.jsf";
	}

	public String edit(Section section) {
		currentSection = section;
		return PATH + "/create.jsf";
	}

	public SectionService getSectionService() {
		return sectionService;
	}

	public void setSectionService(SectionService sectionService) {
		this.sectionService = sectionService;
	}

	public Section getCurrentSection() {
		return currentSection;
	}

	public void setCurrentSection(Section currentSection) {
		this.currentSection = currentSection;
	}

}
