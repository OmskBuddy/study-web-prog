<#-- @ftlvariable name="articles" type="ru.itmo.wp.model.domain.Article[]" -->
<#-- @ftlvariable name="user" type="ru.itmo.wp.model.domain.User" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="articles datatable">
        <div class="caption">${user.login}'s articles</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Title</th>
                <th>Hidden</th>
            </tr>
            </thead>
            <tbody>
            <#list articles as article>
                <tr>
                    <td class="article-id">${article.id}</td>
                    <td class="article-title">${article.title}</td>
                    <td class="article-hidden">
                        <div class="checkbox-field">
                            <#if article.hidden>
                                <button data-article-id="${article.id}">Show</button>
                            <#else>
                                <button data-article-id="${article.id}">Hide</button>
                            </#if>

                        </div>
                    </td>
                </tr>
            </#list>
            <#if articles?size == 0>
                <tr class="noData">
                    <td colspan="3">
                        No data
                    </td>
                </tr>
            </#if>
            </tbody>
        </table>
    </div>

    <script>
        $(function () {
            $(".article-hidden button").click(function () {
                const $this = $(this)
                ajax(
                    {
                        action: "updateHidden",
                        articleId: $this.attr("data-article-id"),
                        buttonText: $this.text()
                    },
                    function (response) {}
                );

                if ($this.text() === "Show") {
                    $this.text("Hide");
                } else {
                    $this.text("Show");
                }

                return false;
            });
        })
    </script>
</@c.page>