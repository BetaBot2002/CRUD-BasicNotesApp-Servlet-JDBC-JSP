<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="notesApp.Notes" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css" integrity="sha512-SzlrxWUlpfuzQ+pcUCosxcglQRNAq/DZjVsC0lE40xsADsfeQoEypE+enwcOiGjk/bSuGGKHEyjSoQ1zVisanQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<title>Notes App</title>
</head>
<style>
	*{
		margin:0;
		padding:0;
		box-sizing:border-box;
		font-family:Arial
	}
	
	body{
		min-height:100vh;
	}
	
	main{
		width:100%;
		height:100vh;
		display:flex;
		flex-direction:row;
		align-items:center;
		justify-content:space-around;
		background:rgb(119, 149, 148);
	}
	
	.noteMaker,.notes{
		width:45%;
	}
	
	.noteMaker > form{
		height:100%;
		display:flex;
		flex-direction:column;
		align-items:left;
		justify-content:center;
	}
	
	.noteMaker > form > textarea{
		height:80vh;
		font-size:16px;
		padding:15px;
		resize:none;
		outline:none;
		border:none;
	}
	
	.noteMaker > form > input{
		height:8vh;
		padding:15px;
		font-size:18px;
		border-radius:10px 10px 0px 0px;
		outline:none;
		border:none;
		background:rgb(220, 213, 199);
	}
	
	.noteMaker > form > button{
		height:5vh;
		padding:5px;
		font-size:18px;
		color:aliceblue;
		font-weight:700;
		background:rgb(35, 39, 43);
		outline:none;
		border:none;
		border-radius:0px 0px 10px 10px;
		
	}
	
	.notes{
		display:flex;
		flex-direction:column;
		align-items:center;
		word-wrap:break-word;
		height:100vh;
		padding:15px;
		row-gap:15px;
		overflow-y:scroll;
	}
	
	.notes::-webkit-scrollbar {
	  width: 20px;
	}
	
	.notes::-webkit-scrollbar-track {
	  background-color: transparent;
	}
	
	.notes::-webkit-scrollbar-thumb {
	  background-color: #a8bbbf;
	  border-radius: 20px;
	  border: 6px solid transparent;
	  background-clip: content-box;
	}
	
	.notes::-webkit-scrollbar-thumb:hover {
	  background-color: #d6dee1;
	}
	
	.notes > div{
		display:flex;
		flex-direction:column;
		width:90%;
		height:max-content;
		padding:15px;
		border-radius:15px;
		box-shadow: rgba(50, 50, 93, 0.25) 0px 50px 100px -20px, rgba(0, 0, 0, 0.3) 0px 30px 60px -30px, rgba(10, 37, 64, 0.35) 0px -2px 6px 0px inset;		
		background:rgb(228, 221, 206)
	}
	
	.notes > div > h3{
		text-align:left;
	}
	
	.noteid{
		display:none
	}
	
	.logout{
		position:absolute;
		top:0;
		right:0;
		margin:5px;
		padding:10px;
		border-radius:5px;
		color:aliceblue;
		font-weight:700;
		background:rgb(35, 39, 43);
		outline:none;
		border:none;
	}
	
	.cancel{
		position:absolute;
		top:40px;
		right:0;
		margin:5px;
		padding:10px;
		border-radius:5px;
		color:aliceblue;
		font-weight:700;
		background:rgb(35, 39, 43);
		outline:none;
		border:none;
		width:70px;
	}
	
	.note{
		position:relative;
	}
	
	.note > button{
		position:absolute;
		top:10px;
		right:15px;
		font-size:14px;
		border:none;
		outline:none;
		background:transparent;
		border-radius:3px;
		padding:3px
	}
	
	.note > .Editbtn{
		right:40px;
	}
	
	.hidden{
		display:none;
	}
</style>
<body>
<%String status=(String)request.getParameter("status"); %>
<%if(status=="Not Inserted"){ %>
	<h1><%=status %></h1>
<%} %>
<main class="container">
<div class="noteMaker">
	<form action="#" method="post">
		<input type="text" name="noteID" class="noteid" required>
		<input type="text" placeholder="Enter the Title" name="title" class="title" required>
		<textarea rows="10" cols="10" name="content" class="content" placeholder="Write your thought...." required></textarea>
		<button class="add">+ Add Note</button>
		<button class="update hidden" type="submit" formaction="update">Update Note</button>
	</form>
</div>
<% ArrayList<Notes> Notes =(ArrayList<Notes>) request.getAttribute("Notes"); %>
<div class="notes">
	<%if(Notes.size()>0){ %>
	<%for(Notes note:Notes){ %>
		<div class="note">
			<h3 class="title-<%= note.getNoteID() %>"><%=note.getTitle() %></h3>
			<p class="content-<%= note.getNoteID() %>"><%= note.getContent() %></p>
			<button onClick="update('<%= note.getNoteID() %>')" class="Editbtn"><i class="fa-solid fa-pencil"></i></button>
			<button onClick="deleteNote('<%= note.getNoteID() %>')" form="delete" class="Delbtn"><i class="fa-solid fa-trash"></i></button>
		</div>
	<%} }else{%>
		<h1>No Notes Yet</h1>
	<%} %>
</div>
<form action="logout" method="post">
	<button class="logout">Log Out</button>
</form>

<form action="delete" method="post" id="delete" class="hidden">
	<input type="text" name="noteid" class="Delnoteid" required>
</form>
<button class="cancel" onClick="cancel()">Cancel</button>
</main>
</body>
<script>
	var uid=Date.now().toString(36) + Math.floor(Math.pow(10, 12) + Math.random() * 9*Math.pow(10, 12)).toString(36)
	document.querySelector('.noteid').value=uid
	
	document.querySelector('.notes').scrollTop=document.querySelector('.notes').scrollHeight
	
	function update(noteid){
		document.querySelector('.noteid').value=noteid
		document.querySelector('.title').value=document.querySelector('.title-'+noteid).innerText
		document.querySelector('.content').value=document.querySelector('.content-'+noteid).innerText
		document.querySelector('.add').classList.add('hidden')
		document.querySelector('.update').classList.remove('hidden')
	}
	
	function deleteNote(noteid){
		if(!confirm("Do you want to delete the note?")){
			event.preventDefault()
		}
		document.querySelector('.Delnoteid').value=noteid
	}
	
	function cancel(){
		location.reload();
	}
</script>
</html>