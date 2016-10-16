define(["jquery", "backbone", "app"],
function($, Backbone, ConfigManager, ReplacesModel){

    var Config = Backbone.Model.extend({
        urlRoot: "configs",

        validate: function(attrs, options){
            var errors = {};

            //if (! attrs.name){
            //    errors.name = "Укажите название"
            //}

            if (!_.isEmpty(errors))
                return errors;
        }
    });

    var API = {
        getConfigEntity: function(id){
            var config = new Config({id: id});
            var defer = $.Deferred();

            config.fetch({
                success: function(data){
                    defer.resolve(data);
                },
                error: function(data){
                    defer.resolve(undefined);
                }
            });

            return defer.promise();
        }
    };

    ConfigManager.reqres.setHandler("config:entity", function(id){
        return API.getConfigEntity(id);
    });

    ConfigManager.reqres.setHandler("config:entity:new", function(){
        return new Config();
    });

    return Config;
});