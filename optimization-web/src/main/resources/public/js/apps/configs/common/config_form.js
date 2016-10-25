define(["jquery", "underscore", "app",
        "apps/configs/common/missing_config",
        "tpl!apps/configs/common/templates/config_form.tpl",
        "backbone.syphon"],
function($, _, ConfigManager, MissingConfigView, formTpl){

        var Form = Marionette.LayoutView.extend({
            template: formTpl,

            events: {
                "click button.js-submit": "submitClicked"
            },

            triggers: {
                "click button.js-cancel": "form:cancel"
            },

            regions: {
            },

            onBeforeShow: function() {
            },

            submitClicked: function(e){
                e.preventDefault();

                var data = Backbone.Syphon.serialize(this);
                data.testCampaignId = this.$el.find("option:selected").val();

                if (data.testCampaignId && data.testCampaignId.length > 0)
                    data.testCampaignName = this.$el.find("option:selected").text();
                else
                    data.testCampaignName = "";

                this.trigger("form:submit", data);
            },

            onFormDataInvalid: function(errors){
                var $view = this.$el;

                var clearFormErrors = function(){
                    var $form = $view.find("form");
                    $form.find(".help-block").each(function(){
                        $(this).remove();
                    });
                    $form.find(".form-group.has-error").each(function(){
                        $(this).removeClass("has-error");
                    });
                };

                var markErrorSubmit = function(){
                    var $submitElDiv = $view.find(".js-submit").closest("div .col-sm-10");
                    var $submitElError = $("<span>", {class: "help-block", text: "Исправьте ошибки заполнения формы"});
                    $submitElDiv.append($submitElError).addClass("has-error");
                };

                var markErrors = function(value, key){
                    var $controlGroup1 = $view.find("#config-" + key).parent();
                    var $controlGroup = $controlGroup1.closest("div .form-group");
                    var $errorEl = $("<span>", {class: "help-block", text: value});
                    $controlGroup.append($errorEl).addClass("has-error");
                };

                clearFormErrors();
                markErrorSubmit();
                _.each(errors, markErrors);
            }
        });

    return Form;
});
