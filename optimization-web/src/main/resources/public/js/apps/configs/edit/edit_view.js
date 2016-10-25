define(["jquery", "app", "../common/config_form"], function($, ConfigManager, ConfigFormView){

        var EditConfigView = ConfigFormView.extend({
            initialize: function (options) {
                this.title = "Редактирование конфига: " + this.model.get("campaignName");
                this.testCandidates = options.testCandidates  || {};
                EditConfigView.__super__.initialize.apply(this, arguments);
            },

            serializeData: function(){
                return {
                    "autoOptimization" : this.model.get('autoOptimization'),
                    "campaignName" : this.model.get('campaignName'),
                    "maxRedirects" : this.model.get('maxRedirects'),
                    "maxTestRedirect" : this.model.get('maxTestRedirect'),
                    "percentage" : this.model.get('percentage'),
                    "testCandidates": this.testCandidates
                }
            },

            onRender: function () {
                if (this.options.generateTitle) {
                    var $title = $("<h3>", {text: this.title});
                    this.$el.prepend($title);
                }

                var that = this;
                this.$el.find("select option").each(function(){
                    var inputEl = $(this);
                    if (that.model.get("testCampaignId") == inputEl.val()){
                        inputEl.prop('selected', true);
                    }
                });
            }
        });

    return EditConfigView;
});