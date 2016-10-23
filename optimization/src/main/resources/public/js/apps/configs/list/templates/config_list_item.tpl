<td class="col-md-1">
    <a href="#configs/<%= id %>/edit" class="btn btn-default btn-sm js-edit">
        <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
    </a>
</td>
<td class="col-md-1"><% if (autoOptimization) { %>
        <input type="checkbox" checked disabled>
    <% } else { %>
        <input type="checkbox" disabled>
    <% } %>
</td>
<td class="col-md-4"><%= campaignName %></td>
<td class="col-md-1"><%= maxRedirects %></td>
<td class="col-md-1"><%= maxTestRedirect %></td>
<td class="col-md-1"><%= percentage %></td>
<td class="col-md-2"><%= testCampaignName %></td>
<td class="col-md-1">  </td>

