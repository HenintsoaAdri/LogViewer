package adri.logviewer.model;

public class TimelineView extends Timeline {
	
	@Override
	public String instance() {
		return getClass().getSuperclass().getSimpleName();
	}
}
