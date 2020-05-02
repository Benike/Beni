package hu.beni.amusementpark.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import hu.beni.amusementpark.constants.HATEOASLinkRelConstants;
import hu.beni.amusementpark.dto.resource.VisitorResource;
import hu.beni.amusementpark.entity.Visitor;
import hu.beni.amusementpark.factory.LinkFactory;
import hu.beni.amusementpark.mapper.VisitorMapper;
import hu.beni.amusementpark.service.VisitorService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@ConditionalOnWebApplication
public class VisitorController {

	private final VisitorService visitorService;
	private final VisitorMapper visitorMapper;
	private final RestTemplate restTemplate;

	@GetMapping("/me")
	public ResponseEntity<VisitorResource> me(Principal principal) {
		return Optional.ofNullable(principal).map(Principal::getName).map(visitorService::findByEmail)
				.map(visitorMapper::toResource).map(ResponseEntity::ok)
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	@PostMapping("/signUp")
	public ResponseEntity<VisitorResource> signUp(HttpServletRequest request,
			@RequestParam(name = "remember-me", required = false) String rememberMe,
			@Valid @RequestBody VisitorResource visitorResource) {
		signUpAsVisitor(visitorResource);
		return copyCookiesAndBody(login(createLoginUrl(request), rememberMe, visitorResource));
	}

	private Visitor signUpAsVisitor(VisitorResource visitorResource) {
		Visitor visitor = visitorMapper.toEntity(visitorResource);
		return visitorService.signUp(visitor);
	}

	private String createLoginUrl(HttpServletRequest request) {
		return "http://" + request.getServerName() + ":" + request.getServerPort() + "/"
				+ HATEOASLinkRelConstants.LOGIN;
	}

	private ResponseEntity<VisitorResource> login(String loginUrl, String rememberMe, VisitorResource visitorResource) {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("email", visitorResource.getEmail());
		map.add("password", visitorResource.getPassword());
		if (Boolean.TRUE.toString().toLowerCase().equals(rememberMe)) {
			map.add("remember-me", rememberMe);
		}
		return restTemplate.postForEntity(loginUrl, map, VisitorResource.class);
	}

	private ResponseEntity<VisitorResource> copyCookiesAndBody(ResponseEntity<VisitorResource> loginResponse) {
		List<String> cookies = loginResponse.getHeaders().get("Set-Cookie");
		return ResponseEntity.ok().header("Set-Cookie", cookies.toArray(new String[cookies.size()]))
				.body(loginResponse.getBody());
	}

	@PostMapping("/visitors/uploadMoney")
	public ResponseEntity<Void> uploadMoney(@RequestBody Integer ammount, Principal principal) {
		visitorService.uploadMoney(ammount, principal.getName());
		return ResponseEntity.ok().build();
	}

	@GetMapping("/visitors")
	public List<VisitorResource> findAllVisitor() {
		List<VisitorResource> resources = visitorMapper.toResources(visitorService.findAllVisitor());
		resources.forEach(pr -> {
			pr.removeLinks();
			pr.add(LinkFactory.createVisitorLinkWithSelfRel(pr.getEmail()));
		});
		return resources;
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/visitors/{visitorEmail}")
	public VisitorResource findByEmail(@PathVariable String visitorEmail) {
		return visitorMapper.toResource(visitorService.findByEmail(visitorEmail));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/visitors/{visitorEmail}")
	public void delete(@PathVariable String visitorEmail) {
		visitorService.delete(visitorEmail);
	}

	@PutMapping("amusement-parks/{amusementParkId}/visitors/enter-park")
	public VisitorResource enterPark(@PathVariable Long amusementParkId, Principal principal) {
		return visitorMapper.toResource(visitorService.enterPark(amusementParkId, principal.getName()));
	}

	@PutMapping("amusement-parks/{amusementParkId}/visitors/leave-park")
	public VisitorResource leavePark(@PathVariable Long amusementParkId, Principal principal) {
		return visitorMapper.toResource(visitorService.leavePark(amusementParkId, principal.getName()));
	}

	@PutMapping("amusement-parks/{amusementParkId}/machines/{machineId}/visitors/get-on-machine")
	public VisitorResource getOnMachine(@PathVariable Long amusementParkId, @PathVariable Long machineId,
			Principal principal) {
		return visitorMapper.toResource(visitorService.getOnMachine(amusementParkId, machineId, principal.getName()));
	}

	@PutMapping("amusement-parks/{amusementParkId}/machines/{machineId}/visitors/get-off-machine")
	public VisitorResource getOffMachine(@PathVariable Long amusementParkId, @PathVariable Long machineId,
			Principal principal) {
		return visitorMapper.toResource(visitorService.getOffMachine(machineId, principal.getName()));
	}
}
