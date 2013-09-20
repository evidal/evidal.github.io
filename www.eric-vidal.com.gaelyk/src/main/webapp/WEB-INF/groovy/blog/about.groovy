package blog

import entities.BlogEntry
import entities.Tag

/**
 * User: evidal
 * Date: 08/04/13
 * Time: 13:31
 */

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

forward "/WEB-INF/pages/about.gtpl"