package hu.beni.amusementpark.controller;

import static hu.beni.amusementpark.factory.LinkFactory.createAmusementParkLink;
import static hu.beni.amusementpark.factory.LinkFactory.createLoginLink;
import static hu.beni.amusementpark.factory.LinkFactory.createLogoutLink;
import static hu.beni.amusementpark.factory.LinkFactory.createMeLink;
import static hu.beni.amusementpark.factory.LinkFactory.createVisitorLink;
import static hu.beni.amusementpark.factory.LinkFactory.createVisitorSignUpLink;

import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BaseLinksController {

	@GetMapping("/links")
	public Link[] getBaseLinks() {
		return new Link[] { createAmusementParkLink(), createVisitorSignUpLink(), createLoginLink(), createLogoutLink(),
				createMeLink(), createVisitorLink() };
	}

}
