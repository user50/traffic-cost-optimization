<td class="col-md-1">
    <a href="#configs/<%= id %>/edit" class="btn btn-default btn-sm js-edit">
        <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
    </a>
</td>
<td class="col-md-1"><% if (autoOptimization) { %>
        <input type="checkbox" checked>
    <% } else { %>
        <input type="checkbox">
    <% } %>
</td>
<td class="col-md-9"><%- campaingId %></td>
<td class="col-md-1"> todo </td>

