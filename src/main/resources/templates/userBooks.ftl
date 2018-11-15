<#import "parts/common.ftl" as c>

<@c.page>
<#if isCurrentUser>
<#include "parts/bookEdit.ftl" />
</#if>

<#include "parts/bookList.ftl" />

</@c.page>