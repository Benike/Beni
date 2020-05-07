package hu.beni.amusementpark.factory;

import static hu.beni.amusementpark.constants.HATEOASLinkRelConstants.ADD_REGISTRY;
import static hu.beni.amusementpark.constants.HATEOASLinkRelConstants.AMUSEMENT_PARK;
import static hu.beni.amusementpark.constants.HATEOASLinkRelConstants.GET_OFF_MACHINE;
import static hu.beni.amusementpark.constants.HATEOASLinkRelConstants.GET_ON_MACHINE;
import static hu.beni.amusementpark.constants.HATEOASLinkRelConstants.LOGIN;
import static hu.beni.amusementpark.constants.HATEOASLinkRelConstants.LOGOUT;
import static hu.beni.amusementpark.constants.HATEOASLinkRelConstants.MACHINE;
import static hu.beni.amusementpark.constants.HATEOASLinkRelConstants.ME;
import static hu.beni.amusementpark.constants.HATEOASLinkRelConstants.SIGN_UP;
import static hu.beni.amusementpark.constants.HATEOASLinkRelConstants.UPLOAD_MONEY;
import static hu.beni.amusementpark.constants.HATEOASLinkRelConstants.VISITOR;
import static hu.beni.amusementpark.constants.HATEOASLinkRelConstants.VISITOR_ENTER_PARK;
import static hu.beni.amusementpark.constants.HATEOASLinkRelConstants.VISITOR_LEAVE_PARK;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.Link;

import hu.beni.amusementpark.controller.AmusementParkController;
import hu.beni.amusementpark.controller.BaseLinksController;
import hu.beni.amusementpark.controller.GuestBookRegistryController;
import hu.beni.amusementpark.controller.MachineController;
import hu.beni.amusementpark.controller.VisitorController;

public class LinkFactory {

	private static final Class<BaseLinksController> baseLinksControllerClass = BaseLinksController.class;
	private static final Class<AmusementParkController> amusementParkControllerClass = AmusementParkController.class;
	private static final Class<MachineController> machineControllerClass = MachineController.class;
	private static final Class<VisitorController> visitorControllerClass = VisitorController.class;
	private static final Class<GuestBookRegistryController> guestBookRegistryControllerClass = GuestBookRegistryController.class;

	public static Link createLoginLink() {
		return linkTo(baseLinksControllerClass).slash(LOGIN).withRel(LOGIN);
	}

	public static Link createLogoutLink() {
		return linkTo(baseLinksControllerClass).slash(LOGOUT).withRel(LOGOUT);
	}

	public static Link createMeLink() {
		return linkTo(methodOn(visitorControllerClass).me(null)).withRel(ME);
	}

	public static Link createMeLinkWithSelfRel() {
		return linkTo(methodOn(visitorControllerClass).me(null)).withSelfRel();
	}

	public static Link createUploadMoneyLink() {
		return linkTo(methodOn(visitorControllerClass).uploadMoney(null, null)).withRel(UPLOAD_MONEY);
	}

	public static Link createAmusementParkLink() {
		return linkTo(methodOn(amusementParkControllerClass).save(null)).withRel(AMUSEMENT_PARK);
	}

	public static Link createVisitorLink() {
		return linkTo(methodOn(visitorControllerClass).findAllVisitor()).withRel(VISITOR);
	}

	public static Link createVisitorLinkWithSelfRel(String email) {
		return linkTo(methodOn(visitorControllerClass).findByEmail(email)).withSelfRel();
	}

	public static Link createAmusementParkSelfLink(Long amusementParkId) {
		return linkTo(methodOn(amusementParkControllerClass).findOne(amusementParkId)).withSelfRel();
	}

	public static Link createMachineLink(Long amusementParkId) {
		return linkTo(methodOn(machineControllerClass).addMachine(amusementParkId, null)).withRel(MACHINE);
	}

	public static Link createMachineSelfLink(Long amusementParkId, Long machineId) {
		return linkTo(methodOn(machineControllerClass).findById(amusementParkId, machineId)).withSelfRel();
	}

	public static Link createVisitorSignUpLink() {
		return linkTo(visitorControllerClass).slash(SIGN_UP).withRel(SIGN_UP);
	}

	public static Link createVisitorEnterParkLink(Long amusementParkId) {
		return linkTo(methodOn(visitorControllerClass).enterPark(amusementParkId, null)).withRel(VISITOR_ENTER_PARK);
	}

	public static Link createGetOnMachineLink(Long amusementParkId, Long machineId) {
		return linkTo(methodOn(visitorControllerClass).getOnMachine(amusementParkId, machineId, null))
				.withRel(GET_ON_MACHINE);
	}

	public static Link createGetOffMachineLink(Long amusementParkId, Long machineId) {
		return linkTo(methodOn(visitorControllerClass).getOffMachine(amusementParkId, machineId, null))
				.withRel(GET_OFF_MACHINE);
	}

	public static Link createVisitorLeavePark(Long amusementParkId) {
		return linkTo(methodOn(visitorControllerClass).leavePark(amusementParkId, null)).withRel(VISITOR_LEAVE_PARK);
	}

	public static Link createGuestBookRegistrySelfLink(Long guestBookRegistryId) {
		return linkTo(methodOn(guestBookRegistryControllerClass).findById(guestBookRegistryId)).withSelfRel();
	}

	public static Link createAddGuestBookRegistryLink(Long amusementParkId) {
		return linkTo(methodOn(guestBookRegistryControllerClass).addRegistry(amusementParkId, null, null))
				.withRel(ADD_REGISTRY);
	}

	private LinkFactory() {
		super();
	}
}
