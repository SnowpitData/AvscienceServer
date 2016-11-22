package avscience.wba;

import java.util.Hashtable;
import java.util.Enumeration;
import java.util.Vector;

import avscience.ppc.AvScienceDataObject;
import avscience.ppc.ValueProfile;

public class TempProfile extends avscience.ppc.AvScienceDataObject implements ValueProfile
{
    public TempProfile()
    {
        tempUnits = "C";
        depthUnits = "cm";
        depths = new Hashtable();
        profile = new Hashtable();
    }

    public TempProfile(String data)
    {
        this();
        popFromString(data);
    }

    public Hashtable getProfile()
    {
        return profile;
    }
    
    public void setAttributes()
    {
        try
        {
            put("tempUnits", tempUnits);
            put("depthUnits", depthUnits);
            String profile_data = this.getProfileFromTable(profile);
            put("profile_data", profile_data);
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
       
    }

    public void getAttributes()
    {
        try
        {
            tempUnits = getString("tempUnits");
            depthUnits = getString("depthUnits");
            this.writeProfileToTable(this, getString("profile_data"));
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
       
    }

    public String[] getPoints()
    {
        String points[] = new String[profile.size()];
        Enumeration depths = getDepths().elements();
        Enumeration temps = profile.elements();
        for(int i = 0; depths.hasMoreElements(); i++)
        {
            Integer D = (Integer)depths.nextElement();
            String s = (new StringBuilder()).append("Depth ").append(D.toString()).append(" Temp ").append(TempList.getInstance().getTempString(tempUnits, getTemp(D))).toString();
            points[i] = s;
        }

        return points;
    }

    public TempProfile(String tempUnits, String depthUnits)
    {
        this.tempUnits = "C";
        this.depthUnits = "cm";
        depths = new Hashtable();
        profile = new Hashtable();
        this.tempUnits = tempUnits;
        this.depthUnits = depthUnits;
    }

    public boolean addPoint(int depth, String temp)
    {
        Integer I = new Integer(depth);
        depths.put(I.toString(), I);
        Object o = profile.put(I, temp);
        return o == null;
    }

    public int getTemp(Integer depth)
    {
        TempList tl = TempList.getInstance();
        String s = (String)profile.get(depth);
        int i = tl.getTemp(tempUnits, s);
        return i;
    }

    public Vector getTemps()
    {
        TempList tl = TempList.getInstance();
        Vector v = new Vector();
        Enumeration e = profile.elements();
        do
        {
            if(!e.hasMoreElements())
                break;
            Integer I = new Integer(tl.getTemp(tempUnits, (String)e.nextElement()));
            if(I != null)
                v.addElement(I);
        } while(true);
        return v;
    }

    public Vector getDepths()
    {
        if (profile==null) profile = new Hashtable();
        Vector v = new Vector();
        Integer I;
        for(Enumeration e = profile.keys(); e.hasMoreElements(); v.addElement(I))
            I = (Integer)e.nextElement();

        return v;
    }

    public void removePoint(String depth)
    {
        Integer I = (Integer)depths.get(depth);
        profile.remove(I);
    }

    public boolean hasPoints()
    {
        return profile.size() > 0;
    }

    public String getTempUnits()
    {
        return tempUnits;
    }

    public String getDepthUnits()
    {
        return depthUnits;
    }

    private String tempUnits;
    private String depthUnits;
    private Hashtable depths = new Hashtable();
    private Hashtable profile = new Hashtable();
}
