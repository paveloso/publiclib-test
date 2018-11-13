<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

    <@c.page>
<div>
    <@l.logout />
    <span><a href="/user">User list</a></span>
</div>
<div>
    <form method="post" action="index" enctype="multipart/form-data">
        <input type="text" name="title" placeholder="Title" />
        <input type="text" name="totalPages" placeholder="Pages">
        <input type="file" name="file">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit">Add</button>
    </form>
</div>
<div>List of Books</div>
<form method="get" action="/index">
    <input type="text" name="filter" value="${filter?ifExists}">
    <button type="submit">Find</button>
</form>
<#list books as book>
<div>
    <b>${book.id}</b>
    <span>${book.title}</span>
    <i>${book.totalPages}</i>
    <strong>${book.addedByWho}</strong>
    <div>
        <#if book.bfilename??>
            <img src="/img/${book.bfilename}">
        </#if>
    </div>
</div>
<#else>
No book
</#list>
</@c.page>