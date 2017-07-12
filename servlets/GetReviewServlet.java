

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

/**
 * Servlet implementation class GetReviewServlet
 */
@WebServlet("/GetReviewServlet")
public class GetReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetReviewServlet() {
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
		String movie_id = request.getParameter("movie_id");	 
		List<NameValuePair> postParams = new ArrayList<NameValuePair>();
		NameValuePair movieid= new BasicNameValuePair("movie_id",movie_id);
		postParams.add(movieid);
		
		String url = "http://localhost:8080/IMDBServices/rest/get/review";
		  
		    	
				
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		post.setEntity(new UrlEncodedFormEntity(postParams));
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
