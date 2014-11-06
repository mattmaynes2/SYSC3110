package prop;

import java.util.Properties;
import java.util.StringTokenizer;
public class StartApp
{
   private static final String DEFINE = "-D";

public static void main(String[] args)
   {
      Properties props = new Properties();
      for (int i= 0; i < args.length; i++)
      {
         if(args[i].startsWith(DEFINE))
         {
           addProperty(props, args[i].substring(2));
         }
      }
      //continue...
   }

   private static void addProperty(Properties props, String s) {
	   StringTokenizer st = new StringTokenizer(s, "=");
	   if(st.countTokens() == 2)
	   {
		   String key = st.nextToken();
		   String value = st.nextToken();
		   props.setProperty(key, value);
	   }
   }
}