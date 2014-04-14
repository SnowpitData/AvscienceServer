package avscience.ppc;
import avscience.wba.*;
import avscience.web.*;

public final class AvScienceObjectTypes
{
	private static AvScienceObjectTypes instance = new AvScienceObjectTypes();
	
	public static AvScienceObjectTypes getInstance()
	{
		return instance;
	}

    AvScienceObjectTypes()
    {
        super();  
    }
    
    public String getName(String key)
    {
    	if (key.equals("1")) return "Avalanche_Occurence";
        if (key.equals("D")) return "Avalanche_Occurence";
        if (key.equals("2")) return "Density_Profile";
        if (key.equals("3")) return "Layer";
        if (key.equals("C")) return "Layer";
        if (key.equals("4")) return "Location";
        if (key.equals("5")) return "Pit_Observation";
        if (key.equals("B")) return "Pit_Observation";
        if (key.equals("6")) return "Shear_Test_Result";
        if (key.equals("E")) return "Shear_Test_Result";
        if (key.equals("7")) return "Temperature_Profile";
        if (key.equals("8")) return "User";
        if (key.equals("F")) return "User";
        if (key.equals("9")) return "Web_User";
        return null;
    }
   
    public Object get(String key)
    {
        if (key.equals("1")) return new avscience.wba.AvOccurence();
        if (key.equals("D")) return new avscience.ppc.AvOccurence();
        if (key.equals("2")) return new DensityProfile();
        if (key.equals("3")) return new avscience.wba.Layer();
        if (key.equals("C")) return new avscience.ppc.Layer();
        if (key.equals("4")) return new avscience.wba.Location();
        if (key.equals("5")) return new avscience.wba.PitObs();
        if (key.equals("B")) return new avscience.ppc.PitObs();
        if (key.equals("6")) return new avscience.wba.ShearTestResult();
        if (key.equals("E")) return new avscience.ppc.ShearTestResult();
        if (key.equals("7")) return new TempProfile();
        if (key.equals("8")) return new avscience.wba.User();
        if (key.equals("F")) return new avscience.ppc.User();
        if (key.equals("9")) return new avscience.web.WebUser();
        return null;
    }
    
    public String getKey(AvScienceDataObject ao)
    {
        return ao.getKey();
    }
}