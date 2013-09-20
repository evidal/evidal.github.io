<% include '/WEB-INF/includes/header.gtpl' %>

<div class="row">
    <div class="span9">
        <%  request.blogentries.each{ blog -> %>
        <div>
            <h1><a href="/article/${blog.encodedId}">${blog.title}</a></h1>
            <strong>Le ${blog.datePublication.format('dd/MM/yyyy')}</strong>
            <div>
            <% blog.tags.each{tag ->%>
                <a href="/tag/${tag}">${tag}</a>
            <% } %>
            </div>
            <% if (user && users.userAdmin) { %>
            <div>
                <a href="/edit/${blog.encodedId}" id="edit" class="btn">Edit</a>
                <a href="/delete/${blog.encodedId}" id="delete" class="btn">Delete</a>
            </div>
            <% } %>
            <p>${blog.formattedShortContent}</p>
            <%if (blog.readMore) { %>
            <a href="/article/${blog.encodedId}">Lire la suite...</a>
            <% } %>
            <div>
                <h4>Comments</h4>
                <a href="/article/${blog.encodedId}#disqus_thread ">Chargement des commentaires</a>
            </div>
        </div>
        <hr/>
        <% } %>
        <%  if(request.blogentries == null || request.blogentries.size() == 0) { %>
        <div>
        Pas d'articles.
        </div>
        <% } %>

        <% def page = request.page %>
        <div class="pager">
            <ul>
            <% if((request.blogentries != null) && (request.blogentries.size() >= 10)) { %>
                <li><a href="${params.tag ? '/tag/'+params.tag+'/'+((page as int) + 1) : '/blog/page/'+((page as int) + 1)}">Un peu avant</a></li>
            <% } %>
            <%if(page > 1) { %>
                <li><a href="${params.tag ? '/tag/'+params.tag+'/'+((page as int) - 1) : '/blog/page/'+((page as int) - 1)}">Un peu apr&egrave;s</a></li>
            <% } %>
            </ul>
        </div>
    </div>

    <% include '/WEB-INF/pages/aside.gtpl' %>
</div>
<script type="text/javascript">
    var disqus_shortname = "ericvidalblog";

    (function () {
        var s = document.createElement('script'); s.async = true;
        s.type = 'text/javascript';
        s.src = 'http://' + disqus_shortname + '.disqus.com/count.js';
        (document.getElementsByTagName('HEAD')[0] || document.getElementsByTagName('BODY')[0]).appendChild(s);
    }());
</script>
<% include '/WEB-INF/includes/footer.gtpl' %>

