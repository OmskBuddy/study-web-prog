<#import "commons.ftlh" as c>

<@c.page>
    <div class="articles">
        <ul class="articles-list" style="list-style-type: none">
            <template>
                <li>
                    <article class="postcard">
                        <div class="article-title"></div>
                        <div class="information">By <a class="user-name"></a>, <a class="article-time"></a>, translation</div>
                        <div class="article-text" style="white-space: pre-wrap"></div>
                        <ul class="attachment"></ul>
                        <div class="footer">
                            <div class="left">
                                <img src="img/voteup.png" title="Vote Up" alt="Vote Up"/>
                                <span class="positive-score">+173</span>
                                <img src="img/votedown.png" title="Vote Down" alt="Vote Down"/>
                            </div>
                            <div class="right">
                                <img src="img/date_16x16.png" title="Publish Time" alt="Publish Time"/>
                                <a class="article-time"></a>
                            </div>
                        </div>
                    </article>
                </li>
            </template>
            <li class="noData">
                <article>
                    No data
                </article>
            </li>
        </ul>
    </div>

    <script>
        $(function () {
            const $noData = $(".articles .noData");
            $noData.find("article").text("Loading...");

            ajax(
                { action: "findAll" },
                function(response) {
                    response["articles"].forEach(function (article) {
                        $noData.hide();
                        const $li = $($(".articles template").prop("content")).clone();
                        $li.find(".article-title").text(article["article"]["title"]);
                        $li.find(".article-text").text(article["article"]["text"]);
                        $li.find(".user-name").text(article["user"]["login"]);
                        $li.find(".article-time").text(article["article"]["creationTime"]);
                        if (!article["article"]["hidden"]) {
                            $(".articles .articles-list").append($li);
                        }
                    });
                }
            );
        })
    </script>
</@c.page>
