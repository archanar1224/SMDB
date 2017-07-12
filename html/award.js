
function validateAward()
{

	/*var receivedOn = document.getElementById("receivedOn").value;
	var movieName = document.getElementById("movieName").value;
	var rating = document.getElementById("rating").value;
	var rdateRegex = new RegExp("^[0-9]{4}$");
	var durRegex = new RegExp("^[0-9]{3}$");*/
	var flag=true;
	
	 if(!document.getElementById("movieName").validity.valid)
	 {
		 flag = false;
		alert("Please enter the Title");
		//document.getElementById("name").value="";
	 }
	 if(!document.getElementById("receivedOn").validity.valid)
	 {
		 flag = false;
		alert("Please enter date");
		//document.getElementById("song").value="";
	 }
	 
	
	
if(flag==false)
	{
	
	}
else
	{
	addAward();
	}

	
}

function getMovieListAward(textfield)
{
	//var text=document.getElementById('name').value;//enter your field name
	var text=textfield.value;
	var length=textfield.id.length;
	//var num=textfield.id.substr(length-1,1);
	//alert(num);
	//alert("in search"+text);
	//alert(text);
	//alert("in get list mve award"+text);
	
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
					  $('#typeahead-target1').empty();
					  var x=resp.Data.length;
						 var count1=0;
						 var movielist=[];
					  for(var i=0;i<x;i++)
					  {
						  var input='^'+text;
						  var re = new RegExp(input,"i");
					
					       if(resp.Data[i]['picture'].match(re))
					    	   {
					    	   if(count1==0)
					    		   {
						    		   var label=document.createElement("label");
						    			label.innerHTML="Movies";
						    			var ul=document.getElementById("typeahead-target1");
						    			ul.appendChild(label);
						    			count1++;
					    		   }
									var div=document.createElement("div");
									div.setAttribute('value',resp.Data[i]['picture']);
									//alert(resp.Data1[i]['name']);
									var img=document.createElement("img");
									img.setAttribute('src',"http://localhost:8080/IMDB/files/"+resp.Data[i]['poster']);
									img.setAttribute('height','50px');
									img.setAttribute('width','50px');
									var a=document.createElement("a");
									a.setAttribute('href','http://localhost:8080/IMDB/html/Movie.html?key='+resp.Data[i]['id']);
									a.innerHTML=resp.Data[i]['picture'];
									var button=document.createElement("button");
									button.setAttribute('class','btn btn-primary');
									button.innerHTML="+";
									button.setAttribute('style','float:right');
									button.setAttribute('id','add_role_button'+resp.Data[i]['id']);
									var movieid=resp.Data[i]['id'];
									var moviename=resp.Data[i]['picture'];
									//alert(count_cast);
								//	var a1=document.createElement("a");
								//	a1.setAttribute('onclick','return populate_text('+num+')');
									$(document).on("click", "#add_role_button"+resp.Data[i]['id'],{movieid:resp.Data[i]['id'],moviename:resp.Data[i]['picture']},
											function(event){
										var textfield=document.getElementById('movieName');
										textfield.value=event.data.moviename;
										
										var ul=document.getElementById("typeahead-target1");
										$('#typeahead-target1').css('display','none');
										return false;
										
										});
								
										
									
									//a1.appendChild(button);
									div.appendChild(img);
									div.appendChild(a);
									div.appendChild(button);
									
									movielist.push(div);
									var li=document.createElement("li");
									
									li.appendChild(div);
								var ul=document.getElementById("typeahead-target1");
								ul.appendChild(li);
								ul.setAttribute('style','display:block;list-style-type:none');
					    	  }

					  	}//for



				  }//else
			  }//success
			});
}


function getPeopleListAward(textfield)
{
	//var text=document.getElementById('name').value;//enter your field name
	var text=textfield.value;
	var length=textfield.id.length;
	//var num=textfield.id.substr(length-1,1);
	//alert(num);
	//alert("in search"+text);
	//alert(text);
	
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
					  $('#typeahead-target2').empty();
					  var x=resp.Data1.length;
						 var count1=0;
						 var personlist=[];
					  for(var i=0;i<x;i++)
					  {
						  var input='^'+text;
						  var re = new RegExp(input,"i");
					
					       if(resp.Data1[i]['name'].match(re))
					    	   {
					    	   if(count1==0)
					    		   {
						    		   var label=document.createElement("label");
						    			label.innerHTML="People";
						    			var ul=document.getElementById("typeahead-target2");
						    			ul.appendChild(label);
						    			count1++;
					    		   }
									var div=document.createElement("div");
									div.setAttribute('value',resp.Data1[i]['name']);
									//alert(resp.Data1[i]['name']);
									var img=document.createElement("img");
									img.setAttribute('src',"http://localhost:8080/IMDB/files/"+resp.Data1[i]['person_poster']);
									img.setAttribute('height','50px');
									img.setAttribute('width','50px');
									var a=document.createElement("a");
									a.setAttribute('href','http://localhost:8080/IMDB/html/persontimeline.html?key='+resp.Data1[i]['personid']);
									a.innerHTML=resp.Data1[i]['name'];
									var button=document.createElement("button");
									button.setAttribute('class','btn btn-primary');
									button.innerHTML="+";
									button.setAttribute('style','float:right');
									button.setAttribute('id','add_cast_button'+resp.Data1[i]['personid']);
									var personid=resp.Data1[i]['personid'];
									var personame=resp.Data1[i]['name'];
									//alert(count_cast);
								//	var a1=document.createElement("a");
								//	a1.setAttribute('onclick','return populate_text('+num+')');
									$(document).on("click", "#add_cast_button"+resp.Data1[i]['personid'],{personid:resp.Data1[i]['personid'],personame:resp.Data1[i]['name']},
											function(event){
										var textfield=document.getElementById('personName');
										textfield.value=event.data.personame;
										var ul=document.getElementById("typeahead-target2");
										$('#typeahead-target2').css('display','none');
										return false;
										
										});
								
										
									
									//a1.appendChild(button);
									div.appendChild(img);
									div.appendChild(a);
									div.appendChild(button);
									
									personlist.push(div);
									var li=document.createElement("li");
									
									li.appendChild(div);
								var ul=document.getElementById("typeahead-target2");
								ul.appendChild(li);
								ul.setAttribute('style','display:block;list-style-type:none');
					    	  }

					  	}//for



				  }//else
			  }//success
			});
}