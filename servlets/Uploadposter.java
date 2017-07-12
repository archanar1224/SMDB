

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.io.FilenameUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

/**
 * Servlet implementation class Uploadposter
 */
@WebServlet("/Uploadposter")
public class Uploadposter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Uploadposter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();	
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if(isMultipart)
		{
		//	System.out.println("HI");
			 ServletFileUpload upload = new ServletFileUpload();
			 List<NameValuePair> postparams=new ArrayList<NameValuePair>();
		        try {
		
		        	FileItemIterator items = upload.getItemIterator(request);
		        	while (items.hasNext()) 
		        	{
		        		FileItemStream item= items.next();
		        	    if (item.isFormField()) 
		        	    {
		        	    	InputStream stream = item.openStream();
		        	    	 String name = item.getFieldName();
		        	    	    String value = Streams.asString(stream);
		        	    	    NameValuePair user=new BasicNameValuePair(name,value);
		        	    	    postparams.add(user);
		        	    	    
		        	    	    System.out.println(name+" "+value);		        	    	
		        	    } 
		        	
		        	    //    processUploadedFile(item);
		        	    else
		        	    {
		        	    	//System.out.println("Hello");
		        	    	try
		        	    	{
		        	    	String path="/home/ramesh/workspace/IMDB/WebContent/files";
		        	    	System.out.println(path);
		        	    	File uploadedFile = new File(path);
		        	    	if(!(uploadedFile.exists()))
		        	    		uploadedFile.mkdir();
		        	    	
		        	    	File savedFile=new File(uploadedFile.getAbsolutePath()+File.separator+item.getName());
		        	    	System.out.println("Hi"+savedFile);
		        	    	
		        	    	FileOutputStream file=new FileOutputStream(savedFile);
		        	    	 InputStream is=item.openStream();
		        	    	 int x=0;
		        	    	 byte[] b=new byte[1024];
		        	    	 
		        	    	 while((x=is.read(b))!=-1)
		        	    	 {
		        	    		 file.write(b, 0, x);
		        	    		 
		        	    	 }
		        	    	file.flush();
		        	    	file.close();
		        	    	
		        	    
		        	    	}
		        	    	catch(Exception e)
		        	    	{
		        	    		e.printStackTrace();
		        	    	}
		        	    	
		        	    }
		        	
		        	}
		        	
		        
		                }
		        catch (FileUploadException e) 
		        {
		        	System.out.println("Hey not able to upload");
		        	e.printStackTrace();
		        }
		        catch(Exception e)
		        {
		        	
		        }
		
		}
		
	}


}
