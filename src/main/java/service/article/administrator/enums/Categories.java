package service.article.administrator.enums;

public enum Categories {

	BROT_UND_BROETCHEN(2, "Brot & Brötchen"), SONSTIGE_BACKWAREN(3, "sonstige Backwaren"), CEREALIEN(4, "Cerealien"),
	BROTAUFSTRICH(5, "Brotaufstrich"), KOSMETIK(6, "Kosmetik"), HYGIENEARTIKEL(7, "Hygieneartikel"),
	HAUSHALTSARTIKEL(8, "Haushaltsartikel"), WASCH_UND_PUTZMITTEL(9, "Wasch- und Putzmittel"),
	FERTIG_PRODUKTE(10, "Fertig­produkte"), Fette(11, "Fette"), OELE(12, "Öle"), FISCH(13, "Fisch"),
	SALATE(14, "Salate"), WURSTWAREN(16, "Wurstwaren"), ERFRISCHUNGS_GETRAENKE(17, "Erfrischungs­getränke"),
	SAFT(18, "Saft"), BIER(19, "Bier"), SPIRITOUSEN(21, "Spiritousen"), KAESE(22, "Käse"), KAFFEE(23, "Kaffee"),
	TEE(24, "Tee"), INSTANTGETRAENKE(25, "Instant­getränke"), NUDELN_REIS(34, "Nudeln & Reis"), SAUCEN(35, "Saucen"),
	MILCH_SAHNE(31, "Milch & Sahne"), JOGHURT_QUARK(32, "Joghurt & Quark"), Eier(33, "Eier"),
	GESUNDHEIT(38, "Gesundheit"), WEIHNACHTEN(41, "Weihnachten"), SALZ_ZUCKER(42, "Salz, Zucker,..."),
	BACKZUTATEN(44, "Backzutaten"), RANDSORTIMENT(45, "Rand­sortiment"),
	SCHOKOLADE_PRALINEN(46, "Schokolade & Pralinen"), BONBONS_FRUCHTGUMMI_CO(47, "Bonbons, Fruchtgummi & Co"),
	KNABBERARTIKEL(48, "Knabberartikel"), Gebaeck(49, "Gebäck"), SPEISEEIS(55, "Speiseeis"), HUND(56, "Hund"),
	KATZE(57, "Katze"), NUESSE(59, "Nüsse"), BACKSHOP(65, "Backshop"), KOERPERPFLEGE(67, "Körperpflege"),
	RIND(70, "Rind"), SCHWEIN(71, "Schwein"), SONSTIGE_FLEISCHWAREN(72, "sonstige Fleischwaren");

	private long id;
	private String name;

	private Categories(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Categories parseOnId(long id) {
		for (Categories category : Categories.values()) {
			if (category.getId() == id) {
				return category;
			}
		}
		return null;
	}
}
