var count_cast=2;
var count_movie=2;

function addRows()
{
var table=document.getElementById('casttable');
var tr=document.createElement('tr');

var tdtitle=document.createElement('td');
tdtitle.setAttribute('class','inputtext');
tdtitle.innerHTML="Name";
var td_element=document.createElement('td');
var inputtext=document.createElement('input');
inputtext.setAttribute('id','personname'+count_cast);
inputtext.setAttribute('type','text');
inputtext.setAttribute('class','add');
inputtext.setAttribute('placeholder','enter cast name');

var tdtitlerole=document.createElement('td');
tdtitlerole.setAttribute('class','inputtext');
tdtitlerole.innerHTML="Role";
var role=document.createElement('select');
role.setAttribute('id','role'+count_cast);
role.setAttribute('style','height:40px');
var option1=document.createElement('option');
option1.innerHTML='Actor';
role.appendChild(option1);


var option2=document.createElement('option');
option2.innerHTML='Director';
role.appendChild(option2);

var option3=document.createElement('option');
option3.innerHTML='Singer';
role.appendChild(option3);

var option4=document.createElement('option');
option4.innerHTML='Actor';
option4.setAttribute('height','30px');
role.appendChild(option4);


td_element.appendChild(inputtext);
table.appendChild(tdtitle);
table.appendChild(td_element);
var tr1=document.createElement('tr');
table.appendChild(tr1);
table.appendChild(tdtitlerole);
table.appendChild(role);
var tr2=document.createElement('tr');
table.appendChild(tr2);
count_cast++;
}


function validatePerson()
{
	//var actorName = document.getElementById("name");
	/*var dob = document.getElementById("dob");
	var photo = document.getElementById("actorphoto");
	var birthplace = document.getElementById("birthplace");*/
	var height = document.getElementById("height").value;
	
	
	var heightRegex = new RegExp("^[0-9]{3}$");
	var flag=true;
	if(!heightRegex.test(height))
		{
		flag = false;
		alert("enter valid height");
		document.getElementById("height").value="";
		
		}
	
	
	 
	
	 if(alert(document.getElementById("name").validity.valid))
	 {
		 flag = false;
		alert("Please upload a photo");
		//document.getElementById("name").value="";
	 }
	
	
if(flag==false)
	{
	
	}
else
	{
	addPerson();
	}

}












function validateMovie()
{
	var releasedate = document.getElementById("releasedate").value;
	var duration = document.getElementById("duration").value;
	var rating = document.getElementById("rating").value;
	var rdateRegex = new RegExp("^[0-9]{4}$");
	var durRegex = new RegExp("^[0-9]{3}$");
	var flag=true;
	if((!rdateRegex.test(releasedate))||releasedate>2017)
		{
		flag = false;
		alert("enter a valid year[0000-2017]");
		document.getElementById("releasedate").value="";
		
		}
	if(!durRegex.test(duration))
		{
		flag= false;
		alert("enter valid duration[100-999]");
		document.getElementById("duration").value="";
		}
	if(rating<1||rating>10)
		{
		flag = false;
		alert("enter valid rating[1-10]");
		document.getElementById("rating").value="";
		}
	
	 if(!document.getElementById("name").validity.valid)
	 {
		 flag = false;
		alert("Please enter the Title");
		document.getElementById("name").value="";
	 }
	 if(!document.getElementById("song").validity.valid)
	 {
		 flag = false;
		alert("Please upload a song");
		document.getElementById("song").value="";
	 }
	 
	 if(!document.getElementById("trailer").validity.valid)
	 {
		 flag = false;
		alert("Please upload a trailer");
		document.getElementById("trailer").value="";
	 }
	 if(!document.getElementById("poster").validity.valid)
	 {
		 flag = false;
		alert("Please upload a poster");
		document.getElementById("poster").value="";
	 }
	 if(!document.getElementById("plot").validity.valid)
	 {
		 flag = false;
		alert("Please enter the Plot");
		document.getElementById("plot").value="";
	 }
	
if(flag==false)
	{
	
	}
else
	{
	addMovie();
	}

}




function verifyMovieName()
{
	var movie_name = document.getElementById('name').value;
	var data='movie_name='+movie_name;
	//alert("in on blur"+data);
	document.getElementById("searchlistdiv").setAttribute('style','list-style-type:none;display:none');
	$.ajax(
			{
			  type: "POST",
			  url:'http://localhost:8080/IMDB/verifyMovieServlet',
			  data:data,
			  dataType:'json',  
			  success: function(resp)
			  {
				  if(resp['movie_name']==null)
					  {
					  document.getElementById('submit').disabled=false;
					  document.getElementById('warning').innerHTML="";
					  }
				  else
				  {
					  document.getElementById('warning').innerHTML="Movie already there";
					  document.getElementById('submit').disabled=true;
				  }
			  },
			  headers: {'Content-Type': 'application/x-www-form-urlencoded'}
			});
	
	return false;
}
function deleteperson()
{
	var person_id=window.localStorage.getItem('person_key');
	var data='person_id='+person_id;
	$.ajax(
			{
			  type: "POST",
			  url:'http://localhost:8080/IMDB/DeletePersonServlet',
			 data:data,
			  dataType:'json',  
			  success: function(resp)
			  {
				  alert("person deleted successfully");
				  window.location.href="http://localhost:8080/IMDB";
				
			  } ,
			 headers: {'Content-Type': 'application/x-www-form-urlencoded'}
			});
	
	return false;
	
}

function delmovie()
{
	var movie_id=window.localStorage.getItem('movie_key');
	var data='movie_id='+movie_id;
	$.ajax(
			{
			  type: "POST",
			  url:'http://localhost:8080/IMDB/DeleteMovieServlet',
			 data:data,
			  dataType:'json',  
			  success: function(resp)
			  {
				  alert("movie deleted successfully");
				  window.location.href="http://localhost:8080/IMDB";
				
			  } ,
			 headers: {'Content-Type': 'application/x-www-form-urlencoded'}
			});
	
	return false;
}


function onloadmain()
{
	getlatestmovies();
	//getmoviessearch();
	var ele=document.getElementById("typeahead-target");
	ele.setAttribute('style','display:none');
	
}

function getlatestmovies()
{
	
	$.ajax(
			{
			  type: "POST",
			  url:'http://localhost:8080/IMDB/LatestMovieServlet',
			 
			  dataType:'json',  
			  success: function(resp){
				  if(resp==null)
					  {
					
					  }
				  else
				  {
					  var x=resp.Data.length;
					  for(var i=0;i<x;i++)
						  {
						  
					 /* document.getElementById("pane"+(i+1)).innerHTML=resp.Data[i]['picture'];
					  document.getElementById("pane"+(i+1)).innerHTML=resp.Data[i]['language'];
					  document.getElementById("pane"+(i+1)).innerHTML=resp.Data[i]['yearOfRelease'];
					  document.getElementById("pane"+(i+1)).innerHTML=resp.Data[i]['rating'];*/
						  
						  var id=resp.Data[i]['id'];
						
						  var picture="Title: "+'<a href="http://localhost:8080/IMDB/html/Movie.html?key='+id+'">'+resp.Data[i]['picture']+'</a><br>';
						  
						  var language="Language: "+resp.Data[i]['language']+'<br>';
						  var yearofrelease="Release Year: "+resp.Data[i]['yearOfRelease']+'<br>';
						  var rating="Rating: "+resp.Data[i]['rating'];
					
						  var img=  document.createElement("img");
						
						  img.setAttribute("src","pictures/"+resp.Data[i]['poster'])
						    img.setAttribute("align",'right')
						     img.setAttribute("width",'160px')
						      img.setAttribute("height",'80px')
						        img.setAttribute("padding-top",'0')
						  
						   
						  document.getElementById("pane"+(i+1)).innerHTML=picture+language+yearofrelease+rating;
						
						  document.getElementById("pane"+(i+1)).appendChild(img);	
						  }
					 
					 

				  }
	                                },
        		 headers: {'Content-Type': 'application/x-www-form-urlencoded'}
           
			});

}
function getMovieList()
{

	var text=document.getElementById('name').value;
	$.ajax(
			{
			  type: "POST",
			  url:'http://localhost:8080/IMDB/Moviesearchresult',
			 
			  dataType:'json',  
			  success: function(resp)
			  {
				  if(resp==null)
					  {
					
					  }
				  else
				  {
					var x=resp.Data.length;
					var movielist=[];
					$('#searchlistdiv').empty();
					var count=0;
					for(var i=0;i<x;i++)
					{
						  var input='^'+text;
						  var re = new RegExp(input,"i");
						  var mydiv = document.getElementById("searchlistdiv");
						  var ul = document.createElement("ul");
						  ul.setAttribute("id","searchlist");
						   if(resp.Data[i]['picture'].match(re))
				        	   {
							   		if(count==0)
								   {
										   var label=document.createElement("label");
											label.innerHTML="Movies";
											
											ul.appendChild(label);
											count++;
								   }
							   		var div=document.createElement("div");
							   		div.setAttribute('value',resp.Data[i]['picture']);
									var img=document.createElement("img");
									img.setAttribute('src',"http://localhost:8080/IMDB/files/"+resp.Data[i]['poster']);
									img.setAttribute('height','50px');
									img.setAttribute('width','50px');
									var a=document.createElement("p");
									//a.setAttribute('href','http://localhost:8080/IMDB/html/Movie.html?key='+resp.Data[i]['id']);
									a.innerHTML=resp.Data[i]['picture'];
									div.appendChild(img);
									div.appendChild(a);
						
									movielist.push(div);
									var li=document.createElement("li");
									li.appendChild(div);
									ul.appendChild(li);
									ul.setAttribute('style','display:block;list-style-type:none');
									mydiv.setAttribute('style','display:block;list-style-type:none');
									mydiv.appendChild(ul);
				        	   }
	
					}//for
		
				  }//else
			  }//success
			});
}
function getPeopleList()
{
	var text=document.getElementById('name').value;//enter your field name

	//alert("in search"+text);
	
	
	$.ajax(
			{
			  type: "POST",
			  url:'http://localhost:8080/IMDB/Moviesearchresult',
			 
			  dataType:'json',  
			  success: function(resp)
			  {
				  if(resp==null)
					  {
					
					  }
				  else
				  {
					  var x=resp.Data1.length;
					
							 var count1=0;
							 var personlist=[];
							 $("#searchlistdiv").empty();
						  for(var i=0;i<x;i++)
						  {
							  
							  var input='^'+text;
							  var re = new RegExp(input,"i");
							  var mydiv = document.getElementById("searchlistdiv");
				    		  var ul=document.createElement("ul");
				    		  ul.setAttribute("id", "searchlist");
						       if(resp.Data1[i]['name'].match(re))
						    	   {
						    	   if(count1==0)
						    		   {
							    		   var label=document.createElement("label");
							    			label.innerHTML="People";
							    			
							    			
							    			ul.appendChild(label);
							    			
							    			count1++;
						    		   }
										var div=document.createElement("div");
										div.setAttribute('value',resp.Data1[i]['name']);
										var img=document.createElement("img");
										img.setAttribute('src',"http://localhost:8080/IMDB/files/"+resp.Data1[i]['person_poster']);
										img.setAttribute('height','50px');
										img.setAttribute('width','50px');
										var a=document.createElement("p");
										//a.setAttribute('href','http://localhost:8080/IMDB/html/persontimeline.html?key='+resp.Data1[i]['personid']);
										a.innerHTML=resp.Data1[i]['name'];
										div.appendChild(img);
										div.appendChild(a);
										
										personlist.push(div);
										var li=document.createElement("li");
										
										li.appendChild(div);
									ul.appendChild(li);
									ul.setAttribute('style','display:block;list-style-type:none');
									mydiv.setAttribute('style','display:block;list-style-type:none');
									mydiv.appendChild(ul);
					    	  }

					  	}//for


					 
				  }//else
			  }//success
			});
}




function getlist()
{
	
	
	var text=document.getElementById('searchtext').value;

	//alert("in search"+text);
	
	
	$.ajax(
			{
			  type: "POST",
			  url:'http://localhost:8080/IMDB/Moviesearchresult',
			 
			  dataType:'json',  
			  success: function(resp)
			  {
				  if(resp==null)
					  {
					
					  }
				  else
				  {
					var x=resp.Data.length;
				  var movielist=[];
				  $('#typeahead-targetdiv').empty();
				var count=0;
				  for(var i=0;i<x;i++)
					  {
					  var input='^'+text;
					  var re = new RegExp(input,"i");
					  var mydiv=document.getElementById("typeahead-targetdiv");
					  var ul = document.createElement("ul");
					  ul.setAttribute("id","typeahead-target");
				   if(resp.Data[i]['picture'].match(re))
		        	   {
					   if(count==0)
						   {
						   var label=document.createElement("label");
							label.innerHTML="Movies";
							
							ul.appendChild(label);
							count++;
						   }
					   	var div=document.createElement("div");
					div.setAttribute('value',resp.Data[i]['picture']);
								var img=document.createElement("img");
								img.setAttribute('src',"http://localhost:8080/IMDB/files/"+resp.Data[i]['poster']);
								img.setAttribute('height','50px');
								img.setAttribute('width','50px');
								var a=document.createElement("a");
								a.setAttribute('href','http://localhost:8080/IMDB/html/Movie.html?key='+resp.Data[i]['id']);
								a.innerHTML=resp.Data[i]['picture'];
								div.appendChild(img);
								div.appendChild(a);
								
								movielist.push(div);
								var li=document.createElement("li");
								li.appendChild(div);
							ul.appendChild(li);
							ul.setAttribute('style','display:block;list-style-type:none');
							mydiv.setAttribute('style','display:block;list-style-type:none');
							mydiv.appendChild(ul);
        	   }
	
		  }//for
		var x=resp.Data1.length;
		 var count1=0;
	  for(var i=0;i<x;i++)
	  {
	  var input='^'+text;
	  var re = new RegExp(input,"i");
	  var mydiv = document.getElementById("typeahead-targetdiv");
	  var ul = document.createElement("ul");
	  ul.setAttribute("id","typeahead-target");
       if(resp.Data1[i]['name'].match(re))
    	   {
    	   if(count1==0)
    		   {
    		   var label=document.createElement("label");
    			label.innerHTML="People";
    			//var ul=document.getElementById("typeahead-target");
    			ul.appendChild(label);
    			count1++;
    		   }
		var div=document.createElement("div");
		div.setAttribute('value',resp.Data1[i]['name']);
					var img=document.createElement("img");
					img.setAttribute('src',"http://localhost:8080/IMDB/files/"+resp.Data1[i]['person_poster']);
					img.setAttribute('height','50px');
					img.setAttribute('width','50px');
					var a=document.createElement("a");
					a.setAttribute('href','http://localhost:8080/IMDB/html/persontimeline.html?key='+resp.Data1[i]['personid']);
					a.innerHTML=resp.Data1[i]['name'];
					div.appendChild(img);
					div.appendChild(a);
					
					movielist.push(div);
					var li=document.createElement("li");
					
					li.appendChild(div);
				ul.appendChild(li);
				ul.setAttribute('style','display:block;list-style-type:none');
				mydiv.setAttribute('style','display:block;list-style-type:none');
				mydiv.appendChild(ul);
    	   }

	  }//for



}//else
			  }//success
			});
}




function gettoppeople()
{
	
	$.ajax(
			{
			  type: "POST",
			  url:'http://localhost:8080/IMDB/TopPeopleServlet',
			 
			  dataType:'json',  
			  success: function(resp){
				  if(resp==null)
					  {
					
					  }
				  else
				  {
					  
						  var x=resp.Data.length;
						  for(var i=0;i<x;i++)
							  {
							  
						 /* document.getElementById("pane"+(i+1)).innerHTML=resp.Data[i]['picture'];
						  document.getElementById("pane"+(i+1)).innerHTML=resp.Data[i]['language'];
						  document.getElementById("pane"+(i+1)).innerHTML=resp.Data[i]['yearOfRelease'];
						  document.getElementById("pane"+(i+1)).innerHTML=resp.Data[i]['rating'];*/
							  
							  var id=resp.Data[i]['id'];
						
							  var picture="Name: "+'<a href="http://localhost:8080/IMDB/html/persontimeline.html?key='+id+'">'+resp.Data[i]['name']+'</a><br>';
							  
						
						
							  var img=  document.createElement("img");
							
							  img.setAttribute("src",'http://localhost:8080/IMDB/files/'+resp.Data[i]['dp'])
							    img.setAttribute("align",'right')
							     img.setAttribute("width",'160px')
							      img.setAttribute("height",'80px')
							        img.setAttribute("padding-top",'0')
							  
							   
							  document.getElementById("pane"+(i+1)).innerHTML=picture;
							
							  document.getElementById("pane"+(i+1)).appendChild(img);	
							  }
							 
							 

						  }},
		        		 headers: {'Content-Type': 'application/x-www-form-urlencoded'}
		           
					});
		}


function getParameterByName(name, url) {
    if (!url) {
      url = window.location.href;
    }
    name = name.replace(/[\[\]]/g, "\\$&");
    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, " "));
}

function displaypersondetails()
{
	var person_id = getParameterByName('key');
	window.localStorage.setItem('person_key',person_id);
	
	var data='person_id='+person_id;
	
	$.ajax(
			{
			  type: "POST",
			  url:'http://localhost:8080/IMDB/Retrievepersondetails',
			 data:data,
			  dataType:'json',  
			  success: function(resp){
				  if(resp==null)
					  {
					
					  }
				  else
				  {
					  alert(resp['movie_id'].length);
					
					  var awardAnchor = document.getElementById("awards");
					  awardAnchor.setAttribute("href","http://localhost:8080/IMDB/html/DisplayPersonAward.html?person_id="+resp['id']+"&person_name="+resp['name']);
					  document.getElementById("name").innerHTML=resp['name'];
					 document.getElementById("gender").innerHTML="<strong>Gender:</strong> "+resp['gender'];
				     document.getElementById("bday").innerHTML="<strong>DOB:</strong> "+resp['dob'];
					document.getElementById("height").innerHTML="<strong>Height:</strong> "+resp['height'];
					document.getElementById("placeofbirth").innerHTML="<strong>Place of Birth:</strong> "+resp['pob'];
					var img=document.getElementById("peopledp");
					var add = document.getElementById("addrole");
					add.setAttribute("href","http://localhost:8080/IMDB/html/addRole.html?person_id="+resp['id']+"&person_name="+resp['name']+"&src="+resp['dp']);
					img.setAttribute('src','http://localhost:8080/IMDB/files/'+resp['dp']);
					for(var i=0;i<4;i++)
					  {
					  
					  document.getElementById("movie"+(i+1)).setAttribute("href","http://localhost:8080/IMDB/html/Movie.html?key="+resp['movie_id'][i]);
					  document.getElementById("moviename"+(i+1)).innerHTML=resp['title'][i];
					  //alert(resp['photo'][i]);
					  document.getElementById("movieimg"+(i+1)).setAttribute("src","http://localhost:8080/IMDB/files/"+resp['poster'][i]);
					  }
					var allmovies = document.getElementById("allmovies");
					var alltable = document.createElement("table");
					alltable.setAttribute("align","center");
					for(var i=4;i<resp['movie_id'].length;i++)
						{
							var tr = document.createElement("tr");
							var td1 = document.createElement("td");
							var td2 = document.createElement("td");
							var td3 = document.createElement("td");
							td1.setAttribute("style","width:100px");
							td2.setAttribute("style","width:100px");
							td3.setAttribute("style","width:50px");
							var img = document.createElement("img");
							img.setAttribute("src","http://localhost:8080/IMDB/files/"+resp['poster'][i]);
							td1.appendChild(img);
							var a = document.createElement("a");
							a.innerHTML = resp['title'][i];
							//a.innerHTML="Nenunnanu";
							
							a.setAttribute("href","http://localhost:8080/IMDB/html/Movie.html/?key="+resp['movie_id'][i]);
							//a.onclick = function () { alert("yoyo"); var div=document.getElementById("typeahead-targetdiv"); div.setAttribute('style','display:block'); return true;} 
							td2.appendChild(a);
							
							//var p = document.createElement("p");
							p.innerHTML = resp['role'][i];
							p.innerHTML="Actor";
							td3.appendChild(p);
							tr.appendChild(td1);
							tr.appendChild(td2);
							tr.appendChild(td3);
							alltable.appendChild(tr);
						}
						allmovies.appendChild(alltable);	 
					for(var j=resp['movie_id'].length;j<4;j++)
					 {
						  $("#movie"+(j+1)).hide();
						  $('#movieimg'+(j+1)).hide();
						  $('#moviename'+(j+1)).hide();
					  }

						  }},
		        		 headers: {'Content-Type': 'application/x-www-form-urlencoded'}
		           
					});

	


}

function hidesearch()
{
	var div=document.getElementById("typeahead-targetdiv");
	div.setAttribute('style','display:none');
}


function displayMovieDetails()
{
	var movie_id = getParameterByName('key');
	//alert(movie_id);
	window.localStorage.setItem('movie_key',movie_id);
	var data = 'movie_id='+movie_id;
	$.ajax(
			{
			  type: "POST",
			  url:'http://localhost:8080/IMDB/GetMovieServlet',
			  data:data,
			  dataType:'json',  
			  success: function(resp){
				  if(resp==null)
					  {
					
					  }
				  else
				  {
					  //alert(movie_id);
					  document.getElementById("addcast_a").setAttribute("href","http://localhost:8080/IMDB/html/addcast.html?key1="+movie_id+"&key2="+resp['title']+"&src="+resp['poster']);
					  document.getElementById("review").setAttribute("href","http://localhost:8080/IMDB/html/Reviews.html?key="+movie_id+"&name="+resp['title']+"&img="+resp['poster']);
					  document.getElementById("award").setAttribute("href","http://localhost:8080/IMDB/html/DisplayMovieAward.html?key="+movie_id);
					  document.getElementById("title").innerHTML = resp['title'];
					  document.getElementById("rating").innerHTML = "Rating: "+resp['rating'];
					  document.getElementById("genre").innerHTML = resp['genre'];
					  document.getElementById("plot_summary").innerHTML = resp['plot_summary'];
					  document.getElementById("yearOfRelease").innerHTML = "Release: "+resp['yearOfRelease'];
					  document.getElementById("duration").innerHTML = "Duration: "+resp['duration'];
					  document.getElementById("language").innerHTML = "Language: "+resp['language'];
					var pic=document.getElementById("moviepic");
					pic.setAttribute('src',"http://localhost:8080/IMDB/files/"+resp['poster']);
					  for(var i=0;i<4;i++)
					  {
					  
					  document.getElementById("cast"+(i+1)).setAttribute("href","http://localhost:8080/IMDB/html/persontimeline.html?key="+resp['id'][i]);
					  document.getElementById("cast"+(i+1)).innerHTML=resp['name'][i];
					  alert(resp['photo'][i]);
					  document.getElementById("castimg"+(i+1)).setAttribute("src","http://localhost:8080/IMDB/files/"+resp['photo'][i]);
					  }
					  var videoname=resp['trailer'];
					  var video=document.getElementById("vid");
						video.setAttribute('src',"http://localhost:8080/IMDB/files/"+videoname);
					    
						video.setAttribute("width",'320px');
					      video.setAttribute("height",'240px');
					        video.setAttribute("controls",'true');
					        
					        var fullcast = document.getElementById("fullcast");
							for(var i=4;i<resp['id'].length;i++)
							{
								  var a = document.createElement("a");
								  a.innerHTML=resp['name'][i];
								  a.setAttribute("href","http://localhost:8080/IMDB/html/persontimeline.html?key="+resp['id'][i]);
								  var p = document.createElement("p");
								  p.innerHTML=resp['role'][i];
								  fullcast.appendChild(a);
								  fullcast.appendChild(p);
							}
					        
							for(var j=resp['id'].length;j<4;j++)
							{
							  $("#cast"+(j+1)).hide();
							  $('#castimg'+(j+1)).hide();
							}
							
					        var divid=document.getElementById("Multimedia");
					        alert(resp['songs'].length+resp['songs'][0]);
					        for(var i=0;i<resp['songs'].length;i++)
							//for(var i=0;i<2;i++)  
					        {
							  var songname=resp['songs'][i];
							  var audio=document.createElement("audio");
							  audio.setAttribute("src","http://localhost:8080/IMDB/files/"+songname);
							  audio.setAttribute("controls","true");
							  audio.setAttribute("autobuffer","true");
							  var name = document.createElement("p");
							  name.innerHTML=resp['songs'][i];
							  divid.appendChild(name);
							  divid.appendChild(audio);


							  						  
					  }
					    
					  
				  }
	                                },
        		 headers: {'Content-Type': 'application/x-www-form-urlencoded'}
           
			});
	
}



function loadReviews()
{
//	resp=[{"author":"haripriya","review":"Good Movie","date":"30-10-15"},{"author":"neha","review":"nice Movie","date":"30-11-16"}];
	var movie_id = getParameterByName('key');
	var movie_name = getParameterByName('name');
	var img = getParameterByName('img');
	var posterimg = document.getElementById("posterimg");
	posterimg.setAttribute("src","http://localhost:8080/IMDB/files/"+img);
	var mydiv = document.getElementById("movie_namediv");
	var h2 = document.createElement("h2");
	h2.innerHTML=movie_name;
	h2.setAttribute("style","color:blue");
	mydiv.appendChild(h2);
	//alert(movie_id);
	var data = 'movie_id='+movie_id;
	$.ajax(
			{
			  type: "POST",
			  url:'../GetReviewServlet',
			  data:data,
			  dataType:'json',  
			  success: function(resp)
			  {
				  
			  if(resp==null)
				  {
				  }
			  else
				  {
	
					var poster = document.getElementById("posterimg");
					poster.setAttribute("src","http://localhost:8080/IMDB/files/"+resp['poster']);
					
				  	var maindiv = document.getElementById("review");
					for(var i=0;i<resp['reviewData'].length;i++)
					{
					
					var div = document.createElement("div");
					var p1 = document.createElement("p");
					var p2 = document.createElement("p");
					var p3 = document.createElement("small");
					var br = document.createElement("br");
					p1.innerHTML="Author:"+resp["reviewData"][i]['author'];
					p1.setAttribute("style","font-size:20px;margin-bottom:0;margin-top:10");
					p2.setAttribute("style","font-size:15px;background-color:#D5D8DC;");
					p2.innerHTML=resp["reviewData"][i]['review'];
					p3.innerHTML=resp["reviewData"][i]['date'];
					p3.setAttribute("style","margin:0");
					div.appendChild(p1);
					div.appendChild(p3);
					div.appendChild(p2);
					div.appendChild(br);
					div.appendChild(br);
					div.appendChild(br);
					maindiv.appendChild(div);
					}
				  }
			  },
			  headers: {'Content-Type': 'application/x-www-form-urlencoded'}
		
		
	
        
				});




}



function loadPersonAward()
{
//	resp=[{"author":"haripriya","review":"Good Movie","date":"30-10-15"},{"author":"neha","review":"nice Movie","date":"30-11-16"}];
	var person_id = getParameterByName('person_id');
	var person_name = getParameterByName('person_name');
	//alert(movie_id);
	var data = 'person_id='+person_id;
	$.ajax(
			{
			  type: "POST",
			  url:'../GetPersonAwardServlet',
			  data:data,
			  dataType:'json',  
			  success: function(resp)
			  {
				  
			  if(resp==null)
			  {
			  }
			  else
			  {
	
				  var poster = document.getElementById("photo");
				  poster.setAttribute("src","http://localhost:8080/IMDB/files/"+resp['photo']);
					
				  var mainDiv = document.getElementById("awardDiv");
				 // var mainTable = document.getElementById("awardTable");
				  for(var i=0;i<resp['awardData'].length;i++)
				  {
					var table = document.createElement("table");
				
					var tr1 = document.createElement("tr");
					var td11 = document.createElement("td");
					var td12 = document.createElement("td");
					tr1.appendChild(td11);tr1.appendChild(td12);
					
					var tr2 = document.createElement("tr");
					var td21 = document.createElement("td");
					var td22 = document.createElement("td");
					tr2.appendChild(td21);tr2.appendChild(td22);
					
					var tr3 = document.createElement("tr");
					var td31 = document.createElement("td");
					var td32 = document.createElement("td");
					tr3.appendChild(td31);tr3.appendChild(td32);
					
					
					
					
					
					td11.innerHTML = "Award Name";
					td12.innerHTML = resp["awardData"][i]['awardName'];
					
					td21.innerHTML = "Award Title";
					td22.innerHTML = resp["awardData"][i]['title'];
					
					td31.innerHTML = "Received On";
					alert(resp["awardData"][i]['receivedOn']);
					td32.innerHTML = resp["awardData"][i]['receivedOn'];
					
					table.appendChild(tr1);table.appendChild(tr2);
					table.appendChild(tr3);
					
						var tr4 = document.createElement("tr");
						var td41 = document.createElement("td");
						var td42 = document.createElement("td");
						tr4.appendChild(td41);tr1.appendChild(td42);
						
						td41.innerHTML = "Movie";
						var a = document.createElement("a");
						a.innerHTML = resp["movieName"];
						a.setAttribute("href","http://localhost:8080/IMDB/html/Movie.html?key="+resp['movieID']);
						td42.appendChild(a);
						table.appendChild(tr4);
					
					
					
					
					mainDiv.appendChild(table);
					
					
					
					
					}
				  }
			  },
			  headers: {'Content-Type': 'application/x-www-form-urlencoded'}
		
		
	
        
				});




}



function loadMovieAward()
{
//	resp=[{"author":"haripriya","review":"Good Movie","date":"30-10-15"},{"author":"neha","review":"nice Movie","date":"30-11-16"}];
	var movie_id = getParameterByName('key');
	//alert(movie_id);
	var data = 'movie_id='+movie_id;
	$.ajax(
			{
			  type: "POST",
			  url:'../GetMovieAwardServlet',
			  data:data,
			  dataType:'json',  
			  success: function(resp)
			  {
				  
			  if(resp==null)
			  {
			  }
			  else
			  {
	
				  var poster = document.getElementById("posterimg");
				  poster.setAttribute("src","http://localhost:8080/IMDB/files/"+resp['poster']);
					
				  var mainDiv = document.getElementById("awardDiv");
				 // var mainTable = document.getElementById("awardTable");
				  for(var i=0;i<resp['awardData'].length;i++)
				  {
					var table = document.createElement("table");
				
					var tr1 = document.createElement("tr");
					var td11 = document.createElement("td");
					var td12 = document.createElement("td");
					tr1.appendChild(td11);tr1.appendChild(td12);
					
					var tr2 = document.createElement("tr");
					var td21 = document.createElement("td");
					var td22 = document.createElement("td");
					tr2.appendChild(td21);tr2.appendChild(td22);
					
					var tr3 = document.createElement("tr");
					var td31 = document.createElement("td");
					var td32 = document.createElement("td");
					tr3.appendChild(td31);tr3.appendChild(td32);
					
					
					
					
					
					td11.innerHTML = "Award Name";
					td12.innerHTML = resp["awardData"][i]['awardName'];
					
					td21.innerHTML = "Award Title";
					td22.innerHTML = resp["awardData"][i]['title'];
					
					td31.innerHTML = "Received On";
					alert(resp["awardData"][i]['receivedOn']);
					td32.innerHTML = resp["awardData"][i]['receivedOn'];
					
					table.appendChild(tr1);table.appendChild(tr2);
					table.appendChild(tr3);
					var awardType = "Person";
					if(resp["awardData"][i]['awardType'].toUpperCase() === awardType.toUpperCase())
					{
						var tr4 = document.createElement("tr");
						var td41 = document.createElement("td");
						var td42 = document.createElement("td");
						tr4.appendChild(td41);tr1.appendChild(td42);
						
						td41.innerHTML = "Person";
						td42.innerHTML = resp["awardData"][i]['person'];
						table.appendChild(tr4);
					}
					
					
					
					mainDiv.appendChild(table);
					
					
					/*p1.setAttribute("style","font-size:20px;margin-bottom:0");
					p2.setAttribute("style","font-size:25px;background-color:gray;width:800px");
					
					p3.setAttribute("style","margin:0");*/
					
					}
				  }
			  },
			  headers: {'Content-Type': 'application/x-www-form-urlencoded'}
		
		
	
        
				});




}

function showDetails()
{
	var title = document.getElementById("awardTitle").value;
	if(title == ("Best actor")||title == ("Best director"))
	{
		$('#personRow').show();	
		$('#songRow').hide();
	}
	if(title == ("movie"))
	{
		$('#personRow').hide();
		$('#songRow').hide();
	}
	if(title == ("song"))
	{
		$('#personRow').hide();
		$('#songRow').show();
	}
}
function hideDetails()
{

	$('#personRow').hide();	
	$('#songRow').hide();
}

function verifyPersonName()
{
	var person_name = document.getElementById('name').value;
	var data='person_name='+person_name;
	//alert("in on blur"+data);
	//$("#searchlist").remove();
	document.getElementById("searchlistdiv").setAttribute('style','list-style-type:none;display:none');
	$.ajax(
			{
			  type: "POST",
			  url:'http://localhost:8080/IMDB/verifyPersonServlet',
			  data:data,
			  dataType:'json',  
			  success: function(resp)
			  {
				  if(resp['person_name']==null)
					  {
					  document.getElementById('submit').disabled=false;
					  document.getElementById('warning').innerHTML="";
					  }
				  else
				  {
					  document.getElementById('warning').innerHTML="Person already there";
					  document.getElementById('submit').disabled=true;
				  }
			  },
			  headers: {'Content-Type': 'application/x-www-form-urlencoded'}
			});
	
	return false;
}
function onloadmain1()
{
	getlatestmovies1();
}
function getlatestmovies1()
{
	alert("get latest movies");
	
	$.ajax(
			{
			  type: "POST",
			  url:'http://localhost:8080/IMDB/LatestMovieServlet',
			 
			  dataType:'json',  
			  success: function(resp){
				  if(resp==null)
					  {
					
					  }
				  else
				  {
					  var x=resp.Data.length;
					  for(var i=0;i<x;i++)
						  {
						  
					 
						  
						  
						
						  var picture= resp.Data[i]['picture'];
						  
						  var language=resp.Data[i]['language'];
						  
						  var imgsrc = "http://localhost:8080/IMDB/files/"+resp.Data[i]['poster'];
						 
						  
						  
						  var moviepage = "http://localhost:8080/IMDB/html/Movie.html?key="+resp.Data[i]['id'];
						  
						  
						  var popoverVariable = '<a style="color:#3b5998;font-size:20px;top:40px;padding-top:20px;" href="'+moviepage+'"   data-html="true" class="x" data-placement="bottom" data-toggle="popover"  data-trigger="hover"  data-content=\' <div class="media-body"><p style="color:black"> <b>'+' </br> <b>Title:</b> '+ picture +' </br> <b>Language:</b> '+ language+' </p></div>\'>'+'<img src="'+imgsrc+'" style="width:280px;height:300px;"></a><br/>';
			              
						
						 $("#pane"+(i+1)).append(popoverVariable);
						 
						  
						  $('[data-toggle="popover"]').popover();
						 }
					 
					 

				  }
	                                },
        		 headers: {'Content-Type': 'application/x-www-form-urlencoded'}
           
			});

}
function gettoppeople1()
{
	alert("get featured people");
	
	$.ajax(
			{
			  type: "POST",
			  url:'http://localhost:8080/IMDB/TopPeopleServlet',
			 
			  dataType:'json',  
			  success: function(resp){
				  if(resp==null)
					  {
					
					  }
				  else
				  {
					  var x=resp.Data.length;
					  for(var i=0;i<x;i++)
						  {
						  
					 
						  
						  
						
						  var name= resp.Data[i]['name'];
						  
						  //var language=resp.Data[i]['language'];
						  
						  var imgsrc = "http://localhost:8080/IMDB/files/"+resp.Data[i]['dp'];
						 
						  
						  
						  var personpage = "http://localhost:8080/IMDB/html/persontimeline.html?key="+resp.Data[i]['id'];
						  
						  
						  var popoverVariable = '<a style="color:#3b5998;font-size:20px;top:40px;padding-top:20px;" href="'+personpage+'"   data-html="true" class="x" data-placement="bottom" data-toggle="popover"  data-trigger="hover"  data-content=\' <div class="media-body"><p style="color:black"> <b>'+' </br> <b>Name:</b> '+ name +' </br> </p></div>\'>'+'<img src="'+imgsrc+'" style="width:280px;height:300px;"></a><br/>';
			              
						
						 $("#pane"+(i+1)).append(popoverVariable);
						 
						  
						  $('[data-toggle="popover"]').popover();
						 }
					 
					 

				  }
	                                },
        		 headers: {'Content-Type': 'application/x-www-form-urlencoded'}
           
			});

}



