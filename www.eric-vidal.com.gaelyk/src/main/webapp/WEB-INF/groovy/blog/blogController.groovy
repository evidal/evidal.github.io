package blog

import com.google.appengine.api.datastore.Text
import entities.BlogEntry
import entities.Tag

def action = params.action
log.info(action)

switch (action) {
    case "view":
        if (params.year != null &&
                params.month != null &&
                params.day != null &&
                params.title != null) {

            log.info("load blog : ${params.year}/${params.month}/${params.day}/${params.title} ")
            request.blog = BlogEntry.get(BlogEntry.generateId(params.year, params.month, params.day, params.title))
        }

        // 10 Last BLogs
        request.tenLastBlogs = datastore.execute {
            select all from "BlogEntry" as BlogEntry
            where published == true
            sort desc by datePublication
            limit 10
        }

        // Tags
        request.tags = datastore.execute {
            select all from "Tag" as Tag
        }

        forward "/WEB-INF/pages/viewblog.gtpl"
        break

    case "edit":
        log.fine("Edit Blog")
        if(users.isUserAdmin() ) {
            if (params.year != null &&
                    params.month != null &&
                    params.day != null &&
                    params.title != null) {

                log.info("load blog : [${params.year}/${params.month}/${params.day}/${params.title}] ")
                BlogEntry.findAll().each{
                    // TO REMOVE
                    log.info("blog title: [${it.id}] ")
                }
                request.blog = BlogEntry.get(BlogEntry.generateId(params.year, params.month, params.day, params.title))
            }

            forward "/WEB-INF/pages/editblog.gtpl"
        }
        break

    case "save":
        log.fine("Update Blog")
        if(users.isUserAdmin() ) {
            log.fine "Parameters : "+params.toString()

            // Cast date Publication as date
            params.datePublication = Date.parse("dd/MM/yyyy",params.datePublication )
            log.fine "params.datePublication : "+params.datePublication

            // Populating Blog
            BlogEntry blog = new BlogEntry()
            blog.title = params.title
            blog.content = params.content as Text
            blog.datePublication = params.datePublication
            blog.published = params.published
            def oldTags = []

            if(params.id != null && params.id.length() > 0) {
                blog.setId params.id
                def b = BlogEntry.get(params.id)
                oldTags = b.tags != null ? b.tags : []
            } else {
                blog.setId blog.generateId()
            }
            blog.blogId = blog.id
            log.fine "blog.id : "+blog.id

            // Tags Management
            log.info("########################")
            log.info("oldTags : "+oldTags)
            blog.tags = params.tags.length() > 0 ?  params.tags?.replaceAll(/ +/,' ').toLowerCase().split(",").collect{it.trim()} : []
            log.info("blog.tags : "+blog.tags)
            def toAdd = blog.tags ? blog.tags : []
            log.info("toAdd : "+toAdd)
            def toRemove = oldTags - toAdd
            log.info("toRemove : "+toRemove)

            // Store Blog
            blog.save()
            log.info "Blog Stored : "+blog.toString()

            // Store Tags
            Tag.syncBlogAndTags(blog.id, toAdd, toRemove)
            log.info "TAGS Stored"
        }
        redirect "/"
        break

    case "delete":
        log.fine("Delete Blog")
        if(users.isUserAdmin() ) {
            log.info("delete blog : ["+BlogEntry.generateId(params.year, params.month, params.day, params.title)+"] ")
            BlogEntry.findAll().each{
                // TO REMOVE
                log.info("blog title: [${it.id}] ")
            }
            BlogEntry.delete(BlogEntry.generateId(params.year, params.month, params.day, params.title))
        }
        redirect "/"
        break

    case "archives":
        log.fine("Archives Blog")
        request.blogentries = datastore.execute {
            select all  from "BlogEntry"
            sort desc by datePublication
        }

        // 10 Last BLogs
        request.tenLastBlogs = datastore.execute {
            select all from "BlogEntry" as BlogEntry
            where published == true
            sort desc by datePublication
            limit 10
        }

        // Tags
        request.tags = datastore.execute {
            select all from "Tag" as Tag
        }

        forward "/WEB-INF/pages/archive.gtpl"
        break

    case "list":
    default:
        log.fine("List Blogs")
        def page = params.page ? params.page : 0
        page=(page as int) - 1
        page = page >= 0 ? page : 0

        def tag = params.tag
        if(tag) {
            Tag t = Tag.get(tag)

            log.info("TAG : "+t.blogs)
            log.info("blogssss : "+datastore.execute {
                select all from "BlogEntry"
                where tags in ["conf"]
            })

            if(t) {
                if (!user || !users.isUserAdmin()) {
                    request.blogentries = datastore.execute {
                        select all from "BlogEntry" as BlogEntry
                        where blogId in t.blogs
                        and published == true
                        sort desc by datePublication
                        limit 10
                        offset page*10
                    }
                } else {
                    request.blogentries = datastore.execute {
                        select all from "BlogEntry" as BlogEntry
                        where blogId in t.blogs
                        sort desc by datePublication
                        limit 10
                        offset page*10
                    }
                }
            }
        } else {
            if (!user || !users.isUserAdmin()) {
                request.blogentries = datastore.execute {
                    select all from "BlogEntry" as BlogEntry
                    where published == true
                    sort desc by datePublication
                    limit 10
                    offset page*10
                }
            } else {
                request.blogentries = datastore.execute {
                    select all from "BlogEntry" as BlogEntry
                    sort desc by datePublication
                    limit 10
                    offset page*10
                }
            }
        }
        request.page = page + 1

        // 10 Last BLogs
        request.tenLastBlogs = datastore.execute {
            select all from "BlogEntry" as BlogEntry
            where published == true
            sort desc by datePublication
            limit 10
        }

        // Tags
        request.tags = datastore.execute {
            select all from "Tag" as Tag
        }

        forward "/WEB-INF/pages/blog.gtpl"
}
