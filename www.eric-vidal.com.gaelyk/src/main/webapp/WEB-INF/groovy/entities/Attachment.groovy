package entities

class Attachment implements Serializable {
	String blobid
	String filename
	String contentType
	Date creation
	long size
    //Key<BlogEntry> blog
	
	static List<Attachment> getAttachments(String blogid) {
		//return Attachment.search(ancestor: new Key(BlogEntry.class, blogid))
        return null
	}
	
	static void getAttachment(String key) {

	}
}
