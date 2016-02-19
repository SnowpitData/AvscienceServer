import java.io.*;
import java.net.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import avscience.server.*;
import java.io.*;
import avscience.pda.Integer;
import avscience.wba.*;
////import java.awt.Toolkit;

public class PitListServlet extends HttpServlet
{
     DAO dao = new DAO();
     String data1="";
     String dsTotal = "";
     
     public void init()
     {
     	/*Toolkit.getDefaultToolkit();
     	try
     	{
     		super.init();
     	}
     	catch(Exception e)
     	{
     		System.out.println(e.toString());
     	}*/
     	
     }
     
     public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException 
     {
     	String data="";
     	String action=request.getParameter("action");
     	if (action==null)action="";
     	if ( action.equals("MAIL")) new Mailer().mail();
     	try
     	{
     		if ("updateDB".equals(request.getParameter("format")))
     		{
     			dao.checkPits();
     		}
     	}
     	catch(Exception e){System.out.println(e.toString());}
     	try
     	{
     		if ("writeAllPitsToFiles".equals(request.getParameter("format")))
     		{
     			dao.writePitsToFiles();
     		}
     	}
     	catch(Exception e){System.out.println(e.toString());}
     	try
     	{
     		if ("pitsfromquery".equals(request.getParameter("format")))
     		{
     			System.out.println("PitServlet pitsfromquery");
     			String query = request.getParameter("q");
     			query = URLDecoder.decode(query,  "UTF-8");
     			///LinkedHashMap pits = dao.getPitsFromQuery(query);
                        String[][] pits = dao.getPitsFromQuery(query);
     			ObjectOutputStream out = new ObjectOutputStream(response.getOutputStream());
                out.writeObject(pits);
                out.flush();
                out.close();
     		}
     	}
     	catch(Exception e){System.out.println(e.toString());}
     	
     	try
     	{
     		if ("writepitfiles".equals(request.getParameter("format")))
     		{
     			String user = request.getParameter("username");
     			dao.writePitFiles(user);
     		}
     	}
     	catch(Exception e){System.out.println(e.toString());}
        try
        {
            System.out.println("PitListServlet");
            if ("bigpitsend1".equals(request.getParameter("format")))
            {
            	String d1 = request.getParameter("PITDATA1");
                d1 = URLDecoder.decode(d1,  "UTF-8");
                if (( d1 != null ) & ( d1.trim().length()>0))
                {
                	data1 = d1;
                	System.out.println("data 1 recieved.");
                	return;
                }
            }
            if ("bigpitsend2".equals(request.getParameter("format")))
            {
            	String d2 = request.getParameter("PITDATA2");
                d2 = URLDecoder.decode(d2,  "UTF-8");
                if (( d2 != null ) & ( d2.trim().length()>0))
                {
                	System.out.println("data 2 recieved.");
                	if (( data1 != null ) & ( data1.trim().length()>0))
                	{
                		System.out.println("d1 and d2 recieved.");
                		data=data1+d2;
                ////P//itObs pit = new PitObs(data);
                    	dao.writePitToDB(data);
                    //	System.out.println("Ex. Pit: " + pit.getName() + " added.");
                    	return;
                	}
                }
            }
            ///////////
            String ds1 = "";
            String ds2 = "";
            String ds3 = "";
            if ("bigpit1".equals(request.getParameter("format")))
            {
            	ds1 = request.getParameter("PITDATA1");
            	ds1 = URLDecoder.decode(ds1,  "UTF-8");
            	if (( ds1!=null ) && ( ds1.trim().length() > 0 ))
            	{
            		dsTotal = ds1;
            		return;
            	}
        	}
        	
        	if ("bigpit2".equals(request.getParameter("format")))
            {
            	ds2 = request.getParameter("PITDATA2");
            	ds2 = URLDecoder.decode(ds2,  "UTF-8");
            	if (( ds2!=null ) && ( ds2.trim().length() > 0 ))
            	{
            		dsTotal += ds2;
            		return;
            	}
        	}
        	if ("bigpit3".equals(request.getParameter("format")))
            {
            	ds3 = request.getParameter("PITDATA3");
            	ds3 = URLDecoder.decode(ds3,  "UTF-8");
            	if ( ds3!=null )
            	{
            		dsTotal += ds3;
            		dao.writePitToDB(dsTotal);
            		return;
            	}
        	}
            ////////////
            if ("pitsend".equals(request.getParameter("format")))
            {
                data = request.getParameter("PITDATA");
                data = URLDecoder.decode(data,  "UTF-8");
                if (( data != null ) & ( data.trim().length()>0))
                {
                	System.out.println("Servlet pit recieved: ");
                    //PitObs pit = new PitObs(data);
                    dao.writePitToDB(data);
                 //  System.out.println("Pit: " + pit.getName() + " added.");
                    return;
                }
                
            }
            
            if ("occsend".equals(request.getParameter("format")))
            {
                data = request.getParameter("OCCDATA");
                data = URLDecoder.decode(data,  "UTF-8");
                if (( data != null ) & ( data.trim().length()>0))
                {
                   // AvOccurence occ = new AvOccurence(data);
                    dao.writeOccToDB(data);
                  //  System.out.println("Occ: " + occ.getPitName() + " added.");
                    return;
                }
            }
            
            
           
           /* if ("object".equals(request.getParameter("format")))
            {
            	System.out.println("Pit List.");
                Vector pits = dao.getPitList();
                //System.out.println("cleaning pit list.");
                //pits = cleanPitList(pits);
                ObjectOutputStream out = new ObjectOutputStream(response.getOutputStream());
                out.writeObject(pits);
                out.flush();
                out.close();
            }*/
            
            if ("listbyuser".equals(request.getParameter("format")))
            {
            	String user = request.getParameter("username");
                Vector pits = dao.getPitList(user);
                ObjectOutputStream out = new ObjectOutputStream(response.getOutputStream());
                out.writeObject(pits);
                out.flush();
                out.close();
            }
            
            if ("pitlist".equals(request.getParameter("format")))
            {
            	String[][] list = dao.getPitListArray(true);
            	ObjectOutputStream out = new ObjectOutputStream(response.getOutputStream());
                out.writeObject(list);
                out.flush();
                out.close();
            }
            
            if ("pitlistquery".equals(request.getParameter("format")))
            {
                System.out.println("Pit List Query: ");
                String q = request.getParameter("q");
                q = URLDecoder.decode(q,  "UTF-8");
                ObjectOutputStream out = new ObjectOutputStream(response.getOutputStream());
                Vector pits = null;
                try
                {
                    pits = dao.getPitListFromQuery(q);
                    //System.out.println("cleaning pit list.");
                    //pits = cleanPitList(pits);
                }
                catch(Exception e)
                {
                    out.writeObject(e.toString());
                    out.flush();
                    out.close();
                    System.out.println(e.toString());
                }
               
                if ( pits!=null )
                {
                    out.writeObject(pits);
                    out.flush();
                    out.close();
                }
            }
            
            if ("pitlistarrayquery".equals(request.getParameter("format")))
            {
                System.out.println("Pit List Array Query: ");
                String q = request.getParameter("q");
                q = URLDecoder.decode(q,  "UTF-8");
                ObjectOutputStream out = new ObjectOutputStream(response.getOutputStream());
                String[][] pits = null;
                try
                {
                    pits = dao.getPitListArrayFromQuery(q, true);
                }
                catch(Exception e)
                {
                    out.writeObject(e.toString());
                    out.flush();
                    out.close();
                    System.out.println(e.toString());
                }
               
                if ( pits!=null )
                {
                    out.writeObject(pits);
                    out.flush();
                    out.close();
                }
            }
            
            if ("occlistarray".equals(request.getParameter("format")))
            {
            	String[][] occs = dao.getOccListArray(true);
                ObjectOutputStream out = new ObjectOutputStream(response.getOutputStream());
                out.writeObject(occs);
                out.flush();
                out.close();
            }
            
            if ("occlistarrayquery".equals(request.getParameter("format")))
            {
                System.out.println("Occ Array List Query: ");
                String q = request.getParameter("q");
                q = URLDecoder.decode(q,  "UTF-8");
                System.out.println("QUERY::" + q);
                String[][] occs = null;
                ObjectOutputStream out = new ObjectOutputStream(response.getOutputStream());
                try
                {
                    occs = dao.getOccListArrayFromQuery(q, false);
                }
                catch(Exception e)
                {
                    out.writeObject(e.toString());
                    out.flush();
                    out.close();
                    System.out.println(e.toString());
                }
                
                if ( occs!=null)
                {
                    out.writeObject(occs);
                    out.flush();
                    out.close();
                }
            }
            
            if ("occlistquery".equals(request.getParameter("format")))
            {
                System.out.println("Occ List Query: ");
                String q = request.getParameter("q");
                q = URLDecoder.decode(q,  "UTF-8");
                System.out.println("QUERY::" + q);
                Vector occs = null;
                ObjectOutputStream out = new ObjectOutputStream(response.getOutputStream());
                try
                {
                    occs = dao.getOccListFromQuery(q);
                }
                catch(Exception e)
                {
                    out.writeObject(e.toString());
                    out.flush();
                    out.close();
                    System.out.println(e.toString());
                }
                
                if ( occs!=null)
                {
                    out.writeObject(occs);
                    out.flush();
                    out.close();
                }
                
                
            }
            
            
            if ("objectocc".equals(request.getParameter("format")))
            {
                Vector occs = dao.getOccList();
                ObjectOutputStream out = new ObjectOutputStream(response.getOutputStream());
                out.writeObject(occs);
                out.flush();
                out.close();
            }
            
            if ("locationlist".equals(request.getParameter("format")))
            {
                Vector locs = dao.getLocationList();
                ObjectOutputStream out = new ObjectOutputStream(response.getOutputStream());
                out.writeObject(locs);
                out.flush();
                out.close();
            }
            
            if ("rangelistall".equals(request.getParameter("format")))
            {
                Vector rgs = dao.getRangeListAll();
                ObjectOutputStream out = new ObjectOutputStream(response.getOutputStream());
                out.writeObject(rgs);
                out.flush();
                out.close();
            }
            
            if ("rangelist".equals(request.getParameter("format")))
            {
                Vector rgs = dao.getRangeList();
                ObjectOutputStream out = new ObjectOutputStream(response.getOutputStream());
                out.writeObject(rgs);
                out.flush();
                out.close();
            }
            
            if ("editnews".equals(request.getParameter("format")))
            {
            	String news = request.getParameter("news");
            	news = URLDecoder.decode(news , "UTF-8");
            	dao.setNewsProps(news);
            }
            
            if ("statelist".equals(request.getParameter("format")))
            {
                Vector locs = dao.getStateList();
                ObjectOutputStream out = new ObjectOutputStream(response.getOutputStream());
                out.writeObject(locs);
                out.flush();
                out.close();
            }
            
            if ("statelistall".equals(request.getParameter("format")))
            {
                Vector locs = dao.getStateListAll();
                ObjectOutputStream out = new ObjectOutputStream(response.getOutputStream());
                out.writeObject(locs);
                out.flush();
                out.close();
            }
            
            if ("tallytests".equals(request.getParameter("format"))) dao.tallyTests();
            
            if ( (request.getParameter("data") != null) && (request.getParameter("data").length() > 0) )
            {
                 String s = request.getParameter("data");
                 s = URLDecoder.decode(s , "UTF-8");
                 //PitObs pit = new PitObs(s);
                // if ( pit != null) System.out.println("PIT::" + pit.getName() + " recieved by servlet.");
                // else System.out.println("PIT recieved NULL:");
                 dao.writePitToDB(s);
            }
            
            if ( (request.getParameter("occdata") != null) && (request.getParameter("occdata").length() > 0) )
            {
                 String s = request.getParameter("occdata");
                 s = URLDecoder.decode(s , "UTF-8");
                // AvOccurence occ = new AvOccurence(s);
                // if ( occ != null) System.out.println("OCC::" + occ.getPitName() + " recieved by servlet.");
               //  else System.out.println("OCC recieved NULL:");
                 dao.writeOccToDB(s);
            }
            
            if ("pda".equals(request.getParameter("source")))
            {
                StringBuffer buffer = new StringBuffer();
                int l = -1;
                byte[] b = new byte[1];
                System.out.println("Reading from pda:: ");
                DataInputStream in = new DataInputStream(request.getInputStream());
                try
                {
                    l = 1;
                    while ( l > 0 )
                    {
                        l = in.available();
                        b = new byte[l];
                        in.read(b);
                        String s = new String(b);
                        if (( s != null) && ( s.length() > 0 )) buffer.append(s);
                   }
                }
                catch(Exception e){System.out.println(e.toString());}
                ///System.out.println("Bytes read:" + n + " " + s + " read from pda: ");
                getObjectsFromPDAString(buffer.toString());
                in.close();
                in = null;
                
            }
        }
        catch(Exception e){System.out.println(e.toString());}
           
     }
     
    Vector cleanPitList(Vector pitlist)
    {
    	avscience.server.CharacterCleaner cleaner = new avscience.server.CharacterCleaner();
    	Vector v = new Vector();
    	Enumeration e = pitlist.elements();
    	while (e.hasMoreElements())
    	{
    		String s = (String) e.nextElement();
    		System.out.println("pre-cleaned: "+s);
    		String ss = cleaner.cleanString(s);
    		System.out.println("CLEANED: "+ss);
    		v.add(ss);
    	}
    	return v;
    }
    private void getObjectsFromPDAString(String dataString)
    {
        String string = null;
        int end = -1;
        int newEnd = -1;
        avscience.wba.PitObs pit = null;
        avscience.wba.AvOccurence occ = null;
        
        end = dataString.indexOf(AvScienceDataObject.pitDelim);
        string = dataString.substring(0, end);
       //pit = new avscience.wba.PitObs(string);
        dao.writePitToDB(string);
       
        while ( true )
        {
            newEnd = dataString.indexOf(AvScienceDataObject.pitDelim, end + 1);
            if ( newEnd < 0 ) break;
            string = dataString.substring(end+1, newEnd);
            end = newEnd;
           // pit = new avscience.wba.PitObs(string);
            dao.writePitToDB(string);
            
        }

        while ( true )
        {
            newEnd = dataString.indexOf(AvScienceDataObject.occDelim, end + 1);
            if ( newEnd < 0 ) break;
            string = dataString.substring(end+1, newEnd);
            end = newEnd;
            //occ = new avscience.wba.AvOccurence(string);
            dao.writeOccToDB(string);
        }
    }
     
     public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException 
     {
        doGet(request, response);
     }
  
}