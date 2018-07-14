// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TempProfile.java

package avscience.wba;

import avscience.pda.Integer;
import java.util.*;

// Referenced classes of package avscience.wba:
//            AvScienceDataObject, TempList

public class TempProfile extends AvScienceDataObject
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
        if (tempUnits!=null)attributes.put("tempUnits", tempUnits);
        if (depthUnits!=null) attributes.put("depthUnits", depthUnits);
        if (depths!=null)attributes.put("depths", depths);
        if (profile!=null) attributes.put("profile", profile);
    }

    public void getAttributes()
    {
        tempUnits = (String)attributes.get("tempUnits");
        depthUnits = (String)attributes.get("depthUnits");
        depths = (Hashtable)attributes.get("depths");
        profile = (Hashtable)attributes.get("profile");
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
        if (profile == null ) return false;
        return profile.size() > 0;
    }

    public String getTempUnits()
    {
        if ( tempUnits == null) return "";
        return tempUnits;
    }

    public String getDepthUnits()
    {
        if ( depthUnits == null) return "";
        return depthUnits;
    }

    public String getKey()
    {
        return new String("7");
    }

    private String tempUnits;
    private String depthUnits;
    private Hashtable depths = new Hashtable();
    private Hashtable profile = new Hashtable();
}
