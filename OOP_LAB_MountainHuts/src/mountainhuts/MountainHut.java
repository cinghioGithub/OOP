package mountainhuts;

import java.util.Optional;

public class MountainHut {
	
	private String Name;
	private Optional<Integer> Altitude;
	private String Category;
	private Integer BedsNumber;
	private Municipality Municipality;
	
	public MountainHut(String name, Integer altitude, String category, Integer bedsNumber, Municipality municipality) {
		this.Name = name;
		this.Altitude = Optional.ofNullable(altitude);
		this.Category = category;
		this.BedsNumber = bedsNumber;
		this.Municipality = municipality;
	}

	public String getName() {
		return this.Name;
	}

	public Optional<Integer> getAltitude() {
		return this.Altitude;
	}

	public String getCategory() {
		return this.Category;
	}

	public Integer getBedsNumber() {
		return this.BedsNumber;
	}

	public Municipality getMunicipality() {
		return this.Municipality;
	}

}
