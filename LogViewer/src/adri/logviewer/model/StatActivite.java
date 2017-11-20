package adri.logviewer.model;

import java.time.Year;
import java.time.YearMonth;
import java.util.List;

import adri.logviewer.util.DatePart;

public class StatActivite {
	private int annee = Year.now().getValue();
	private int mois = YearMonth.now().getMonth().getValue();;
	private DatePart by = DatePart.JOUR;
	private List<DetailStat> detail;
	
	public StatActivite(){}

	public int getAnnee() {
		return annee;
	}
	public void setAnnee(int annee) {
		if(0 < annee && annee < Year.now().getValue()){
			this.annee = annee;
		}
	}

	public int getMois() {
		return mois;
	}
	public void setMois(int mois) {
		if(1 < mois && mois < 12){
			this.mois = mois;
		}
	}

	public DatePart getBy() {
		return by;
	}
	public void setBy(DatePart by) {
		this.by = by;
	}

	public List<DetailStat> getDetail() {
		return detail;
	}
	public void setDetail(List<DetailStat> detail) {
		this.detail = detail;
	}
	public String getJSON(){
		if(getDetail() == null || getDetail().isEmpty()){
			return "Aucune statistique d'activité disponible";
		}
		String v = ",";
		String virgule = "";
		StringBuilder builder = new StringBuilder();
		builder.append('[');
		for(DetailStat i : getDetail()){
			builder.append(virgule)
			.append("{x: new Date(")
			.append(i.getAnnee()).append(v)
			.append(i.getMois()-1).append(v)
			.append(i.getJour()).append(v)
			.append("),")
			.append("y:").append(i.getNombre()).append("}");
			virgule = v;
		}
		builder.append(']');
		return builder.toString();
	}
	
}
