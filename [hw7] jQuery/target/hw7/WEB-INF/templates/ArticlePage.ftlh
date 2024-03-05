<#import "commons.ftlh" as c>

<@c.page>

    <div class="article form-box">
        <div class="header">Write Article</div>
        <div class="body">
            <form method="post" action="makeArticle">
                <input type="hidden" name="action" value="article"/>
                <div class="field">
                    <div class="name">
                        <label for="title">Title</label>
                    </div>
                    <div class="value">
                        <input id="title" name="title" value="${title!}"/>
                    </div>
                </div>
                <div class="field">
                    <div class="name">
                        <label for="text">Text</label>
                    </div>
                    <div class="value">
                        <textarea id="text" name="text" rows="10" style="white-space: pre-wrap;">${text!}</textarea>
                    </div>
                </div>
                <div class="checkbox-field">
                    <input type="checkbox" id="hidden" name="hidden">
                    <label for="hidden" style="padding-left: 0.2rem">Hide article</label>
                </div>
                <div class="error"></div>
                <div class="button-field">
                    <input type="submit" value="Create">
                </div>
            </form>
        </div>
    </div>

    <script>
        $(function () {


            $(".article form").submit(function () {
                const title = $(this).find("input[name='title']").val();
                const text = $(this).find("textarea[name='text']").val();
                const hidden = $(this).find("input[name='hidden']").prop("checked");
                const $error = $(this).find(".error");

                ajax(
                    { action: "makeArticle", title, text, hidden },
                    function (response) {
                        if (response["error"]) {
                            $error.text(response["error"]);
                        }
                    }
                );

                return false;
            });
        })
    </script>

</@c.page>