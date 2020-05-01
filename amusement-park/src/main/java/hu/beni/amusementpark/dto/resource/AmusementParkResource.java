package hu.beni.amusementpark.dto.resource;

import java.io.Serializable;
import java.util.Optional;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AmusementParkResource extends ResourceSupport implements Serializable {

	private static final long serialVersionUID = -7411398427560874485L;

	@Null
	private Long identifier;

	@NotNull
	@Size(min = 5, max = 20)
	private String name;

	@NotNull
	@Range(min = 500, max = 50000)
	private Integer capital;

	@NotNull
	@Range(min = 50, max = 5000)
	private Integer totalArea;

	@NotNull
	@Range(min = 5, max = 200)
	private Integer entranceFee;

	@Builder
	public AmusementParkResource(Long identifier, String name, Integer capital, Integer totalArea, Integer entranceFee,
			Link[] links) {
		super();
		this.identifier = identifier;
		this.name = name;
		this.capital = capital;
		this.totalArea = totalArea;
		this.entranceFee = entranceFee;
		Optional.ofNullable(links).ifPresent(this::add);
	}

}
