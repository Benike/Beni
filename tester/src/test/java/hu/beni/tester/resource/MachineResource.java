package hu.beni.tester.resource;

import org.springframework.hateoas.RepresentationModel;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MachineResource extends RepresentationModel<MachineResource> {

	private Long identifier;

	private String fantasyName;

	private Integer size;

	private Integer price;

	private Integer numberOfSeats;

	private Integer minimumRequiredAge;

	private Integer ticketPrice;

	private String type;

}
