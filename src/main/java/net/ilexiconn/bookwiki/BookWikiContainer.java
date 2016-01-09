package net.ilexiconn.bookwiki;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * @author iLexiconn
 */
public class BookWikiContainer {
    private Category[] categories;
    private Page[] pages;
    private Recipe[] recipes;

    private String generalCategory;

    public Category[] getCategories() {
        return categories;
    }

    public Page[] getPages() {
        return pages;
    }

    public Recipe[] getRecipes() {
        return recipes;
    }

    public String getGeneralCategory() {
        return generalCategory;
    }

    public class Category extends ContainerHandler {
        private String id;
        private String name;
        private String icon;
        private transient ItemStack iconInstance;
        private String defaultPage;
        private transient Page pageInstance;

        public String getID() {
            return id;
        }

        public String getName() {
            return name;
        }

        public ItemStack getIcon() {
            if (iconInstance == null) {
                Item item = Item.getByNameOrId(icon);
                if (item != null) {
                    iconInstance = new ItemStack(item);
                } else {
                    BookWiki.logger.error("Can't find item or block with name " + icon);
                    iconInstance = new ItemStack(Blocks.crafting_table); //TODO: Switch to a better fallback item.
                }
            }
            return iconInstance;
        }

        public Page getDefaultPage() {
            if (pageInstance == null) {
                for (Page page : container.getPages()) {
                    if (page.getID().equals(defaultPage) && page.getCategory() == this) {
                        pageInstance = page;
                        break;
                    }
                }
            }
            return pageInstance;
        }
    }

    public class Page extends ContainerHandler {
        private String id;
        private String content;
        private String category;
        private transient Category categoryInstance;

        public String getID() {
            return id;
        }

        public String getContent() {
            return content;
        }

        public Category getCategory() {
            if (categoryInstance == null) {
                for (Category category : container.getCategories()) {
                    if (category.getID().equals(this.category)) {
                        categoryInstance = category;
                        break;
                    }
                }
            }
            return categoryInstance;
        }
    }

    public class Recipe extends ContainerHandler {
        private String id;
        private boolean shapeless;
        private String[] recipe;
        private transient ItemStack[] recipeInstance;
        private String result;
        private transient ItemStack resultInstance;

        public String getID() {
            return id;
        }

        public boolean isShapeless() {
            return shapeless;
        }

        public ItemStack[] getRecipe() {
            if (recipeInstance == null) {
                if (recipe.length == 9) {
                    recipeInstance = new ItemStack[9];
                    for (int i = 0; i < 9; i++) {
                        String s = recipe[i];
                        if (!s.isEmpty()) {
                            Item item = Item.getByNameOrId(s);
                            if (item != null) {
                                recipeInstance[i] = new ItemStack(item);
                            } else {
                                BookWiki.logger.error("Can't find item or block with name " + s);
                                recipeInstance[i] = null; //TODO: Use a fallback item.
                            }
                        }
                    }
                } else {
                    throw new RuntimeException("Can't create recipe with less than 9 slots");
                }
            }
            return recipeInstance;
        }

        public ItemStack getResult() {
            if (resultInstance == null) {
                int stackSize = 1;
                if (result.contains("*")) {
                    String[] s = result.split("\\*");
                    result = s[0];
                    stackSize = Integer.parseInt(s[1]);
                }
                Item item = Item.getByNameOrId(result);
                if (item != null) {
                    resultInstance = new ItemStack(item, stackSize);
                } else {
                    BookWiki.logger.error("Can't find item or block with name " + result);
                    resultInstance = new ItemStack(Blocks.crafting_table); //TODO: Switch to a better fallback item.
                }
            }
            return resultInstance;
        }
    }

    public class ContainerHandler {
        BookWikiContainer container;

        public void setContainer(BookWikiContainer container) {
            this.container = container;
        }
    }
}
