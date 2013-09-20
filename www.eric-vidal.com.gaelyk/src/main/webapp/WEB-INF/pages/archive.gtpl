<% include '/WEB-INF/includes/header.gtpl' %>
<div class="row">
    <div class="span9">
        <%  request.blogentries.each{ blog -> %>
            <div><strong>${blog.datePublication.format('dd/MM/yyyy')}</strong> <a href="/article/${blog.id}">${blog.title}</a></div>
        <%}%>
    </div>
    <% include '/WEB-INF/pages/aside.gtpl' %>
</div>

<% include '/WEB-INF/includes/footer.gtpl' %>