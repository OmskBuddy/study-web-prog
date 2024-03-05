<#-- @ftlvariable name="user_id" type="java.lang.Long" -->
<#-- @ftlvariable name="handle" type="java.lang.String" -->
<#import "./common.ftlh" as c>

<@c.page>
    <#if handle??>
        <#assign u = c.findBy(users, "handle", handle)!>
        <@display_user_card u/>
    <#elseif user_id??>
        <#assign u = c.findBy(users, "id", user_id)!>
        <@display_user_card u/>
    <#else>
        No such user
    </#if>
</@c.page>

<#macro display_user_card user>
    <#attempt>
        <@c.usercard user/>
        <@arrows user/>
    <#recover>
        Error: unknown user
    </#attempt>
</#macro>

<#macro arrows user>
    <#assign next=c.next(users, "handle", user.handle)!/>
    <#assign prev=c.prev(users, "handle", user.handle)!/>

    <div class="arrows">
        <div class="left">
            <#if prev?has_content>
                <a href="/user?handle=${prev.handle}">←</a>
            <#else>
                <span>←</span>
            </#if>
        </div>
        <div class="right">
            <#if next?has_content>
                <a href="/user?handle=${next.handle}">→</a>
            <#else>
                <span>→</span>
            </#if>
        </div>
    </div>
</#macro>
