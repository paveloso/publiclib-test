<#import "parts/common.ftl" as c>


    <@c.page>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="/index" class="form-inline">
                <input type="text" name="filter" class="form-control" value="${filter?ifExists}" placeholder="Search by title">
                <button type="submit" class="btn btn-primary ml-2">Search</button>
            </form>
        </div>
    </div>

<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
    Add new book
</a>
<div class="collapse" id="collapseExample">
    <div class="form-group mt-3">
        <form method="post" action="index" enctype="multipart/form-data">
            <div class="form-group">
                <input type="text" class="form-control" name="title" placeholder="Title" />
            </div>
            <div class="form-group">
                <input type="text" class="form-control" name="totalPages" placeholder="Pages">
            </div>
            <div class="form-group">
                <div class="custom-file">
                    <input type="file" name="file" id="customFile">
                    <label class="custom-file-label" for="customFile">Choose file</label>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Add</button>
            </div>
        </form>
    </div>
</div>
    <div class="card-columns">
<#list books as book>
    <div class="card my-3">
        <#if book.bfilename??>
            <img src="/img/${book.bfilename}" class="card-img-top">
        </#if>
        <div class="m-2">
            <span>${book.title}</span>
            <i>${book.totalPages}</i>
        </div>
        <div class="card-footer text-muted">
            ${book.addedByWho}
        </div>
    </div>
<#else>
No book
</#list>
    </div>
</@c.page>