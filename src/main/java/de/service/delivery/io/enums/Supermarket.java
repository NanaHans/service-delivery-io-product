package de.service.delivery.io.enums;

public enum Supermarket {
	ALDI_SÜD(1, "Aldi Süd"), ALDI_NORD(2, "Aldi Nord"), LIDL(3, "Lidl"), REWE(4, "Rewe"), EDEKA(5, "Edeka"),
	PENNY(6, "Penny"), NETTO(7, "Netto");

	private long id;
	private String name;

	private Supermarket(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static Supermarket parseOnId(long id) {
		for (Supermarket spm : Supermarket.values()) {
			if (spm.getId() == id) {
				return spm;
			}
		}
		return null;
	}

}
