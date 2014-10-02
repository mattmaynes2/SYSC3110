import java.util.EventListener;


public interface CourseListener extends EventListener {

	public void midtermAnnounced(CourseEvent e);
	public void midtermPostponed(CourseEvent e);
}
