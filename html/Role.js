var count_movie=2;
var movie_id;
var movie_name;

function addRole()
{
	var data=new Array();
	//alert(count_cast);
for(var i=1;i<count_movie;i++)
	{
	data[i-1] = 
    {
        movieid:document.getElementById('hidden'+i).value,
		role:document.getElementById('role'+i).value
	 };
	}
	var dataString = JSON.stringify(data);
	alert(dataString);
	var person_id=getParameterByName('person_id');
	var dataJSON =
		{
			json: dataString,
			
		};
	
	var data='json='+dataString+'&person_id='+person_id;
$.ajax(
		{
		  type: "POST",
		  url:'http://localhost:8080/IMDB/AddRoleServlet',
		 data:data,
		  dataType:'json',  
		  success: function(resp)
		  {
			  if(resp==null)
				  {
				
				  }
			  else
			  {
				  window.location.href="http://localhost:8080/IMDB/html/persontimeline.html?key="+resp['person_id'];
			  }
			  
		  }
		}
		);




}

function getid()
{
var person_id=getParameterByName('person_id');
var person_name=getParameterByName('person_name');
document.getElementById('name').innerHTML=person_name;
var source = getParameterByName('src');
alert("img"+source);
document.getElementById('photo').setAttribute("src","http://localhost:8080/IMDB/files/"+source);



}

function getMovieListAdd(textfield)
{
	//var text=document.getElementById('name').value;//enter your field name
	var text=textfield.value;
	var length=textfield.id.length;
	var num=textfield.id.substr(length-1,1);
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
					  $('#typeahead-target'+num).empty();
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
						    			var ul=document.getElementById("typeahead-target"+num);
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
									button.setAttribute('id',num+'add_role_button'+resp.Data[i]['id']);
									var movieid=resp.Data[i]['id'];
									var moviename=resp.Data[i]['picture'];
									//alert(count_cast);
								//	var a1=document.createElement("a");
								//	a1.setAttribute('onclick','return populate_text('+num+')');
									$(document).on("click", "#"+num+"add_role_button"+resp.Data[i]['id'],{movieid:resp.Data[i]['id'],moviename:resp.Data[i]['picture']},
											function(event){
										var textfield=document.getElementById('moviename'+num);
										textfield.value=event.data.moviename;
										var hiddenfield=document.getElementById('hidden'+num);
										hiddenfield.value=event.data.movieid;
										var ul=document.getElementById("typeahead-target"+num);
										$('#typeahead-target'+num).css('display','none');
										return false;
										
										});
								
										
									
									//a1.appendChild(button);
									div.appendChild(img);
									div.appendChild(a);
									div.appendChild(button);
									
									movielist.push(div);
									var li=document.createElement("li");
									
									li.appendChild(div);
								var ul=document.getElementById("typeahead-target"+num);
								ul.appendChild(li);
								ul.setAttribute('style','display:block;list-style-type:none');
					    	  }

					  	}//for



				  }//else
			  }//success
			});
}
function populate_text(num)
{
	//alert("hello");
var textfield=document.getElementById('moviename'+num);
textfield.value=movie_name;
var hiddenfield=document.getElementById('hidden'+num);
hiddenfield.value=movie_id;
var ul=document.getElementById("typeahead-target"+num);
$('#typeahead-target'+num).css('display','none');
return false;

}

function addRows()
{
var table=document.getElementById('movietable');
var tr=document.createElement('tr');

var tdtitle=document.createElement('td');
tdtitle.setAttribute('class','inputtext');
tdtitle.innerHTML="Movie Name";
var td_element=document.createElement('td');
var inputtext=document.createElement('input');
inputtext.setAttribute('id','moviename'+count_movie);
inputtext.setAttribute('type','text');
inputtext.setAttribute('class','add');
inputtext.setAttribute('placeholder','enter movie name');
inputtext.setAttribute('autocomplete','off');
inputtext.setAttribute('onkeyup','return getMovieListAdd(this);');

var inputtext1=document.createElement('input');
inputtext1.setAttribute('id','hidden'+count_movie);
inputtext1.setAttribute('style','display:none');


var ul=document.createElement('ul');
ul.setAttribute('id','typeahead-target'+count_movie);
ul.setAttribute('class','tt-menu');
ul.setAttribute('data-click','search');
ul.setAttribute('style','list-style-type:none;display:none');

var tdtitlerole=document.createElement('td');
tdtitlerole.setAttribute('class','inputtext');
tdtitlerole.innerHTML="Role";
var role=document.createElement('select');
role.setAttribute('id','role'+count_movie);
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
td_element.appendChild(inputtext1);
td_element.appendChild(ul);
table.appendChild(tdtitle);
table.appendChild(td_element);
var tr1=document.createElement('tr');
table.appendChild(tr1);
table.appendChild(tdtitlerole);
table.appendChild(role);
var tr2=document.createElement('tr');
table.appendChild(tr2);
count_movie++;
}