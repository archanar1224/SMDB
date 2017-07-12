

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Servlet implementation class TopPeopleServlet
 */
@WebServlet("/TopPeopleServlet")
public class TopPeopleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TopPeopleServlet() {
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
		String url = "http://localhost:8080/IMDBServices/rest/get/featuredpeople";
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);

		
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
