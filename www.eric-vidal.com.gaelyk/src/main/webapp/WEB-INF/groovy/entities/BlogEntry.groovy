package entities

import com.google.appengine.api.datastore.Text
import groovyx.gaelyk.datastore.Entity
import groovyx.gaelyk.datastore.Ignore
import groovyx.gaelyk.datastore.Key
import org.apache.commons.codec.net.URLCodec
import ys.wikiparser.WikiParser

@Entity
class BlogEntry implements Serializable {
    // id format yyyy/MM/dd/title
    @Key String id
    String blogId // required for search by tag
    String title
    Text content
    List<String> tags
	Date datePublication
	Boolean published

    @Ignore
	public String getFormattedContent() {
		return WikiParser.renderXHTML(this.content.getValue())
	}

    @Ignore
    public String getFormattedShortContent() {
        def formattedContent = getFormattedContent()
        def indexBreak = formattedContent.indexOf('<hr/>') -1
        if( indexBreak != -2) {
            formattedContent = formattedContent[0..indexBreak]
        }
        return formattedContent
    }

    @Ignore
    public boolean getReadMore(){
        def formattedContent = getFormattedContent()
        def indexBreak = formattedContent.indexOf('<hr/>') -1
        if( indexBreak != -2) {
            return true
        }
        return false
    }

    @Ignore
    public String getEncodedId() {
        def slashIdx = id.lastIndexOf("/")
        id.substring(slashIdx)
        URLCodec c = new URLCodec()
        return id.substring(0,slashIdx+1)+c.encode(id.substring(slashIdx+1))
    }

    String generateId() {
        id = datePublication.format("yyyy/MM/dd")+"/"+title
        return id;
    }

    static String generateId(int year, int month, int day, String title) {
        return String.format("%04d/%02d/%02d/%s", year, month, day, title);
    }

    static String generateId(String year, String month, String day, String title) {
        return String.format("%s/%s/%s/%s", year, month, day, title);
    }

    String toString() {
        return "["+id+"] : ["+content.getValue()+"]"
    }
}