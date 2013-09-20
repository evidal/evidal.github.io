<% include '/WEB-INF/includes/header.gtpl' %>
<link rel="stylesheet" type="text/css" href="/css/datepicker.css"/>
<script type="text/javascript" src="/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="/js/messages_fr.js"></script>

<div class="row">
    <div class="span8">
        <form method="POST" action="/blog/save" id="blogForm">
            <input type="hidden" name="id" value="${request.blog ? request.blog.id :''}">

            <fieldset>
                <label for="title" class="blogform">Title</label>
                <input type="text" name="title" id="f_title" value="${request.blog ? request.blog.title :''}" autofocus class="required">
            </fieldset>

            <fieldset>
                <label for="content" class="blogform">Content</label>
                <textarea rows="15" name="content" id="f_content" class="required">${request.blog ? request.blog.content.value : ""}</textarea>
            </fieldset>


            <a id="previewbtn" class="btn">Preview</a>
            <%  if(request.blog) {%>
                <a href="/blog/attachments/${request.blog.encodedId}" id="attachments" class="btn">Attachments</a>
            <%  } %>
            <br><br>
            <fieldset>
                <label for="tags" class="blogform">TAGS</label>
                <%
                    def tags = ""
                    if(request.blog) request.blog.tags.each {
                       tags+= it+", "
                    }
                    if(tags.length() > 0)
                        tags = tags.substring(0,tags.lastIndexOf(","))
                %>
                <input type="text" name="tags" id="f_tags" value="${tags}" autofocus>
            </fieldset>


            <fieldset>
                <label for="date" class="blogform">Date</label>
                <input type="text" name="datePublication" id="f_date" value="${request.blog ? request.blog.datePublication.format('dd/MM/yyyy') : ''}" class="required frenchDate">
            </fieldset>

            <% def published = request.blog ? request.blog.published : true %>
            <fieldset>
                <label for="published" class="blogform">Publication</label>
                <input type="checkbox" name="published" id="f_published" ${published ? "checked": ""} >
            </fieldset>

            <input type="submit" name="save" value="Save" class="btn">
        </form>
    </div>
    <div class="span4">
        <img src="/images/creole_cheat_sheet.png">
    </div>
</div>

<div id="preview" class="modal hide fade">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h3>Preview</h3>
    </div>
    <div class="modal-body" id="previewBody">
    </div>
    <div class="modal-footer">
        <a href="#" class="btn" class="close" data-dismiss="modal" aria-hidden="true">Close</a>
    </div>
</div>

<script type="text/javascript">
    \$("#f_date").datepicker({ format: 'dd/mm/yyyy' });

    \$("#previewbtn").click(function() {
        var content = \$("#f_content").val();
         \$.post( "/blog/utils/formatContent.groovy", { content: content }, function( data ) {
         \$( "#previewBody" ).empty().append( data );
         });
		\$("#preview" ).modal("show");
    });

    \$.validator.addMethod(
        "frenchDate",
        function(value, element) {
            return value.match(/^[0-3]?[0-9]\\/[01]?[0-9]\\/[12][90][0-9][0-9]\$/);
        },
        "Please enter a date in the format dd/mm/yyyy"
    );

    \$(document).ready(
    function(){
        \$("#blogForm").validate();
    });
</script>
<% include '/WEB-INF/includes/footer.gtpl' %>