get "/", redirect: "/blog"
get "/blog", forward: "/blog/blogController.groovy?action=list"
get "/blog/", forward: "/blog/blogController.groovy?action=list"
get "/blog/page/@page", forward: "/blog/blogController.groovy?action=list&page=@page"
get "/article/@year/@month/@day/@title", forward: "/blog/blogController.groovy?action=view&year=@year&month=@month&day=@day&title=@title"
get "/tag/@tag", forward: "/blog/blogController.groovy?action=list&tag=@tag"
get "/tag/@tag/@page", forward: "/blog/blogController.groovy?action=list&tag=@tag&page=@page"
get "/edit/@year/@month/@day/@title", forward: "/blog/blogController.groovy?action=edit&year=@year&month=@month&day=@day&title=@title"
get "/edit", forward: "/blog/blogController.groovy?action=edit"
get "/blog/archives", forward: "/blog/blogController.groovy?action=archives"
post "/blog/save", forward: "/blog/blogController.groovy?action=save"
get "/delete/@year/@month/@day/@title", forward: "/blog/blogController.groovy?action=delete&year=@year&month=@month&day=@day&title=@title"


get "/blog/get/@filename", redirect: "/blog/attachments/get.groovy?filename=@filename"
get "/blog/attachments/@year/@month/@day/@title", forward: "/blog/blogController.groovy?action=attachments&year=@year&month=@month&day=@day&title=@title"

get "/blog/admin", forward: "/blog/admin.groovy"
get "/blog/clear", forward: "/blog/clear.groovy"

get "/blog/auteur", forward: "/blog/about.groovy"

get "/blog/eric-vidal.rss", forward: "/blog/rss.groovy"

get "/favicon.ico", redirect: "/images/small_lightbulb.png" 	