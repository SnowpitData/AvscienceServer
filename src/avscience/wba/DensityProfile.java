

package avscience.wba;

import avscience.pda.Integer;
import java.util.*;

// Referenced classes of package avscience.wba:
//            AvScienceDataObject

public class DensityProfile extends AvScienceDataObject
{

    public DensityProfile()
    {
        densityUnits = "";
        depthUnits = "";
        depths = new Hashtable();
        profile = new Hashtable();
    }

    public DensityProfile(String s)
    {
        this();
        popFromString(s);
    }

    public void setAttributes()
    {
        if (densityUnits!=null) attributes.put("densityUnits", densityUnits);
        if (depthUnits!=null) attributes.put("depthUnits", depthUnits);
        if (depths!=null) attributes.put("depths", depths);
        if (profile !=null) attributes.put("profile", profile);
    }

    public void getAttributes()
    {
        densityUnits = (String)attributes.get("densityUnits");
        depthUnits = (String)attributes.get("depthUnits");
        depths = (Hashtable)attributes.get("depths");
        profile = (Hashtable)attributes.get("profile");
    }

    public String[] getPoints()
    {
        String as[] = new String[profile.size()];
        Enumeration enumeration = getDepths().elements();
        for(int i = 0; enumeration.hasMoreElements(); i++)
        {
            Integer integer = (Integer)enumeration.nextElement();
            String s = "Depth " + integer.toString() + " Density " + (String)profile.get(integer) + " " + densityUnits;
            as[i] = s;
        }

        return as;
    }

    public DensityProfile(String s, String s1)
    {
        depths = new Hashtable();
        profile = new Hashtable();
        densityUnits = s;
        depthUnits = s1;
    }

    public boolean addPoint(int i, String s)
    {
        Integer integer = new Integer(i);
        depths.put(integer.toString(), integer);
        Object obj = profile.put(integer, s);
        return obj == null;
    }

    public String getDensity(Integer integer)
    {
        String s = (String)profile.get(integer);
        return s;
    }

    public Vector getDensities()
    {
        Vector vector = new Vector();
        Enumeration enumeration = profile.elements();
        do
        {
            if(!enumeration.hasMoreElements())
                break;
            String s = (String)enumeration.nextElement();
            if(s != null)
                vector.addElement(s);
        } while(true);
        return vector;
    }

    public Vector getDepths()
    {
        Vector vector = new Vector();
        if (profile==null) profile = new Hashtable();
        Enumeration enumeration = profile.keys();
        if(enumeration != null)
        {
            Integer integer;
            for(; enumeration.hasMoreElements(); vector.addElement(integer))
                integer = (Integer)enumeration.nextElement();

        }
        return vector;
    }

    public void removePoint(String s)
    {
        Integer integer = (Integer)depths.get(s);
        profile.remove(integer);
    }

    public boolean hasPoints()
    {
        if (profile == null ) return false;
        return profile.size() > 0;
    }

    public String getDensityUnits()
    {
        if ( densityUnits == null) return "";
        return densityUnits;
    }

    public String getDepthUnits()
    {
        if ( depthUnits == null) return "";
        return depthUnits;
    }

    public String getKey()
    {
        return new String("2");
    }

    private String densityUnits = "";
    private String depthUnits = "";
    private Hashtable depths = new Hashtable();
    private Hashtable profile = new Hashtable();
}
