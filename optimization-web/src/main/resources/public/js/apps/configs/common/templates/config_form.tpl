<form class="form-horizontal">

    <div id="test-region"></div>

    <% if (autoOptimization) { %>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-6">
            <div class="checkbox">
                <label>
                    <input type="checkbox" name="autoOptimization" checked> Автооптимизация
                </label>
            </div>
        </div>
    </div>
    <% } else { %>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-6">
            <div class="checkbox">
                <label>
                    <input type="checkbox" name="autoOptimization"> Автооптимизация
                </label>
            </div>
        </div>
    </div> <% } %>

    <div class="form-group">
        <label for="config-campaignName" class="col-sm-2 control-label">
            Название:</label>
        <div class="col-sm-6">
            <input id="config-campaignName" name="campaignName" class="form-control"
                   type="text" value="<%= campaignName %>" disabled/>
        </div>
    </div>

    <div class="form-group">
        <label for="config-maxRedirects" class="col-sm-2 control-label">
            Макс. редиректы:</label>
        <div class="col-sm-6">
            <input id="config-maxRedirects" name="maxRedirects" class="form-control"
                   type="text" value="<%= maxRedirects %>"/>
        </div>
    </div>

    <div class="form-group">
        <label for="config-maxTestRedirect" class="col-sm-2 control-label">
            Макс. тест. редиректы:</label>
        <div class="col-sm-6">
            <input id="config-maxTestRedirect" name="maxTestRedirect" class="form-control"
                   type="text" value="<%= maxTestRedirect %>"/>
        </div>
    </div>

    <div class="form-group">
        <label for="config-percentage" class="col-sm-2 control-label">
            % для вычитания:</label>
        <div class="col-sm-6">
            <input id="config-percentage" name="percentage" class="form-control"
                   type="text" value="<%= percentage %>"/>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-2 control-label">
            Тест кампания:</label>
        <div class="col-sm-6">
            <select class="form-control">
                <option value="">Нет тестовой кампании</option>
                <% _.each(testCandidates, function(item){ %>
                <option value="<%= item.get('campaignId')%>"><%= item.get('campaignName') %></option>
                <% }); %>
            </select>

        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-10">
            <button class="btn btn-default js-submit">Сохранить</button>
            <button class="btn btn-default js-cancel">Отмена</button>
        </div>
    </div>

</form>
