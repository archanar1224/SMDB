var songs=[];
function readPosterURL(input) {
	alert("in poster");

    if (input.files && input.files[0])
    {
        var reader = new FileReader();

        reader.onload = function (e)
        {
            $('#posterimg').attr('src', e.target.result) ;
        }

        reader.readAsDataURL(input.files[0]);
    }
}


function readActorPhoto(input) {
	alert("in actor photo");
    if (input.files && input.files[0])
    {
        var reader = new FileReader();

        reader.onload = function (e)
        {
        	alert("yes!")
            $('#photoimg').attr('src', e.target.result) ;
        }

        reader.readAsDataURL(input.files[0]);
    }
}


function readSong(input) {
	alert("in song");

    if (input.files && input.files[0])
    {
        var reader = new FileReader();

        /*reader.onload = function (e)
        {
           // $('#posterimg').attr('src', e.target.result) ;
           alert(e.target.result);
           alert("inside onload");
           songs.push(e.target.result);
        }*/
        alert($(input).val());
        alert("new");
        for(var i=0;i<input.files.length;i++)
        {
        	alert(input.files.item(i).name);
        	songs.push(input.files.item(i).name);
        }
		var str = "Added ";
        var songlist = "";
        str = str + songs[0];
        for(var i=1;i<songs.length;i++)
        	{
        		songlist = songlist + ","+ songs[i];
        	}
        $('#addedsongs').html(str+songlist);
        reader.readAsDataURL(input.files[0]);
        
    }
}






function addMovie()
{
	
	var form=new FormData(document.getElementById('moviedetails'));
	var file=document.getElementById('poster').files[0];
	form.append('image',file);
	var songfiles = document.getElementById('song');
	for(var i=0;i<songfiles.files.length;i++)
		{
		
		var name="song"+i;
		form.append(name,songfiles.files[i]);
		}
	var trailer=document.getElementById('trailer').files[0];
	form.append('trailer',trailer);
	alert("after loading files");
	$.ajax(
			{
			  type: "POST",
			  url: "../Uploadposter",
			  data: form,
			  contentType: false,
			  cache:false,
			   processData:false,
			    success: function(resp)
			  {
				//alert(resp);  
			
				
			  }
			
			  
			
			});



	console.log("inside movie js");
	var title = document.getElementById("name").value;
	var language = document.getElementById("language").value;
	var releasedate = document.getElementById("releasedate").value;
	var duration = document.getElementById("duration").value;
	var rating = document.getElementById("rating").value;
	var plot = document.getElementById("plot").value;
	var poster = document.getElementById("poster").value;
	var trailer = document.getElementById("trailer").value;
	var Genre=[];
	var j=0;
	for(var i=1;i<=5;i++)
		{
		if(document.getElementById("Genre"+i).checked==1)
		{
			Genre[j] = document.getElementById("Genre"+i).value;
			
			j++;
		}
		}
	//console.log(songs.files.length);
	var data = 
    {
        title:title,
	language:language,
	releasedate:releasedate,
	duration:duration,
	rating:rating,
	plot:plot,
	poster:poster,
	songs:songs,
	trailer:trailer,
	genre:Genre
	
        
    };
	var dataString = JSON.stringify(data);
	var dataJSON =
		{
			json: dataString
		};
	
	$.ajax(
			{
			  type: "POST",
			  url:'../AddMovie',
			  //url:'http://localhost:8081/IMDBServices/rest/Add/AddMovie?jsonData='+dataJSON,
			  data:dataJSON,
			  dataType:'json',  
			  success: function(resp){
				  if(resp==null)
					  {
					  	alert("null resp");
					  }
				  else
				  {
					  alert("in else");
					  window.location.href="http://localhost:8080/IMDB/html/Movie.html?key="+resp['movie_id'];

				  }
	                                },
        		 headers: {'Content-Type': 'application/x-www-form-urlencoded'}
           
			});
	
}

function readPhoto(input) {

    if (input.files && input.files[0])
    {
        var reader = new FileReader();

        reader.onload = function (e)
        {
            $('#photo').attr('src', e.target.result) ;
        }

        reader.readAsDataURL(input.files[0]);
    }
}




function addPerson()
{
	
	var form=new FormData(document.getElementById('persondetails'));
	var file=document.getElementById('actorphoto').files[0];
	form.append('image',file);
	
	alert("after loading files");
	$.ajax(
			{
			  type: "POST",
			  url: "../Uploadposter",
			  data: form,
			  contentType: false,
			  cache:false,
			   processData:false,
			    success: function(resp)
			  {
				//alert(resp);  
			
				
			  }
			
			  
			
			});


	
	
	console.log("js js");
	var personName = document.getElementById("name").value;
	var gender = document.getElementById("gender").value;
    var dob = document.getElementById("dob").value;
	var height =  document.getElementById("height").value;
	var birthplace = document.getElementById("birthplace").value;
	var photo = document.getElementById("actorphoto").value;
	console.log("gender "+ gender);
	var personJSON = 
    {
        name: personName,
        gender: gender,
        dob : dob,
        height : height,
        placeOfBirth : birthplace,
        photo : photo
        
    };

    var personString = JSON.stringify(personJSON);
    var dataJSON =
        {
            person: personString
        };
    
    $.ajax(
            {
              type: "POST",
              url:'../AddPersonServlet',
              //url:'http://localhost:8081/IMDBServices/rest/User/getMovie?jsonData='+dataJSON,
              data:dataJSON,
              dataType:'json',  
              success: function(resp){
                  if(resp==null)
                  {
                    
                  }
                  else
                  {
                	  window.location.href="http://localhost:8080/IMDB/html/persontimeline.html?key="+resp['person_id'];
                  }
                                    },
                 headers: {'Content-Type': 'application/x-www-form-urlencoded'}
           
            });





}
function addReview()
{
	var movie_id = getParameterByName('key');
	var name = document.getElementById("author").value;
	var review = document.getElementById("new_review").value;
	var data = 
	{
	    author:name,
		review:review,
		movie_id:movie_id
	}
	var dataString = JSON.stringify(data);
	var dataJSON =
		{
			json: dataString
		};
	$.ajax(
			{
			  type: "POST",
			  url:'../AddReviewServlet',
			  
			  data:dataJSON,
			  dataType:'json',  
			  success: function(resp){
				  if(resp==null)
				  {
					 alert("Review added successfully");		
				  }

				  
	                                },
        		 headers: {'Content-Type': 'application/x-www-form-urlencoded'}
           
			});



}



function addAward()
{
	
	
	
	var awardName = document.getElementById("awardName").value;
	var receivedOn = document.getElementById("receivedOn").value;
    var awardTitle = document.getElementById("awardTitle").value;
	var movieName =  document.getElementById("movieName").value;
	var personName = document.getElementById("personName").value;
	//var song = document.getElementById("songs").value;
	alert(personName + " " + awardName + " " + receivedOn + " " + awardTitle + " " + movieName);
	var awardJSON = 
    {
		awardName: awardName,
		receivedOn: receivedOn,
        title : awardTitle,
        movieName : movieName,
        personName : personName
      //  songName : song
        
    };

    var awardString = JSON.stringify(awardJSON);
    var dataJSON =
        {
            award: awardString
        };
    
    $.ajax(
            {
              type: "POST",
              url:'../AddAwardServlet',
              //url:'http://localhost:8080/IMDBServices/rest/User/getMovie?jsonData='+dataJSON,
              data:dataJSON,
              dataType:'json',  
              success: function(resp){
                  if(resp==null)
                      {
                	  	
                      }
                  else
                  {
                     if(resp['flag'])
                     {
                    	 	alert(resp["message"]);
                    	 	window.location.href="http://localhost:8080/IMDB/";
                     }
                     else
                     {
                    	alert(resp["message"]); 
                     }

                  }
                                    },
                 headers: {'Content-Type': 'application/x-www-form-urlencoded'}
           
            });





}

