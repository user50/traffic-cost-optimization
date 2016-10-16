define(["jquery", "app", "apps/configs/list/list_view"], function($, ConfigManager, ListView){

        return {
            listConfigs : function(){
                require(["entities/config/collection"], function(){

                    var configsListLayout = new ListView.Layout();

                    var fetchedConfigs = ConfigManager.request("config:entities");

                    $.when(fetchedConfigs).done(function(configs){

                        var configListView = new ListView.Configs({
                            collection: configs
                        });

                        configsListLayout.on("show", function(){
                            configsListLayout.configsRegion.show(configListView);
                        });

                        configListView.on("childview:config:show", function(childView, args){
                            ConfigManager.trigger("config:show", args.model.get("id"));
                        });


                        configListView.on("childview:config:edit", function(childView, args) {
                            require(["apps/configs/edit/edit_view"], function(EditView) {
                                var model = args.model;
                                var view = new EditView({
                                    model: model
                                });
                                view.on("form:submit", function (data) {
                                    if (model.save(data)) {
                                        childView.render();
                                        view.trigger("dialog:close");
                                        childView.flash("success");
                                    }
                                    else {
                                        view.triggerMethod("form:data:invalid", model.validationError);
                                    }
                                });

                                view.on("form:cancel", function () {
                                    view.trigger("dialog:close");
                                });

                                ConfigManager.regions.dialog.show(view);
                            });

                        });

                        ConfigManager.regions.main.show(configsListLayout);

                    });
                });

            }
        };
});