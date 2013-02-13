/**
 * Created with IntelliJ IDEA.
 * User: Rokonoid
 * Date: 11/29/12
 * Time: 10:38 AM
 * To change this template use File | Settings | File Templates.
 */


$(function () {
    window.MyContent = Backbone.Model.extend({
            defaults: {
                content: "you have wrote nothing yet, write something...",
                done: false
            },
            initialize: function () {
                if (!this.get("content'")) {
                    this.set({"content": this.defaults.content});
                }
            },
            toggle: function () {
                this.save(
                    { done: !this.get("done")}
                );
            }
        }
    );

    window.MyContentList = Backbone.Collection.extend({
        // Reference to this collection's model.
        model: MyContent,

        url: "write/about",

        // Filter down the list of all myContents items that are finished.
        done: function () {
            return this.filter(function (myContents) {
                return myContents.get('done');
            });
        },

        // Filter down the list to only myContents items that are still not finished.
        remaining: function () {
            return this.without.apply(this, this.done());
        },

        // We keep the myContents in sequential order, despite being saved by unordered
        // GUID in the database. This generates the next order number for new items.
        nextOrder: function () {
            if (!this.length) return 1;
            return this.last().get('order') + 1;
        },

        // myContents are sorted by their original insertion order.
        comparator: function (myContents) {
            return myContents.get('order');
        }
    });

    window.MyContents = new MyContentList;


// MyContentView Item View
// --------------

// The DOM element for a MyContent item...
    window.MyContentView = Backbone.View.extend({

        //... is a list tag.
        tagName: "li",

        // Cache the template function for a single item.
        template: _.template($('#item-template').html()),

        // The DOM events specific to an item.
        events: {
            "click .check": "toggleDone",
            "dblclick div.myConent-content": "edit",
            "click span.myConent-destroy": "clear",
            "keypress .myConent-input": "updateOnEnter"
        },

        // The MyContentView listens for changes to its model, re-rendering. Since there's
        // a one-to-one correspondence between a **MyContent** and a **MyContentView** in this
        // app, we set a direct reference on the model for convenience.
        initialize: function () {
            this.model.bind('change', this.render, this);
            this.model.bind('destroy', this.remove, this);
        },

        // Re-render the contents of the todo item.
        render: function () {
            $(this.el).html(this.template(this.model.toJSON()));
            this.setContent();
            return this;
        },

        // To avoid XSS (not that it would be harmful in this particular app),
        // we use `jQuery.text` to set the contents of the todo item.
        setContent: function () {
            var content = this.model.get('content');
            this.$('.myConent-content').text(content);
            this.input = this.$('.myConent-input');
            this.input.bind('blur', _.bind(this.close, this));
            this.input.val(content);
        },

        // Toggle the `"done"` state of the model.
        toggleDone: function () {
            this.model.toggle();
        },

        // Switch this view into `"editing"` mode, displaying the input field.
        edit: function () {
            $(this.el).addClass("editing");
            this.input.focus();
        },

        // Close the `"editing"` mode, saving changes to the todo.
        close: function () {
            this.model.save({content: this.input.val()});
            $(this.el).removeClass("editing");
        },

        // If you hit `enter`, we're through editing the item.
        updateOnEnter: function (e) {
            if (e.keyCode == 13) this.close();
        },

        // Remove this view from the DOM.
        remove: function () {
            $(this.el).remove();
        },

        // Remove the item, destroy the model.
        clear: function () {
            this.model.destroy();
        }
    });


// The Application
// ---------------

// Our overall **AppView** is the top-level piece of UI.
    window.AppView = Backbone.View.extend({

        // Instead of generating a new element, bind to the existing skeleton of
        // the App already present in the HTML.
        el: $("#myContentApp"),

        // Our template for the line of statistics at the bottom of the app.
        statsTemplate: _.template($('#stats-template').html()),

        // Delegated events for creating new items, and clearing completed ones.
        events: {
            "keypress #new-todo": "createOnEnter",
            "keyup #new-todo": "showTooltip",
            "click .todo-clear a": "clearCompleted"
        },

        // At initialization we bind to the relevant events on the `Todos`
        // collection, when items are added or changed. Kick things off by
        // loading any preexisting todos that might be saved in *localStorage*.
        initialize: function () {
            this.input = this.$("#new-contents");

            Todos.bind('add', this.addOne, this);
            Todos.bind('reset', this.addAll, this);
            Todos.bind('all', this.render, this);

            Todos.fetch();
        },

        // Re-rendering the App just means refreshing the statistics -- the rest
        // of the app doesn't change.
        render: function () {
            this.$('#todo-stats').html(this.statsTemplate({
                total: Todos.length,
                done: Todos.done().length,
                remaining: Todos.remaining().length
            }));
        },

        // Add a single todo item to the list by creating a view for it, and
        // appending its element to the `<ul>`.
        addOne: function (myContents) {
            var view = new MyContentView({model: myContents});
            this.$("#myContents-list").append(view.render().el);
        },

        // Add all items in the **Todos** collection at once.
        addAll: function () {
            MyContentList.each(this.addOne);
        },

        // Generate the attributes for a new Todo item.
        newAttributes: function () {
            return {
                content: this.input.val(),
                order: MyContentList.nextOrder(),
                done: false
            };
        },

        // If you hit return in the main input field, create new **Todo** model,
        // persisting it to *localStorage*.
        createOnEnter: function (e) {
            if (e.keyCode != 13) return;
            Todos.create(this.newAttributes());
            this.input.val('');
        },

        // Clear all done todo items, destroying their models.
        clearCompleted: function () {
            _.each(MyContentList.done(), function (todo) {
                todo.destroy();
            });
            return false;
        },

        // Lazily show the tooltip that tells you to press `enter` to save
        // a new todo item, after one second.
        showTooltip: function (e) {
            var tooltip = this.$(".ui-tooltip-top");
            var val = this.input.val();
            tooltip.fadeOut();
            if (this.tooltipTimeout) clearTimeout(this.tooltipTimeout);
            if (val == '' || val == this.input.attr('placeholder')) return;
            var show = function () {
                tooltip.show().fadeIn();
            };
            this.tooltipTimeout = _.delay(show, 1000);
        }

    });

// Finally, we kick things off by creating the **App**.
    window.App = new AppView;
});