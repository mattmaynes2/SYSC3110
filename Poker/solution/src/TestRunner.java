import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
    public static void main(String[] args) {
	try{
	    Result result = JUnitCore.runClasses(Class.forName(args[0]));
	    for (Failure failure : result.getFailures()) {
		System.out.println(failure.toString());
	    }
	    System.out.println(result.wasSuccessful());
	} catch (Exception e){
	    System.out.println("Class name argument required");
     
	}
    }
}  