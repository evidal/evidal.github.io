package entities

import groovyx.gaelyk.datastore.Entity
import groovyx.gaelyk.datastore.Ignore
import groovyx.gaelyk.datastore.Key

import java.util.logging.Logger;

@Entity
class Tag implements Serializable {
    @Key String id
    String name
	List<String> blogs
		
	static syncBlogAndTags(def blog, def toAdd, def toRemove) {
        def log = Logger.getLogger(Tag.class.toString())

		// Add New Tags
		if(toAdd)
			toAdd.each{
                Tag tag
				try {
                    log.info "it : [${it}]"
                    tag = Tag.get(it)
					log.info " [${tag}] fetched"
				} catch(e) {
                    log.info "[${tag}] not found"
                }
				if(! tag) {
					tag = new Tag()
					tag.id = it
                    tag.name = it
				}
				if(!tag.blogs) {
                    tag.blogs = new ArrayList<String>()
                }
				if(!tag.blogs.contains(blog)) {
                    tag.blogs.add(blog)
                    log.fine "add [${blog}] to [${tag}]"
                    tag.save()
                }
			}
		
		// Remove New Tags
		if(toRemove)
			toRemove.each{
				def tag
				try {
					tag = Tag.get(it)
					log.info "[${tag}] fetched"
				} catch(e) {}
				
				if(tag) {
					tag.blogs-= blog
					log.fine "Remove [${blog}] from [${tag}]"
					if(tag.blogs.isEmpty())
						tag.delete()
					else
						tag.save()
				}
			}
	}

    @Ignore
	static List getTags() {
		return Tag.findAll()
	}
}
