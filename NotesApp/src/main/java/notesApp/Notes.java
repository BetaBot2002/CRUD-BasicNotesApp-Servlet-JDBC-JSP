package notesApp;

public class Notes {
	private String noteID;
	private String title;
	private String content;
	
	public Notes(String noteID, String title, String content) {
		this.noteID = noteID;
		this.title = title;
		this.content = content;
	}

	public String getNoteID() {
		return noteID;
	}

	public void setNoteID(String noteID) {
		this.noteID = noteID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Notes [noteID=" + noteID + ", title=" + title + ", content=" + content + "]";
	}
	
	
	
}
