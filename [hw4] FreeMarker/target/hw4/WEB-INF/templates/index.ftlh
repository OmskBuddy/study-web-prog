<#import "./common.ftlh" as c>

<@c.page>
    <#list posts?reverse as p>
        <@c.postcard p false/>
    </#list>
</@c.page>
