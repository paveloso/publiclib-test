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

    <#include "parts/bookEdit.ftl" />

    <#include "parts/bookList.ftl" />


</@c.page>