package com.pragma.home360.infrastructure.constants;

public final class ApiConstants {

    // Base API path
    public static final String API_V1 = "/api/v1";

    // Authentication endpoints
    public static final String AUTH_PATH = API_V1 + "/auth/**";

    // User endpoints
    public static final String USERS_BASE = API_V1 + "/users";
    public static final String USERS_PATH = USERS_BASE + "/**";
    public static final String USERS_VENDEDOR = USERS_BASE + "/vendedor";

    // Category endpoints
    public static final String CATEGORY_BASE = API_V1 + "/category";
    public static final String CATEGORY_PATH = CATEGORY_BASE + "/**";

    // Location endpoints
    public static final String LOCATION_BASE = API_V1 + "/Location";
    public static final String LOCATION_PATH = LOCATION_BASE + "/**";

    public static final String DEPARTMENT_BASE = API_V1 + "/department";
    public static final String DEPARTMENT_PATH = DEPARTMENT_BASE + "/**";

    public static final String CITY_BASE = API_V1 + "/city";
    public static final String CITY_PATH = CITY_BASE + "/**";

    // House endpoints
    public static final String HOUSE_BASE = API_V1 + "/house";
    public static final String HOUSE_PATH = HOUSE_BASE + "/**";
    public static final String HOUSE_SEARCH = HOUSE_BASE + "/search";

    // Documentation endpoints
    public static final String SWAGGER_UI = "/swagger-ui/**";
    public static final String API_DOCS = "/v3/api-docs/**";

    // Roles
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_VENDEDOR = "VENDEDOR";
    public static final String ROLE_COMPRADOR = "COMPRADOR";

    // Private constructor to prevent instantiation
    private ApiConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}