package avscience.ppc;
import waba.util.Date;
import waba.sys.Time;
import avscience.wba.*;
public class ShearTestResult extends avscience.ppc.AvScienceDataObject
{
    public String code;
    private String score = "";
    private String quality = "";
    public String character = "";
    public String fractureCat="";
    private String sdepth = "";
    private String depthUnits= "";
    private String comments = " ";
    private String dateString = "";
    private String ctScore = "";
    private String ecScore="";
    public String numberOfTaps="0";
    public String releaseType="";
    /// PST
    public String lengthOfCut="0";
    public String lengthOfColumn="0";
    
    private String s;
    private int mult=1;
    
    public ShearTestResult(String data)
    {
    	this();
    	popFromString(data);
    	setAttributes();
    }
    
    public boolean isNoFail()
    {
    	boolean is = false;
    	if ( score==null) return false;
    	if ( score.equals("SBN") || score.equals("CTN") || score.equals("RB7") || score.equals("STN") || score.equals("ECTNR")) is=true;
    	return is;
    }
    
    public void setComments(String comments)
    {
    	this.comments=comments;
    }
    
    public ShearTestResult() {super();}
    
    public ShearTestResult(String code, String score, String quality, String sdepth, String depthUnits, String comments, String ctScore, String ecScore, String fractureChar, String cat)
    {
    	if ( code.equals("PST")) this.code=code;
        else this.code = score.substring(0, 2);
        this.score = score;
        this.quality = quality;
        this.sdepth = sdepth;
        this.depthUnits = depthUnits;
        this.comments = comments;
        this.ctScore = ctScore;
        this.ecScore=ecScore;
        this.character=fractureChar;
        this.fractureCat=cat;
        
        Date date = new Date();
        Time time = new Time();
        int hour = time.hour;
        int min = time.minute;
        int sec = time.second;
        int yr = date.getYear();
        int mnth = date.getMonth();
        int day = date.getDay();
       	dateString = mnth+"/"+day+"/"+yr;
        s = score + " " + quality + " " + sdepth + " " + dateString + "." + hour + ":" + min+ ":"+sec;
        if ( code.equals("PST"))  s=code+" "+s;
    }
    
    public String getComments()
    {
        if ( comments != null ) return comments;
        else return " ";
    }
    
	
	public double getDepthSI()
	{
		if ( isNoFail() ) return 0.0;
		double dsi = getDepthValue();
		if ( depthUnits.equals("inches") ) dsi = (dsi/2.54);
		return dsi;
	}
    
    public String getDepth()
    {
    	if ( isNoFail() ) return "0";
    	if (getDepthValueInt()<0) return "0";
    	if ( sdepth==null) sdepth="";
        return sdepth;
    }
    
    public int getDepthValueInt()
    {
    	if ( isNoFail() ) return 0;
    	return (int) (10*getDepthValue());
    }
    
    
    public int getCTScoreAsInt()
    {
        if (ctScore == null ) ctScore = "";
        ctScore = ctScore.trim();
        if (ctScore.length() < 1) return 0;
        Integer I = null;
        try
        {
            I = new java.lang.Integer(ctScore);
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
            return 0;
        }
        return I.intValue();
    }
    public int getECScoreAsInt()
    {
        if (ecScore == null ) ecScore = "";
        ecScore = ecScore.trim();
        if (ecScore.length() < 1) return 0;
        Integer I = null;
        try
        {
            I = new java.lang.Integer(ecScore);
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
            return 0;
        }
        return I.intValue();
    }
    
    public String getDateString()
    {
        return dateString;
    }
    
    public double getDepthValue()
    {
        if ( isNoFail() ) return 0.0;
        if ( sdepth == null ) sdepth = "";
        sdepth = sdepth.trim();
        if ( sdepth.length() < 1 ) return 0.0;
    	try
    	{
    	     return new Double(sdepth).doubleValue();
    	}
    	catch(Exception e)
    	{
    	    return 0.0;	
    	}
    	
    }
    
    public String getDepthUnits()
    {
    	if ( depthUnits==null) depthUnits = "";
        return depthUnits;
    }
    
    public String getQuality()
    {
    	if ( quality==null ) quality="";
        return quality;
    }
    
    public String getReleaseType()
    {
    	if ( releaseType==null )return "";
    	return releaseType;
    }
    
    public String getScore()
    {
    	if ( score==null) score="";
        return score;
    }
    
    public void setMult(int mult)
    {
    	this.mult = mult;
    }
    
    public int getMult()
    {
    	return mult;
    }
    
    public String toString()
    {
    	return s;
    }
    
    public String getCode()
    {
    	if (code==null) code="";
        return code;
    }
    
    public String getCTScore()
    {
    	if ( ctScore==null ) ctScore="";
    	return ctScore;
    }
    
    public String getECScore()
    {
    	if ( ecScore==null ) ecScore="";
    	return ecScore;
    }
    
    public int getNumberOfTaps()
    {
        if (numberOfTaps == null) return 0;
        if (numberOfTaps.trim().length()<1) return 0;
        return new Integer(numberOfTaps).intValue();
    }
    
    public int getLengthOfColumn()
    {
        if (lengthOfColumn == null) return 0;
        if (lengthOfColumn.trim().length()<1) return 0;
        return new Integer(lengthOfColumn).intValue();
    }
    
    public int getLengthOfCut()
    {
        if (lengthOfCut == null) return 0;
        if (lengthOfCut.trim().length()<1) return 0;
        return new Integer(lengthOfCut).intValue();
    }
    
    public void setAttributes()
    {
    	attributes.put("s", s);
    	attributes.put("code", code);
    	attributes.put("score", score);
    	attributes.put("ctScore", ctScore);
    	attributes.put("ecScore", ecScore);
    	attributes.put("quality", quality);
    	attributes.put("sdepth", sdepth);
    	attributes.put("depthUnits", depthUnits);
    	attributes.put("comments", comments);
    	attributes.put("dateString", dateString);
    	attributes.put("releaseType", releaseType);
    	attributes.put("lengthOfCut", lengthOfCut);
    	attributes.put("lengthOfColumn", lengthOfColumn);
    	attributes.put("character", character);
    	attributes.put("fractureCat", fractureCat);
    	attributes.put("numberOfTaps", numberOfTaps);
    }
    
    public void getAttributes()
    { 
    	s = (String) attributes.get("s");
    	code = (String) attributes.get("code");
    	score = (String) attributes.get("score");
    	ctScore = (String) attributes.get("ctScore");
    	ecScore = (String) attributes.get("ecScore");
    	quality = (String) attributes.get("quality");
    	sdepth = (String) attributes.get("sdepth");
    	depthUnits = (String) attributes.get("depthUnits");
    	comments = (String) attributes.get("comments");
    	dateString = (String) attributes.get("dateString");
    	releaseType = (String) attributes.get("releaseType");
    	if ( releaseType==null) releaseType="";
    	lengthOfColumn = (String) attributes.get("lengthOfColumn");
    	if ( lengthOfColumn==null) lengthOfColumn="";
    	lengthOfCut = (String) attributes.get("lengthOfCut");
    	if ( lengthOfCut==null) lengthOfCut="";
    	character = (String) attributes.get("character");
    	if ( character==null) character="";
    	fractureCat = (String) attributes.get("fractureCat");
    	if ( fractureCat==null) fractureCat="";
    	numberOfTaps=(String)attributes.get("numberOfTaps");
    	if (numberOfTaps==null)numberOfTaps="";
    }
    
    public String getKey()
    {
        return new String("E");
    }
    
    
}