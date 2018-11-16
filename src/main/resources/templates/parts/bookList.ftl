<#include "security.ftl">
<div class="card-columns">
<#list books as book>
    <div class="card my-3">
        <#if book.bfilename??>
            <img src="/img/${book.bfilename}" class="card-img-top">
        </#if>
        <div class="m-2">
            <span>${book.title}</span><br/>
            <i>${book.totalPages}</i>
        </div>
        <div class="card-footer text-muted">
            <a href="/user-books/${book.addedByUser.id}">${book.addedByWho}</a>
            <#if book.addedByUser.id == currentUserId>
            <a class="btn btn-primary" href="/user-books/${book.addedByUser.id}?book=${book.id}">Edit</a>
            </#if>
        </div>
    </div>
<#else>
No book
</#list>
</div>