<% include '/WEB-INF/includes/header.gtpl' %>
<div class="row">
    <div class="span9">
		<article>
			<header>
				<h3><a href="/article/${request.blog.encodedId}">${request.blog.title}</a></h3>
				Le ${request.blog.datePublication.format('dd/MM/yyyy')},  Tags : <% request.blog.tags.each{tag ->%>
				<a href="/tag/${tag}">${tag}</a>
				<% } %>
				<% if (user && users.userAdmin) { %>
                    <div>
                        <a href="/edit/${request.blog.encodedId}" id="edit" class="btn">Edit</a>
                        <a href="/delete/${request.blog.encodedId}" id="delete" class="btn">Delete</a>
                    </div>
				<% } %>
			</header>
			
			<p>${request.blog.formattedContent}</p>
		</article>

        <div id="disqus_thread"></div>
        <script type="text/javascript">
            var disqus_shortname = "ericvidalblog";
            (function() {
                var dsq = document.createElement('script'); dsq.type = 'text/javascript'; dsq.async = true;
                dsq.src = '//' + disqus_shortname + '.disqus.com/embed.js';
                (document.getElementsByTagName('head')[0] || document.getElementsByTagName('body')[0]).appendChild(dsq);
            })();
        </script>
        <noscript>Please enable JavaScript to view the <a href="http://disqus.com/?ref_noscript">comments powered by Disqus.</a></noscript>
        <a href="http://disqus.com" class="dsq-brlink">comments powered by <span class="logo-disqus">Disqus</span></a>
    </div>

    <% include '/WEB-INF/pages/aside.gtpl' %>
</div>

<% include '/WEB-INF/includes/footer.gtpl' %>