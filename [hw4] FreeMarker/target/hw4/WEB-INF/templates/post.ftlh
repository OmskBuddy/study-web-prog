<#-- @ftlvariable name="post_id" type="java.lang.Long" -->

<#import "./common.ftlh" as c>

<@c.page>
    <#if post_id??>
        <#assign p = c.findBy(posts, "id", post_id)>
        <#if p?has_content>
            <@display_postcard p/>
        <#else>
            Error: unknown post
        </#if>
    </#if>
</@c.page>

<#macro display_postcard post>
    <#attempt>
        <@c.postcard post true/>
    <#recover>
        Error: unknown post
    </#attempt>
</#macro>
