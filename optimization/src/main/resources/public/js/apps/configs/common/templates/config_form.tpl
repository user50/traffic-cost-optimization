<form class="form-horizontal">

    <div id="test-region"></div>

    <div class="form-group">
        <label for="config-campaingId" class="col-sm-2 control-label">
            Название:</label>
        <div class="col-sm-6">
            <input id="config-campaingId" name="campaingId" class="form-control"
                   type="text" value="<%= campaingId %>"/>
        </div>
    </div>

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
        <div class="col-sm-10">
            <button class="btn btn-default js-submit">Сохранить</button>
            <button class="btn btn-default js-cancel">Отмена</button>
        </div>
    </div>

</form>
