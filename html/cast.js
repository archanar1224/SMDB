var count_cast=2;
var person_id;
var person_name;

function addCast()
{
	var data=new Array();
	//alert(count_cast);
for(var i=1;i<count_cast;i++)
	{
	data[i-1] = 
    {
        personid:document.getElementById('hidden'+i).value,
		role:document.getElementById('role'+i).value
	 };
	}
	var dataString = JSON.stringify(data);
	alert(dataString);
	var movie_id=getParameterByName('key1');
	var dataJSON =
		{
			json: dataString,
			
		};
	
	var data='json='+dataString+'&movie_id='+movie_id;
$.ajax(
		{
		  type: "POST",
		  url:'http://localhost:8080/IMDB/AddCastServlet',
		 data:data,
		  dataType:'json',  
		  success: function(resp)
		  {
			  if(resp==null)
				  {
				
				  }
			  else
			  {
				  window.location.href="http://localhost:8080/IMDB/html/Movie.html?key="+resp['movie_id'];
			  }
			  
		  }
		}
		);




}

function getid()
{
var movie_id=getParameterByName('key1');
var movie_name=getParameterByName('key2');
document.getElementById('name').innerHTML=movie_name;
var source = getParameterByName('src');
document.getElementById('posterimg').setAttribute("src","http://localhost:8080/IMDB/files/"+source);

}

function getPeopleListAdd(textfield)
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
						    			var ul=document.getElementById("typeahead-target"+num);
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
									button.setAttribute('id',num+'add_cast_button'+resp.Data1[i]['personid']);
									var personid=resp.Data1[i]['personid'];
									var personame=resp.Data1[i]['name'];
									//alert(count_cast);
								//	var a1=document.createElement("a");
								//	a1.setAttribute('onclick','return populate_text('+num+')');
									$(document).on("click", "#"+num+"add_cast_button"+resp.Data1[i]['personid'],{personid:resp.Data1[i]['personid'],personame:resp.Data1[i]['name']},
											function(event){
										var textfield=document.getElementById('personname'+num);
										textfield.value=event.data.personame;
										var hiddenfield=document.getElementById('hidden'+num);
										hiddenfield.value=event.data.personid;
										var ul=document.getElementById("typeahead-target"+num);
										$('#typeahead-target'+num).css('display','none');
										return false;
										
										});
								
										
									
									//a1.appendChild(button);
									div.appendChild(img);
									div.appendChild(a);
									div.appendChild(button);
									
									personlist.push(div);
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
var textfield=document.getElementById('personname'+num);
textfield.value=person_name;
var hiddenfield=document.getElementById('hidden'+num);
hiddenfield.value=person_id;
var ul=document.getElementById("typeahead-target"+num);
$('#typeahead-target'+num).css('display','none');
return false;

}

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
inputtext.setAttribute('autocomplete','off');
inputtext.setAttribute('onkeyup','return getPeopleListAdd(this);');

var inputtext1=document.createElement('input');
inputtext1.setAttribute('id','hidden'+count_cast);
inputtext1.setAttribute('style','display:none');


var ul=document.createElement('ul');
ul.setAttribute('id','typeahead-target'+count_cast);
ul.setAttribute('class','tt-menu');
ul.setAttribute('data-click','search');
ul.setAttribute('style','list-style-type:none;display:none');

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
count_cast++;
}