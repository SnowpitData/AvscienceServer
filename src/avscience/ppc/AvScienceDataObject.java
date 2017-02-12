package avscience.ppc;

import org.json.*;
import java.util.Enumeration;
import java.util.Hashtable;

public abstract class AvScienceDataObject extends org.json.JSONObject
{
        public AvScienceDataObject()
        {
            super();
        }
        
        public AvScienceDataObject(String json) throws JSONException
        {
            super(json);
            popAttributes();
        }
        public String toJSON()
        {
            writeAttributes();
            return toString();
        }
        
        public String toXML()
        {
            writeAttributes();
            return new XMLWriter().getXMLString(this);
        }
        
        public String getProfileFromTable(Hashtable table)
  	{
            System.out.println("getProfileFromTable()");
            Enumeration e = table.keys();
            StringBuffer buffer = new StringBuffer();
            while (e.hasMoreElements())
            {
                Object o = e.nextElement();
  		buffer.append(o);
  		buffer.append("_");
  		Object oo = table.get(o);
  		buffer.append(oo);
  		buffer.append(";");
            }
            String s = buffer.toString();
            String ss = "";
            if (s.length()>0) ss = s.substring(0, s.length()-1);
            return ss;
  	}
        
        public void writeProfileToTable(ValueProfile vp, String s)
  	{
            if ( s!=null)
	    {
                System.out.println("profile: "+s);
	    	s=s.trim();
	    	String[] points = s.split(";");
	    	System.out.println("Number of points: "+points.length);
		for ( int i = 0; i<points.length; i++)
	        {
                    String[] point = points[i].split("_");
                    if (point.length>1)
                    {
                        int depth = new Integer(point[0]).intValue();
			vp.addPoint(depth, point[1]);
                    }			
                 }	
	    }
  	}
  	
        public abstract void popAttributes();
        public abstract void writeAttributes();
}