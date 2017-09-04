package adri.logviewermain.model;

public class BaseModel {
	private int id;
	public BaseModel(int id){
		setId(id);
	}
	public BaseModel() {}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object obj) {
		return BaseModel.class.isInstance(obj) 
				&& this.instance().contentEquals(((BaseModel)obj).instance())
				&& getId() == ((BaseModel)obj).getId();
	}
	@Override
	public int hashCode() {
		return getId();
	}
	@Override
	public String toString() {
		return this.getClass().getName() + "[id = "
				+ this.getId()
				+ " ]";
	}
	public String getName(){
		return "la Base Model #" + getId();
	}
	public String instance(){
		return this.getClass().getSimpleName();
	}
}
