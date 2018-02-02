package hu.beni.tester.factory;

import static hu.beni.tester.AmusementParkTesterApplicationTests.*;

import java.time.LocalDate;

import hu.beni.tester.dto.AddressDTO;
import hu.beni.tester.dto.AmusementParkDTO;
import hu.beni.tester.dto.MachineDTO;
import hu.beni.tester.dto.VisitorDTO;
import hu.beni.tester.enums.MachineType;

public class ValidDTOFactory {

	public static MachineDTO createMachine() {
		return MachineDTO.builder()
				.fantasyName("Nagy hajó")
				.size(100)
				.price(MACHINE_PRICE)
				.numberOfSeats(30)
				.minimumRequiredAge(18)
				.ticketPrice(MACHINE_TICKET_PRICE)
				.type(MachineType.CAROUSEL).build();
	}

	public static AmusementParkDTO createAmusementParkWithAddress() {
		AddressDTO addressDTO = createAddress();
		AmusementParkDTO amusementParkDTO = createAmusementPark();
		amusementParkDTO.setAddress(addressDTO);
		return amusementParkDTO;
	}
	
	public static VisitorDTO createVisitor() {
    	return VisitorDTO.builder()
    			.name("Németh Bence")
        		.dateOfBirth(LocalDate.of(1994, 10, 22))
        		.spendingMoney(VISITOR_SPENDING_MONEY).build();
    }

	private static AddressDTO createAddress() {
		return AddressDTO.builder()
				.zipCode("1148")
				.city("Budapest")
				.country("Magyarország")
				.street("Fogarasi út")
				.houseNumber("80/C")
				.build();
	}

	private static AmusementParkDTO createAmusementPark() {
		return AmusementParkDTO.builder()
				.name("Beni parkja")
				.capital(AMUSEMENT_PARK_CAPITAL)
				.totalArea(1000)
				.entranceFee(AMUSEMENT_PARK_ENTRANCE_FEE).build();
	}
}