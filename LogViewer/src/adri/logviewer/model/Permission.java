package adri.logviewer.model;

import java.util.Date;

import adri.logviewer.util.StringUtil;

public class Permission extends BaseModel{
	
	private PermissionType permission;
	private Date dateAjout = new Date();
	
	public Permission(){
		super();
	}
	public Permission(int id){
		this.setId(id);
	}
	public Permission(PermissionType permission){
		this.setPermission(permission);
	}
	public Permission(PermissionType permission, Date dateAjout) {
		this.setPermission(permission);
		this.setDateAjout(dateAjout);
	}
	
	public PermissionType getPermission() {
		return permission;
	}
	public void setPermission(PermissionType permission) {
		this.permission = permission;
	}
	public void setPermission(String permission) {
		setPermission(PermissionType.valueOf(permission));
	}
	
	public Date getDateAjout() {
		return dateAjout;
	}
	public String getDateAjoutString() {
		return StringUtil.getInstance().getDateString(getDateAjout());
	}
	public void setDateAjout(Date dateAjout) {
		this.dateAjout = dateAjout;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Permission){
			Permission value = (Permission)obj;
			return this.getPermission().equals(value.getPermission());
		}
		return false;
	}
	@Override
	public int hashCode() {
	    return this.getPermission().hashCode();
	}
	@Override
	public String toString() {
		return "[" + super.toString() + ", " + this.getPermission() +
				 "]";
	}
	@Override
	public String instance() {
		return getClass().getSuperclass().getSimpleName();
	}
}
