package shift.borsch.controllers;

public class Resources {
    public static final String ROOT_PREFIX = "/";
    public static final String LOGIN_PREFIX = "/login";
    public static final String LOGOUT_PREFIX = "/logout";
    public static final String USER_PREFIX = "/id{idUser}";
    public static final String FOOD_PREFIX = "/id{idUser}/food/";
    public static final String FRIDGE_PREFIX = "/id{idUser}/fridge/";
    public static final String RECIPE_PREFIX = "/id{idUser}/recipes/";
    public static final String PRODUCT_BY_RECIPE_PREFIX = "/id{idUser}/recipe={idRecipe}/product/";

    public static final String ADMIN_PREFIX = "/admin/";

    public static final String SUCCESS_STATUS = "OK";
    public static final String UNSUCCESS_STATUS = "NOT OK";
}