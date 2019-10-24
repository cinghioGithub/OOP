package mountainhuts;

public class Municipality {
	
	private String Name;
	private String Province;
	private Integer Altitude;
	
	public Municipality(String name, String province, Integer altitude) {
		this.Name = name;
		this.Province = province;
		this.Altitude = altitude;
	}

	public String getName() {
		return this.Name;
	}

	public String getProvince() {
		return this.Province;
	}

	public Integer getAltitude() {
		return this.Altitude;
	}

}
