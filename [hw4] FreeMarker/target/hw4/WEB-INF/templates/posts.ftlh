<#-- @ftlvariable name="user_id" type="java.lang.Long" -->

<#import "./common.ftlh" as c>

<@c.page>
    <#if user_id??>
        <#list posts as p>
            <#if user_id == p.user_id>
                <@c.postcard p false/>
            </#if>
        </#list>
    <#else>
        No such posts
    </#if>
</@c.page>
