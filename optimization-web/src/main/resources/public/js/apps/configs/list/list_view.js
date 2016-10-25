define(["jquery", "marionette", "app",
        "tpl!apps/configs/list/templates/config_list.tpl",
        "tpl!apps/configs/list/templates/config_list_item.tpl",
        "tpl!apps/configs/list/templates/config_list_layout.tpl"],
function($, Marionette, ConfigManager, listTpl, listItemTpl, listLayoutTpl, listPanelTpl){

        var Config = Marionette.ItemView.extend({
            tagName: "tr",
            template: listItemTpl,

            triggers: {
                "click a.js-edit" : "config:edit",
                "change input" : "config:show"
            },

            flash: function(cssClass){
                var $view = this.$el;
                $view.hide().toggleClass(cssClass).fadeIn(800, function(){
                    setTimeout(function(){
                        $view.toggleClass(cssClass)
                    }, 500);
                });
            },

            remove: function(){
                var self = this;
                this.$el.fadeOut(function(){
                    Marionette.ItemView.prototype.remove.call(self);
                });
            }

        });

        var Configs = Marionette.CompositeView.extend({
            tagName: "table",
            className: "table table-hover",
            template: listTpl,
            childView: Config,
            childViewContainer: "tbody",

            initialize: function(){
                this.listenTo(this.collection, "reset", function(){
                    this.appendHtml = function(collectionView, itemView, index){
                        collectionView.$el.append(itemView.el);
                    }
                });
            },

            onCompositeCollectionRendered: function(){
                this.appendHtml = function(collectionView, itemView, index){
                    collectionView.$el.prepend(itemView.el);
                }
            }
        });

        var Layout = Marionette.LayoutView.extend({
            template: listLayoutTpl,

            regions: {
                configsRegion: "#configs-region"
            }
        });

    return {
        Layout: Layout,
        Configs: Configs,
        Config: Config
    }
});