package adri.logviewer.util;

public enum DatePart {
	ANNEE("Par année","year(date)","year(date)",""),
	MOIS("Par mois","month(date), year(date)", "month(date), year(date)", "year(date) = :year"),
	JOUR("Par jour","day(date), month(date), year(date)","day(date), month(date), year(date)", "month(date) = :month and year(date) = :year");
	
	private String label;
	private String select;
	private String group;
	private String where;
	
	private DatePart(String label,String select, String group, String where) {
		setLabel(label);
		setSelect(select);
		setGroup(group);
		setWhere(where);
	}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getSelect() {
		return select;
	}

	private void setSelect(String select) {
		this.select = select;
	}

	public String getGroup() {
		return group;
	}

	private void setGroup(String group) {
		this.group = group;
	}

	public String getWhere() {
		return where;
	}

	private void setWhere(String where) {
		this.where = where;
	}
	
}
