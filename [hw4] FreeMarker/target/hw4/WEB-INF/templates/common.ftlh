<#macro page>
    <!DOCTYPE html>
    <html lang="ru">
    <head>
        <meta charset="UTF-8">
        <title>Codeforces</title>
        <link rel="stylesheet" href="/css/normalize.css">
        <link rel="stylesheet" href="/css/style.css">
        <link rel="icon" href="/favicon.ico">
    </head>
    <body>
    <@header/>
    <div class="middle">
        <@sidebar/>
        <main>
            <#nested/>
        </main>
    </div>
    <@footer/>
    </body>
    </html>
</#macro>


<#macro header>
    <header>
        <a href="#" class="logo"><img alt="Codeforces" title="Codeforces"
                                      src="/img/codeforces-logo-with-telegram.png"></a>
        <div class="languages">
            <a href="#"><img alt="In English" title="In English"
                             src="/img/gb.png"></a>
            <a href="#"><img alt="In Russian" title="In Russian"
                             src="/img/ru.png"></a>
        </div>
        <div class="enter-or-register-box">
            <#if user??>
                <@userlink user true/>
                |
                <a href="/logout">Logout</a>
            <#else>
                <a href="/enter">Enter</a>
                |
                <a href="/register">Register</a>
            </#if>
        </div>
        <nav>
            <#assign items=[
            {"href": "/index", "name": "Home"},
            {"href": "/misc/help", "name": "Help"},
            {"href": "/users", "name": "Users"}
            ]/>
            <ul>
                <#list items as item>
                    <#if currentUrl == item.href>
                        <li id="active-nav-link">
                            <a href="${item.href}">${item.name}</a>
                        </li>
                    <#else>
                        <li>
                            <a href="${item.href}">${item.name}</a>
                        </li>
                    </#if>
                </#list>
            </ul>
        </nav>
    </header>
</#macro>


<#macro sidebar_post post>
    <section>
        <div class="header">Post#${post.id}</div>
        <div class="body"><@short_post_text post/></div>
        <div class="footer">
            <a href="/post?post_id=${post.id}">View all</a>
        </div>
    </section>
</#macro>


<#macro sidebar>
    <aside>
        <#list posts?reverse as p>
            <@sidebar_post p/>
        </#list>
    </aside>
</#macro>


<#macro footer>
    <footer>
        2010-2021 &copy; by Mike Mirzayanov
    </footer>
</#macro>


<#macro userlink user nameOnly>
<#-- @ftlvariable name="user" type="ru.itmo.web.hw4.model.User" -->
    <#if nameOnly>
        <a href="/user?handle=${user.handle}">${user.handle}</a>
    <#else>
        <a href="/user?handle=${user.handle}" style="text-decoration: none; color: ${user.color}; font-weight: bold;">${user.handle}</a>
    </#if>

</#macro>


<#macro usercard user>
<#-- @ftlvariable name="user" type="ru.itmo.web.hw4.model.User" -->
<#-- @ftlvariable name="post_count" type="java.util.HashMap" -->
    <div class="user-card">
        <#assign items = ["id", "handle", "name"]/>
        <div class="person-information">
            <div class="user-title-name"><@userlink user false/></div>
            <ul>
                <#list items as item>
                    <li>
                        <p>${item}: ${user[item]}</p>
                    </li>
                </#list>
                <#assign key = "${user.id}"/>
                <li>post: <a href="/posts?user_id=${user.id}">${post_count[key]}</a></li>
            </ul>
        </div>
        <div class="person-img"><img src="img/person.jpg" alt="person-img" title="person-img"/></div>
    </div>
</#macro>


<#macro postlink post>
<#-- @ftlvariable name="post" type="ru.itmo.web.hw4.model.Post" -->
    <a href="/post?post_id=${post.id}">${post.title}</a>
</#macro>


<#macro postcard post full>
<#-- @ftlvariable name="post" type="ru.itmo.web.hw4.model.Post" -->
    <article>
        <div class="title"><@postlink post/></div>
        <div class="body">
            <#if full>
                ${post.text}
            <#else>
                <@short_post_text post/>
            </#if>
        </div>
    </article>
</#macro>


<#macro short_post_text post>
    <#if post.text?length gt 350>
        ${post.text?substring(0, 350)}...
    <#else>
        ${post.text}
    </#if>
</#macro>


<#function findBy items key value>
    <#list items as item>
        <#if item[key] == value>
            <#return item/>
        </#if>
    </#list>
    <#return 0/>
</#function>


<#function next items key value>
    <#list items as item>
        <#if item[key] == value>
            <#return items[item_index+1]/>
        </#if>
    </#list>
    <#return 0/>
</#function>


<#function prev items key value>
    <#list items as item>
        <#if item[key] == value>
            <#return items[item_index-1]/>
        </#if>
    </#list>
</#function>
