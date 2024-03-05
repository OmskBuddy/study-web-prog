<#-- @ftlvariable name="user" type="ru.itmo.wp.domain.User" -->
<#-- @ftlvariable name="message" type="java.lang.String" -->
<#import "/spring.ftl" as spring/>
<#macro page>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Codeforces</title>
    <link rel="icon" type="image/png" href="<@spring.url '/favicon.png'/>" />
    <link rel="stylesheet" type="text/css" href="<@spring.url '/css/normalize.css'/>">
    <link rel="stylesheet" type="text/css" href="<@spring.url '/css/style.css'/>">
    <link rel="stylesheet" type="text/css" href="<@spring.url '/css/form.css'/>">
    <link rel="stylesheet" type="text/css" href="<@spring.url '/css/datatable.css'/>">
    <link rel="stylesheet" type="text/css" href="<@spring.url '/css/article.css'/>">
    <script src="<@spring.url '/js/jquery-3.4.1.min.js'/>"></script>
    <script src="<@spring.url '/js/notify.js'/>"></script>
</head>
<body>
<header>
    <a href="<@spring.url '/'/>"><img src="<@spring.url '/img/logo.png'/>" alt="Codeforces" title="Codeforces"/></a>
    <div class="languages">
        <a href="#"><img src="<@spring.url '/img/gb.png'/>" alt="In English" title="In English"/></a>
        <a href="#"><img src="<@spring.url '/img/ru.png'/>" alt="In Russian" title="In Russian"/></a>
    </div>
    <div class="enter-or-register-box">
        <#if user??>
            ${user.login}
            |
            <a href="<@spring.url '/logout'/>">logout</a>
        <#else>
            <a href="<@spring.url '/enter'/>">enter</a>
            |
            <a href="<@spring.url '/register'/>">register</a>
        </#if>
    </div>
    <nav>
        <ul>
            <li><a href="<@spring.url '/'/>">Domina</a></li>
            <li><a href="<@spring.url '/users/all'/>">Russ'es</a></li>
            <#if user??>
                <li><a href="<@spring.url '/writePost'/>">Write Post</a></li>
                <li><a href="<@spring.url '/myPosts'/>">Posts</a></li>
            </#if>
        </ul>
    </nav>
</header>
<div class="middle">
    <aside>
        <section>
            <div class="header">
                Posmotri
            </div>
            <div class="body">
                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Cupiditate ducimus enim facere impedit nobis,
                nulla placeat quam suscipit unde voluptatibus.
            </div>
            <div class="footer">
                <a href="#">Rassmotret'</a>
            </div>
        </section>
    </aside>
    <main>
        <#nested/>
    </main>
</div>
<footer>
    <a href="<@spring.url '/'/>">Ancient Russ'es</a> &copy; 862 by Niketas
</footer>
</body>
<script>
    <#--noinspection JSUnresolvedFunction-->
    $(function () {
        <#if message??>
            $.notify("${message?js_string}", {className: "success", position: "bottom right"});
        </#if>
    })
</script>
</html>
</#macro>

<#macro error field>
    <@spring.bind field/>
    <#if spring.status.errorMessage??>
        <div class="error">${spring.status.errorMessage}</div>
    </#if>
</#macro>

<#macro tagcard tag>
<#-- @ftlvariable name="tag" type="ru.itmo.wp.domain.Tag" -->
    <div style="margin-right: 1rem; border: 2px solid black; border-radius: 6px; padding: 0.1rem">
        <a href="#" style="text-decoration: none; color: #3B5998; font-size: 14px">${tag.name}</a>
    </div>
</#macro>

<#macro post post>
<#-- @ftlvariable name="post" type="ru.itmo.wp.domain.Post" -->
    <article>
        <div class="title">
            <a href="/post/${post.id}" style="text-decoration: none; color: inherit; white-space: pre-wrap">${post.title}</a>
        </div>
        <div class="information">By ${post.user.login}, ${post.creationTime}</div>
        <div class="body"><@article post.text/></div>
        <#if post.tags?size!= 0>
            <ul class="tags" style="display: flex; list-style-type: none; padding: 0;">
                <#list post.tags as tag>
                    <li>
                        <@tagcard tag=tag/>
                    </li>
                </#list>
            </ul>
        </#if>
        <ul class="attachment">
            <li>Announcement of <a href="#">Codeforces Round #510 (Div. 1)</a></li>
            <li>Announcement of <a href="#">Codeforces Round #510 (Div. 2)</a></li>
        </ul>
        <div class="footer">
            <div class="left">
                <img src="<@spring.url '/img/voteup.png'/>" title="Vote Up" alt="Vote Up"/>
                <span class="positive-score">+173</span>
                <img src="<@spring.url '/img/votedown.png'/>" title="Vote Down" alt="Vote Down"/>
            </div>
            <div class="right">
                <img src="<@spring.url '/img/date_16x16.png'/>" title="Publish Time" alt="Publish Time"/>
                ${post.creationTime}
                <img src="<@spring.url '/img/comments_16x16.png'/>" title="Comments" alt="Comments"/>
                <a href="/post/${post.id}">${post.comments?size}</a>
            </div>
        </div>
    </article>
</#macro>

<#macro comment comment>
    <div class="comment" style="margin-top: 1rem">
        <table class="comment-table">
            <tbody><tr>
                <td class="left" style="text-align: center; width: 16rem;">
                    <div class="avatar">
                        <a href="#" style="position: relative;">
                            <img src="/img/redruss.png" height="64" width="64">
                        </a>
                        <div>
                            <a href="#" style="text-decoration: none; color: inherit; font-weight: bold">
                                ${comment.user.login}
                            </a>
                        </div>
                    </div>
                </td>
                <td class="right">
                    <div class="comment-content">
                        <div class="comment-text">
                            <@article comment.text/>
                        </div>
                    </div>
                </td>
            </tr></tbody>
        </table>
    </div>
</#macro>

<#macro article text>
    <#list text?split("\n") as line>
        <a style="white-space: pre-wrap">${line}</a>
        <br>
    </#list>
</#macro>
