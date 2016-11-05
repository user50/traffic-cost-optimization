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
<td class="col-md-1">
    <%
    if (lastOptimization == 0 ){
    print ('Отсувствует');
    }
    else {
    var today = new Date(lastOptimization);
    var hh = today.getHours();
    var MM = today.getMinutes();
    var dd = today.getDate();
    var mm = today.getMonth()+1; //January is 0!
    var yyyy = today.getFullYear();

    if(dd<10) {
    dd='0'+dd
    }

    if(mm<10) {
    mm='0'+mm
    }

    if(MM<10) {
    MM='0'+MM
    }

    today = dd+'/'+mm+'/'+yyyy + " " + hh + ":" + MM;

    print (today);
    }
    %>
</td>

