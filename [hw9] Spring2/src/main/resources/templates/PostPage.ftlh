<#-- @ftlvariable name="post" type="ru.itmo.wp.domain.Post" -->
<#import "commons.ftlh" as c>

<@c.page>
    <@c.post post=post/>

    <div class="form" id="comment-form">
        <div class="header">Write Comment</div>
        <div class="body">
            <form method="post" action="">
                <input type="hidden" name="action" value="writeComment"/>
                <div class="field">
                    <div class="value">
                        <textarea id="text" name="text">${comment.text!}</textarea>
                    </div>
                    <@c.error "comment.text"/>
                </div>
                <div class="error"></div>
                <div class="button-field">
                    <input type="submit" value="Comment">
                </div>
            </form>
        </div>
    </div>

    <div class="form" style="margin-top: 2rem">
        <div class="header">Comments (${post.comments?size})</div>
        <#list post.comments as cmt>
            <@c.comment comment=cmt/>
        </#list>
    </div>
</@c.page>

