package avscience.ppc;
import java.util.Date;
import waba.sys.Time;
import avscience.wba.*;
import avscience.pc.*;
import java.util.*;

public class PitObs extends avscience.ppc.AvScienceDataObject
{
    private avscience.wba.Location loc = new avscience.wba.Location();
    private String aspect = "";
    private String incline = "";
    private String precip = "";
    private String sky = "";
    private String windspeed = "";
    private String winDir = "";
    private String windLoading = "";
    private String skiBoot = "ski";
    private String surfacePen = "";
    private String airTemp = "";
    private String stability = "";
    private String pitNotes = "";
    private String crownObs = "false";
    private String date="";
    private String time="";
    private String serial="";
    private String archname="";
    private String heightOfSnowpack="";
    public String skiAreaPit="false";
    public String bcPit="false";
    public String aviPit="false";
    public String aviLoc="";
    
    public java.util.Vector<Layer> layers = new java.util.Vector<Layer>();
    public java.util.Vector<ShearTestResult> shearTests = new java.util.Vector<ShearTestResult>();
    private TempProfile tempProfile = new TempProfile();
    private DensityProfile densityProfile = new DensityProfile();
    private String measureFrom = "top";
    private String currentEditLayer = "";
    private String currentEditTest = "";
    private String dateString = "";
    private String timestamp = "";
    private avscience.ppc.User user;
    public String iLayerNumber="";
    public String iDepth="";
    public String testPit="false";
    public String version = "";
    private String bld="";
    private java.util.Vector<String> activities = new java.util.Vector<String>();
    private String actsString="";
    private String edited="false";
    ///public Hashtable attributes = new Hashtable();
    public PitObs() {super();}
    
    public static void main(String[] args )
    {
        new PitObs("aviLoc~1crown`user~3FtempUnits~1C`useSymbols~1true`state~1BC`name~1MattLucas`elvUnits~1m`share~1true`prof~1false`depthUnits~1cm`latType~1N`email~1lucasmatt@gmail.com`affil~1CAA Active`hardnessScaling~1linear`rhoUnits~1kg/cubic_m`last~1Lucas`first~1Matt`measureFrom~1top`coordType~1Lat/Lon`fractureCat~1Fracture Character`longType~1W`|dateString~111:19:6`iLayerNumber~1`bld~155`stability~1 `aviPit~1false`incline~125`winDir~1 `bcPit~1false`testPit~1false`tempProfile~37depths~4depths1~2\n" +
"       $140`160`180`1100`110`10`130`150`170`190`1110`120`depths2~2\n" +
"                                                                  $640&660&680&6100&610&60&630&650&670&690&6110&620&profile~4profile1~2\n" +
"                                                       $60&640&680&620&660&6100&650&610&670&630&6110&690&profile2~2\n" +
"                                   $1-19.0`1-1.0`10.0`1-8.0`10.0`10.0`10.0`1-13.0`10.0`1-4.0`10.0`10.0`tempUnits~1C`depthUnits~1cm`|windspeed~1Calm`aspect~1280`skiBoot~1Foot`measureFrom~1top`sky~1Clear`loc~34zone~117T`ns~1N`state~1BC`range~1`north~10`elv~11200`name~1North Hirsch`east~10`ew~1W`type~1LATLON`lat~1`ID~1`longitude~1`|surfacePen~140`densityProfile~32depths~4depths1~2 $depths2~2 $profile~4profile1~2 $profile2~2 $depthUnits~1`densityUnits~1`|windLoading~1no`layers~$3CgrainSizeUnits1~1mm`grainSizeUnits2~1mm`grainType~1Decomposing & fragmented precip. particles`grainSize~11.0`multipleHardness~1false`startDepth~10`grainSuffix~1 `multipleDensity~1false`grainSuffix1~1 `hsuffix1~1 `hardness1~1F`hsuffix2~1 `hardness2~1 `layerNumber~11`fromTop~1true`multipleGrainType~1false`multipleGrainSize~1true`grainSize1~12.0`waterContent~1Dry`endDepth~12`|3CgrainSizeUnits1~1mm`grainSizeUnits2~1mm`grainType~1Decomposing & fragmented precip. particles`grainSize~11.0`multipleHardness~1false`startDepth~12`grainSuffix~1 `multipleDensity~1false`grainSuffix1~1 `hsuffix1~1 `hardness1~1F`hsuffix2~1 `hardness2~1 `layerNumber~12`fromTop~1true`multipleGrainType~1true`multipleGrainSize~1false`grainType1~1Faceted rounded particles`grainSize1~1 `waterContent~1Dry`endDepth~120`|3CgrainSizeUnits1~1mm`grainSizeUnits2~1mm`grainType~1Ice formations`grainSize~1 `multipleHardness~1false`startDepth~120`grainSuffix~1 `multipleDensity~1false`grainSuffix1~1 `hsuffix1~1 `hardness1~1P`hsuffix2~1 `hardness2~1 `layerNumber~13`fromTop~1true`multipleGrainType~1false`multipleGrainSize~1false`grainSize1~1 `waterContent~1 `endDepth~121`|3CgrainSizeUnits1~1mm`grainSizeUnits2~1mm`grainType~1Faceted Crystals`grainSize~11.0`multipleHardness~1false`startDepth~121`grainSuffix~1 `multipleDensity~1false`grainSuffix1~1 `hsuffix1~1 `hardness1~14F`hsuffix2~1 `hardness2~1 `layerNumber~14`fromTop~1true`multipleGrainType~1false`multipleGrainSize~1false`grainSize1~1 `waterContent~1Dry`endDepth~126`|3CgrainSizeUnits1~1mm`grainSizeUnits2~1mm`grainType~1Ice formations`grainSize~1 `multipleHardness~1false`startDepth~126`grainSuffix~1 `multipleDensity~1false`grainSuffix1~1 `hsuffix1~1 `hardness1~1P`hsuffix2~1 `hardness2~1 `layerNumber~15`fromTop~1true`multipleGrainType~1false`multipleGrainSize~1false`grainSize1~1 `waterContent~1 `endDepth~127`|3CgrainSizeUnits1~1mm`grainSizeUnits2~1mm`grainType~1Faceted Crystals`grainSize~11.0`multipleHardness~1false`startDepth~127`grainSuffix~1 `multipleDensity~1false`grainSuffix1~1 `hsuffix1~1 `hardness1~11F`hsuffix2~1 `hardness2~1 `layerNumber~16`fromTop~1true`multipleGrainType~1false`multipleGrainSize~1true`grainSize1~12.0`waterContent~1 `endDepth~140`|3CgrainSizeUnits1~1mm`grainSizeUnits2~1mm`grainType~1Rounded polycrystals`grainSize~13.0`multipleHardness~1true`startDepth~140`grainSuffix~1 `multipleDensity~1false`grainSuffix1~1 `hsuffix1~1 `hardness1~11F`hsuffix2~1-`hardness2~1P`layerNumber~17`fromTop~1true`multipleGrainType~1false`multipleGrainSize~1true`grainSize1~15.0`waterContent~1Moist`endDepth~163`|3CgrainSizeUnits1~1mm`grainSizeUnits2~1mm`grainType~1Rounded polycrystals`grainSize~11.0`multipleHardness~1false`startDepth~163`grainSuffix~1 `multipleDensity~1false`grainSuffix1~1 `hsuffix1~1 `hardness1~1P`hsuffix2~1 `hardness2~1 `layerNumber~18`fromTop~1true`multipleGrainType~1false`multipleGrainSize~1false`grainSize1~1 `waterContent~1Moist`endDepth~1110`|heightOfSnowpack~1150`shearTests~2$3Esdepth~152`depthUnits~1cm`code~1CT`quality~1Q2`dateString~11/11/2018`lengthOfColumn~10`character~1SC`numberOfTaps~1`fractureCat~1Fracture Character`lengthOfCut~10`score~1CTM`s~1CTM Q2 52 1/11/2018.19:20:54`releaseType~1`ctScore~111`|3Ecomments~1Irregular fail`sdepth~152`depthUnits~1cm`code~1CT`quality~1Q2`lengthOfColumn~10`dateString~11/11/2018`character~1SC`numberOfTaps~1`fractureCat~1Fracture Character`lengthOfCut~10`score~1CTH`s~1CTH Q2 52 1/11/2018.19:22:52`releaseType~1`ctScore~130`|precip~1None`serial~1Matthew Lucas1515726409132`version~1Version 10 - build 55 PC: Windows 10`crownObs~1false`timestamp~11515703800000`edited~1true`iDepth~1`activities~2 $currentEditTest~1CTH Q2 52 1/11/2018.19:20:40`airTemp~1-12.5`skiAreaPit~1false`");
    }
    
    public Hashtable exportAttributes()
    {
    	setAttributes();
    	Hashtable atts = attributes;
    	atts.remove("currentEditLayer");
    	atts.remove("currentEditTest");
    	atts.remove("edited");
    	///atts.remove("skiBoot");
    	atts.remove("dateString");
    	atts.remove("archName");
    	return atts;
    }
    
    public void setAttributes()
    {
    	if (loc!=null) attributes.put("loc", loc);
    	if (aspect != null) attributes.put("aspect", aspect);
    	if (incline !=null) attributes.put("incline", incline);
    	if (precip!=null) attributes.put("precip", precip);
    	if (sky!=null)attributes.put("sky", sky);
    	if (windspeed!=null) attributes.put("windspeed", windspeed);
    	if (winDir!=null)attributes.put("winDir", winDir);
    	if (windLoading!=null) attributes.put("windLoading", windLoading);
    	if (skiBoot!=null) attributes.put("skiBoot", skiBoot);
    	if (surfacePen!=null)attributes.put("surfacePen", surfacePen);
    	if (airTemp!=null) attributes.put("airTemp", airTemp);
    	if (stability!=null) attributes.put("stability", stability);
    	if (pitNotes!=null)attributes.put("pitNotes", pitNotes);
    	if (crownObs!=null)attributes.put("crownObs", crownObs);
    	if (layers!=null)attributes.put("layers", layers);
    	if (shearTests!=null)attributes.put("shearTests", shearTests);
    	if (tempProfile!=null)attributes.put("tempProfile", tempProfile);
    	if (densityProfile!=null)attributes.put("densityProfile", densityProfile);
    	if (measureFrom!=null) attributes.put("measureFrom", measureFrom);
    	if (currentEditLayer != null) attributes.put("currentEditLayer", currentEditLayer);
    	if (currentEditTest != null) attributes.put("currentEditTest", currentEditTest);
    	if (dateString !=null) attributes.put("dateString", dateString);
    	if (user!=null)attributes.put("user", user);
    	if (activities!=null) attributes.put("activities", activities);
    	if (actsString!=null) attributes.put("actsString", actsString);
        if (date !=null)attributes.put("date", date);
        if (time!=null) attributes.put("time", time);
        if (timestamp!=null)attributes.put("timestamp", timestamp);
        //System.out.println("Date: "+date);
        //System.out.println("time: "+time);
        //System.out.println("timestamp: "+timestamp);
        if (edited!=null)attributes.put("edited", edited);
        if (serial!=null)attributes.put("serial", serial);
        if ( archname != null) attributes.put("archname", archname);
        if (testPit!=null)attributes.put("testPit", testPit);
        if (iLayerNumber!=null) attributes.put("iLayerNumber", iLayerNumber);
        if (iDepth!=null)attributes.put("iDepth", iDepth);
        if (version != null) attributes.put("version", version);
        if (bld!=null) attributes.put("bld", bld);
        if (skiAreaPit!=null) attributes.put("skiAreaPit", skiAreaPit);
        if (bcPit!=null) attributes.put("bcPit", bcPit);
        if (aviPit!=null) attributes.put("aviPit", aviPit);
        if (aviLoc!=null) attributes.put("aviLoc", aviLoc);
        if (heightOfSnowpack!=null) attributes.put("heightOfSnowpack", heightOfSnowpack);
    }
    
    public void getAttributes()
    {
        System.out.println("PitObs::getAttributes()");
    	StringSerializable genloc = (StringSerializable)attributes.get("loc");
    	if (genloc!=null) loc  = new avscience.wba.Location(genloc.dataString()); 
        System.out.println("gotLocation");
    	aspect = (String) attributes.get("aspect");
        System.out.println("gotspect");
    	incline = (String) attributes.get("incline");
    	precip = (String) attributes.get("precip");
    	sky = (String) attributes.get("sky");
    	windspeed = (String) attributes.get("windspeed");
    	winDir = (String) attributes.get("winDir");
    	windLoading = (String) attributes.get("windLoading");
    	skiBoot = (String) attributes.get("skiBoot");
    	Object sp = attributes.get("surfacePen");
    	if (sp!=null) surfacePen = sp.toString();
    	airTemp = (String) attributes.get("airTemp");
    	stability = (String) attributes.get("stability");
    	pitNotes = (String) attributes.get("pitNotes");
        System.out.println("gotPitNotes");
    	crownObs = (String) attributes.get("crownObs");
    	layers = (java.util.Vector<Layer>) attributes.get("layers");
    	if (layers==null) layers = new java.util.Vector<Layer>();
        System.out.println("gotLayers");
    	shearTests = (java.util.Vector<ShearTestResult>) attributes.get("shearTests");
    	if (shearTests==null) shearTests = new java.util.Vector<ShearTestResult>();
        System.out.println("gotShearTests");
    	tempProfile = (TempProfile) attributes.get("tempProfile");
        System.out.println("gotTP");
    	densityProfile = (DensityProfile) attributes.get("densityProfile");
        System.out.println("gotDP");
    	measureFrom = (String) attributes.get("measureFrom");
        System.out.println("gotMeasureFrom");
    	currentEditLayer = (String) attributes.get("currentEditLayer");
        System.out.println("editlayer");
    	currentEditTest = (String) attributes.get("currentEditTest");
        System.out.println("edittests");
    	dateString = (String) attributes.get("dateString");
        System.out.println("gotdatestring");
    	
        /*Object u = attributes.get("user");
        if (u!=null)
        {
            if ( u instanceof avscience.wba.User ) user = new avscience.ppc.User(((avscience.wba.User)u).dataString());
            else user = (avscience.ppc.User) u;
        }*/
    	//StringSerializable genuser = (StringSerializable) attributes.get("user");
    //	if (genuser!=null) user = new avscience.ppc.User(genuser.dataString());
       // System.out.println("gotUser");
    	Object o = attributes.get("activities");
    	if ( o instanceof java.util.Vector) activities = (java.util.Vector<String>) o;
    	else if ( o instanceof avscience.util.Vector )
    	{
    		activities = new java.util.Vector<String>();
    		avscience.util.Enumeration e = ((avscience.util.Vector)o).elements();
    		if ( e!=null )
    		{
    			while ( e.hasMoreElements())
    			{
    				activities.add(e.nextElement().toString());
    			}
    		}
    	}
    	else if ( o instanceof String )
    	{
    		setPitNotes(o.toString()+" "+getPitNotes());
    	}
    	else activities = new java.util.Vector<String>();
        System.out.println("gotActivities");
    	actsString = (String)attributes.get("actsString");
        date = (String) attributes.get("date");
        time = (String) attributes.get("time");
        timestamp = (String) attributes.get("timestamp");
        edited = (String) attributes.get("edited");
        System.out.println("gotedited");
        serial = (String) attributes.get("serial");
        archname = (String)attributes.get("archname");
        testPit = (String)attributes.get("testPit");
        bld = (String) attributes.get("bld");
        bcPit = (String) attributes.get("bcPit");
        System.out.println("gotBCit");
        skiAreaPit = (String) attributes.get("skiAreaPit");
        aviPit = (String) attributes.get("aviPit");
        aviLoc = (String) attributes.get("aviLoc");
        o = null;
        o = (String) attributes.get("iLayerNumber");
        if ( o!=null ) iLayerNumber = (String) o;
        o = attributes.get("iDepth");
       	if ( o!=null ) iDepth = (String) o;
       	o = attributes.get("version");
       	if ( o!=null ) version = (String) o;
       	
       	Object sph = attributes.get("heightOfSnowpack");
       	if (sph!=null) heightOfSnowpack = sph.toString();
    }
    
    public boolean isPracticePit()
    {
    	if ( testPit==null ) return false;
    	if ( testPit.equals("true")) return true;
    	else  return false;
    }
    
    public int getBuild()
    {
    	try
    	{
    		Integer I = new Integer(bld);
    		if (I!=null) return I.intValue();
    		else return -1;
    	}
    	catch(Exception e)
    	{
    		return -1;
    	}
    }
    
    public PitObs(avscience.ppc.User user, String serial, int bld, String version)
    {
        this.user = user;
        this.serial = serial;
        this.version=version;
        this.bld=bld+"";
        Date date = new Date();
        Time t = new Time();
        dateString = date.getDate()+":"+t.hour+":"+t.minute;
        setTimestamp(System.currentTimeMillis());
        measureFrom = user.getMeasureFrom();
    }
    
    public PitObs(avscience.wba.User user, String serial, int bld, String version)
    {
    	this(new avscience.ppc.User(user.dataString()), serial, bld, version);
    }
    
    public String getSerial()
    {
    	if (serial==null) serial = "";
    	return serial.trim();
    }
    
    public boolean getShare()
    {
    	return getUser().getShare();
    }
    
    public void setShare(boolean share)
    {
    	if (share) getUser().setShare("true");
    	else getUser().setShare("false");
    }
    
    public Layer getPILayer()
    {
        for(java.util.Enumeration layers = getLayers(); layers.hasMoreElements();)
        {
            Layer l = (Layer)layers.nextElement();
            if(layerIsCritical(l))
                return l;
        }

        return null;
    }

    
    
    public avscience.ppc.Layer getLayerByString(String s)
    {
    	s = s.trim();
    	if ( s.indexOf("PI") > 0 )
    	{
    	//	s.replaceAll("PI", " ");
    		s=s.substring(0, s.length()-2);
    	}
    	s = s.trim();
    	System.out.println("getLayerByString(): "+s);
    	
        for(java.util.Enumeration e = layers.elements(); e.hasMoreElements();)
        {
        	StringSerializable gl = (StringSerializable) e.nextElement();
        	
        	avscience.ppc.Layer l = new avscience.ppc.Layer(gl.dataString());
            System.out.println(l.toString());
            if(l.toString().trim().equals(s))return l;
        }
        return null;
    }
    
    public PitObs(String data)
    { 
    	this();
        popFromString(data);
    	getAttributes();
    //	updatePitLayers(this);
    }
    
   /* public void updatePitLayers(avscience.ppc.PitObs pit)
	{
		System.out.println("updatePitLayers()");
		if (pit==null)
		{
			System.out.println("Pit is null.");
			return;
		}
		avscience.ppc.User u = pit.getUser();
		boolean fromTop = false;
		if ( u.getMeasureFrom().trim().equals("top")) fromTop = true;
		else fromTop = false;
		java.util.Enumeration layers = pit.getLayers();
		while ( layers.hasMoreElements() )
		{
			Object o = layers.nextElement();
			if (o!=null)
			{
				String lstring = o.toString();
				if ( lstring!=null )
				{
					avscience.ppc.Layer l = pit.getLayer(lstring);
					if (l!=null)
					{
						l.setFromTop(fromTop);
						pit.updateCurrentEditLayer(l);
					}
						
				}
			}
				
		}
	}*/
    
    public void setDateString(String date, String time)
    {
    	String hr = "12";
    	String mn = "00";
    	String yr = "2004";
    	String mnth = "12";
    	String day = "1";
    	if ( time.trim().length() == 3 )
    	{
    		hr = time.substring(0, 1);
    		mn = time.substring(1, 3);
    	}
    	if ( time.trim().length() > 3 )
    	{
    		hr = time.substring(0, 2);
    		mn = time.substring(2, 4);
    	}
    	
    	if ( date.trim().length() > 7 )
    	{
    		yr = date.substring(0, 4);
    		mnth = date.substring(4, 6);
    		day = date.substring(6, 8);
    	}
    	
    	dateString = mnth+"/"+day+"/"+yr+":"+hr+":"+mn;
    	
    }
    
    public void orderLayers()
    {
    	Enumeration e = getLayers();
    	while ( e.hasMoreElements() )
    	{
    		avscience.ppc.Layer l = (avscience.ppc.Layer) e.nextElement();
    		double strt = l.getStartDepth();
    		double end = l.getEndDepth();
    		if (strt > end) l.swapDepths();
    	}
    }
    
    public String getDateString()
    {
        return dateString;
    }
    
    public void setHeightOfSnowpack(String height)
    {
    	heightOfSnowpack=height;
    }
    
    public String getHeightOfSnowpack()
    {
    	return heightOfSnowpack;
    }
    
    public String getName()
    {
    	System.out.println("getName()");
    	String ds="";
    	long ts = getTimestamp();
    	System.out.println("ts: "+ts);
    	if ( ts > 0 )
    	{
    		java.util.Date date = new java.util.Date(ts);
    		if (date!=null) ds =  date.toString();
    	}
    	else
    	{
    		if ( dateString == null ) dateString = "";	
        	ds = dateString;
       	}
       	if (ds==null) ds=" ";
        String name = "";
        if ( loc!=null ) name = loc.getLocName().trim();
        else name = "";
        
        if ( ds.length() > 10) ds = ds.substring(3, 10);
        if ( name == null ) name ="";
        String nds = "";
        
       // if (ds.length()>5)
       // {
       // 	nds=ds.substring(3, ds.length());
       // }
      //  else nds=ds=
        name = name + " : " + ds;
        name = name.trim();
        if ( name.length() > 26 ) name = name.substring(0, 26);
        return name;
    }
    
    /*public String getDate()
    {
    	if ( ts > 0 ) return new Date(ts).toString();
    	else return ds;
    }*/
    
    public String getDBName()
    {
        if (dateString==null) dateString = " ";
        String name = null;
        if ( loc!=null ) name = loc.getName().trim();
        else name = "";
        name = name + " " + dateString;
        name = name.trim();
        if ( name.length() > 24 ) name = name.substring(0, 24);
        return name;
    }
    
    public String getArchName()
    {
    	return archname;
    }
   
    public String getAspect()
    {
    	if ( aspect==null ) aspect ="";
        return aspect;
    }
    
    public String getIncline()
    {
    	if ( incline==null ) incline="";
        return incline;
    }
    
    public String getPrecip()
    {
        return precip;
    }
    
    public String getSky()
    {
        return sky;
    }
    
    public String getWindspeed()
    {
        return windspeed;
    }
    
    public String getWinDir()
    {
        return winDir;
    }
    
    public String getWindLoading()
    {
        return windLoading;
    }
    
    public String getSkiBoot()
    {
        return skiBoot;
    }
    
    public String getSurfacePen()
    {
    	if (surfacePen==null) return "";
        return surfacePen;
    }
    
    public String getAirTemp()
    {
    	if ( airTemp==null ) airTemp="";
        return airTemp;
    }
    
    public void setArchName(String archname)
    {
    	this.archname=archname;
    }
    
    public String getStability()
    {
        return stability;
    }
    
    public String getPitNotes()
    {
    	if ( pitNotes==null ) pitNotes=" ";
        return pitNotes;
    }
    
    public DensityProfile getDensityProfile()
    {
    	return densityProfile;
    }
    
    public void setDensityProfile(DensityProfile densityProfile)
    {
    	this.densityProfile = densityProfile;
    }
    
    public void setLocation(avscience.wba.Location loc)
    {
        this.loc = loc;
    }
    
    public avscience.wba.Location getLocation()
    {
    	if (loc==null)loc=new Location();
        return loc;
    }
    
    public String getTime()
    {
    	if (time==null) time="";
        return time;
    }
    
    public String getDate()
    {
        System.out.println("getDate()");
    	if ( date==null )
    	{
    		//return new Date(getTimestamp()).toString();
            return new Date(System.currentTimeMillis()).toString();
    	}
        else return date;
    }
    
    public void setUser(avscience.ppc.User user)
    {
    	this.user=user;
    }
    
    public User getUser()
    {
    	if (user==null)user=new User();
    	return user;
	}
    
    public boolean getCrownObs()
    {
    	if (( crownObs == null ) || ( crownObs.trim().length() < 4 )) return false;
        else if ( crownObs.equals("true")  ) return true;
        else return false;
    }
    
    public void setCrownObs(boolean b)
    {
        if ( b ) crownObs = "true";
        else crownObs = "false";
    }
 
    public void setAspect(String aspect)
    {
        this.aspect = aspect;
    }
    
    public void setIncline(String incline)
    {
        this.incline = incline;
    }
    
    public void setPrecip(String precip)
    {
        this.precip = precip;
    }
    
    public void setSky(String sky)
    {
        this.sky = sky;
    }
    
    public void setWindSpeed(String windspeed)
    {
        this.windspeed = windspeed;
    }
    
    public void setWinDir(String winDir)
    {
        this.winDir = winDir;
    }
    
    public void setWindLoading(String windLoading)
    {
        this.windLoading = windLoading;
    }
    
    public void setSkiBoot(String skiBoot)
    {
        this.skiBoot = skiBoot;
    }
    
    public void setSurfacePen(String surfacePen)
    {
        this.surfacePen = surfacePen;
    }
    
    public void setSerial(String serial)
    {
    	this.serial=serial;
    }
    
    public void setAirTemp(String airTemp)
    {
        this.airTemp = airTemp;
    }
    
    public void setTime(String time)
    {
        this.time = time;
    }
    
    public void setDate(String date)
    {
        this.date = date;
    }
    
    public void setStability(String stability)
    {
        this.stability = stability;
    }
    
    public void setPitNotes(String pitNotes)
    {
        this.pitNotes = pitNotes;
    }
    
    public void setActivities(java.util.Vector activities)
    {
        this.activities = activities;
    }    
    
    public String getMeasureFrom()
    {
        return measureFrom;
    }
    
    public String getActivitiesString()
    {
    	StringBuffer buffer = new StringBuffer();
    	Enumeration e = getActivities().elements();
    	while ( e.hasMoreElements() )
    	{
    		buffer.append(e.nextElement());
    		buffer.append(";");
    	}
    	return buffer.toString();
    }
    
    public java.util.Vector getActivities()
    {
        return activities;
    }
    
    public boolean hasTempProfile()
    {
        if ( tempProfile == null ) return false;
        
        else return true;
    }
    
    public boolean hasDensityProfile()
    {
        if ( densityProfile == null ) return false;
        
        else return true;
    }
    
    public boolean isSkiAreaPit()
    {
    	if (skiAreaPit==null) return false;
    	if ( skiAreaPit.equals("true") ) return true;
    	else return false;
    }
    
    public boolean isBCPit()
    {
    	if (bcPit==null) return false;
    	if ( bcPit.equals("true") ) return true;
    	else return false;
    }
    
    public boolean hasLayers()
    {
    	if (layers==null) return false;
        else if ( layers.size() > 0 ) return true;
        else return false;
    }
    
    public void setTempProfile(TempProfile tempProfile)
    {
        this.tempProfile = tempProfile;
    }
    
    public TempProfile getTempProfile()
    {
        return tempProfile;
    }
    
    public String getDepthUnits()
    {
        return tempProfile.getDepthUnits();
    }
    
    public void addLayer(String data)
    {
    	avscience.ppc.Layer layer = new avscience.ppc.Layer(data);
       	layers.addElement(layer);
    }
    
    public boolean getEdited()
    {
    	if ( edited==null) return false;
    	if ( edited.trim().equals("true")) return true;
    	else return false;
    }
    
    public void setEdited()
    {
    	edited = "true";
    }
    
    public int getCurrentLayerNumber()
    {
        int num = 0;
        Enumeration e = layers.elements();
        
        while ( e.hasMoreElements() )
        {
        	avscience.ppc.Layer l = (avscience.ppc.Layer) e.nextElement();
            if ( l.getLayerNumber() > num ) num=l.getLayerNumber();
        }
       
        return num+1;
    }
    
    public void removeLayer(String layerString)
    {
        Enumeration e = layers.elements();
        
        while ( e.hasMoreElements() )
        {
        	avscience.ppc.Layer l  = (avscience.ppc.Layer) e.nextElement();
        	if (l.getLString().equals(layerString)) layers.removeElement(l);
        	if (l.toString().equals(layerString)) layers.removeElement(l);
        }
    }
    
    
    public avscience.ppc.Layer getLayer(String layerString)
    {
        Enumeration e = layers.elements();
        
        while ( e.hasMoreElements() )
        {
        	StringSerializable gl = (StringSerializable) e.nextElement();
        	avscience.ppc.Layer l = new avscience.ppc.Layer(gl.dataString());
        	if (l.getLString().equals(layerString)) return l;
        }
        return null;
    }
    
    public avscience.ppc.Layer getCurrentEditLayer()
    {
        return getLayer(currentEditLayer);
    }
    
    public void setTimestamp(long time)
    {
    	System.out.println("setTimestamp()");
    	this.timestamp = time+"";
    	System.out.println("timestamp: "+timestamp);
    }
    
    public long getTimestamp()
    {
    	System.out.println("getTimestamp():");
       // return System.currentTimeMillis();
    	if (timestamp==null) return 0;
    	long ts=0;
    	if ( timestamp == null ) timestamp = "";
    	else
    	{
	    //	timestamp=timestamp.trim();
	      	if ( timestamp.length()>0 ) 
	      	{
	      		try
	      		{
	      			ts = new Long(timestamp).longValue();
	      		}
	      		catch(Throwable t){ts=0;}
	      	}
	    }
      	if ( ts < 1 )
	      	{
		      	boolean udate = true;
		      	
		      	String dt = getDate();
		      	if (dt.trim().length()<8) udate = false;
		      	String time = getTime();
		      	if ( udate )
		      	{
		      	
			      	String yr="0";
			      	String mnth="0";
			      	String dy="0";
			      	String hr = "0";
			      	String min = "0";
			      	if (!(dt.trim().length()<8)) 
			      	{
			      		yr = dt.substring(0, 4);
			      		mnth = dt.substring(4, 6);
			      		dy = dt.substring(6, 8);
			      	}
			      	
			      	if ( !(time.trim().length()<4))
			      	{
			      		hr = time.substring(0, 2);
			      		min = time.substring(2, 4);
			      	}
			      	
			      	int y = new Integer(yr).intValue();
			      	int m = new Integer(mnth).intValue()-1;
			      	int d = new Integer(dy).intValue();
			      	int h = new Integer(hr).intValue();
			      	int mn = new Integer(min).intValue();
			      	java.util.Calendar cal = java.util.Calendar.getInstance();
			      	cal.set(y, m, d, h, mn);
			      	ts = cal.getTimeInMillis();
			     }
			     
			}
			return ts;
    }
    
    public void addShearTestResult(String data)
    {
    	avscience.ppc.ShearTestResult res = new avscience.ppc.ShearTestResult(data);
        shearTests.addElement(res);
    }
    
    public void removeShearTestResult(String resultString)
    {
    	System.out.println("remove Test: "+resultString);
    	Enumeration e = shearTests.elements();
    	while ( e.hasMoreElements())
    	{
        	avscience.ppc.ShearTestResult res = (avscience.ppc.ShearTestResult) e.nextElement();
        	if ( res.toString().equals(resultString)) shearTests.removeElement(res);
        }
    }
    
    public void updateCurrentTestResult(avscience.ppc.ShearTestResult result)
    {
        removeShearTestResult(currentEditTest);
        addShearTestResult(result.dataString());
    }
    
    public avscience.ppc.ShearTestResult getShearTestResult(String testString)
    {
        avscience.ppc.ShearTestResult r = null;
        StringSerializable gt = null;
        Enumeration e = shearTests.elements();
        while ( e.hasMoreElements() )
        {
        	gt = (StringSerializable) e.nextElement();
        	r = new avscience.ppc.ShearTestResult(gt.dataString()); 
        	
        	String s = r.toString();
        	if ( testString.equals(s) ) 
        	{ 
        		return r;
        	}
        }
        return r;
    }
    
    public void setCurrentEditLayer(String layerString)
    {
        currentEditLayer = layerString;
    }
    
    public void setCurrentEditTest(String testString)
    {
        currentEditTest = testString;
    }
    
    public String getCurrentEditTest() { return currentEditTest; }
    
    public void updateCurrentEditLayer(avscience.ppc.Layer layer)
    {
        removeLayer(layer.getLString());
        removeLayer(layer.toString());
        addLayer(layer.dataString());
    }
    
    public Layer getCriticalLayer()
    {
    	Enumeration lys = getLayers();
    	while ( lys.hasMoreElements() )
    	{
    		Layer l = (Layer) lys.nextElement();
    		if (layerIsCritical(l)) return l;
    	}
    	return null;
    }
    
    public boolean layerIsCritical(Layer l)
    {
    	boolean critical=false;
    	
    	if ( hasProblemInterface())
    	{
    		if ( iLayerNumber.equals(l.getLayerNumberString())) critical = true;
    	}
    	
    	return critical;
    }
    
    public boolean isAviPit()
    {
    	if ( aviPit==null ) return false;
    	if (aviPit.equals("true")) return true;
    	else return false;
    }
    
    public boolean hasProblemInterface()
    {
    	if ( iLayerNumber.trim().length()>0) return true;
    	else return false;
    //if ( iDepth.trim().length() > 0 ) return true;
    //	else return false;
    }
    
    public void setProblemInterface(String iDepth, String iLayerNumber)
    {
    	this.iDepth=iDepth;
    	this.iLayerNumber=iLayerNumber;
    }
    
    public String[] getLayerStrings()
    {
    	String[] layerStrings = new String[layers.size()]; 
    	Enumeration e = layers.elements();
        int i = 0;
        while ( e.hasMoreElements() )
        {
        	avscience.ppc.Layer l = (avscience.ppc.Layer) e.nextElement();
        	
        	String s  = l.toString();
        	String lNum = l.getLayerNumber()+"";
        	if ( lNum.equals(iLayerNumber)) s = s+" PI";
        	layerStrings[i] = s;
        	i++;
        }
    	return layerStrings;
    }
    
    public String[] getTestResultStrings()
    {
        Enumeration tests = shearTests.elements();
        String[] resultStrings = new String[shearTests.size()]; 
        int i = 0;
        while ( tests.hasMoreElements() )
        {
            resultStrings[i] = tests.nextElement().toString();
            i++;
        }
        return resultStrings;
    }
    
    private java.util.Vector sortAscendingLayers(java.util.Vector layers)
    {
    	//System.out.println("sortAsc");
        boolean sorted = false;
        int length = layers.size();
        int i = 0;
        avscience.ppc.Layer layer;
        avscience.ppc.Layer layerInc;

        if (length > 0)
        {
            while (!sorted)
            {
                sorted = true;
                for(i=0; i<length - 1; i++)
                {
                	layer = (avscience.ppc.Layer) layers.elementAt(i);
                	int strt = layer.getStartDepthInt();
                	int end = layer.getEndDepthInt();
                    int n = strt+end;
                    layerInc = (avscience.ppc.Layer) layers.elementAt(i+1);
                    int istrt = layerInc.getStartDepthInt();
                    int iend = layerInc.getEndDepthInt();
                    int ninc = istrt+iend;
                  
                    if ( ninc < n )
                    {
                            layers.setElementAt(layerInc, i);
                            layers.setElementAt(layer, i+1);
                            sorted = false;
                    }
                }
            }
        }
        return layers;
    }
    
    public Vector getLayersVector()
    {
    	if ( layers==null ) layers = new Vector();
    ///	if ( layers.size()>1 ) layers = sortAscendingLayers(layers);
        return layers;
    }
    
    public Enumeration getLayers()
    {
    	if ( layers==null ) layers = new Vector();
        return layers.elements();
    }
    
    public Enumeration getShearTests()
    {
        if ( shearTests!=null) return shearTests.elements();
        else return new Vector().elements();
    }
    
    public String getKey()
    {
        return new String("B");
    }
}