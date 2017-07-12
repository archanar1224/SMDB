import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

@WebServlet("/verifyMovieServlet")
public class VerifyMovieServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	    
	    public VerifyMovieServlet() {
	        // TODO Auto-generated constructor stub
	    }
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			response.getWriter().append("Served at: ").append(request.getContextPath());
		}
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	
	String movie_name=request.getParameter("movie_name");
	System.out.println("in servlet verify movie" + movie_name);
	String url = "http://localhost:8080/IMDBServices/rest/verify/movie?movie_name="+movie_name;
	  
	    	
			
			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(url);
			//post.setEntity(new UrlEncodedFormEntity(postParams));
			HttpResponse rsp = client.execute(post);
			BufferedReader rd = new BufferedReader(new InputStreamReader(rsp
					.getEntity().getContent()));

			String line = "";
			String output="";
			while ((line = rd.readLine()) != null) {
				//System.out.println(line);
				output += line + System.getProperty("line.separator");
				

			}
			System.out.println(output);
			PrintWriter out = response.getWriter();
			out.println(output);
			out.close();
				

			
			

			
		}


	}