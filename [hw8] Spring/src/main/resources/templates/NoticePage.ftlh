<#import "commons.ftlh" as c>

<@c.page>
    <div class="notice form-box">
        <div class="header">Add notice</div>
        <div class="body">
            <form method="post" action="">
                <div class="field">
                    <div class="name">
                        <label for="content">Notice</label>
                    </div>
                    <div class="value">
                        <textarea id="content" name="content" rows="10" style="resize: none; white-space: pre-wrap">${noticeContent.content!}</textarea>
                    </div>
                    <@c.error "noticeContent.content"/>
                </div>
                <div class="error"></div>
                <div class="button-field">
                    <input type="submit" value="Add">
                </div>
            </form>
        </div>
    </div>
</@c.page>
