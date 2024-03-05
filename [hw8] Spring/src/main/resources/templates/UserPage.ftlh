<#import "commons.ftlh" as c>

<@c.page>
    <#if user??>
        <h1>User Profile</h1>
        <h2>ID: ${user.id}</h2>
        <h2>Login: ${user.login}</h2>
        <h2>Creation time: ${user.creationTime}</h2>
    <#else>
        Error with user data!
    </#if>
</@c.page>
