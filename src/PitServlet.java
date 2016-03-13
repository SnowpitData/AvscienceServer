import java.io.*;
import java.net.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import avscience.server.*;
import java.io.*;
import avscience.pda.Integer;
import avscience.wba.*;
import org.jdom.*;
import org.jdom.output.*;
import org.jdom.input.*;
import avscience.ppc.XMLReader;
import avscience.ppc.XMLWriter;
import avscience.ppc.PitObs;
import avscience.pc.PitFrame;
import javax.imageio.ImageIO;
//import java.awt.Toolkit;
import java.awt.image.*;
//import com.sun.image.codec.jpeg.*;

public class PitServlet extends HttpServlet
{
	//DAO dao;
	
     DAO dao = new DAO();
     
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
        System.out.println("PitServlet");
        String type = request.getParameter("TYPE");
        if ( type!= null ) type = type.trim();
        System.out.println("TYPE " + type);
        
        if ( type==null) return;
        if (type.equals("WRITE_ALL_TO_XML")) 
        {
        	dao.writeAllPitsToXML();
        	return;
        }
        
        if (type.equals("UPLOAD_TEST")) 
        {
        	new UploadTest();
        	return;
        }
        
        if (type.equals("WRITE_SLF")) 
        {
        	dao.writeSLFLayersToFile();
        	return;
        }
        
        if (type.equals("WRITE_ECTPITS")) dao.writeECPTTestPits();
            
        if (type.equals("WRITE_EMAIL_FILE")) dao.getEmailsAsCSVFile();
        
        if (type.equals("GET_ADDRESSES")) 
        {
        	dao.getUserAddreses();
        }
        if ( type.equals("DOWNLOAD"))
        {
        	String targ = request.getParameter("TARGET");
        	if (( targ!=null) && ( targ.trim().length() > 2 ))
        	{
        		try
        		{
        			dao.logDownload(targ);
        		}
        		catch(Exception e){System.out.println("download failed: "+e.toString());}
        		try
        		{
	        		if (targ.equals("INSTALLER")) response.sendRedirect("http://www.snowpilot.org/downloads/setup.exe");
	        		else if (targ.equals("PCPILOT")) response.sendRedirect("http://www.snowpilot.org/downloads/PC-Pilot.jar");
	        		else if (targ.equals("PDA")) response.sendRedirect("http://www.snowpilot.org/downloads/SnowPilot.prc");
	        	}
	        	catch(Exception e){System.out.println("download failed: "+e.toString());}
        		
        	}
        	
        }
       
        if ( type.equals("PIT") )
        {
            try
            {
                System.out.println("PitServlet");
                String name = (String) request.getParameter("PITNAME");
                System.out.println("Getting pit:: " + name);
                String pit = dao.getPit(name);
                if (pit == null) System.out.println("pit null.");
                //else System.out.println("PIT:: " + pit.getName());
              
                if (("object".equals(request.getParameter("format"))) && (pit != null) )
                {
                    ObjectOutputStream out = new ObjectOutputStream(response.getOutputStream());
                    out.writeObject(pit);
                    out.flush();
                    out.close();
                }
            }
            catch(Exception e){System.out.println(e.toString());}
        }
        
        if ( type.equals("PITSTRING") )
        {
            try
            {
                System.out.println("PitServlet");
                String name = (String) request.getParameter("PITNAME");
                System.out.println("Getting pit:: " + name);
                String data = dao.getPit(name);
                //if (pit == null) System.out.println("pit null.");
               // else System.out.println("PIT:: " + pit.getName());
              //	 = pit.dataString();
                if ( data!=null )
                {
                    ObjectOutputStream out = new ObjectOutputStream(response.getOutputStream());
                    out.writeObject(data);
                    out.flush();
                    out.close();
                }
            }
            catch(Exception e){System.out.println(e.toString());}
        }
        
        if (type.equals("GET_NEWS"))
        {
        	try
        	{
        		ObjectOutputStream out = new ObjectOutputStream(response.getOutputStream());
                out.writeObject(dao.getNews());
                out.flush();
                out.close();
        	}
        	catch(Exception e){System.out.println(e.toString());}
        }
        
        if (type.equals("UPDATE_LAYERS"))
        {
        	dao.updateLayers();
        }
        
        if ( type.equals("OCC") )
        {
            try
            {
                System.out.println("PitServlet: occ");
                String name = (String) request.getParameter("OCCNAME");
                System.out.println("Getting occ:: " + name);
                AvOccurence occ = dao.getOcc(name);
                if (occ == null) System.out.println("occ null.");
                else System.out.println("OCC:: " + occ.getPitName());
              
                if (("object".equals(request.getParameter("format"))) && (occ != null) )
                {
                    ObjectOutputStream out = new ObjectOutputStream(response.getOutputStream());
                    out.writeObject(occ);
                    out.flush();
                    out.close();
                }
            }
            catch(Exception e){System.out.println(e.toString());}
        }
        
        if ( type.equals("PPCOCC") )
        {
            try
            {
            	String serial = (String) request.getParameter("SERIAL");
            	if (( serial!=null) && (serial.trim().length()>0))
                {
	                System.out.println("Getting occ:: # " + serial);
	                String data = dao.getPPCOcc(serial);
	                if (data == null)
	                {
	                	System.out.println("occ null.");
	                	data="";
	                }
	                
	                if ( data.trim().length()<3) System.out.println("No data for pit # "+serial);
	                
	                ObjectOutputStream out = new ObjectOutputStream(response.getOutputStream());
	                out.writeObject(data);
	                out.flush();
	                out.close();
	            }
            }
            catch(Exception e){System.out.println(e.toString());}
        }
        
        if ( type.equals("FIXOCCS")) dao.cleanOccData();
        
        if ( type.equals("OCCCROWNOBS"))
        {
        	try
            {
                String ser = (String) request.getParameter("SERIAL");
                if (( ser!=null) && (ser.trim().length()>0))
                {
	                System.out.println("Getting pit LOCAL SERIAL:: # " + ser);
	                String data = dao.getPitByLocalSerial(ser);
	                if (data == null)
	                {
	                	System.out.println("pit null.");
	                	data="";
	                }
	                
	                if ( data.trim().length()<3) System.out.println("No data for pit # "+ser);
	                
	                ObjectOutputStream out = new ObjectOutputStream(response.getOutputStream());
	                out.writeObject(data);
	                out.flush();
	                out.close();
	            }
	                
                
            }
            catch(Exception e){System.out.println(e.toString());}
        }
        if ( type.equals("XMLPITLIST"))
        {
        	Element e = new Element("Pit_List");
        	String[][] table = dao.getPitListArray(true);
        	int size = table[0].length;
        ///	if (size>24) size=24;
        	for ( int i = 0; i<size; i++ )
        	{
        		String serial = table[1][i];
        		String data = dao.getPPCPit(serial);
        		avscience.ppc.PitObs pit = new avscience.ppc.PitObs(data);
        		Element ep = new Element("pit");
        		Element number = new Element("number");
        		number.setText(i+"");
        		ep.addContent(number);
        		Element id = new Element("id");
        		id.setText(serial);
        		ep.addContent(id);
        		Element user = new Element("User");
        		avscience.ppc.User u = pit.getUser();
        		if (u==null)u = new avscience.ppc.User();
        		user.setText(u.getFirst()+" "+u.getLast());
        		ep.addContent(user);
        		Element loc = new Element("location");
        		loc.setText(pit.getLocation().getName());
        		ep.addContent(loc);
        		e.addContent(ep);
        	}
        	Document doc = new Document(e);
        	XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
			try
			{
				outputter.output(doc, response.getOutputStream());
			}
			catch(Exception ex)
			{
				System.out.println(ex.toString());
			}
	
        }
        //////////////////////////
        if ( type.equals("XMLPITLIST_FROMQUERY"))
        {
        	String query = (String) request.getParameter("QUERY");
        	Element e = new Element("Pit_List");
        	String[][] table = dao.getPitListArrayFromQuery(query, false);
        	int size = table[0].length;
        	for ( int i = 0; i<size; i++ )
        	{
        		String serial = table[1][i];
        		String data = dao.getPPCPit(serial);
        		avscience.ppc.PitObs pit = new avscience.ppc.PitObs(data);
        		Element ep = new Element("pit");
        		Element number = new Element("number");
        		number.setText(i+"");
        		ep.addContent(number);
        		Element id = new Element("id");
        		id.setText(serial);
        		ep.addContent(id);
        		Element user = new Element("User");
        		if ( pit!=null)
        		{
        			if ( pit.getUser()!=null)
        			{
        				user.setText(pit.getUser().getFirst()+" "+pit.getUser().getLast());
		        		ep.addContent(user);
		        		Element loc = new Element("location");
		        		loc.setText(pit.getLocation().getName());
		        		ep.addContent(loc);
		        		e.addContent(ep);
        			}
        		}
        	}
        	Document doc = new Document(e);
        	XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
			try
			{
				outputter.output(doc, response.getOutputStream());
			}
			catch(Exception ex)
			{
				System.out.println(ex.toString());
			}
        /*	XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
			try
			{
				String dir = "/Users/mark/Sites/";
				String fname = "PitsFromQuery"+query+".xml";
				File file = new File(dir, fname);
				try
				{
					file.delete();
				}
				catch(Exception eee){}
				file = new File(dir, fname);
				outputter.output(doc, new FileOutputStream(file));
				String url = "http://home.kahrlconsulting.com/"+fname;
	        	response.sendRedirect(url);
			}
			catch(Exception ex)
			{
				System.out.println(ex.toString());
			}*/
			
        }
        /////////////////////////////
        /////////////////////////////////
        if ( type.equals("IMAGE_FROM_XML"))
        {
        	System.out.println("IMAGE_FROM_XML");
        	Element er = new Element("Pit-send-status");
        	try
            {
            	SAXBuilder builder = new SAXBuilder();
            	System.out.println("Getting doc from input stream.....");
            	Document doc = builder.build(request.getInputStream());
            	System.out.println("Got doc: ");
               
                String name = "Pit:"+System.currentTimeMillis()+".xml";
                File pfile = new File(name);
                
                XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
                System.out.println("Saving pit info to xml: ");
				try
				{
					outputter.output(doc, new FileOutputStream(pfile));
				}
				catch(Exception ex)
				{
					System.out.println(ex.toString());
				}
				System.out.println("Saved pit to file: "+name);
				
				XMLReader reader = new XMLReader();
				System.out.println("Getting pit from Doc....");
				avscience.ppc.PitObs pit = reader.getPitFromDoc(doc);
                                if ( pit!=null )
                                {
                                        //new ImageWriter(pit, response, serial);

                                        System.out.println("Starting pit frame.");
                                        PitFrame frame = new PitFrame(pit, null, true);
                                        System.out.println("Getting image from pit frame.");
                                        BufferedImage image = frame.getPitImage();
                                        String serial = pit.getSerial();
                                        File f = new File("/Users/kahrlconsulting/Sites/kahrlconsulting/pits/"+serial+".jpg");
                                        if (f.exists()) f.delete();
                                        ImageIO.write(image, "jpg", f);
                                        
                                        String srl = "http://kahrlconsulting.com/pits/"+serial+".jpg";
                                        response.sendRedirect(srl);
                                        frame=null;
                                        image=null;
                                        f=null;
                                        
                                }
				
				///dao.writePitToDB(pit);
				
                ///er.setAttribute("status", "Pit Added to database.");
                //Document dr = new Document(er);
	         //   outputter = new XMLOutputter(Format.getPrettyFormat());
			///	outputter.output(dr, response.getOutputStream());
				//////////////////////
            }
            catch(Exception e)
            {
            	System.out.println(e.toString());
            	try
                {
	                er.setAttribute("status", "Could not render pti image ! "+e.toString());
	               Document dr = new Document(er);
	                XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
					outputter.output(dr, response.getOutputStream());
                }
                catch(Exception ee)
            	{
            		System.out.println(ee.toString());
            	}
            }
        }
        
        
        /////////////////////////////////
        //////////////////////////////
        
        if ( type.equals("XMLPIT_SEND"))
        {
        	System.out.println("XMLPIT_SEND");
        	Element er = new Element("Pit-send-status");
        	try
            {
            	SAXBuilder builder = new SAXBuilder();
            	System.out.println("Getting doc from input stream.....");
            	Document doc = builder.build(request.getInputStream());
            	System.out.println("Got doc: ");
               
                String name = "Pit:"+System.currentTimeMillis()+".xml";
                File pfile = new File(name);
                
                XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
                System.out.println("Saving pit info to xml: ");
				try
				{
					outputter.output(doc, new FileOutputStream(pfile));
				}
				catch(Exception ex)
				{
					System.out.println(ex.toString());
				}
				System.out.println("Saved pit to file: "+name);
				
				XMLReader reader = new XMLReader();
				System.out.println("Getting pit from Doc....");
				avscience.ppc.PitObs pit = reader.getPitFromDoc(doc);
				
				dao.writePitToDB(pit);
				
                er.setAttribute("status", "Pit Added to database.");
                Document dr = new Document(er);
	            outputter = new XMLOutputter(Format.getPrettyFormat());
				outputter.output(dr, response.getOutputStream());
				//////////////////////
            }
            catch(Exception e)
            {
            	System.out.println(e.toString());
            	try
                {
	                er.setAttribute("status", "Pit NOT Added to database: "+e.toString());
	                Document dr = new Document(er);
	                XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
					outputter.output(dr, response.getOutputStream());
                }
                catch(Exception ee)
            	{
            		System.out.println(ee.toString());
            	}
            }
        }
        
        //////////
       /* if ( type.equals("XMLPIT"))
        {
        	try
            {
                String ser = (String) request.getParameter("SERIAL");
                if (( ser!=null) && (ser.trim().length()>0))
                {
	                System.out.println("Getting pit for XML:: # " + ser);
	                String data = dao.getPPCPit(ser);
	                if (data == null)
	                {
	                	System.out.println("pit is NULL!!!!!.");
	                	data="";
	                }
	                
	                if ( data.trim().length()<3) System.out.println("No data for pit!! # "+ser);
	                avscience.ppc.PitObs pit = new avscience.ppc.PitObs(data);
	                avscience.ppc.XMLWriter writer = new avscience.ppc.XMLWriter();
	                Document doc = writer.getDocumentFromPit(pit);
	                
	                XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
					try
					{
						outputter.output(doc, response.getOutputStream());
					}
					catch(Exception ex)
					{
						System.out.println(ex.toString());
					}
	            //   /* String dir = "/Users/mark/Sites/";
	              //  String file = "Pit_"+ser+".xml";
	           //     File xmlFile = new File(dir, file);
	            //    avscience.ppc.XMLWriter writer = new avscience.ppc.XMLWriter(xmlFile);
	              //  avscience.ppc.PitObs pit = new avscience.ppc.PitObs(data);
	             //   writer.writePitToXML(pit);
	              //  String url = "http://home.kahrlconsulting.com/"+file;
	             //   response.sendRedirect(url);
	            }
            }
            catch(Exception e){System.out.println(e.toString());}
        } */
        ////////
        //////////
        if ( type.equals("CAAMLPIT"))
        {
        	try
            {
                String ser = (String) request.getParameter("SERIAL");
                if (( ser!=null) && (ser.trim().length()>0))
                {
	                System.out.println("Getting pit for XML:: # " + ser);
	                String data = dao.getPPCPit(ser);
	                if (data == null)
	                {
	                	System.out.println("pit is NULL!!!!!.");
	                	data="";
	                }
	                
	                if ( data.trim().length()<3) System.out.println("No data for pit!! # "+ser);
	                String dir = "/Users/mark/Sites/";
	                String file = "Pit_"+ser+"_CAAML.xml";
	                File xmlFile = new File(dir, file);
	                avscience.ppc.PitObs pit = new avscience.ppc.PitObs(data);
	                avscience.ppc.CAAMLWriter writer = new avscience.ppc.CAAMLWriter(pit, xmlFile);
	                writer.writePitToCAAML(pit);
	                String url = "http://home.kahrlconsulting.com/"+file;
	                response.sendRedirect(url);
	            }
            }
            catch(Exception e){System.out.println(e.toString());}
        }
        
        if ( type.equals("PPCPIT"))
        {
        	try
            {
                String ser = (String) request.getParameter("SERIAL");
                if (( ser!=null) && (ser.trim().length()>0))
                {
	                System.out.println("Getting pit:: # " + ser);
	                String data = dao.getPPCPit(ser);
	                System.out.println("servlet data: "+data);
	                if (data == null)
	                {
	                	System.out.println("pit null.");
	                	data="";
	                }
	                
	                if ( data.trim().length()<3) System.out.println("No data for pit # "+ser);
	                
	                ObjectOutputStream out = new ObjectOutputStream(response.getOutputStream());
	                out.writeObject(data);
	                out.flush();
	                out.close();
	            }
            }
            catch(Exception e){System.out.println(e.toString());}
        }
        
        
        if ( type.equals("DELETEPIT"))
        {
        	try
        	{
        		String serial = request.getParameter("SERIAL");
        		String user = request.getParameter("USERNAME");
        		String name = request.getParameter("PITNAME");
        		dao.deletePit(user, serial, name);
        	}
        	catch(Exception e){System.out.println(e.toString());}
        }
        
        if ( type.equals("DELETEDBPIT"))
        {
        	try
        	{
        		String serial = request.getParameter("DBSERIAL");
        		dao.deletePit(serial);
        	}
        	catch(Exception e){System.out.println(e.toString());}
        }
        
        if ( type.equals("DELETEOCC"))
        {
        	try
        	{
        		String serial = request.getParameter("SERIAL");
        		String user = request.getParameter("USERNAME");
        		String name = request.getParameter("OCCNAME");
        		dao.deleteOcc(user, serial, name);
        	}
        	catch(Exception e){System.out.println(e.toString());}
        }
        
        if ( type.equals("PITIMAGE"))
        {
               ///Toolkit.getDefaultToolkit();
        	try
        	{
        		String serial = request.getParameter("SERIAL");
        		System.out.println("Getting pit image for: "+serial);
        		String data = dao.getPPCPit(serial);
        		System.out.println("got data for: "+serial);
        		avscience.ppc.PitObs pit = new avscience.ppc.PitObs(data);
        		System.out.println("Pit constructed.");
        		if ( pit!=null )
        		{
        			//new ImageWriter(pit, response, serial);
        			
        			System.out.println("Starting pit frame.");
        			PitFrame frame = new PitFrame(pit, null, true);
        			System.out.println("Getting image from pit frame.");
        			BufferedImage image = frame.getPitImage();
        			File f = new File("/Users/kahrlconsulting/Sites/kahrlconsulting/pits/"+serial+".jpg");
        			if (f.exists()) f.delete();
                                ImageIO.write(image, "jpg", f);
        			/*FileOutputStream fout = new FileOutputStream(f);
        			   en = JPEGCodec.createJPEGEncoder(fout);
        			System.out.println("Encoding image.");
					en.encode(image);
					System.out.println("Writing image.");
	                fout.flush();
	                fout.close();*/
	                String srl = "http://kahrlconsulting.com/pits/"+serial+".jpg";
	                response.sendRedirect(srl);
	                frame=null;
	                image=null;
	                f=null;
	               // en=null;
	               // System.gc();
        		}
        		else System.out.println("Pit is null!!!!!!!!!!!!!");
        	}
        	catch(Exception e){System.out.println(e.toString());}
        }
        
        if ( type.equals("AUTHSUPERUSER"))
        {
        	System.out.println("AUTHSUPERUSER");
        	String auth = "FALSE";
        	try
            {
            	String name = request.getParameter("USERNAME");
            	String email = request.getParameter("EMAIL");
            	System.out.println("Name: "+name+" email: "+email);
            	if ((name!=null ) && ( email!=null))
            	{
            		name = name.trim();
            		email = email.trim();
            		if (( name.length() > 1 ) && ( email.length() > 1 ))
            		{
            			if ( dao.authSuperUser(name, email)) auth = "TRUE";
            		}
            	}
            	ObjectOutputStream out = new ObjectOutputStream(response.getOutputStream());
                out.writeObject(auth);
                out.flush();
                out.close();
            }
            catch(Exception e){System.out.println(e.toString());}
        }
        
        if ( type.equals("OCCURENCE_QUERY"))
        {
        	System.out.println("OCCURENCE_QUERY");
        	String user = request.getParameter("USER");
        	if (user!=null) user = URLDecoder.decode(user);
        	System.out.println("User: "+user);
        	String email = request.getParameter("EMAIL");
        	if (email!=null) email = URLDecoder.decode(email);
        	System.out.println("Email: "+email);
        	PrintWriter writer=null;
        	if ((user!=null) && ( email!=null))
        	{
        		if ((user.trim().length() > 0 ) && ( email.trim().length()>0))
        		{
        			if (dao.authDataUser(user, email))
        			{
        				System.out.println("User authed.");
        				String whereclause = request.getParameter("WHERECLAUSE");
        				whereclause = URLDecoder.decode(whereclause);
        				if ((whereclause!=null) && (whereclause.trim().length()>1))
        				{
        					System.out.println("Where clause: "+whereclause);
        					try
        					{
        						writer = response.getWriter();
        						StringBuffer buffer = new StringBuffer();
        						String[][] occs = dao.getOccListArrayFromQuery(whereclause, false);
        						
        						String[] sers = occs[1];
        						System.out.println(" occs recieved:: "+sers.length);
        						if ((sers!=null) && sers.length>0)
        						{
        							for (int i=0; i<sers.length; i++)
        							{
        								String sr = sers[i];
        								long otime=0;
        								String data = dao.getPPCOcc(sr);
        								if (( data!=null) && data.trim().length()>2)
        								{
        									avscience.ppc.AvOccurence occ = new avscience.ppc.AvOccurence(data);
        									String ser = occ.getSerial();
        									String pn = occ.getPitName();
        									avscience.ppc.PitObs pit=null;
									        if ((ser != null ) && ( ser.trim().length()>0))
									        {	
									        	System.out.println("getPitBySerial: "+ser);
									        	String pdata = dao.getPitByLocalSerial(ser);
									        	if ((pdata!=null) && (pdata.trim().length()>1))
									        	{
									        		System.out.println("getting pit by local serial.");
									        		pit = new avscience.ppc.PitObs(pdata);
									        		if (pit!=null) otime = pit.getTimestamp();
									        	}
									        	else System.out.println("Can't get Pit: "+ser +" by serial.");
									        }
									        else
									        {
									        	String wdata = dao.getPit(pn);
									        //	= wpit.dataString();
									        	if ((wdata!=null) && (wdata.trim().length()>1))
									        	{
									        		pit = new avscience.ppc.PitObs(wdata);
									        	}
									        	else System.out.println("Can't get Pit: "+pn +" by Name.");
									        }
        									//////////////////////////////////
        									
        									LinkedHashMap attributes = setLabels(pit);
									        Location loc = pit.getLocation();
									        avscience.util.Hashtable atts = null;
									        avscience.util.Enumeration e = null;
									        if (pit.getUser()!=null) buffer.append("User: " + pit.getUser().getName() + "\n");
									        buffer.append("Avalanche Occurrence Record: \n");
									        buffer.append(occ.getPitName() + "\n");
									        buffer.append("Location: \n");
									        if (loc!=null) buffer.append(loc.toString() + "\n");
									        atts = occ.attributes;
									        String dtime="";
									        long ltime = pit.getTimestamp();
									    	try
									    	{
									    		if ( ltime > 0 ) 
									    		{
									    			Date date = new Date(ltime);
									    			dtime = date.toString();
									    		}
									    	}
									    	catch(Exception ee){System.out.println(ee.toString());}
									        atts.put("dtime", dtime);
									       // e = attributes.keySet();
									       	Object[] keys = attributes.keySet().toArray();
									        System.out.println("attribute # "+atts.size());
									        String l = null;
									        String v = null;
									        for ( int j=0; j<keys.length; j++ )
									        {
									        	String s = keys[j].toString();
									            //System.out.println("s: "+s);
									            v = (String) atts.get(s);
									            //System.out.println("att: "+v);
									            l = (String) attributes.get(s);
									            //System.out.println("label: "+l);
									            s = l + " " + v + "\n";
									            if (( v!=null ) && ( v.trim().length() > 0 ))
									            {
									            	if (!( s.trim().equals("null")) )buffer.append(s);
									            }
        									}
        								}
        								buffer.append("\n\n");
        							}
        							System.out.println(buffer.toString());
        							writer.println(buffer.toString());
        						}
        					}
        					catch(Exception e)
        					{
        						System.out.println(e.toString());
        						if (writer!=null) writer.println(e.toString());
        					}
        				}
        			}
        		}
        	}
        }
        
        if ( type.equals("pda") )
        {
            System.out.println("getting data from pda::");
            DataInputStream in = null;
            StringBuffer buffer = new StringBuffer();
            int l = -1;
            byte[] b = new byte[1];
            System.out.println("Reading from pda:: ");
            try
            {
                in = new DataInputStream(request.getInputStream());
            
                l = 1;
                while ( l > 0 )
                {
                    l = in.available();
                    b = new byte[l];
                    in.read(b);
                    String s = new String(b);
                    if (( s != null) && ( s.length() > 0 )) buffer.append(s);
                }
            
                System.out.println("Bytes read: " + buffer.length() + " read from pda: ");
                //getObjectsFromPDAString(buffer.toString());
                in.close();
                in = null;
            }
            catch(Exception e){System.out.println(e.toString());}
            
            if ( request.getParameter("DATA").equals("PIT") )
            {
            	String d = buffer.toString();
                //PitObs pit = new PitObs(d);
                dao.writePitToDB(d);
            }
            
            if ( request.getParameter("DATA").equals("OCC") )
            {
                //AvOccurence occ = new AvOccurence(buffer.toString());
                dao.writeOccToDB(buffer.toString());
            }
            
        }
        
        
           
     }
     
     public LinkedHashMap setLabels(avscience.ppc.PitObs pit)
     {
     	LinkedHashMap attributes = new LinkedHashMap();
        avscience.ppc.User u = pit.getUser();
        if ( u==null ) u = new avscience.ppc.User();
        attributes.put("pitObs", "Name: ");
        attributes.put("dtime", "Date/Time: ");
        attributes.put("estDate", "Estimated date: ");
        attributes.put("estTime", "Estimated Time: ");
        attributes.put("elvStart", "Elevation Start: (" + u.getElvUnits() + ") ");
        attributes.put("elvDeposit", "Elevation Deposit: (" + u.getElvUnits() + ") ");
        attributes.put("fractureWidth", "Fracture Width: (" + u.getElvUnits() + ") ");
        attributes.put("fractureLength", "Fracture Length: (" + u.getElvUnits() + ") ");
        attributes.put("lengthOfAvalanche", "Avalanche Length: (" + u.getElvUnits() + ") ");
       
        attributes.put("aspect", "Primary Aspect: ");
        attributes.put("aspect1", "Aspect 1: ");
        attributes.put("aspect2", "Aspect 2: ");
        attributes.put("type", "Type: ");
        attributes.put("wcStart", "Water Content Start: ");
        attributes.put("wcDeposit", "Water Content Deposit: ");
        attributes.put("triggerType", "Trigger Type: ");
        attributes.put("triggerCode", "Trigger Code: ");
        attributes.put("causeOfRelease", "Cause of release: ");
        attributes.put("sympathetic", "Sympathetic? ");
        attributes.put("sympDistance", "Sympathetic/remote distance: ");
        
        attributes.put("USSize", "Size relative to Path: ");
        attributes.put("CASize", "Size destructive force: ");
        attributes.put("avgFractureDepth", "Average Fracture Depth: (" + u.getDepthUnits() + ") " );
        attributes.put("maxFractureDepth", "Max. Fracture Depth: (" + u.getDepthUnits() + ") ");
        attributes.put("levelOfBedSurface", "Level Of Bed Surface: ");
        attributes.put("weakLayerType", "Weak Layer Type: ");
        attributes.put("crystalSize", "Weak Layer Crystal Size: ");
        attributes.put("sizeSuffix", "Weak Layer Size suffix: ");
        attributes.put("weakLayerHardness", "Weak Layer Hardness: ");
        attributes.put("hsuffix", "Weak Layer Hardness suffix: ");
        
        attributes.put("crystalTypeAbove", "Crystal Type Above: ");
        attributes.put("crystalSizeAbove", "Crystal Size Above: ");
        attributes.put("sizeSuffixAbove", "Size suffix above: ");
        attributes.put("hardnessAbove", "Hardness above: ");
        attributes.put("hsuffixAbove", "Hardness suffix above: ");
        
        attributes.put("crystalTypeBelow", "Crystal Type Below: ");
        attributes.put("crystalSizeBelow", "Crystal Size Below: ");
        attributes.put("sizeSuffixBelow", "Size suffix below: ");
        attributes.put("hardnessBelow", "Hardness below: ");
        attributes.put("hsuffixBelow", "Hardness suffix below: ");
        attributes.put("snowPackType", "Snow Pack Typology: ");
        attributes.put("avgStartAngle", "Avg Start Angle: ");
        attributes.put("maxStartAngle", "Max Start Angle: ");
        attributes.put("minStartAngle", "Min Start Angle: ");
        attributes.put("alphaAngle", "Alpha Angle: ");
        attributes.put("depthOfDeposit",  "Depth of deposit: (" + u.getDepthUnits() + ") ");
        attributes.put("lengthOfDeposit", "Length of deposit: ");
        attributes.put("widthOfDeposit", "Width of deposit: ");
        attributes.put("densityOfDeposit", "Density of deposit (" + u.getRhoUnits() + ") ");
  //      attributes.put("areaOfDeposit", "Area of deposit: (square-" + u.getElvUnits() + ") ");
  		attributes.put("numPeopleCaught", "Number of people caught: ");
        attributes.put("numPeoplePartBuried", "Number of people part buried: ");
        attributes.put("numPeopleTotalBuried", "Number of people totally buried: ");
        
        attributes.put("injury", "Injuries: ");
        attributes.put("fatality", "Fatalites: ");
        
        attributes.put("bldgDmg", "Building Damage US $: ");
        attributes.put("eqDmg", "Equipment Damage US $: ");
        attributes.put("vehDmg", "Vehicle Damage US $: ");
        attributes.put("miscDmg", "Misc Damage US $: ");
        attributes.put("estDamage", "Total Damage US $: ");
        
        
        attributes.put("comments", "Comments: ");
        attributes.put("hasPit", "Has pit observation? ");
        return attributes;
    }
     
     public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException 
     {
        doGet(request, response);
     }
     
     
    private void getObjectsFromPDAString(String dataString)
    {
        System.out.println("writing pda data to DB.");
        String string = null;
        int end = -1;
        int newEnd = -1;
        avscience.wba.PitObs pit = null;
        avscience.wba.AvOccurence occ = null;
        
        end = dataString.indexOf(AvScienceDataObject.pitDelim);
        string = dataString.substring(0, end);
       // pit = new avscience.wba.PitObs(string);
        dao.writePitToDB(string);
       
        while ( true )
        {
            newEnd = dataString.indexOf(AvScienceDataObject.pitDelim, end + 1);
            if ( newEnd < 0 ) break;
            string = dataString.substring(end+1, newEnd);
            end = newEnd;
            //pit = new avscience.wba.PitObs(string);
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
    
    class PitImageWriter extends Thread
    {
    	PitObs pit;
    	HttpServletResponse response;
    	String serial;
    	public PitImageWriter(PitObs pit, HttpServletResponse response, String serial) 
    	{
    		this.pit=pit;
    		this.response=response;
    		this.serial=serial;
    	}
    	
    	public void run()
    	{
    	//	new ImageWriter(pit, response, serial);
    	}
    		
    }
  
}